package com.salsalabs.ignite.automation.pages.hq.activities;

import com.salsalabs.ignite.automation.common.Browser;
import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.PropertyName;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.CheckBox;
import com.salsalabs.ignite.automation.elements.Label;
import com.salsalabs.ignite.automation.elements.SelectBox;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.CheckBoxImpl;
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
	SelectBoxImpl personStatesSelectBox = new SelectBoxImpl("//select[@name='Address@Home@State']", "States");
	CheckBox fundraisingCheckBox = new CheckBoxImpl("//div[contains(text(), 'Fundraising')]/input", "Fundraising");
	CheckBox newsletterCheckBox = new CheckBoxImpl("//div[contains(text(), 'Newsletter')]/input", "Newsletter");
	CheckBox emailCheckBox = new CheckBoxImpl("//input[contains(@class, 'Email')]", "Email");
	
	Button subscribeButton = new ButtonImpl("//input[@value='Subscribe!']", "Subscribe", true);
	
	Label subscriptionIsSuccessMessage = new LabelImpl("//h1[contains(.,'Thank You!')]", "Subscription is success");

	public SubscribeWidget() {
		deletecoockies();
		refresh();
	}
	
	public SubscribeWidget(boolean clean) {
		if (clean) {
			deletecoockies();
			refresh();
		}
	}
	
	protected void verifyBasicElementsVisible(){
		verifier.verifyElementIsDisplayed(true, 
				personEmailField, 
				personFNameField, 
				personLNameField, 
				personCityField, 
				personZipField, 
				personStatesSelectBox, 
				fundraisingCheckBox, 
				newsletterCheckBox);
	}
	
	public void verifyWidgetElementsVisible(boolean visible){
		if (visible) {
			verifyBasicElementsVisible();
			verifier.verifyElementIsDisplayed(true, subscribeButton);
		} else {
			verifier.verifyElementIsNotDisplayed(true, subscribeButton);
		}
	}
	
	public AddSubscribeWidgetPage backToSubscribeWidgetPage() {
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
		fillSubscribeWidget(personEmail, personFName, personLName, personCity, personZip, "");
		return this;
		
	}
	
	public SubscribeWidget fillSubscribeWidget(String personEmail,
			String personFName,
			String personLName,
			String personCity,
			String personZip,
			String state) {
		personEmailField.type(personEmail);
		personFNameField.type(personFName);
		personLNameField.type(personLName);
		personCityField.type(personCity);
		personZipField.type(personZip);
		
		if (state.equals("")) {
			personStatesSelectBox.selectByIndex(Integer.parseInt(CommonUtils.getRandomValueFromTo(1, 50, 0)));
		}else{
			personStatesSelectBox.selectByValue(state);
		}
		
		subscribeButton.click();
		return this;
		
	}
	
	public SubscribeWidget verifySubscriptionIsSuccesses() {
		for (int i = 0; i < 10; i++) {
			if (waitConditionBecomesTrue(subscriptionIsSuccessMessage.isVisible(), 10)) {
				break;
			}
		}
		verifier.verifyElementIsDisplayed(subscriptionIsSuccessMessage);
		return this;
	}
}
