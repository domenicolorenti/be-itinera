from django.db import models

class Vote(models.Model):
    cod = models.AutoField(primary_key=True)
    email = models.CharField(max_length=60, null=False, unique=True)
    voteSum = models.IntegerField(default=0, null=False)
    cont = models.IntegerField(default=0, null=False)