package com.davidsonecm.metcheck.rest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

import junit.framework.TestCase;

import com.davidsonecm.metcheck.Weather;

public class HTMLToWeatherConverterTest extends TestCase {

	public void testResultHtml() throws FileNotFoundException, IOException, ParseException{
		String location = "src/com/davidsonecm/metcheck/rest/todayResult.html";
		
		File file = new File(location);
		
		assertTrue(file.exists());
		
		String html = readToString(location);
		
		Weather weather = HTMLToWeatherConverter.extractFirstRow(html);
		assertTrue(weather!=null);
	}
	
	private String readToString(String location) {
		File myFile = new File(location);
		FileInputStream fIn = null;
		try {
			fIn = new FileInputStream(myFile);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
		String aDataRow = "";
		StringBuilder sb = new StringBuilder(1000000);
		try {
			while ((aDataRow = myReader.readLine()) != null) {
				// aBuffer += aDataRow + "\n";
				sb.append(aDataRow);
				sb.append('\n');
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			myReader.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return sb.toString();
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
