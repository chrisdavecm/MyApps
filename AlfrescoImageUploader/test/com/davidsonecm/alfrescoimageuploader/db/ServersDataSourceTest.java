package com.davidsonecm.alfrescoimageuploader.db;

import java.util.List;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import com.davidsonecm.alfrescoimageuploader.domain.Server;

public class ServersDataSourceTest extends AndroidTestCase {
	 private static final String TEST_FILE_PREFIX = "test_";
	 private ServersDataSource dataSource;
	 public void testGetServers(){
		 Server server = new Server("Server1", "127.0.0.1", "8080");
		 assertTrue(server.getId() == 0);
		 
		 Server newServer = dataSource.createServer(server);
		 assertTrue(newServer.getId() != 0);
		 
		 List<Server> servers = dataSource.getServers();
		 assertTrue(servers.size() == 1);
		 
		 assertEquals(newServer.getId(), servers.get(0).getId());
		 
		 Server server2 = new Server("Server2", "127.0.0.1", "8080");
		 dataSource.createServer(server2);
		 
		 servers = dataSource.getServers();
		 
		 assertTrue(servers.size() == 2);
	 }
	 
	 public void testCreateServer(){
		 Server server = new Server("Server1", "127.0.0.1", "8080");
		 assertTrue(server.getId() == 0);
		 
		 Server newServer = dataSource.createServer(server);
		 assertTrue(newServer.getId() != 0);
	 }
	 
	 public void testSetup(){
		 assertNotNull(dataSource);
	 }
	 
	protected void setUp() throws Exception {
		super.setUp();
		
		RenamingDelegatingContext context 
        	= new RenamingDelegatingContext(getContext(), TEST_FILE_PREFIX);
		dataSource = new ServersDataSource(context);
		dataSource.open();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		dataSource.close();
	}

}
