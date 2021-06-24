package com.example.dailynews.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.dailynews.Adapters.NewsAdapter;
import com.example.dailynews.Model.DailyNewsData;
import com.example.dailynews.R;
import com.example.dailynews.Utils.QueryUtils;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static String searchTerm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btn_health(@org.jetbrains.annotations.NotNull View view) {
        Intent healthIntent = new Intent(MainActivity.this, NewsFeedOverview.class);
        searchTerm = "Health";
        healthIntent.putExtra("health_search_term", searchTerm);
        healthIntent.putExtra("btn_health_id", view.getId());
        startActivity(healthIntent);
    }

    public void btn_business(@org.jetbrains.annotations.NotNull View view) {
        Intent businessIntent = new Intent(MainActivity.this, NewsFeedOverview.class);
        searchTerm = "Business";
        businessIntent.putExtra("Business_search_term", searchTerm);
        businessIntent.putExtra("btn_business_id", view.getId());
        startActivity(businessIntent);
    }

    public void btn_stocks(@org.jetbrains.annotations.NotNull View view){
        Intent stocksIntent = new Intent(MainActivity.this, NewsFeedOverview.class);
        searchTerm = "Stocks";
        stocksIntent.putExtra("Stocks_search_term", searchTerm);
        stocksIntent.putExtra("btn_stocks_id", view.getId());
        startActivity(stocksIntent);
    }

    public void btn_politics(View view){
        Intent politicsIntent = new Intent(MainActivity.this, NewsFeedOverview.class);
        searchTerm = "Politics";
        politicsIntent.putExtra("Politics_search_term", searchTerm);
        politicsIntent.putExtra("btn_politics_id", view.getId());
        startActivity(politicsIntent);
    }

    public void btn_sport(View view){
        Intent sportIntent = new Intent(MainActivity.this, NewsFeedOverview.class);
        searchTerm = "Sport";
        sportIntent.putExtra("Sport_search_term", searchTerm);
        sportIntent.putExtra("btn_sport_id", view.getId());
        startActivity(sportIntent);
    }

    public void btn_tech(View view){
        Intent techIntent = new Intent(MainActivity.this, NewsFeedOverview.class);
        searchTerm = "Technology";
        techIntent.putExtra("Tech_search_term", searchTerm);
        techIntent.putExtra("btn_tech_id", view.getId());
        startActivity(techIntent);
    }
}