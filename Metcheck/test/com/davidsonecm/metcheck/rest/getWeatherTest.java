package com.davidsonecm.metcheck.rest;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import com.davidsonecm.metcheck.Weather;
import com.davidsonecm.metcheck.WeatherType;

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
		array[9] = "<img src=/IMAGES/GENERIC/ICONS/ANIMATED/SH.gif title=\"Showers\" border=0>";
		
		Map<String,String> map = new HashMap<String, String>();
		map.put("From", array[0]);
		map.put("Until", array[1]);
		map.put("Temp", array[2]);
		map.put("Feels", array[3]);
		map.put("Pressure", array[4]);
		map.put("Rain", array[5]);
		map.put("Cloud", array[6]);
		map.put("", array[7]);
		map.put("Speed", array[8]);
		map.put("Weather", array[9]);
		
		Weather weather = new Weather(map);
		
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
		
		WeatherType w = weather.getWeatherType();
		assertTrue(WeatherType.SHOWERS == w);
	}
	
	public void testRestCall() throws ParseException, IOException{
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
	
	

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
