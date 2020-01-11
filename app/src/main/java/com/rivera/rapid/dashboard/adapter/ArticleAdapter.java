package com.rivera.rapid.dashboard.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.rivera.rapid.R;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    public int list_images [] = {R.drawable.mini_bg, R.drawable.mini_bg, R.drawable.mini_bg, R.drawable.mini_bg};
    public String stringTitle [] = {
            "ONE YEARS LATE BUT WE MADE THIS FOR YOU – HA+ HYDRATING SERUM",
            "TWO YEARS LATE BUT WE MADE THIS FOR YOU – HA+ HYDRATING SERUM",
            "THREE YEARS LATE BUT WE MADE THIS FOR YOU – HA+ HYDRATING SERUM",
            "FOUR YEARS LATE BUT WE MADE THIS FOR YOU – HA+ HYDRATING SERUM"
    };
    public String stringDesc [] = {
            "Our signature, award-winning bespoke facial is designed for severely congested and acne-prone skin…",
            "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s",
            "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC",
            "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC"
    };
    public String stringDate [] = {
            "4 May 2020",
            "4 April 2020",
            "4 March 2020",
            "4 February 2020"

    };

    private Context context;

    public ArticleAdapter(Context context) {
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.article_layout_page, parent, false);
        return new ArticleAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String titles = stringTitle[position];
        String descs = stringDesc[position];
        String dates = stringDate[position];
        Integer bg = list_images[position];

        holder.textView13.setText(titles);
        holder.textView12.setText(descs);
        holder.textView11.setText(dates);
    }

    @Override
    public int getItemCount() {
        return stringTitle.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView12, textView13, textView11;

        CardView cv;

        public ViewHolder(View itemView) {
            super(itemView);
            this.textView12 = (TextView) itemView.findViewById(R.id.textView12);
            this.textView13 = (TextView) itemView.findViewById(R.id.textView13);
            this.textView11 = (TextView) itemView.findViewById(R.id.textView11);


        }
    }
}
