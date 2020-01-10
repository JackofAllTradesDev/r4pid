package com.rivera.rapid.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.rivera.rapid.R;
import com.rivera.rapid.login.LoginActivity;

/**
 * Created by Jaymon on 01/10/2020.
 */

public class MainActivity extends AppCompatActivity implements SplashScreenPresenter.View {
    SplashScreenPresenter splashScreenPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        splashScreenPresenter = new SplashScreenPresenter(this, getApplicationContext());
        splashScreenPresenter.setHandler();

    }

    @Override
    public void nextActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        this.startActivity ( intent );
    }
}
