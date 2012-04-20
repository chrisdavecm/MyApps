package com.davidsonecm.metcheck.rest;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;
import android.util.Log;

import com.davidsonecm.metcheck.Weather;

/**
 * A class to go get the weather
 * 
 * @author User
 * 
 */
public class GetWeather {

	public Weather getWeatherNow(String location) throws ParseException {
		// Create HtmlCleaner object to turn the page into
		// XML that we can analyze to get the songs from the page.
		HtmlCleaner pageParser = new HtmlCleaner();
		CleanerProperties props = pageParser.getProperties();
		props.setAllowHtmlInsideAttributes(true);
		props.setAllowMultiWordAttributes(true);
		props.setRecognizeUnicodeChars(true);
		props.setOmitComments(true);
		 
		try {
			//this is the today URL
			URL url = new URL("http://www.metcheck.com/V40/UK/FREE/today.asp?zipcode=da7+5dx");
			//this is the 14 days url
			//URL url = new URL("http://www.metcheck.com/V40/UK/FREE/14days.asp?zipcode="+URLEncoder.encode(location));
		     URLConnection conn = url.openConnection();
		     TagNode node = pageParser.clean(new InputStreamReader(conn.getInputStream()));
		     
		     // XPath string for locating download links...
		     // XPATH says "Select out of all 'table' elements with attribute 'class'
		     // equal to 'fileFormats' which contain element 'tr'..."
		     String xPathExpression = "//td[@class='dataTableDataRow']";
		     try {
		          // Stupid API returns Object[]...  Why not TagNodes?  We'll cast it later
		          Object[] downloadNodes = node.evaluateXPath(xPathExpression);
		          System.out.println(downloadNodes.length);
		      
		          // Iterate through the nodes selected by the XPath statement...
		          boolean reachedSongs = false;
		          String[] weatherList = new String[10];
		          for (int i = 0; i < downloadNodes.length; i++) {
		        	  Object linkNode = downloadNodes[i];
		        	  String s = pageParser.getInnerHtml(((TagNode)linkNode));
		        	  //to start lets use a regex to recognize a time \d{1,2}:\d{2}
		        	  if(foundTime(s)){
		        		  //we have found the first time
		        		  System.out.println(s);
		        		  //so need to get the next x tds
		        		  int end = i + 10;
		        		  int count = 0;
		        		  Date date = new Date();
		        		  Calendar c = Calendar.getInstance();
		        		  c.setTime(date);
		        		  
		        		  for ( ; i < end ; i++) {
		        			  linkNode = downloadNodes[i];
				        	  s = pageParser.getInnerHtml(((TagNode)linkNode));
				        	  System.out.println(s);
				        	  weatherList[count++] = s;
		        		  }
		        		  return new Weather(weatherList);
		        	  }
				}
		        
		     } catch (XPatherException e) {
		          Log.e("ERROR", e.getMessage());
		     }
		} catch (IOException e) {
		     Log.e("ERROR", e.getMessage());
		}
		return null;
	}
//	public Weather getWeatherNow(String location) {
//
//		HttpURLConnection urlConnection = null;
//		InputStream in;
//
//		try {
//			URL url = new URL("http://www.metcheck.com/V40/UK/FREE/today.asp?zipcode=da7+5dx");
//			//URL url = new URL("http://www.metcheck.com/V40/UK/FREE/14days.asp?zipcode="+URLEncoder.encode(location));
//			urlConnection = (HttpURLConnection) url.openConnection();
//			in = new BufferedInputStream(urlConnection.getInputStream());
//			readStream(in);
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//
//			urlConnection.disconnect();
//		}
//		return null;
//	}
//
//	private void readStream(InputStream in) throws IOException {
//		byte[] contents = new byte[1024];
//
//        int bytesRead=0;
//        String strFileContents = null; 
//        StringBuilder sb = new StringBuilder();
//        while( (bytesRead = in.read(contents)) != -1){ 
//            strFileContents = new String(contents, 0, bytesRead);
//            sb.append(strFileContents);
//        }
//       System.out.print(sb.toString());
//
//	}
//
	protected boolean foundTime(String s) {
		if(s == null){
			return false;
		}
		return Pattern.matches("\\d{1,2}:\\d{2}", s);
	}
}