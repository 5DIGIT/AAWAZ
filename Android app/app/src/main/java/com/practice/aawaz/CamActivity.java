package com.practice.aawaz;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CamActivity extends AppCompatActivity {

    ImageView imgcap;
    Button bt;
    Button sendimage;
    String encodedimage = "";
    String addressString = "";
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_cam);

        // added
        // StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        // StrictMode.setThreadPolicy(policy);
        String url = "http://b4db-2401-4900-4637-d767-65b0-eb9-1e7d-585b.in.ngrok.io/aawaz/complaints/user/10";
        // http://192.168.137.1:5000/send-image/
        // http://192.168.137.147:8000/aawaz/users/
        // RequestQueue queue = Volley.newRequestQueue(this);
        // JSONObject userData = new JSONObject();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            addressString = extras.getString("completeaddress");
            //The key argument here must match that used in the other activity
        }

        imgcap = findViewById(R.id.capturedimg);
        bt = findViewById(R.id.capturedbutton);
        sendimage = findViewById(R.id.sendencodedimage);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateandTime = sdf.format(new Date());

        String[] arrofstr = addressString.split(",",-2);
        String[] pinstate= arrofstr[arrofstr.length-2].split(" ",2);

        StringTokenizer str = new StringTokenizer(arrofstr[arrofstr.length-2]," ");

        String state = str.nextToken();
        String pin = str.nextToken();

        String lat = arrofstr[0];
        String longi = arrofstr[1];

        String latlong = lat.trim() + "$" + longi.trim();


        String city = arrofstr[arrofstr.length - 3];

        if (ContextCompat.checkSelfPermission(CamActivity.this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(CamActivity.this, new String[]{
                    Manifest.permission.CAMERA

            }, 100);
        }

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Print 3:" + addressString);
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 100);
            }
        });

        sendimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //output

                for(String a :arrofstr)
                    System.out.println(a);

                System.out.println(city+","+pin+","+state);

                System.out.println("   "+latlong);


                //Initialize byte stream
                ByteArrayOutputStream stream = new ByteArrayOutputStream();

                //compress bitmap
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);

                //Initialize byte array

                byte[] bytes = stream.toByteArray();

                //Get base64 encoded string
                // encodedimage = Base64.encodeToString(bytes,Base64.DEFAULT);

                OkHttpClient client = new OkHttpClient();

                RequestBody requestBody = new MultipartBody.Builder()

                        .setType(MultipartBody.FORM)

                        .addFormDataPart("img", "image.jpg", RequestBody.create(MediaType.parse("image/jpeg"), bytes))
                        //.addFormDataPart("location",addressString)
                        .addFormDataPart("time",currentDateandTime)
                        .addFormDataPart("pincode",pin)
                        .addFormDataPart("state",state)
                        .addFormDataPart("city",city)
                        .addFormDataPart("latlong",latlong)


                        .build();

                // Build request with URL and request body

                Request requests1 = new Request.Builder()

                        .url(url)

                        .post(requestBody)

                        .build();

                // Send request asynchronously and handle response

                Toast.makeText(CamActivity.this, "Report sent successfully", Toast.LENGTH_LONG).show();


                client.newCall(requests1).enqueue(new Callback() {

                    @Override

                    public void onFailure(Call call, IOException e) {

                        e.printStackTrace();

                        // Handle failure here

                    }

                    @Override

                    public void onResponse(Call call, Response response) throws IOException {

                        if (response.isSuccessful()) {

                            // Handle success here



                        } else {

                            // Handle failure here


                        }
                    }

                });

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100) {

            // image stored in bitmap
            bitmap = (Bitmap) data.getExtras().get("data");
            imgcap.setImageBitmap(bitmap);

        }
    }
}
