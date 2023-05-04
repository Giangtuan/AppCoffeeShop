package com.example.foodorder.view.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;

import com.example.foodorder.R;
import com.example.foodorder.constant.GlobalFuntion;
import com.example.foodorder.view.BaseActivity;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        startMainActivity();
    }

    private void startMainActivity() {
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            GlobalFuntion.startActivity(SplashActivity.this, LoginActivity.class);
            finish();
        }, 2000);
    }
}