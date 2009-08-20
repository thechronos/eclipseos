package org.eclipse.os.poweroff.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("service")
public interface PowerOffService extends RemoteService {
	
	public String poweroff();

}
