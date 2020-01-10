package com.rivera.rapid.login;

import android.content.Context;
/**
 * Created by Jaymon on 01/10/2020.
 */
public class LoginPresenter {
    private static final String TAG = "Login";
    private Context context;
    private View view;

    public LoginPresenter(View view, Context context) {
        this.view = view;
        this.context = context;
    }

    public void showButton(){
        view.showContinue();
    }
    public void next(){
        view.nextActivity();
    }

    public interface View{
        void showContinue();
        void nextActivity();

    }
}
