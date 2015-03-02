package pages.hq;

import com.mailosaur.exception.MailosaurException;

import core.util.Browser;
import core.util.CommonUtils;
import core.util.EmailClient;
import core.util.PropertyName;
import core.util.SeleneseTestCase;
import elements.Button;
import elements.CheckBox;
import elements.Label;
import elements.TextBox;
import elements.impl.ButtonImpl;
import elements.impl.CheckBoxImpl;
import elements.impl.LabelImpl;
import elements.impl.TextBoxImpl;
import pages.hq.activities.SubscribeWidget;
import pages.hq.manage.UnsubscribePage;
//import selenium.EmailClient;

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
	
	public HomePage doSuccessLogin(String userName, String password) {
		open();
		sleep(3000);
		if(new HomePage().dashboardTab.isNotExists()) {
			logOut();
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
			sleep(5000);
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
		sleep(5000);
		CommonUtils.setProperty(PropertyName.CURRENT_WINDOW_HANDLE, currentWindowHandle);		
		return new SubscribeWidget();
	}
	
	public LoginPage verifyValidationForFailLogin(String userName) {
		sleep(5000);
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
		try {
			open(new EmailClient().getURLByEndWord(CommonUtils.getProperty(PropertyName.ADMIN_ORG_NAME) + " has invited you to Salsa Solis. Let's get started.", "completion"));
		} catch (MailosaurException e) {
			SeleneseTestCase.logger.error("",e);
		}
		return new InviteCompletionPage();
	}
	
	public UnsubscribePage openUnsubscribeLinkFromEmail(String emailSubj) {
		try {
			open(new EmailClient().getUnsubscribeLink(emailSubj));
		} catch (MailosaurException e) {
			e.printStackTrace();
		}
		return new UnsubscribePage();
	}
	
}
