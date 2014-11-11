package suites;

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
import selenium.CommonUtils;
import selenium.EmailClient;
import selenium.HttpClient;
import selenium.SeleneseTestCase;
import tests.ActivitiesTests;
import tests.AdminTest;
import tests.EmailBlastTest;
import tests.SettingsTests;

public class SubscribeAndUnsubscribe_Suite extends SeleneseTestCase{

	@Test(enabled = true, groups = {"createAdmin"}, description = "484:51:New org was NOT created")
	@Parameters({ "admin.login",
     	"admin.password",     	
     	"createOrg.domainType",
		"createOrg.orgName",
		"createOrg.orgDescrption",
		"createOrg.firstName",
		"createOrg.lastName",		
		"createOrg.status",
		"createOrg.featureList" })
	public void createOrgTest(String login,
		     	String password,	     	
		     	String domainType,
				String orgName,
				String orgDescrption,
				String firstName,
				String lastName,		
				String status,
				String featureList){
		
		new AdminTest().createOrgTest(login, password, domainType, orgName, orgDescrption, firstName, lastName, status, featureList);	
	}
	
	@Test(enabled = true, groups = {"createAdmin"}, dependsOnMethods="createOrgTest", description = "489:52:New Admin account was NOT confirmed")
	@Parameters({ "email.login",
     	"email.password",
     	"newuser.password"})
	public void confirmAdminAccountTest(String login,
	     	String password,	     	
			String userPassword){
		new AdminTest().confirmAdminAccountTest(login, password, userPassword);
	}
	
	@Test(priority=30, groups = {"createAdmin"}, description = "489:52:New admin can NOT login", dependsOnMethods="confirmAdminAccountTest")
	public void loginAsNewSuperAdminTest(){
		new AdminTest().loginAsNewSuperAdminTest();
	}

	@Parameters({"createwidget.widgetName", "createwidget.widgetDescription"})
	@Test( priority=40, groups = {"activities.createSubscribeFormAndSubscribeSupporter"}, description = "", dependsOnMethods="loginAsNewSuperAdminTest")
	public void createSubscribeFormAndSubscribeSupporter(String widgetName, String widgetDescription){
		new ActivitiesTests().createSubscribeWidget(widgetName, widgetDescription);
		new ActivitiesTests().subscribeSupporterTest("");
	}
	
/*	@Test( priority=30, groups = {"activities.subscribeByLinkInEmail"}, description = "", dependsOnMethods="createSubscribeFormAndSubscribeSupporter")
	public void subscribeByLinkInEmail(){
		new ActivitiesTests().subscribeSupporterTest();
	}
	*/
	
	@Test( priority=50, groups = {"activities.subscribeExistedSupporter"}, description = "", dependsOnMethods="loginAsNewSuperAdminTest")
	public void subscribeExistedSupporter() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, JSONException{
		Supporter supporter = new Supporter();
		supporter.finalEMAIL = EmailClient.getEmailBox("emailforsub" + CommonUtils.getUnicName());
		
		//Create new supporter
		new HttpClient().login().createSupporter(supporter.getSupporterJSON(supporter.finalEMAIL));
		
		new ActivitiesTests().subscribeSupporterTest(supporter.finalEMAIL);
	}	
	
	@Test( priority=10, groups = {"settings.unsubscribeSupporter", ""}, description = "", dependsOnMethods="loginAsNewSuperAdminTest")
	public void unsubscribeSupporterTest() throws KeyManagementException, ClientProtocolException, NoSuchAlgorithmException, KeyStoreException, URISyntaxException, IOException, JSONException {
		new SettingsTests().unsubscribeSupporterTest();
	}
	
	@Test( priority=10, groups = {"settings.unsubscribeSupporterViaEMAIL", ""}, description = "", dependsOnMethods="loginAsNewSuperAdminTest")
	public void unsubscribeSupporterByEmailTest() throws KeyManagementException, ClientProtocolException, NoSuchAlgorithmException, KeyStoreException, URISyntaxException, IOException, JSONException {

		new SettingsTests().unsubscribeSupporterByEmailTest();
	}
	
	@Test( priority=60, groups = {"email.sendEmailsToUnsubscribedSupporters"}, description = "", dependsOnMethods="unsubscribeSupporterTest")
	public void sendEmailsToUnsubscribedSupporters() throws KeyManagementException, ClientProtocolException, NoSuchAlgorithmException, KeyStoreException, URISyntaxException, IOException, JSONException{
		new EmailBlastTest().sendEmailsToUnsubscribedSupporters();
	}			
	
	@Test( priority=30, groups = {"activities.subscribeUnsubscribedSupporter"}, description = "", dependsOnMethods="unsubscribeSupporterTest")
	public void subscribeUnsubscribedSupporter(){
		new ActivitiesTests().subscribeSupporterTest(CommonUtils.getProperty("unsubscribedSupporter"));
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/*	@DataProvider(name = "getLinkFromEmail")
	public Object[][] getLinkFromEmail() {
	 
		new ActivitiesTests().
		
		return new Object[][] {
	   { "Cedric"}
	 };
	}*/

	
}
