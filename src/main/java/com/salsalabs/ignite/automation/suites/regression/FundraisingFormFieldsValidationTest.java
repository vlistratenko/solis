package com.salsalabs.ignite.automation.suites.regression;

import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.HttpClient;
import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;
import com.salsalabs.ignite.automation.pages.hq.activities.*;
import com.salsalabs.ignite.automation.pages.hq.manage.CustomFieldsPage;
import com.salsalabs.ignite.automation.pages.hq.manage.PaymentGatewaysPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.ITestContext;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Test(groups = {"fundraisingFormFieldsValidation"})
public class FundraisingFormFieldsValidationTest extends SeleneseTestCase {

        private AddDonationWidgetPage addDonationPage;
        private FormFieldConfigurationModalWindow formFieldConfigurationModal;
        private PaymentGatewaysPage paymentGatewaysPage;
        private String widgetName;
        private String widgetDescription;
        private String supporterEmail;
        private String gatewayName;

        @Parameters({"login","password"})
        @BeforeGroups(groups = {"fundraisingFormFieldsValidation"})
        public void generateCustomFieldsViaAPI(ITestContext context, String login, String password){
            paymentGatewaysPage = new PaymentGatewaysPage();
            gatewayName = "CardConnectPaymentGateway_" + RandomStringUtils.randomAlphanumeric(5);
            CommonUtils.setProperty("gatewayName", gatewayName);
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
                    getCustomFieldApiGenerator("fundraisingActivityDateTimeCustomField", "FieldDescription").
                    setControlType("DATETIME"));

            CustomFieldsPage.CustomField activityNumberCustomFieldConfig = CustomFieldsPage.createCustomField(CustomFieldsPage.
                    getCustomFieldApiGenerator("fundraisingActivityNumberCustomField", "FieldDescription").
                    setControlType("INPUT"));

            CustomFieldsPage.CustomField activitySingleChoiceCustomFieldConfig = CustomFieldsPage.createCustomField(CustomFieldsPage.
                    getCustomFieldApiGenerator("fundraisingActivitySingleChoiceCustomField", "FieldDescription").
                    setControlType("RADIO").
                    setSingleChoiceFieldValueLabelsApi("value1", "value2").
                    setDefaultValue("value1"));

            CustomFieldsPage.CustomField activityYesNoCustomFieldConfig = CustomFieldsPage.createCustomField(CustomFieldsPage.
                    getCustomFieldApiGenerator("fundraisingActivityYesNoCustomField", "FieldDescription").
                    setYesNoFieldControlOrientationApi("VERTICAL").
                    setDefaultValue("true").
                    setYesNoFieldValueLabelsApi("truefalse"));

            CustomFieldsPage.CustomField activityTextBoxCustomFieldConfig = CustomFieldsPage.createCustomField(CustomFieldsPage.
                    getCustomFieldApiGenerator("fundraisingActivityTextBoxCustomField", "FieldDescription").
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
                    httpClient.createCustomField(activityTextBoxCustomFieldConfig.createActivityCustomFieldViaApiJsonObject((CustomFieldsPage.CustomFieldType.TextBox),"FUNDRAISE"));
                    httpClient.createCustomField(activityNumberCustomFieldConfig.createActivityCustomFieldViaApiJsonObject((CustomFieldsPage.CustomFieldType.Number),"FUNDRAISE"));
                    httpClient.createCustomField(activityYesNoCustomFieldConfig.createActivityCustomFieldViaApiJsonObject((CustomFieldsPage.CustomFieldType.YesNo),"FUNDRAISE"));
                    httpClient.createCustomField(activitySingleChoiceCustomFieldConfig.createActivityCustomFieldViaApiJsonObject((CustomFieldsPage.CustomFieldType.SingleChoice),"FUNDRAISE"));
                    httpClient.createCustomField(activityDateTimeCustomFieldConfig.createActivityCustomFieldViaApiJsonObject((CustomFieldsPage.CustomFieldType.DateTime),"FUNDRAISE"));
                    httpClient.createGateway(paymentGatewaysPage.getCardConnectPaymentGatewayJSONObject(gatewayName));
  
            
        }

