package com.example.admin.donate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Splashscreen extends AppCompatActivity {
    ImageView imgSplash;
    Animation Faded;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        imgSplash = (ImageView) findViewById(R.id.imgSplash);
        Animation();
        imgSplash.startAnimation(Faded);
        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep (2500);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent intent = new Intent(Splashscreen.this,Login_Activity.class);
                    startActivity(intent);

                }
            }
        };
        timerThread.start();

    }
    private void Animation(){

        Faded = AnimationUtils.loadAnimation(this, R.anim.aim_faded);
    }
}
