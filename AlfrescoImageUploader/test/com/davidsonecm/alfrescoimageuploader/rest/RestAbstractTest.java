package com.davidsonecm.alfrescoimageuploader.rest;

import java.io.InputStream;
import java.io.StringBufferInputStream;

import junit.framework.TestCase;

public class RestAbstractTest extends TestCase {
	public void testProcessResponse(){
		String ticket = "TICKET_4a8a31023312cb202693ac8cfcf9c7c2e3421248";
		String test = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"+
			"<ticket>"+ticket+"</ticket>";
		RestAbstract restAbstract = new RestAbstract();
		restAbstract.processResponse(test);
		assertEquals(ticket, restAbstract.getAlf_ticket());
		
	}
	
	public void testConvertStreamToString(){
		String test = "This is my test string";
		RestGetSites restGetSites = new RestGetSites();
		@SuppressWarnings("deprecation")
		InputStream is = new StringBufferInputStream(test);
		
		String response = restGetSites.convertStreamToString(is);
		
		assertEquals(test, response);
	}

	public void testConvertStreamToStringWithLines(){
		String test = "This is my test string\nwith several lines\nline3";
		RestGetSites restGetSites = new RestGetSites();
		@SuppressWarnings("deprecation")
		InputStream is = new StringBufferInputStream(test);
		
		String response = restGetSites.convertStreamToString(is);

		assertEquals(test, response);
	}
}
