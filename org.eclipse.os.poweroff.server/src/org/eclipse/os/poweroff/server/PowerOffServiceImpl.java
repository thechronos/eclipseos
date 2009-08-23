package org.eclipse.os.poweroff.server;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.os.poweroff.client.PowerOffService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.gwt.user.server.rpc.SerializationPolicy;

@SuppressWarnings("serial")
public class PowerOffServiceImpl extends RemoteServiceServlet implements
		PowerOffService {

	@Override
	public String poweroff() {
		try {
			Runtime.getRuntime().exec("poweroff");
			return null;
		} catch (IOException e) {
			return e.getLocalizedMessage();
		}
	}

	@Override
	protected SerializationPolicy doGetSerializationPolicy(
			HttpServletRequest request, String moduleBaseURL, String strongName) {
		return Activator.doGetSerializationPolicy(request, moduleBaseURL, strongName);
	}
}
