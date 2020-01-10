package com.rivera.rapid.splashscreen;

import android.content.Context;
import android.os.Handler;
import android.view.View;

/**
 * Created by Jaymon on 01/10/2020.
 */

public class SplashScreenPresenter {
    private static final String TAG = "SplashScreen";
    private View view;
    private Context context;
    final Handler handler = new Handler();
    public SplashScreenPresenter(View view, Context context) {
        this.view = view;
        this.context = context;
    }
    public void setHandler(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.nextActivity();
            }
        }, 2000);
    }

    public interface View{
        void nextActivity();

    }
}
