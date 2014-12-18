package pages.HQ;

import com.mailosaur.exception.MailosaurException;

import objects.Browser;
import objects.Button;
import objects.CheckBox;
import objects.Label;
import objects.TextBox;
import pages.HQ.Activities.SubscribeWidget;
import pages.HQ.Manage.UnsubscribePage;
import selenium.CommonUtils;
//import selenium.EmailClient;
import selenium.EmailClient;

public class LoginPage extends Browser{

	Button		LoginButton = new Button("//descendant-or-self::button", "Login button");
	TextBox 	LoginField = new TextBox("//input[@name='email']", "Login field");
	TextBox 	PasswordField = new TextBox("//input[@id='password']", "Password field");
	CheckBox 	RemebmerMe = new CheckBox("//label[contains(text(), 'Remember')]/input", "Remember my email address checkbox");
	Button 		LearnMoreLink = new Button("//a[contains(text(), 'Learn more')]", "Learn more link");
	Label 		InvalidEmailAddressOrPasswordValidationLabel = new Label("//*[text()=\"We can't find the email address and password combo you entered. Please try again.\"]", "Invalid email address or password, please try again message");
	Label 		EmailAddressandPasswordIsRequiredValidationLabel = new Label("//*[contains(text(),'An email address and password is required to sign in.')]", "An email address and password is required to sign in message");
	
	public LoginPage(){
		//logOut();
	}
	
	public LoginPage(Boolean doLogOut){
		if (doLogOut) {
			logOut();
		}
	}
	
	public HomePage doSuccessLogin(String userName, String password) {
		open();
		sleep(3000);
		if(new HomePage().dashboardTab.isNotDisplayed()) {
			logOut();
			LoginField.type(userName);
			PasswordField.removeAttribute("readonly");
			PasswordField.type(password);
			LoginButton.click();
			CommonUtils.setProperty("current.Login", userName);
			CommonUtils.setProperty("current.Password", password);
			if (userName.equalsIgnoreCase(CommonUtils.getProperty("Admin.email"))) {
				CommonUtils.setProperty("current.firstName", CommonUtils.getProperty("Admin.firstName"));
				CommonUtils.setProperty("current.lastName", CommonUtils.getProperty("Admin.lastName"));
			}else{
				CommonUtils.setProperty("current.firstName", CommonUtils.getProperty("CM.firstName"));
				CommonUtils.setProperty("current.lastName", CommonUtils.getProperty("CM.lastName"));
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
		//super.dri
		
		String currentWindowHandle = super.openInNewWindow(CommonUtils.getProperty("subscribeWidgetLink"));		
		sleep(5000);
		CommonUtils.setProperty("currentWindowHandle", currentWindowHandle);		
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
			//open("mail.ru");
			open(new EmailClient().getURLByEndWord("Welcome to the Salsa Community, " + CommonUtils.getProperty("Admin.firstName") + "!", "completion"));
		} catch (MailosaurException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
