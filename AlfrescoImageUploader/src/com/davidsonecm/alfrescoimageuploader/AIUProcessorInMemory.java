package com.davidsonecm.alfrescoimageuploader;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.davidsonecm.alfrescoimageuploader.domain.Server;

public class AIUProcessorInMemory implements AIUProcessor {
	List<Server> servers = new ArrayList<Server>();
	@Override
	public void saveServer(Server server) {
		servers.add(server);
	}

	@Override
	public Server getServer(String string) {
		for (Iterator iterator = servers.iterator(); iterator.hasNext();) {
			Server server = (Server) iterator.next();
			if(server.getName().equals(string)){
				return server;
			}
		}
		return null;
	}

}
