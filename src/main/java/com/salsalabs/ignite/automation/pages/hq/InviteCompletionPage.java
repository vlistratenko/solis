package com.salsalabs.ignite.automation.pages.hq;


import java.io.File;

import com.salsalabs.ignite.automation.common.Browser;
import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.PropertyName;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.CheckBox;
import com.salsalabs.ignite.automation.elements.DropDown;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.CheckBoxImpl;
import com.salsalabs.ignite.automation.elements.impl.DropDownImpl;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;

public class InviteCompletionPage extends Browser {

	TextBox passwordField = new TextBoxImpl("//form[@id='loginInvite']/descendant::input[@name='loginInvitePassword']", "Passowrd");
	TextBox confirmPasswordField = new TextBoxImpl("//form[@id='loginInvite']/descendant::input[@name='loginInviteConfirmPassword']", "Confirm password");
	DropDown recoveryQuestionField = new DropDownImpl("//custom-select2[@out='fields.challengeQuestion']/", "//custom-select2[@out='fields.challengeQuestion']/descendant::a", "Recovery question");
	TextBox recoveryAnswerField = new TextBoxImpl("//form[@id='loginInvite']/descendant::input[@name='challengeAnswer']", "Recovery answer");
	DropDown recoveryQuestionField2 = new DropDownImpl("//custom-select2[@out='fields.challengeQuestionTwo']/", "//custom-select2[@out='fields.challengeQuestionTwo']/descendant::a", "Recovery question two");
	TextBox recoveryAnswerField2 = new TextBoxImpl("//form[@id='loginInvite']/descendant::input[@name='challengeAnswerTwo']", "Recovery answer two");
	
	Button createAccountButton = new ButtonImpl("//button[contains(@ng-click,'login_create_account_completion_submit')]", "Create account");
	
	
	TextBox emailField = new TextBoxImpl("//input[@id='email']", "Email for General Questions", true);
	TextBox address1Field = new TextBoxImpl("//input[@id='line1']", "HQ Mailing Address line 1", true);
	TextBox address2Field = new TextBoxImpl("//input[@placeholder='Street Address 2']", "HQ Mailing Address line 2", false);
	TextBox cityField = new TextBoxImpl("//input[@id='city']", "City", true);
	TextBox zipField = new TextBoxImpl("//input[@id='zip']", "Zip", true);
	DropDown statesField = new DropDownImpl("//custom-select2[@data='states']/div/ul/li", "//custom-select2[@data='states']/div/a", "States");
	CheckBox acceptTermsOfService = new CheckBoxImpl("//input[@id='tosVersion']", "Terms");
	Button saveButton = new ButtonImpl("//button[@ng-click=\"doSaveOrgInfo(orgForm,'communicate');\"]", "Save org account");
	
	
	//Design form
	TextBox fileField = new TextBoxImpl("//input[@id='logoUpload']", "Logo", false);
	TextBox primaryColorField = new TextBoxImpl("//input[@ng-model='defaultScheme.primary']", "Primary Color", false);
	TextBox secondaryColorField = new TextBoxImpl("//input[@ng-model='defaultScheme.secondary']", "Secondary Color", false);
	TextBox tertiaryColorField = new TextBoxImpl("//input[@ng-model='defaultScheme.tertiary']", "Tertiary Color", false);
	TextBox fromNameField = new TextBoxImpl("//input[@ng-model='settings.communication.emailDefaultFromName']", "From Name", true);
	TextBox fromEmailField = new TextBoxImpl("//input[@ng-model='settings.communication.emailDefaultFromAddress']", "From Email", true);
	TextBox replyToEmailField = new TextBoxImpl("//input[@ng-model='settings.communication.emailDefaultReplyto']", "Reply To email", true);
	Button finishButton = new ButtonImpl("//button[@ng-click=\"processing.submitOrg=true;doSaveOrgDefaults(orgDefaultsForm);\"]", "Save account");
	
	public HomePage completeInvite(String password) {
		sleep(3);
		passwordField.type(password);
		confirmPasswordField.type(password);
		recoveryQuestionField.selectByLabelJS("What was your childhood nickname?");
		recoveryAnswerField.type("Auto answer");
		recoveryQuestionField2.selectByLabelJS("What school did you attend for sixth grade?");
		recoveryAnswerField2.type("Auto answer");
		createAccountButton.click();
		//Tell Us About Your Organization!
		emailField.waitElement();
		emailField.type(CommonUtils.getProperty(PropertyName.ADMIN_EMAIL));
		address1Field.type("Address line 1");
		address2Field.type("Address line 2");
		cityField.type("TestCity");
		zipField.type("20147");
		statesField.selectByID(CommonUtils.getRandomValueFromTo(1, 5, 0));
		acceptTermsOfService.check(true);
		saveButton.click();
		
		//Design form
		primaryColorField.waitElement();
		fileField.setAttribute("class", "ng-pristine ng-valid");
		fileField.type(new File("images\\image.jpg").getAbsolutePath());
		primaryColorField.type("#BD1335");
		secondaryColorField.type("#16B816");
		tertiaryColorField.type("#343BB0");
		fromNameField.type(CommonUtils.getProperty(PropertyName.ADMIN_FIRST_NAME) + " " + CommonUtils.getProperty(PropertyName.ADMIN_LAST_NAME));
		fromEmailField.type(CommonUtils.getProperty(PropertyName.ADMIN_EMAIL));
		replyToEmailField.type(CommonUtils.getProperty(PropertyName.ADMIN_EMAIL));		
		finishButton.click();		
		return new HomePage();
	}
	
	public HomePage completeCMInvite(String password) {
		sleep(3);
		passwordField.type(password);
		confirmPasswordField.type(password);
		recoveryQuestionField.selectByLabelJS("What was your childhood nickname?");
		recoveryAnswerField.type("Auto answer");
		recoveryQuestionField2.selectByLabelJS("What school did you attend for sixth grade?");
		recoveryAnswerField2.type("Auto answer");
		createAccountButton.click();
		return new HomePage();
	}
}
