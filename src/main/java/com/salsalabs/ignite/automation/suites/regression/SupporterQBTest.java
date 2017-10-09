package com.salsalabs.ignite.automation.suites.regression;


import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;
import com.salsalabs.ignite.automation.pages.hq.supporters.CreateEditSupporterQuery;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SupporterQBTest  extends SeleneseTestCase {

    @Parameters({"login", "password"})
    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"qb"})
    public void createNewQuery(String login, String password) {
        new LoginPage().
                doSuccessLogin(login, password).
                openAudiencePage().
                openSupporterQBPage().
                createNewQuery();
    }

    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"qb"}, dependsOnMethods = { "createNewQuery" })
    public void verifySupporterFiledsCriteria() {
        new CreateEditSupporterQuery().pickFirstCriteria("Supporter Fields").tempTest().checkIfTableIsAvailable();
    }
}
