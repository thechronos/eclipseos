package org.eclipse.os.home.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Home implements EntryPoint {
	
	private AppsServiceAsync appsService = GWT.create(AppsService.class);
	
	@Override
	public void onModuleLoad() {
		VerticalPanel topPanel = new VerticalPanel();
		topPanel.setSpacing(5);
		RootPanel.get().add(topPanel);
		
		final HTML status = new HTML("Discovering apps...");
		topPanel.add(status);
		
		final VerticalPanel appsPanel = new VerticalPanel();
		topPanel.add(appsPanel);
		
		appsService.getApps(new AsyncCallback<AppInfo[]>() {
			@Override
			public void onSuccess(AppInfo[] result) {
				for (int i = 0; i < result.length; ++i) {
					final AppInfo app = result[i];
					HTML appLink = new HTML(
							"<a href=" + app.getUrl() + ">" + app.getName() + "</a>");
					appsPanel.add(appLink);
				}
				status.setHTML("<b>Select application:<b>");
			}
			@Override
			public void onFailure(Throwable caught) {
				status.setText("Failed to get applications");
			}
		});
	}
	
}
