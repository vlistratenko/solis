package com.salsalabs.ignite.automation.suites.regression;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.common.Supporter;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;

import com.salsalabs.ignite.automation.pages.hq.supporters.SupportersAddPage;
import com.salsalabs.ignite.automation.pages.hq.supporters.SupportersPage;

public class CreateSupporterManuallyTest  extends SeleneseTestCase{
	private SupportersAddPage supporterAddPage;
	private SupportersPage supportersPage;
	private Supporter supporter;

	@Parameters({ "login", "Passward" , "textBoxCfName" , "sChoiceCfName" , "numberBoxCfName" , "dateCfname" , "yesNoCfName"})
	@Test(enabled = true, groups = {"createSupporterManually"}, retryAnalyzer = RetryAnalyzer.class)
	public void testCreateSupporterManually(String login , String passward , String textBoxCfName, String sChoiceCfName, String numberBoxCfName,String dateCfname, String yesNoCfName ) throws KeyManagementException, ClientProtocolException, NoSuchAlgorithmException, KeyStoreException, URISyntaxException, IOException, JSONException {
		
		doLoginAndOpenAddSupporterPage(login, passward );
		supporter = Supporter.generateSupporter();
		supportersPage = supporterAddPage.
		createNewSupporterWithCustomFields(login, passward, supporter , textBoxCfName , dateCfname , numberBoxCfName, sChoiceCfName, yesNoCfName);
		supportersPage.verifySupporterOnTopOfTableFull(supporter).
		openSupporterDetailsPage().verifySupporterData(supporter.getFinalEMAIL(),
				supporter.getFirstName(),
				supporter.getLastName(),
				supporter.getcPhone(),
				supporter.getAddressLine1(),
				supporter.getCity(),				
				supporter.getZipCode(),
				supporter.getFacebook(),
				supporter.getTwitter(), 
				supporter.getExternalId(),
				supporter.getBirthdate(),
				supporter.getMiddleName(), 
				supporter.getLinkedin()).
		verifySupporterCustomFieldData(
				textBoxCfName, 
				dateCfname, 
				numberBoxCfName ,
				yesNoCfName, 
				sChoiceCfName, 
				SupportersAddPage.supportercustomFieldtextBoxValue, 
				SupportersAddPage.supportercustomFielddDateValue, 
				SupportersAddPage.supportercustomFieldNumberBoxValue,
				SupportersAddPage.boleanOptionValue,
				SupportersAddPage.singleChoiceValue
				);
	}
	
	private void doLoginAndOpenAddSupporterPage(String login , String passward) {
		supporterAddPage = new LoginPage().
		doSuccessLogin(login, passward).
				openAudiencePage().
				openSupportersPage().
				openAddSupporterPage();
	}

}
