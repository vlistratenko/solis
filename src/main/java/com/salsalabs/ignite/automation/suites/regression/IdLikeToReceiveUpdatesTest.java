package com.salsalabs.ignite.automation.suites.regression;

import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.pages.hq.HomePage;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;
import com.salsalabs.ignite.automation.pages.hq.activities.AddSubscribeWidgetPage;
import com.salsalabs.ignite.automation.pages.hq.activities.DonationWidget;
import com.salsalabs.ignite.automation.pages.hq.supporters.SupportersPage;
import org.apache.commons.lang.RandomStringUtils;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class IdLikeToReceiveUpdatesTest extends SeleneseTestCase {

    private String activityName;
    private String fieldLabel;
    private String email;
    private String hqHandle;
    /*
    @Parameters({ "login", "password"})
    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = { "activities.UpdatesSubscription" })
    public void checkSignupForm(String login, String password) throws InterruptedException {
        generateTestData();
        AddSubscribeWidgetPage addSubscribeWidgetPage = new LoginPage().
                doSuccessLogin(login, password).
                openActivitiesPage().
                openSubscribeWidgetsPage().
                openAddSubscribeWidgetPage().
                fillFieldsWidgetStepOne(activityName, "desc").
                selectLayoutStep("Hero").
                editIdLikeToReceiveUpdatesCheckBoxProperties(fieldLabel, "False (Unchecked)").
                checkIdLikeToReceiveUpdatesCheckBoxProperties(fieldLabel, "False (Unchecked)").
                editIdLikeToReceiveUpdatesCheckBoxProperties(fieldLabel, "True (Checked)").
                goToAutorespondersTab().
                publishFromAutoresponders();
        hqHandle = driver.getWindowHandle();
        addSubscribeWidgetPage.openSubscribeWidget();
        new SubscribeWidget().fillSubscribeWidgetMinimumFieldsSet(email, fieldLabel, fieldLabel, true);
        getDriver().switchTo().window(hqHandle);
        checkSubscriptionStatus();
    }

    */
    @Parameters({ "login", "password"})
    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = { "activities.UpdatesSubscription" })
    public void fundraisingForm(String login, String password) throws InterruptedException {
       generateTestData();
        AddSubscribeWidgetPage addSubscribeWidgetPage = new LoginPage().
                doSuccessLogin(login, password).
                openActivitiesPage().
                openFundraisingWidgetPage().
                openAddDonationWidgetPage().
                createForm(activityName,"desc").
                selectLayoutStep("Suggested").
                switchBetweenFormSteps(2).
                editIdLikeToReceiveUpdatesCheckBoxProperties(fieldLabel, "False (Unchecked)").
                checkIdLikeToReceiveUpdatesCheckBoxProperties(fieldLabel, "False (Unchecked)").
                editIdLikeToReceiveUpdatesCheckBoxProperties(fieldLabel, "True (Checked)").
                goToAutorespondersTab().
                publishFromAutoresponders();
        hqHandle = driver.getWindowHandle();
        addSubscribeWidgetPage.openSubscribeWidget();
        new DonationWidget().
                fillTheFirstStepOfTheDonationForm("10", false, false).
                fillTheSecondStepOfTheDonationForm(email, fieldLabel, fieldLabel, "addr", "city", "20009", "AL", true).
                fillTheThirdStepOfTheDonationForm("4111111111111111", "123", "10", "2025").
                clickDonationButtonNewForms();
        getDriver().switchTo().window(hqHandle);
        checkSubscriptionStatus();
    }
    /*
    @Parameters({ "login", "password"})
    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = { "activities.UpdatesSubscription" })
    public void petitionForm(String login, String password) throws InterruptedException {
        AddSubscribeWidgetPage addSubscribeWidgetPage = new LoginPage().
                doSuccessLogin(login, password).
                openActivitiesPage().
                openPetitionsPage().
                openAddPetitionPage().
                fillFieldsWidgetStepOne(activityName, "desc").
                selectLayoutStep("Hero").
                editIdLikeToReceiveUpdatesCheckBoxProperties(fieldLabel, "False (Unchecked)").
                checkIdLikeToReceiveUpdatesCheckBoxProperties(fieldLabel, "False (Unchecked)").
                editIdLikeToReceiveUpdatesCheckBoxProperties(fieldLabel, "True (Checked)").
                goToAutorespondersTab().
                publishFromAutoresponders();
        hqHandle = driver.getWindowHandle();
        addSubscribeWidgetPage.openSubscribeWidget();
        new PetitionWidget().fillSubscribeWidget(email, activityName, activityName, activityName, "20009").clickOnSubmitFormButton();
        getDriver().switchTo().window(hqHandle);
        checkSubscriptionStatus();
        checkSubscriptionStatus();
    }

    @Parameters({ "login", "password"})
    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = { "activities.UpdatesSubscription" })
    public void targetedActionForm(String login, String password) throws InterruptedException {
         new LoginPage().
                doSuccessLogin(login, password).
                openActivitiesPage().
                openTargetedActionsPage().
                openAddTargetedActionPage().
                fillFieldsWidgetStepOne(activityName, "desc").
                pickTargetAndEnableChannel("Brad James", AddTargetedActionPage.Channel.EMAIL).
                goToComposeFormTargetsPage().
                selectLayoutStep("Hero").
                editIdLikeToReceiveUpdatesCheckBoxProperties(fieldLabel, "False (Unchecked)").
                checkIdLikeToReceiveUpdatesCheckBoxProperties(fieldLabel, "False (Unchecked)").
                editIdLikeToReceiveUpdatesCheckBoxProperties(fieldLabel, "True (Checked)").
                goToAutorespondersTab().
                publishFromAutoresponders().
                openSubscribeWidget();
        new TargetActionsPage().fillAndSubmitWidget(email, activityName, activityName, activityName, "91602", "NY", "10753 blix");
        checkSubscriptionStatus();
    }

    @Parameters({ "login", "password"})
    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = { "activities.UpdatesSubscription" })
    public void eventForm(String login, String password) throws InterruptedException {
        new LoginPage().doSuccessLogin(login, password)
                .openActivitiesPage()
                .openEventsPage()
                .clickCreateAnEventButton()
                .specifyEventReferenceName(activityName)
                .specifyEventPublicilyVisibleName(activityName)
                .clickNextButtonInSetupTab()
                .specifyTicketName(activityName)
                .clickSaveTicketInfoButton()
                .clickContinueButton()
                .clickNextButtonInTicketsTab()
                .selectLayout("Basic")
                .clickNextButtonInSelectLayoutTab()
                .clickNextButtonInComposeTab()
                .clickPublishOnAutorespondersTab()
                .openWidget(activityName);

        Thread.sleep(15000);
        new EventWidget()
                .openEventRegistrationPage()
                .fillEventRegistrationForm(email, activityName, activityName)
                .fillEventDonationForm(
                        email,
                        activityName,
                        activityName,
                        activityName,
                        activityName,
                        "20009",
                        "WA",
                        "10",
                        "card holder",
                        "4111111111111111",
                        "123",
                        "12",
                        "2022"
                );
        checkSubscriptionStatus();
    }

    @Parameters({ "login", "password"})
    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = { "activities.UpdatesSubscription" })
    public void p2pForm(String login, String password) throws InterruptedException {
        AddP2PPage_PublishedDeatailsTab publishedDeatailsTab = new LoginPage().
                doSuccessLogin(login, password).
                openActivitiesPage().
                openP2PPage().
                openCreateNewp2pForm().
                fillSetupStepAndGoNext(
                        activityName,
                        activityName,
                        "12/15/2017"
                        , "8:00am",
                        "12/01/2021",
                        "8:00am",
                        "(GMT-10:00) Aleutian Islands",
                        false).
                fillRegistrationStepAndGoNext(
                        activityName,
                        true,
                        "10",
                        "5").
                clickContinueButton().
                clickContinueButton().
                clickNextButton().
                selectLayoutAndClickNext("Basic").
                openCheckoutSubTab().
                editIdLikeToReceiveUpdatesCheckBoxProperties(fieldLabel, "False (Unchecked)").
                checkIdLikeToReceiveUpdatesCheckBoxProperties(fieldLabel, "False (Unchecked)").
                editIdLikeToReceiveUpdatesCheckBoxProperties(fieldLabel, "True (Checked)").
                clickNextToEventPageButton().
                clickNextToTeamTabButton().
                clickNextToAutorespondersTabButton().
                clickPublishButton();
        hqHandle = driver.getWindowHandle();
        publishedDeatailsTab.openWidget(activityName.toLowerCase());
        new Eventp2pWidget().openp2pEventRegistrationPage().clickNextButtonOnRegistrationTypesPage().
                fillFundraiserSignInForm(activityName, activityName, email, "qwerty", "qwerty", false).
                clickCheckOutButton().
                fillp2pEventDonationForm(
                        email,
                        activityName,
                        activityName,
                        activityName,
                        activityName,
                        "20009",
                        "WA",
                        "10",
                        "Card Holder",
                        "4111111111111111",
                        "123",
                        "12",
                        "2022",
                        true,
                        true,
                        true).
                clickOnSubmitFormButton();
        getDriver().switchTo().window(hqHandle);
        checkSubscriptionStatus();
    }

       */

    private void checkSubscriptionStatus() {
        new HomePage().openAudiencePage();
        new SupportersPage().
                openSupporterDetailsPage(email).
                goToSubscriptionTab().
                verifySupporterStatus("Subscribed");
    }

    private void generateTestData() {
        activityName = RandomStringUtils.randomAlphanumeric(10);
        fieldLabel = RandomStringUtils.randomAlphanumeric(10);
        email = RandomStringUtils.randomAlphanumeric(5) + "@test.com";
    }
}
