package com.salsalabs.ignite.automation.suites.regression;

import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.HttpClient;
import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.pages.hq.HomePage;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;
import com.salsalabs.ignite.automation.pages.hq.activities.ActivitiesPage;
import com.salsalabs.ignite.automation.pages.hq.activities.AddSubscribeWidgetPage;
import com.salsalabs.ignite.automation.pages.hq.activities.FormFieldConfigurationPage;
import com.salsalabs.ignite.automation.pages.hq.activities.SubscribeWidget;
import com.salsalabs.ignite.automation.pages.hq.manage.CustomFieldsPage;
import com.salsalabs.ignite.automation.pages.hq.supporters.SupportersAddPage;
import com.salsalabs.ignite.automation.pages.hq.supporters.SupportersPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class FieldsValidationTest extends SeleneseTestCase {

    private AddSubscribeWidgetPage addSignupFormsPage;
    private ActivitiesPage activitiesPage;

    @Parameters({"login","password","supporterTextBoxCustomFieldName","supporterNumberCustomFieldName","supporterSingleChoiceCustomFieldName","supporterYesNoCustomFieldName",
            "supporterDateTimeCustomFieldName","activityDateTimeCustomFieldName","activityNumberCustomFieldName", "activityYesNoCustomFieldName", "activitySingleChoiceCustomFieldName",
            "activityTextBoxCustomFieldName"})
    @Test(enabled = true, groups = {"val"}, retryAnalyzer = RetryAnalyzer.class)
    public void testCreateAndPublishSignupForm(String login, String password, String supporterTextBoxCustomFieldName, String supporterNumberCustomFieldName,
                                               String supporterSingleChoiceCustomFieldName, String supporterYesNoCustomFieldName, String supporterDateTimeCustomFieldName,
                                               String activityDateTimeCustomFieldName, String activityNumberCustomFieldName, String activityYesNoCustomFieldName,
                                               String activitySingleChoiceCustomFieldName, String activityTextBoxCustomFieldName){

        String widgetName = "SubscribeWidgetName_" + RandomStringUtils.randomAlphanumeric(5);
        String widgetDescription = "SubscribeWidgetDescription_" + RandomStringUtils.randomAlphanumeric(10);
        String supporterEmail = "autosupporter" + RandomStringUtils.randomAlphanumeric(4)+"@test.com";


        CustomFieldsPage.CustomField supporterDateTimeCustomFieldConfig = CustomFieldsPage.createCustomField(CustomFieldsPage.
                getCustomFieldApiGenerator(supporterDateTimeCustomFieldName, "FieldDescription").
                setControlType("DATETIME").
                setDateFieldMinDateApi("09/15/2017").
                setDateFieldMaxDateApi("09/15/2018").
                setDateFieldMinTimeApi("10:30pm").
                setDateFieldMaxTimeApi("11:30pm"));

        CustomFieldsPage.CustomField supporterTextBoxCustomFieldConfig = CustomFieldsPage.createCustomField(CustomFieldsPage.
                getCustomFieldApiGenerator(supporterTextBoxCustomFieldName, "FieldDescription").
                setControlType("INPUT").
                setGhostText(supporterTextBoxCustomFieldName).
                setTextFieldMinLengthValue(3).
                setTextFieldMaxLengthValue(200).
                setTextFieldValidationValue("ANY_CHARACTER"));

        CustomFieldsPage.CustomField supporterNumberCustomFieldConfig = CustomFieldsPage.createCustomField(CustomFieldsPage.
                getCustomFieldApiGenerator(supporterNumberCustomFieldName, "FieldDescription").
                setControlType("INPUT"));

        CustomFieldsPage.CustomField supporterSingleChoiceCustomFieldConfig = CustomFieldsPage.createCustomField(CustomFieldsPage.
                getCustomFieldApiGenerator(supporterSingleChoiceCustomFieldName, "FieldDescription").
                setControlType("RADIO").
                setSingleChoiceFieldValueLabelsApi("value1", "value2").
                setDefaultValue("value1"));

        CustomFieldsPage.CustomField supporterYesNoCustomFieldConfig = CustomFieldsPage.createCustomField(CustomFieldsPage.
                getCustomFieldApiGenerator(supporterYesNoCustomFieldName, "FieldDescription").
                setYesNoFieldControlOrientationApi("VERTICAL").
                setDefaultValue("true").
                setYesNoFieldValueLabelsApi("YesNo"));

        CustomFieldsPage.CustomField activityDateTimeCustomFieldConfig = CustomFieldsPage.createCustomField(CustomFieldsPage.
                getCustomFieldApiGenerator(activityDateTimeCustomFieldName, "FieldDescription").
                setControlType("DATETIME").
                setDateFieldMinDateApi("09/15/2017").
                setDateFieldMaxDateApi("09/15/2018").
                setDateFieldMinTimeApi("10:30pm").
                setDateFieldMaxTimeApi("11:30pm"));

        CustomFieldsPage.CustomField activityNumberCustomFieldConfig = CustomFieldsPage.createCustomField(CustomFieldsPage.
                getCustomFieldApiGenerator(activityNumberCustomFieldName, "FieldDescription").
                setControlType("INPUT"));

        CustomFieldsPage.CustomField activitySingleChoiceCustomFieldConfig = CustomFieldsPage.createCustomField(CustomFieldsPage.
                getCustomFieldApiGenerator(activitySingleChoiceCustomFieldName, "FieldDescription").
                setControlType("RADIO").
                setSingleChoiceFieldValueLabelsApi("value1", "value2").
                setDefaultValue("value1"));

        CustomFieldsPage.CustomField activityYesNoCustomFieldConfig = CustomFieldsPage.createCustomField(CustomFieldsPage.
                getCustomFieldApiGenerator(activityYesNoCustomFieldName, "FieldDescription").
                setYesNoFieldControlOrientationApi("VERTICAL").
                setDefaultValue("true").
                setYesNoFieldValueLabelsApi("YesNo"));

        CustomFieldsPage.CustomField activityTextBoxCustomFieldConfig = CustomFieldsPage.createCustomField(CustomFieldsPage.
                getCustomFieldApiGenerator(activityTextBoxCustomFieldName, "FieldDescription").
                setControlType("INPUT").
                setGhostText(activityTextBoxCustomFieldName).
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

        // fill title and description
        addSignupFormsPage = new LoginPage().doSuccessLogin(login, password).openActivitiesPage().
                openSubscribeWidgetsPage().openAddSubscribeWidgetPage();
        addSignupFormsPage.fillFieldsWidgetStepOne(widgetName, widgetDescription);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // select layout for form
        addSignupFormsPage.selectBlankLayout();
        // leave default design and go to settings
        // leave default settings and publish for

        //addSignupFormsPage.dropVEFormElement();

        addSignupFormsPage.dropOneColumnRow();
        //addSignupFormsPage.dropOneColumnRow();

        addSignupFormsPage.dropVEFormElement();
      //  addSignupFormsPage.dropVEFormFieldElement();

        new FormFieldConfigurationPage().dropFormFieldByName(supporterTextBoxCustomFieldName).markFieldAsRequired().saveFieldConfiguration();
        new FormFieldConfigurationPage().dropFormFieldByName(supporterNumberCustomFieldName).markFieldAsRequired().saveFieldConfiguration();
        new FormFieldConfigurationPage().dropFormFieldByName(supporterSingleChoiceCustomFieldName).saveFieldConfiguration();
        new FormFieldConfigurationPage().dropFormFieldByName(supporterYesNoCustomFieldName).saveFieldConfiguration();
        new FormFieldConfigurationPage().dropFormFieldByName(supporterDateTimeCustomFieldName).markFieldAsRequired().saveFieldConfiguration();
        new FormFieldConfigurationPage().dropFormFieldByName(activityTextBoxCustomFieldName).markFieldAsRequired().saveFieldConfiguration();
        new FormFieldConfigurationPage().dropFormFieldByName(activityNumberCustomFieldName).markFieldAsRequired().saveFieldConfiguration();
        new FormFieldConfigurationPage().dropFormFieldByName(activitySingleChoiceCustomFieldName).saveFieldConfiguration();
        new FormFieldConfigurationPage().dropFormFieldByName(activityYesNoCustomFieldName).saveFieldConfiguration();
        new FormFieldConfigurationPage().dropFormFieldByName(activityDateTimeCustomFieldName).markFieldAsRequired().saveFieldConfiguration();

        new FormFieldConfigurationPage().dropAllSupporterFieldsOnFormAndMarkAsRequired();

        addSignupFormsPage.editVEField("City").markFieldAsRequired().saveFieldConfiguration();
        addSignupFormsPage.editVEField("State").markFieldAsRequired().saveFieldConfiguration();
        addSignupFormsPage.editVEField("Zip Code").markFieldAsRequired().saveFieldConfiguration();
        addSignupFormsPage.editVEField("Address, line 1").markFieldAsRequired().saveFieldConfiguration();

        //new FormFieldConfigurationPage().dropAllSupporterFieldsOnForm();

        addSignupFormsPage.goToAutorespondersTab();
        addSignupFormsPage.publishFromAutoresponders();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        addSignupFormsPage.openSubscribeWidget();


        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new SubscribeWidget().fillSubscribeWidgetAllSupporterAndCustomFields(supporterEmail, "personFName","personLName","personCity","20008",
                "UA-63", "addressLine1", "addressLine2", "Male", "777-777-7777","personMName",
                "en-US", "suffixValue", "titleValue","777-777-7777","777-777-7777","UA","09/11/2017",
                "supporterTextBoxCustomFieldValue", "13", "10/11/2017 07:08 am",
                "activityTextBoxCustomFieldValue","13","10/11/2017 07:08 am");

 /*       new LoginPage().doSuccessLogin(login, password).openAudiencePage().openSupportersPage();
        new SupportersPage().clickFirstTopSuppor();
        new SupportersAddPage().verifyEmailAndMiddleName(); //TODO: get fields values using API & verify then

        CustomFieldsPage.CustomField dateTimeField = CustomFieldsPage.createCustomField(CustomFieldsPage.
                getCustomFieldApiGenerator("DATEtS111313", "FieldDescription").
                setControlType("DATETIME").
                setDateFieldMinDateApi("09/15/2017").
                setDateFieldMaxDateApi("09/15/2018").
                setDateFieldMinTimeApi("10:30pm").
                setDateFieldMaxTimeApi("11:30pm"));*/





    }


}
