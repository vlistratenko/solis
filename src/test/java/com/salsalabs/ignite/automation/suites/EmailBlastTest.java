package com.salsalabs.ignite.automation.suites;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mailosaur.model.Email;
import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.PropertyName;
import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;
import com.salsalabs.ignite.automation.pages.hq.emails.AddEmailsPage_PublishTab;
import com.salsalabs.ignite.automation.pages.hq.emails.EmailBlastDetailsPage;

/**
 * <b>This test contains scenarios related to Email Blast Sending (TestLink: TC18, TC19, TC20, TC21)</b>
 *
 */
public class EmailBlastTest extends SeleneseTestCase{
	
	/**
	 * <b>Create and send single/split email blast</b>
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
	 * <li> Add some splits if it's required
	 * <li> Fill From Address
	 * <li> Fill Subject
	 * <li> Insert a Link to the email
	 * <li> Click 'Next' Button
	 * <li> <font color="green"><b>Verify Audience number</b></font>
	 * <li> Click 'Send now' Button
	 * <li> <font color="green"><b>Verify Email is present in the All Messaging table. Check the status.</b></font>
	 * <li> Wait for emails
	 * <li> Open emails
	 * <li> Click on the link
	 * </ul>
	 *  
	 */	
	@Parameters({ "sendEmail.from", "sendEmail.openAmount", "sendEmail.clickAmount", "sendEmail.unsubscribeAmount", "sendEmail.emailOfSupporter", "sendEmail.amountOfSupporter", "sendEmail.hardBounceAmount", "sendEmail.percentageOfTestGroup", "sendEmail.splitsAmount" })
	@Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = { "email.sendEmails" }, description = "")
	public void sendEmailBlastTest(String emailFrom, Integer openAmount, Integer clickAmount, Integer unsubAmount, String emailOfSupporter, Integer amountOfSupporters, Integer hardBounceAmount, Integer percentageOfTestGroup, int splitsAmount) {
		String emailBlastName = "TestV" + CommonUtils.getUnicName();
		String emailSubject = "TestVAuto" + CommonUtils.getUnicName();
		CommonUtils.setProperty(PropertyName.EMAIL_FROM, emailFrom);
		if (splitsAmount > 1) {
			CommonUtils.setProperty(PropertyName.EMAIL_SPLIT_BLAST_NAME, emailBlastName);
			CommonUtils.setProperty(PropertyName.EMAIL_SPLIT_SUBJECT, emailSubject);
			CommonUtils.setProperty(PropertyName.PERCENTAGE_OF_TEST_GROUP, percentageOfTestGroup.toString());
		} else {
			CommonUtils.setProperty(PropertyName.EMAIL_BLAST_NAME, emailBlastName);
			CommonUtils.setProperty(PropertyName.EMAIL_SUBJECT, emailSubject);
		}
		
		String propertyPublished = splitsAmount > 1 ? PropertyName.AMOUNT_OF_PUBLISHED_SPLIT_EMAILS : PropertyName.AMOUNT_OF_PUBLISHED_EMAILS;
		
		LoginPage loginPage = new LoginPage();
		loginPage.
		doSuccessLogin().
		openMessagingPage().
		openEmailBlastsPage().
		openAddEmailPage().
		fillAllFieldsAndGoForward(emailBlastName).
		selectAudienceType("Selected segments of your list, or specific supporters").//(""Entire list ").
		addSupporters(amountOfSupporters, propertyPublished).
		addSupporters("unex", hardBounceAmount, propertyPublished).
		openComposePage().
		selectLayout(1).
		fillAllFieldsAndGoForward(emailSubject, emailFrom, splitsAmount, "google.com").
		fillAllFieldsAndPublish(percentageOfTestGroup, splitsAmount).
		openDashboard().
		openMessagingPage().
		verifyActivityIsPresentInTableAllActivities("Email", emailBlastName);
		
		Integer published = Integer.valueOf(CommonUtils.getProperty(propertyPublished));
		if (published == amountOfSupporters) {
			hardBounceAmount = 0;
		}
		new AddEmailsPage_PublishTab().
		verifyAmountOfEmails(published - hardBounceAmount, splitsAmount, 20, false);
		
		Map<String, List<?>> emails = loginPage.openEmails(splitsAmount, openAmount);
		loginPage.clickLinkInEmail(emails, splitsAmount, "http://google.com", clickAmount);
		loginPage.unsubscribeByEmail(emails, splitsAmount, unsubAmount);
	}
	
	/**
	 * <b>Verify email KPI</b>
	 * <p>
	 * Steps:
	 * <ul>
	 * <li> Login into existing organization
	 * <li> Open Messaging page --> Emails
	 * <li> Click on the last completed email
	 * <li> <font color="green"><b>In the delivery section, verify: [a] the delivery rate is displayed and the bar is colored the appropriate amount; [b] numbers are displayed for soft and hard bounces. </b></font>
	 * <li> <font color="green"><b>In the performance section, verify that the rates are displayed and the bar is colored the appropriate amounts for: [a] open rate; [b] click rate; [d] unsubscribe rate. </b></font>
	 * <li> Make screenshot
	 * </ul>
	 *  
	 */	
	@Parameters({ "sendEmail.openAmount", "sendEmail.clickAmount", "sendEmail.unsubscribeAmount", "sendEmail.amountOfSupporter", "sendEmail.hardBounceAmount", "sendEmail.splitsAmount" })
	@Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = { "email.verifyKPI" }, description = "")
	public void verifyEmailKPITest(Integer openAmount, Integer clickAmount, Integer unsubAmount, Integer amountOfSupporters, Integer hardBounceAmount, Integer splitAmount) {
		String name;
		Integer published;
		if (splitAmount > 1) {
			name = CommonUtils.getProperty(PropertyName.EMAIL_SPLIT_BLAST_NAME);
			published = Integer.valueOf(CommonUtils.getProperty(PropertyName.AMOUNT_OF_PUBLISHED_SPLIT_EMAILS));
		} else {
			name = CommonUtils.getProperty(PropertyName.EMAIL_BLAST_NAME);
			published = Integer.valueOf(CommonUtils.getProperty(PropertyName.AMOUNT_OF_PUBLISHED_EMAILS));
		}
		if (published == amountOfSupporters) {
			hardBounceAmount = 0;
		}
		new LoginPage().
		doSuccessLogin().
		openMessagingPage().
		openEmailBlastsPage().
		openEmailBlastDetailsPage(name).
		verifyDeliveryRateStat(published, hardBounceAmount).
		verifyOpenRateStat(openAmount, published, hardBounceAmount).
		verifyClickRateStat(clickAmount, published, hardBounceAmount).
		verifyHardBouncesStat(hardBounceAmount).
		verifyUnsubRateStat(unsubAmount, published, hardBounceAmount);
		
		if (splitAmount > 1) {
			new EmailBlastDetailsPage().verifySplitTestResult(splitAmount, openAmount, clickAmount, unsubAmount, published, hardBounceAmount);
		}
		
		makeScreenshot("Email KPI Success. " + name);
	}
}
