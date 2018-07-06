package com.salsalabs.ignite.automation.suites.regression;

import org.apache.commons.lang.RandomStringUtils;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.pages.hq.HomePage;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;
import com.salsalabs.ignite.automation.pages.hq.manage.ManagePage;

public class MessageTopicTest extends SeleneseTestCase {
	@Parameters("login")
	@Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = { "settings.MessageTopic" }, description = "")
	public void verifyMessageTopicCreationTest(String login) throws Exception {
		String topic = "Topic" + RandomStringUtils.randomAlphanumeric(5);
		LoginPage loginPage = new LoginPage();
		loginPage.doSuccessLogin(login, "qwerty").
		openSettingsPage().
		switchToMessageTopicsPage().
		addCustomTopic(topic).
	    verifyAddedTopicInTheTable(topic);
		new HomePage().openMessagingPage()
		.openEmailBlastsPage().
		openAddEmailPage().
		verifyNewAddedMessageTopicInTheBlast(topic);
		new ManagePage().openSettingsPage().
		switchToMessageTopicsPage().
		deleteMessageTopic();
		

	}

}
