package com.davidsonecm.alfrescoimageuploader.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.davidsonecm.alfrescoimageuploader.domain.Site;

public class RestGetSites extends RestAbstract{
	public RestGetSites(String server, String port) {
		this("http", server, port);
	}

	public RestGetSites(String protocol, String server, String port) {
		super();
		this.protocol = protocol;
		this.server = server;
		this.port = port;
	}
	public RestGetSites(){
		
	}
	
	public List<Site> getSites() throws IOException, JSONException {
		List<Site> sites = new ArrayList<Site>();
		
		String response = getResponse("/alfresco/service/api/people/admin/sites?size=10&pos=1");
		
		System.out.println(response);
		
		JSONArray jsonArray = new JSONArray(response);
		for (int i = 0; i < jsonArray.length(); i++) {
	        JSONObject jsonObject = jsonArray.getJSONObject(i);
	        System.out.println(jsonObject);
	        String title = jsonObject.getString("title");
	        String shortName = jsonObject.getString("shortName");
	        String node = jsonObject.getString("node");
	        Site site = new Site(title,shortName,node);
	        sites.add(site);
	      }
		
				
		return sites;
	}
	
}
