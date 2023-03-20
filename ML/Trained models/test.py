#the following code is to test the models we created 

import pickle
import skimage.io as io
import os
from PIL import Image

priority = {'Image': [], 'Priority': []}


def prediction(image):

    if road_model.predict(image) == 0:
        print("Image is not a road")
        return
    if fire_model.predict(image) == 1:
        print("fire...")
        print(" ")
    elif accident_model.predict(image) == 0:
        print("Image contains accident")
    else:
        print("No Accident")


# Loading Model using pickle
# Model path
path = 'accident_model'
road_path = 'road_model'
fire_path = 'fire_model'


accident_model = pickle.load(open(path, "rb"))
road_model = pickle.load(open(road_path, "rb"))
fire_model = pickle.load(open(fire_path, "rb"))


# Loading two images using absolute path
img_1 = 'C:/Users/ayamb/Desktop/project/AWAAZ/model/acc.jpg'
car = 'C:/Users/ayamb/Desktop/project/AWAAZ/model/car.jpg'
anuneet = 'C:/Users/ayamb/Desktop/project/AWAAZ/model/ayam.jpg'
firr = 'C:/Users/ayamb/Desktop/project/AWAAZ/model/firrr.jpg'


def res(f_img):
    img = Image.open(f_img)
    img = img.resize((128, 128))
    img.save(f_img)


res(img_1)
res(car)
res(anuneet)
res(firr)
# Applying this transformation to the image (Code sent on mail to ayam)
tep = io.imread(img_1, as_gray=True).reshape(1, -1)
tep1 = io.imread(car, as_gray=True).reshape(1, -1)
tepf = io.imread(anuneet, as_gray=True).reshape(1, -1)
tep_Fire = io.imread(firr, as_gray=True).reshape(1, -1)


prediction(tep)
# print(road_model.predict(tepf))
# print(accident_model.predict(tepf))
# print(fire_model.predict(tepf))
