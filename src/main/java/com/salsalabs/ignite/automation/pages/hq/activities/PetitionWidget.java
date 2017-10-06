package com.salsalabs.ignite.automation.pages.hq.activities;


import com.salsalabs.ignite.automation.common.Supporter;
import com.salsalabs.ignite.automation.elements.CheckBox;
import com.salsalabs.ignite.automation.elements.Label;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.CheckBoxImpl;
import com.salsalabs.ignite.automation.elements.impl.LabelImpl;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;

public class PetitionWidget extends SubscribeWidget {
	
	ButtonImpl expandPetitionFormButton = new ButtonImpl("//button[@type='submit']", "Expand Petition Form", true);
	ButtonImpl signButton = new ButtonImpl("//a[@data-ignite-submit-button='data-ignite-submit-button']", "Sign Petition", true);
	TextBox comment = new TextBoxImpl("//textarea[@name='field-comment']", "Comment textbox");
	CheckBox displaySignatureCheckBox = new CheckBoxImpl("//input[@name='field-showSignature']", "Display My Signature");
	CheckBox displayCommentCheckBox = new CheckBoxImpl("//input[@name='field-showComment']", "Display My Comment");
	//Label signatureBlock = new LabelImpl("(//div[contains(text(), 'Signatures')]", "Signature Block");
	
	
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
			verifyBasicElementsVisible();
			verifier.verifyElementIsDisplayed(true, comment, displaySignatureCheckBox , displayCommentCheckBox);
		} else {
			verifier.verifyElementIsNotDisplayed(true, signButton);
		}
	}
	
	public PetitionWidget signPetition(Supporter sup,
			String commentText,
			boolean displaySign,
			boolean displayComment) {
		signPetitionWithOutChecking(sup, commentText, displaySign, displayComment);
		refresh();
	    verifyNewSignature(sup, commentText, displaySign, displayComment);
		return this;
	}
	
	public PetitionWidget verifyNewSignature(Supporter sup,
			String commentText,
			boolean displaySign,
			boolean displayComment) {
		Label signName = new LabelImpl("(//div[@class='sli-signature'])[1]/div[@class='sli-signature-name']", "Signature Name");
		Label signLoc = new LabelImpl("(//div[@class='sli-signature'])[1]/div[@class='sli-signature-location']", "Signature Location");
		Label signCom = new LabelImpl("(//div[@class='sli-signature'])[1]/div[@class='sli-signature-comment']", "SIgnature Comment");
		verifier.verifyEquals(displaySign ? sup.getFirstName() + " " + sup.getLastName() : "Anonymous", signName.getText().replace("Name: ", ""));
		verifier.verifyEquals((sup.getCity() + ", " + sup.getState()).toUpperCase(), signLoc.getText().replace("LOCATION: ", ""));
		if (!displayComment) {
			verifier.verifyElementIsNotDisplayed(signCom);
		} else {
			verifier.verifyEquals("Comment: " + commentText, signCom.getText());
		}
		return this;
	}

	public SubscribeWidget verifySignIsSuccesses() {
		super.verifySubscriptionIsSuccesses();
		return this;
	}
	
	public PetitionWidget signPetitionWithOutChecking(Supporter sup,
			String commentText,
			boolean displaySign,
			boolean displayComment) {
		personEmailField.type(sup.getFinalEMAIL());
		personFNameField.type(sup.getFirstName());
		personLNameField.type(sup.getLastName());
		
		if (sup.getCity().length()<1) {
			sup.setCity("NewYork");
		}
		personCityField.type(sup.getCity());
		
		personZipField.type(sup.getZipCode());
		
		if (sup.getState().length()<1) {
			sup.setState("NY");
		}
		if (sup.getState().length()==2) {
			personStatesSelectBox.selectByValue(sup.getState());
		}else if (sup.getState().length() > 2) {
			personStatesSelectBox.selectByLabel(sup.getState());
		}
		
		comment.type(commentText);
		if (!displaySign) {
			displaySignatureCheckBox.changeState();
		}
		if(!displayComment){
			displayCommentCheckBox.changeState();
		}
		sleep(3);
		signButton.click();
		sleep(3);
		return this;
	}
}
