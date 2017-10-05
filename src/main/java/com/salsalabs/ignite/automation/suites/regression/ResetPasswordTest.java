package com.salsalabs.ignite.automation.suites.regression;

import com.salsalabs.ignite.automation.common.EmailClient;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.common.SquirrelEmailClient;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;

import javax.mail.Message;
import java.util.List;
import java.util.Map;

/**
 * <b>This class contains Reset password scenario (https://jira.salsalabs.net/browse/IG-10599)</b>
 */

public class ResetPasswordTest extends SeleneseTestCase {
	
	   
    /**
     * <b>Reset password</b>
     * <p>
     * Steps:
     * <ul>
     * <li> Go to login page
     * <li> Click on 'I forgot' link
     * <li> Specify CM email address >> Click on 'Reset My Password' button
     * <li> Go to CM email box, find password recovery email and click on the password reset link
     * <li> Specify new password, fill in 'retype' password and security question fields
     * <li> Click on 'Let's go!' button
     * <li> Go to login page
     * <li> <font color="green"><b>Verify whether you're able to login with new password</b></font>
	 * <li> <font color="green"><b>Verify that password reset confirmation email is delivered</b></font>
     */
	
	@Parameters({"login", "emailsAmount", "emailSubject", "password"})
    @Test(enabled = true, groups = {"resetPassword"}, retryAnalyzer = RetryAnalyzer.class)
    public void resetPassword(String login, Integer emailsAmount, String emailSubject, String password) {
		new SquirrelEmailClient("testauto").deleteAllEmails();
		LoginPage lp = new LoginPage();
		lp.openPage()
		.clickIForgotPasswordLink()
		.specifyEmailAddress(login)
		.clickResetMyPasswordButton();

		lp.openEmails(emailSubject, emailsAmount);
		lp.openPasswordRecoveryPage()
				.specifyNewRecoveryPassword(password)
				.retypeNewRecoveryPassword(password)
				.specifySequrityQuestionAnswer("1")
				.confirmResetPassword()
				.openPage()
				.doSuccessLogin(login, password);
		Assert.assertEquals(!getDriver().getCurrentUrl().contains("/#/login"), true, "You were not logged in with new password");
		Assert.assertEquals(new SquirrelEmailClient("testauto").getEmailBySubject("Your password has been changed.") != null, true, "Password reset confirmation email is not found in email box");
	}
}
