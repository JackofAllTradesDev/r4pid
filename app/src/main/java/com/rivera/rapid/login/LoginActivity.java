package com.rivera.rapid.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.hbb20.CountryCodePicker;
import com.rivera.rapid.R;
import com.rivera.rapid.auth.AuthAcitivity;

/**
 * Created by Jaymon on 01/10/2020.
 */
public class LoginActivity extends AppCompatActivity implements LoginPresenter.View {
    CountryCodePicker ccp;
    private EditText cpNumber;
    private TextView continueLabel;
    LoginPresenter loginPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ccp = (CountryCodePicker) findViewById(R.id.ccp);
        cpNumber = (EditText) findViewById(R.id.cpNumber);
        continueLabel = (TextView) findViewById(R.id.continueLabel);
        loginPresenter = new LoginPresenter(this, getApplicationContext());
        cpNumber.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                loginPresenter.showButton();
                return false;
            }
        });

        continueLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.next();
            }
        });


    }


    @Override
    public void showContinue() {
        Log.e("ddsadsa", "dsadsa");
        continueLabel.setVisibility(View.VISIBLE);
    }

    @Override
    public void nextActivity() {
        Intent intent = new Intent(getBaseContext(), AuthAcitivity.class);
        intent.putExtra("EXTRA_CPNUMBER", "+"+ccp.getFullNumberWithPlus()+cpNumber.getText().toString());
        startActivity(intent);
    }
}
