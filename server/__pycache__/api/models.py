from django.db import models


class Users(models.Model):
   user_id = models.AutoField(primary_key=True,auto_created=True)
   name = models.CharField(max_length=100,null=False)
   phone = models.CharField(unique=True,max_length=10,null=False)

class Complaints(models.Model):
   complaint_id = models.AutoField(primary_key=True,auto_created=True)
   user_id = models.ForeignKey(Users, on_delete=models.DO_NOTHING,default='1')
   img = models.ImageField(upload_to=f'images/',null=False)
   description = models.CharField(max_length=1000,default='Pending')
   department = models.CharField(max_length=100,default='Pending')
   status = models.CharField(max_length=50,default='Pending')
   location = models.CharField(max_length=100,default='Pending')
   latlong = models.CharField(max_length=100,default='Pending')
   city = models.CharField(max_length=50,default='Pending') 
   state = models.CharField(max_length=50,default='Pending')
   pincode = models.CharField(max_length=6,default='000000')
   time = models.DateTimeField(default='2020-01-01 00:00:00+00:00') 
