package com.davidsonecm.alfrescoimageuploader;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import com.davidsonecm.alfrescoimageuploader.domain.Server;

public class AIUProcessorDBTest extends AndroidTestCase {
	private static final String TEST_FILE_PREFIX = "test_";
	AIUProcessor aiup;
	
	public void testGetServers(){
		String serverName = "nameofserver", address = "localhost", port = "8080";
		Server server = new Server(serverName, address, port);
		
		server = aiup.saveServer(server);
		
		Server server2 = aiup.getServer(server.getId());
		
		Server server3 = new Server(serverName, address, port);
		
		assertEquals(server3, server2);
		
		assertEquals(server.getName(), serverName);
		assertEquals(server.getAddress(), address);
		assertEquals(server.getPort(), port);
		
	}
	
	public void testNullServer(){
		Server server2 = aiup.getServer(500);
		
		assertEquals(null, server2);
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		
		RenamingDelegatingContext context 
        	= new RenamingDelegatingContext(getContext(), TEST_FILE_PREFIX);
		aiup = new AIUProcessorDB(context);
	}
	protected void tearDown(){
		aiup.closeResources();
	}
}
