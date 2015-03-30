package com.salsalabs.ignite.automation.suites;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.pages.hq.AlertsPage;
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
	private AlertsPage alertsPage;
	private List<CustomField> customFields = new ArrayList<CustomField>();
	private String importName;
	private String importStatusCompleted = "COMPLETED";
	
	public ImportCustomFieldsTest() {
		importName = "Import_" + CommonUtils.getUnicName();
		customFields.add(new CustomField(CustomFieldType.TextBox, "CustomField_" + RandomStringUtils.randomAlphabetic(3)));
		customFields.add(new CustomField(CustomFieldType.Number, "CustomField_" + RandomStringUtils.randomAlphabetic(3)));
	}

	@Test(groups = {"importWithCustomFields"}, retryAnalyzer = RetryAnalyzer.class)
	public void initImportWithCustomFieldsTest() {
		doLogin();
		createCustomFields();
		openImportsPage();
		importWithCustomFields();
		importPage.verifyStatusOfImport(importName, importStatusCompleted);
		openAlertsPage();
		alertsPage.checkImport(importName);
	}
	
	private void importWithCustomFields() {
		ImportAddPage importAddPage = importPage.startNewImport();
		importAddPage.fillFirstStep(importName, "description", customFields);
		importAddPage.fillSecondStep("2").fillThirdStep();
		importPage = importAddPage.openImportPage();
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
	
	private void openAlertsPage() {
		alertsPage = homePage.openAlertPopup().openAlertsPage();
	}

}