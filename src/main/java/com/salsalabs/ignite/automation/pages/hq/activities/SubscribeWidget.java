package com.salsalabs.ignite.automation.pages.hq.activities;

import com.salsalabs.ignite.automation.common.Browser;
import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.PropertyName;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.Label;
import com.salsalabs.ignite.automation.elements.SelectBox;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.LabelImpl;
import com.salsalabs.ignite.automation.elements.impl.SelectBoxImpl;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;

public class SubscribeWidget extends Browser{

	TextBox personEmailField = new TextBoxImpl("//input[@name='PersonContact@Email@Value']", "Email", true);
	TextBox personFNameField = new TextBoxImpl("//input[@name='PersonCensus@FirstName']", "First name", true);
	TextBox personLNameField = new TextBoxImpl("//input[@name='PersonCensus@LastName']", "Last name", true);
	TextBox personCityField = new TextBoxImpl("//input[@name='Address@Home@City']", "City", true);
	TextBox personZipField = new TextBoxImpl("//input[@name='Address@Home@Zip']", "Zip", true);
	SelectBox personStatesSelectBox = new SelectBoxImpl("//select[@name='Address@Home@State']", "States");
	Button subscribeButton = new ButtonImpl("//input[@value='Subscribe!']", "Subscribe", true);
	
	Label subscriptionIsSccessMessage = new LabelImpl("//div[.='Thanks for signing up!']", "Subscription is success");

	public SubscribeWidget() {
		deletecoockies();
		refresh();
	}
	
	public AddSubscribeWidgetPage backToSubscribegWidgetPage() {
		closeWindow();
		switchToWindow(CommonUtils.getProperty(PropertyName.CURRENT_WINDOW_HANDLE));
		return new AddSubscribeWidgetPage();
	}
	
	public LoginPage backToLoginPage() {
		closeWindow();
		switchToWindow(CommonUtils.getProperty(PropertyName.CURRENT_WINDOW_HANDLE));
		return new LoginPage();
	}

	public SubscribeWidget fillSubscribeWidget(String personEmail,
			String personFName,
			String personLName,
			String personCity,
			String personZip) {
		personEmailField.type(personEmail);
		personFNameField.type(personFName);
		personLNameField.type(personLName);
		personCityField.type(personCity);
		personZipField.type(personZip);
		personStatesSelectBox.selectByIndex(Integer.parseInt(CommonUtils.getRandomValueFromTo(1, 50, 0)));
		subscribeButton.click();
		return this;
		
	}
	
	public SubscribeWidget verifySubscriptionIsSuccesses() {
		for (int i = 0; i < 10; i++) {
			if (waitConditionBecomesTrue(subscriptionIsSccessMessage.isDisplayed(), 10)) {
				break;
			}
		}
		verifier.verifyElementIsDisplayed(subscriptionIsSccessMessage);
		return this;
	}
}
