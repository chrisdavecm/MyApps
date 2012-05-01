package com.davidsonecm.metcheck;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import junit.framework.TestCase;

public class WeatherTest extends TestCase {

	public void testGetDateFromString() throws ParseException{
		Weather weather = new Weather();
		Date date = weather.getDateFromString("12:00");
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		assertEquals(12, c.get(Calendar.HOUR_OF_DAY));
		assertEquals(0, c.get(Calendar.MINUTE));
	}
	public void testGetImage(){
		Weather weather = new Weather();
		String s = weather.getImage("<img src=\"/IMAGES/GENERIC/ICONS/ANIMATED/NSU.gif\" title=\"Clear\" border=\"0\" />");
		assertEquals("NSU", s);
		s = weather.getImage("<img src=\"/IMAGES/GENERIC/ICONS/ANIMATED/SU.gif\" title=\"Clear\" border=\"0\" />");
		assertEquals("SU", s);
	}
}
