package com.davidsonecm.alfrescoimageuploader.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.davidsonecm.alfrescoimageuploader.domain.Folder;
import com.davidsonecm.alfrescoimageuploader.domain.Site;

public class RestGetDocLib extends RestAbstract{
	public RestGetDocLib(String server, String port) {
		this("http", server, port);
	}
	public RestGetDocLib(String protocol, String server, String port) {
		super();
		this.protocol = protocol;
		this.server = server;
		this.port = port;
	}

	public List<Folder> getFolders() throws IOException, JSONException {
		return getFolders("");
	}
	public List<Folder> getFolders(String path) throws IOException, JSONException  {
		List<Folder> folders = new ArrayList<Folder>();
		
		String response = getResponse("/alfresco/service/slingshot/doclib/treenode/site/swsdp/documentLibrary/"+path+"?");
		
		System.out.println(response);
		
		JSONObject jsonObject = new JSONObject(response);
		
		JSONArray jsonArray = jsonObject.getJSONArray("items");
		for (int i = 0; i < jsonArray.length(); i++) {
	        JSONObject jsonItem = jsonArray.getJSONObject(i);
	        System.out.println(jsonItem);
	        String name = jsonItem.getString("name");
	        boolean hasChildren = jsonItem.getBoolean("hasChildren");
	        String nodeRef = jsonItem.getString("nodeRef");
	        Folder folder = new Folder(name,nodeRef,hasChildren);
	        folders.add(folder);
	      }
		
				
		return folders;
	}

}
