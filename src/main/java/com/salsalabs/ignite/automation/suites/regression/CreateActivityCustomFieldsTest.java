package com.salsalabs.ignite.automation.suites.regression;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;
import com.salsalabs.ignite.automation.pages.hq.manage.CustomFieldsPage;
import com.salsalabs.ignite.automation.pages.hq.manage.CustomFieldsPage.CustomField;
import com.salsalabs.ignite.automation.pages.hq.manage.CustomFieldsPage.CustomFieldType;

public class CreateActivityCustomFieldsTest extends SeleneseTestCase {
	private CustomFieldsPage page;
	static final String singUp = "Sign-Up";
	static final String fundraising = "Fundraising Form";
	static final String petition = "Petition";
	static final String targettedAction = "Targeted Action";
	static final String event = "Event";
	static final String p2pEvent = "Peer-to-Peer Event";
	
	@Parameters({ "login", "Passward" })
	@Test(enabled = true, groups = { "CreateAllActivityCustomFields" }, retryAnalyzer = RetryAnalyzer.class)
	public void createAllCustomFieldTypeSignUP(String login , String passward) {
		if (page == null) {
			page = doLoginAndOpenCustomFieldPage(login , passward);
		}
		
		//this label  is used  to delete 5 created activity custom Fields with proper Type  
		String uniqueSignUpFormIdentifier = "ksing";
		CustomField textBox = generateCustomField(CustomFieldType.TextBox, uniqueSignUpFormIdentifier);
		CustomField dateTime = generateCustomField(CustomFieldType.DateTime , uniqueSignUpFormIdentifier);
		CustomField singleChoice = generateCustomField(CustomFieldType.SingleChoice, uniqueSignUpFormIdentifier);
		CustomField yesNO = generateCustomField(CustomFieldType.YesNo , uniqueSignUpFormIdentifier);
		CustomField number = generateCustomField(CustomFieldType.Number, uniqueSignUpFormIdentifier);
		page.
		openActivittiesCustomFieldsTab().
		selectActivityCustomField(singUp).
		createCustomField(textBox).
		selectActivityCustomField(singUp).
		createCustomField(dateTime).
		selectActivityCustomField(singUp).
		createCustomField(singleChoice).
		selectActivityCustomField(singUp).
		createCustomField(number).
		selectActivityCustomField(singUp).
		createCustomField(yesNO).
		deleteCustomField(uniqueSignUpFormIdentifier);
	}
	
	@Parameters({ "login", "Passward" })
	@Test(enabled = true, groups = { "CreateAllActivityCustomFields" }, retryAnalyzer = RetryAnalyzer.class)
	public void createAllCustomFieldTypeFund(String login , String passward) {
		if (page == null) {
			page = doLoginAndOpenCustomFieldPage(login , passward);
		}
		
		//this label  is used  to delete 5 created activity custom Fields with proper Type  
		String uniqueSignUpFormIdentifier = "kfund";
		CustomField textBox = generateCustomField(CustomFieldType.TextBox, uniqueSignUpFormIdentifier);
		CustomField dateTime = generateCustomField(CustomFieldType.DateTime , uniqueSignUpFormIdentifier);
		CustomField singleChoice = generateCustomField(CustomFieldType.SingleChoice, uniqueSignUpFormIdentifier);
		CustomField yesNO = generateCustomField(CustomFieldType.YesNo , uniqueSignUpFormIdentifier);
		CustomField number = generateCustomField(CustomFieldType.Number, uniqueSignUpFormIdentifier);
		page.
		openActivittiesCustomFieldsTab().
		selectActivityCustomField(fundraising).
		createCustomField(textBox).
		selectActivityCustomField(fundraising).
		createCustomField(dateTime).
		selectActivityCustomField(fundraising).
		createCustomField(singleChoice).
		selectActivityCustomField(fundraising).
		createCustomField(number).
		selectActivityCustomField(fundraising).
		createCustomField(yesNO).
		deleteCustomField(uniqueSignUpFormIdentifier);
	}
	
	@Parameters({ "login", "Passward" })
	@Test(enabled = true, groups = { "CreateAllActivityCustomFields" }, retryAnalyzer = RetryAnalyzer.class)
	public void createAllCustomFieldTypePetition(String login , String passward) {
		if (page == null) {
			page = doLoginAndOpenCustomFieldPage(login , passward);
		}
		
		//this label  is used  to delete 5 created activity custom Fields with proper Type  
		String uniqueSignUpFormIdentifier = "kpet";
		CustomField textBox = generateCustomField(CustomFieldType.TextBox, uniqueSignUpFormIdentifier);
		CustomField dateTime = generateCustomField(CustomFieldType.DateTime , uniqueSignUpFormIdentifier);
		CustomField singleChoice = generateCustomField(CustomFieldType.SingleChoice, uniqueSignUpFormIdentifier);
		CustomField yesNO = generateCustomField(CustomFieldType.YesNo , uniqueSignUpFormIdentifier);
		CustomField number = generateCustomField(CustomFieldType.Number, uniqueSignUpFormIdentifier);
		page.
		openActivittiesCustomFieldsTab().
		selectActivityCustomField(petition).
		createCustomField(textBox).
		selectActivityCustomField(petition).
		createCustomField(dateTime).
		selectActivityCustomField(petition).
		createCustomField(singleChoice).
		selectActivityCustomField(petition).
		createCustomField(number).
		selectActivityCustomField(petition).
		createCustomField(yesNO).
		deleteCustomField(uniqueSignUpFormIdentifier);
	}
	
