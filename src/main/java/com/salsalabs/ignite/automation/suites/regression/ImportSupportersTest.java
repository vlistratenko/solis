package com.salsalabs.ignite.automation.suites.regression;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.HttpClient;
import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;
import com.salsalabs.ignite.automation.pages.hq.manage.CustomFieldsPage;
import com.salsalabs.ignite.automation.pages.hq.manage.ImportAddPage;
import com.salsalabs.ignite.automation.pages.hq.manage.ImportPage;
import com.salsalabs.ignite.automation.pages.hq.manage.CustomFieldsPage.CustomField;
import com.salsalabs.ignite.automation.pages.hq.manage.CustomFieldsPage.CustomFieldType;

public class ImportSupportersTest  extends SeleneseTestCase{
	private CustomFieldsPage customFieldsPage;
	private ImportPage importPage;
	private ImportAddPage importAddpage = new ImportAddPage();
	private List<CustomField> customFields = new ArrayList<CustomField>();
	private String importName;
	private String importStatusCompleted = "COMPLETED";
	private final String customFiledType = "PERSON";
	private String customFieldTextName= "ImportTextField";
	private String customFieldSingleChoiceName= "ImportSChoiceField";
	private String customFieldSDatedName= "ImportDateField";
	private String customFieldBoleanName= "ImportBoleanField";
	private String customFieldNumberName= "ImportNumberField";
	
	@Parameters({"login" , "Passward" })
	@Test(enabled = true, groups = {"importWithCustomFields"}, retryAnalyzer = RetryAnalyzer.class)
	public void initImportWithCustomFieldsTest(String login, String passward) throws KeyManagementException, ClientProtocolException, NoSuchAlgorithmException, KeyStoreException, URISyntaxException, IOException, JSONException {
		customFieldsPage = new CustomFieldsPage();
		new HttpClient().
		login(login, passward)
	     .createCustomField(customFieldsPage.getTextBoxCustomFieldJson(customFiledType, customFieldTextName))
	     .createCustomField(customFieldsPage.getSingleChoiceCustomFieldJson(customFiledType,customFieldSingleChoiceName))
	     .createCustomField(customFieldsPage.getDateCustomFieldJson(customFiledType, customFieldSDatedName))
	     .createCustomField(customFieldsPage.getBoleanCustomFieldJson(customFiledType, customFieldBoleanName))
	     .createCustomField(customFieldsPage.getNumberCustomFieldJson(customFiledType, customFieldNumberName));
		
			customFields.add(generateCustomField(CustomFieldType.TextBox, customFieldTextName));
			customFields.add(generateCustomField(CustomFieldType.DateTime , customFieldSDatedName));
			customFields.add(generateCustomField(CustomFieldType.SingleChoice , customFieldSingleChoiceName));
			customFields.add(generateCustomField(CustomFieldType.YesNo , customFieldBoleanName));
			customFields.add(generateCustomField(CustomFieldType.Number , customFieldNumberName));
		
		importName = "Import_" + CommonUtils.getUnicName();
		openImportsPage(login, passward );
		importPage.hitImportListOFSupportersButton().
		fillFirstStep(importName, "ImportDescription" , customFields).
		fillSecondStep("2").
		fillThirdStep();
		importPage.verifyStatusOfImport(importName, importStatusCompleted);
	
	}
	
	private CustomField generateCustomField(CustomFieldType customFieldType, String customFieldName) {
		return new CustomField(customFieldType, customFieldName);
	}

	private void openImportsPage(String userName, String password) {
		importPage = new LoginPage().doSuccessLogin(userName, password).openAudiencePage().openImportPage();
	}
	

}
