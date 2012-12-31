package com.davidsonecm.alfrescoimageuploader.rest;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import org.json.JSONException;

import junit.framework.TestCase;

import com.davidsonecm.alfrescoimageuploader.domain.Folder;
import com.davidsonecm.alfrescoimageuploader.domain.Site;

public class RestGetDocLibTestIntegration extends TestCase {

	public void testGetDocLib() throws IOException, JSONException{
		RestGetDocLib restGetDocLib = new RestGetDocLib("localhost", "8080");
		List<Folder> folders = restGetDocLib.getFolders();
		assertNotNull(folders);
	}
	/**
	 * This is a test based on the WQS site being installed.
	 * Should be replaced with some generated data
	 * @throws IOException
	 * @throws JSONException
	 */
	public void testGetAgencyFiles() throws IOException, JSONException{
		RestGetDocLib restGetDocLib = new RestGetDocLib("localhost", "8080");
		//TODO need to url encode
		
		List<Folder> folders = restGetDocLib.getFolders(URLEncoder.encode("Agency Files").replace("+", "%20"));
		assertNotNull(folders);
	}
}
