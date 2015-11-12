package com.salsalabs.ignite.automation.pages.other;

import com.salsalabs.ignite.automation.common.Browser;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;

public class TwitterAndFBAuthorization extends Browser {
	
	private Button loginFacebook = new ButtonImpl("//input[@name='login']", "Log In");
	private Button loginTwitter = new ButtonImpl("//input[@id='allow']", "Authorize app");
	private TextBox twitterUsername = new TextBoxImpl("//input[@id='username_or_email']", "Twitter username");
	private TextBox twitterPassword = new TextBoxImpl("//input[@id='password']", "Twitter password");
	private TextBox facebookUsername = new TextBoxImpl("//input[@id='email']", "Facebook username");
	private TextBox facebookPassword = new TextBoxImpl("//input[@id='pass']", "facebook password");
	
	public void authorizeTwitter(String username, String password) {
		twitterUsername.type(username);
		twitterPassword.type(password);
		loginTwitter.click();
	}
	
	public void authorizeFacebook(String username, String password) {
		facebookUsername.type(username);
		facebookPassword.type(password);
		loginFacebook.click();
	}
	
}
