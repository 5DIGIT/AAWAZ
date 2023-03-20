import os
import skimage.io as io
from skimage.transform import resize
import numpy as np
from sklearn.model_selection import train_test_split
import matplotlib.pyplot as plt
from skimage.io import imshow
from PIL import Image
from sklearn.pipeline import make_pipeline
from sklearn.preprocessing import StandardScaler
from sklearn import linear_model
from sklearn import metrics
import pickle
import imghdr


path = '/Users/adityapramar/Desktop/Awaaz22/notroad'
path2 = '/Users/adityapramar/Desktop/Awaaz22/notaccident'
path3 = '/Users/adityapramar/Desktop/Awaaz22/fire'

exts = ['jpg', 'png', 'jpeg']


for file in os.listdir(path):
    f_img = path+"/"+file
    if imghdr.what(f_img) in exts:
        img = Image.open(f_img)
        img = img.resize((128, 128))
        img.save(f_img)
        print("DoneACC : ")

