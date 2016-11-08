package com.salsalabs.ignite.automation.suites;


import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.PropertyName;
import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.common.Supporter;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.pages.hq.HomePage;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;
import com.salsalabs.ignite.automation.pages.hq.segments.SegmentsAddPage;
import com.salsalabs.ignite.automation.pages.hq.supporters.SupportersAddPage;
import com.salsalabs.ignite.automation.pages.hq.supporters.SupportersPage;

public class WNGTest extends SeleneseTestCase {
	
	private SupportersAddPage supporterAddPage;
	private SupportersPage supportersPage;
	private Supporter supporter;
	
	
	@Test(enabled = true, groups = { "createSegment"}, retryAnalyzer = RetryAnalyzer.class)
	public void createCustomSegmentTest(){

		LoginPage loginPage = new LoginPage();
		loginPage.
		doSuccessLogin("704.3e41c646@mailosaur.in",  "!QAZ2wsx");
		
		loginPage.open("https://hq.test.ignite.net/#/audience/supporters/query/f83c85dc-8cb3-4c00-9c6e-a78cc1f61a8a");
		
	
		SegmentsAddPage segPage = new SegmentsAddPage();
		String firstLevelItems[] = segPage.getListOfAvailableActivityTypeItems();
		for (int i = 0; i < firstLevelItems.length; i++) {
			System.err.println(firstLevelItems[i]);
		}
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
