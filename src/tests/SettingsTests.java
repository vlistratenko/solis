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
import selenium.CommonUtils;
import selenium.EmailClient;
import selenium.HttpClient;
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
		searchSupporter(new Supporter().importedEmail).
		openSupporterDetailsPage().
		verifySupporterData(new Supporter().importedEmail,
		new Supporter().firstName,
		new Supporter().lastName,
		new Supporter().cPhone,
		new Supporter().addressLine1,
		new Supporter().City,
		new Supporter().zipCode,
		new Supporter().facebook,
		new Supporter().twitter,
		"Subscribed");
	}
	
	@Test( priority=10, groups = {"settings.unsubscribeSupporter", ""}, description = "")
	public void unsubscribeSupporterTest() throws KeyManagementException, ClientProtocolException, NoSuchAlgorithmException, KeyStoreException, URISyntaxException, IOException, JSONException {
		String introductoryText = "Message edited by Auto script";
		Supporter supporter = new Supporter();
		supporter.finalEMAIL = EmailClient.getEmailBox(CommonUtils.getUnicName());
		new HttpClient().login(CommonUtils.getProperty("Admin.email"), CommonUtils.getProperty("Admin.Password")).
			createSupporter(supporter.getSupporterJSON(supporter.finalEMAIL));
		CommonUtils.setProperty("unsubscribedSupporter", supporter.finalEMAIL);
		LoginPage loginPage = new LoginPage();
		loginPage.
		doSuccessLogin(CommonUtils.getProperty("Admin.email"), CommonUtils.getProperty("Admin.Password")).
		openSettingsPage().
		switchToUnsubscribeSettingsPage().
		editIntroductionaryText(introductoryText).
		openUnsubscribePage().
		verifyInroductoryText(introductoryText).
		fillUnsubscribeForm(supporter.finalEMAIL).
		clickUnsubscribeButton().
		verifyUnsubscribeIsSuccesses().
		backToUnsubscribeSettingsPage().
		openAudiencePage().
		openSupportersPage().
		searchSupporter(supporter.finalEMAIL).
		openSupporterDetailsPage().
		verifySupporterData(supporter.finalEMAIL,
				supporter.firstName,
				supporter.lastName,
				supporter.cPhone,
				"",
				supporter.City,
				supporter.zipCode,
				supporter.facebook,
				supporter.twitter,
		"Unsubscribed");
		CommonUtils.checkAndFail("unsubscribeSupporterTest");
		
	}
	
	@Test( priority=10, groups = {"settings.unsubscribeSupporterViaEMAIL", ""}, description = "")
	public void unsubscribeSupporterByEmailTest() throws KeyManagementException, ClientProtocolException, NoSuchAlgorithmException, KeyStoreException, URISyntaxException, IOException, JSONException {

		Supporter supporter = new Supporter();
		supporter.finalEMAIL = EmailClient.getEmailBox(CommonUtils.getUnicName());
		new HttpClient().login(CommonUtils.getProperty("Admin.email"), CommonUtils.getProperty("Admin.Password")).
			createSupporter(supporter.getSupporterJSON(supporter.finalEMAIL));
		String emailSubj = "Subj" + CommonUtils.getUnicName(); 
		CommonUtils.setProperty("emailSubject", emailSubj);
		
		LoginPage loginPage = new LoginPage();
		loginPage.
		doSuccessLogin(CommonUtils.getProperty("Admin.email"), CommonUtils.getProperty("Admin.Password")).
		openActivitiesPage().
		openEmailBlastsPage().
		openAddEmailPage().
		fillAllFieldsAndGoForward(CommonUtils.getUnicName()).
		selectAudienceType(" Selected segments of your list, or specific supporters").
		addSupporters(supporter.finalEMAIL, 1).
		SelectEmailType().
		openComposePage().
		selectLayout("Basic").
		fillAllFieldsAndGoForward(emailSubj, "", 1).
		fillAllFieldsAndPublish(100, 1).
		verifyAmountOfEmails(1, 1, 15, true).
		openUnsubscribeLinkFromEmail(emailSubj).
		fillUnsubscribeForm(supporter.finalEMAIL).
		clickUnsubscribeButton().
		verifyUnsubscribeIsSuccesses();
		
		loginPage.
		openPage().
		doSuccessLogin(CommonUtils.getProperty("Admin.email"), CommonUtils.getProperty("Admin.Password")).
		openAudiencePage().
		openSupportersPage().searchSupporter(supporter.finalEMAIL).
		openSupporterDetailsPage().
		verifySupporterStatus("Unsubscribed");
		CommonUtils.checkAndFail("unsubscribeSupporterByEmailTest");
		
	}
	
	
}
