import json
from django.shortcuts import render
from django.http import HttpResponse, JsonResponse
from ranking.models import Vote

from dotenv import load_dotenv
import os

load_dotenv()

# ENV TEST
def say_hello(request):
    profileurl = os.environ.get('PROFILE_URL')
    return HttpResponse(profileurl)



def isActive(request):
    return HttpResponse("Service is Active", status=200)



#############################
# Business votes controller #
#############################

class VoteDto():
    def __init__(self, email, vote):
        self.email = email
        self.vote = vote

    def __dict__(self):
        return {
            'email': self.email,
            'vote': self.vote
        }

def add_vote(request):
    email = request.GET.get('email')
    review_vote = request.GET.get('vote')

    if email and review_vote:
        try:
            vote = Vote.objects.get(email=email)
            vote.voteSum += float(review_vote)
            vote.cont += 1
            vote.save()
        except Vote.DoesNotExist:
            vote = Vote(email=email, voteSum=int(review_vote), cont=1)
            vote.save()

        return JsonResponse({'message': 'Vote added successfully'}, status=200)
    else:
        return JsonResponse({'error': 'Email or vote parameter is missing'}, status=400)



def get_vote_by_email(request):
    email = request.GET.get('email')
    if email:
        try:
            vote = Vote.objects.get(email=email)
            vote_avg = vote.voteSum / vote.cont if email else 0
            return HttpResponse( vote_avg, content_type="application/json")
        except Vote.DoesNotExist:
            return HttpResponse({'error': 'No vote found for the provided email'}, status=404)
    else:
        return HttpResponse({'error': 'Email parameter is missing'}, status=400)



def get_all_votes(request):
    try:
        votes = Vote.objects.all().values('email', 'voteSum', 'cont')
        vote_list = {item['email']: (item['voteSum'] / item['cont']) for item in votes}

        return JsonResponse({'votes': vote_list}, content_type="application/json")
    except Exception as e:
        return JsonResponse({'error': str(e)}, status=500)
