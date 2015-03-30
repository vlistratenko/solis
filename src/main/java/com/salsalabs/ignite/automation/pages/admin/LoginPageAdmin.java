package com.salsalabs.ignite.automation.pages.admin;

import com.salsalabs.ignite.automation.common.Browser;
import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.PropertyName;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;


public class LoginPageAdmin extends Browser {

	Button		loginButton = new ButtonImpl("//input[@value='Login']", "Login button");
	TextBox 	loginField = new TextBoxImpl("//input[@name='j_username']", "Login field");
	TextBox 	passwordField = new TextBoxImpl("//input[@name='j_password']", "Password field");
	
	public LoginPageAdmin() {
	}
	
	public HomePageAdmin doSuccessLogin() {
		String userName, password;
		sleep(3);
		open(SeleneseTestCase.USED_ENVIRONMENT.getBaseAdminUrl());
		sleep(5);
		if (System.getProperty("USED_ADMIN_USER") != null) {
			userName = System.getProperty("USED_ADMIN_USER");
		}else{
			userName = CommonUtils.getProperty(PropertyName.LDAP_LOGIN);
		}
		if (System.getProperty("USED_ADMIN_PASS") != null) {
			password = System.getProperty("USED_ADMIN_PASS");
		}else{
			password = CommonUtils.getProperty(PropertyName.LDAP_PASSWORD);
		}
		loginField.type(userName);
		passwordField.type(password);
		loginButton.click();
		return new HomePageAdmin();
	}

}
