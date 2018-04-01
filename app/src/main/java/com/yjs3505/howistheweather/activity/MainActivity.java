package com.yjs3505.howistheweather.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yjs3505.howistheweather.R;
import com.yjs3505.howistheweather.entity.Forecast;
import com.yjs3505.howistheweather.task.ForecastDownloaderTask;

import java.text.SimpleDateFormat;

/**
 * @author Bora SAYINER
 * @version 1.0.0
 * @since 31.03.2018 17:56
 */
public class MainActivity extends Activity {

    public static final String TAG = MainActivity.class.getName();

    private static final String URL = "https://api.darksky.net/forecast/a554d15f0af2af2e4597b401cff1b1b4/41,28?lang=tr&units=ca";

    private TextView lblLocation;
    private ImageView ivIcon;
    private TextView lblTime;
    private TextView lblTemp;
    private TextView lblHumidity;
    private TextView lblRain;
    private TextView lblSummary;
    private ImageView ivRefresh;
    private ProgressBar pbDownloading;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
        registerEvents();
    }

    private void initializeViews() {
        this.lblLocation = findViewById(R.id.lbl_location);
        this.ivIcon = findViewById(R.id.iv_icon);
        this.lblTime = findViewById(R.id.lbl_time);
        this.lblTemp = findViewById(R.id.lbl_temp);
        this.lblHumidity = findViewById(R.id.lbl_humidity);
        this.lblRain = findViewById(R.id.lbl_rain);
        this.lblSummary = findViewById(R.id.lbl_summary);
        this.ivRefresh = findViewById(R.id.iv_refresh);
        this.pbDownloading = findViewById(R.id.pb_downloading);
    }

    private void registerEvents() {
        ivRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ForecastDownloaderTask(MainActivity.this).execute(URL);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        new ForecastDownloaderTask(this).execute(URL);
    }

    public void updateForecast(Forecast forecast) {
        lblLocation.setText(forecast.getTimezone());
        ivIcon.setImageResource(Forecast.getIconId(forecast.getIcon()));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        lblTime.setText(simpleDateFormat.format(forecast.getTime()));
        lblTemp.setText(String.valueOf((int) forecast.getTemperature()));
        lblHumidity.setText("%" + String.valueOf(forecast.getHumidity() * 100));
        lblRain.setText("%" + String.valueOf(forecast.getPrecipProbability() * 100));
        lblSummary.setText(forecast.getSummary());
    }

    public void setRefreshVisibility(int visible) {
        pbDownloading.setVisibility(visible);
        ivRefresh.setVisibility(visible == View.VISIBLE ? View.INVISIBLE : View.VISIBLE);
    }
}
