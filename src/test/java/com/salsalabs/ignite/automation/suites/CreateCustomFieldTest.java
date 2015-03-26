package com.salsalabs.ignite.automation.suites;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;
import com.salsalabs.ignite.automation.pages.hq.manage.CustomFieldsPage;

public class CreateCustomFieldTest extends SeleneseTestCase {

	@Test(groups = {"createCustomField"}, retryAnalyzer = RetryAnalyzer.class)
	@Parameters({"cf.customFieldType"})
	public void createCustomField(String customFieldType) {
		doLoginAndOpenCustomFieldPage().createCustomField(customFieldType);
	}
	
	@Test(groups = {"createAllCustomFields"}, retryAnalyzer = RetryAnalyzer.class)
	public void createAllCustomFieldsTest() {
		doLoginAndOpenCustomFieldPage().createCustomFields();
	}
	
	private CustomFieldsPage doLoginAndOpenCustomFieldPage() {
		return new LoginPage().
				doSuccessLogin().
				openSettingsPage().
				switchToCustomFieldsPage();
	}
}
