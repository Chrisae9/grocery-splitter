from rest_framework import routers
from rest_framework import urlpatterns
from .api import ReceiptsViewSet

router = routers.DefaultRouter()
router.register('api/receipts', ReceiptsViewSet, 'receipts')

urlpatterns = router.urls