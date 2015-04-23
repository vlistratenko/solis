package com.salsalabs.ignite.automation.pages.hq.activities;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.CheckBox;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.CheckBoxImpl;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;

public class PetitionWidget extends SubscribeWidget {
	
	Button signButton = new ButtonImpl("//input[@value='Sign Petition']", "Sign Petition", true);
	TextBox comment = new TextBoxImpl("//textarea[@id='field-comment']", "Comment textbox");
	CheckBox displaySignatureCheckBox = new CheckBoxImpl("//input[@name='field-showSignature']", "Display My Signature");
	
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
			verifyBasicElementsVisible();
			verifier.verifyElementIsDisplayed(true, comment, displaySignatureCheckBox);
		} else {
			verifier.verifyElementIsNotDisplayed(true, signButton);
		}
	}
}
