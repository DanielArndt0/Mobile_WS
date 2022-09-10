package com.app.academia;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.app.academia.databinding.ActivitySplashScreenBinding;

public class SplashScreenActivity extends AppCompatActivity {

        ActivitySplashScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        init();
    }

    private void init() {
        if (getSupportActionBar() != null)
            getSupportActionBar().hide();
        splash(6000, 3000);
    }

    private void splash(long firstTime, long time) {
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        boolean b = false;
        if (!sharedPreferences.getBoolean("i", b)) {
            editor.putBoolean("i", true).apply();
            changeAct(firstTime);
            return;
        }
        changeAct(time);
    }


    private void changeAct(long time) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
                finish();
            }
        }, time);
    }
}