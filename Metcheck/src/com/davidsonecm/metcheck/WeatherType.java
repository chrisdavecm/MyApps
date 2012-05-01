package com.davidsonecm.metcheck;

public enum WeatherType {
	FAIR(R.drawable.fair), NIGHT_CLOUDY(R.drawable.night_cloudy), 
	NIGHT_PARTLY_CLOUDY(R.drawable.night_partly_cloudy), NIGHT_SHOWERS(R.drawable.night_showers), 
	NIGHT_SUNNY(R.drawable.night_sunny), PARTLY_CLOUDY(R.drawable.partly_cloudy), 
	SHOWERS(R.drawable.showers), SUNNY(R.drawable.sunny), NIGHT_LIGHT_RAIN(R.drawable.night_light_rain),
	HEAVY_RAIN(R.drawable.heavy_rain),NIGHT_FAIR(R.drawable.night_fair);
	
	private WeatherType(int i){
		image = i;
	}
	int image;
	public int getImage(){
		return image;
	}
}
