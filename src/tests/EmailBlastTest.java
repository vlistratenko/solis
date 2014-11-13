package tests;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import objects.Supporter;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.HQ.LoginPage;
import pages.HQ.Manage.UnsubscribePage;
import selenium.CommonUtils;
import selenium.EmailClient;
import selenium.HttpClient;
import selenium.SeleneseTestCase;

public class EmailBlastTest extends SeleneseTestCase{
	
	@Parameters({"sendEmail.From", "sendEmail.OpenAmount", "sendEmail.ClickAmount", "sendEmail.emailOfSupporter", "sendEmail.amountOfSupporter"})
	@Test( priority=10, groups = {"email.sendEmails", ""}, description = "")
	public void sendEmailsTest(String emailFrom, Integer openAmount, Integer clickAmount, String emailOfSupporter, Integer amountOfSupporters, Integer hardBounceAmount) {
		String emailBlastName = "TestV" + CommonUtils.getUnicName();
		String emailSubject = "TestVAuto" + CommonUtils.getUnicName();
		CommonUtils.setProperty("emailBlastName", emailBlastName);
		CommonUtils.setProperty("emailSubject", emailSubject);	
		CommonUtils.setProperty("emailFrom", emailFrom);
		
		if(emailOfSupporter.equals("")){
			emailOfSupporter = EmailClient.getEmailBox("");
		}
		
		LoginPage loginPage = new LoginPage();
		loginPage.
		doSuccessLogin(CommonUtils.getProperty("Admin.email"),  CommonUtils.getProperty("Admin.Password")).
		openActivitiesPage().
		openEmailBlastsPage().
		openAddEmailPage().
		fillAllFieldsAndGoForward(emailBlastName).
		selectAudienceType(" Selected segments of your list, or specific supporters").//(""Entire list ").
		addSupporters(emailOfSupporter, amountOfSupporters).
		addSupporters("unex", hardBounceAmount).
		SelectEmailType().
		openComposePage().
		selectLayout("Basic").
		fillAllFieldsAndGoForward(emailSubject, emailFrom, 1).
		fillAllFieldsAndPublish(100, 1).
		verifyAmountOfEmails(Integer.valueOf(CommonUtils.getProperty("amountOfPablishedEmails")) - hardBounceAmount, 1, 15, false).
		verifyAmountOfEmails(Integer.valueOf(CommonUtils.getProperty("amountOfPablishedEmails")) - hardBounceAmount, 1, 5, false).
		verifyAmountOfEmails(Integer.valueOf(CommonUtils.getProperty("amountOfPablishedEmails")) - hardBounceAmount, 1, 5, true);
		
		loginPage.openEmails(emailSubject, openAmount/*Integer.valueOf(CommonUtils.getProperty("amountOfPablishedEmails"))*/);
		loginPage.clickLinkInEmail(emailSubject, "http://salsalabs.com", clickAmount/*Integer.valueOf(CommonUtils.getProperty("amountOfPablishedEmails"))*/);
		
		loginPage.
		doSuccessLogin(CommonUtils.getProperty("Admin.email"),  CommonUtils.getProperty("Admin.Password")).
		openActivitiesPage().
		openEmailBlastsPage().
		openEmailBlastDetailsPage(CommonUtils.getProperty("emailBlastName")).
		verifyOpenRateStat(openAmount).
		verifyClickRateStat(clickAmount).
		verifyHardBouncesStat(hardBounceAmount);
		
		makeScreenshot("Email KPI Success");
	}
	

	//@Parameters({"sendEmail.From", "sendEmail.OpenAmount", "sendEmail.ClickAmount", "sendEmail.emailOfSupporter", "sendEmail.amountOfSupporter"})
	@Test( priority=10, groups = {"email.sendEmailsToUnsubscribedSupporters"}, description = "")
	public void sendEmailsToUnsubscribedSupporters() throws KeyManagementException, ClientProtocolException, NoSuchAlgorithmException, KeyStoreException, URISyntaxException, IOException, JSONException {
		
		String emailSubj = "SendEmailsToUnsubscribed" + CommonUtils.getUnicName();
		CommonUtils.setProperty("emailSubject", emailSubj);
		
		Supporter supporter1 = new Supporter();
		Supporter supporter2 = new Supporter();
		Supporter supporter3 = new Supporter();
		Supporter supporter4 = new Supporter();
		
		supporter1.finalEMAIL = EmailClient.getEmailBox("emailforunsub" + CommonUtils.getUnicName());
		new HttpClient().login(CommonUtils.getProperty("Admin.email"), CommonUtils.getProperty("Admin.Password")).
			createSupporter(supporter1.getSupporterJSON(supporter1.finalEMAIL));
		
		supporter2.finalEMAIL = EmailClient.getEmailBox("emailforunsub" + CommonUtils.getUnicName());
		new HttpClient().login(CommonUtils.getProperty("Admin.email"), CommonUtils.getProperty("Admin.Password")).
			createSupporter(supporter2.getSupporterJSON(supporter2.finalEMAIL));
		
		supporter3.finalEMAIL = EmailClient.getEmailBox("emailforunsub" + CommonUtils.getUnicName());
		new HttpClient().login(CommonUtils.getProperty("Admin.email"), CommonUtils.getProperty("Admin.Password")).
			createSupporter(supporter3.getSupporterJSON(supporter3.finalEMAIL));
		
		supporter4.finalEMAIL = EmailClient.getEmailBox("emailforunsub" + CommonUtils.getUnicName());
		new HttpClient().login(CommonUtils.getProperty("Admin.email"), CommonUtils.getProperty("Admin.Password")).
			createSupporter(supporter4.getSupporterJSON(supporter4.finalEMAIL));
		
		
		LoginPage loginPage = new LoginPage();
		UnsubscribePage unsPage = loginPage.
		doSuccessLogin(CommonUtils.getProperty("Admin.email"),  CommonUtils.getProperty("Admin.Password")).
		openSettingsPage().switchToUnsubscribeSettingsPage().openUnsubscribePage();
		
		unsPage.
		fillUnsubscribeForm(supporter1.finalEMAIL).
		clickUnsubscribeButton().
		verifyUnsubscribeIsSuccesses();
		
		unsPage.clearCache().refreshPage();
		
		unsPage.
		fillUnsubscribeForm(supporter2.finalEMAIL).
		clickUnsubscribeButton().
		verifyUnsubscribeIsSuccesses();
		
		unsPage.
		backToUnsubscribeSettingsPage().		
		openActivitiesPage().
		openEmailBlastsPage().
		openAddEmailPage().
		fillAllFieldsAndGoForward(CommonUtils.getUnicName()).
		selectAudienceType(" Selected segments of your list, or specific supporters").
		addSupporters("emailforunsub", 4).
		SelectEmailType().
		openComposePage().
		selectLayout("Basic").
		fillAllFieldsAndGoForward(emailSubj, "", 1).
		fillAllFieldsAndPublish(100, 1).
		verifyAmountOfEmailsForPublishing("2").
		verifyAmountOfEmails(Integer.valueOf(CommonUtils.getProperty("amountOfPablishedEmails")), 1, 15, true);
	}
}
