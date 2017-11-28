package com.salsalabs.ignite.automation.suites;


import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.common.SeleneseTestCase_Debug;

import java.util.List;

import org.openqa.selenium.logging.LogEntry;
import org.testng.annotations.Test;


public class WNGTest extends SeleneseTestCase {
	
	@Test(enabled = true, groups = { "createSegment"}, retryAnalyzer = RetryAnalyzer.class, description = "Syndication:1")
	public void createCustomSegmentTest() throws Exception{
		
		List<LogEntry> list = getJsConsoleErrors();
		System.err.println(list.size());
		
		//CommonUtils.getListOfFilesInFolder();
		/*String[] processesToKill = {"chromedriver"};
		String processName = CommonUtils.isProcessRunning("chromedriver");
		if (!processName.equals("")) {
			CommonUtils.killProcess(processName);
		}*/
		/*System.err.println("Test is done");
		throw new ElementNotFoundException("Test locator", "xpath", "Test  v message");*/

	}
	
	/*
	@Test(enabled = true, groups = { "createSegment"}, retryAnalyzer = RetryAnalyzer.class)
	public void segmentruleVerification() {
		
		
		LoginPage loginPage = new LoginPage();
		loginPage.doSuccessLogin(CommonUtils.getProperty(PropertyName.ADMIN_EMAIL),  CommonUtils.getProperty(PropertyName.ADMIN_PASSWORD));
		HomePage homePage = new HomePage();
		supporterAddPage = homePage.
		openAudiencePage().
		openSupportersPage().
		openAddSupporterPage();
		
		
		supporter = Supporter.generateSupporter();
		supportersPage = supporterAddPage.createNewSupporter(supporter);
	
		
	}*/


	
}
