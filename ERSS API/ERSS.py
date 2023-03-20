# this app is to simulate an ERSS API
# the main server sends an http request to this API when it detects an emergency condition

from flask import Flask, jsonify, request
from PIL import Image
import base64
from io import BytesIO

# initialising the flask app
app = Flask(__name__)


# receiving accident image and location{latidude and longitude}


@app.route('/emergency/', methods=['POST'])
def success():
    if request.method == 'POST':
        f = request.files['img']
        latitude = request.form.get('latitude')
        longitude = request.form.get('longitude')
        f.save("image/"+f.filename)
        return jsonify({'maybe': 'success', 'latitude': latitude, 'longitude': longitude})


# driver function
if __name__ == '__main__':
    # turn debug to false on actual deployment
    app.run(host='192.168.137.1', debug=True)
