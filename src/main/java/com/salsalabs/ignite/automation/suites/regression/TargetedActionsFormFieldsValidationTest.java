package com.salsalabs.ignite.automation.suites.regression;

import com.salsalabs.ignite.automation.common.HttpClient;
import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;
import com.salsalabs.ignite.automation.pages.hq.activities.AddTargetedActionPage;
import com.salsalabs.ignite.automation.pages.hq.activities.FormFieldConfigurationModalWindow;
import com.salsalabs.ignite.automation.pages.hq.activities.TargetedActionPublicForm;
import com.salsalabs.ignite.automation.pages.hq.manage.CustomFieldsPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.ITestContext;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Test(groups = {"targetedActionFormFieldsValidation"})
public class TargetedActionsFormFieldsValidationTest extends SeleneseTestCase {

    String widgetName;
    FormFieldConfigurationModalWindow formFieldConfigurationModal;
    private AddTargetedActionPage targetedActionPage;
    private String supporterEmail;

    @Parameters({"login","password"})
    @BeforeGroups(groups = {"targetedActionFormFieldsValidation"})
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
                getCustomFieldApiGenerator("targetedActionActivityDateTimeCustomField", "FieldDescription").
                setControlType("DATETIME"));

        CustomFieldsPage.CustomField activityNumberCustomFieldConfig = CustomFieldsPage.createCustomField(CustomFieldsPage.
                getCustomFieldApiGenerator("targetedActionActivityNumberCustomField", "FieldDescription").
                setControlType("INPUT"));

        CustomFieldsPage.CustomField activitySingleChoiceCustomFieldConfig = CustomFieldsPage.createCustomField(CustomFieldsPage.
                getCustomFieldApiGenerator("targetedActionActivitySingleChoiceCustomField", "FieldDescription").
                setControlType("RADIO").
                setSingleChoiceFieldValueLabelsApi("value1", "value2").
                setDefaultValue("value1"));

        CustomFieldsPage.CustomField activityYesNoCustomFieldConfig = CustomFieldsPage.createCustomField(CustomFieldsPage.
                getCustomFieldApiGenerator("targetedActionActivityYesNoCustomField", "FieldDescription").
                setYesNoFieldControlOrientationApi("VERTICAL").
                setDefaultValue("true").
                setYesNoFieldValueLabelsApi("truefalse"));

        CustomFieldsPage.CustomField activityTextBoxCustomFieldConfig = CustomFieldsPage.createCustomField(CustomFieldsPage.
                getCustomFieldApiGenerator("targetedActionActivityTextBoxCustomField", "FieldDescription").
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
        httpClient.createCustomField(activityTextBoxCustomFieldConfig.createActivityCustomFieldViaApiJsonObject((CustomFieldsPage.CustomFieldType.TextBox),"TARGETED_LETTER"));
        httpClient.createCustomField(activityNumberCustomFieldConfig.createActivityCustomFieldViaApiJsonObject((CustomFieldsPage.CustomFieldType.Number),"TARGETED_LETTER"));
        httpClient.createCustomField(activityYesNoCustomFieldConfig.createActivityCustomFieldViaApiJsonObject((CustomFieldsPage.CustomFieldType.YesNo),"TARGETED_LETTER"));
        httpClient.createCustomField(activitySingleChoiceCustomFieldConfig.createActivityCustomFieldViaApiJsonObject((CustomFieldsPage.CustomFieldType.SingleChoice),"TARGETED_LETTER"));
        httpClient.createCustomField(activityDateTimeCustomFieldConfig.createActivityCustomFieldViaApiJsonObject((CustomFieldsPage.CustomFieldType.DateTime),"TARGETED_LETTER"));
    }

    /**
     * <b>Create and submit Targeted Action form with all supporter non-required fields</b>
     * <p>
     * Steps:
     * <ul>
     * <li> Login > Open Activities page > Open Targeted Action forms page > Click on Create Targeted Action form
     * <li> Specify form name and description > Pick up target > Configure Email channel message > Proceed to Social Promotion tab
     * <li> Proceed to Select layout tab > Select Blank layout > Drop One-column row element > Drop Multi-step form element
     * <li> Drop all supporter fields on the form > Go to Action page wizard step > Drop One-column row element > Drop Targeted Messages form element
     * <li> Go to Autoresponders > Publish form > Go to form public URL
     * <li> Fill in all fields > Submit the form
     * <li> <font color="green"><b>Verify whether created supporter contains all fields values that have been specified when submitting the form</b></font>
     */

    @Parameters({"login", "password"})
    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class)
    public void testCreatePublishSubmitTargetedActionsFormNonRequiredSupporterFields(String login, String password) {
        widgetName = "TargetedActionForm_" + RandomStringUtils.randomAlphanumeric(9);
        supporterEmail = RandomStringUtils.randomAlphanumeric(7)+"ta@test.com";
        targetedActionPage = new LoginPage().doSuccessLogin(login, password)
                .openActivitiesPage()
                .openTargetedActionsPage()
                .openAddTargetedActionPage()
                .fillFieldsWidgetStepOne(widgetName,"TADescription")
                .pickTargetAndEnableChannel("brad james", AddTargetedActionPage.Channel.EMAIL)
                .editMessage(1)
                .clickOnNextButtonInTargetsTab()
                .clickOnNextButtonInSocialPromotionTab();
        targetedActionPage.selectLayoutStep("Blank")
                .dropOneColumnRow();
        targetedActionPage.dropMultiStepFormElement();
        new FormFieldConfigurationModalWindow().dropAllSupporterFieldsOnForm();
        targetedActionPage.goToActionPageWizardStep()
                .dropOneColumnRow();
        targetedActionPage.dropTargetedMessagesFormElement()
                .goToAutorespondersTab()
                .publishFromAutoresponders()
                .openSubscribeWidget();

        new TargetedActionPublicForm().fillTargetedActionFormWithAllSupporterFields(
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
                "777-777-7777",
                "777-777-7777",
                "UA",
                "09/11/2017").
                clickOnSubmitFormButton();
        targetedActionPage.verifySubmittedSupporterFieldsArePresentInSupporterDetails(SeleneseTestCase.USED_ENVIRONMENT.getBaseTestUrl(), login, password);
    }

    /**
     * <b>Create and submit Targeted Action form with all supporter required fields</b>
     * <p>
     * Steps:
     * <ul>
     * <li> Login > Open Activities page > Open Targeted Action forms page > Click on Create Targeted Action form
     * <li> Specify form name and description > Pick up target > Configure Email channel message > Proceed to Social Promotion tab
     * <li> Proceed to Select layout tab > Select Blank layout > Drop One-column row element > Drop Multi-step form element
     * <li> Drop all supporter fields on the form and mark them as required > Go to Action page wizard step > Drop One-column row element > Drop Targeted Messages form element
     * <li> Go to Autoresponders > Publish form > Go to form public URL
     * <li> Click on Submit button
     * <li> <font color="green"><b>Verify whether validation messages appears and prompts to fill in required fields</b></font>
     */

    @Parameters({"login", "password"})
    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class)
    public void testCreatePublishSubmitTargetedActionsFormRequiredEmptySupporterFields(String login, String password) {
        widgetName = "TargetedActionForm_" + RandomStringUtils.randomAlphanumeric(9);
        supporterEmail = RandomStringUtils.randomAlphanumeric(7)+"ta@test.com";
        targetedActionPage = new LoginPage().doSuccessLogin(login, password)
                .openActivitiesPage()
                .openTargetedActionsPage()
                .openAddTargetedActionPage()
                .fillFieldsWidgetStepOne(widgetName,"TADescription")
                .pickTargetAndEnableChannel("brad james", AddTargetedActionPage.Channel.EMAIL)
                .editMessage(1)
                .clickOnNextButtonInTargetsTab()
                .clickOnNextButtonInSocialPromotionTab();
        targetedActionPage.selectLayoutStep("Blank")
                .dropOneColumnRow();
        targetedActionPage.dropMultiStepFormElement();
        new FormFieldConfigurationModalWindow().dropAllSupporterFieldsOnFormAndMarkAsRequired();
        targetedActionPage.goToActionPageWizardStep()
                .dropOneColumnRow();
        targetedActionPage.dropTargetedMessagesFormElement()
                .goToAutorespondersTab()
                .publishFromAutoresponders()
                .openSubscribeWidget();

        TargetedActionPublicForm taf = new TargetedActionPublicForm();
                taf.clickOnSubmitFormButton();
                taf.verifyValidationMessageFieldRequireValueDisplayedForEmptySupporterFields();
    }

    /**
     * <b>Create and submit Targeted Action form with all custom non-required fields</b>
     * <p>
     * Steps:
     * <ul>
     * <li> Pre-create custom fields of all types (if not created already). This includes all supporter custom fields and all activity Targeted Action form-specific custom fields
     * <li> Login > Open Activities page > Open Targeted Action forms page > Click on Create Targeted Action form
     * <li> Specify form name and description > Pick up target > Configure Email channel message > Proceed to Social Promotion tab
     * <li> Proceed to Select layout tab > Select Blank layout > Drop One-column row element > Drop Multi-step form element
     * <li> Drop pre-created custom fields on form > Go to Action page wizard step > Drop One-column row element > Drop Targeted Messages form element
     * <li> Go to Autoresponders > Publish form > Go to form public URL
     * <li> Fill in all fields > Submit the form
     * <li> <font color="green"><b>Verify whether created supporter contains all fields values that have been specified when submitting the form</b></font>
     */

    @Parameters({"login", "password"})
    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class)
    public void testCreatePublishSubmitTargetedActionsFormNonRequiredCustomFields(String login, String password) {
        widgetName = "TargetedActionForm_" + RandomStringUtils.randomAlphanumeric(9);
        supporterEmail = RandomStringUtils.randomAlphanumeric(7)+"ta@test.com";
        targetedActionPage = new LoginPage().doSuccessLogin(login, password)
                .openActivitiesPage()
                .openTargetedActionsPage()
                .openAddTargetedActionPage()
                .fillFieldsWidgetStepOne(widgetName,"TADescription")
                .pickTargetAndEnableChannel("brad james", AddTargetedActionPage.Channel.EMAIL)
                .editMessage(1)
                .clickOnNextButtonInTargetsTab()
                .clickOnNextButtonInSocialPromotionTab();
        targetedActionPage.selectLayoutStep("Blank")
                .dropOneColumnRow();
        targetedActionPage.dropMultiStepFormElement();
        formFieldConfigurationModal = new FormFieldConfigurationModalWindow();
        formFieldConfigurationModal.dropFormFieldByName("targetedActionActivityTextBoxCustomField").saveFormFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("targetedActionActivityNumberCustomField").saveFormFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("supporterSingleChoiceCustomField").saveFormFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("supporterYesNoCustomField").saveFormFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("supporterDateTimeCustomField").saveFormFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("targetedActionActivitySingleChoiceCustomField").saveFormFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("targetedActionActivityYesNoCustomField").saveFormFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("targetedActionActivityDateTimeCustomField").saveFormFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("supporterTextBoxCustomField").saveFormFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("supporterNumberCustomField").saveFormFieldConfiguration();
        targetedActionPage.goToActionPageWizardStep()
                .dropOneColumnRow();
        targetedActionPage.dropTargetedMessagesFormElement()
                .goToAutorespondersTab()
                .publishFromAutoresponders()
                .openSubscribeWidget();

        new TargetedActionPublicForm().fillTargetedActionFormWithAllCustomFields(
                supporterEmail,
                "personFName",
                "personLName",
                "supporterTextBoxCustomFieldValue",
                "13",
                "10/11/2017 01:30 am",
                "activityTextBoxCustomFieldValue",
                "13",
                "10/11/2017 01:30 am",
                "10753 blix",
                "CA",
                "91602").
                clickOnSubmitFormButton();
        targetedActionPage.verifySubmittedCustomFieldsArePresentInSupporterDetails(SeleneseTestCase.USED_ENVIRONMENT.getBaseTestUrl(), login, password);
    }

    /**
     * <b>Create and submit Targeted Action form with all custom required fields</b>
     * <p>
     * Steps:
     * <ul>
     * <li> Pre-create custom fields of all types (if not created already). This includes all supporter custom fields and all activity Targeted Action form-specific custom fields
     * <li> Login > Open Activities page > Open Targeted Action forms page > Click on Create Petition form
     * <li> Specify form name and description > Select Blank layout > Drop One-column row element > Drop VE form element
     * <li> Drop on the layout all available pre-created custom fields and mark them as required
     * <li> Go to Autoresponders > Publish form > Go to form public URL
     * <li> Click on Submit button
     * <li> <font color="green"><b>Verify whether validation messages appears and prompts to fill in required fields</b></font>
     */

    @Parameters({"login", "password"})
    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class)
    public void testCreatePublishSubmitTargetedActionsFormRequiredEmptyCustomFields(String login, String password) {
        widgetName = "TargetedActionForm_" + RandomStringUtils.randomAlphanumeric(9);
        supporterEmail = RandomStringUtils.randomAlphanumeric(7)+"ta@test.com";
        targetedActionPage = new LoginPage().doSuccessLogin(login, password)
                .openActivitiesPage()
                .openTargetedActionsPage()
                .openAddTargetedActionPage()
                .fillFieldsWidgetStepOne(widgetName,"TADescription")
                .pickTargetAndEnableChannel("brad james", AddTargetedActionPage.Channel.EMAIL)
                .editMessage(1)
                .clickOnNextButtonInTargetsTab()
                .clickOnNextButtonInSocialPromotionTab();
        targetedActionPage.selectLayoutStep("Blank")
                .dropOneColumnRow();
        targetedActionPage.dropMultiStepFormElement();
        formFieldConfigurationModal = new FormFieldConfigurationModalWindow();
        formFieldConfigurationModal.dropFormFieldByName("targetedActionActivityTextBoxCustomField").markFieldAsRequired().saveFormFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("targetedActionActivityNumberCustomField").markFieldAsRequired().saveFormFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("supporterSingleChoiceCustomField").markFieldAsRequired().saveFormFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("supporterYesNoCustomField").markFieldAsRequired().saveFormFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("supporterDateTimeCustomField").markFieldAsRequired().saveFormFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("targetedActionActivitySingleChoiceCustomField").markFieldAsRequired().saveFormFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("targetedActionActivityYesNoCustomField").markFieldAsRequired().saveFormFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("targetedActionActivityDateTimeCustomField").markFieldAsRequired().saveFormFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("supporterTextBoxCustomField").markFieldAsRequired().saveFormFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("supporterNumberCustomField").markFieldAsRequired().saveFormFieldConfiguration();
        targetedActionPage.goToActionPageWizardStep()
                .dropOneColumnRow();
        targetedActionPage.dropTargetedMessagesFormElement()
                .goToAutorespondersTab()
                .publishFromAutoresponders()
                .openSubscribeWidget();

        TargetedActionPublicForm taf = new TargetedActionPublicForm();
        taf.clickOnSubmitFormButton();
        taf.verifyValidationMessageFieldRequireValueDisplayedForEmptyCustomFields();
    }
}