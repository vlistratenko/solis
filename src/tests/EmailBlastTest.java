package tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.HQ.LoginPage;
import selenium.CommonUtils;

public class EmailBlastTest {
	
	@Parameters({"sendEmail.From"})
	@Test( priority=10, groups = {"email.sendEmails", ""}, description = "")
	public void sendEmailsTest(String emailFrom) {
		String emailBlastName = "TestV" + CommonUtils.getUnicName();
		String emailSubject = "TestVAuto" + CommonUtils.getUnicName();
		
		LoginPage loginPage = new LoginPage();
		loginPage.
		doSuccessLogin(CommonUtils.getProperty("Admin.email"),  CommonUtils.getProperty("Admin.Password")).
		openActivitiesPage().
		openEmailBlastsPage().
		openAddEmailPage().
		fillAllFieldsAndGoForward(emailBlastName).
		selectAudienceType("Entire list ").
		SelectEmailType().
		openComposePage().
		selectLayout("Basic").
		fillAllFieldsAndGoForward(emailSubject, emailFrom, 1).
		fillAllFieldsAndPublish(100, 1).
		verifyAmountOfEmails(Integer.valueOf(CommonUtils.getProperty("amountOfSupporters")), 1);
		
		CommonUtils.setProperty("emailBlastName", emailBlastName);
		CommonUtils.setProperty("emailSubject", emailSubject);	
		CommonUtils.setProperty("emailFrom", emailFrom);
	
	}
}
