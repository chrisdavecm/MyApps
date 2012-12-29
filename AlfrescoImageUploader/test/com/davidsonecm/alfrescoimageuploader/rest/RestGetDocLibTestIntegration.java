package com.davidsonecm.alfrescoimageuploader.rest;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;

import junit.framework.TestCase;

import com.davidsonecm.alfrescoimageuploader.domain.Folder;
import com.davidsonecm.alfrescoimageuploader.domain.Site;

public class RestGetDocLibTestIntegration extends TestCase {

	public void testGetSites() throws IOException, JSONException{
		RestGetDocLib restGetDocLib = new RestGetDocLib("localhost", "8080");
		List<Folder> folders = restGetDocLib.getFolders();
		assertNotNull(folders);
	}
}
