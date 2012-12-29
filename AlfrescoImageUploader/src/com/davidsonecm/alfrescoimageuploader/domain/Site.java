package com.davidsonecm.alfrescoimageuploader.domain;

public class Site {
	private String title;
	private String node;
	private String shortName;
	
	
	public Site(String title, String node, String shortName) {
		super();
		this.title = title;
		this.node = node;
		this.shortName = shortName;
	}
	public void setTitle(String name) {
		this.title = name;
	}
	public String getTitle() {
		return title;
	}
	public void setNoderef(String node) {
		this.node = node;
	}
	public String getNode() {
		return node;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getShortName() {
		return shortName;
	}
}
