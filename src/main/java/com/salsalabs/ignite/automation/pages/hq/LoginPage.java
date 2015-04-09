package com.salsalabs.ignite.automation.pages.hq;

import com.mailosaur.exception.MailosaurException;
import com.salsalabs.ignite.automation.common.Browser;
import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.PropertyName;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.CheckBox;
import com.salsalabs.ignite.automation.elements.Label;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.CheckBoxImpl;
import com.salsalabs.ignite.automation.elements.impl.LabelImpl;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;
import com.salsalabs.ignite.automation.pages.hq.activities.SubscribeWidget;
import com.salsalabs.ignite.automation.pages.hq.manage.UnsubscribePage;

public class LoginPage extends Browser{

	Button		LoginButton = new ButtonImpl("//descendant-or-self::button", "Login button");
	TextBox 	LoginField = new TextBoxImpl("//input[@name='email']", "Login field");
	TextBox 	PasswordField = new TextBoxImpl("//input[@id='password']", "Password field");
	CheckBox 	RemebmerMe = new CheckBoxImpl("//label[contains(text(), 'Remember')]/input", "Remember my email address checkbox");
	Button 		LearnMoreLink = new ButtonImpl("//a[contains(text(), 'Learn more')]", "Learn more link");
	Label 		InvalidEmailAddressOrPasswordValidationLabel = new LabelImpl("//*[text()=\"We can't find the email address and password combo you entered. Please try again.\"]", "Invalid email address or password, please try again message");
	Label 		EmailAddressandPasswordIsRequiredValidationLabel = new LabelImpl("//*[contains(text(),'An email address and password is required to sign in.')]", "An email address and password is required to sign in message");
	
	public LoginPage(){
	}
	
	public LoginPage(boolean doLogOut){
		if (doLogOut) {
			logOut();
		}
	}
	
	public HomePage doSuccessLogin() {
		return doSuccessLogin(CommonUtils.getProperty(PropertyName.ADMIN_EMAIL), CommonUtils.getProperty(PropertyName.ADMIN_PASSWORD));
	}
	
	public HomePage doSuccessLogin(String userName, String password) {
		open();
		sleep(3);
		if(new HomePage().dashboardTab.isNotExists()) {
			//logOut();
			LoginField.type(userName);
			PasswordField.removeAttribute("readonly");
			PasswordField.type(password);
			LoginButton.click();
			CommonUtils.setProperty(PropertyName.CURRENT_LOGIN, userName);
			CommonUtils.setProperty(PropertyName.CURRENT_PASSWORD, password);
			if (userName.equalsIgnoreCase(CommonUtils.getProperty(PropertyName.ADMIN_EMAIL))) {
				CommonUtils.setProperty(PropertyName.CURRENT_FIRST_NAME, CommonUtils.getProperty(PropertyName.ADMIN_FIRST_NAME));
				CommonUtils.setProperty(PropertyName.CURRENT_LAST_NAME, CommonUtils.getProperty(PropertyName.ADMIN_LAST_NAME));
			}else{
				CommonUtils.setProperty(PropertyName.CURRENT_FIRST_NAME, CommonUtils.getProperty(PropertyName.CM_FIRST_NAME));
				CommonUtils.setProperty(PropertyName.CURRENT_LAST_NAME, CommonUtils.getProperty(PropertyName.CM_LAST_NAME));
			}
			sleep(10);
		}else{
			new HomePage().dashboardTab.click();
		}
		return new HomePage();
	}
	
	public LoginPage doFailLogin(String userName, String password) {
		open();
		LoginField.type(userName);
		PasswordField.removeAttribute("readonly");
		PasswordField.type(password);
		LoginButton.click();
		return this;
	}
	
	public HomePage doSuccessLoginAndRememberMe(String userName, String password) {
		RemebmerMe.check();
		return doSuccessLogin(userName, password);
	}
	
	public LoginPage openPage() {
		super.open();
		return this;
	}
	
	public SubscribeWidget openSubscribeWidgetByLink() {
		String currentWindowHandle = super.openInNewWindow(CommonUtils.getProperty(PropertyName.SUBSCRIBE_WIDGET_LINK));		
		sleep(5);
		CommonUtils.setProperty(PropertyName.CURRENT_WINDOW_HANDLE, currentWindowHandle);		
		return new SubscribeWidget();
	}
	
	public LoginPage verifyValidationForFailLogin(String userName) {
		sleep(5);
		if (userName.equals("") || !userName.contains("@")) {
			InvalidEmailAddressOrPasswordValidationLabel.isDisplayed();
			EmailAddressandPasswordIsRequiredValidationLabel.isDisplayed();
		}else{
			InvalidEmailAddressOrPasswordValidationLabel.isDisplayed();
			EmailAddressandPasswordIsRequiredValidationLabel.isNotDisplayed();
		}
		return this;
	}
	
	public InviteCompletionPage openConfirmationPage() {
		open(getInvitationUrl());
		return new InviteCompletionPage();
	}
	
	public String getInvitationUrl() {
		try {
			return SeleneseTestCase.emailClient.getURLByEndWord(CommonUtils.getProperty(PropertyName.ADMIN_ORG_NAME) + " has invited you to Salsa Solis. Let's get started.", "completion");
		} catch (MailosaurException e) {
			SeleneseTestCase.logger.error("", e);
			return null;
		}
	}
	
	public UnsubscribePage openUnsubscribeLinkFromEmail(String emailSubj) {
		try {
			open(SeleneseTestCase.emailClient.getUnsubscribeLink(emailSubj));
		} catch (MailosaurException e) {
			e.printStackTrace();
		}
		return new UnsubscribePage();
	}
	
}
