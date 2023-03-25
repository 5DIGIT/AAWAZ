package com.practice.aawaz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;

import okhttp3.RequestBody;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;
import org.json.JSONException;
import org.json.JSONObject;


public class Registration_Activity extends AppCompatActivity {

    String msg1 = "",msg2 = "",output = "";

    Button jus;
    TextInputLayout username, userphone;
    TextView tx;
    OkHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_registration);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String url = "http://b4db-2401-4900-4637-d767-65b0-eb9-1e7d-585b.in.ngrok.io/aawaz/complaints/user/10";
        RequestQueue queue = Volley.newRequestQueue(this);

        //hooks
        jus = findViewById(R.id.joinUs);
        username = findViewById(R.id.userName);
        userphone = findViewById(R.id.userPhoneNumber);
        JSONObject userData = new JSONObject();


        jus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Create a JSON object to hold the user data.

                try {
                    // Get the user's input from the TextInputLayouts.
                    String name = username.getEditText().getText().toString();
                    String phone = userphone.getEditText().getText().toString();

                    // Add the name and phone number fields to the JSON object.
                    userData.put("name", name);
                    userData.put("phone", phone);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // Create a new JsonObjectRequest.
                JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, userData,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // Handle the response from the server.
                                Log.d("POST Response", response.toString());
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Handle the error.
                                Log.e("POST Error", error.toString());
                            }
                        });
                queue.add(jsonRequest);
                System.out.println(userData);
            }
        });
    }
}
