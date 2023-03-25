package com.practice.aawaz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class LocationActivity extends AppCompatActivity {


    List<Address> addresses;


    FusedLocationProviderClient fusedLocationProviderClient;
    TextView lattitude,longitude,address,city,country;
    Button getLocation;
    private final static int REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_location);
        lattitude = findViewById(R.id.lattitude);
        longitude = findViewById(R.id.longitude);
        address = findViewById(R.id.address);
        city = findViewById(R.id.city);
        country = findViewById(R.id.country);
        getLocation = findViewById(R.id.getLocation);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        getLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getLastLocation();

            }
        });

    }

    private void getLastLocation(){




        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){


            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {

                            if (location != null){

                                String completeAddress="";
                                String lat = "";
                                String longi = "";


                                try {
                                    Geocoder geocoder = new Geocoder(LocationActivity.this, Locale.getDefault());
                                    addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

                                    StringBuilder sb = new StringBuilder();
                                    for (int i = 0; i <= addresses.get(0).getMaxAddressLineIndex(); i++) {
                                        sb.append(addresses.get(0).getAddressLine(i)).append(" ");
                                    }



                                    completeAddress = sb.toString();

                                    StringBuilder sb1 = new StringBuilder();
                                    sb1.append(addresses.get(0).getLatitude()).append(" ");


                                    StringBuilder sb2 = new StringBuilder();
                                    sb2.append(addresses.get(0).getLongitude()).append(" ");

                                    lat = sb1.toString();
                                    longi = sb2.toString();

                                    System.out.println(lat+"  "+longi);

                                    completeAddress = lat + "," + longi + "," + completeAddress;



                                    System.out.println("Print 1: "+completeAddress);



                                    lattitude.setText("Lattitude: "+addresses.get(0).getLatitude());
                                    longitude.setText("Longitude: "+addresses.get(0).getLongitude());
                                    address.setText("Address: "+addresses.get(0).getAddressLine(0));
                                    city.setText("City: "+addresses.get(0).getLocality());
                                    country.setText("Country: "+addresses.get(0).getCountryName());



                                    Intent intent = new Intent(LocationActivity.this,LoginActivity.class);
                                    intent.putExtra("completeaddress",completeAddress);

                                    Handler handler = new Handler();

                                    int delay = 1000;

                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            startActivity(intent);
                                        }
                                    },delay);



                                } catch (IOException e) {
                                    e.printStackTrace();
                                }



                            }

                        }
                    });




        }else {

            askPermission();


        }


    }

    private void askPermission() {

        ActivityCompat.requestPermissions(LocationActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @org.jetbrains.annotations.NotNull String[] permissions, @NonNull @org.jetbrains.annotations.NotNull int[] grantResults) {

        if (requestCode == REQUEST_CODE){

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){


                getLastLocation();

            }else {


                Toast.makeText(LocationActivity.this,"Please provide the required permission",Toast.LENGTH_SHORT).show();

            }



        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}