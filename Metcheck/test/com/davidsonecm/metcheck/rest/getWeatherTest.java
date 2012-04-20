package com.davidsonecm.metcheck.rest;

import java.text.ParseException;
import java.util.Date;

import junit.framework.TestCase;

import com.davidsonecm.metcheck.Weather;

public class getWeatherTest extends TestCase {
	
	public void testArrayConstructor() throws ParseException{
		String[] array = new String[10];
		array[0] = "12:00";
		array[1] = "17:59";
		array[2] = "8 °c";
		array[3] = "4 °c";
		array[4] = "1000 mb";
		array[5] = "2.9 mm";
		array[6] = "84 %";
		array[7] = "<img>";
		array[8] = "13 mph";
		array[9] = "<img>";
		Weather weather = new Weather(array);
		
		Date startDate = weather.getStartTime();
		assertNotNull(startDate);
		assertEquals(12, startDate.getHours());
		assertEquals(0, startDate.getMinutes());
		
		Date endDate = weather.getEndTime();
		assertNotNull(endDate);
		assertEquals(17, endDate.getHours());
		assertEquals(59, endDate.getMinutes());
		
		double temp = weather.getTemperature();
		assertNotNull(temp);
		assertTrue(temp>0);
		
		double feels = weather.getTemperatureFeels();
		assertNotNull(feels);
		assertTrue(feels>0);
		
		int pressure = weather.getPressure();
		assertTrue(pressure>0);
		
		double rain = weather.getRain();
		assertTrue(rain>0.0);
		
		int cloud = weather.getCloud();
		assertTrue(cloud>0);
		
		int wind = weather.getSpeed();
		assertTrue(wind>0);
	}
	
	public void testRestCall() throws ParseException{
		GetWeather getWeather = new GetWeather();
		Weather weather = getWeather.getWeatherNow("DA7 5DX");
		assertNotNull(weather);
		Date startDate = weather.getStartTime();
		assertNotNull(startDate);
		
		Date endDate = weather.getStartTime();
		assertNotNull(endDate);
		
		double temp = weather.getTemperature();
		assertNotNull(temp);
		assertTrue(temp>0);
		
		double feels = weather.getTemperatureFeels();
		assertNotNull(feels);
		assertTrue(feels>0);
		
		int pressure = weather.getPressure();
		assertTrue(pressure>0);
		
		double rain = weather.getRain();
		assertTrue(rain>0.0);
		
		int cloud = weather.getCloud();
		assertTrue(cloud>0);
		
		int wind = weather.getSpeed();
		assertTrue(wind>0);
		
	}
	
	public void testFindTime(){
		GetWeather getWeather = new GetWeather();
		assertTrue(getWeather.foundTime("12:00"));
		assertTrue(getWeather.foundTime("1:00"));
		assertTrue(getWeather.foundTime("12:59"));
		assertTrue(getWeather.foundTime("23:34"));
		assertFalse(getWeather.foundTime("12.00"));
		assertFalse(getWeather.foundTime("12:0"));
		assertFalse(getWeather.foundTime(""));
		assertFalse(getWeather.foundTime(null));
		//TODO these should fail really
		assertTrue(getWeather.foundTime("12:62"));
		assertTrue(getWeather.foundTime("52:62"));
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
