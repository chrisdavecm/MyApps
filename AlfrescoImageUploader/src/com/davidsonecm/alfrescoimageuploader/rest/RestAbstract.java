package com.davidsonecm.alfrescoimageuploader.rest;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class RestAbstract {
	String alf_ticket;
	String protocol, server, port;
	String username = "admin", password = "admin";
	public RestAbstract(){
		
	}
	
	protected void login() throws IOException {
		String response = getResponseForLogin("/alfresco/service/api/login?u="+username+"&pw="+password);
		processResponse(response);
	}
	
	public String getResponse(String path) throws IOException{
		if(alf_ticket == null){
			login();
		}
		URL url = new URL(protocol+"://" + server + ":" + port + path + "&alf_ticket="+alf_ticket);
		URLConnection urlConnection = url.openConnection();
		InputStream in = new BufferedInputStream(urlConnection.getInputStream());
		try {
			return convertStreamToString(in);
		}finally {
		    in.close();
		}
	}
	
	private String getResponseForLogin(String path) throws IOException{
		URL url = new URL(protocol+"://" + server + ":" + port + path);
		URLConnection urlConnection = url.openConnection();
		InputStream in = new BufferedInputStream(urlConnection.getInputStream());
		try {
			return convertStreamToString(in);
		}finally {
		    in.close();
		}
	}
	
	protected String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
 
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString().trim();
    }

	public String getAlf_ticket() {
		return alf_ticket;
	}

	public void setAlf_ticket(String alf_ticket) {
		this.alf_ticket = alf_ticket;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public void processResponse(String test) {
		int start = test.indexOf("<ticket>")+"<ticket>".length();
		int end = test.indexOf("</ticket>");

		String ticket = test.substring(start, end);
		
		alf_ticket = ticket;
		
		
		
	}
}
