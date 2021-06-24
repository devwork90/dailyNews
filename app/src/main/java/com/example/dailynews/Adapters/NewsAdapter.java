package com.example.dailynews.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.dailynews.Activities.MainActivity;
import com.example.dailynews.Model.DailyNewsData;
import com.example.dailynews.R;

import java.util.ArrayList;

import static com.example.dailynews.Activities.MainActivity.searchTerm;

public class NewsAdapter extends ArrayAdapter<DailyNewsData> {

    private MainActivity mainActivity = new MainActivity();

    public NewsAdapter(@NonNull Context context, ArrayList<DailyNewsData> dailyNewsDataArrayList) {
        super(context, 0, dailyNewsDataArrayList);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_list, parent, false
            );

        }

        DailyNewsData dailyNewsData = getItem(position);
        TextView newsTitle = listItemView.findViewById(R.id.webTitle);
        TextView newsType = listItemView.findViewById(R.id.newsType);
        TextView publishDate = listItemView.findViewById(R.id.publishedDate);
        TextView webLink = listItemView.findViewById(R.id.webLink);
        ImageView articleImg = listItemView.findViewById(R.id.newsImgPlaceholder);

        newsType.setText(dailyNewsData.getType());
        if (searchTerm == "Health") {
            articleImg.setImageResource(R.drawable.ic_baseline_local_hospital_24);
        } else if (searchTerm == "Business") {
            articleImg.setImageResource(R.drawable.ic_baseline_business_center_24);
        } else if (searchTerm == "Stocks") {
            articleImg.setImageResource(R.drawable.ic_baseline_attach_money_24);
        }else if (searchTerm == "Politics"){
            articleImg.setImageResource(R.drawable.ic_baseline_account_balance_24);
        }else if(searchTerm == "Sport"){
            articleImg.setImageResource(R.drawable.ic_baseline_sports_cricket_24);
        }else {
            articleImg.setImageResource(R.drawable.ic_baseline_app_settings_alt_24);
        }
        publishDate.setText(dailyNewsData.getWebPublicationDate());
        webLink.setText(dailyNewsData.getWebUrl());
        newsTitle.setText(dailyNewsData.getWebTitle());
        return listItemView;
    }

}
