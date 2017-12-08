package com.salsalabs.ignite.automation.suites.regression;

import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.pages.hq.supporters.SupportersPage;
import org.apache.commons.lang.RandomStringUtils;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class IdLikeToReceiveUpdatesTest extends SeleneseTestCase {

    private String activityName;
    private String fieldLabel;
    private String email;
    private String hqHandle;

    @Parameters({ "login", "password"})
    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = { "activities.UpdatesSubscription" })
    public void idLikeToReceiveUpdatesTest(String login, String password) {
        //Signup forms
        /*generateTestData();
        ActivitiesPage activitiesPage = new LoginPage().
                doSuccessLogin(login, password).
                openActivitiesPage();
        AddSubscribeWidgetPage addSubscribeWidgetPage = activitiesPage.openSubscribeWidgetsPage().
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
        activitiesPage.openAudiencePage();
        checkSubscriptionStatus();*/

        //Funsdraising forms
        generateTestData();
       /* ActivitiesPage activitiesPage = new LoginPage().
                doSuccessLogin(login, password).
                openActivitiesPage().
                openFundraisingWidgetPage().
                openAddDonationWidgetPage();*/

       /* new LoginPage().
                doSuccessLogin(login, password).
                openActivitiesPage().
                openFundraisingWidgetPage().
                openAddDonationWidgetPage().
                createForm(activityName,"desc").
                selectLayoutStep("Hero").
                switchBetweenFormSteps(2).
                editIdLikeToReceiveUpdatesCheckBoxProperties(fieldLabel, "False (Unchecked)").
                checkIdLikeToReceiveUpdatesCheckBoxProperties(fieldLabel, "False (Unchecked)").
                editIdLikeToReceiveUpdatesCheckBoxProperties(fieldLabel, "True (Checked)").
                goToAutorespondersTab().
                publishFromAutoresponders().
                openSubscribeWidget();
        new DonationWidget().
                fillTheFirstStepOfTheDonationForm("10", false, false).
                fillTheSecondStepOfTheDonationForm(email, fieldLabel, fieldLabel, "addr", "city", "20009", "AL", true).
                fillTheThirdStepOfTheDonationForm("4111111111111111", "123", "10", "2025").
                clickDonationButtonNewForms();


        new LoginPage().
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
                publishFromAutoresponders().
                openSubscribeWidget();
        new PetitionWidget().fillSubscribeWidget(email, activityName, activityName, activityName, "20009").clickOnSubmitFormButton();
        checkSubscriptionStatus();
        */

        /*new LoginPage().
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
        checkSubscriptionStatus();*/

    }



    private void checkSubscriptionStatus() {
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
