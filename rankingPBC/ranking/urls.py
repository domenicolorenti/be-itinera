from django.urls import path
from . import views

# URLConf
urlpatterns = [
    path('hello/', views.say_hello),
    path('addVote/', views.add_vote),
    path('getVote/', views.get_vote_by_email),
    path('getAllVotes/', views.get_all_votes),
    path('isActive/', views.isActive)
]