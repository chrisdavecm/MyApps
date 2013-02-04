package com.davidsonecm.alfrescoimageuploader;

import java.util.List;
import com.davidsonecm.alfrescoimageuploader.domain.Server;

/**
 * The job of the processor is to receive a request and respond from the database.
 * It will also start up an additional thread to make a server call if necessary.
 * @author User
 *
 */
public interface AIUProcessor {

	Server saveServer(Server server);

	List<Server> getServers();
	
	Server getServer(long id);

	void closeResources();

}
