package com.davidsonecm.metcheck.rest;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.ParseException;
import com.davidsonecm.metcheck.Weather;

/**
 * A class to go get the weather
 * 
 * @author User
 * 
 */
public class GetWeather {

	public Weather getWeatherNow(String location) throws ParseException, IOException {
		URL url = new URL("http://www.metcheck.com/V40/UK/FREE/today.asp?zipcode="+URLEncoder.encode(location));
		URLConnection conn = url.openConnection();

		InputStream is = conn.getInputStream();
		
		final char[] buffer = new char[0x10000];
		StringBuilder out = new StringBuilder();
		Reader in = new InputStreamReader(is, "UTF-8");
		try {
		  int read;
		  do {
		    read = in.read(buffer, 0, buffer.length);
		    if (read>0) {
		      out.append(buffer, 0, read);
		    }
		  } while (read>=0);
		} finally {
		  in.close();
		}
		String result = out.toString();


		
		return HTMLToWeatherConverter.extractFirstRow(result);
	}
}