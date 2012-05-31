package com.davidsonecm.metcheck.rest;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.davidsonecm.metcheck.Weather;

public class HTMLToWeatherConverter {



	public static List<Weather> extractDay(String html) throws ParseException {
		Document doc = Jsoup.parse(html);

		List<String> headers = getHeaders(doc);
		String[] weatherHeaders = new String[headers.size()];

		Elements dataRows = doc.select("td.dataTableDataRow");

		List<Weather> weatherList = new ArrayList<Weather>();
		
		for (int i = 0; i < dataRows.size(); i++) {
			System.out.println(i+" dataRows.get(i).toString()"
					+ dataRows.get(i).ownText());
			if (dataRows.get(i).hasText()) {
				// own text is the string for this level only
				String text = dataRows.get(i).ownText();
				if (foundTime(text)) {
					// we have found the first time
					System.out.println(text);
					// so need to get the next x tds
					int end = i + headers.size();
					int count = 0;
					Date date = new Date();
					Calendar c = Calendar.getInstance();
					c.setTime(date);

					for (; i < end; i++) {
						String s = null;
						System.out.println(i+" dataRows.get(i).toString()"
								+ dataRows.get(i).ownText());

						if(headers.get(count).equals("Weather")){
							s = dataRows.get(i).child(0).toString();
						}else{
							s = dataRows.get(i).ownText();
						}
						System.out.println(s);
						weatherHeaders[count++] = s;
					}
					i--;
					Map<String,String> map = new HashMap<String, String>();
					for (int j = 0; j < weatherHeaders.length; j++) {
						map.put(headers.get(j), weatherHeaders[j]);
					}
					weatherList.add(new Weather(map));
					if(weatherList.size() == 8){
						return weatherList;
					}
				}
			}

		}

		
		return weatherList;
	}
	/**
	 * Takes a string of html to parse
	 * @param html
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public static Weather extractFirstRow(String html)
			throws ParseException, IOException {
		List<Weather> list = extractDay(html);
		if(list!=null && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}

	private static String HEADER_START = "From", HEADER_END = "Weather";

	private static List<String> getHeaders(Document doc) {
		List<String> headings = new ArrayList();

		Elements dataRows = doc.select("td.dataTableTopRow");
		boolean addToList = false;

		for (int i = 0; i < dataRows.size(); i++) {
			if (dataRows.get(i).hasText()) {
				// own text is the string for this level only
				String text = dataRows.get(i).ownText();
				if (text.equals(HEADER_START)) {
					addToList = true;
				}
				if (text.equals(HEADER_END)) {
					headings.add(text);
					break;
				}
				if (addToList) {
					headings.add(text);
				}
			}
		}

		return headings;

	}

	protected static boolean foundTime(String s) {
		if (s == null) {
			return false;
		}
		return Pattern.matches("\\d{1,2}:\\d{2}", s);
	}

	
}
