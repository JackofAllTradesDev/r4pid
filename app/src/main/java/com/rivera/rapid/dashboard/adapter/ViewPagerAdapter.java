package com.rivera.rapid.dashboard.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.rivera.rapid.R;
import com.rivera.rapid.dashboard.Bridge;
import com.rivera.rapid.model.WeatherMessages;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.text.DecimalFormat;
import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    List<WeatherMessages> transactionList;
    private Bridge bridge;

    public ViewPagerAdapter(Context context, List<WeatherMessages> transactionList, Bridge listener) {
        this.transactionList = transactionList;
        this.context = context;
        this.bridge = listener;
    }
    @Override
    public int getCount() {
        return transactionList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.small_pager, container, false);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.levels);
        TextView temp = (TextView) view.findViewById(R.id.tempLbl);
        TextView speed = (TextView) view.findViewById(R.id.speedLbl);
        TextView humid = (TextView) view.findViewById(R.id.humidLbl);
        TextView title = (TextView) view.findViewById(R.id.titleLbl);
        TextView sub = (TextView) view.findViewById(R.id.subsLbl);
        TextView desc = (TextView) view.findViewById(R.id.descLbl);
//        DotsIndicator dotsIndicator = (DotsIndicator) view.findViewById(R.id.dots_indicator);
        ImageView imageButton = (ImageButton) view.findViewById(R.id.imageButton);
        ImageView tempImage = (ImageView) view.findViewById(R.id.imageView4);
        ImageView speedImage = (ImageView) view.findViewById(R.id.imageView6);
        ImageView humidImage = (ImageView) view.findViewById(R.id.imageView5);

        imageButton.setBackgroundResource(R.drawable.more);
        tempImage.setBackgroundResource(R.drawable.raindrops);
        speedImage.setBackgroundResource(R.drawable.orion_sunny_wind_gust);
        humidImage.setBackgroundResource(R.drawable.humidity);
        if(position < 1){
            linearLayout.setVisibility(View.VISIBLE);
        }else{
            linearLayout.setVisibility(View.INVISIBLE);
        }
        DecimalFormat df = new DecimalFormat("#.##");
        String com = df.format(transactionList.get(position).getMain().getTemp());
        temp.setText( com+"˚C");
        speed.setText(transactionList.get(position).getWind().getSpeed().toString()+"km/h");
        humid.setText(transactionList.get(position).getMain().getHumidity().toString()+"%");
        title.setText(transactionList.get(position).getMessageTitle());
        sub.setText(transactionList.get(position).getMessageSubtitle());
        desc.setText(transactionList.get(position).getDesc());

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("TAG", transactionList.get(position).getMessageTitle());
               bridge.getData(transactionList.get(position).getMessageTitle(), transactionList.get(position).getDesc());
            }
        });

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
