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
}
