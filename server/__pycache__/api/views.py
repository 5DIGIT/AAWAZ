from django.shortcuts import render
from django.http import HttpResponse
from rest_framework import viewsets
from api.serializers import UsersSerializer, ComplaintsSerializer, UsernameSerializer, ComplaintsuserSerializer
from .models import Users, Complaints
from rest_framework.decorators import api_view
from django.views.decorators.csrf import csrf_exempt, csrf_protect
from rest_framework.response import Response
from django.http import JsonResponse
from .responce import get_complaints
from  aawaz.model.test import model


# class UsersViewSet(viewsets.ModelViewSet):
#    queryset = Users.objects.all()
#    serializer_class = UsersSerializer
#    # def get_queryset(self):
#    #    query = self.request.GET.get('query', None)
#    #    self.queryset = Users.objects.filter(title__icontains=query)
#    #    return self.queryset

@api_view(['GET', 'POST'])
def users(request):
   if request.method == 'GET':
      users = Users.objects.all()
      serializer = UsersSerializer(users, many=True)
      return JsonResponse(serializer.data, safe=False)
   if request.method == 'POST':
      serializer = UsersSerializer(data=request.data)
      if serializer.is_valid():
         serializer.save()
         return JsonResponse({'message': 'User created successfully'}, status=201)
      return JsonResponse(serializer.errors, status=400)
   
@api_view(['GET','DELETE'])
def usersName(request, phone):
   if request.method == 'GET':
      user = Users.objects.filter(phone=phone)
      #print(user)
      #print(user)
      serializer = UsernameSerializer(user, many=True)
      #print(serializer.validated_data.get('name'))
      if serializer.data:
         return JsonResponse(serializer.data, safe=False)
      return JsonResponse({'message':'User does not exist'}, status=400)

   if request.method == 'DELETE':
      Users.objects.filter(phone=phone).delete()
      return JsonResponse({'message': 'User deleted successfully'}, status=204)

@api_view(['GET', 'POST'])
@csrf_exempt
def complaintsUser(request, user_id):
   if request.method == 'GET':
         complaint = Complaints.objects.filter(user_id=user_id)
         serializer = ComplaintsuserSerializer(complaint, many=True)
         if serializer.data:
            return JsonResponse(serializer.data, safe=False)
         return JsonResponse({'message':'No complaints yet'}, status=400)

   if request.method == 'POST':
      serializer = ComplaintsSerializer(data=request.data)
      if serializer.is_valid():
         serializer.save()
         print(serializer.validated_data.get('img'))
         #path = 'images/'+str(serializer.validated_data.get('img')).replace(' ','_')
         path ='images/image_4pFSvnb.jpg'
         print(path)
         r = model(path)
         if r != 'Not road' or r != 'No Accident':
            print('here')
            print(r)
            #Complaints.objects.filter(complaint_id=serializer.validated_data.get('complaint_id')).update(status=r)
            get_complaints(path, request.data['latlong'], r)
         return JsonResponse({'message': 'Complaint created successfully'}, status=201)
      else:
         return JsonResponse(serializer.errors, status=400)

@api_view(['GET'])
def complaints(request, complaint_id):
   if request.method == 'GET':
      complaint = Complaints.objects.filter(complaint_id=complaint_id)
      serializer = ComplaintsSerializer(complaint,many=True)
      if serializer.data:
         return JsonResponse(serializer.data, safe=False)
      return JsonResponse({'message':'Complaint does not exist'}, status=400)
