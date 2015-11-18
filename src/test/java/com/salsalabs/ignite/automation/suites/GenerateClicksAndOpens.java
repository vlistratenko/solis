package com.salsalabs.ignite.automation.suites;


import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.salsalabs.ignite.automation.common.EmailClient;
import com.salsalabs.ignite.automation.common.Environment;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;

public class GenerateClicksAndOpens {
	public static Environment USED_ENVIRONMENT;
	public static EmailClient<?> emailClient;
	WebDriver driver;
	
	@BeforeTest
	public void before() throws Exception {
		USED_ENVIRONMENT = new Environment("TEST", "LOCAL");
		emailClient = USED_ENVIRONMENT.getSquirrelEmailClient();
		driver = new HtmlUnitDriver(true);
		emailClient.setDriver(driver);
		//driver =  new SeleneseTestCase().startTestOnDriver("PHANTOMJS", USED_ENVIRONMENT.getBaseTestUrl());

	}
	
	@Test
	public void generate() {
		LoginPage loginPage = new LoginPage();
		List<?>/*Map<String, List<?>>*/ emails = loginPage.openEmails("TestJM 1454", 5);
		loginPage.clickLinkInEmail(null, "TestJM 1454", "http://google.com", 3);	

	}
	
	@AfterTest
	public void after() {
		emailClient.closeConnection();
		driver.close();
	}
}
