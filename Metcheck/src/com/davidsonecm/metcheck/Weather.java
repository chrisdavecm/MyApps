package com.davidsonecm.metcheck;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Weather {
	public Weather(){
		
	}
	/**
	 * Array of the values to represent a weather entry, order:
	 * startTime,endTime,temp,feels,pressure,
	 * rain,cloud,dirImage,speed,weatherImage
	 * @param values
	 * @throws ParseException 
	 */
	public Weather(String[] values) throws ParseException{
		//format is "2012 Fri 27 Apr 12:00"
		this.startTime = getDateFromString(values[0]);
		
		this.endTime = getDateFromString(values[1]);
		
		this.temperature = Integer.parseInt(values[2].replaceAll(" °c", "").trim());
		
		this.temperatureFeels = Integer.parseInt(values[3].replaceAll(" °c", "").trim());
		
		this.pressure = Integer.parseInt(values[4].replaceAll(" mb", "").trim());
		
		
		this.rain = Double.parseDouble(values[5].replaceAll(" mm", "").trim());
		
		this.cloud = Integer.parseInt(values[6].replaceAll(" %", "").trim());
		
		this.speed = Integer.parseInt(values[8].replaceAll(" mph", "").trim());
	}
	
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

}
