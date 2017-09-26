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

	/**
	 * <b>Create single custom field of specified type.</b>
	 * <p>
	 * Steps:
	 * <ul>
	 * <li>Login into existing organization
	 * <li>Open custom field creation page
	 * <li>Fill specific field type options on step 1
	 * <li>Fill name, description on step 2
	 * <li>Click creation button on step 3
	 * <li><font color="green"><b>Verify that custom field was created. Is
	 * listed on the page</b></font>
	 * <li>Remove custom field
	 * <li><font color="green"><b>Verify that custom field removed. It's not
	 * listed on the page</b></font>
	 * </ul>
	 * 
	 */
	@Test(enabled = false, groups = { "createAllSupporterCustomFields"  }, retryAnalyzer = RetryAnalyzer.class)
	@Parameters({ "login", "Passward" })
	public void createCustomField(String cfType , String login , String passward) {
		if (page == null) {
			page = doLoginAndOpenCustomFieldPage(login, passward );
		}
		CustomField cf = generateCustomField(cfType);
		page.createCustomField(cf).deleteCustomField(cf);
	}

	/**
	 * <b>Create single custom field of Text type.</b> Depends on
	 * {@link #initAllCustomFieldsCreationTest()
	 * initAllCustomFieldsCreationTest} method.
	 * <p>
	 * Steps:
	 * <ul>
	 * <li>Using Custom Field page, click on Create Custom Field button
	 * <li>Click on Text Box on step 1
	 * <li>Fill name, description on step 2
	 * <li>Fill ghost text on step 3
	 * <li>Click creation button
	 * <li><font color="green"><b>Verify that custom field was created. Is
	 * listed on the page</b></font>
	 * <li>Remove custom field
	 * <li><font color="green"><b>Verify that custom field removed. It's not
	 * listed on the page</b></font>
	 * </ul>
	 * 
	 */
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

	/**
	 * <b>Create single custom field of Number type.</b> Depends on
	 * {@link #initAllCustomFieldsCreationTest()
	 * initAllCustomFieldsCreationTest} method.
	 * <p>
	 * Steps:
	 * <ul>
	 * <li>Using Custom Field page, click on Create Custom Field button
	 * <li>Click on Number on step 1
	 * <li>Fill name, description on step 2
	 * <li>Fill ghost text on step 3
	 * <li>Click creation button
	 * <li><font color="green"><b>Verify that custom field was created. Is
	 * listed on the page</b></font>
	 * <li>Remove custom field
	 * <li><font color="green"><b>Verify that custom field removed. It's not
	 * listed on the page</b></font>
	 * </ul>
	 * 
	 */
	@Parameters({ "login", "Passward" })
	@Test(enabled = true, groups = { "createAllSupporterCustomFields" }, retryAnalyzer = RetryAnalyzer.class)
	public void createNumberCustomField(String login , String passward) {
		if (page == null) {
			page = doLoginAndOpenCustomFieldPage(login, passward );
		}

		CustomField cf = generateCustomField(CustomFieldType.Number);
		page.createCustomField(cf).deleteCustomField(cf);
	}

	/**
	 * <b>Create single custom field of Boolean (Yes/No) type.</b> Depends on
	 * {@link #initAllCustomFieldsCreationTest()
	 * initAllCustomFieldsCreationTest} method.
	 * <p>
	 * Steps:
	 * <ul>
	 * <li>Using Custom Field page, click on Create Custom Field button
	 * <li>Click on Yes/No on step 1
	 * <li>Fill name, description on step 2
	 * <li>Fill ghost text on step 3
	 * <li>Click creation button
	 * <li><font color="green"><b>Verify that custom field was created. Is
	 * listed on the page</b></font>
	 * <li>Remove custom field
	 * <li><font color="green"><b>Verify that custom field removed. It's not
	 * listed on the page</b></font>
	 * </ul>
	 * 
	 */
	@Parameters({ "login", "Passward" })
	@Test(enabled = true, groups = { "createAllSupporterCustomFields" }, retryAnalyzer = RetryAnalyzer.class)
	public void createYesNoCustomField(String login , String passward ) {
		if (page == null) {
			page = doLoginAndOpenCustomFieldPage(login, passward );
		}
		CustomField cf = generateCustomField(CustomFieldType.YesNo);
		page.createCustomField(cf).deleteCustomField(cf);
	}

	/**
	 * <b>Create single custom field of Date/Time type.</b> Depends on
	 * {@link #initAllCustomFieldsCreationTest()
	 * initAllCustomFieldsCreationTest} method.
	 * <p>
	 * Steps:
	 * <ul>
	 * <li>Using Custom Field page, click on Create Custom Field button
	 * <li>Click on Date/Time on step 1
	 * <li>Fill name, description on step 2
	 * <li>Fill ghost text on step 3
	 * <li>Click creation button
	 * <li><font color="green"><b>Verify that custom field was created. Is
	 * listed on the page</b></font>
	 * <li>Remove custom field
	 * <li><font color="green"><b>Verify that custom field removed. It's not
	 * listed on the page</b></font>
	 * </ul>
	 * 
	 */
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

	/**
	 * <b>Create single custom field of Single Choice type.</b> Depends on
	 * {@link #initAllCustomFieldsCreationTest()
	 * initAllCustomFieldsCreationTest} method.
	 * <p>
	 * Steps:
	 * <ul>
	 * <li>Using Custom Field page, click on Create Custom Field button
	 * <li>Click on Single Choice on step 1
	 * <li>Fill name, description on step 2
	 * <li>Fill ghost text on step 3
	 * <li>Click creation button
	 * <li><font color="green"><b>Verify that custom field was created. Is
	 * listed on the page</b></font>
	 * <li>Remove custom field
	 * <li><font color="green"><b>Verify that custom field removed. It's not
	 * listed on the page</b></font>
	 * </ul>
	 * 
	 */
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
