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

public class SettingsTests extends SeleneseTestCase{
	
	@Parameters({"wePayNickName", "wePayDescr", "wePayOrgType"})
	@Test(retryAnalyzer=RetryAnalyzer.class,  priority=10, groups = {"settings.wepay", ""}, description = "")
	public void createWePayAcountTest(String wePayNickName, String wePayDescr, String wePayOrgType) {		
		wePayNickName = wePayNickName + CommonUtils.getUnicName();
		LoginPage loginPage = new LoginPage();
		loginPage.
		doSuccessLogin(CommonUtils.getProperty(PropertyName.ADMIN_EMAIL), CommonUtils.getProperty(PropertyName.ADMIN_PASSWORD)).
		openSettingsPage().
		switchToPaymentGatewaysPage().
		openAddWePayPage().
		createWePayAcount(wePayNickName, wePayDescr, wePayOrgType).
		verifyCreatedAccountExists(wePayNickName).
		verifyWePayEmail().
		openWePayConfirmationPage();
	}
	

	@Test(retryAnalyzer=RetryAnalyzer.class,  priority=10, groups = {"settings.import", ""}, description = "")
	public void importSupportersTest() {
		
		LoginPage loginPage = new LoginPage();
		loginPage.
		doSuccessLogin(CommonUtils.getProperty(PropertyName.ADMIN_EMAIL), CommonUtils.getProperty(PropertyName.ADMIN_PASSWORD)).
		openSettingsPage().
		openImportPage().
		startNewImport().
		fillFirstStep("AutoTestImport", "Test description").
		fillSecondStep("2").
		fillThirdStep().
		openImportPage().
		verifyStatusOfImport(CommonUtils.getProperty(PropertyName.IMPORT_NAME) , "COMPLETED").
		openAudiencePage().
		openSupportersPage().
		searchSupporter(new Supporter().getImportedEmail()).
		openSupporterDetailsPage().
		verifySupporterData(new Supporter().getImportedEmail(),
		new Supporter().getFirstName(),
		new Supporter().getLastName(),
		new Supporter().getcPhone(),
		new Supporter().getAddressLine1(),
		new Supporter().getCity(),
		new Supporter().getZipCode(),
		new Supporter().getFacebook(),
		new Supporter().getTwitter());
	}
	
	@Test(retryAnalyzer=RetryAnalyzer.class,  priority=10, groups = {"settings.unsubscribeSupporter", ""}, description = "")
	public void unsubscribeSupporterTest() throws KeyManagementException, ClientProtocolException, NoSuchAlgorithmException, KeyStoreException, URISyntaxException, IOException, JSONException {
		String introductoryText = "Message edited by Auto script";
		Supporter supporter = new Supporter();
		supporter.setFinalEMAIL(emailClient.getEmailBox(CommonUtils.getUnicName()));
		new HttpClient().login(CommonUtils.getProperty(PropertyName.ADMIN_EMAIL), CommonUtils.getProperty(PropertyName.ADMIN_PASSWORD)).
			createSupporter(supporter.getSupporterJSON(supporter.getFinalEMAIL()));
		CommonUtils.setProperty(PropertyName.UNSUBSCRIBED_SUPPORTER, supporter.getFinalEMAIL());
		LoginPage loginPage = new LoginPage();
		loginPage.
		doSuccessLogin(CommonUtils.getProperty(PropertyName.ADMIN_EMAIL), CommonUtils.getProperty(PropertyName.ADMIN_PASSWORD)).
		openSettingsPage().
		switchToUnsubscribeSettingsPage().
		editIntroductionaryText(introductoryText).
		openUnsubscribePage().
		verifyInroductoryText(introductoryText).
		fillUnsubscribeForm(supporter.getFinalEMAIL()).
		clickUnsubscribeButton().
		verifyUnsubscribeIsSuccesses().
		backToUnsubscribeSettingsPage().
		openAudiencePage().
		openSupportersPage().
		searchSupporter(supporter.getFinalEMAIL()).
		openSupporterDetailsPage().
		verifySupporterData(supporter.getFinalEMAIL(),
				supporter.getFirstName(),
				supporter.getLastName(),
				supporter.getcPhone(),
				"",
				supporter.getCity(),
				supporter.getZipCode(),
				supporter.getFacebook(),
				supporter.getTwitter());
		CommonUtils.checkAndFail("unsubscribeSupporterTest");
		
	}
	
