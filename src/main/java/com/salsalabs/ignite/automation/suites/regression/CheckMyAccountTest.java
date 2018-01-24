package com.salsalabs.ignite.automation.suites.regression;

import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;
import com.salsalabs.ignite.automation.pages.hq.MyAccountPage;
import org.apache.commons.lang.RandomStringUtils;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Test
public class CheckMyAccountTest extends SeleneseTestCase {

    private String firstName = RandomStringUtils.randomAlphanumeric(10);
    private String lastName = RandomStringUtils.randomAlphanumeric(10);
    private String email = RandomStringUtils.randomAlphanumeric(10) + "@testauto.igniteaction.net";
    private String newPassword = "qwerty";
    private String confirmPassword = "qwerty";
    private String securityAnswerOne = "1";
    private String securityAnswerTwo = "1";
    private String oldLogin;

    @Parameters({"login", "password"})
    @Test(priority = 0, enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"checkMyAccount"})
    public void updateMyProfileData(String login, String password) throws InterruptedException {
        //Org name is MyAccountTest
        oldLogin = login;
        mailosaur.deleteAllEmails();
        MyAccountPage myAccountPage = new LoginPage().
                doSuccessLogin(login, password).
                openMyAccountPage().
                enterSecureArea(password).
                updatePersonalInfo(firstName, lastName, email).
                updatePassword(newPassword, confirmPassword).
                updateSecurityAnswers(securityAnswerOne, securityAnswerTwo).
                checkIfDiscardButtonsAppear().
                verifyAccountUpdatedEmail(mailosaur).
                updatePersonalInfo(firstName, lastName, oldLogin); //change email back
    }
}
