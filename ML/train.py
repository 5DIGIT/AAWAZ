import os
import skimage.io as io
import numpy as np
from sklearn.model_selection import train_test_split
from sklearn.pipeline import make_pipeline
from sklearn.preprocessing import StandardScaler
from sklearn import linear_model
from sklearn import metrics
import pickle
import imghdr


images = []
labels = []

path2 = '/Users/adityapramar/Desktop/Awaaz22/accident'
path = '/Users/adityapramar/Desktop/Awaaz22/notaccident'
path3 = '/Users/adityapramar/Desktop/Awaaz22/fire'
path4 = '/Users/adityapramar/Desktop/Awaaz22/notroad'
path5 = '/Users/adityapramar/Desktop/Awaaz22/road'

exts = ['jpg', 'png', 'jpeg']

for file in os.listdir(path4):
    f_img = path4+"/"+file
    if imghdr.what(f_img) not in exts:
        os.remove(f_img)
        print("Deleted")
for file in os.listdir(path5):
    f_img = path5+"/"+file
    if imghdr.what(f_img) not in exts:
        print("Deleted")
        os.remove(f_img)


for file in os.listdir(path4):
    tep = io.imread(os.path.join(path4, file), as_gray=True).reshape(1, -1)
    images.append(tep)
    labels.append(0)

for file in os.listdir(path5):
    tep = io.imread(os.path.join(path5, file), as_gray=True).reshape(1, -1)
    images.append(tep)
    labels.append(1)

x = np.concatenate(images, axis=0)


x_train, x_test, y_train, y_test = train_test_split(x, labels, shuffle=True)

clf = make_pipeline(StandardScaler(),
                    linear_model.Perceptron(tol=1e-3))


clf.fit(x_train, y_train)

y_pred = clf.predict(x_test)

acu = metrics.accuracy_score(y_test, y_pred)
print("Accuracy is : ", acu*100, '%')

with open('road_model', 'wb') as files:
    pickle.dump(clf, files)
