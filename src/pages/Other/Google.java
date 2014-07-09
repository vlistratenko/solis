package pages.Other;

import com.mailosaur.exception.MailosaurException;

import objects.Browser;
import objects.Button;
import objects.Label;
import objects.TextBox;
import pages.HQ.InviteCompletionPage;
import pages.HQ.LoginPage;
import selenium.CommonUtils;
import selenium.EmailClient;
import selenium.SeleneseTestCase;

public class Google extends Browser{
	TextBox loginField = new TextBox("//input[@id='Email']", "Gmail login");
	TextBox passField = new TextBox("//input[@id='Passwd']", "Gmail passwd");
	Button signInButton = new Button("//input[@id='signIn']", "Sign In");
	Button importantEmails = new Button("//a[text()='Important']", "Important folder");
	Button email = new Button("//td/descendant::b[text()='Welcome to Ignite HQ']", "Email header");
	Button inviteLink = new Button("//a[contains(@href, 'invite_completion')]", "Invite link");
	Button emailBlastSubject = new Button("//td/descendant::b[text()='" + CommonUtils.getProperty("emailSubject") + "']", "Email header");
	Label emailFromLabel = new Label("//span[.='" + CommonUtils.getProperty("emailFrom") + "']", "Label with email from");
	
	
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
		sleep(5000);
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
			Integer testGroup = Integer.valueOf(CommonUtils.getProperty("percentageOfTestGroup"));
			float s = (float) ((float)testGroup/(float)100);
			amountEmailsInSplit = (int) (amountOfEmails/amountOfSplits * s);
			
		}
		for (int i = 1; i <= amountOfSplits; i++) {
			String subj = CommonUtils.getProperty("emailSplitsSubject") + " Split " + i;
			Integer amountEmails = null;
			try {
				amountEmails = new EmailClient().getEmailsBySubject(subj).size();
			} catch (MailosaurException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if ( ! CommonUtils.getProperty("Environment").equalsIgnoreCase("dev")) {
				verify(amountEmails, amountEmailsInSplit, "Not all emails were delivered");
			}else{
				verify(amountEmails > 0, true, "O emails were delivered");	
			}
			/*if (amountOfSplits > 1) {
				emailBlastSubject.path = emailBlastSubject.path.replace(CommonUtils.getProperty("emailSubject"), CommonUtils.getProperty("emailSplitsSubject") + " Split " + i);
			}
			emailBlastSubject.setImplicity(3000);
			emailBlastSubject.click();
			emailBlastSubject.setImplicity(SeleneseTestCase.defaultTimeOut);			
			if (!CommonUtils.getProperty("Environment").equalsIgnoreCase("dev")) {
				verify(emailFromLabel.getElementsAmount(), amountEmailsInSplit, "Not all emails were delivered");
			}else{
				verify(emailFromLabel.getElementsAmount() > 0, true, "O emails were delivered");	
			}			
			importantEmails.click();
			emailBlastSubject.path = emailBlastSubject.path.replace(CommonUtils.getProperty("emailSplitsSubject") + " Split " + i, CommonUtils.getProperty("emailSubject"));*/
		}
		
		return new LoginPage();
	}
	
	
}
