package com.salsalabs.ignite.automation.suites;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;

public class CreateCustomFieldTest extends SeleneseTestCase {

	@Test(groups = {"createCustomField"}, retryAnalyzer = RetryAnalyzer.class)
	@Parameters({"cf.customFieldType"})
	public void createCustomField(String customFieldType) {
		new LoginPage().
		doSuccessLogin().
		openSettingsPage().
		switchToCustomFieldsPage().
		createCustomField(customFieldType);
	}
}
