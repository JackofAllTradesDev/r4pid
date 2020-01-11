package com.rivera.rapid.dashboard;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.rivera.rapid.R;
import com.rivera.rapid.appointments.AppointmentActivity;
import com.rivera.rapid.chat.ChatActivity;
import com.rivera.rapid.dashboard.adapter.ArticleAdapter;
import com.rivera.rapid.dashboard.adapter.ViewPagerAdapter;
import com.rivera.rapid.model.Main;
import com.rivera.rapid.model.WeatherMessages;
import com.rivera.rapid.model.Wind;
import com.rivera.rapid.notifications.NotificationActivity;
import com.rivera.rapid.products.ProductsActivity;
import com.rivera.rapid.treatments.TreamentsActivity;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator2;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements HomePresenter.View, Bridge {

    Toolbar toolbar;
    ViewPager viewPager;
    HomePresenter homePresenter;
    public int list_images [] = {R.drawable.mini_bg, R.drawable.mini_bg, R.drawable.mini_bg};
    WeatherMessages weatherMessages;
    List<WeatherMessages> weatherMessagesList;
    ViewPagerAdapter viewPagerAdapter;
    DotsIndicator dotsIndicator;
    CircleIndicator2  indicator;
    Button news;
    Button pro;
    Button promo;
    ImageButton prod;
    ImageButton treat;
    ImageButton appoint;
    Main main;
    Wind wind;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
     ProgressDialog dialog;
    Intent intent;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        homePresenter = new HomePresenter(this, getActivity().getApplicationContext());
        homePresenter.setApi();
        main = new Main();
        wind = new Wind();
        weatherMessages = new WeatherMessages();
        weatherMessagesList = new ArrayList<>();
        dotsIndicator = (DotsIndicator) view.findViewById(R.id.dots_indicator);
        indicator = view.findViewById(R.id.indicator);
        recyclerView = (RecyclerView)view.findViewById(R.id.reclycerView);
        news = (Button) view.findViewById(R.id.btnNews);
        pro = (Button) view.findViewById(R.id.btnProlugue);
        promo = (Button) view.findViewById(R.id.btnPromo);
        prod = (ImageButton) view.findViewById(R.id.imageButton2);
        treat = (ImageButton) view.findViewById(R.id.imageButton3);
        appoint = (ImageButton) view.findViewById(R.id.imageButton4);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setIcon(R.drawable.location);
        setHasOptionsMenu(true);
        getLocation();
        setRecyclerView();
        news.setBackgroundResource(R.drawable.button_article);
        pro.setBackgroundResource(R.drawable.button_article_white);
        promo.setBackgroundResource(R.drawable.button_article_white);
        news.setTextColor(Color.parseColor("#ffffff"));
        dialog = new ProgressDialog(getActivity());

        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                news.setBackgroundResource(R.drawable.button_article);
                pro.setBackgroundResource(R.drawable.button_article_white);
                promo.setBackgroundResource(R.drawable.button_article_white);
                news.setTextColor(Color.parseColor("#ffffff"));
                pro.setTextColor(Color.parseColor("#595e60"));
                promo.setTextColor(Color.parseColor("#595e60"));
            }
        });
        pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                news.setBackgroundResource(R.drawable.button_article_white);
                pro.setBackgroundResource(R.drawable.button_article);
                promo.setBackgroundResource(R.drawable.button_article_white);
                pro.setTextColor(Color.parseColor("#ffffff"));
                promo.setTextColor(Color.parseColor("#595e60"));
                news.setTextColor(Color.parseColor("#595e60"));
            }
        });
        promo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                news.setBackgroundResource(R.drawable.button_article_white);
                pro.setBackgroundResource(R.drawable.button_article_white);
                promo.setBackgroundResource(R.drawable.button_article);
                promo.setTextColor(Color.parseColor("#ffffff"));
                pro.setTextColor(Color.parseColor("#595e60"));
                news.setTextColor(Color.parseColor("#595e60"));

            }
        });

        prod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getActivity(), ProductsActivity.class);
                startActivity(intent);
            }
        });

        appoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getActivity(), AppointmentActivity.class);
                startActivity(intent);
            }
        });
        treat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getActivity(), TreamentsActivity.class);
                startActivity(intent);
            }
        });
        return view;

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.toolbar_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()) {
            case R.id.notificationMenu:
                 intent = new Intent(getActivity(), NotificationActivity.class);
                startActivity(intent);
                return true;
            case R.id.messageMenu:
                 intent = new Intent(getActivity(), ChatActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void setRecyclerView(){
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(layoutManager);
        adapter = new ArticleAdapter(getActivity().getBaseContext());
        recyclerView.setAdapter(adapter);

        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(recyclerView);
        indicator.attachToRecyclerView(recyclerView, pagerSnapHelper);

        adapter.notifyDataSetChanged();

    }

    public void getLocation(){
        String country_name = null;
        LocationManager lm = (LocationManager)getActivity().getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        Geocoder geocoder = new Geocoder(getActivity().getApplicationContext());
        for(String provider: lm.getAllProviders()) {
            @SuppressWarnings("ResourceType") Location location = lm.getLastKnownLocation(provider);
            if(location!=null) {
                try {
                    List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                    if(addresses != null && addresses.size() > 0) {
                        country_name = addresses.get(0).getCountryName();
                        break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(country_name);
    }




    @Override
    public void setString(Double weather, Double speed, Integer humid, String title, String sub, String desc) {

            int a;
            for( a = 0; a < 3; a++){
                Log.e("AAA", weather.toString());

                main.setTemp(weather);
                main.setHumidity(humid);
                wind.setSpeed(speed);
                weatherMessages.setMessageTitle(title);
                weatherMessages.setMessageSubtitle(sub);
                weatherMessages.setDesc(desc);
                weatherMessages.setMain(main);
                weatherMessages.setWind(wind);
                weatherMessagesList.add(weatherMessages);
            }
        viewPagerAdapter = new ViewPagerAdapter(getActivity(),weatherMessagesList, HomeFragment.this);
        viewPager.setAdapter(viewPagerAdapter);
        dotsIndicator.setViewPager(viewPager);
        Log.e("TAG", String.valueOf(weatherMessagesList.size()));

        }

    @Override
    public void loading() {
////        dialog.setMessage("Loading..");
//        dialog.setCancelable(false);
//        dialog.setInverseBackgroundForced(false);
//        dialog.show();
    }

    @Override
    public void finishLoading() {
//        dialog.hide();
    }

    @Override
    public void getData(String title, String desc) {
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(desc);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}
