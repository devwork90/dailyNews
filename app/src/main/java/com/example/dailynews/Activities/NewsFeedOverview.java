package com.example.dailynews.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import com.example.dailynews.Adapters.NewsAdapter;
import com.example.dailynews.Model.DailyNewsData;
import com.example.dailynews.R;
import com.example.dailynews.Utils.QueryUtils;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NewsFeedOverview extends AppCompatActivity {
    public static final String LOG_TAG = NewsFeedOverview.class.getSimpleName();
    private NewsAdapter mNewsAdapter;
    private String itemSelector;
    private String Url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed_overview);

        int intValue_0 = getIntent().getIntExtra("btn_health_id", 0);
        int intValue_1 = getIntent().getIntExtra("btn_business_id", 1);
        int intValue_2 = getIntent().getIntExtra("btn_stocks_id", 2);
        int intValue_3 = getIntent().getIntExtra("btn_politics_id", 3);
        int intValue4 = getIntent().getIntExtra("btn_sport_id", 4);
        int intValue5 = getIntent().getIntExtra("btn_tech_id", 5);

        if (intValue_0 == 5) {

        } else if (intValue_0 == R.id.btn_health) {
            itemSelector = getIntent().getStringExtra("health_search_term");
            Url = getSearchTerm(itemSelector);

        } else if (intValue_1 == R.id.btn_business) {
            itemSelector = getIntent().getStringExtra("Business_search_term");
            Url = getSearchTerm(itemSelector);

        } else if (intValue_2 == R.id.btn_stocks) {
            itemSelector = getIntent().getStringExtra("Stocks_search_term");
            Url = getSearchTerm(itemSelector);
        } else if (intValue_3 == R.id.btn_politics) {
            itemSelector = getIntent().getStringExtra("Politics_search_term");
            Url = getSearchTerm(itemSelector);
        } else if (intValue4 == R.id.btn_sport) {
            itemSelector = getIntent().getStringExtra("Sport_search_term");
            Url = getSearchTerm(itemSelector);
        } else if (intValue5 == R.id.btn_tech) {
            itemSelector = getIntent().getStringExtra("Tech_search_term");
            Url = getSearchTerm(itemSelector);
        }


        ListView newsListView = findViewById(R.id.list);

        mNewsAdapter = new NewsAdapter(this, new ArrayList<>());
        newsListView.setAdapter(mNewsAdapter);

        // Start the AsyncTask to fetch the earthquake data
        DailyNewsAsyncTask task = new NewsFeedOverview.DailyNewsAsyncTask();
        task.execute(Url);
    }


    private String getSearchTerm(String queryParameter) {
        String DOMAIN = "https://content.guardianapis.com/search?q=";
        String API_KEY = "&api-key=cf9312e9-649b-4129-a77a-7b943556dd7a";

        StringBuffer sb = new StringBuffer(DOMAIN);
        sb.append(queryParameter);
        sb.append(API_KEY);
        String Url = sb.toString();
        return Url;
    }

    public class DailyNewsAsyncTask extends AsyncTask<String, Void, List<DailyNewsData>> {


        @Override
        protected List<DailyNewsData> doInBackground(String... urls) {
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }
            List<DailyNewsData> results = QueryUtils.fetchNewsData(urls[0]);
            return results;
        }

        @Override
        protected void onPostExecute(List<DailyNewsData> dailyNewsData) {
            //super.onPostExecute(dailyNewsData);
            mNewsAdapter.clear();

            if (dailyNewsData != null && !dailyNewsData.isEmpty()) {
                mNewsAdapter.addAll(dailyNewsData);
            }

        }
    }
}