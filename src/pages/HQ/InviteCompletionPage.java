package pages.HQ;

import objects.Browser;
import objects.Button;
import objects.TextBox;

public class InviteCompletionPage extends Browser {

	TextBox passwordField = new TextBox("//form[@id='loginInvite']/descendant::input[@name='loginInvitePassword']", "Passowrd");
	TextBox confirmPasswordField = new TextBox("//form[@id='loginInvite']/descendant::input[@name='loginInviteConfirmPassword']", "Confirm password");
	TextBox recoveryQuestionField = new TextBox("//form[@id='loginInvite']/descendant::input[@name='challengeQuestion']", "Recovery question");
	TextBox recoveryAnswerField = new TextBox("//form[@id='loginInvite']/descendant::input[@name='challengeAnswer']", "Recovery answerd");
	Button createAccountButton = new Button("//button[.='Create Account']", "Create account");
	
	public void completeInvite(String password) {
		sleep(3000);
		passwordField.type(password);
		confirmPasswordField.type(password);
		recoveryQuestionField.type("Auto question");
		recoveryAnswerField.type("Auto answer");
		createAccountButton.click();
		sleep(5000);
	}
}
