package com.salsalabs.ignite.automation.suites.regression;

import com.salsalabs.ignite.automation.common.HttpClient;
import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;
import com.salsalabs.ignite.automation.pages.hq.activities.*;
import com.salsalabs.ignite.automation.pages.hq.manage.CustomFieldsPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONException;
import org.testng.ITestContext;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

@Test(groups = {"petitionFormFieldsValidation"})
public class PetitionFormFieldsValidationTest extends SeleneseTestCase {

    private AddPetitionPage addPetitionPage;
    private FormFieldConfigurationModalWindow formFieldConfigurationModal;
    String widgetName;
    String widgetDescription;
    String supporterEmail;

    @Parameters({"login","password"})
    @BeforeGroups(groups = {"petitionFormFieldsValidation"})
    public void generateCustomFieldsViaAPI(ITestContext context, String login, String password){
        logger.info("Generating custom fields for " + context.getSuite().getName() + " suite");
        CustomFieldsPage.CustomField supporterDateTimeCustomFieldConfig = CustomFieldsPage.createCustomField(CustomFieldsPage.
                getCustomFieldApiGenerator("supporterDateTimeCustomField", "FieldDescription").
                setControlType("DATETIME").
                setDateFieldMinDateApi("09/15/2000").
                setDateFieldMaxDateApi("09/15/2030").
                setDateFieldMinTimeApi("10:30pm").
                setDateFieldMaxTimeApi("11:30pm"));

        CustomFieldsPage.CustomField supporterTextBoxCustomFieldConfig = CustomFieldsPage.createCustomField(CustomFieldsPage.
                getCustomFieldApiGenerator("supporterTextBoxCustomField", "FieldDescription").
                setControlType("INPUT").
                setGhostText("supporterTextBoxCustomFieldGhostText").
                setTextFieldMinLengthValue(3).
                setTextFieldMaxLengthValue(200).
                setTextFieldValidationValue("ANY_CHARACTER"));

        CustomFieldsPage.CustomField supporterNumberCustomFieldConfig = CustomFieldsPage.createCustomField(CustomFieldsPage.
                getCustomFieldApiGenerator("supporterNumberCustomField", "FieldDescription").
                setControlType("INPUT"));

        CustomFieldsPage.CustomField supporterSingleChoiceCustomFieldConfig = CustomFieldsPage.createCustomField(CustomFieldsPage.
                getCustomFieldApiGenerator("supporterSingleChoiceCustomField", "FieldDescription").
                setControlType("RADIO").
                setSingleChoiceFieldValueLabelsApi("value1", "value2").
                setDefaultValue("value1"));

        CustomFieldsPage.CustomField supporterYesNoCustomFieldConfig = CustomFieldsPage.createCustomField(CustomFieldsPage.
                getCustomFieldApiGenerator("supporterYesNoCustomField", "FieldDescription").
                setYesNoFieldControlOrientationApi("VERTICAL").
                setDefaultValue("true").
                setYesNoFieldValueLabelsApi("truefalse"));

        CustomFieldsPage.CustomField activityDateTimeCustomFieldConfig = CustomFieldsPage.createCustomField(CustomFieldsPage.
                getCustomFieldApiGenerator("petitionActivityDateTimeCustomField", "FieldDescription").
                setControlType("DATETIME").
                setDateFieldMinDateApi("09/15/2017").
                setDateFieldMaxDateApi("09/15/2018").
                setDateFieldMinTimeApi("10:30pm").
                setDateFieldMaxTimeApi("11:30pm"));

        CustomFieldsPage.CustomField activityNumberCustomFieldConfig = CustomFieldsPage.createCustomField(CustomFieldsPage.
                getCustomFieldApiGenerator("petitionActivityNumberCustomField", "FieldDescription").
                setControlType("INPUT"));

        CustomFieldsPage.CustomField activitySingleChoiceCustomFieldConfig = CustomFieldsPage.createCustomField(CustomFieldsPage.
                getCustomFieldApiGenerator("petitionActivitySingleChoiceCustomField", "FieldDescription").
                setControlType("RADIO").
                setSingleChoiceFieldValueLabelsApi("value1", "value2").
                setDefaultValue("value1"));

        CustomFieldsPage.CustomField activityYesNoCustomFieldConfig = CustomFieldsPage.createCustomField(CustomFieldsPage.
                getCustomFieldApiGenerator("petitionActivityYesNoCustomField", "FieldDescription").
                setYesNoFieldControlOrientationApi("VERTICAL").
                setDefaultValue("true").
                setYesNoFieldValueLabelsApi("truefalse"));

        CustomFieldsPage.CustomField activityTextBoxCustomFieldConfig = CustomFieldsPage.createCustomField(CustomFieldsPage.
                getCustomFieldApiGenerator("petitionActivityTextBoxCustomField", "FieldDescription").
                setControlType("INPUT").
                setGhostText("activityTextBoxCustomField").
                setTextFieldMinLengthValue(3).
                setTextFieldMaxLengthValue(200).
                setTextFieldValidationValue("ANY_CHARACTER"));

        try {
            try {
                HttpClient httpClient = new HttpClient(SeleneseTestCase.USED_ENVIRONMENT.getBaseTestUrl()).login(login, password);
                httpClient.createCustomField(supporterTextBoxCustomFieldConfig.createSupporterCustomFieldViaApiJsonObject(CustomFieldsPage.CustomFieldType.TextBox));
                httpClient.createCustomField(supporterNumberCustomFieldConfig.createSupporterCustomFieldViaApiJsonObject(CustomFieldsPage.CustomFieldType.Number));
                httpClient.createCustomField(supporterYesNoCustomFieldConfig.createSupporterCustomFieldViaApiJsonObject(CustomFieldsPage.CustomFieldType.YesNo));
                httpClient.createCustomField(supporterSingleChoiceCustomFieldConfig.createSupporterCustomFieldViaApiJsonObject(CustomFieldsPage.CustomFieldType.SingleChoice));
                httpClient.createCustomField(supporterDateTimeCustomFieldConfig.createSupporterCustomFieldViaApiJsonObject(CustomFieldsPage.CustomFieldType.DateTime));
                httpClient.createCustomField(activityTextBoxCustomFieldConfig.createActivityCustomFieldViaApiJsonObject((CustomFieldsPage.CustomFieldType.TextBox),"PETITION"));
                httpClient.createCustomField(activityNumberCustomFieldConfig.createActivityCustomFieldViaApiJsonObject((CustomFieldsPage.CustomFieldType.Number),"PETITION"));
                httpClient.createCustomField(activityYesNoCustomFieldConfig.createActivityCustomFieldViaApiJsonObject((CustomFieldsPage.CustomFieldType.YesNo),"PETITION"));
                httpClient.createCustomField(activitySingleChoiceCustomFieldConfig.createActivityCustomFieldViaApiJsonObject((CustomFieldsPage.CustomFieldType.SingleChoice),"PETITION"));
                httpClient.createCustomField(activityDateTimeCustomFieldConfig.createActivityCustomFieldViaApiJsonObject((CustomFieldsPage.CustomFieldType.DateTime),"PETITION"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
    }

    /**
     * <b>Create and submit Petition form with all supporter non-required fields</b>
     * <p>
     * Steps:
     * <ul>
     * <li> Pre-create custom fields of all types (if not created already). This includes all supporter custom fields and all activity Petition form-specific custom fields
     * <li> Login > Open Activities page > Open Petition forms page > Click on Create Petition form
     * <li> Specify form name and description > Select Blank layout > Drop One-column row element > Drop VE form element
     * <li> Drop on the layout all available supporter fields
     * <li> Go to Autoresponders > Publish form > Go to form public URL
     * <li> Fill in all fields > Submit the form
     * <li> <font color="green"><b>Verify whether created supporter contains all fields values that have been specified when submitting the form</b></font>
     */

    @Parameters({"login","password"})
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testCreatePublishSubmitPetitionFormSupporterNonRequiredFields(String login, String password){
        widgetName = "PetitionWidgetName_" + RandomStringUtils.randomAlphanumeric(5);
        widgetDescription = "PetitionWidgetDescription_" + RandomStringUtils.randomAlphanumeric(10);
        supporterEmail = "autosupporter" + RandomStringUtils.randomAlphanumeric(4)+"@test.com";
        addPetitionPage = new LoginPage()
                .doSuccessLogin(login, password)
                .openActivitiesPage()
                .openPetitionsPage()
                .openAddPetitionPage();
        addPetitionPage.fillFieldsWidgetStepOne(widgetName, widgetDescription)
                .selectLayoutStep("Blank");
        addPetitionPage.dropOneColumnRow();
        addPetitionPage.dropVEFormElement();
        new FormFieldConfigurationModalWindow().dropAllSupporterFieldsOnForm();
        addPetitionPage.goToAutorespondersTab();
        addPetitionPage.publishFromAutoresponders();
        addPetitionPage.openSubscribeWidget();
        SubscribeWidget petitionForm1 = new SubscribeWidget();
        petitionForm1.fillSubscribeWidgetAllSupporterFields(
                supporterEmail,
                "personFName",
                "personLName",
                "personCity",
                "20008",
                "UA-63",
                "addressLine1",
                "addressLine2",
                "Male",
                "777-777-7777",
                "personMName",
                "en-US",
                "suffixValue",
                "titleValue",
                "777-777-7777",
                "777-777-7777",
                "UA",
                "09/11/2017").
                clickOnSubmitFormButton();
        addPetitionPage.verifySubmittedSupporterFieldsArePresentInSupporterDetails(SeleneseTestCase.USED_ENVIRONMENT.getBaseTestUrl(), login, password);
    }

    /**
     * <b>Create and submit Petition form with all custom non-required fields</b>
     * <p>
     * Steps:
     * <ul>
     * <li> Pre-create custom fields of all types (if not created already). This includes all supporter custom fields and all activity Petition form-specific custom fields
     * <li> Login > Open Activities page > Open Petition forms page > Click on Create Petition form
     * <li> Specify form name and description > Select Blank layout > Drop One-column row element > Drop VE form element
     * <li> Drop on the layout all available pre-created custom fields
     * <li> Go to Autoresponders > Publish form > Go to form public URL
     * <li> Fill in all fields > Submit the form
     * <li> <font color="green"><b>Verify whether created supporter contains all fields values that have been specified when submitting the form</b></font>
     */

    @Parameters({"login","password"})
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testCreatePublishSubmitPetitionFormCustomNonRequiredFields(String login, String password){
        widgetName = "PetitionWidgetName_" + RandomStringUtils.randomAlphanumeric(5);
        widgetDescription = "PetitionWidgetDescription_" + RandomStringUtils.randomAlphanumeric(10);
        supporterEmail = "autosupporter" + RandomStringUtils.randomAlphanumeric(4)+"@test.com";
        addPetitionPage = new LoginPage()
                .doSuccessLogin(login, password)
                .openActivitiesPage()
                .openPetitionsPage()
                .openAddPetitionPage();
        addPetitionPage.fillFieldsWidgetStepOne(widgetName, widgetDescription)
                .selectLayoutStep("Blank");
        addPetitionPage.dropOneColumnRow();
        addPetitionPage.dropVEFormElement();
        formFieldConfigurationModal = new FormFieldConfigurationModalWindow();
        formFieldConfigurationModal.dropFormFieldByName("petitionActivityTextBoxCustomField").saveFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("petitionActivityNumberCustomField").saveFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("supporterSingleChoiceCustomField").saveFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("supporterYesNoCustomField").saveFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("supporterDateTimeCustomField").saveFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("petitionActivitySingleChoiceCustomField").saveFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("petitionActivityYesNoCustomField").saveFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("petitionActivityDateTimeCustomField").saveFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("supporterTextBoxCustomField").saveFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("supporterNumberCustomField").saveFieldConfiguration();
        addPetitionPage.goToAutorespondersTab();
        addPetitionPage.publishFromAutoresponders();
        addPetitionPage.openSubscribeWidget();
        SubscribeWidget petitionForm2 = new SubscribeWidget();
        petitionForm2.fillSubscribeWidgetAllCustomFields(
                supporterEmail,
                "personFName",
                "personLName",
                "supporterTextBoxCustomFieldValue",
                "13",
                "10/11/2017 01:30 am",
                "activityTextBoxCustomFieldValue",
                "13",
                "10/11/2017 01:30 am").
                clickOnSubmitFormButton();
        addPetitionPage.verifySubmittedCustomFieldsArePresentInSupporterDetails(SeleneseTestCase.USED_ENVIRONMENT.getBaseTestUrl(),login,password);
    }

    /**
     * <b>Create and submit Petition form with all supporter required fields</b>
     * <p>
     * Steps:
     * <ul>
     * <li> Login > Open Activities page > Open Petition forms page > Click on Create Petition form
     * <li> Specify form name and description > Select Blank layout > Drop One-column row element > Drop VE form element
     * <li> Drop on the layout all available supporter fields and mark them as required
     * <li> Go to Autoresponders > Publish form > Go to form public URL
     * <li> Click on Submit button
     * <li> <font color="green"><b>Verify whether validation messages appears and prompts to fill in required fields</b></font>
     */

    @Parameters({"login","password"})
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testCreatePublishSubmitPetitionFormRequiredEmptySupporterFields(String login, String password){
        widgetName = "PetitionWidgetName_" + RandomStringUtils.randomAlphanumeric(5);
        widgetDescription = "PetitionWidgetDescription_" + RandomStringUtils.randomAlphanumeric(10);
        supporterEmail = "autosupporter" + RandomStringUtils.randomAlphanumeric(4)+"@test.com";
        addPetitionPage = new LoginPage()
                .doSuccessLogin(login, password)
                .openActivitiesPage()
                .openPetitionsPage()
                .openAddPetitionPage();
        addPetitionPage.fillFieldsWidgetStepOne(widgetName, widgetDescription)
                .selectLayoutStep("Blank");
        addPetitionPage.dropOneColumnRow();
        addPetitionPage.dropVEFormElement();
        addPetitionPage.editVEField("City").markFieldAsRequired().saveFieldConfiguration();
        addPetitionPage.editVEField("State").markFieldAsRequired().saveFieldConfiguration();
        addPetitionPage.editVEField("Zip Code").markFieldAsRequired().saveFieldConfiguration();
        new FormFieldConfigurationModalWindow().dropAllSupporterFieldsOnFormAndMarkAsRequired();
        addPetitionPage.goToAutorespondersTab();
        addPetitionPage.publishFromAutoresponders();
        addPetitionPage.openSubscribeWidget();
        SubscribeWidget petitionForm3 = addPetitionPage.openSubscribeWidget();
        petitionForm3.clickOnSubmitFormButton().
                verifyValidationMessageFieldRequireValueDisplayedForEmptySupporterFields();
    }

    /**
     * <b>Create and submit Petition form with all custom required fields</b>
     * <p>
     * Steps:
     * <ul>
     * <li> Pre-create custom fields of all types (if not created already). This includes all supporter custom fields and all activity Petition form-specific custom fields
     * <li> Login > Open Activities page > Open Petition forms page > Click on Create Petition form
     * <li> Specify form name and description > Select Blank layout > Drop One-column row element > Drop VE form element
     * <li> Drop on the layout all available pre-created custom fields and mark them as required
     * <li> Go to Autoresponders > Publish form > Go to form public URL
     * <li> Click on Submit button
     * <li> <font color="green"><b>Verify whether validation messages appears and prompts to fill in required fields</b></font>
     */

    @Parameters({"login","password"})
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testCreatePublishSubmitPetitionFormRequiredEmptyCustomFields(String login, String password){
        widgetName = "PetitionWidgetName_" + RandomStringUtils.randomAlphanumeric(5);
        widgetDescription = "PetitionWidgetDescription_" + RandomStringUtils.randomAlphanumeric(10);
        supporterEmail = "autosupporter" + RandomStringUtils.randomAlphanumeric(4)+"@test.com";
        addPetitionPage = new LoginPage()
                .doSuccessLogin(login, password)
                .openActivitiesPage()
                .openPetitionsPage()
                .openAddPetitionPage();
        addPetitionPage.fillFieldsWidgetStepOne(widgetName, widgetDescription)
                .selectLayoutStep("Blank");
        addPetitionPage.dropOneColumnRow();
        addPetitionPage.dropVEFormElement();
        formFieldConfigurationModal = new FormFieldConfigurationModalWindow();
        formFieldConfigurationModal.dropFormFieldByName("petitionActivityTextBoxCustomField").markFieldAsRequired().saveFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("petitionActivityNumberCustomField").markFieldAsRequired().saveFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("supporterSingleChoiceCustomField").saveFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("supporterYesNoCustomField").saveFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("supporterDateTimeCustomField").markFieldAsRequired().saveFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("petitionActivitySingleChoiceCustomField").saveFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("petitionActivityYesNoCustomField").saveFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("petitionActivityDateTimeCustomField").markFieldAsRequired().saveFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("supporterTextBoxCustomField").markFieldAsRequired().saveFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("supporterNumberCustomField").markFieldAsRequired().saveFieldConfiguration();
        addPetitionPage.goToAutorespondersTab();
        addPetitionPage.publishFromAutoresponders();
        SubscribeWidget petitionForm4 = addPetitionPage.openSubscribeWidget();
        petitionForm4.clickOnSubmitFormButton().
                verifyValidationMessageFieldRequireValueDisplayedForEmptyCustomFields();
    }
}
