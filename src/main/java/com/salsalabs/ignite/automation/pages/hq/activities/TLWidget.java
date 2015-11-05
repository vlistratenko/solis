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
	TextBox addressField = new TextBoxImpl("//input[@name='tl-zipauth-street']", "Street Number and Name", true);
	TextBox zipField = new TextBoxImpl("//input[@name='tl-zipauth-zip']", "Zip", true);
	TextBox homePhoneField = new TextBoxImpl("//input[@name='PersonContact@HomePhone@Value']", "Home phone", true);
	ButtonImpl findMyLeadersButton = new ButtonImpl("//input[@type='submit']", "Find My Leaders");
	ButtonImpl sendButton = new ButtonImpl("//input[@value='Send!']", "Find My Leaders");
	Label donationIsSccessMessage = new LabelImpl("//h1[.='Thank You!']", "Donation is success");
	SelectBoxImpl personTitleSelectBox = new SelectBoxImpl("//select[@name='PersonCensus@TITLE']", "Title");
	
	public TLWidget() {
		super();
	}

	
	
	
	
	public TLWidget findLegistratorTLForm(String personAddressLine1, String personZip) 
	{		
		addressField.type("10753 BLIX");
		zipField.type("91602");
		clickFindButton();
		sleep(3);
		return this;
	}
	
	public SubscribeWidget fillTLWidget(String title,
			String personEmail,
			String personFName,
			String personLName) {
		personTitleSelectBox.selectByValue(title);
		personEmailField.type(personEmail);
		personFNameField.type(personFName);
		personLNameField.type(personLName);
		homePhoneField.type("123-123-1234");

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

