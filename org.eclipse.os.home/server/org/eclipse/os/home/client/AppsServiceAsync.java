package org.eclipse.os.home.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AppsServiceAsync {

	public void getApps(AsyncCallback<AppInfo[]> callback);
	
}
