from django.urls import include,path
from .views import users,usersName,complaintsUser,complaints
from rest_framework import routers


# router = routers.DefaultRouter()
# router.register(r'users', UsersViewSet, basename='users')
# router.register(r'complaints', ComplaintsViewSet)


urlpatterns = [
    #path('', include(router.urls)),
    path('users/', users, name='users'),
    path('users/<str:phone>', usersName, name='usersname'),
    path('complaints/user/<str:user_id>', complaintsUser, name='complaints_user'),
    path('complaints/<str:complaint_id>', complaints, name='complaints')
]