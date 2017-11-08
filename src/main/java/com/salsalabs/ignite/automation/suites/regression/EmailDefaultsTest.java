package com.salsalabs.ignite.automation.suites.regression;

import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.pages.hq.HomePage;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;
import org.apache.commons.lang.RandomStringUtils;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class EmailDefaultsTest extends SeleneseTestCase {

    private String fromName = RandomStringUtils.randomAlphanumeric(10);
    private String fromEmail = RandomStringUtils.randomAlphanumeric(8) + "@salsalabs.com";
    private String replyToEmail = RandomStringUtils.randomAlphanumeric(8) + "@salsalabs.com";;

    @Parameters({ "login", "password"})
    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = { "settings.emailDefaults" })
    public void emailDefaultsTest(String login, String password) {
        HomePage homePage = new LoginPage().
                doSuccessLogin(login, password).
                openSettingsPage().
                switchToEmailDefaultsPagePage().
                changeEmailDefaults(fromName, fromEmail, replyToEmail);
        homePage.
                openMessagingPage().
                openEmailBlastsPage().
                openAddEmailPage().
                fillAllFieldsAndGoForward(fromName).
                addSupporters(login, 1, "prop").
                openComposePage().
                selectLayout("Basic").
                checkEmailDefaults(fromName, fromEmail, replyToEmail);

    }
}
