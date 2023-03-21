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

This is the Android component of our project, which was developed using XML and Java programming languages.

The application facilitates user authentication for login and registration purposes, allowing them to report any issues in their vicinity by taking a photo and submitting it to the server with a single click. The server is equipped with a machine learning model that evaluates the submitted content to determine if it is spam. If the content is deemed non-spam, it is forwarded to the relevant authorities for further action.

The application comprises several components, including a login screen, location screen, registration window, and the primary capture intent. The location screen is included to demonstrate how the application can access a user's live location and transmit it to the server along with the captured image, enabling the machine learning model to provide relevant information to the appropriate authorities for assistance.

The login page will authenticate user with phone OTP but for now it has been made static intentionaly for simplicity.
It will be updated in future updates.

# Example
A user posting a valid complain photo request from Gomti Nagar Extension Lucknow having pincode 226010 will be informed to Makdurpur Chauki for further help.

# Alternate Download
You can download the apk file and run the application from the apk  file uploaded in this section.

# Note
Please give a considerate time to app after giving location permission as it is slow to fetch the device location for now.
