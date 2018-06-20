package com.salsalabs.ignite.automation.pages.hq.activities;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;

public class FundraisingWidgetPage extends ActivitiesPage {
	Button createFundraisingWidgetButton = new ButtonImpl("//*[@autotest-id='btn_create_fundraising_form_forms_dashboard']", "Create a Fundraising Form");

	public AddDonationWidgetPage openAddDonationWidgetPage() {
		createFundraisingWidgetButton.fluentWaitForElementPresenceIgnoringExceptions();
		createFundraisingWidgetButton.scrollIntoView();
		createFundraisingWidgetButton.clickJS();
		return new AddDonationWidgetPage();
	}
}
