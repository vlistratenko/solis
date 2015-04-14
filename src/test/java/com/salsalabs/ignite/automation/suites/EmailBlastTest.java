package com.salsalabs.ignite.automation.suites;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.PropertyName;
import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;
import com.salsalabs.ignite.automation.pages.hq.emails.AddEmailsPage_PublishTab;

/**
 * <b>This test contains scenarios related to Email Blast Sending (TestLink: TC18, TC19)</b>
 *
 */
public class EmailBlastTest extends SeleneseTestCase{
	
	/**
	 * <b>Create and send single email blast</b>
	 * <p>
	 * Steps:
	 * <ul>
	 * <li> Login into existing organization
	 * <li> Open Messaging page --> Emails
	 * <li> Click on Create an Email
	 * <li> Fill name and description
	 * <li> Add supporters to receive this email 
	 * <li> Add a supporter that will be bounced
	 * <li> Choose basic layout
	 * <li> Fill From Address
	 * <li> Fill Subject
	 * <li> Insert a Link to the email
	 * <li> Click 'Next' Button
	 * <li> <font color="green"><b>Verify Audience number</b></font>
	 * <li> Click 'Send now' Button
	 * <li> Wait for emails
	 * <li> Open emails
	 * <li> Click on the link
	 * <li> Open result page
	 * <li> <font color="green"><b>In the delivery section, verify: [a] the delivery rate is displayed and the bar is colored the appropriate amount; [b] numbers are displayed for soft and hard bounces. </b></font>
	 * <li> <font color="green"><b>In the performance section, verify that the rates are displayed and the bar is colored the appropriate amounts for: [a] open rate; [b] click rate;</b></font>
	 * <li> Make screenshot
	 * </ul>
	 *  
	 */
	@Parameters({"sendEmail.From", "sendEmail.OpenAmount", "sendEmail.ClickAmount", "sendEmail.emailOfSupporter", "sendEmail.amountOfSupporter", "sendEmail.hardBounceAmount"})
	@Test(retryAnalyzer=RetryAnalyzer.class,  priority=10, groups = {"email.sendEmails", ""}, description = "")
	public void sendEmailsTest(String emailFrom, Integer openAmount, Integer clickAmount, String emailOfSupporter, Integer amountOfSupporters, Integer hardBounceAmount) {
		String emailBlastName = "TestV" + CommonUtils.getUnicName();
		String emailSubject = "TestVAuto" + CommonUtils.getUnicName();
		CommonUtils.setProperty(PropertyName.EMAIL_BLAST_NAME, emailBlastName);
		CommonUtils.setProperty(PropertyName.EMAIL_SUBJECT, emailSubject);	
		CommonUtils.setProperty(PropertyName.EMAIL_FROM, emailFrom);
		
		LoginPage loginPage = new LoginPage();
		loginPage.
		doSuccessLogin().
		openMessagingPage().
		openEmailBlastsPage().
		openAddEmailPage().
		fillAllFieldsAndGoForward(emailBlastName).
		SelectEmailType().
		selectAudienceType(" Selected segments of your list, or specific supporters").//(""Entire list ").
		addSupporters(emailOfSupporter, amountOfSupporters).
		addSupporters("unex", hardBounceAmount).
		openComposePage().
		selectLayout("Basic").
		fillAllFieldsAndGoForward(emailSubject, emailFrom, 1).
		fillAllFieldsAndPublish(100, 1);
		Integer published = Integer.valueOf(CommonUtils.getProperty(PropertyName.AMOUNT_OF_PUBLISHED_EMAILS));
		if (published == amountOfSupporters) {
			hardBounceAmount = 0;
		}
		new AddEmailsPage_PublishTab().
		verifyAmountOfEmails(published - hardBounceAmount, 1, 15, false).
		verifyAmountOfEmails(published - hardBounceAmount, 1, 5, false).
		verifyAmountOfEmails(published - hardBounceAmount, 1, 5, true);
		
		loginPage.openEmails(emailSubject, openAmount);
		loginPage.clickLinkInEmail(emailSubject, "http://salsalabs.com", clickAmount);
		
		loginPage.
		doSuccessLogin().
		openMessagingPage().
		openEmailBlastsPage().
		openEmailBlastDetailsPage(CommonUtils.getProperty(PropertyName.EMAIL_BLAST_NAME)).
		verifyDeliveryRateStat(hardBounceAmount).
		verifyOpenRateStat(openAmount, hardBounceAmount).
		verifyClickRateStat(clickAmount, hardBounceAmount).
		verifyHardBouncesStat(hardBounceAmount);
		
		makeScreenshot("Email KPI Success");
	}
}
