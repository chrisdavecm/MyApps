package com.davidsonecm.alfrescoimageuploader.rest;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;

import junit.framework.TestCase;

import com.davidsonecm.alfrescoimageuploader.domain.Site;

public class RestGetSitesTestIntegration extends TestCase {

	public void testGetSites() throws IOException, JSONException{
		RestGetSites restGetSites = new RestGetSites("localhost", "8080");
		List<Site> sites = restGetSites.getSites();
		assertNotNull(sites);
	}
}
