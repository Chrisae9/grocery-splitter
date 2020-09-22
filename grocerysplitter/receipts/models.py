from django.db import models

class Receipts(models.Model):
    name = models.CharField(max_length=100)
    items = models.CharField(max_length=500, blank=True)
    created_at = models.DateTimeField(auto_now_add=True)
