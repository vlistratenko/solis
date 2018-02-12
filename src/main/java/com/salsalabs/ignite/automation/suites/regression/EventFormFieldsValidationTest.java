package com.salsalabs.ignite.automation.suites.regression;

import com.salsalabs.ignite.automation.common.HttpClient;
import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;
import com.salsalabs.ignite.automation.pages.hq.activities.*;
import com.salsalabs.ignite.automation.pages.hq.activities.event.AddEventPageComposeTabCheckout;
import com.salsalabs.ignite.automation.pages.hq.activities.event.AddEventPageComposeTabRegistration;
import com.salsalabs.ignite.automation.pages.hq.manage.CustomFieldsPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.ITestContext;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Test(groups = {"eventFormFieldsValidation"})
public class EventFormFieldsValidationTest extends SeleneseTestCase {

    private String widgetName;
    private String supporterEmail;
    private FormFieldConfigurationModalWindow formFieldConfigurationModal;

    @Parameters({"login","password"})
    @BeforeGroups(groups = {"eventFormFieldsValidation"})
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
                getCustomFieldApiGenerator("eventActivityDateTimeCustomField", "FieldDescription").
                setControlType("DATETIME"));

        CustomFieldsPage.CustomField activityNumberCustomFieldConfig = CustomFieldsPage.createCustomField(CustomFieldsPage.
                getCustomFieldApiGenerator("eventActivityNumberCustomField", "FieldDescription").
                setControlType("INPUT"));

        CustomFieldsPage.CustomField activitySingleChoiceCustomFieldConfig = CustomFieldsPage.createCustomField(CustomFieldsPage.
                getCustomFieldApiGenerator("eventActivitySingleChoiceCustomField", "FieldDescription").
                setControlType("RADIO").
                setSingleChoiceFieldValueLabelsApi("value1", "value2").
                setDefaultValue("value1"));

        CustomFieldsPage.CustomField activityYesNoCustomFieldConfig = CustomFieldsPage.createCustomField(CustomFieldsPage.
                getCustomFieldApiGenerator("eventActivityYesNoCustomField", "FieldDescription").
                setYesNoFieldControlOrientationApi("VERTICAL").
                setDefaultValue("true").
                setYesNoFieldValueLabelsApi("truefalse"));

        CustomFieldsPage.CustomField activityTextBoxCustomFieldConfig = CustomFieldsPage.createCustomField(CustomFieldsPage.
                getCustomFieldApiGenerator("eventActivityTextBoxCustomField", "FieldDescription").
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
        httpClient.createCustomField(activityTextBoxCustomFieldConfig.createActivityCustomFieldViaApiJsonObject((CustomFieldsPage.CustomFieldType.TextBox),"TICKETED_EVENT"));
        httpClient.createCustomField(activityNumberCustomFieldConfig.createActivityCustomFieldViaApiJsonObject((CustomFieldsPage.CustomFieldType.Number),"TICKETED_EVENT"));
        httpClient.createCustomField(activityYesNoCustomFieldConfig.createActivityCustomFieldViaApiJsonObject((CustomFieldsPage.CustomFieldType.YesNo),"TICKETED_EVENT"));
        httpClient.createCustomField(activitySingleChoiceCustomFieldConfig.createActivityCustomFieldViaApiJsonObject((CustomFieldsPage.CustomFieldType.SingleChoice),"TICKETED_EVENT"));
        httpClient.createCustomField(activityDateTimeCustomFieldConfig.createActivityCustomFieldViaApiJsonObject((CustomFieldsPage.CustomFieldType.DateTime),"TICKETED_EVENT"));
    }

    /**
     * <b>Create and submit Event form with all supporter required fields</b>
     * <p>
     * Steps:
     * <ul>
     * <li> Login > Open Activities page > Open Event forms page > Click on Create Event form
     * <li> Specify form name, publicly visible name and description > Specify Ticket name > Save
     * <li> Select Blank layout > Drop One-column row element > Drop Donate and Register buttons > Go to Registration tab
     * <li> Drop One-column row element > Drop Registration element > Go to Checkout tab
     * <li> Drop One-column row element > Drop VE Form element > Drop all supporter fields and mark them as required
     * <li> Go to Autoresponders > Publish form > Go to form public URL
     * <li> Click on "I can't make it but I'd like to donate" button > Click on Submit! button
     * <li> <font color="green"><b>Verify whether validation messages appears and prompts to fill in required fields</b></font>
     */

    @Parameters({"login", "password"})
    @Test(enabled = false, retryAnalyzer = RetryAnalyzer.class)
    public void testCreatePublishSubmitEventFormRequiredEmptySupporterFields(String login, String password) {
        widgetName = "EventForm_" + RandomStringUtils.randomAlphanumeric(5);
        EventWidget eventForm = new EventWidget();
        AddEventPageComposeTabCheckout composeTab = new AddEventPageComposeTabCheckout();
        new LoginPage().doSuccessLogin(login, password)
                .openActivitiesPage()
                .openEventsPage()
                .clickCreateAnEventButton()
                .specifyEventReferenceName(widgetName)
                .specifyEventPublicilyVisibleName("EventFormPublicName_" + RandomStringUtils.randomAlphanumeric(5))
                .clickNextButtonInSetupTab()
                .specifyTicketName("TicketName_" + RandomStringUtils.randomAlphanumeric(5))
                .clickSaveTicketInfoButton()
                .clickContinueButton()
                .clickNextButtonInTicketsTab()
                .selectLayout("Blank")
                .clickNextButtonInSelectLayoutTab()
                .dropOneColumnRow()
                .dropDonateButton()
                .dropRegisterButton()
                .goToRegistrationWizardStep();
        new AddEventPageComposeTabRegistration()
                .dropOneColumnRow()
                .dropRegistration()
                .goToCheckoutWizardStep();
        composeTab.dropOneColumnRow()
                .dropVEFormElement();
        composeTab.editVEField("Phone").markFieldAsRequired().saveFieldConfiguration();
        new FormFieldConfigurationModalWindow().dropAllSupporterFieldsOnFormAndMarkAsRequired();
        new AddEventPageComposeTabCheckout()
                .clickNextButtonInComposeTab()
                .clickPublishOnAutorespondersTab()
                .openEventWidget(widgetName);
        eventForm.openDonationPage().clickSubmitButton()
                .verifyValidationMessageFieldRequireValueDisplayedForEmptySupporterFields();
    }

    /**
     * <b>Create and submit Event form with all supporter non-required fields</b>
     * <p>
     * Steps:
     * <ul>
     * <li> Login > Open Activities page > Open Event forms page > Click on Create Event form
     * <li> Specify form name, publicly visible name and description > Specify Ticket name > Save
     * <li> Select Blank layout > Drop One-column row element > Drop Donate and Register buttons > Go to Registration tab
     * <li> Drop One-column row element > Drop Registration element > Go to Checkout tab
     * <li> Drop One-column row element > Drop VE Form element > Drop all supporter fields on the layout
     * <li> Go to Autoresponders > Publish form > Go to form public URL
     * <li> Click on "I can't make it but I'd like to donate" button > Fill in all fields > Click on Submit! button
     * <li> <font color="green"><b>Verify whether created supporter contains all fields values that have been specified when submitting the form</b></font>
     */

    @Parameters({"login", "password"})
    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class)
    public void testCreatePublishSubmitEventFormSupporterNonRequiredFields(String login, String password) {
        widgetName = "EventForm_" + RandomStringUtils.randomAlphanumeric(5);
        supporterEmail = "autosupporter" + RandomStringUtils.randomAlphanumeric(4) + "@test.com";
        EventWidget eventForm = new EventWidget();
        new LoginPage().doSuccessLogin(login, password)
                .openActivitiesPage()
                .openEventsPage()
                .clickCreateAnEventButton()
                .specifyEventReferenceName(widgetName)
                .specifyEventPublicilyVisibleName("EventFormPublicName_" + RandomStringUtils.randomAlphanumeric(5))
                .clickNextButtonInSetupTab()
                .specifyTicketName("TicketName_" + RandomStringUtils.randomAlphanumeric(5))
                .clickSaveTicketInfoButton()
                .clickContinueButton()
                .clickNextButtonInTicketsTab()
                .selectLayout("Blank")
                .clickNextButtonInSelectLayoutTab()
                .dropOneColumnRow()
                .dropDonateButton()
                .dropRegisterButton()
                .goToRegistrationWizardStep();
        new AddEventPageComposeTabRegistration()
                .dropOneColumnRow()
                .dropRegistration()
                .goToCheckoutWizardStep();
        new AddEventPageComposeTabCheckout()
                .dropOneColumnRow()
                .dropVEFormElement();
        new FormFieldConfigurationModalWindow().dropAllSupporterFieldsOnForm();
        new AddEventPageComposeTabCheckout()
                .clickNextButtonInComposeTab()
                .clickPublishOnAutorespondersTab()
                .openEventWidget(widgetName);
        eventForm.openDonationPage();
        eventForm.fillDonationWidgetAllSupporterFields(
                "4111111111111111", "123", "11", "2023", "donationTest donationTest", supporterEmail, "FirstName", "LastName",
                "City", "91602", "UA-63", "address1", "address2", "Male", "777-777-7777", "MidName", "en-US",
                "suffix", "title", "777-777-7777", "777-777-7777", "UA", "09/11/2017", "5");
        eventForm.clickSubmitButton();
        new AddSubscribeWidgetPage()
                .verifySubmittedSupporterFieldsArePresentInSupporterDetails(SeleneseTestCase.USED_ENVIRONMENT.getBaseTestUrl(), login, password);
    }

    /**
     * <b>Create and submit Event form with all custom non-required fields</b>
     * <p>
     * Steps:
     * <ul>
     * <li> Login > Open Activities page > Open Event forms page > Click on Create Event form
     * <li> Specify form name, publicly visible name and description > Specify Ticket name > Save
     * <li> Select Blank layout > Drop One-column row element > Drop Donate and Register buttons > Go to Registration tab
     * <li> Drop One-column row element > Drop Registration element > Go to Checkout tab
     * <li> Drop One-column row element > Drop VE Form element > Drop on the layout all available pre-created custom fields
     * <li> Go to Autoresponders > Publish form > Go to form public URL
     * <li> Click on "I can't make it but I'd like to donate" button > Fill in all fields > Click on Submit! button
     * <li> <font color="green"><b>Verify whether created supporter contains all fields values that have been specified when submitting the form</b></font>
     */

    @Parameters({"login", "password"})
    @Test(enabled = false, retryAnalyzer = RetryAnalyzer.class)
    public void testCreatePublishSubmitEventFormCustomNonRequiredFields(String login, String password) {
        widgetName = "EventForm_" + RandomStringUtils.randomAlphanumeric(5);
        supporterEmail = "autosupporter" + RandomStringUtils.randomAlphanumeric(4) + "@test.com";
        formFieldConfigurationModal = new FormFieldConfigurationModalWindow();
        EventWidget eventForm = new EventWidget();
        new LoginPage().doSuccessLogin(login, password)
                .openActivitiesPage()
                .openEventsPage()
                .clickCreateAnEventButton()
                .specifyEventReferenceName(widgetName)
                .specifyEventPublicilyVisibleName("EventFormPublicName_" + RandomStringUtils.randomAlphanumeric(5))
                .clickNextButtonInSetupTab()
                .specifyTicketName("TicketName_" + RandomStringUtils.randomAlphanumeric(5))
                .clickSaveTicketInfoButton()
                .clickContinueButton()
                .clickNextButtonInTicketsTab()
                .selectLayout("Blank")
                .clickNextButtonInSelectLayoutTab()
                .dropOneColumnRow()
                .dropDonateButton()
                .dropRegisterButton()
                .goToRegistrationWizardStep();
        new AddEventPageComposeTabRegistration()
                .dropOneColumnRow()
                .dropRegistration()
                .goToCheckoutWizardStep();
        new AddEventPageComposeTabCheckout()
                .dropOneColumnRow()
                .dropVEFormElement();
        formFieldConfigurationModal.dropFormFieldByName("eventActivityTextBoxCustomField").saveFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("eventActivityNumberCustomField").saveFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("supporterSingleChoiceCustomField").saveFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("supporterYesNoCustomField").saveFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("supporterDateTimeCustomField").saveFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("eventActivitySingleChoiceCustomField").saveFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("eventActivityYesNoCustomField").saveFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("eventActivityDateTimeCustomField").saveFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("supporterTextBoxCustomField").saveFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("supporterNumberCustomField").saveFieldConfiguration();
        new AddEventPageComposeTabCheckout()
                .clickNextButtonInComposeTab()
                .clickPublishOnAutorespondersTab()
                .openEventWidget(widgetName);
        eventForm.openDonationPage();
        eventForm.fillDonationWidgetAllCustomFields(
                "supporterTextBoxCustomFieldValue",
                "13",
                "10/11/2017 01:30 am",
                "activityTextBoxCustomFieldValue",
                "13",
                "10/11/2017 01:30 am",
                supporterEmail,
                "personFName",
                "personLName",
                "address1",
                "city",
                "20009",
                "NY",
                "10",
                "name onCard",
                "4111111111111111",
                "123",
                "11",
                "2023")
                .clickOnSubmitFormButton();
        new AddSubscribeWidgetPage()
                .verifySubmittedCustomFieldsArePresentInSupporterDetails(SeleneseTestCase.USED_ENVIRONMENT.getBaseTestUrl(), login, password);
    }

    /**
     * <b>Create and submit Event form with all custom required fields</b>
     * <p>
     * Steps:
     * <ul>
     * <li> Login > Open Activities page > Open Event forms page > Click on Create Event form
     * <li> Specify form name, publicly visible name and description > Specify Ticket name > Save
     * <li> Select Blank layout > Drop One-column row element > Drop Donate and Register buttons > Go to Registration tab
     * <li> Drop One-column row element > Drop Registration element > Go to Checkout tab
     * <li> Drop One-column row element > Drop VE Form element > Drop on the layout all available pre-created custom fields and mark them as required
     * <li> Go to Autoresponders > Publish form > Go to form public URL
     * <li> Click on "I can't make it but I'd like to donate" button > Click on Submit! button
     * <li> <font color="green"><b>Verify whether validation messages appears and prompts to fill in required fields</b></font>
     */

    @Parameters({"login", "password"})
    @Test(enabled = false, retryAnalyzer = RetryAnalyzer.class)
    public void testCreatePublishSubmitEventFormCustomRequiredFields(String login, String password) {
        widgetName = "EventForm_" + RandomStringUtils.randomAlphanumeric(5);
        supporterEmail = "autosupporter" + RandomStringUtils.randomAlphanumeric(4) + "@test.com";
        formFieldConfigurationModal = new FormFieldConfigurationModalWindow();
        EventWidget eventForm = new EventWidget();
        new LoginPage().doSuccessLogin(login, password)
                .openActivitiesPage()
                .openEventsPage()
                .clickCreateAnEventButton()
                .specifyEventReferenceName(widgetName)
                .specifyEventPublicilyVisibleName("EventFormPublicName_" + RandomStringUtils.randomAlphanumeric(5))
                .clickNextButtonInSetupTab()
                .specifyTicketName("TicketName_" + RandomStringUtils.randomAlphanumeric(5))
                .clickSaveTicketInfoButton()
                .clickContinueButton()
                .clickNextButtonInTicketsTab()
                .selectLayout("Blank")
                .clickNextButtonInSelectLayoutTab()
                .dropOneColumnRow()
                .dropDonateButton()
                .dropRegisterButton()
                .goToRegistrationWizardStep();
        new AddEventPageComposeTabRegistration()
                .dropOneColumnRow()
                .dropRegistration()
                .goToCheckoutWizardStep();
        new AddEventPageComposeTabCheckout()
                .dropOneColumnRow()
                .dropVEFormElement();
        formFieldConfigurationModal.dropFormFieldByName("eventActivityTextBoxCustomField").markFieldAsRequired().saveFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("eventActivityNumberCustomField").markFieldAsRequired().saveFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("supporterSingleChoiceCustomField").saveFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("supporterYesNoCustomField").saveFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("supporterDateTimeCustomField").markFieldAsRequired().saveFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("eventActivitySingleChoiceCustomField").saveFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("eventActivityYesNoCustomField").saveFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("eventActivityDateTimeCustomField").markFieldAsRequired().saveFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("supporterTextBoxCustomField").markFieldAsRequired().saveFieldConfiguration();
        formFieldConfigurationModal.dropFormFieldByName("supporterNumberCustomField").markFieldAsRequired().saveFieldConfiguration();
        new AddEventPageComposeTabCheckout()
                .clickNextButtonInComposeTab()
                .clickPublishOnAutorespondersTab()
                .openEventWidget(widgetName);
        eventForm.openDonationPage().clickOnSubmitFormButton();
        new SubscribeWidget().verifyValidationMessageFieldRequireValueDisplayedForEmptyCustomFields();
    }
}