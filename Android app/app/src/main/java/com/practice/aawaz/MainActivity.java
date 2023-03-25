package com.practice.aawaz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private static final int SPLASH = 5000;

    // variables
    Animation top, bottom;
    ImageView img;
    TextView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        // Animations

        top = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottom = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        //hooks

        img = findViewById(R.id.imageView);
        logo = findViewById(R.id.textView);

        img.setAnimation(top);
        logo.setAnimation(bottom);

        boolean b = new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, LocationActivity.class);

                Pair[] pairs = new Pair[2];
                pairs[0] = new Pair<View, String>(img, "logo_image");
                pairs[1] = new Pair<View, String>(logo, "logo_text");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, pairs);
                startActivity(intent, options.toBundle());
                finish();
            }
        }, SPLASH);
    }
}