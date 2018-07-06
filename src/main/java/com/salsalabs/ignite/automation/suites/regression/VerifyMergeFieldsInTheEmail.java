package com.salsalabs.ignite.automation.suites.regression;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.Environment;
import com.salsalabs.ignite.automation.common.PropertyName;
import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.common.Supporter;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;
import com.salsalabs.ignite.automation.pages.hq.activities.EventWidget;
import com.salsalabs.ignite.automation.pages.p2p.Eventp2pWidget;

public class VerifyMergeFieldsInTheEmail extends SeleneseTestCase {
	//list of customfields in the email (37 in total)
	public String mergeFieldZipCode = "";
	public String mergeFieldTypeOfMostRecentDonation = "";
	public String mergeFieldTotalPetitionSignatures = "";
	public String mergeFieldTitle = "";
	public String mergeFieldSupporterSince = "";
	public String mergeFieldSuffix = "";
	public String mergeFieldSubscriptionDate = "";
	public String mergeFieldSubscriptionChangeDate = "";
	public String mergeFieldStateSenateDistrict = "";
	public String mergeFieldStateHouseDistrict = "";
	public String mergeFieldState = "";
	public String mergeFieldOrganizationName = "";
	public String mergeFieldOrgZip = "";
	public String mergeFieldOrgState = "";
	public String mergeFieldOrgPhone = "";
	public String mergeFieldOrgEmail = "";
	public String mergeFieldOrgCity = "";
	public String mergeFieldOrgAddressLine2 = "";
	public String mergeFieldOrgAddressLine1 = "";
	public String mergeFieldNumber = "";
	public String mergeFieldMiddleName = "";
	public String mergeFieldLifetimeNumberofDonations = "";
	public String mergeFieldLifetimeGivingAmount = "";
	public String mergeFieldLastPetitionSignatureDate = "";
	public String mergeFieldLastName = "";
	public String mergeFieldLargestDonationAmount = "";
	public String mergeFieldFirstPetitionSignatureDate = "";
	public String mergeFieldFirstName = "";
	public String mergeFieldFederalHouseDistrict = "";
	public String mergeFieldEmailAddress = "";
	public String mergeFieldDateOfMostRecentDonation = "";
	public String mergeFieldCity = "";
	public String mergeFieldCellPhone = "";
	public String mergeFieldAverageDonationAmount = "";
	public String mergeFieldAmountOfMostRecentDonation = "";
	public String mergeFieldAddressline2 = "";
	public String mergeFieldAddressline1 = "";
	
	
	LoginPage loginPage;
	
	@Parameters({ "sendEmail.from", "login", "segmentName"})
	@Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = { "email.verifyAllMergeFields" }, description = "")
	public void VerifyMergeFieldsInTheEmailTest(String emailFrom, String login, String segmentName) throws KeyManagementException, ClientProtocolException, NoSuchAlgorithmException, KeyStoreException, JSONException, URISyntaxException, IOException {
		
		String emailBlastName = "Merge fields testing!!! Blast from " + CommonUtils.getTodayDate() + " " + CommonUtils.getRandomNumericValueFixedLength(5);
		String emailSubject = emailBlastName;
		//String host = new Environment("UAT", "LOCAL").getBaseTestUrl().replace("https://", "");
		CommonUtils.setProperty(PropertyName.EMAIL_FROM, emailFrom);
		CommonUtils.setProperty(PropertyName.EMAIL_BLAST_NAME, emailBlastName);
		CommonUtils.setProperty(PropertyName.EMAIL_SUBJECT, emailSubject);
		CommonUtils.setProperty(PropertyName.SUPPORTER_EMAIL, SeleneseTestCase.emailClient.getEmailBox("supporterformergefieldstesting"));
		
		String[] supporterFieldsForVerificationInSubj = {"Title", "Zip Code", "City", "supporterDateTimeCustomField", "supporterSingleChoiceCustomField", "supporterTextBoxCustomField"};	
		LoginPage loginPage = new LoginPage();
		loginPage.
		doSuccessLogin(login, "qwerty").
		openMessagingPage().
		openEmailBlastsPage().
		openAddEmailPage().
		fillAllFieldsAndGoForward(emailBlastName).
		selectAudienceType("Selected segments of your list, or specific supporters").
		addSupporters(CommonUtils.getProperty(PropertyName.SUPPORTER_EMAIL), 1, PropertyName.AMOUNT_OF_SUPPORTERS).//(""Entire list ").
		//addSegment(segmentName).
		openComposePage().
		selectLayout("Basic").
		addLink("").
		AddAllMergeFieldsIntoSubject(supporterFieldsForVerificationInSubj).
		AddAllMergeFieldsIntoBody().
		fillAllFieldsAndGoForward("", emailFrom, 1).
		fillAllFieldsAndPublish(100, 1).
		openDashboard().
		openMessagingPage().
		verifyActivityIsPresentInTableAllActivities("Email", emailBlastName).
		waitForStatus("COMPLETED", 12).
		verifyMergeFieldsInTheEmailBody().
		verifyMergeFieldsInTheEmailSubject(supporterFieldsForVerificationInSubj);
		
		CommonUtils.checkAndFail("VerifyMergeFieldsInTheEmailTest");
		
		//
		
	}
		
}
