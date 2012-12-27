package com.davidsonecm.metcheck;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
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
        StringBuilder sb = new StringBuilder();
        sb.append(24);
        sb.deleteCharAt(34.5);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams( new LinearLayout.LayoutParams(
        		LayoutParams.FILL_PARENT,
        		LayoutParams.FILL_PARENT ) );
        
        GetWeather getWeather = new GetWeather();
		try {
			List<Weather> weatherList = getWeather.getWeatherNext24Hours("DA7 5DX");
			
			java.text.DateFormat dateFormat = new SimpleDateFormat("HH:mm");
			
			for (Iterator iterator = weatherList.iterator(); iterator.hasNext();) {
				Weather weather = (Weather) iterator.next();
				WeatherWidget weatherWidget1 = new WeatherWidget(this,dateFormat.format(weather.getStartTime()) , dateFormat.format(weather.getEndTime()), weather.getWeatherType());
		        linearLayout.addView(weatherWidget1);
			}
			
			
		} catch (ParseException e) {
			Log.d(TAG, e.getMessage());
		} catch (IOException e) {
			Log.d(TAG, e.getMessage());
		}
        
        
        setContentView(linearLayout);
    }
}