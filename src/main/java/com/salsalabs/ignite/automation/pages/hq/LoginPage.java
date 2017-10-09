package com.salsalabs.ignite.automation.pages.hq;

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
import com.salsalabs.ignite.automation.pages.hq.activities.DonationWidget;
import com.salsalabs.ignite.automation.pages.hq.activities.EventFundraiserWidgetPage;
import com.salsalabs.ignite.automation.pages.hq.activities.EventWidget;
import com.salsalabs.ignite.automation.pages.hq.activities.Eventp2pWidget;
import com.salsalabs.ignite.automation.pages.hq.activities.PetitionWidget;
import com.salsalabs.ignite.automation.pages.hq.activities.SubscribeWidget;
import com.salsalabs.ignite.automation.pages.hq.activities.TLWidget;
import com.salsalabs.ignite.automation.pages.hq.manage.UnsubscribePage;
import com.salsalabs.ignite.automation.suites.regression.EventTeamWidgetPage;

public class LoginPage extends Browser{

	Button		LoginButton = new ButtonImpl("//descendant-or-self::button", "Login button");
	TextBox 	LoginField = new TextBoxImpl("//input[@name='email']", "Login field");
	TextBox 	PasswordField = new TextBoxImpl("//input[@id='password']", "Password field");
	CheckBox 	RemebmerMe = new CheckBoxImpl("//label[contains(text(), 'Remember')]/input", "Remember my email address checkbox");
	Button 		LearnMoreLink = new ButtonImpl("//a[contains(text(), 'Learn more')]", "Learn more link");
	Label 		InvalidEmailAddressOrPasswordValidationLabel = new LabelImpl("//*[text()=\"We can't find the email address and password combo you entered. Please try again.\"]", "Invalid email address or password, please try again message");
	Label 		EmailAddressandPasswordIsRequiredValidationLabel = new LabelImpl("//*[contains(text(),'An email address and password is required to sign in.')]", "An email address and password is required to sign in message");
	Button      endHelp = new ButtonImpl("//div[@class='inmplayer-popover-button-end inmplayer-button']", "End");
	Button		iForgotPassword = new ButtonImpl("//label/*[contains(text(),'I forgot')]", "I forgot password button");
	Button		resetMyPassword = new ButtonImpl("//*[contains(text(),'Password!')]/ancestor::button", "Reset my password button");
	TextBox		newPassword = new TextBoxImpl("//*[@id='login_fp_new_password']", "New password text field");
	TextBox		retypeNewPassword = new TextBoxImpl("//*[@id='login_fp_new_password_confirm']", "Retype new password text field");
	TextBox		securityQuestion = new TextBoxImpl("//p/following-sibling::input", "Security question text field");
	Button		confirmSendingResetPassword = new ButtonImpl("//*[@id='loginResetPasswordForm']//button[1]", "Let's go! button");
	
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
		}
		if (SeleneseTestCase.USED_ENVIRONMENT.getServer().name().equalsIgnoreCase("uat")) {
			endHelp.setImplicity(3);
			if(!endHelp.isNotDisplayed()){
				endHelp.click();
			}
		}		
		return new HomePage(10);
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
	
	public LoginPage clickIForgotPasswordLink(){
		iForgotPassword.click();
		return this;
	}
	
	public LoginPage specifyEmailAddress(String emailAddress) {
		LoginField.type(emailAddress);
		return this;
	}

	public LoginPage specifyNewRecoveryPassword(String password) {
		newPassword.type(password);
		return this;
	}

	public LoginPage retypeNewRecoveryPassword(String password) {
		retypeNewPassword.type(password);
		return this;
	}

	public LoginPage specifySequrityQuestionAnswer(String sequrityQuestionAnswer) {
		securityQuestion.type(sequrityQuestionAnswer);
		return this;
	}
	
	public LoginPage clickResetMyPasswordButton(){
		resetMyPassword.click();
		return this;
	}

	public LoginPage confirmResetPassword(){
		confirmSendingResetPassword.click();
		return this;
	}
	
	public LoginPage openPage() {
		super.open();
		return this;
	}
	
	public SubscribeWidget openSubscribeWidgetByLink() {
		return openSubscribeWidgetByLink(CommonUtils.getProperty(PropertyName.SUBSCRIBE_WIDGET_LINK));
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

	public LoginPage openPasswordRecoveryPage() {
		open(getPasswordRecoveryUrl());
		return this;
	}
	
	public String getInvitationUrl() {
		return SeleneseTestCase.emailClient.getURLByEndWord(CommonUtils.getProperty(PropertyName.ADMIN_ORG_NAME) + " has invited you to Salsa Solis. Let's get started.", "completion");
	}

	public String getPasswordRecoveryUrl() {
		return SeleneseTestCase.emailClient.getURLByEndWord("Recovering your password.", "rcv");
	}
	
	public UnsubscribePage openUnsubscribeLinkFromEmail(String emailSubj) {
		open(SeleneseTestCase.emailClient.getUnsubscribeLink(emailSubj));
		return new UnsubscribePage();
	}

	public SubscribeWidget openSubscribeWidgetByLink(String string) {
		openWidget(string);		
		return new SubscribeWidget();
	}
	
	public DonationWidget openDonationWidgetByLink(String string) {
		openWidget(string);			
		return new DonationWidget();
	}
	
	public EventWidget openEventWidgetByLink(String string) {
		openWidget(string);			
		return new EventWidget();
	}
	
	public Eventp2pWidget openp2pEventWidgetByLink(String string) {
		openWidget(string);			
		return new Eventp2pWidget();
	}
	
	public EventFundraiserWidgetPage openp2pEventFundraiserWidgetByLink(String string) {
		openWidget(string);			
		return new EventFundraiserWidgetPage();
	}
	
	public EventTeamWidgetPage openp2pEventTeamWidgetByLink(String string) {
		openWidget(string);			
		return new EventTeamWidgetPage();
	}
	
	public PetitionWidget openPetitionWidgetByLink(String string) {
		openWidget(string);	
		return new PetitionWidget();
	}
	
	public TLWidget openTLWidgetByLink(String string) {
		openWidget(string);	
		return new TLWidget();
	}
	
	private void openWidget(String string) {
		String currentWindowHandle = super.openInNewWindow(string);		
		sleep(5);
		CommonUtils.setProperty(PropertyName.CURRENT_WINDOW_HANDLE, currentWindowHandle);	
	}
	
	public void open(String url) {
		super.open(url);
	}
	
}
