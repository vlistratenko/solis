package com.salsalabs.ignite.automation.suites.regression;

import com.salsalabs.ignite.automation.common.*;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;
import com.salsalabs.ignite.automation.pages.hq.activities.AddSubscribeWidgetPage;
import com.salsalabs.ignite.automation.pages.hq.activities.FormFieldConfigurationModalWindow;
import com.salsalabs.ignite.automation.pages.hq.activities.SubscribeWidget;
import com.salsalabs.ignite.automation.pages.hq.manage.CustomFieldsPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONException;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;


public class SignupFormFieldsValidationTest extends SeleneseTestCase {

    private AddSubscribeWidgetPage addSignupFormsPage;
    private FormFieldConfigurationModalWindow formFieldConfigurationModal;

    @Parameters({"login","password"})
    @BeforeSuite(alwaysRun = true)
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
                getCustomFieldApiGenerator("activityDateTimeCustomField", "FieldDescription").
                setControlType("DATETIME").
                setDateFieldMinDateApi("09/15/2017").
                setDateFieldMaxDateApi("09/15/2018").
                setDateFieldMinTimeApi("10:30pm").
                setDateFieldMaxTimeApi("11:30pm"));

        CustomFieldsPage.CustomField activityNumberCustomFieldConfig = CustomFieldsPage.createCustomField(CustomFieldsPage.
                getCustomFieldApiGenerator("activityNumberCustomField", "FieldDescription").
                setControlType("INPUT"));

        CustomFieldsPage.CustomField activitySingleChoiceCustomFieldConfig = CustomFieldsPage.createCustomField(CustomFieldsPage.
                getCustomFieldApiGenerator("activitySingleChoiceCustomField", "FieldDescription").
                setControlType("RADIO").
                setSingleChoiceFieldValueLabelsApi("value1", "value2").
                setDefaultValue("value1"));

        CustomFieldsPage.CustomField activityYesNoCustomFieldConfig = CustomFieldsPage.createCustomField(CustomFieldsPage.
                getCustomFieldApiGenerator("activityYesNoCustomField", "FieldDescription").
                setYesNoFieldControlOrientationApi("VERTICAL").
                setDefaultValue("true").
                setYesNoFieldValueLabelsApi("truefalse"));

        CustomFieldsPage.CustomField activityTextBoxCustomFieldConfig = CustomFieldsPage.createCustomField(CustomFieldsPage.
                getCustomFieldApiGenerator("activityTextBoxCustomField", "FieldDescription").
                setControlType("INPUT").
                setGhostText("activityTextBoxCustomField").
                setTextFieldMinLengthValue(3).
                setTextFieldMaxLengthValue(200).
                setTextFieldValidationValue("ANY_CHARACTER"));

        try {
            try {
                HttpClient httpClient = new HttpClient().login(login, password);
                httpClient.createCustomField(supporterTextBoxCustomFieldConfig.createSupporterCustomFieldViaApiJsonObject(CustomFieldsPage.CustomFieldType.TextBox));
                httpClient.createCustomField(supporterNumberCustomFieldConfig.createSupporterCustomFieldViaApiJsonObject(CustomFieldsPage.CustomFieldType.Number));
                httpClient.createCustomField(supporterYesNoCustomFieldConfig.createSupporterCustomFieldViaApiJsonObject(CustomFieldsPage.CustomFieldType.YesNo));
                httpClient.createCustomField(supporterSingleChoiceCustomFieldConfig.createSupporterCustomFieldViaApiJsonObject(CustomFieldsPage.CustomFieldType.SingleChoice));
                httpClient.createCustomField(supporterDateTimeCustomFieldConfig.createSupporterCustomFieldViaApiJsonObject(CustomFieldsPage.CustomFieldType.DateTime));
                httpClient.createCustomField(activityTextBoxCustomFieldConfig.createActivityCustomFieldViaApiJsonObject((CustomFieldsPage.CustomFieldType.TextBox),"SUBSCRIBE"));
                httpClient.createCustomField(activityNumberCustomFieldConfig.createActivityCustomFieldViaApiJsonObject((CustomFieldsPage.CustomFieldType.Number),"SUBSCRIBE"));
                httpClient.createCustomField(activityYesNoCustomFieldConfig.createActivityCustomFieldViaApiJsonObject((CustomFieldsPage.CustomFieldType.YesNo),"SUBSCRIBE"));
                httpClient.createCustomField(activitySingleChoiceCustomFieldConfig.createActivityCustomFieldViaApiJsonObject((CustomFieldsPage.CustomFieldType.SingleChoice),"SUBSCRIBE"));
                httpClient.createCustomField(activityDateTimeCustomFieldConfig.createActivityCustomFieldViaApiJsonObject((CustomFieldsPage.CustomFieldType.DateTime),"SUBSCRIBE"));
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
     * <b>Create and submit Signup form with all supporter non-required fields</b>
     * <p>
     * Steps:
     * <ul>
     * <li> Pre-create custom fields of all types (if not created already). This includes all supporter custom fields and all activity Signup form-specific custom fields
     * <li> Login > Open Activities page > Open Signup forms page > Click on Create Signup form
     * <li> Specify form name and description > Select Blank layout > Drop One-column row element > Drop VE form element
     * <li> Drop on the layout all available supporter fields
     * <li> Go to Autoresponders > Publish form > Go to form public URL
     * <li> Fill in all fields > Submit the form
     * <li> <font color="green"><b>Verify whether created supporter contains all fields values that have been specified when submitting the form</b></font>
     */

    @Parameters({"login","password"})
    @Test(groups = {"signupFormFieldsValidation"}, retryAnalyzer = RetryAnalyzer.class)
    public void testCreatePublishSubmitSignupFormSupporterNonRequiredFields(String login, String password){

      String widgetName = "SubscribeWidgetName_" + RandomStringUtils.randomAlphanumeric(5);
      String widgetDescription = "SubscribeWidgetDescription_" + RandomStringUtils.randomAlphanumeric(10);
      String supporterEmail = "autosupporter" + RandomStringUtils.randomAlphanumeric(4)+"@test.com";

        addSignupFormsPage = new LoginPage().doSuccessLogin(login, password)
                .openActivitiesPage()
                .openSubscribeWidgetsPage()
                .openAddSubscribeWidgetPage();
        addSignupFormsPage.fillFieldsWidgetStepOne(widgetName, widgetDescription);
        addSignupFormsPage.selectLayoutStep("Blank");
        addSignupFormsPage.dropOneColumnRow();
        addSignupFormsPage.dropVEFormElement();
        formFieldConfigurationModal = new FormFieldConfigurationModalWindow();
        formFieldConfigurationModal.dropAllSupporterFieldsOnForm();
        addSignupFormsPage.goToAutorespondersTab();
        addSignupFormsPage.publishFromAutoresponders();
        addSignupFormsPage.openSubscribeWidget();

        SubscribeWidget signupForm1 = new SubscribeWidget();
        signupForm1.fillSubscribeWidgetAllSupporterFields(
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
                "09/11/2017");

        addSignupFormsPage.verifySubmittedSupporterFieldsArePresentInSupporterDetails("https://hq.test.igniteaction.net",login,password);
    }

    /**
     * <b>Create and submit Signup form with all custom non-required fields</b>
     * <p>
     * Steps:
     * <ul>
     * <li> Pre-create custom fields of all types (if not created already). This includes all supporter custom fields and all activity Signup form-specific custom fields
     * <li> Login > Open Activities page > Open Signup forms page > Click on Create Signup form
     * <li> Specify form name and description > Select Blank layout > Drop One-column row element > Drop VE form element
     * <li> Drop on the layout all available pre-created custom fields
     * <li> Go to Autoresponders > Publish form > Go to form public URL
     * <li> Fill in all fields > Submit the form
     * <li> <font color="green"><b>Verify whether created supporter contains all fields values that have been specified when submitting the form</b></font>
     */

    @Parameters({"login","password"})
    @Test(enabled=true, groups = {"signupFormFieldsValidation"}, retryAnalyzer = RetryAnalyzer.class)
    public void testCreatePublishSubmitSignupFormCustomNonRequiredFields(String login, String password){

        String widgetName = "SubscribeWidgetName_" + RandomStringUtils.randomAlphanumeric(5);
        String widgetDescription = "SubscribeWidgetDescription_" + RandomStringUtils.randomAlphanumeric(10);
        String supporterEmail = "autosupporter" + RandomStringUtils.randomAlphanumeric(4)+"@test.com";

        addSignupFormsPage = new LoginPage().doSuccessLogin(login, password)
                .openActivitiesPage()
                .openSubscribeWidgetsPage()
                .openAddSubscribeWidgetPage();
        addSignupFormsPage.fillFieldsWidgetStepOne(widgetName, widgetDescription);
        addSignupFormsPage.selectLayoutStep("Blank");
        addSignupFormsPage.dropOneColumnRow();
        addSignupFormsPage.dropVEFormElement();
        formFieldConfigurationModal = new FormFieldConfigurationModalWindow();
        new FormFieldConfigurationModalWindow().dropFormFieldByName("activityTextBoxCustomField").saveFieldConfiguration();
        new FormFieldConfigurationModalWindow().dropFormFieldByName("activityNumberCustomField").saveFieldConfiguration();
        new FormFieldConfigurationModalWindow().dropFormFieldByName("supporterSingleChoiceCustomField").saveFieldConfiguration();
        new FormFieldConfigurationModalWindow().dropFormFieldByName("supporterYesNoCustomField").saveFieldConfiguration();
        new FormFieldConfigurationModalWindow().dropFormFieldByName("supporterDateTimeCustomField").saveFieldConfiguration();
        new FormFieldConfigurationModalWindow().dropFormFieldByName("activitySingleChoiceCustomField").saveFieldConfiguration();
        new FormFieldConfigurationModalWindow().dropFormFieldByName("activityYesNoCustomField").saveFieldConfiguration();
        new FormFieldConfigurationModalWindow().dropFormFieldByName("activityDateTimeCustomField").saveFieldConfiguration();
        new FormFieldConfigurationModalWindow().dropFormFieldByName("supporterTextBoxCustomField").saveFieldConfiguration();
        new FormFieldConfigurationModalWindow().dropFormFieldByName("supporterNumberCustomField").saveFieldConfiguration();
        addSignupFormsPage.goToAutorespondersTab();
        addSignupFormsPage.publishFromAutoresponders();
        addSignupFormsPage.openSubscribeWidget();

        SubscribeWidget signupForm2 = new SubscribeWidget();
        signupForm2.fillSubscribeWidgetAllCustomFields(
                supporterEmail,
                "personFName",
                "personLName",
                "supporterTextBoxCustomFieldValue",
                "13",
                "10/11/2017 01:30 am",
                "activityTextBoxCustomFieldValue",
                "13",
                "10/11/2017 01:30 am");

        addSignupFormsPage.verifySubmittedCustomFieldsArePresentInSupporterDetails("https://hq.test.igniteaction.net",login,password);
    }

    /**
     * <b>Create and submit Signup form with all supporter required fields</b>
     * <p>
     * Steps:
     * <ul>
     * <li> Login > Open Activities page > Open Signup forms page > Click on Create Signup form
     * <li> Specify form name and description > Select Blank layout > Drop One-column row element > Drop VE form element
     * <li> Drop on the layout all available supporter fields and mark them as required
     * <li> Go to Autoresponders > Publish form > Go to form public URL
     * <li> Click on Submit button
     * <li> <font color="green"><b>Verify whether validation messages appears and prompts to fill in required fields</b></font>
     */

    @Parameters({"login","password"})
    @Test(enabled=true, groups = {"signupFormFieldsValidation"}, retryAnalyzer = RetryAnalyzer.class)
    public void testCreatePublishSubmitSignupFormRequiredEmptySupporterFields(String login, String password){

        String widgetName = "SubscribeWidgetName_" + RandomStringUtils.randomAlphanumeric(5);
        String widgetDescription = "SubscribeWidgetDescription_" + RandomStringUtils.randomAlphanumeric(10);

        addSignupFormsPage = new LoginPage().doSuccessLogin(login, password)
                .openActivitiesPage()
                .openSubscribeWidgetsPage()
                .openAddSubscribeWidgetPage();
        addSignupFormsPage.fillFieldsWidgetStepOne(widgetName, widgetDescription);
        addSignupFormsPage.selectLayoutStep("Blank");
        addSignupFormsPage.dropOneColumnRow();
        addSignupFormsPage.dropVEFormElement();
        formFieldConfigurationModal = new FormFieldConfigurationModalWindow();
        addSignupFormsPage.editVEField("Address, line 1").markFieldAsRequired().saveFieldConfiguration();
        addSignupFormsPage.editVEField("City").markFieldAsRequired().saveFieldConfiguration();
        addSignupFormsPage.editVEField("State").markFieldAsRequired().saveFieldConfiguration();
        addSignupFormsPage.editVEField("Zip Code").markFieldAsRequired().saveFieldConfiguration();
        formFieldConfigurationModal.dropAllSupporterFieldsOnFormAndMarkAsRequired();
        addSignupFormsPage.goToAutorespondersTab();
        addSignupFormsPage.publishFromAutoresponders();
        addSignupFormsPage.openSubscribeWidget();

        SubscribeWidget signupForm3 = addSignupFormsPage.openSubscribeWidget();
        signupForm3.clickOnSubmitFormButton().
                verifyValidationMessageFieldRequireValueDisplayedForEmptySupporterFields();
    }

    /**
     * <b>Create and submit Signup form with all custom required fields</b>
     * <p>
     * Steps:
     * <ul>
     * <li> Pre-create custom fields of all types (if not created already). This includes all supporter custom fields and all activity Signup form-specific custom fields
     * <li> Login > Open Activities page > Open Signup forms page > Click on Create Signup form
     * <li> Specify form name and description > Select Blank layout > Drop One-column row element > Drop VE form element
     * <li> Drop on the layout all available pre-created custom fields and mark them as required
     * <li> Go to Autoresponders > Publish form > Go to form public URL
     * <li> Click on Submit button
     * <li> <font color="green"><b>Verify whether validation messages appears and prompts to fill in required fields</b></font>
     */

    @Parameters({"login","password"})
    @Test(enabled=true, groups = {"signupFormFieldsValidation"}, retryAnalyzer = RetryAnalyzer.class)
    public void testCreatePublishSubmitSignupFormRequiredEmptyCustomFields(String login, String password){

        String widgetName = "SubscribeWidgetName_" + RandomStringUtils.randomAlphanumeric(5);
        String widgetDescription = "SubscribeWidgetDescription_" + RandomStringUtils.randomAlphanumeric(10);

        addSignupFormsPage = new LoginPage().doSuccessLogin(login, password)
                .openActivitiesPage()
                .openSubscribeWidgetsPage()
                .openAddSubscribeWidgetPage();
        addSignupFormsPage.fillFieldsWidgetStepOne(widgetName, widgetDescription);
        addSignupFormsPage.selectLayoutStep("Blank");
        addSignupFormsPage.dropOneColumnRow();
        addSignupFormsPage.dropVEFormElement();
        formFieldConfigurationModal = new FormFieldConfigurationModalWindow();
        new FormFieldConfigurationModalWindow().dropFormFieldByName("activityTextBoxCustomField").markFieldAsRequired().saveFieldConfiguration();
        new FormFieldConfigurationModalWindow().dropFormFieldByName("activityNumberCustomField").markFieldAsRequired().saveFieldConfiguration();
        new FormFieldConfigurationModalWindow().dropFormFieldByName("supporterSingleChoiceCustomField").saveFieldConfiguration();
        new FormFieldConfigurationModalWindow().dropFormFieldByName("supporterYesNoCustomField").saveFieldConfiguration();
        new FormFieldConfigurationModalWindow().dropFormFieldByName("supporterDateTimeCustomField").markFieldAsRequired().saveFieldConfiguration();
        new FormFieldConfigurationModalWindow().dropFormFieldByName("activitySingleChoiceCustomField").saveFieldConfiguration();
        new FormFieldConfigurationModalWindow().dropFormFieldByName("activityYesNoCustomField").saveFieldConfiguration();
        new FormFieldConfigurationModalWindow().dropFormFieldByName("activityDateTimeCustomField").markFieldAsRequired().saveFieldConfiguration();
        new FormFieldConfigurationModalWindow().dropFormFieldByName("supporterTextBoxCustomField").markFieldAsRequired().saveFieldConfiguration();
        new FormFieldConfigurationModalWindow().dropFormFieldByName("supporterNumberCustomField").markFieldAsRequired().saveFieldConfiguration();
        addSignupFormsPage.goToAutorespondersTab();
        addSignupFormsPage.publishFromAutoresponders();

        SubscribeWidget signupForm4 = addSignupFormsPage.openSubscribeWidget();
        signupForm4.clickOnSubmitFormButton().
                verifyValidationMessageFieldRequireValueDisplayedForEmptyCustomFields();
    }
}
