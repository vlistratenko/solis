package com.salsalabs.ignite.automation.suites;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.HttpClient;
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

/**
 * <b>This test contains scenarios related to custom fields import (TestLink: TC9)</b>
 * and <b>scenarios to import supporters without custom fields mapping (TestLink: TC7)</b>
 * @author a.hubachov
 */
public class ImportSupportersTest extends SeleneseTestCase {
	private HomePage homePage;
	private CustomFieldsPage customFieldsPage;
	private ImportPage importPage;
	private AlertsPage alertsPage;
	private List<CustomField> customFields = new ArrayList<CustomField>();
	private String importName;
	private String importStatusCompleted = "COMPLETED";
	private final String customFiledType = "PERSON";
	/**
	 * <b>Import of supporters with 2 custom fields (Text and Numeric).</b>
	 * <p>
	 * Steps:
	 * <ul>
	 * <li> Login into existing organization
	 * <li> Open custom field creation page
	 * <li> Create 2 new custom fields (Text and Numeric)
	 * <li> Open imports page (Audience --> Manage Imports)
	 * <li> Click on Import Supporters List button
	 * <li> Fill import name, description, choose generated file with 2 custom fields
	 * <li> Click on Next button
	 * <li> Choose that data starts on row #2
	 * <li> Map fields including new custom fields
	 * <li> Click on Next button
	 * <li> Wait for completion
	 * <li> Go to import's page
	 * <li> <font color="green"><b>Verify that table contains record about current import completed</b></font>
	 * <li> Open alerts page
	 * <li> <font color="green"><b>Verify that alerts table contains record about current import started</b></font>
	 * <li> <font color="green"><b>Verify that alerts table contains record about current import finished</b></font>
	 * <li> Open custom fields page
	 * <li> Remove each new custom field
	 * <li> <font color="green"><b>Verify that custom field removed. It's not listed on the page</b></font>
	 * </ul>
	 * @throws JSONException 
	 * @throws IOException 
	 * @throws URISyntaxException 
	 * @throws KeyStoreException 
	 * @throws NoSuchAlgorithmException 
	 * @throws ClientProtocolException 
	 * @throws KeyManagementException 
	 *  
	 */
	@Parameters({"login" , "Passward" })
	@Test(enabled = true, groups = {"importWithCustomFields"}, retryAnalyzer = RetryAnalyzer.class)
	 
	
	public void initImportWithCustomFieldsTest(String login, String Passward, String texBoxCustomFieldName, String singleChoiceCustomFieldName, String dateCustomFieldName , String yesNoCustomFieldName, String numberCustomFieldName) throws KeyManagementException, ClientProtocolException, NoSuchAlgorithmException, KeyStoreException, URISyntaxException, IOException, JSONException {
		new HttpClient().
		login(login, Passward)
	     .createCustomField(	customFieldsPage.getTextBoxCustomFieldJson(customFiledType, texBoxCustomFieldName))
	     .createCustomField(customFieldsPage.getSingleChoiceCustomFieldJson(customFiledType,singleChoiceCustomFieldName))
	     .createCustomField(customFieldsPage.getDateCustomFieldJson(customFiledType, dateCustomFieldName))
	     .createCustomField(customFieldsPage.getBoleanCustomFieldJson(customFiledType, yesNoCustomFieldName))
	     .createCustomField(	customFieldsPage.getNumberCustomFieldJson(customFiledType, numberCustomFieldName));
		
		CustomField textBox = generateCustomField(CustomFieldType.TextBox, texBoxCustomFieldName);
		CustomField dateTime = generateCustomField(CustomFieldType.DateTime , dateCustomFieldName);
		CustomField singleChoice = generateCustomField(CustomFieldType.SingleChoice, singleChoiceCustomFieldName);
		CustomField yesNO = generateCustomField(CustomFieldType.YesNo , yesNoCustomFieldName);
		CustomField number = generateCustomField(CustomFieldType.Number, numberCustomFieldName);
		customFields.add(textBox);
		customFields.add(dateTime);
		customFields.add(singleChoice);
		customFields.add(yesNO);
		customFields.add(number);
		
		
		importName = "Import_" + CommonUtils.getUnicName();
	
		openImportsPageFromCustomFieldsPage(login, Passward );
		importPage.hitImportListOFSupportersButton().
		fillFirstStep(importName, "ImportDescription" , customFields);
		
		//importPage.verifyStatusOfImport(importName, importStatusCompleted);
	//	removeCustomFields();
//		openAlertsPage();
//		alertsPage.verifyImportAlerts(importName);
	}
	
	/**
	 * <b>Import of supporters without custom fields.</b>
	 * <p>
	 * Steps:
	 * <ul>
	 * <li> Login into existing organization
	 * <li> Open imports page (Audience --> Manage Imports)
	 * <li> Click on Import Supporters List button
	 * <li> Fill import name, description, choose generated file without custom fields
	 * <li> Click on Next button
	 * <li> Choose that data starts on row #2
	 * <li> Map fields
	 * <li> Click on Next button
	 * <li> Wait for completion
	 * <li> Go to import's page
	 * <li> <font color="green"><b>Verify that table contains record about current import completed</b></font>
	 * <li> Open alerts page
	 * <li> <font color="green"><b>Verify that alerts table contains record about current import started</b></font>
	 * <li> <font color="green"><b>Verify that alerts table contains record about current import finished</b></font>
	 * </ul>
	 *  
	 */
	@Test(enabled = true, groups = {"importSupporters"}, retryAnalyzer = RetryAnalyzer.class)
	public void testImportSupporters() {
		importName = "Import_" + CommonUtils.getUnicName();
		customFields = null;
		doLogin();
		openImportsPage();
		//doImport();
		importPage.verifyStatusOfImport(importName, importStatusCompleted);
	}
	
	/*private void doImport() {
		ImportAddPage importAddPage = importPage.startNewImport();
		importAddPage.fillFirstStep(importName, "description", customFields);
		importAddPage.fillSecondStep("2").fillThirdStep();
		importPage = importAddPage.openImportPage();
	}*/
	
	private void doLogin() {
		homePage = new LoginPage().doSuccessLogin();
	}
	
	private void createCustomFields() {
		customFieldsPage = homePage.openSettingsPage().switchToCustomFieldsPage();
		for (CustomField cf : customFields) {
			customFieldsPage.createCustomField(cf);
		}
	}
	
	private CustomField generateCustomField(CustomFieldType customFieldType, String customFieldName) {
		return new CustomField(customFieldType, customFieldName);
	}

	
	private void openImportsPageFromCustomFieldsPage(String userName, String password) {
		importPage = new LoginPage().doSuccessLogin(userName, password).openAudiencePage().openImportPage();
	}
	
	private void openImportsPage() {
		importPage = homePage.openAudiencePage().openImportPage();
	}
	
	private void openAlertsPage() {
		alertsPage = homePage.openAlertsPage();
	}
	
	private void removeCustomFields() {
		customFieldsPage = homePage.openSettingsPage().switchToCustomFieldsPage();
		for (CustomField cf : customFields) {
			customFieldsPage.deleteCustomField(cf);
		}
	}

}