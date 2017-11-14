package com.salsalabs.ignite.automation.suites.regression;

import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;
import com.salsalabs.ignite.automation.pages.hq.MyProfilePage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class PermissionGridAvailabilityTest extends SeleneseTestCase {

    @Parameters({ "org1.login", "org2.login", "org3.login", "org4.login", "org5.login", "org6.login", "org7.login", "password"})
    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = { "administration.verifyPermissionGrid" })
    public void permissionGridAvailabilityTest(String org1, String org2, String org3, String org4,  String org5,  String org6,
                                   String org7, String password) {
        List<String> orgs = Arrays.asList(new String[]{org1, org2, org3, org4, org5, org6, org7});

        for (String login : orgs) {
            LoginPage loginPage = new LoginPage();
            MyProfilePage myProfilePage = loginPage.doSuccessLogin(login, password).openMyProfilePage();

            Assert.assertTrue(myProfilePage.checkIfElementsExistOnPage());
            Assert.assertTrue(getJsConsoleErrors().isEmpty());
            loginPage.logOut();
        }
    }
}
