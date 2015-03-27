package com.salsalabs.ignite.automation.suites;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.pages.hq.HomePage;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;
import com.salsalabs.ignite.automation.pages.hq.manage.CustomFieldsPage;
import com.salsalabs.ignite.automation.pages.hq.manage.CustomFieldsPage.CustomField;
import com.salsalabs.ignite.automation.pages.hq.manage.CustomFieldsPage.CustomFieldType;
import com.salsalabs.ignite.automation.pages.hq.manage.ImportAddPage;
import com.salsalabs.ignite.automation.pages.hq.manage.ImportPage;

public class ImportCustomFieldsTest extends SeleneseTestCase {
	private HomePage homePage;
	private CustomFieldsPage customFieldsPage;
	private ImportPage importPage;
	private List<CustomField> customFields = new ArrayList<CustomField>();
	
	public ImportCustomFieldsTest() {
		customFields.add(new CustomField(CustomFieldType.TextBox, "CustomField_" + CommonUtils.getUnicName()));
		customFields.add(new CustomField(CustomFieldType.Number, "CustomField_" + CommonUtils.getUnicName()));
	}

	@Test(groups = {"importWithCustomFields"}, retryAnalyzer = RetryAnalyzer.class)
	public void initImportWithCustomFieldsTest() {
		doLogin();
		createCustomFields();
		openImportsPage();
		importWithCustomFields();
	}
	
	private void importWithCustomFields() {
		ImportAddPage importAddPage = importPage.startNewImport();
		importAddPage.fillFirstStep("Import_" + CommonUtils.getUnicName(), "description", customFields);
		importAddPage.fillSecondStep("2").fillThirdStep();
	}
	
	private void doLogin() {
		homePage = new LoginPage().doSuccessLogin();
	}
	
	private void createCustomFields() {
		customFieldsPage = homePage.openSettingsPage().switchToCustomFieldsPage();
		for (CustomField cf : customFields) {
			customFieldsPage.createCustomField(cf);
		}
	}
	
	private void openImportsPage() {
		importPage = customFieldsPage.openAudiencePage().openImportPage();
	}

}