package com.davidsonecm.alfrescoimageuploader.domain;

public class Folder {
	String name;
	String nodeRef;
	boolean hasChildren;
	String path;
	public Folder(String name, String nodeRef, boolean hasChildren) {
		super();
		this.name = name;
		this.nodeRef = nodeRef;
		this.hasChildren = hasChildren;
	}
	
	
}
