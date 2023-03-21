import requests
#from .models import Users, Complaints

url = "http://192.168.137.1:5000/emergency/"
def get_complaints(image,latlong,description):
    # complaint = Complaints.objects.filter(complaint_id=complaint_id)    
    # print(complaint)
    img = open(image, 'rb')
    #print(a.read())
    file = {'img': img}
    r = requests.post(url, files=file, data={'latlong': latlong, 'description': description})
    data = r.json()
    #print(data)


#get_complaints('image.jpg', '77.123', '28.123')