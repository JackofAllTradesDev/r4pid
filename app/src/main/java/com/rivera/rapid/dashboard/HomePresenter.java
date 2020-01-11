package com.rivera.rapid.dashboard;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.rivera.rapid.R;
import com.rivera.rapid.client.ApiClient;
import com.rivera.rapid.client.ApiInterface;
import com.rivera.rapid.dashboard.adapter.ViewPagerAdapter;
import com.rivera.rapid.model.Weather;
import com.rivera.rapid.model.WeatherMessages;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter {
    private static final String TAG = "Login";
    private Context context;
    private View view;
    ApiInterface apiInterface;





    public HomePresenter(View view, Context context) {
        this.view = view;
        this.context = context;
    }

    public void setApi(){
        view.loading();
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<WeatherMessages> call = apiInterface.doGetListResources();
        call.enqueue(new Callback<WeatherMessages>() {
            @Override
            public void onResponse(Call<WeatherMessages> call, Response<WeatherMessages> response) {

                if(response.isSuccessful()){
                    Double temp = response.body().getMain().getTemp().doubleValue();
                    Double computation = (temp - 32)*5/9;
                    String computed = Double.toString(computation);
                    Double speed = response.body().getWind().getSpeed();
                    Integer humid = response.body().getMain().getHumidity();

                    view.setString(computation, speed, humid, "Hello There", "Eeeek, its getting warm and sticky : ( ", "For your oily skin types, try cleansing your face with an gel based cleanser to break down and dissolve the oil on your…");
                    view.finishLoading();
                }

            }

            @Override
            public void onFailure(Call<WeatherMessages> call, Throwable t) {

            }
        });

    }

    public interface View{
        void setString(Double weather, Double speed, Integer humid, String title, String sub, String desc );
        void loading();
        void finishLoading();


    }
}
