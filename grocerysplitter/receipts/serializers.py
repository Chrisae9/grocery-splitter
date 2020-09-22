from rest_framework import serializers
from .models import Receipts

# Receipts Serializer
class ReceiptsSerializer(serializers.ModelSerializer):
    class Meta:
        model = Receipts
        fields = '__all__'