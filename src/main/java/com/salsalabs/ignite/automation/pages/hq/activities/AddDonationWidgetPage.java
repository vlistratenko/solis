package com.salsalabs.ignite.automation.pages.hq.activities;

import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.PropertyName;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.DropDown;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.DropDownImpl;
import com.salsalabs.ignite.automation.pages.hq.manage.CustomTargetsPage;

public class AddDonationWidgetPage extends AddSubscribeWidgetPage {

	Button nextButton = new ButtonImpl("//button[@id='btnCompose2']", "Design My Widget button", true);
	Button btnPublish = new ButtonImpl("//button[@id='btnPublish']", "Publish This Form >>");
	DropDown gatewaysList = new DropDownImpl(
			"//gateways-and-queues//*[@class='custom dropdown']",
			"//gateways-and-queues//*[@class='custom dropdown']/a",
			"Gateways dropdown");

	public AddDonationWidgetPage selectGatewayByName(String gatewayName) {
		gatewaysList.selectByLabelJS(gatewayName);
		return this;
	}

	public AddDonationWidgetPage() {
		linkProperty = PropertyName.DONATION_WIDGET_LINK;
	}
	
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
		btnPublish.click();
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
	protected SubscribeWidget newWidget(boolean clean) {
		return new DonationWidget(clean);
	}
}
