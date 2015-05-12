package com.salsalabs.ignite.automation.pages.hq.activities;


import com.salsalabs.ignite.automation.common.Supporter;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.CheckBox;
import com.salsalabs.ignite.automation.elements.Label;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.CheckBoxImpl;
import com.salsalabs.ignite.automation.elements.impl.LabelImpl;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;

public class PetitionWidget extends SubscribeWidget {
	
	Button signButton = new ButtonImpl("//input[@value='Sign Petition']", "Sign Petition", true);
	TextBox comment = new TextBoxImpl("//textarea[@id='field-comment']", "Comment textbox");
	CheckBox displaySignatureCheckBox = new CheckBoxImpl("//input[@name='field-showSignature']", "Display My Signature");
	CheckBox displayCommentCheckBox = new CheckBoxImpl("//input[@name='field-showComment']", "Display My Comment");
	
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
	
	public PetitionWidget signPetition(Supporter sup,
			String commentText,
			boolean displaySign,
			boolean displayComment) {
		signButton.click();
		personEmailField.type(sup.getFinalEMAIL());
		personFNameField.type(sup.getFirstName());
		personLNameField.type(sup.getLastName());
		personCityField.type(sup.getCity());
		personZipField.type(sup.getZipCode());
		personStatesSelectBox.selectByLabel(sup.getState());
		comment.type(commentText);
		if (!displaySign) {
			displaySignatureCheckBox.changeState();
		}
		if(!displayComment){
			displayCommentCheckBox.changeState();
		}
		signButton.click();
		sleep(3);
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
		verifier.verifyEquals(displaySign ? sup.getFirstName() + " " + sup.getLastName() : "Anonymous", signName.getText());
		verifier.verifyEquals((sup.getCity() + ", " + sup.getState()).toUpperCase(), signLoc.getText());
		if (!displayComment) {
			verifier.verifyElementIsNotDisplayed(signCom);
		} else {
			verifier.verifyEquals("Comment: " + commentText, signCom.getText());
		}
		return this;
	}
}
