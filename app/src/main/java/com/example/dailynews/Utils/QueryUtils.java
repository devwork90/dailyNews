package com.example.dailynews.Utils;

import android.util.Log;

import com.example.dailynews.Model.DailyNewsData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class QueryUtils {

    public static final String LOG_TAG = QueryUtils.class.getSimpleName();

    public QueryUtils() {
    }

    private static URL createURL(String StringUrl) {
        URL url = null;

        try {
            url = new URL(StringUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static List<DailyNewsData> fetchNewsData(String requestURL){

        //Create URL Object
        URL url = createURL(requestURL);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        // Extract relevant fields from the JSON response and create a list of {@link DailyNewsData}
        List<DailyNewsData> dailyNewsData = extractNewsFromJson(jsonResponse);

        return dailyNewsData;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "The seems to be a problem retrieving News data JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private static List<DailyNewsData> extractNewsFromJson(String newsDataJSON) {

        //Check if if newsDataJSON is empty or null then return results early
        if (newsDataJSON.isEmpty()) {
            return null;
        }

        //Created an empty arrayList so we may adding news data to
        List<DailyNewsData> dailyNewsData = new ArrayList<>();

        try {
            JSONObject baseJsonResponse = new JSONObject(newsDataJSON);
            JSONObject responseObj = baseJsonResponse.getJSONObject("response");
            JSONArray resultArray = responseObj.getJSONArray("results");
            for (int i=0; i< resultArray.length(); i++){

                JSONObject newsArticle = resultArray.getJSONObject(i);
                String type = newsArticle.getString("type");
                String sectionName = newsArticle.getString("sectionName");
                String title = newsArticle.getString("webTitle");
                String webUrl = newsArticle.getString("webUrl");

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String date = newsArticle.getString("webPublicationDate");
                date = String.valueOf(sdf.parse(date));
                DailyNewsData newsData = new DailyNewsData(type,sectionName, date, title,webUrl);

                dailyNewsData.add(newsData);
            }
        } catch (JSONException | ParseException e) {
            e.printStackTrace();
        }
        return dailyNewsData;

    }

}
