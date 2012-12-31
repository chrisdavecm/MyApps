package com.davidsonecm.alfrescoimageuploader;

import com.davidsonecm.alfrescoimageuploader.domain.Server;

import junit.framework.TestCase;

public class AIUProcessorTest extends TestCase {
	public void testGetServers(){
		String serverName = "nameofserver", address = "localhost", port = "8080";
		Server server = new Server(serverName, address, port);
		
		AIUProcessor aiup = new AIUProcessorInMemory();
		
		aiup.saveServer(server);
		
		Server server2 = aiup.getServer("nameofserver");
		
		Server server3 = new Server(serverName, address, port);
		
		assertEquals(server3, server2);
		
		assertEquals(server.getName(), serverName);
		assertEquals(server.getAddress(), address);
		assertEquals(server.getPort(), port);
		
	}
	public void testNullServer(){
		AIUProcessor aiup = new AIUProcessorInMemory();
		
		Server server2 = aiup.getServer("nameofserver");
		
		assertEquals(null, server2);
	}
}
