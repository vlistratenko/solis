package com.salsalabs.ignite.automation.suites.old;

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
import com.salsalabs.ignite.automation.tests.old.ActivitiesTests;
import com.salsalabs.ignite.automation.tests.old.AdminTest;
import com.salsalabs.ignite.automation.tests.old.EmailBlastTest;
import com.salsalabs.ignite.automation.tests.old.SettingsTests;

public class SubscribeAndUnsubscribe_Suite extends SeleneseTestCase{
/*
	@Test(retryAnalyzer=RetryAnalyzer.class, enabled = true, groups = {"createAdmin"}, description = "484:51:New org was NOT created")
	@Parameters({"createOrg.domainType",
		"createOrg.orgName",
		"createOrg.orgDescrption",
		"createOrg.firstName",
		"createOrg.lastName",		
		"createOrg.status",
		"createOrg.featureList" })
	public void createOrgTest(	     	
		     	String domainType,
				String orgName,
				String orgDescrption,
				String firstName,
				String lastName,		
				String status,
				String featureList){
		
		new AdminTest().createOrgTest(domainType, orgName, orgDescrption, firstName, lastName, status, featureList);	
	}
	
	@Test(retryAnalyzer=RetryAnalyzer.class, enabled = true, groups = {"createAdmin"}, dependsOnMethods="createOrgTest", description = "489:52:New Admin account was NOT confirmed")
	@Parameters({"newuser.password"})
	public void confirmAdminAccountTest(String userPassword){
		new AdminTest().confirmAdminAccountTest(userPassword);
	}
	
	@Test(retryAnalyzer=RetryAnalyzer.class, priority=30, groups = {"createAdmin"}, description = "489:52:New admin can NOT login", dependsOnMethods="confirmAdminAccountTest")
	public void loginAsNewSuperAdminTest(){
		new AdminTest().loginAsNewSuperAdminTest();
	}

	@Parameters({"createwidget.widgetName", "createwidget.widgetDescription"})
	@Test(retryAnalyzer=RetryAnalyzer.class,  priority=40, groups = {"activities.createSubscribeFormAndSubscribeSupporter"}, description = "", dependsOnMethods="loginAsNewSuperAdminTest")
	public void createSubscribeFormAndSubscribeSupporter(String widgetName, String widgetDescription){
		new ActivitiesTests().createSubscribeWidget(widgetName, widgetDescription, "Basic");
		new ActivitiesTests().subscribeSupporterTest("");
	}
	
	@Test(retryAnalyzer=RetryAnalyzer.class,  priority=50, groups = {"activities.subscribeExistedSupporter"}, description = "", dependsOnMethods="loginAsNewSuperAdminTest")
	public void subscribeExistedSupporter() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, JSONException{
		Supporter supporter = new Supporter();
		supporter.setFinalEMAIL(emailClient.getEmailBox("emailforsub" + CommonUtils.getUnicName()));
		
		//Create new supporter
		new HttpClient().login().createSupporter(supporter.getSupporterJSON(supporter.getFinalEMAIL()));
		
		new ActivitiesTests().subscribeSupporterTest(supporter.getFinalEMAIL());
	}	
	
	@Test(retryAnalyzer=RetryAnalyzer.class,  priority=10, groups = {"settings.unsubscribeSupporter", ""}, description = "", dependsOnMethods="loginAsNewSuperAdminTest")
	public void unsubscribeSupporterTest() throws KeyManagementException, ClientProtocolException, NoSuchAlgorithmException, KeyStoreException, URISyntaxException, IOException, JSONException {
		new SettingsTests().unsubscribeSupporterTest();
	}
	
	@Test(retryAnalyzer=RetryAnalyzer.class,  priority=10, groups = {"settings.unsubscribeSupporter", ""}, description = "", dependsOnMethods="loginAsNewSuperAdminTest")
	public void unsubscribeUnexistedSupporterTest() {
		new SettingsTests().unsubscribeUnexistedSupporterTest();
	}
	
	@Test(retryAnalyzer=RetryAnalyzer.class,  priority=10, groups = {"settings.unsubscribeSupporterViaEMAIL", ""}, description = "", dependsOnMethods="loginAsNewSuperAdminTest")
	public void unsubscribeSupporterByEmailTest() throws KeyManagementException, ClientProtocolException, NoSuchAlgorithmException, KeyStoreException, URISyntaxException, IOException, JSONException {

		new SettingsTests().unsubscribeSupporterByEmailTest();
	}
	
	@Test(retryAnalyzer=RetryAnalyzer.class,  priority=60, groups = {"email.sendEmailsToUnsubscribedSupporters"}, description = "", dependsOnMethods="unsubscribeSupporterTest")
	public void sendEmailsToUnsubscribedSupporters() throws KeyManagementException, ClientProtocolException, NoSuchAlgorithmException, KeyStoreException, URISyntaxException, IOException, JSONException{
		//new EmailBlastTest().sendEmailsToUnsubscribedSupporters();

	}			
	
	@Test(retryAnalyzer=RetryAnalyzer.class,  priority=30, groups = {"activities.subscribeUnsubscribedSupporter"}, description = "", dependsOnMethods="unsubscribeSupporterTest")
	public void subscribeUnsubscribedSupporter(){
		new ActivitiesTests().subscribeSupporterTest(CommonUtils.getProperty(PropertyName.UNSUBSCRIBED_SUPPORTER));
	}*/
}
