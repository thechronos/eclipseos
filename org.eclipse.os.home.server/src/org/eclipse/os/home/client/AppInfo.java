package org.eclipse.os.home.client;

import com.google.gwt.user.client.rpc.IsSerializable;

public class AppInfo implements IsSerializable {

	private String name;
	private String url;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
}
