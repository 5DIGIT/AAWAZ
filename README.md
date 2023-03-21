# AAWAZ
A citizen centric app which will enable people to register any road safety problem, raise an issue or report any violation through an app. Even in case of an accident, as soon as it is reported on app shall send message to police, ambulance and nearby hospital, For violations, police and other stakeholders may take an action and resolve the issue. This app will help to prevent many accidents. The best part is that rather than writing an entire complaint, the people will just have to upload a photo which will be classified on its own by our app.

To get started with out application you must first download the APK. 

# APK
To download the APK of our application you can visit our website.
All the files of this application can be found on the Android branch of this repository.

# ML
Our pre-trained models are available in the ML folder. These models can be directly used using there call functions. To use these models in a piece of code you can see the trial.py file. Which gives an example of the working if all the three models.

These models are incorporated in our server. They tell us whether a given photo is spam, fire or an accident. Other classifications can be added in the future.

# BACKEND
The  ML model classifies the image as spam, accident with fire, accident without fire or no accident. All these triggers, trigger a unique response sequence. When a fire is detected the ERSS is notified that on the accident site there is need of a firebrigade along with a higher urgency rate, on the other hand when an accident without a fire is detected it generates a standard response sequence where ERSS is notified of the accident. In the third case, i.e. when a spam photo is detected the model disregards it and it does not generate a response sequence.
Lastly, when no accident is recognised the server will further classify the images, and detect any defect in the road. On successful detection the respective authorities will be notified. This part of the project will be encorporated in future updates.

# Aawaz Android

This is the android side of our project developed in XML and Java. 

This application helps any user to authenticate its login and registration and then send a complain around him by simply clicking the photo of the problem and send it with a simple click button to the server where an ML model is hosted to determine is it a spam or not.

If not spam it will inform the respected authority for the proceedings.

The login page is intentionally made static, it can be activated easily in the future version of the application.

The application has a -
Login Screen , Location Screen , Registration window and the main Capture Intent.

Location screen has been included to show that we access live location of the user and therefore send it to the server along with the image to help model determine and send the  important information to paticular authority for the help.

# Example
A user posting a valid complain photo request from Gomti Nagar Extension Lucknow having pincode 226010 will be informed to Makdurpur Chauki for further help.

# Alternate Download
You can download the apk file and run the application from the apk  file uploaded in this section.


