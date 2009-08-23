package org.eclipse.os.home.server;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.os.home.client.AppInfo;
import org.eclipse.os.home.client.AppsService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.gwt.user.server.rpc.SerializationPolicy;

@SuppressWarnings("serial")
public class AppsServiceImpl extends RemoteServiceServlet implements AppsService {
	
	@Override
	public AppInfo[] getApps() {
		IExtensionRegistry extReg = Platform.getExtensionRegistry();
		IExtensionPoint extPoint = extReg.getExtensionPoint(Activator.getID() + ".apps");
		if (extPoint == null)
			return new AppInfo[0];
		IExtension[] exts = extPoint.getExtensions();
		List<AppInfo> apps = new LinkedList<AppInfo>();
		for (int i = 0; i < exts.length; ++i) {
			IConfigurationElement[] elems = exts[i].getConfigurationElements();
			for (int j = 0; j < elems.length; ++j) {
				IConfigurationElement elem = elems[j];
				AppInfo app = new AppInfo();
				app.setName(elem.getAttribute("name"));
				app.setUrl(elem.getAttribute("startPage"));
				apps.add(app);
			}
		}
		return apps.toArray(new AppInfo[apps.size()]);
	}

	@Override
	protected SerializationPolicy doGetSerializationPolicy(
			HttpServletRequest request, String moduleBaseURL, String strongName) {
		return Activator.doGetSerializationPolicy(request, moduleBaseURL, strongName);
	}

}
