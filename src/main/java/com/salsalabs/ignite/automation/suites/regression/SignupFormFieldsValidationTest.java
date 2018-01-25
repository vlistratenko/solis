package com.salsalabs.ignite.automation.suites.regression;

import com.salsalabs.ignite.automation.common.*;
import com.salsalabs.ignite.automation.elements.VE2Elements.SignupFormElements;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;
import com.salsalabs.ignite.automation.pages.hq.activities.AddSubscribeWidgetPage;
import com.salsalabs.ignite.automation.pages.hq.activities.FormFieldConfigurationModalWindow;
import com.salsalabs.ignite.automation.pages.hq.activities.SubscribeWidget;
import com.salsalabs.ignite.automation.pages.hq.manage.CustomFieldsPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONException;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

@Test(groups = {"signupFormFieldsValidation"})
public class SignupFormFieldsValidationTest extends SeleneseTestCase {

    private AddSubscribeWidgetPage addSignupFormsPage;
    private FormFieldConfigurationModalWindow formFieldConfigurationModal;
    String widgetName;
    String widgetDescription;
    String supporterEmail;

    @Parameters({"login","password"})
    @BeforeGroups(groups = {"signupFormFieldsValidation"})
    public void generateCustomFieldsViaAPI(ITestContext context, String login, String password){
        logger.info("Generating custom fields for " + context.getSuite().getName() + " suite");

        CustomFieldsPage.CustomField supporterDateTimeCustomFieldConfig = CustomFieldsPage.createCustomField(CustomFieldsPage.
                getCustomFieldApiGenerator("supporterDateTimeCustomField", "FieldDescription").
                setControlType("DATETIME"));

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
                getCustomFieldApiGenerator("signupActivityDateTimeCustomField", "FieldDescription").
                setControlType("DATETIME"));

        CustomFieldsPage.CustomField activityNumberCustomFieldConfig = CustomFieldsPage.createCustomField(CustomFieldsPage.
                getCustomFieldApiGenerator("signupActivityNumberCustomField", "FieldDescription").
                setControlType("INPUT"));

        CustomFieldsPage.CustomField activitySingleChoiceCustomFieldConfig = CustomFieldsPage.createCustomField(CustomFieldsPage.
                getCustomFieldApiGenerator("signupActivitySingleChoiceCustomField", "FieldDescription").
                setControlType("RADIO").
                setSingleChoiceFieldValueLabelsApi("value1", "value2").
                setDefaultValue("value1"));

        CustomFieldsPage.CustomField activityYesNoCustomFieldConfig = CustomFieldsPage.createCustomField(CustomFieldsPage.
                getCustomFieldApiGenerator("signupActivityYesNoCustomField", "FieldDescription").
                setYesNoFieldControlOrientationApi("VERTICAL").
                setDefaultValue("true").
                setYesNoFieldValueLabelsApi("truefalse"));

        CustomFieldsPage.CustomField activityTextBoxCustomFieldConfig = CustomFieldsPage.createCustomField(CustomFieldsPage.
                getCustomFieldApiGenerator("signupActivityTextBoxCustomField", "FieldDescription").
                setControlType("INPUT").
                setGhostText("activityTextBoxCustomField").
                setTextFieldMinLengthValue(3).
                setTextFieldMaxLengthValue(200).
                setTextFieldValidationValue("ANY_CHARACTER"));

                HttpClient httpClient = new HttpClient(SeleneseTestCase.USED_ENVIRONMENT.getBaseTestUrl()).login(login, password);
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
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testCreatePublishSubmitSignupFormSupporterNonRequiredFields(String login, String password){
        widgetName = "SubscribeWidgetName_" + RandomStringUtils.randomAlphanumeric(5);
        widgetDescription = "SubscribeWidgetDescription_" + RandomStringUtils.randomAlphanumeric(10);
        supporterEmail = "autosupporter" + RandomStringUtils.randomAlphanumeric(4)+"@test.com";
        addSignupFormsPage = new LoginPage().doSuccessLogin(login, password)
                .openActivitiesPage()
                .openSubscribeWidgetsPage()
                .openAddSubscribeWidgetPage();
        addSignupFormsPage.fillFieldsWidgetStepOne(widgetName, widgetDescription);
        addSignupFormsPage.selectLayoutStep("Blank");
        addSignupFormsPage.dropOneColumnRow();
        addSignupFormsPage.dropVEFormElement();
        new FormFieldConfigurationModalWindow().dropAllSupporterFieldsOnForm();
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
                "09/11/2017").
                clickOnSubmitFormButton();

