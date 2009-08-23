package org.eclipse.os.poweroff.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class PowerOff implements EntryPoint {
	private static final String confirmMsg = "<b>Are you sure you want to power off your computer?</b>";
	private static final String failedMsg = "Computer did not respond to power off request";
	
	private PowerOffServiceAsync powerOffService = GWT.create(PowerOffService.class);
	
	public void onModuleLoad() {
		VerticalPanel topPanel = new VerticalPanel();
		topPanel.setSpacing(5);
		RootPanel.get().add(topPanel);
		
		topPanel.add(new HTML(confirmMsg));
		
		Button confirmButton = new Button("Yes, Power Off");
		confirmButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				powerOffService.poweroff(new AsyncCallback<String>() {
					@Override
					public void onSuccess(String result) {
						// Actually, this should never return but if it does it has a message
						RootPanel.get().add(new HTML(failedMsg + "<br>" + result));
					}
					
					@Override
					public void onFailure(Throwable caught) {
						RootPanel.get().add(new HTML(failedMsg));
					}
				});
			}
		});
		topPanel.add(confirmButton);
	}
}
