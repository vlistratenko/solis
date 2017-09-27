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

public class CreateSupporterCustomFieldTest extends SeleneseTestCase {
	private CustomFieldsPage page;


	@Test(enabled = false, groups = { "createAllSupporterCustomFields"  }, retryAnalyzer = RetryAnalyzer.class)
	@Parameters({ "login", "Passward" })
	public void createCustomField(String cfType , String login , String passward) {
		if (page == null) {
			page = doLoginAndOpenCustomFieldPage(login, passward );
		}
		CustomField cf = generateCustomField(cfType);
		page.createCustomField(cf).deleteCustomField(cf);
	}


	@Parameters({ "login", "Passward" })
	@Test(enabled = true, groups = { "createAllSupporterCustomFields" }, retryAnalyzer = RetryAnalyzer.class)
	public void createTextBoxCustomField(String login , String passward) {
		if (page == null) {
			page = doLoginAndOpenCustomFieldPage(login , passward);
		}
		CustomField cf = generateCustomField(CustomFieldType.TextBox);
		page.
		createCustomField(cf).
		deleteCustomField(cf);
	}


	@Parameters({ "login", "Passward" })
	@Test(enabled = true, groups = { "createAllSupporterCustomFields" }, retryAnalyzer = RetryAnalyzer.class)
	public void createNumberCustomField(String login , String passward) {
		if (page == null) {
			page = doLoginAndOpenCustomFieldPage(login, passward );
		}

		CustomField cf = generateCustomField(CustomFieldType.Number);
		page.createCustomField(cf).deleteCustomField(cf);
	}

	@Parameters({ "login", "Passward" })
	@Test(enabled = true, groups = { "createAllSupporterCustomFields" }, retryAnalyzer = RetryAnalyzer.class)
	public void createYesNoCustomField(String login , String passward ) {
		if (page == null) {
			page = doLoginAndOpenCustomFieldPage(login, passward );
		}
		CustomField cf = generateCustomField(CustomFieldType.YesNo);
		page.createCustomField(cf).deleteCustomField(cf);
	}


	@Parameters({ "login", "Passward" })
	@Test(enabled = true, groups = { "createAllSupporterCustomFields" }, retryAnalyzer = RetryAnalyzer.class)
	public void createDateTimeCustomField(String login , String passward ) {
		if (page == null) {
			page = doLoginAndOpenCustomFieldPage(login, passward);
		}
		CustomField cf = generateCustomField(CustomFieldType.DateTime);
		page.
		createCustomField(cf).
		deleteCustomField(cf);
	}


	@Parameters({ "login", "Passward" })
	@Test(enabled = true, groups = { "createAllSupporterCustomFields" }, retryAnalyzer = RetryAnalyzer.class)
	public void createSingleChoiceCustomField(String login , String passward ) {
		if (page == null) {
			page = doLoginAndOpenCustomFieldPage(login , passward);
		}
		CustomField cf = generateCustomField(CustomFieldType.SingleChoice);
		page.
		createCustomField(cf).
		deleteCustomField(cf);
	}
	
	@Parameters({ "login", "Passward" })
	@Test(enabled = true, groups = { "createAllSupporterCustomFields" }, retryAnalyzer = RetryAnalyzer.class)
	public void supporterCustomFieldsValidationTest(String login , String passward ) {
		if (page == null) {
			page = doLoginAndOpenCustomFieldPage(login , passward);
		}
		CustomField textBox = generateCustomField(CustomFieldType.TextBox);
		CustomField dateTime = generateCustomField(CustomFieldType.DateTime);
		CustomField singleChoice = generateCustomField(CustomFieldType.SingleChoice);
		CustomField yesNO = generateCustomField(CustomFieldType.YesNo);
		page.
		proceedToTheStep3(textBox).
		validateTextBox(textBox).
		proceedToTheStep3(dateTime).
		validateDateTimeField(dateTime).
		proceedToTheStep3(yesNO).
		validateYesNo(yesNO).
		proceedToTheStep3(singleChoice).
		validateSingleChoice(singleChoice);
		
	}

	private CustomField generateCustomField(String customFieldType) {
		return generateCustomField(CustomFieldType.valueOf(customFieldType));
	}

	private CustomField generateCustomField(CustomFieldType customFieldType) {
		return new CustomField(customFieldType, customFieldType.name() + "_" + CommonUtils.getUnicName());
	}

	private CustomFieldsPage doLoginAndOpenCustomFieldPage(String login , String passward) {
		return new LoginPage().doSuccessLogin(login ,passward ).openSettingsPage().switchToCustomFieldsPage();
	}

}
