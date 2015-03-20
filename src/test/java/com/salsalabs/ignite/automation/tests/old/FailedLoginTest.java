package com.salsalabs.ignite.automation.tests.old;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;

public class FailedLoginTest extends SeleneseTestCase {

	@Parameters({ "fail.login", "fail.password"})
	@Test(retryAnalyzer=RetryAnalyzer.class, priority=150, groups = {"acceptanceTests.user", "dev"}, description = "502:55:Validation for not valid CM credentials is NOT correct.")
	public void loginWithFailTest(String login, String password ){
		String logins[] = CommonUtils.getArrayFromStringBySymbol(login, ":");
		String passwords[] = CommonUtils.getArrayFromStringBySymbol(password, ":");
		for (int i = 0; i < logins.length; i++) {
			LoginPage loginPage = new LoginPage();
			loginPage.
			doFailLogin(logins[i], passwords[i]).
			verifyValidationForFailLogin(logins[i]);	
		}		
	}

}
