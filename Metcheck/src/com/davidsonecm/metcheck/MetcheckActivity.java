package com.davidsonecm.metcheck;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import android.app.Activity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.davidsonecm.metcheck.rest.GetWeather;

public class MetcheckActivity extends Activity {
	final static String TAG = "MetcheckActivity";
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams( new LinearLayout.LayoutParams(
        		LayoutParams.FILL_PARENT,
        		LayoutParams.FILL_PARENT ) );
        
        GetWeather getWeather = new GetWeather();
		try {
			Weather weather = getWeather.getWeatherNow("DA7 5DX");
			
			java.text.DateFormat dateFormat = new SimpleDateFormat("HH:mm");
			
			WeatherWidget weatherWidget1 = new WeatherWidget(this,dateFormat.format(weather.getStartTime()) , dateFormat.format(weather.getEndTime()), weather.getWeatherType());
	        linearLayout.addView(weatherWidget1);
		} catch (ParseException e) {
			Log.d(TAG, e.getMessage());
		} catch (IOException e) {
			Log.d(TAG, e.getMessage());
		}
        
        
        setContentView(linearLayout);
    }
}