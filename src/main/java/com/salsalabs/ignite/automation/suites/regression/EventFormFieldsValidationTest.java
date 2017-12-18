package com.salsalabs.ignite.automation.suites.regression;

import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;
import com.salsalabs.ignite.automation.pages.hq.activities.EventWidget;
import com.salsalabs.ignite.automation.pages.hq.activities.FormFieldConfigurationModalWindow;
import com.salsalabs.ignite.automation.pages.hq.activities.event.AddEventPageComposeTabCheckout;
import com.salsalabs.ignite.automation.pages.hq.activities.event.AddEventPageComposeTabRegistration;
import com.salsalabs.ignite.automation.pages.hq.activities.event.AddEventWidgetPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Test(groups = {"eventFormFieldsValidation"})
public class EventFormFieldsValidationTest extends SeleneseTestCase {

        String widgetName;

        @Parameters({"login","password"})
        @Test(retryAnalyzer = RetryAnalyzer.class)
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
                new AddEventPageComposeTabCheckout()
                        .dropOneColumnRow()
                        .dropVEFormElement();
                new FormFieldConfigurationModalWindow().dropAllSupporterFieldsOnForm();
                new AddEventPageComposeTabCheckout()
                        .clickNextButtonInComposeTab()
                        .clickPublishOnAutorespondersTab()
                        .openEventWidget(widgetName);

                EventWidget eventForm = new EventWidget();
                eventForm.openDonationPage().clickSubmitButton().
                        verifyValidationMessageFieldRequireValueDisplayedForEmptySupporterFields();
    }
}


