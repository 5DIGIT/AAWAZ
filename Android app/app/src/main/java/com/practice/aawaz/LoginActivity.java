package com.practice.aawaz;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;




public class LoginActivity extends AppCompatActivity {

    Button logmein, signup,sendotp;
    TextInputLayout phoneno, otpp;


    String msg1 = "";
    String msg2 = "";
    String msg = "";
    String addressString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            addressString = extras.getString("completeaddress");
            //The key argument here must match that used in the other activity
        }


        //Hooks
        logmein = findViewById(R.id.login_button1);
        signup = findViewById(R.id.callSignup);
        phoneno = findViewById(R.id.phone);
        otpp = findViewById(R.id.password);
        sendotp = findViewById(R.id.sendOtp1);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, Registration_Activity.class);
                startActivity(intent);
            }
        });

        logmein.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                msg1 = phoneno.getEditText().getText().toString();
                msg2 = otpp.getEditText().getText().toString();
                msg = "login%" + msg1 + "#" + msg2;

                System.out.println("Print 2: "+addressString);

                Intent intent = new Intent(LoginActivity.this, CamActivity.class);
                intent.putExtra("completeaddress", addressString);
                startActivity(intent);
        };
    });

        sendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "OTP sent", Toast.LENGTH_SHORT).show();
            }
        });


    }

}

