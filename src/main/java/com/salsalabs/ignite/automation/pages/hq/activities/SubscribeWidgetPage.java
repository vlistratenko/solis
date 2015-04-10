package com.salsalabs.ignite.automation.pages.hq.activities;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;

public class SubscribeWidgetPage extends ActivitiesPage {
	
	Button createSubscribeWidgetButton = new ButtonImpl("//a[contains(text(), 'Create a Sign-up Form')]", "Create a Signup Form");

	public AddSubscribeWidgetPage openAddSubscribeWidgetPage() {
		createSubscribeWidgetButton.click();
		return new AddSubscribeWidgetPage();
	}
}
