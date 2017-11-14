package com.salsalabs.ignite.automation.suites.regression;

import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;
import com.salsalabs.ignite.automation.pages.hq.manage.UnsubscribeSettingsPage;
import org.apache.commons.lang.RandomStringUtils;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SubscriptionManagementTest extends SeleneseTestCase {

    private String introdactoryText = RandomStringUtils.randomAlphanumeric(20);

    @Parameters({ "login", "password"})
    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = { "settings.subscriptionManagement" })
    public void subscriptionManagementTest(String login, String password) {
        UnsubscribeSettingsPage unsubscribeSettingsPage = new LoginPage().
                doSuccessLogin(login, password).
                openSettingsPage().
                switchToUnsubscribeSettingsPage().
                editIntroductionaryText(introdactoryText);
        unsubscribeSettingsPage.openUnsubscribePage().verifyInroductoryText(introdactoryText);
    }
}
