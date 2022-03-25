package com.example.eproject.project.splashAct;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eproject.R;
import com.example.eproject.project.OwnerLogin;

public class SplashActivity extends AppCompatActivity {

    // Constant Time Delay (this means 2.5 seconds)
    private final int SPLASH_DELAY = 2500;

    // Field (Widgets)
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); //This Line Hide Actionbar

        setContentView(R.layout.activity_splash);

        getWindow().setBackgroundDrawable(null);

        // Method to call
        initializeView();
        animateLogo();
        goToMainActivity();

    }



    private void initializeView() {
        imageView = findViewById(R.id.imageView);
    }

    private void animateLogo() {
        // This Method Will Animate Logo
        Animation fadingInAnimation = AnimationUtils.loadAnimation(this,R.anim.fade_in);
        fadingInAnimation.setDuration(SPLASH_DELAY);

        imageView.startAnimation(fadingInAnimation);
    }

    private void goToMainActivity() {
        // This method will take the user to main activity when animation is finished
        new Handler().postDelayed(()->{
            startActivity(new Intent(SplashActivity.this, OwnerLogin.class));
            finish();
        }, SPLASH_DELAY);
    }
}