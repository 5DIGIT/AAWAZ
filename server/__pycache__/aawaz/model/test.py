import pickle
import skimage.io as io
import os
from PIL import Image

priority = {'Image': [], 'Priority': []}
# Loading Model using pickle
# Model path
path = 'aawaz/model/accident_model'
road_path = 'aawaz/model/road_model'
fire_path = 'aawaz/model/fire_model'


accident_model = pickle.load(open(path, "rb"))
road_model = pickle.load(open(road_path, "rb"))
fire_model = pickle.load(open(fire_path, "rb"))

def prediction(image):

    if road_model.predict(image) == 0:
        return "Not road"
    if fire_model.predict(image) == 1:
        return "fire"
    elif accident_model.predict(image) == 1:
        return "Accident"
    else:
        return "No Accident"

def model(path):


    # Loading two images using absolute path
    #img_1 = f'images/{path}'
    #img_1 = f'images/{path}'
    img_1 = path

    def res(f_img):
        img = Image.open(f_img)
        img = img.resize((128, 128))
        img.save(f_img)


    res(img_1)

    # Applying this transformation to the image (Code sent on mail to ayam)
    tep = io.imread(img_1, as_gray=True).reshape(1, -1)

    return prediction(tep)

#print(model('aawaz/model/images (4).jpg'))