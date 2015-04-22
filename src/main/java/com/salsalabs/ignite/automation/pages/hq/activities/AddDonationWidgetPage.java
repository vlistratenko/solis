package com.salsalabs.ignite.automation.pages.hq.activities;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;

public class AddDonationWidgetPage extends AddSubscribeWidgetPage {

	Button nextButton = new ButtonImpl("//button[@id='btnCompose2']", "Design My Widget button", true);
	Button goToStep3Btn = new ButtonImpl("//button[@id='btnCompose3']", "Next: Page Settings >>");
	
	@Override
	public AddDonationWidgetPage createForm(String widgetName, String widgetDescription) {
		this.widgetName = widgetName;
		widgetNameField.type(widgetName);
		widgetDescriptionField.type(widgetDescription);
		nextButton.click();
		sleep(5);
		return this;
	}
	
	// Go to settings of form
	public AddDonationWidgetPage fillThirdStep() {
		goToStep3Btn.click();
		sleep(5);
		return this;
	}
	
	public DonationWidget openDonationWidget(String formName) {
		this.widgetName = formName;
		return this.openDonationWidget();
	}
	
	public DonationWidget openDonationWidget() {
		return openWidget(DonationWidget.class);
	}
	
	@Override
	protected void verifyWidgetElements(boolean visibleForCm, boolean visibleForSupporter) {
		new DonationWidget(false).verifyWidgetElementsVisible(visibleForCm);
		new DonationWidget(true).verifyWidgetElementsVisible(visibleForSupporter);
	}
}
