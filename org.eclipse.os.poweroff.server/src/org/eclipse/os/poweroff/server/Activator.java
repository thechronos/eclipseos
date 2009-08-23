package org.eclipse.os.poweroff.server;

import java.io.InputStream;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.Bundle;

import com.google.gwt.user.server.rpc.SerializationPolicy;
import com.google.gwt.user.server.rpc.SerializationPolicyLoader;

public class Activator extends Plugin {

	private static Activator singleton;
	
	public Activator() {
		singleton = this;
	}

	public static String getID() {
		return singleton.getBundle().getSymbolicName();
	}
	
	public static SerializationPolicy doGetSerializationPolicy(
			HttpServletRequest request, String moduleBaseURL, String strongName) {
		try {
		    String contextRelativePath = "war/poweroff/";
		    String resourcePath = SerializationPolicyLoader.getSerializationPolicyFileName(contextRelativePath + strongName);
		    Bundle clientBundle = Platform.getBundle(getID().replace(".server", ".client"));
		    URL resourceURL = clientBundle.getResource(resourcePath);
			InputStream in = resourceURL.openStream();
			return SerializationPolicyLoader.loadFromStream(in, null);
		} catch (Exception e) {
			singleton.getLog().log(new Status(Status.ERROR, singleton.getBundle().getSymbolicName(), "GetSerializationPolicy", e));
		}
		return null;
	}

}
