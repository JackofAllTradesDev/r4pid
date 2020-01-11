package com.rivera.rapid.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hbb20.CountryCodePicker;
import com.rivera.rapid.R;
import com.rivera.rapid.auth.AuthAcitivity;
import com.rivera.rapid.dashboard.DashboardActivity;

/**
 * Created by Jaymon on 01/10/2020.
 */
public class LoginActivity extends AppCompatActivity implements LoginPresenter.View {
    CountryCodePicker ccp;
    private EditText cpNumber;
    private TextView continueLabel;
    private LinearLayout bottomLayout;
    private Button google;
    LoginPresenter loginPresenter;
    Boolean labelChange = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ccp = (CountryCodePicker) findViewById(R.id.ccp);
        cpNumber = (EditText) findViewById(R.id.cpNumber);
        continueLabel = (TextView) findViewById(R.id.continueLbl);
        bottomLayout = (LinearLayout) findViewById(R.id.btmLayout);
        google = (Button)findViewById(R.id.button3);
        loginPresenter = new LoginPresenter(this, getApplicationContext());
        cpNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {

                    bottomLayout.setVisibility(View.VISIBLE);
                    Log.e("Login", "check");
                }else{
                    bottomLayout.setVisibility(View.GONE);
                    loginPresenter.showButton();
                }
            }
        });


        continueLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.next();
            }
        });
        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), DashboardActivity.class);
                startActivity(intent);
            }
        });


    }



    @Override
    public void showContinue() {
        Log.e("ddsadsa", "dsadsa");

        continueLabel.setText("Continue");

    }

    @Override
    public void nextActivity() {
        Intent intent = new Intent(getBaseContext(), AuthAcitivity.class);
        intent.putExtra("EXTRA_CPNUMBER", "+"+ccp.getFullNumberWithPlus()+cpNumber.getText().toString());
        startActivity(intent);
    }
}
