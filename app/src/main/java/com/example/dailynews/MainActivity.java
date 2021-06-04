package com.example.dailynews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.dailynews.Model.DailyNewsData;
import com.example.dailynews.Utils.QueryUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String LOG_TAG = MainActivity.class.getSimpleName();
    private String Url = "https://content.guardianapis.com/search?q=Health&api-key=cf9312e9-649b-4129-a77a-7b943556dd7a";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Start the AsyncTask to fetch the earthquake data
        DailyNewsAsyncTask task = new DailyNewsAsyncTask();
        task.execute(Url);

        /*BottomNavigationView bottomNavigationView = findViewById(R.id.myNavBar);
        final Menu menu = bottomNavigationView.getMenu();
        final MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);
*/
    }

    public class DailyNewsAsyncTask extends AsyncTask<String, Void, List<DailyNewsData>>{


        @Override
        protected List<DailyNewsData> doInBackground(String... urls) {
            if(urls.length < 1 || urls[0] == null) {
                return null;
            }
            List<DailyNewsData> results = QueryUtils.fetchNewsData(urls[0]);
            return results;
        }

        @Override
        protected void onPostExecute(List<DailyNewsData> dailyNewsData) {
            super.onPostExecute(dailyNewsData);
        }
    }
}