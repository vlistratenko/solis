package com.salsalabs.ignite.automation.suites.regression;

import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.pages.hq.FeedBackDialogPopUp;
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
    private String newPassword = "!QAZ2wsxe";
    private String confirmPassword = "!QAZ2wsxe";
    private String securityAnswerOne = "1";
    private String securityAnswerTwo = "1";
    private String oldLogin;

    /*
     * org name TestV Co2712
     * admin 2712.3e41c646@mailosaur.io / !QAZ2wsx
     * Do NOT use this admin account in this test. Only CM account can be used!!!!!
     */
    @Parameters({"login", "password"})
    @Test(priority = 0, enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"checkMyAccount"})
    public void updateMyAccountData(String login, String password) throws InterruptedException {
        //Org name is MyAccountTest
        oldLogin = login;
        mailosaur.deleteAllEmails();
        MyAccountPage myAccountPage = new LoginPage().
                doSuccessLogin(login, password).
                openMyAccountPage().
                enterSecureArea(password);
        myAccountPage.updatePersonalInfo(firstName, lastName, email);
        waitAndCloseConfirmationPopUp();
        myAccountPage.updatePassword(newPassword, confirmPassword);
        waitAndCloseConfirmationPopUp();
        myAccountPage.updateSecurityAnswers(securityAnswerOne, securityAnswerTwo).
                checkIfDiscardButtonsAppear();
        waitAndCloseConfirmationPopUp();
        myAccountPage.verifyAccountUpdatedEmail(mailosaur, false);
        
        //change back account data
        myAccountPage.updatePersonalInfo(firstName, lastName, oldLogin).//change email back
                updatePassword(password, password);//change password back
        email = login;
        waitAndCloseConfirmationPopUp();
        CommonUtils.checkAndFail("updateMyAccountData");
        
                
    }
    
    private void waitAndCloseConfirmationPopUp() {
    	String savingConfirmationPath = "//*[text()= '" + email + " settings has been saved']";
    	new FeedBackDialogPopUp(savingConfirmationPath, "settings has been saved confirmation popup").
    	waitDialogPopUp(20).
    	closePopUp();
	}
}
