package tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.HQ.LoginPage;
import selenium.CommonUtils;
import selenium.EmailClient;
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
		verifyAmountOfEmails(Integer.valueOf(CommonUtils.getProperty("amountOfPablishedEmails")), 1, 15, false).
		verifyAmountOfEmails(Integer.valueOf(CommonUtils.getProperty("amountOfPablishedEmails")), 1, 5, false).
		verifyAmountOfEmails(Integer.valueOf(CommonUtils.getProperty("amountOfPablishedEmails")), 1, 5, true);
		
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
}
