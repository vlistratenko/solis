package com.salsalabs.ignite.automation.pages.hq.activities;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;

public class FundraisingWidgetPage extends ActivitiesPage{
	Button createFundraisingWidgetButton = new ButtonImpl("//a[contains(text(), 'Create a Fundraising Form')]", "Create a Fundraising Form");

	public AddDonationWidgetPage openAddDonationWidgetPage() {
		createFundraisingWidgetButton.click();
		return new AddDonationWidgetPage();
	}
}
