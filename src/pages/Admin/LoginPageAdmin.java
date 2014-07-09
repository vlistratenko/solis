package pages.Admin;

import objects.Browser;
import objects.Button;
import objects.TextBox;
import selenium.SeleneseTestCase;


public class LoginPageAdmin extends Browser {

	Button		LoginButton = new Button("//input[@value='Login']", "Login button");
	TextBox 	LoginField = new TextBox("//input[@name='j_username']", "Login field");
	TextBox 	PasswordField = new TextBox("//input[@name='j_password']", "Password field");
	
	public LoginPageAdmin() {
		//SeleneseTestCase.bug.add("Open login page for admin");
	}
	
	public HomePageAdmin doSuccessLogin(String userName, String password) {
		//SeleneseTestCase.bug.add("Do success login. " + userName + "/" + password);
		open(SeleneseTestCase.USED_ENVIRONMENT.getBaseAdminUrl());
		LoginField.type(userName);
		PasswordField.type(password);
		LoginButton.click();
		return new HomePageAdmin();
	}

}
