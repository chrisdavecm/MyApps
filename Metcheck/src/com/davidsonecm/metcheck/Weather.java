package com.davidsonecm.metcheck;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

public class Weather {
	public Weather(){
		
	}
	/**
	 * Array of the values to represent a weather entry, order:
	 * startTime,endTime,temp,feels,pressure,
	 * rain,cloud,dirImage,speed,weatherImage
	 * @param map
	 * @throws ParseException 
	 */
	public Weather(Map<String, String> map) throws ParseException{
		//format is "2012 Fri 27 Apr 12:00"
		this.startTime = getDateFromString(map.get("From"));
		
		this.endTime = getDateFromString(map.get("Until"));
		
		this.temperature = Integer.parseInt(map.get("Temp").replaceAll(" °c", "").trim());
		
		this.temperatureFeels = Integer.parseInt(map.get("Feels").replaceAll(" °c", "").trim());
		
		String p = map.get("Pressure");
		if(p!=null){
			this.pressure = Integer.parseInt(p.replaceAll(" mb", "").trim());
		}
		
		
		this.rain = Double.parseDouble(map.get("Rain").replaceAll(" mm", "").trim());
		
		this.cloud = Integer.parseInt(map.get("Cloud").replaceAll(" %", "").trim());
		
		this.speed = Integer.parseInt(map.get("Speed").replaceAll(" mph", "").trim());
		
		//<img src=/IMAGES/GENERIC/ICONS/ANIMATED/SH.gif title="Showers" border=0>
		System.out.println("values[10]"+map.get("Weather"));
		String image = getImage(map.get("Weather"));
		
		if(image.equals("SH")){
			weatherType = WeatherType.SHOWERS;
		}else if(image.equals("NLR")){
			weatherType = WeatherType.NIGHT_LIGHT_RAIN;
		}else if(image.equals("PC")){
			weatherType = WeatherType.PARTLY_CLOUDY;
		}else if(image.equals("NSH")){
			weatherType = WeatherType.NIGHT_SHOWERS;
		}else if(image.equals("HR")){
			weatherType = WeatherType.HEAVY_RAIN;
		}else if(image.equals("NFA")){
			weatherType = WeatherType.NIGHT_FAIR;
		}else if(image.equals("NSU")){
			weatherType = WeatherType.NIGHT_SUNNY;
		}else if(image.equals("FA")){
			weatherType = WeatherType.FAIR;
		}else if(image.equals("SU")){
			weatherType = WeatherType.SUNNY;
		}else if(image.equals("NPC")){
			weatherType = WeatherType.NIGHT_PARTLY_CLOUDY;
		}else if(image.equals("NCL")){
			weatherType = WeatherType.NIGHT_CLOUDY;
		}
	}
	
	protected String getImage(String string) {
		//TODO this is a huge assumption about the final / always being there
		int start = string.lastIndexOf("/",string.lastIndexOf("/")-1)+1;
		int end = string.indexOf(".gif")	;	
		System.out.println("start"+start);
		System.out.println("string"+string.charAt(75));
		System.out.println("end"+end);
		System.out.println("string"+string);
		String image = string.substring(start, end);
		System.out.println("image"+image);
		return image;
	}

	WeatherType weatherType;
	protected Date getDateFromString(String string) throws ParseException {
		return new SimpleDateFormat("HH:mm", Locale.ENGLISH).parse(string);
	}

	double rain;
	public int getTemperatureFeels() {
		return temperatureFeels;
	}

	public void setTemperatureFeels(int temperatureFeels) {
		this.temperatureFeels = temperatureFeels;
	}

	public double getRain() {
		return rain;
	}

	public void setRain(double rain) {
		this.rain = rain;
	}

	public int getPressure() {
		return pressure;
	}

	public void setPressure(int pressure) {
		this.pressure = pressure;
	}

	public int getCloud() {
		return cloud;
	}

	public void setCloud(int cloud) {
		this.cloud = cloud;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	int temperature,temperatureFeels,pressure,cloud,speed;
	Date startTime,endTime;
	
	

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	public int getTemperature() {
		return temperature;
	}
	public WeatherType getWeatherType() {
		return weatherType;
	}

}
