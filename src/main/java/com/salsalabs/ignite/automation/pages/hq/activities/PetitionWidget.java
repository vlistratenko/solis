package com.salsalabs.ignite.automation.pages.hq.activities;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.CheckBox;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.CheckBoxImpl;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;

public class PetitionWidget extends SubscribeWidget {
	
	Button signButton = new ButtonImpl("//input[@value='Sign Petition']", "Sign Petition", true);
	CheckBox fundraisingCheckBox = new CheckBoxImpl("//div[contains(text(), 'Fundraising')]/input", "Fundraising");
	CheckBox newsletterCheckBox = new CheckBoxImpl("//div[contains(text(), 'Newsletter')]/input", "Newsletter");
	CheckBox emailCheckBox = new CheckBoxImpl("//label[contains(text(), 'Email')]/preceding-sibling::input", "Email");
	TextBox comment = new TextBoxImpl("//textarea[@id='field-comment']", "Comment textbox");
	CheckBox displaySignatureCheckBox = new CheckBoxImpl("//input[name='field-showSignature']", "Display My Signature");
	
	public PetitionWidget() {
		super();
	}
	
	public PetitionWidget(boolean clean) {
		super(clean);
	}
	
	@Override
	public void verifyWidgetElementsVisible(boolean visible){
		if (visible) {
			verifier.verifyElementIsDisplayed(true, signButton);
			signButton.click();
			verifier.verifyElementIsDisplayed(true, personEmailField, personFNameField, personLNameField, personCityField, personZipField, personStatesSelectBox,
					fundraisingCheckBox, newsletterCheckBox, emailCheckBox, comment, displaySignatureCheckBox);
		} else {
			verifier.verifyElementIsNotDisplayed(true, signButton);
		}
	}
}
