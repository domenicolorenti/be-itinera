from django.contrib import admin
from .models import Vote

# Register your models here.

@admin.register(Vote)
class VoteAdmin(admin.ModelAdmin):
    pass
