package org.eclipse.os.home.server;

import java.io.InputStream;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;

import com.google.gwt.user.server.rpc.SerializationPolicy;
import com.google.gwt.user.server.rpc.SerializationPolicyLoader;

public class Activator extends Plugin {
	private static Activator singleton;
	
	public Activator() {
		singleton = this;
	}
	
	public static SerializationPolicy doGetSerializationPolicy(
			HttpServletRequest request, String moduleBaseURL, String strongName) {
		try {
		    String contextPath = request.getContextPath();
		    String modulePath = new URL(moduleBaseURL).getPath();
		    String contextRelativePath = modulePath.substring(contextPath.length());
		    String serializationPolicyFilePath = SerializationPolicyLoader.getSerializationPolicyFileName(contextRelativePath + strongName);
		    IPath resourcePath = new Path("war").append(new Path(serializationPolicyFilePath));
		    URL resourceURL = singleton.getBundle().getResource(resourcePath.toString());
			InputStream in = resourceURL.openStream();
			return SerializationPolicyLoader.loadFromStream(in, null);
		} catch (Exception e) {
			singleton.getLog().log(new Status(Status.ERROR, singleton.getBundle().getSymbolicName(), "GetSerializationPolicy", e));
		}
		return null;
	}

}