	@Test(retryAnalyzer=RetryAnalyzer.class,  priority=13, groups = {"settings.unsubscribeUnexistedSupporterTest", ""}, description = "")
	public void unsubscribeUnexistedSupporterTest(){
		Supporter supporter = new Supporter();
		supporter.setFinalEMAIL(emailClient.getEmailBox(CommonUtils.getUnicName()));
		LoginPage loginPage = new LoginPage();
		loginPage.
		doSuccessLogin(CommonUtils.getProperty(PropertyName.ADMIN_EMAIL), CommonUtils.getProperty(PropertyName.ADMIN_PASSWORD)).
		openSettingsPage().
		switchToUnsubscribeSettingsPage().
		openUnsubscribePage().
		fillUnsubscribeForm(supporter.getFinalEMAIL()).
		clickUnsubscribeButton().
		verifyUnsubscribeIsSuccesses().
		backToUnsubscribeSettingsPage().
		openAudiencePage().
		openSupportersPage().
		searchSupporter(supporter.getFinalEMAIL()).
		checkSupporterNotExists(supporter.getFinalEMAIL());
		CommonUtils.checkAndFail("unsubscribeSupporterTest");		
	}
	
	@Test(retryAnalyzer=RetryAnalyzer.class,  priority=10, groups = {"settings.unsubscribeSupporterViaEMAIL", ""}, description = "")
	public void unsubscribeSupporterByEmailTest() throws KeyManagementException, ClientProtocolException, NoSuchAlgorithmException, KeyStoreException, URISyntaxException, IOException, JSONException {

		/*Supporter supporter = new Supporter();
		supporter.setFinalEMAIL(emailClient.getEmailBox(CommonUtils.getUnicName()));
		new HttpClient().login(CommonUtils.getProperty(PropertyName.ADMIN_EMAIL), CommonUtils.getProperty(PropertyName.ADMIN_PASSWORD)).
			createSupporter(supporter.getSupporterJSON(supporter.getFinalEMAIL()));
		String emailSubj = "Subj" + CommonUtils.getUnicName(); 
		CommonUtils.setProperty(PropertyName.EMAIL_SUBJECT, emailSubj);
		
		LoginPage loginPage = new LoginPage();
		loginPage.
		doSuccessLogin(CommonUtils.getProperty(PropertyName.ADMIN_EMAIL), CommonUtils.getProperty(PropertyName.ADMIN_PASSWORD)).
		openMessagingPage().
		openEmailBlastsPage().
		openAddEmailPage().
		fillAllFieldsAndGoForward(CommonUtils.getUnicName()).
		selectAudienceType(" Selected segments of your list, or specific supporters").
		addSupporters(supporter.getFinalEMAIL(), 1).
		SelectEmailType().
		openComposePage().
		selectLayout("Basic").
		fillAllFieldsAndGoForward(emailSubj, "", 1).
		fillAllFieldsAndPublish(100, 1).
		verifyAmountOfEmails(1, 1, 15, true).
		openUnsubscribeLinkFromEmail(emailSubj).
		fillUnsubscribeForm(supporter.getFinalEMAIL()).
		clickUnsubscribeButton().
		verifyUnsubscribeIsSuccesses();
		
		loginPage.
		openPage().
		doSuccessLogin(CommonUtils.getProperty(PropertyName.ADMIN_EMAIL), CommonUtils.getProperty(PropertyName.ADMIN_PASSWORD)).
		openAudiencePage().
		openSupportersPage().searchSupporter(supporter.getFinalEMAIL()).
		openSupporterDetailsPage().
		verifySupporterStatus("Unsubscribed");
		CommonUtils.checkAndFail("unsubscribeSupporterByEmailTest");*/
		
	}
	
	
}
