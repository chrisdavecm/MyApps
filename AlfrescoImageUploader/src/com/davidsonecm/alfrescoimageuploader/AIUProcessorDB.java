package com.davidsonecm.alfrescoimageuploader;

import java.util.List;

import android.content.Context;

import com.davidsonecm.alfrescoimageuploader.db.ServersDataSource;
import com.davidsonecm.alfrescoimageuploader.domain.Server;

public class AIUProcessorDB implements AIUProcessor {
	ServersDataSource dataSource;
	public AIUProcessorDB(Context context){
		dataSource = new ServersDataSource(context);
		dataSource.open();
	}
	
	public void closeResources(){
		if(dataSource != null){
			dataSource.close();
		}
	}

	@Override
	public Server saveServer(Server server) {
		return dataSource.createServer(server);

	}

	@Override
	public Server getServer(long id) {
		return dataSource.getServer(id);
	}

	@Override
	public List<Server> getServers() {
		// TODO Auto-generated method stub
		return null;
	}

}
