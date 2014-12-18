package pages.HQ;


import java.io.File;
import objects.Browser;
import objects.Button;
import objects.DropDown;
import objects.TextBox;
import selenium.CommonUtils;

public class InviteCompletionPage extends Browser {

	TextBox passwordField = new TextBox("//form[@id='loginInvite']/descendant::input[@name='loginInvitePassword']", "Passowrd");
	TextBox confirmPasswordField = new TextBox("//form[@id='loginInvite']/descendant::input[@name='loginInviteConfirmPassword']", "Confirm password");
	DropDown recoveryQuestionField = new DropDown("//custom-select2[@out='fields.challengeQuestion']/", "//custom-select2[@out='fields.challengeQuestion']/descendant::a", "Recovery question");
	TextBox recoveryAnswerField = new TextBox("//form[@id='loginInvite']/descendant::input[@name='challengeAnswer']", "Recovery answer");
	DropDown recoveryQuestionField2 = new DropDown("//custom-select2[@out='fields.challengeQuestionTwo']/", "//custom-select2[@out='fields.challengeQuestionTwo']/descendant::a", "Recovery question two");
	TextBox recoveryAnswerField2 = new TextBox("//form[@id='loginInvite']/descendant::input[@name='challengeAnswerTwo']", "Recovery answer two");
	
	Button createAccountButton = new Button("//button/*[.='Continue']", "Create account");
	
	
	TextBox emailField = new TextBox("//input[@id='email']", "Email for General Questions", true);
	TextBox address1Field = new TextBox("//input[@id='line1']", "HQ Mailing Address line 1", true);
	TextBox address2Field = new TextBox("//input[@placeholder='Street Address 2']", "HQ Mailing Address line 2", false);
	TextBox cityField = new TextBox("//input[@id='city']", "City", true);
	TextBox zipField = new TextBox("//input[@id='zip']", "Zip", true);
	DropDown statesField = new DropDown("//custom-select2[@data='states']/div/ul/li", "//custom-select2[@data='states']/div/a", "States");
	Button saveButton = new Button("//button/*[contains(text(),'Save')]", "Save org account");
	
	
	//Design form
	TextBox fileField = new TextBox("//input[@id='logoUpload']", "Logo", false);
	TextBox primaryColorField = new TextBox("//input[@ng-model='defaultScheme.primary']", "Primary Color", false);
	TextBox secondaryColorField = new TextBox("//input[@ng-model='defaultScheme.secondary']", "Secondary Color", false);
	TextBox tertiaryColorField = new TextBox("//input[@ng-model='defaultScheme.tertiary']", "Tertiary Color", false);
	TextBox fromNameField = new TextBox("//input[@ng-model='settings.communication.emailDefaultFromName']", "From Name", true);
	TextBox fromEmailField = new TextBox("//input[@ng-model='settings.communication.emailDefaultFromAddress']", "From Email", true);
	TextBox replyToEmailField = new TextBox("//input[@ng-model='settings.communication.emailDefaultReplyto']", "Reply To email", true);
	Button finishButton = new Button("//button/*[.=\"Let's go!\"]", "Save account");
	
	public HomePage completeInvite(String password) {
		sleep(3000);
		passwordField.type(password);
		confirmPasswordField.type(password);
		recoveryQuestionField.selectByLabelJS("What was your childhood nickname?");
		recoveryAnswerField.type("Auto answer");
		recoveryQuestionField2.selectByLabelJS("What school did you attend for sixth grade?");
		recoveryAnswerField2.type("Auto answer");
		createAccountButton.click();
		//sleep(15000);		
		//Tell Us About Your Organization!
		emailField.waitElement();
		emailField.type(CommonUtils.getProperty("Admin.email"));
		address1Field.type("Address line 1");
		address2Field.type("Address line 2");
		cityField.type("TestCity");
		zipField.type("20147");
		statesField.selectByID(CommonUtils.getRandomValueFromTo(1, 5, 0));
		saveButton.click();
		//sleep(10000);
		
		//Design form
		primaryColorField.waitElement();
		fileField.setAttribute("class", "ng-pristine ng-valid");
		fileField.type(new File("images\\image.jpg").getAbsolutePath());
		primaryColorField.type("#BD1335");
		secondaryColorField.type("#16B816");
		tertiaryColorField.type("#343BB0");
		fromNameField.type(CommonUtils.getProperty("Admin.firstName") + " " + CommonUtils.getProperty("Admin.lastName"));
		fromEmailField.type(CommonUtils.getProperty("Admin.email"));
		replyToEmailField.type(CommonUtils.getProperty("Admin.email"));
		finishButton.click();
		sleep(3000);
		return new HomePage();
	}
}
