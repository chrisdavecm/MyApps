package com.davidsonecm.metcheck;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class WeatherWidget extends RelativeLayout {

	ImageView weatherImageView;
	TextView startTimeTextView;
	TextView endTimeTextView;
	
	public WeatherWidget(Context context,String startTime, String endTime, WeatherType weatherType) {
		super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.weatherwidget, this);
        
        startTimeTextView = (TextView) findViewById(R.id.startTime);
        startTimeTextView.setText(startTime);
        
        weatherImageView = (ImageView) findViewById(R.id.weatherImageView);
        weatherImageView.setImageResource(weatherType.getImage());
        
        endTimeTextView = (TextView) findViewById(R.id.endTime);
        endTimeTextView.setText(endTime);
	}

	
	
}
