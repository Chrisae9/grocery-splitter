from .models import Receipts
from rest_framework import viewsets, permissions
from .serializers import ReceiptsSerializer

# Receipts Viewset
class ReceiptsViewSet(viewsets.ModelViewSet):
    queryset = Receipts.objects.all()
    permission_classes = [
        permissions.AllowAny
    ]

    serializer_class = ReceiptsSerializer