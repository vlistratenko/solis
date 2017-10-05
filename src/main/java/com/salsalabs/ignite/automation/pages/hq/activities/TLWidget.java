package com.salsalabs.ignite.automation.pages.hq.activities;

import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.PropertyName;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.CheckBox;
import com.salsalabs.ignite.automation.elements.Label;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.CheckBoxImpl;
import com.salsalabs.ignite.automation.elements.impl.LabelImpl;
import com.salsalabs.ignite.automation.elements.impl.SelectBoxImpl;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;

public class TLWidget extends SubscribeWidget{
	TextBox addressField = new TextBoxImpl("//input[@name='field-address-line1']", "Street Number and Name", true);
	TextBox zipField = new TextBoxImpl("//input[@name='field-address-zip']", "Zip", true);
	TextBox homePhoneField = new TextBoxImpl("//input[@name='PersonContact@HomePhone@Value']", "Home phone", true);
	ButtonImpl findMyLeadersButton = new ButtonImpl("//button[@type='submit'] |//a[contains(., 'Take Action')]", "Find My Leaders");
	ButtonImpl sendButton = new ButtonImpl("//button[@type='submit']", "Send button");
	Label donationIsSccessMessage = new LabelImpl("//h1[.='Thank You!']", "Donation is success");
	SelectBoxImpl personTitleSelectBox = new SelectBoxImpl("//select[@name='PersonCensus@TITLE']", "Title");
	
	public TLWidget() {
		super();
	}	
	
	public TLWidget findLegistratorTLForm(String personAddressLine1,
			String personZip,
			String personEmail,
			String personFName,
			String personLName, 
			String personCity, 
			String state) 
	{		
		addressField.type(personAddressLine1);
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
		clickFindButton();
		sleep(3);
		return this;
	}
	
	public SubscribeWidget fillTLWidget(String title) {
		personTitleSelectBox.selectByValue(title);		
		//homePhoneField.type("123-123-1234");
		sleep(3);
		sendButton.click();
		return this;
		
	}
	
	public TLWidget clickFindButton() {
		findMyLeadersButton.click();
		return this;
	}
	
	public TLWidget verifyDonationIsSuccesses() {
		for (int i = 0; i < 10; i++) {
			if (waitConditionBecomesTrue(donationIsSccessMessage.isDisplayed(), 10)) {
				break;
			}
		}
		verifier.verifyElementIsDisplayed(donationIsSccessMessage);
		return this;
	}
	
	public AddDonationWidgetPage backToFundraisingWidgetPage() {
		closeWindow();
		switchToWindow(CommonUtils.getProperty(PropertyName.CURRENT_WINDOW_HANDLE));
		return new AddDonationWidgetPage();
	}
}