	@Parameters({ "login", "Passward" })
	@Test(enabled = true, groups = { "CreateAllActivityCustomFields" }, retryAnalyzer = RetryAnalyzer.class)
	public void createAllCustomFieldTypeTA(String login , String passward) {
		if (page == null) {
			page = doLoginAndOpenCustomFieldPage(login , passward);
		}
		
		//this label  is used  to delete 5 created activity custom Fields with proper Type  
		String uniqueSignUpFormIdentifier = "ktact";
		CustomField textBox = generateCustomField(CustomFieldType.TextBox, uniqueSignUpFormIdentifier);
		CustomField dateTime = generateCustomField(CustomFieldType.DateTime , uniqueSignUpFormIdentifier);
		CustomField singleChoice = generateCustomField(CustomFieldType.SingleChoice, uniqueSignUpFormIdentifier);
		CustomField yesNO = generateCustomField(CustomFieldType.YesNo , uniqueSignUpFormIdentifier);
		CustomField number = generateCustomField(CustomFieldType.Number, uniqueSignUpFormIdentifier);
		page.
		openActivittiesCustomFieldsTab().
		selectActivityCustomField(targettedAction).
		createCustomField(textBox).
		selectActivityCustomField(targettedAction).
		createCustomField(dateTime).
		selectActivityCustomField(targettedAction).
		createCustomField(singleChoice).
		selectActivityCustomField(targettedAction).
		createCustomField(number).
		selectActivityCustomField(targettedAction).
		createCustomField(yesNO).
		deleteCustomField(uniqueSignUpFormIdentifier);
	}
	
	@Parameters({ "login", "Passward" })
	@Test(enabled = true, groups = { "CreateAllActivityCustomFields" }, retryAnalyzer = RetryAnalyzer.class)
	public void createAllCustomFieldTypeEvent(String login , String passward) {
		if (page == null) {
			page = doLoginAndOpenCustomFieldPage(login , passward);
		}
		
		//this label  is used  to delete 5 created activity custom Fields with proper Type  
		String uniqueSignUpFormIdentifier = "kevent";
		CustomField textBox = generateCustomField(CustomFieldType.TextBox, uniqueSignUpFormIdentifier);
		CustomField dateTime = generateCustomField(CustomFieldType.DateTime , uniqueSignUpFormIdentifier);
		CustomField singleChoice = generateCustomField(CustomFieldType.SingleChoice, uniqueSignUpFormIdentifier);
		CustomField yesNO = generateCustomField(CustomFieldType.YesNo , uniqueSignUpFormIdentifier);
		CustomField number = generateCustomField(CustomFieldType.Number, uniqueSignUpFormIdentifier);
		page.
		openActivittiesCustomFieldsTab().
		selectActivityCustomField(event).
		createCustomField(textBox).
		selectActivityCustomField(event).
		createCustomField(dateTime).
		selectActivityCustomField(event).
		createCustomField(singleChoice).
		selectActivityCustomField(event).
		createCustomField(number).
		selectActivityCustomField(event).
		createCustomField(yesNO).
		deleteCustomField(uniqueSignUpFormIdentifier);
	}
	
	
	@Parameters({ "login", "Passward" })
	@Test(enabled = true, groups = { "CreateAllActivityCustomFields" }, retryAnalyzer = RetryAnalyzer.class)
	public void createAllCustomFieldTypeP2p(String login , String passward) {
		if (page == null) {
			page = doLoginAndOpenCustomFieldPage(login , passward);
		}
		
		//this label  is used  to delete 5 created activity custom Fields with proper Type  
		String uniqueSignUpFormIdentifier = "kp2p";
		CustomField textBox = generateCustomField(CustomFieldType.TextBox, uniqueSignUpFormIdentifier);
		CustomField dateTime = generateCustomField(CustomFieldType.DateTime , uniqueSignUpFormIdentifier);
		CustomField singleChoice = generateCustomField(CustomFieldType.SingleChoice, uniqueSignUpFormIdentifier);
		CustomField yesNO = generateCustomField(CustomFieldType.YesNo , uniqueSignUpFormIdentifier);
		CustomField number = generateCustomField(CustomFieldType.Number, uniqueSignUpFormIdentifier);
		page.
		openActivittiesCustomFieldsTab().
		selectActivityCustomField(p2pEvent).
		createCustomField(textBox).
		selectActivityCustomField(p2pEvent).
		createCustomField(dateTime).
		selectActivityCustomField(p2pEvent).
		createCustomField(singleChoice).
		selectActivityCustomField(p2pEvent).
		createCustomField(number).
		selectActivityCustomField(p2pEvent).
		createCustomField(yesNO).
		deleteCustomField(uniqueSignUpFormIdentifier);
	}
	
	

	private CustomField generateCustomField(CustomFieldType customFieldType, String activityTypeUnickName) {
		return new CustomField(customFieldType, customFieldType.name() + "_" + CommonUtils.getUnicName() + " " + activityTypeUnickName);
	}

	private CustomFieldsPage doLoginAndOpenCustomFieldPage(String login , String passward) {
		return new LoginPage().doSuccessLogin(login ,passward ).openSettingsPage().switchToCustomFieldsPage();
	}

}
