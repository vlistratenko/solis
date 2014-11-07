package tests;


import objects.Supporter;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.HQ.LoginPage;
import selenium.CommonUtils;
import selenium.SeleneseTestCase;

public class SettingsTests extends SeleneseTestCase{
	
	@Parameters({"wePayNickName", "wePayDescr", "wePayOrgType"})
	@Test( priority=10, groups = {"settings.wepay", ""}, description = "")
	public void createWePayAcountTest(String wePayNickName, String wePayDescr, String wePayOrgType) {		
		wePayNickName = wePayNickName + CommonUtils.getUnicName();
		LoginPage loginPage = new LoginPage();
		loginPage.
		doSuccessLogin(CommonUtils.getProperty("Admin.email"), CommonUtils.getProperty("Admin.Password")).
		openSettingsPage().
		switchToPaymentGatewaysPage().
		openAddWePayPage().
		createWePayAcount(wePayNickName, wePayDescr, wePayOrgType).
		verifyCreatedAccountExists(wePayNickName).
		verifyWePayEmail().
		openWePayConfirmationPage();
	}
	

	@Test( priority=10, groups = {"settings.import", ""}, description = "")
	public void importSupportersTest() {
		
		LoginPage loginPage = new LoginPage();
		loginPage.
		doSuccessLogin(CommonUtils.getProperty("Admin.email"), CommonUtils.getProperty("Admin.Password")).
		openSettingsPage().
		openImportPage().
		startNewImport().
		fillFirstStep("AutoTestImport", "Test description").
		fillSecondStep("2").
		fillThirdStep().
		openImportPage().
		verifyStatusOfImport(CommonUtils.getProperty("ImportName") , "COMPLETED").
		openAudiencePage().
		openSupportersPage().
		searchSupporter("importedSup").
		openSupporterDetailsPage().
		verifySupporterData(new Supporter().Email,
		new Supporter().firstName,
		new Supporter().lastName,
		new Supporter().cPhone,
		new Supporter().addressLine1,
		new Supporter().City,
		new Supporter().zipCode,
		new Supporter().Facebook,
		new Supporter().twitter,
		"Subscribed");
	}
	
	@Test( priority=10, groups = {"settings.unsubscribeSupporter", ""}, description = "")
	public void unsubscribeSupporterTest() {
		String introductoryText = "Message edited by Auto script";
		Supporter supporter = new Supporter();
		
		LoginPage loginPage = new LoginPage();
		loginPage.
		doSuccessLogin(CommonUtils.getProperty("Admin.email"), CommonUtils.getProperty("Admin.Password")).
		openSettingsPage().
		switchToUnsubscribeSettingsPage().
		editIntroductionaryText(introductoryText).
		openUnsubscribePage().
		fillUnsubscribeForm(CommonUtils.getProperty("subscribedSupporter")).
		clickUnsubscribeButton().
		verifyInroductoryText(introductoryText).
		verifyUnsubscribeIsSuccesses().
		backToUnsubscribeSettingsPage().
		openAudiencePage().
		openSupportersPage().
		searchSupporter(CommonUtils.getProperty("subscribedSupporter")).
		openSupporterDetailsPage().
		verifySupporterData(supporter.subscribedEmail,
				supporter.firstName,
				supporter.lastName,
				"",
				"",
				supporter.City,
				supporter.zipCode,
				"",
				"",
		"Unsubscribed");
	}
	
	
}
