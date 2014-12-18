package pages.Admin;

import objects.Browser;
import objects.Button;
import objects.TextBox;
import selenium.CommonUtils;
import selenium.SeleneseTestCase;


public class LoginPageAdmin extends Browser {

	Button		LoginButton = new Button("//input[@value='Login']", "Login button");
	TextBox 	LoginField = new TextBox("//input[@name='j_username']", "Login field");
	TextBox 	PasswordField = new TextBox("//input[@name='j_password']", "Password field");
	
	public LoginPageAdmin() {
		//SeleneseTestCase.bug.add("Open login page for admin");
	}
	
	public HomePageAdmin doSuccessLogin() {
		//SeleneseTestCase.bug.add("Do success login. " + userName + "/" + password);
		String userName, password;
		sleep(3000);
		open(SeleneseTestCase.USED_ENVIRONMENT.getBaseAdminUrl());
		sleep(5000);
		if (System.getProperty("USED_ADMIN_USER") != null) {
			userName = System.getProperty("USED_ADMIN_USER");
		}else{
			userName = CommonUtils.getProperty("ldap.login");
		}
		if (System.getProperty("USED_ADMIN_PASS") != null) {
			password = System.getProperty("USED_ADMIN_PASS");
		}else{
			password = CommonUtils.getProperty("ldap.password");
		}
		LoginField.type(userName);
		PasswordField.type(password);
		LoginButton.click();
		return new HomePageAdmin();
	}

}
