package com.salsalabs.ignite.automation.suites.regression;

import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.HttpClient;
import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;
import com.salsalabs.ignite.automation.pages.hq.activities.AddDonationWidgetPage;
import com.salsalabs.ignite.automation.pages.hq.activities.DonationWidget;
import com.salsalabs.ignite.automation.pages.hq.activities.FormFieldConfigurationModalWindow;
import com.salsalabs.ignite.automation.pages.hq.activities.SubscribeWidget;
import com.salsalabs.ignite.automation.pages.hq.event.AddEventPageComposeTabCheckout;
import com.salsalabs.ignite.automation.pages.hq.event.AddEventPageComposeTabRegistration;
import com.salsalabs.ignite.automation.pages.hq.manage.CustomFieldsPage;
import com.salsalabs.ignite.automation.pages.hq.manage.PaymentGatewaysPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONException;
import org.testng.ITestContext;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

@Test(groups = {"eventFormFieldsValidation"})
public class EventFormFieldsValidationTest extends SeleneseTestCase {

        @Parameters({"login","password"})
        @Test(retryAnalyzer = RetryAnalyzer.class)
        public void testCreatePublishSubmitEventFormRequiredEmptyCustomFields(String login, String password){
                new LoginPage().doSuccessLogin(login, password)
                        .openActivitiesPage()
                        .openEventsPage()
                        .clickCreateAnEventButton()
                        .specifyEventReferenceName("EventForm_" + RandomStringUtils.randomAlphanumeric(5))
                        .specifyEventPublicilyVisibleName("EventFormPublicName_" + RandomStringUtils.randomAlphanumeric(5))
                        .clickNextButtonInSetupTab()
                        .specifyTicketName("TicketName_" + RandomStringUtils.randomAlphanumeric(5))
                        .clickSaveTicketInfoButton()
                        .clickContinueButton()
                        .clickNextButtonInTicketsTab()
                        .selectLayout("Blank")
                        .clickNextButtonInSelectLayoutTab()
                        .dropOneColumnRow()
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
                        .clickPublishOnAutorespondersTab();

                        /*.openSubscribeWidgetsPage()
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
        }*/

    }}


