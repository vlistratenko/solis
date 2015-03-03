package pages.other;

import com.mailosaur.exception.MailosaurException;

import core.util.Browser;
import core.util.CommonUtils;
import core.util.EmailClient;
import core.util.PropertyName;
import core.util.SeleneseTestCase;
import elements.Button;
import elements.Label;
import elements.TextBox;
import elements.impl.ButtonImpl;
import elements.impl.LabelImpl;
import elements.impl.TextBoxImpl;
import pages.hq.InviteCompletionPage;
import pages.hq.LoginPage;

public class Google extends Browser{
	TextBox loginField = new TextBoxImpl("//input[@id='Email']", "Gmail login");
	TextBox passField = new TextBoxImpl("//input[@id='Passwd']", "Gmail passwd");
	Button signInButton = new ButtonImpl("//input[@id='signIn']", "Sign In");
	Button importantEmails = new ButtonImpl("//a[text()='Important']", "Important folder");
	Button email = new ButtonImpl("//td/descendant::b[text()='Welcome to Ignite HQ']", "Email header");
	Button inviteLink = new ButtonImpl("//a[contains(@href, 'invite_completion')]", "Invite link");
	Button emailBlastSubject = new ButtonImpl("//td/descendant::b[text()='" + CommonUtils.getProperty(PropertyName.EMAIL_SUBJECT) + "']", "Email header");
	Label emailFromLabel = new LabelImpl("//span[.='" + CommonUtils.getProperty(PropertyName.EMAIL_FROM) + "']", "Label with email from");
	
	
	public Google login(String login, String password) {
		closeGoogleSession();
		open("https://accounts.google.com/ServiceLogin?sacu=1&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&hl=ru&service=mail");
		loginField.type(login);
		passField.type(password);
		signInButton.click();
		return this;
	}
	
	public InviteCompletionPage openConfirmationPage() {
		importantEmails.click();
		email.setImplicity(600);
		email.click();
		email.setImplicity(SeleneseTestCase.defaultTimeOut);
		sleep(5);
		String url = inviteLink.getLastElement().getAttribute("href");
		deletecoockies();
		open(url);
		if (!getLocation().equalsIgnoreCase(url)) {
			open(url);
		}
		return new InviteCompletionPage();
	}
	
	public LoginPage verifyAmountOfEmails(int amountOfEmails, int amountOfSplits) {
		Integer amountEmailsInSplit = amountOfEmails;
		if (amountOfSplits > 1) {
			Integer testGroup = Integer.valueOf(CommonUtils.getProperty(PropertyName.PERCENTAGE_OF_TEST_GROUP));
			float s = (float) ((float)testGroup/(float)100);
			amountEmailsInSplit = (int) (amountOfEmails/amountOfSplits * s);
			
		}
		for (int i = 1; i <= amountOfSplits; i++) {
			String subj = CommonUtils.getProperty(PropertyName.EMAIL_SPLIT_SUBJECT) + " Split " + i;
			Integer amountEmails = null;
			try {
				amountEmails = new EmailClient().getEmailsBySubject(subj).size();
			} catch (MailosaurException e) {
				SeleneseTestCase.logger.error("",e);
			}
			if ( ! CommonUtils.getProperty(PropertyName.ENVIRONMENT).equalsIgnoreCase("dev")) {
				verifier.verifyEquals(amountEmails, amountEmailsInSplit, "Not all emails were delivered");
			}else{
				verifier.verifyEquals(amountEmails > 0, true, "O emails were delivered");	
			}
		}
		
		return new LoginPage();
	}
	
	
}
