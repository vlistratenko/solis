package com.salsalabs.ignite.automation.suites.regression;

import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.pages.hq.HomePage;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;
import org.apache.commons.lang.RandomStringUtils;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class IdLikeToReceiveUpdatesTest extends SeleneseTestCase {

    private String activityName = RandomStringUtils.randomAlphanumeric(10);
    private String fieldLabel = RandomStringUtils.randomAlphanumeric(10);

    @Parameters({ "login", "password"})
    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = { "activities.UpdatesSubscription" })
    public void idLikeToReceiveUpdatesTest(String login, String password) {
        HomePage homePage = new LoginPage().
                doSuccessLogin(login, password).
                openActivitiesPage().
                openSubscribeWidgetsPage().
                openAddSubscribeWidgetPage().
                fillFieldsWidgetStepOne(activityName, "desc").
                selectLayoutStep("Hero").
                editIdLikeToReceiveUpdatesCheckBoxProperties(fieldLabel, false).
                checkIdLikeToReceiveUpdatesCheckBoxProperties(fieldLabel, false).
                editIdLikeToReceiveUpdatesCheckBoxProperties(fieldLabel, true).
                publishForm();

    }
}