        addSignupFormsPage.verifySubmittedSupporterFieldsArePresentInSupporterDetails(SeleneseTestCase.USED_ENVIRONMENT.getBaseTestUrl(),login,password);
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
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testCreatePublishSubmitSignupFormCustomNonRequiredFields(String login, String password){
        widgetName = "SubscribeWidgetName_" + RandomStringUtils.randomAlphanumeric(5);
        widgetDescription = "SubscribeWidgetDescription_" + RandomStringUtils.randomAlphanumeric(10);
        supporterEmail = "autosupporter" + RandomStringUtils.randomAlphanumeric(4)+"@test.com";
        addSignupFormsPage = new LoginPage().doSuccessLogin(login, password)
                .openActivitiesPage()
                .openSubscribeWidgetsPage()
                .openAddSubscribeWidgetPage();
        addSignupFormsPage.fillFieldsWidgetStepOne(widgetName, widgetDescription);
        addSignupFormsPage.selectLayoutStep("Blank");
        addSignupFormsPage.dropOneColumnRow();
        addSignupFormsPage.dropVEFormElement();
        formFieldConfigurationModal = new FormFieldConfigurationModalWindow();
        formFieldConfigurationModal.dropFormFieldByName("signupActivityTextBoxCustomField").saveFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("signupActivityNumberCustomField").saveFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("supporterSingleChoiceCustomField").saveFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("supporterYesNoCustomField").saveFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("supporterDateTimeCustomField").saveFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("signupActivitySingleChoiceCustomField").saveFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("signupActivityYesNoCustomField").saveFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("signupActivityDateTimeCustomField").saveFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("supporterTextBoxCustomField").saveFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("supporterNumberCustomField").saveFieldConfiguration();
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
                "10/11/2017 01:30 am").
                clickOnSubmitFormButton();

        addSignupFormsPage.verifySubmittedCustomFieldsArePresentInSupporterDetails(SeleneseTestCase.USED_ENVIRONMENT.getBaseTestUrl(),login,password);
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
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testCreatePublishSubmitSignupFormRequiredEmptySupporterFields(String login, String password){
        widgetName = "SubscribeWidgetName_" + RandomStringUtils.randomAlphanumeric(5);
        widgetDescription = "SubscribeWidgetDescription_" + RandomStringUtils.randomAlphanumeric(10);
        supporterEmail = "autosupporter" + RandomStringUtils.randomAlphanumeric(4)+"@test.com";
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
        SubscribeWidget signupForm3 = new SubscribeWidget();
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
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testCreatePublishSubmitSignupFormRequiredEmptyCustomFields(String login, String password){
        widgetName = "SubscribeWidgetName_" + RandomStringUtils.randomAlphanumeric(5);
        widgetDescription = "SubscribeWidgetDescription_" + RandomStringUtils.randomAlphanumeric(10);
        supporterEmail = "autosupporter" + RandomStringUtils.randomAlphanumeric(4)+"@test.com";
        addSignupFormsPage = new LoginPage().doSuccessLogin(login, password)
                .openActivitiesPage()
                .openSubscribeWidgetsPage()
                .openAddSubscribeWidgetPage();
        addSignupFormsPage.fillFieldsWidgetStepOne(widgetName, widgetDescription);
        addSignupFormsPage.selectLayoutStep("Blank");
        addSignupFormsPage.dropOneColumnRow();
        addSignupFormsPage.dropVEFormElement();
        formFieldConfigurationModal = new FormFieldConfigurationModalWindow();
        formFieldConfigurationModal.dropFormFieldByName("signupActivityTextBoxCustomField").markFieldAsRequired().saveFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("signupActivityNumberCustomField").markFieldAsRequired().saveFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("supporterSingleChoiceCustomField").saveFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("supporterYesNoCustomField").saveFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("supporterDateTimeCustomField").markFieldAsRequired().saveFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("signupActivitySingleChoiceCustomField").saveFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("signupActivityYesNoCustomField").saveFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("signupActivityDateTimeCustomField").markFieldAsRequired().saveFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("supporterTextBoxCustomField").markFieldAsRequired().saveFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("supporterNumberCustomField").markFieldAsRequired().saveFieldConfiguration();
        addSignupFormsPage.goToAutorespondersTab();
        addSignupFormsPage.publishFromAutoresponders();
        addSignupFormsPage.openSubscribeWidget();
        SubscribeWidget signupForm4 = new SubscribeWidget();
        signupForm4.clickOnSubmitFormButton().
                verifyValidationMessageFieldRequireValueDisplayedForEmptyCustomFields();
    }
}
