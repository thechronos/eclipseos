package org.eclipse.os.home.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("apps")
public interface AppsService extends RemoteService {

	public AppInfo[] getApps();
	
}
