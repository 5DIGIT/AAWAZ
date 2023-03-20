ML Model

The train.py file trains three separate models (one is demonstrated). Each model is trained using the sklearn Linear Model Perceptron algorithm. Datasets taken from kaggle were used to create the model. preprocessing.py takes in images and formats them in a way that the trainer and model can read it properly.

These models require large datasets to give better accuracy. But due to lack of resources we were able to train the models with only 200 images each. Which impacts the accuracy of the model substantially. But with a larger dataset the same models would give a good enough accuracy for our usecase.
