package com.davidsonecm.metcheck.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

import junit.framework.TestCase;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;

import com.davidsonecm.metcheck.Weather;

public class HTMLToWeatherConverterTest extends TestCase {

	public void testResultHtml() throws FileNotFoundException, IOException, ParseException{
		String location = "src/com/davidsonecm/metcheck/rest/todayResult.html";
		
		File file = new File(location);
		
		assertTrue(file.exists());
		
		Weather weather = HTMLToWeatherConverter.extractFirstRow(location);
		assertTrue(weather!=null);
	}
	
	public void testFindTime(){
		assertTrue(HTMLToWeatherConverter.foundTime("12:00"));
		assertTrue(HTMLToWeatherConverter.foundTime("1:00"));
		assertTrue(HTMLToWeatherConverter.foundTime("12:59"));
		assertTrue(HTMLToWeatherConverter.foundTime("23:34"));
		assertFalse(HTMLToWeatherConverter.foundTime("12.00"));
		assertFalse(HTMLToWeatherConverter.foundTime("12:0"));
		assertFalse(HTMLToWeatherConverter.foundTime(""));
		assertFalse(HTMLToWeatherConverter.foundTime(null));
		//TODO these should fail really
		assertTrue(HTMLToWeatherConverter.foundTime("12:62"));
		assertTrue(HTMLToWeatherConverter.foundTime("52:62"));
	}
}
