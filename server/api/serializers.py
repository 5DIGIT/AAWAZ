from rest_framework import serializers

from .models import Users, Complaints

class UsersSerializer(serializers.ModelSerializer):
   class Meta:
       model = Users
       fields = ('user_id','name', 'phone')

class UsernameSerializer(serializers.ModelSerializer):
   class Meta:
       model = Users
       fields = ('user_id','name',)

class ComplaintsSerializer(serializers.ModelSerializer):
   class Meta:
       model = Complaints
       fields = ('user_id','complaint_id', 'img', 'description', 'department', 'status', 'location','latlong', 'city', 'state', 'pincode', 'time')

class ComplaintsuserSerializer(serializers.ModelSerializer):
   class Meta:
       model = Complaints
       fields = ('complaint_id', 'status')