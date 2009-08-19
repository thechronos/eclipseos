package org.eclipse.os.home.server;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.os.home.client.GreetingService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.gwt.user.server.rpc.SerializationPolicy;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {

	public String greetServer(String input) {
		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");
		return "Hello, " + input + "!<br><br>I am running " + serverInfo
				+ ".<br><br>It looks like you are using:<br>" + userAgent;
	}
	
	@Override
	protected SerializationPolicy doGetSerializationPolicy(
			HttpServletRequest request, String moduleBaseURL, String strongName) {
		return Activator.doGetSerializationPolicy(request, moduleBaseURL, strongName);
	}
}
