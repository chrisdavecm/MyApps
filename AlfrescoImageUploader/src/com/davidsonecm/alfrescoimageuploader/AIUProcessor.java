package com.davidsonecm.alfrescoimageuploader;

import com.davidsonecm.alfrescoimageuploader.domain.Server;

public interface AIUProcessor {

	void saveServer(Server server);

	Server getServer(String string);

}
