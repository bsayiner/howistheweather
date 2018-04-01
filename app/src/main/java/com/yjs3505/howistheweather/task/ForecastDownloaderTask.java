package com.yjs3505.howistheweather.task;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import com.yjs3505.howistheweather.activity.MainActivity;
import com.yjs3505.howistheweather.entity.Forecast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author Bora SAYINER
 * @version 1.0.0
 * @since 01.04.2018 16:29
 */
public class ForecastDownloaderTask extends AsyncTask<String, Void, String> {

    private final MainActivity context;

    public ForecastDownloaderTask(MainActivity context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        context.setRefreshVisibility(View.VISIBLE);
    }

    @Override
    protected String doInBackground(String... strings) {
//        String result = null;
//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder()
//                .url("http://www.mynet.com")
//                .build();
//        try {
//            Response response = client.newCall(request).execute();
//            result = response.body().string();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return downloadOldSchool(strings[0]).toString();
    }

    @NonNull
    private StringBuilder downloadOldSchool(String string) {
        final StringBuilder stringBuilder = new StringBuilder();
        try {
            URL url = new URL(string);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String buffer;
            while ((buffer = bufferedReader.readLine()) != null) {
                stringBuilder.append(buffer);
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuilder;
    }


    @Override
    protected void onPostExecute(String string) {
        try {
            JSONObject jsonObject = new JSONObject(string);
            Forecast forecast = new Forecast();
            forecast.setLatitude(jsonObject.getDouble("latitude"));
            forecast.setLongitude(jsonObject.getDouble("longitude"));
            forecast.setTimezone(jsonObject.getString("timezone"));
            JSONObject currently = jsonObject.getJSONObject("currently");
            forecast.setTime(new Date(currently.getLong("time") * 1000));
            forecast.setIcon(currently.getString("icon"));
            forecast.setSummary(currently.getString("summary"));
            forecast.setPrecipProbability(currently.getDouble("precipProbability"));
            forecast.setTemperature(currently.getDouble("temperature"));
            forecast.setHumidity(currently.getDouble("humidity"));
            context.updateForecast(forecast);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        context.setRefreshVisibility(View.INVISIBLE);
    }
}
