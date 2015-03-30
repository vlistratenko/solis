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
	 * <li> Fill specific field type options on step 1
	 * <li> Fill name, description on step 2
	 * <li> Click creation button on step 3
	 * <li> <font color="green"><b>Verify that custom field was created. Is listed on the page</b></font>
	 * </ul>
	 *  
	 */
	@Test(groups = {"createCustomField"}, retryAnalyzer = RetryAnalyzer.class)
	@Parameters({"cf.customFieldType"})
	public void createCustomField(String customFieldType) {
		doLoginAndOpenCustomFieldPage().createCustomField(customFieldType);
	}
	
	/**
	 * <b>Initializing of custom fields page</b>
	 * <p>
	 * Steps:
	 * <ul>
	 * <li> Login into existing organization
	 * <li> Open custom field creation page
	 * </ul>
	 *  
	 */
	@Test(groups = {"createAllCustomFields"}, retryAnalyzer = RetryAnalyzer.class)
	public void initAllCustomFieldsCreationTest() {
		if (page == null) {
			page = doLoginAndOpenCustomFieldPage();
		}
	}
	
	/**
	 * <b>Create single custom field of Text type.</b>
	 * Depends on {@link #initAllCustomFieldsCreationTest() initAllCustomFieldsCreationTest} method.
	 * <p>
	 * Steps:
	 * <ul>
	 * <li> Using Custom Field page, click on Create Custom Field button
	 * <li> Click on Text Box on step 1
	 * <li> Fill name, description on step 2
	 * <li> Fill ghost text on step 3
	 * <li> Click creation button
	 * <li> <font color="green"><b>Verify that custom field was created. Is listed on the page</b></font>
	 * </ul>
	 *  
	 */
	@Test(groups = {"createAllCustomFields"}, dependsOnMethods = {"initAllCustomFieldsCreationTest"}, retryAnalyzer = RetryAnalyzer.class)
	public void createTextBoxCustomField() {
		page.createCustomField(CustomFieldType.TextBox);
	}
	
	/**
	 * <b>Create single custom field of Number type.</b>
	 * Depends on {@link #initAllCustomFieldsCreationTest() initAllCustomFieldsCreationTest} method.
	 * <p>
	 * Steps:
	 * <ul>
	 * <li> Using Custom Field page, click on Create Custom Field button
	 * <li> Click on Number on step 1
	 * <li> Fill name, description on step 2
	 * <li> Fill ghost text on step 3
	 * <li> Click creation button
	 * <li> <font color="green"><b>Verify that custom field was created. Is listed on the page</b></font>
	 * </ul>
	 *  
	 */
	@Test(groups = {"createAllCustomFields"}, dependsOnMethods = {"initAllCustomFieldsCreationTest"}, retryAnalyzer = RetryAnalyzer.class)
	public void createNumberCustomField() {
		page.createCustomField(CustomFieldType.Number);
	}
	
	/**
	 * <b>Create single custom field of Boolean (Yes/No) type.</b>
	 * Depends on {@link #initAllCustomFieldsCreationTest() initAllCustomFieldsCreationTest} method.
	 * <p>
	 * Steps:
	 * <ul>
	 * <li> Using Custom Field page, click on Create Custom Field button
	 * <li> Click on Yes/No on step 1
	 * <li> Fill name, description on step 2
	 * <li> Fill ghost text on step 3
	 * <li> Click creation button
	 * <li> <font color="green"><b>Verify that custom field was created. Is listed on the page</b></font>
	 * </ul>
	 *  
	 */
	@Test(groups = {"createAllCustomFields"}, dependsOnMethods = {"initAllCustomFieldsCreationTest"}, retryAnalyzer = RetryAnalyzer.class)
	public void createYesNoCustomField() {
		page.createCustomField(CustomFieldType.YesNo);
	}
	
	/**
	 * <b>Create single custom field of Date/Time type.</b>
	 * Depends on {@link #initAllCustomFieldsCreationTest() initAllCustomFieldsCreationTest} method.
	 * <p>
	 * Steps:
	 * <ul>
	 * <li> Using Custom Field page, click on Create Custom Field button
	 * <li> Click on Date/Time on step 1
	 * <li> Fill name, description on step 2
	 * <li> Fill ghost text on step 3
	 * <li> Click creation button
	 * <li> <font color="green"><b>Verify that custom field was created. Is listed on the page</b></font>
	 * </ul>
	 *  
	 */
	@Test(groups = {"createAllCustomFields"}, dependsOnMethods = {"initAllCustomFieldsCreationTest"}, retryAnalyzer = RetryAnalyzer.class)
	public void createDateTimeCustomField() {
		page.createCustomField(CustomFieldType.DateTime);
	}
	
	/**
	 * <b>Create single custom field of Single Choice type.</b>
	 * Depends on {@link #initAllCustomFieldsCreationTest() initAllCustomFieldsCreationTest} method.
	 * <p>
	 * Steps:
	 * <ul>
	 * <li> Using Custom Field page, click on Create Custom Field button
	 * <li> Click on Single Choice on step 1
	 * <li> Fill name, description on step 2
	 * <li> Fill ghost text on step 3
	 * <li> Click creation button
	 * <li> <font color="green"><b>Verify that custom field was created. Is listed on the page</b></font>
	 * </ul>
	 *  
	 */
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
