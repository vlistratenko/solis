package com.salsalabs.ignite.automation.suites.regression;

import com.salsalabs.ignite.automation.common.GoogleDriveClient;
import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.common.TestInfo;
import com.salsalabs.ignite.automation.pages.donation.DonationsPage;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;
import com.salsalabs.ignite.automation.pages.hq.activities.*;
import com.salsalabs.ignite.automation.pages.hq.activities.event.AddEventPageComposeTabCheckout;
import com.salsalabs.ignite.automation.pages.hq.activities.event.AddEventPageComposeTabRegistration;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Test(groups = {"eventFormFieldsValidation"})
public class EventFormFieldsValidationTest extends SeleneseTestCase {

        private String widgetName;
        private String supporterEmail;

        @Parameters({"login","password"})
        @Test(enabled = false, retryAnalyzer = RetryAnalyzer.class)
        public void testCreatePublishSubmitEventFormRequiredEmptySupporterFields(String login, String password){
                widgetName = "EventForm_" + RandomStringUtils.randomAlphanumeric(5);
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
                AddEventPageComposeTabCheckout composeTab = new AddEventPageComposeTabCheckout();
                composeTab.dropOneColumnRow()
                        .dropVEFormElement();
                composeTab.editVEField("Phone").markFieldAsRequired().saveFieldConfiguration();
                new FormFieldConfigurationModalWindow().dropAllSupporterFieldsOnFormAndMarkAsRequired();
                new AddEventPageComposeTabCheckout()
                        .clickNextButtonInComposeTab()
                        .clickPublishOnAutorespondersTab()
                        .openEventWidget(widgetName);

                EventWidget eventForm = new EventWidget();
                eventForm.openDonationPage().clickSubmitButton().
                        verifyValidationMessageFieldRequireValueDisplayedForEmptySupporterFields();
    }

        @Parameters({"login","password"})
        @Test(enabled = false, retryAnalyzer = RetryAnalyzer.class)
        public void testCreatePublishSubmitEventFormupporterNonRequiredFields(String login, String password){
                widgetName = "EventForm_" + RandomStringUtils.randomAlphanumeric(5);
                supporterEmail = "autosupporter" + RandomStringUtils.randomAlphanumeric(4)+"@test.com";
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

                EventWidget eventForm = new EventWidget();
                eventForm.openDonationPage();
                new DonationWidget().fillDonationWidgetAllSupporterFields(
                        "4111111111111111","123","11","2023","donationTest donationTest",supporterEmail,"FirstName","LastName",
                        "City","91602","UA-63","address1","address2","Male","777-777-7777","MidName","en-US",
                        "suffix","title","777-777-7777","777-777-7777","UA","09/11/2017","5");
                eventForm.clickSubmitButton();
                new AddSubscribeWidgetPage()
                        .verifySubmittedSupporterFieldsArePresentInSupporterDetails(SeleneseTestCase.USED_ENVIRONMENT.getBaseTestUrl(),login,password);
       }

}