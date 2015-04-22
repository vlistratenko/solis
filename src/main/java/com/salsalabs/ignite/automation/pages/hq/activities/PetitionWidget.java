package com.salsalabs.ignite.automation.pages.hq.activities;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;

public class PetitionWidget extends SubscribeWidget {
	
	Button signButton = new ButtonImpl("//input[@value='Sign Petition']", "Sign Petition", true);
	
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
			verifier.verifyElementIsDisplayed(true, personEmailField, personFNameField, personLNameField, personCityField, personZipField, personStatesSelectBox);
		} else {
			verifier.verifyElementIsNotDisplayed(true, signButton);
		}
	}
}
