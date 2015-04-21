package com.salsalabs.ignite.automation.pages.hq.activities;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;

public class SubscribeWidgetPage extends ActivitiesPage {
	
	Button createSubscribeWidgetButton = new ButtonImpl("//*[@autotest-id='btn_create_subscribe_form_forms_dashboard']", "Create a Signup Form");

	public AddSubscribeWidgetPage openAddSubscribeWidgetPage() {
		createSubscribeWidgetButton.click();
		return new AddSubscribeWidgetPage();
	}
}
