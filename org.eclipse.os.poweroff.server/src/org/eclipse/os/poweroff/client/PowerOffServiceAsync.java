package org.eclipse.os.poweroff.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface PowerOffServiceAsync {

	void poweroff(AsyncCallback<String> callback);
	
}
