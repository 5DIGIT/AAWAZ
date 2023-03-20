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
        print("Image contains fire")
        print(" ")
    elif accident_model.predict(image) == 1:
        print("Image contains accident")
        print(" ")
    else: print("No Accident")
    return


# Loading Model using pickle
# Model path
path = '/Users/adityapramar/Desktop/Awaaz22/accident_model'
road_path = '/Users/adityapramar/Desktop/Awaaz22/road_model'
fire_path = '/Users/adityapramar/Desktop/Awaaz22/fire_model'


accident_model = pickle.load(open(path, "rb"))
road_model = pickle.load(open(road_path, "rb"))
fire_model = pickle.load(open(fire_path, "rb"))


# Loading two images using absolute path
acc_img = '/Users/adityapramar/Desktop/Awaaz22/accident/Screenshot 2023-03-20 at 9.29.35 PM.png'
no_acc_img = '/Users/adityapramar/Desktop/Awaaz22/roadNoAcc.png'
fire_img = '/Users/adityapramar/Desktop/Awaaz22/accident/Screenshot 2023-03-20 at 9.30.25 PM.png'
selfie_img = '/Users/adityapramar/Desktop/Awaaz22/Screenshot 2023-03-20 at 9.29.35 PM.png'


# Applying this transformation to the image (Code sent on mail to ayam)

tep_acc = io.imread(os.path.join(path, acc_img), as_gray=True).reshape(1, -1)
tep_no_acc = io.imread(os.path.join(path, no_acc_img), as_gray=True).reshape(1, -1)
tep_fire = io.imread(os.path.join(path, fire_img), as_gray=True).reshape(1, -1)
tep_selfie = io.imread(os.path.join(path, selfie_img), as_gray=True).reshape(1, -1)


prediction(tep_selfie)

