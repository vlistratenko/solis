package com.salsalabs.ignite.automation.pages.hq.activities;


import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.Supporter;
import com.salsalabs.ignite.automation.elements.CheckBox;
import com.salsalabs.ignite.automation.elements.Label;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.VE2Elements.Signatures;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.CheckBoxImpl;
import com.salsalabs.ignite.automation.elements.impl.LabelImpl;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.function.Predicate;

public class PetitionWidget extends SubscribeWidget {

	ButtonImpl expandPetitionFormButton = new ButtonImpl("//button[@type='submit']", "Expand Petition Form", true);
	ButtonImpl signButton = new ButtonImpl("//a[@data-ignite-submit-button='data-ignite-submit-button']", "Sign Petition", true);
	ButtonImpl signButtonOld = new ButtonImpl("//button[@type='submit']", "Sign Petition", true);
	TextBox comment = new TextBoxImpl("//textarea[@name='field-comment']", "Comment textbox");
	CheckBox displaySignatureCheckBox = new CheckBoxImpl("//input[@name='field-showSignature']", "Display My Signature");
	CheckBox displayCommentCheckBox = new CheckBoxImpl("//input[@name='field-showComment']", "Display My Comment");
	Label signatureBlock = new LabelImpl("//*[contains(@id, 'sig-container')]", "Signature Block");


	public PetitionWidget() {
		super();
	}

	public PetitionWidget(boolean clean) {
		super(clean);
	}

	@Override
	public void verifyWidgetElementsVisible(boolean visible) {
		if (visible) {
			verifier.verifyElementIsDisplayed(true, signButton);
			verifyBasicElementsVisible();
			verifier.verifyElementIsDisplayed(true, comment, displaySignatureCheckBox, displayCommentCheckBox);
		} else {
			verifier.verifyElementIsNotDisplayed(true, signButton);
		}
	}

	public PetitionWidget signPetition(Supporter sup,
									   String commentText,
									   boolean displaySign,
									   boolean displayComment) {
		signPetitionWithOutChecking(sup, commentText, displaySign, displayComment);
		clickOnSignPetitionButton();
		refresh();
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

		if (sup.getCity().length() < 1) {
			sup.setCity("NewYork");
		}
		personCityField.type(sup.getCity());

		personZipField.type(sup.getZipCode());

		if (sup.getState().length() < 1) {
			sup.setState("NY");
		}
		if (sup.getState().length() == 2) {
			personStatesSelectBox.selectByValue(sup.getState());
		} else if (sup.getState().length() > 2) {
			personStatesSelectBox.selectByLabel(sup.getState());
		}

		comment.type(commentText);
		if (!displaySign) {
			displaySignatureCheckBox.changeState();
		}
		if (!displayComment) {
			displayCommentCheckBox.changeState();
		}
		return this;
	}

	public PetitionWidget signPetitionWithOutCheckingOldForms(Supporter sup,
															  String commentText,
															  boolean displaySign,
															  boolean displayComment) {
		expandPetitionFormButton.click();
		signPetitionWithOutChecking(sup, commentText, displaySign, displayComment);
		return clickOnSignPetitionButtonOldForms();
	}

	public PetitionWidget clickOnSignPetitionButton() {
		sleep(3);
		signButton.click();
		sleep(3);
		return this;
	}

	public PetitionWidget clickOnSignPetitionButtonOldForms() {
		sleep(3);
		signButtonOld.click();
		sleep(3);
		return this;
	}

	public String getLastSignatureFirstName() {
		Label firstName = new LabelImpl("//*[@class='sli-signature'][1]//*[@class='sli-signature-name']", "Fist&Last name label");
		return firstName.getText().split(" ")[1];
	}

	public String getLastSignatureLastName() {
		Label firstName = new LabelImpl("//*[@class='sli-signature'][1]//*[@class='sli-signature-name']", "Fist&Last name label");
		return firstName.getText().split(" ")[2];
	}

	public String getLastSignatureCity() {
		String city = "";
		Label firstName = new LabelImpl("//*[@class='sli-signature'][1]//*[@class='sli-signature-location']", "Address label");
		Predicate<Label> addressFieldIsDisplayed = (Label a) -> a.isExists();
		if (addressFieldIsDisplayed.test(firstName)) {
			int val = firstName.getText().split(" ").length;
			if (val == 3)
				city = firstName.getText().split(" ")[1].substring(0, firstName.getText().split(" ")[1].length() - 1);
			else if (val == 2) {
				city = firstName.getText().split(" ")[1];
			} else {
				city = "";
			}
		}
		return city;
	}

	public String getLastSignatureState() {
		String city = "";
		Label firstName = new LabelImpl("//*[@class='sli-signature'][1]//*[@class='sli-signature-location']", "Address label");
		Predicate<Label> addressFieldIsDisplayed = (Label a) -> a.isExists();
		if (addressFieldIsDisplayed.test(firstName)) {
			int val = firstName.getText().split(" ").length;
			if (val == 3) city = firstName.getText().split(" ")[2];
			else if (val == 2) {
				city = firstName.getText().split(" ")[1];
			} else {
				city = "";
			}
		}
		return city;
	}

	public void verifyPublicFormDisplaysCorrectSupporterNameAndAddressFormat(Signatures.CustomizedSupporterNameOptions nameFormat, Signatures.CustomizedSupporterLocationOptions addressFormat) {
		switch (nameFormat){
			case FIRST_NAME_LAST_NAME:
				Assert.assertEquals(CommonUtils.getProperty("supporterFirstFieldNameValue"), getLastSignatureFirstName());
				Assert.assertEquals(CommonUtils.getProperty("supporterLastNameFieldValue"), getLastSignatureLastName()); break;
			case FIRST_NAME_LAST_INITIAL:
				Assert.assertEquals(CommonUtils.getProperty("supporterFirstFieldNameValue"), getLastSignatureFirstName());
				Assert.assertEquals(CommonUtils.getProperty("supporterLastNameFieldValue").substring(0,1), getLastSignatureLastName()); break;
			case FIRST_INITIAL_LAST_INITIAL:
				Assert.assertEquals(CommonUtils.getProperty("supporterFirstFieldNameValue").substring(0,1), getLastSignatureFirstName());
				Assert.assertEquals(CommonUtils.getProperty("supporterLastNameFieldValue").substring(0,1), getLastSignatureLastName()); break;
			case FIRST_INITIAL_LAST_NAME:
				Assert.assertEquals(CommonUtils.getProperty("supporterFirstFieldNameValue").substring(0,1), getLastSignatureFirstName());
				Assert.assertEquals(CommonUtils.getProperty("supporterLastNameFieldValue"), getLastSignatureLastName()); break;
		}

		switch (addressFormat) {
			case CITY_ONLY:
				Assert.assertEquals(CommonUtils.getProperty("supporterCityFieldValue").toUpperCase(), getLastSignatureCity()); break;
			case STATE_ONLY:
				Assert.assertEquals(CommonUtils.getProperty("supporterStateFieldValue").toUpperCase(), getLastSignatureState()); break;
			case DO_NOT_DISPLAY:
				Assert.assertEquals("", getLastSignatureCity());
				Assert.assertEquals("", getLastSignatureState()); break;
			case CITY_STATE:
				Assert.assertEquals(CommonUtils.getProperty("supporterCityFieldValue").toUpperCase(), getLastSignatureCity());
				Assert.assertEquals(CommonUtils.getProperty("supporterStateFieldValue").toUpperCase(), getLastSignatureState()); break;
		}
	}
}
