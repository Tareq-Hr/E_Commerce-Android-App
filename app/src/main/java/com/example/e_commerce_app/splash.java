package com.example.e_commerce_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class splash extends AppCompatActivity {
    private static  int SPLASH_SCREEN=5000;
    Animation topAnim, bottomAnim,leftanim,rightanim;
    ImageView image,image3;
    TextView logo,text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        leftanim=AnimationUtils.loadAnimation(this, R.anim.leftanim);
        rightanim=AnimationUtils.loadAnimation(this, R.anim.rightanim);
        image=findViewById(R.id.imageView3);
        logo=findViewById(R.id.textView6);
        image3=findViewById(R.id.imageView5);
        text2=findViewById(R.id.textView7);
        image.setAnimation(topAnim);
        logo.setAnimation(bottomAnim);
        image3.setAnimation(rightanim);
        text2.setAnimation(leftanim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent= new Intent(splash.this,accueil.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);
    }
}