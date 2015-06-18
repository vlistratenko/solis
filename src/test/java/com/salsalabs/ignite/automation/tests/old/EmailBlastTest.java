package com.salsalabs.ignite.automation.tests.old;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.MailosourEmailClient;
import com.salsalabs.ignite.automation.common.HttpClient;
import com.salsalabs.ignite.automation.common.PropertyName;
import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.common.Supporter;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;
import com.salsalabs.ignite.automation.pages.hq.emails.AddEmailsPage_PublishTab;
import com.salsalabs.ignite.automation.pages.hq.manage.UnsubscribePage;
import com.salsalabs.ignite.automation.pages.other.Dispatcher;

public class EmailBlastTest extends SeleneseTestCase{
	
	/*@Parameters({"sendEmail.from", "sendEmail.openAmount", "sendEmail.clickAmount", "sendEmail.emailOfSupporter", "sendEmail.amountOfSupporter", "sendEmail.hardBounceAmount"})
	@Test(retryAnalyzer=RetryAnalyzer.class,  priority=10, groups = {"email.sendEmails", ""}, description = "")
	public void sendEmailsTest(String emailFrom, Integer openAmount, Integer clickAmount, String emailOfSupporter, Integer amountOfSupporters, Integer hardBounceAmount) {
		String emailBlastName = "TestV" + CommonUtils.getUnicName();
		String emailSubject = "TestVAuto" + CommonUtils.getUnicName();
		CommonUtils.setProperty(PropertyName.EMAIL_BLAST_NAME, emailBlastName);
		CommonUtils.setProperty(PropertyName.EMAIL_SUBJECT, emailSubject);	
		CommonUtils.setProperty(PropertyName.EMAIL_FROM, emailFrom);
		
		if(emailOfSupporter.equals("")){
			emailOfSupporter = emailClient.getEmailBox("");
		}
		
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
		
		loginPage.openEmails(emailSubject, openAmountInteger.valueOf(CommonUtils.getProperty(PropertyName.AMOUNT_OF_PUBLISHED_EMAILS)));
		loginPage.clickLinkInEmail(emailSubject, "http://salsalabs.com", clickAmountInteger.valueOf(CommonUtils.getProperty(PropertyName.AMOUNT_OF_PUBLISHED_EMAILS)));
		
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
	
	@Parameters({"sendEmail.from",
		"sendEmail.openAmount",
		"sendEmail.clickAmount",
		"sendEmail.emailOfSupporter",
		"sendEmail.amountOfSupporter",
		"sendEmail.hardBounceAmount",
		"sendEmail.percentageOfTestGroup",
		"sendEmail.splitsAmount"})
	@Test(retryAnalyzer=RetryAnalyzer.class,  priority=20, groups = {"email.sendSplitEmails"}, description = "528:62:Split emails were NOT sent")
	public void sendSplitEmailsTest(String emailFrom,
			Integer openAmount,
			Integer clickAmount,
			String emailOfSupporter,
			Integer amountOfSupporters,
			Integer hardBounceAmount,
			Integer percentageOfTestGroup,
			int splitsAmount) {
		String emailBlastName = "TestV" + CommonUtils.getUnicName();
		String emailSubject = "TestVAuto" + CommonUtils.getUnicName();
		CommonUtils.setProperty(PropertyName.EMAIL_BLAST_NAME, emailBlastName);
		CommonUtils.setProperty(PropertyName.EMAIL_SPLIT_SUBJECT, emailSubject);
		CommonUtils.setProperty(PropertyName.EMAIL_FROM, emailFrom);
		CommonUtils.setProperty(PropertyName.PERCENTAGE_OF_TEST_GROUP, percentageOfTestGroup.toString());
		
		if(emailOfSupporter.equals("")){
			emailOfSupporter = emailClient.getEmailBox("");
		}
		
		LoginPage loginPage = new LoginPage();
		loginPage.
		doSuccessLogin(CommonUtils.getProperty(PropertyName.ADMIN_EMAIL),  CommonUtils.getProperty(PropertyName.ADMIN_PASSWORD)).
		openMessagingPage().
		openEmailBlastsPage().
		openAddEmailPage().
		fillAllFieldsAndGoForward(emailBlastName).
		selectAudienceType(" Selected segments of your list, or specific supporters").//(""Entire list ").
		addSupporters(emailOfSupporter, amountOfSupporters).
		//addSupporters("unex", hardBounceAmount).
		SelectEmailType().
		openComposePage().
		selectLayout("Basic").
		fillAllFieldsAndGoForward(emailSubject, emailFrom, splitsAmount).
		fillAllFieldsAndPublish(percentageOfTestGroup, splitsAmount).
		verifyAmountOfEmails(Integer.valueOf(CommonUtils.getProperty(PropertyName.AMOUNT_OF_PUBLISHED_EMAILS)) - hardBounceAmount, splitsAmount, 15, false).
		verifyAmountOfEmails(Integer.valueOf(CommonUtils.getProperty(PropertyName.AMOUNT_OF_PUBLISHED_EMAILS)) - hardBounceAmount, splitsAmount, 5, false).
		verifyAmountOfEmails(Integer.valueOf(CommonUtils.getProperty(PropertyName.AMOUNT_OF_PUBLISHED_EMAILS)) - hardBounceAmount, splitsAmount, 5, true);
		
		loginPage.openEmails(emailSubject, openAmount);
		loginPage.clickLinkInEmail(emailSubject, "http://salsalabs.com", clickAmount);


		
		loginPage.
		doSuccessLogin(CommonUtils.getProperty(PropertyName.ADMIN_EMAIL),  CommonUtils.getProperty(PropertyName.ADMIN_PASSWORD)).
		openMessagingPage().
		openEmailBlastsPage().
		openEmailBlastDetailsPage(CommonUtils.getProperty(PropertyName.EMAIL_BLAST_NAME)).
		verifyOpenRateStat(openAmount,hardBounceAmount).
		verifyClickRateStat(clickAmount,hardBounceAmount).
		verifyHardBouncesStat(hardBounceAmount);
		
		makeScreenshot("Email KPI Success");
		
	}

	//@Parameters({"sendEmail.from", "sendEmail.openAmount", "sendEmail.clickAmount", "sendEmail.emailOfSupporter", "sendEmail.amountOfSupporter"})
	@Test(retryAnalyzer=RetryAnalyzer.class,  priority=10, groups = {"email.sendEmailsToUnsubscribedSupporters"}, description = "")
	public void sendEmailsToUnsubscribedSupporters() throws KeyManagementException, ClientProtocolException, NoSuchAlgorithmException, KeyStoreException, URISyntaxException, IOException, JSONException {
		
		String emailSubj = "SendEmailsToUnsubscribed" + CommonUtils.getUnicName();
		String emailBlastName = CommonUtils.getUnicName();
		CommonUtils.setProperty(PropertyName.EMAIL_SUBJECT, emailSubj);
		
		Supporter supporter1 = new Supporter();
		Supporter supporter2 = new Supporter();
		Supporter supporter3 = new Supporter();
		Supporter supporter4 = new Supporter();
		
		supporter1.setFinalEMAIL(emailClient.getEmailBox("emailforunsub" + CommonUtils.getUnicName()));
		new HttpClient().login(CommonUtils.getProperty(PropertyName.ADMIN_EMAIL), CommonUtils.getProperty(PropertyName.ADMIN_PASSWORD)).
			createSupporter(supporter1.getSupporterJSON(supporter1.getFinalEMAIL()));
		
		supporter2.setFinalEMAIL(emailClient.getEmailBox("emailforunsub" + CommonUtils.getUnicName()));
		new HttpClient().login(CommonUtils.getProperty(PropertyName.ADMIN_EMAIL), CommonUtils.getProperty(PropertyName.ADMIN_PASSWORD)).
			createSupporter(supporter2.getSupporterJSON(supporter2.getFinalEMAIL()));
		
		supporter3.setFinalEMAIL(emailClient.getEmailBox("emailforunsub" + CommonUtils.getUnicName()));
		new HttpClient().login(CommonUtils.getProperty(PropertyName.ADMIN_EMAIL), CommonUtils.getProperty(PropertyName.ADMIN_PASSWORD)).
			createSupporter(supporter3.getSupporterJSON(supporter3.getFinalEMAIL()));
		
		supporter4.setFinalEMAIL(emailClient.getEmailBox("emailforunsub" + CommonUtils.getUnicName()));
		new HttpClient().login(CommonUtils.getProperty(PropertyName.ADMIN_EMAIL), CommonUtils.getProperty(PropertyName.ADMIN_PASSWORD)).
			createSupporter(supporter4.getSupporterJSON(supporter4.getFinalEMAIL()));
		
		
		LoginPage loginPage = new LoginPage();
		UnsubscribePage unsPage = loginPage.
		doSuccessLogin(CommonUtils.getProperty(PropertyName.ADMIN_EMAIL),  CommonUtils.getProperty(PropertyName.ADMIN_PASSWORD)).
		openSettingsPage().switchToUnsubscribeSettingsPage().openUnsubscribePage();
		
		unsPage.
		fillUnsubscribeForm(supporter1.getFinalEMAIL()).
		clickUnsubscribeButton().
		verifyUnsubscribeIsSuccesses();
		
		unsPage.clearCache().refreshPage();
		
		unsPage.
		fillUnsubscribeForm(supporter2.getFinalEMAIL()).
		clickUnsubscribeButton().
		verifyUnsubscribeIsSuccesses();
		
		unsPage.
		backToUnsubscribeSettingsPage().		
		openMessagingPage().
		openEmailBlastsPage().
		openAddEmailPage().
		fillAllFieldsAndGoForward(emailBlastName).
		selectAudienceType(" Selected segments of your list, or specific supporters").
		addSupporters("emailforunsub", 4).
		SelectEmailType().
		openComposePage().
		selectLayout("Basic").
		fillAllFieldsAndGoForward(emailSubj, "", 1).
		fillAllFieldsAndPublish(100, 1).
		verifyAmountOfEmailsForPublishing("2").
		verifyAmountOfEmails(Integer.valueOf(CommonUtils.getProperty(PropertyName.AMOUNT_OF_PUBLISHED_EMAILS)), 1, 15, true);
		
		new Dispatcher().
				openDispatcher().
				selectJob("Email Blast - Sender").
				verifyValue("Throughput", "Email Blast: " + emailBlastName, "2 / 2 (2 ps)", "Wrong value in the dispatcher", true);

	}*/
}
