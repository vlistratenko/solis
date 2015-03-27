package com.salsalabs.ignite.automation.suites;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;
import com.salsalabs.ignite.automation.pages.hq.manage.CustomFieldsPage;
import com.salsalabs.ignite.automation.pages.hq.manage.CustomFieldsPage.CustomFieldType;

/**
 * <b>This test contains scenarios related to custom fields creation (TestLink: TC8)</b>
 *
 */
public class CreateCustomFieldTest extends SeleneseTestCase {
	private CustomFieldsPage page;

	/**
	 * <b>Create single custom field of specified type.</b>
	 * <p>
	 * Steps:
	 * <ul>
	 * <li> Login into existing organization
	 * <li> Open custom field creation page
	 * <li> Fill name, description
	 * <li> Fill specific field type options
	 * <li> Click creation button
	 * <li> <font color="green"><b>Verify that custom field was created. Is listed on the page</b></font>
	 * </ul>
	 *  
	 */
	@Test(groups = {"createCustomField"}, retryAnalyzer = RetryAnalyzer.class)
	@Parameters({"cf.customFieldType"})
	public void createCustomField(String customFieldType) {
		doLoginAndOpenCustomFieldPage().createCustomField(customFieldType);
	}
	
	@Test(groups = {"createAllCustomFields"}, retryAnalyzer = RetryAnalyzer.class)
	public void initAllCustomFieldsCreationTest() {
		if (page == null) {
			page = doLoginAndOpenCustomFieldPage();
		}
	}
	
	@Test(groups = {"createAllCustomFields"}, dependsOnMethods = {"initAllCustomFieldsCreationTest"}, retryAnalyzer = RetryAnalyzer.class)
	public void createTextBoxCustomField() {
		page.createCustomField(CustomFieldType.TextBox);
	}
	
	@Test(groups = {"createAllCustomFields"}, dependsOnMethods = {"initAllCustomFieldsCreationTest"}, retryAnalyzer = RetryAnalyzer.class)
	public void createNumberCustomField() {
		page.createCustomField(CustomFieldType.Number);
	}
	
	@Test(groups = {"createAllCustomFields"}, dependsOnMethods = {"initAllCustomFieldsCreationTest"}, retryAnalyzer = RetryAnalyzer.class)
	public void createYesNoCustomField() {
		page.createCustomField(CustomFieldType.YesNo);
	}
	
	@Test(groups = {"createAllCustomFields"}, dependsOnMethods = {"initAllCustomFieldsCreationTest"}, retryAnalyzer = RetryAnalyzer.class)
	public void createDateTimeCustomField() {
		page.createCustomField(CustomFieldType.DateTime);
	}
	
	@Test(groups = {"createAllCustomFields"}, dependsOnMethods = {"initAllCustomFieldsCreationTest"}, retryAnalyzer = RetryAnalyzer.class)
	public void createSingleChoiceCustomField() {
		page.createCustomField(CustomFieldType.SingleChoice);
	}
	
	private CustomFieldsPage doLoginAndOpenCustomFieldPage() {
		return new LoginPage().
				doSuccessLogin().
				openSettingsPage().
				switchToCustomFieldsPage();
	}
}
