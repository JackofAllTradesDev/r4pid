package com.rivera.rapid.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import com.rivera.rapid.R;

import java.util.concurrent.TimeUnit;

public class AuthAcitivity extends AppCompatActivity {
    String passNumber;
    private TextView completeNumber;
    CountDownTimer startTimer;
    private TextView tv_timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_acitivity);
        completeNumber = (TextView) findViewById(R.id.number);
        tv_timer = (TextView) findViewById(R.id.timerCount);
        passNumber = getIntent().getStringExtra("EXTRA_CPNUMBER");
        completeNumber.setText(passNumber);
        timer();

    }


    public void timer(){
        startTimer = new CountDownTimer(300000, 1000) {

            public void onTick(long millisUntilFinished) {




                tv_timer.setText(String.format("Request new code in %02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)%60,
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60));
                if(millisUntilFinished == 0)
                {

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            tv_timer.setText("( 00 SEC )");
                        }
                    }, 1000);
                }


            }

            public void onFinish() {
                tv_timer.setText("Timer finish");
            }
        }.start();
    }
}