        /**
         * <b>Create and submit Fundraising form with all supporter non-required fields</b>
         * <p>
         * Steps:
         * <ul>
         * <li> Pre-create custom fields of all types (if not created already). This includes all supporter custom fields and all activity Fundraising form-specific custom fields
         * <li> Login > Open Activities page > Open Fundraising forms page > Click on Create Fundraising form
         * <li> Specify form name and description > Select Blank layout > Drop One-column row element > Drop VE form element
         * <li> Drop on the layout all available supporter fields
         * <li> Go to Autoresponders > Publish form > Go to form public URL
         * <li> Fill in all fields > Submit the form
         * <li> <font color="green"><b>Verify whether created supporter contains all fields values that have been specified when submitting the form</b></font>
         */

        @Parameters({"login","password"})
        @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class)
        public void testCreatePublishSubmitFundraisingFormSupporterNonRequiredFields(String login, String password){
            widgetName = "FundraisingFormtName_" + RandomStringUtils.randomAlphanumeric(5);
            widgetDescription = "FundraisingFormDescription_" + RandomStringUtils.randomAlphanumeric(10);
            supporterEmail = "autosupporter" + RandomStringUtils.randomAlphanumeric(4)+"@test.com";
            addDonationPage = new LoginPage()
                    .doSuccessLogin(login, password)
                    .openActivitiesPage()
                    .openFundraisingWidgetPage()
                    .openAddDonationWidgetPage();
            addDonationPage.fillFieldsWidgetStepOne(widgetName, widgetDescription)
                    .selectLayoutStep("Blank");
            addDonationPage.selectGatewayByName(CommonUtils.getProperty("gatewayName"));
            addDonationPage.dropOneColumnRow();
            addDonationPage.dropVEFormElement();
            new FormFieldConfigurationModalWindow().dropAllSupporterFieldsOnForm();
            addDonationPage.goToAutorespondersTab();
            addDonationPage.publishFromAutoresponders();
            addDonationPage.openSubscribeWidget();
            DonationWidget fundraisingForm1 = new DonationWidget();
            fundraisingForm1.fillDonationWidgetAllSupporterFields(
                    "4111111111111111","123","11","2023","donationTest donationTest",supporterEmail,"FirstName","LastName",
                    "City","91602","UA-63","address1","address2","Male","777-777-7777","MidName","en-US",
                    "suffix","title","777-777-7777","777-777-7777","UA","09/11/2017","5").
                    clickOnSubmitFormButton();
            addDonationPage.verifySubmittedSupporterFieldsArePresentInSupporterDetails(SeleneseTestCase.USED_ENVIRONMENT.getBaseTestUrl(), login, password);
        }

        /**
         * <b>Create and submit Fundraising form with all custom non-required fields</b>
         * <p>
         * Steps:
         * <ul>
         * <li> Pre-create custom fields of all types (if not created already). This includes all supporter custom fields and all activity Fundraising form-specific custom fields
         * <li> Login > Open Activities page > Open Fundraising forms page > Click on Create Fundraising form
         * <li> Specify form name and description > Select Blank layout > Drop One-column row element > Drop VE form element
         * <li> Drop on the layout all available pre-created custom fields
         * <li> Go to Autoresponders > Publish form > Go to form public URL
         * <li> Fill in all fields > Submit the form
         * <li> <font color="green"><b>Verify whether created supporter contains all fields values that have been specified when submitting the form</b></font>
         */

        @Parameters({"login","password"})
        @Test(enabled=true, retryAnalyzer = RetryAnalyzer.class)
        public void testCreatePublishSubmitFundraisingFormCustomNonRequiredFields(String login, String password){
            widgetName = "FundraisingFormtName_" + RandomStringUtils.randomAlphanumeric(5);
            widgetDescription = "FundraisingFormDescription_" + RandomStringUtils.randomAlphanumeric(10);
            supporterEmail = "autosupporter" + RandomStringUtils.randomAlphanumeric(4)+"@test.com";
            addDonationPage = new LoginPage()
                    .doSuccessLogin(login, password)
                    .openActivitiesPage()
                    .openFundraisingWidgetPage()
                    .openAddDonationWidgetPage();
            addDonationPage.fillFieldsWidgetStepOne(widgetName, widgetDescription)
                    .selectLayoutStep("Blank");
            addDonationPage.selectGatewayByName(CommonUtils.getProperty("gatewayName"));
            addDonationPage.dropOneColumnRow();
            addDonationPage.dropVEFormElement();
            formFieldConfigurationModal = new FormFieldConfigurationModalWindow();
            formFieldConfigurationModal.dropFormFieldByName("fundraisingActivityTextBoxCustomField").saveFormFieldConfiguration();
            formFieldConfigurationModal.dropFormFieldByName("fundraisingActivityNumberCustomField").saveFormFieldConfiguration();
            formFieldConfigurationModal.dropFormFieldByName("supporterSingleChoiceCustomField").saveFormFieldConfiguration();
            formFieldConfigurationModal.dropFormFieldByName("supporterYesNoCustomField").saveFormFieldConfiguration();
            formFieldConfigurationModal.dropFormFieldByName("supporterDateTimeCustomField").saveFormFieldConfiguration();
            formFieldConfigurationModal.dropFormFieldByName("fundraisingActivitySingleChoiceCustomField").saveFormFieldConfiguration();
            formFieldConfigurationModal.dropFormFieldByName("fundraisingActivityYesNoCustomField").saveFormFieldConfiguration();
            formFieldConfigurationModal.dropFormFieldByName("fundraisingActivityDateTimeCustomField").saveFormFieldConfiguration();
            formFieldConfigurationModal.dropFormFieldByName("supporterTextBoxCustomField").saveFormFieldConfiguration();
            formFieldConfigurationModal.dropFormFieldByName("supporterNumberCustomField").saveFormFieldConfiguration();
            addDonationPage.goToAutorespondersTab();
            addDonationPage.publishFromAutoresponders();
            addDonationPage.openSubscribeWidget();
            DonationWidget fundraisingForm2 = new DonationWidget();
            fundraisingForm2.fillDonationWidgetAllCustomFields(
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
                    "2023").
                    clickOnSubmitFormButton();
            addDonationPage.verifySubmittedCustomFieldsArePresentInSupporterDetails(SeleneseTestCase.USED_ENVIRONMENT.getBaseTestUrl(),login,password);
        }

        /**
         * <b>Create and submit Fundraising form with all supporter required fields</b>
         * <p>
         * Steps:
         * <ul>
         * <li> Login > Open Activities page > Open Fundraising forms page > Click on Create Fundraising form
         * <li> Specify form name and description > Select Blank layout > Drop One-column row element > Drop VE form element
         * <li> Drop on the layout all available supporter fields and mark them as required
         * <li> Go to Autoresponders > Publish form > Go to form public URL
         * <li> Click on Submit button
         * <li> <font color="green"><b>Verify whether validation messages appears and prompts to fill in required fields</b></font>
         */

        @Parameters({"login","password"})
        @Test(enabled=true, retryAnalyzer = RetryAnalyzer.class)
        public void testCreatePublishSubmitFundraisingFormRequiredEmptySupporterFields(String login, String password){
            widgetName = "FundraisingFormtName_" + RandomStringUtils.randomAlphanumeric(5);
            widgetDescription = "FundraisingFormDescription_" + RandomStringUtils.randomAlphanumeric(10);
            supporterEmail = "autosupporter" + RandomStringUtils.randomAlphanumeric(4)+"@test.com";
            addDonationPage = new LoginPage()
                    .doSuccessLogin(login, password)
                    .openActivitiesPage()
                    .openFundraisingWidgetPage()
                    .openAddDonationWidgetPage();
            addDonationPage.fillFieldsWidgetStepOne(widgetName, widgetDescription)
                    .selectLayoutStep("Blank");
            addDonationPage.selectGatewayByName(CommonUtils.getProperty("gatewayName"));
            addDonationPage.dropOneColumnRow();
            addDonationPage.dropVEFormElement();
            new FormFieldConfigurationModalWindow().dropAllSupporterFieldsOnFormAndMarkAsRequired();
            addDonationPage.editVEField("Phone").markFieldAsRequired().saveFormFieldConfiguration();
            addDonationPage.goToAutorespondersTab();
            addDonationPage.publishFromAutoresponders();
            addDonationPage.openSubscribeWidget();
            DonationWidget fundraisingForm3 = new DonationWidget();
            fundraisingForm3.clickOnSubmitFormButton();
            fundraisingForm3.verifyValidationMessageFieldRequireValueDisplayedForEmptySupporterFieldsAndDonation();
        }

        /**
         * <b>Create and submit Fundraising form with all custom required fields</b>
         * <p>
         * Steps:
         * <ul>
         * <li> Pre-create custom fields of all types (if not created already). This includes all supporter custom fields and all activity Fundraising form-specific custom fields
         * <li> Login > Open Activities page > Open Fundraising forms page > Click on Create Fundraising form
         * <li> Specify form name and description > Select Blank layout > Drop One-column row element > Drop VE form element
         * <li> Drop on the layout all available pre-created custom fields and mark them as required
         * <li> Go to Autoresponders > Publish form > Go to form public URL
         * <li> Click on Submit button
         * <li> <font color="green"><b>Verify whether validation messages appears and prompts to fill in required fields</b></font>
         */

        @Parameters({"login","password"})
        @Test(enabled=true, retryAnalyzer = RetryAnalyzer.class)
        public void testCreatePublishSubmitFundraisingFormRequiredEmptyCustomFields(String login, String password){
            widgetName = "FundraisingFormtName_" + RandomStringUtils.randomAlphanumeric(5);
            widgetDescription = "FundraisingFormDescription_" + RandomStringUtils.randomAlphanumeric(10);
            supporterEmail = "autosupporter" + RandomStringUtils.randomAlphanumeric(4)+"@test.com";
            addDonationPage = new LoginPage()
                    .doSuccessLogin(login, password)
                    .openActivitiesPage()
                    .openFundraisingWidgetPage()
                    .openAddDonationWidgetPage();
            addDonationPage.fillFieldsWidgetStepOne(widgetName, widgetDescription)
                    .selectLayoutStep("Blank");
            addDonationPage.selectGatewayByName(CommonUtils.getProperty("gatewayName"));
            addDonationPage.dropOneColumnRow();
            addDonationPage.dropVEFormElement();
            formFieldConfigurationModal = new FormFieldConfigurationModalWindow();
            formFieldConfigurationModal.dropFormFieldByName("fundraisingActivityTextBoxCustomField").markFieldAsRequired().saveFormFieldConfiguration();
            formFieldConfigurationModal.dropFormFieldByName("fundraisingActivityNumberCustomField").markFieldAsRequired().saveFormFieldConfiguration();
            formFieldConfigurationModal.dropFormFieldByName("supporterSingleChoiceCustomField").saveFormFieldConfiguration();
            formFieldConfigurationModal.dropFormFieldByName("supporterYesNoCustomField").saveFormFieldConfiguration();
            formFieldConfigurationModal.dropFormFieldByName("supporterDateTimeCustomField").markFieldAsRequired().saveFormFieldConfiguration();
            formFieldConfigurationModal.dropFormFieldByName("fundraisingActivitySingleChoiceCustomField").saveFormFieldConfiguration();
            formFieldConfigurationModal.dropFormFieldByName("fundraisingActivityYesNoCustomField").saveFormFieldConfiguration();
            formFieldConfigurationModal.dropFormFieldByName("fundraisingActivityDateTimeCustomField").markFieldAsRequired().saveFormFieldConfiguration();
            formFieldConfigurationModal.dropFormFieldByName("supporterTextBoxCustomField").markFieldAsRequired().saveFormFieldConfiguration();
            formFieldConfigurationModal.dropFormFieldByName("supporterNumberCustomField").markFieldAsRequired().saveFormFieldConfiguration();
            addDonationPage.goToAutorespondersTab();
            addDonationPage.publishFromAutoresponders();
            addDonationPage.openSubscribeWidget();
            SubscribeWidget fundraisingForm4 = new SubscribeWidget();
            fundraisingForm4.clickOnSubmitFormButton().
                    verifyValidationMessageFieldRequireValueDisplayedForEmptyCustomFields();
        }
    }


