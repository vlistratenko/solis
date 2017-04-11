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
import com.salsalabs.ignite.automation.pages.hq.activities.Eventp2pWidget;

public class VerifyMergeFieldsInTheEmail extends SeleneseTestCase {
	
	LoginPage loginPage;
	
	@Parameters({ "sendEmail.from", "login", "segmentName"})
	@Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = { "email.verifyAllMergeFields" }, description = "")
	public void sendEmailBlastTest(String emailFrom, String login, String segmentName) {
		
		String emailBlastName = "Merge fields testing!!! Blast from " + CommonUtils.getTodayDate() + " " + CommonUtils.getRandomNumericValueFixedLength(5);
		String emailSubject = emailBlastName;
		//String host = new Environment("UAT", "LOCAL").getBaseTestUrl().replace("https://", "");
		CommonUtils.setProperty(PropertyName.EMAIL_FROM, emailFrom);
		CommonUtils.setProperty(PropertyName.EMAIL_BLAST_NAME, emailBlastName);
		CommonUtils.setProperty(PropertyName.EMAIL_SUBJECT, emailSubject);
				
		LoginPage loginPage = new LoginPage();
		loginPage.
		doSuccessLogin(login, "!QAZ2wsx").
		openMessagingPage().
		openEmailBlastsPage().
		openAddEmailPage().
		fillAllFieldsAndGoForward(emailBlastName).
		selectAudienceType("Selected segments of your list, or specific supporters").//(""Entire list ").
		addSegment(segmentName).
		openComposePage().
		selectLayout(1).
		AddAllMergeFields().
		fillAllFieldsAndGoForward(emailSubject, emailFrom, 1, "organizationforinternationalchange.uat.igniteaction.net/socialjusticeequality/index.html").
		fillAllFieldsAndPublish(100, 1).
		openDashboard().
		openMessagingPage().
		verifyActivityIsPresentInTableAllActivities("Email", emailBlastName).
		waitForStatus("COMPLETED", 6);
		
		Integer openAmount = CommonUtils.getRandomValueNumericFromTo(1, Integer.parseInt(CommonUtils.getProperty(PropertyName.AMOUNT_OF_PUBLISHED_EMAILS)));
		Integer clickAmount = CommonUtils.getRandomValueNumericFromTo(1, openAmount);
		Integer unsubAmount = CommonUtils.getRandomValueNumericFromTo(1, 10);
		Integer conversionAmount = CommonUtils.getRandomValueNumericFromTo(1, 10);
		Map<String, List<?>> emails = loginPage.openEmails(1, openAmount);		
		loginPage.clickLinkInEmail(emails, 1, "http://organizationforinternationalchange.uat.igniteaction.net/socialjusticeequality/index.html", clickAmount);		
		loginPage.unsubscribeByEmail(emails, 1, unsubAmount);
		
		///loginPage.clickLinkInEmailAndFillDonationForm(emails, emailSubject, "http://organizationforinternationalchange.uat.igniteaction.net/socialjusticeequality/index.html", conversionAmount, login, host);
	}
		
}
