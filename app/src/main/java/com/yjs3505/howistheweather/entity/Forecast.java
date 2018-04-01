package com.yjs3505.howistheweather.entity;

import com.yjs3505.howistheweather.R;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Bora SAYINER
 * @version 1.0.0
 * @since 01.04.2018 16:43
 */
public class Forecast implements Serializable {

    private double latitude;
    private double longitude;
    private String timezone;
    private Date time;
    private double temperature;
    private String icon;
    private double humidity;
    private double precipProbability;
    private String summary;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getPrecipProbability() {
        return precipProbability;
    }

    public void setPrecipProbability(double precipProbability) {
        this.precipProbability = precipProbability;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public static int getIconId(String icon) {
        int iconId = R.drawable.clear_day;
        if (icon.equals("clear-day")) {
            iconId = R.drawable.clear_day;
        } else if (icon.equals("clear-night")) {
            iconId = R.drawable.clear_night;
        } else if (icon.equals("rain")) {
            iconId = R.drawable.rain;
        } else if (icon.equals("snow")) {
            iconId = R.drawable.snow;
        } else if (icon.equals("sleet")) {
            iconId = R.drawable.sleet;
        } else if (icon.equals("wind")) {
            iconId = R.drawable.wind;
        } else if (icon.equals("fog")) {
            iconId = R.drawable.fog;
        } else if (icon.equals("cloudy")) {
            iconId = R.drawable.cloudy;
        } else if (icon.equals("partly-cloudy-day")) {
            iconId = R.drawable.partly_cloudy;
        } else if (icon.equals("partly-cloudy-night")) {
            iconId = R.drawable.cloudy_night;
        }
        return iconId;
    }

    @Override
    public String toString() {
        return "Forecast{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", timezone='" + timezone + '\'' +
                ", time=" + time +
                ", temperature=" + temperature +
                ", icon='" + icon + '\'' +
                ", humidity=" + humidity +
                ", precipProbability=" + precipProbability +
                ", summary='" + summary + '\'' +
                '}';
    }
}
