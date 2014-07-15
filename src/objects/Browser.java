package objects;

import java.util.ArrayList;
import java.util.Set;

import junit.framework.AssertionFailedError;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import pages.HQ.LoginPage;

import com.mailosaur.exception.MailosaurException;
import com.mailosaur.model.Email;
import com.thoughtworks.selenium.Selenium;

import selenium.CommonUtils;
import selenium.Driver;
import selenium.EmailClient;
import selenium.SeleneseTestCase;

public abstract class Browser{
	static WebDriver driver;
	static Logger logger;
	Selenium selenium;
	int cTimeOut;
	String elementName;
	Integer defaultTimeOut;
	
	
	public Browser() {
		driver = SeleneseTestCase.driver;
		logger = SeleneseTestCase.logger;
		selenium = SeleneseTestCase.selenium;
		cTimeOut = SeleneseTestCase.cTimeOut;
		defaultTimeOut = SeleneseTestCase.defaultTimeOut;
	}
	
	
	protected String getBrowser() {
		Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
		String browserName = cap.getBrowserName().toLowerCase();
		return browserName;
	}
	
	protected void open(){		
	
		open(SeleneseTestCase.USED_ENVIRONMENT.getBaseTestUrl());
	}
	
	protected void logOut(){		
		deletecoockies();		
		open(SeleneseTestCase.USED_ENVIRONMENT.getBaseTestUrl() + "/#/logout");
		sleep(5000);
	}
	
	protected void open(String url){		
		logger.info("Try to open URL - " + url);	
		SeleneseTestCase.bug.add("Open " + url);
		driver.navigate().to(url);		
	}
	
	protected void refresh(){		
		logger.info("Try to open refresh the page");		
		driver.navigate().refresh();		
	}
	protected void close(){
		SeleneseTestCase.close();
	}
	
	protected void deletecoockies(){
		SeleneseTestCase.deletecoockies();
		sleep(5000);
	}

	/**
	 * @param timeOut in seconds
	 */
	protected static void implicityWait(Integer timeOut){		
		SeleneseTestCase.implicityWait(timeOut);				
	}
	
	protected void openURLInOtherBrowserAndVerifyByTitle() {
		WebDriver driverT = null;
		String windHandle = driver.getWindowHandle();
		if (/*browser*/getBrowser().contains("firefox")) {
			driverT = new Driver().get_driver("*iexplore");
		}else{
			driverT = new Driver().get_driver("*firefox");
		}
		driverT.navigate().to(SeleneseTestCase.driver.getCurrentUrl());
		//String title2 = driverT.getTitle();
		driverT.close();
		switchToWindow(windHandle);
		//verifyNotEquals(SeleneseTestCase.driver.getTitle(), title2, "After opening URL in other browser, title is the same");
	}
	
	protected void switchToWindow(String popUpWindowHandle){		
		logger.debug("Try to switch focus to window " + popUpWindowHandle);
		SeleneseTestCase.driver.switchTo().window(popUpWindowHandle);				
	}
	
	protected void switchToPopupWindow (String currentWindowHandle) {
		String popUpWindowHandle = null;		     
		Set<String> openWindowsList = SeleneseTestCase.driver.getWindowHandles();
		for(String windowHandle:openWindowsList)
        {
        	if (!windowHandle.equals(currentWindowHandle))
        		popUpWindowHandle=windowHandle;
        }
        switchToWindow(popUpWindowHandle);	
	}
	
	protected void switchToFrame(String xpath) {
		driver.switchTo().frame(driver.findElement(By.xpath(xpath)));
	}
	
	protected void switchDefaultContent() {
		driver.switchTo().defaultContent();
	}
	
	protected void assertObjects (Object actual, Object expected) {
		verify(actual, expected, "", false);	
	}
	
	protected void assertObjects (Object actual, Object expected, String message) {
		logger.debug("Check equality of two objects. ");
		verify(actual, expected, message, false);		
	}
	
	protected void verify (Object actual, Object expected, String message) {
		logger.info("Check equality of two objects. ");
		SeleneseTestCase.bug.add("Error " + message + ". Expected [" + expected + "] but was [" + actual + "]");
		verify(actual, expected, message, true);		
	}
	
	protected void verifyNotEquals (Object actual, Object expected, String message) {
		logger.debug("Check equality of two objects. ");
		verifyNotEquals(actual, expected, message, true);		
	}
	
	private void verify(Object actual, Object expected, String message, Boolean Fail){
		try {
			Assert.assertEquals(actual, expected);
		} catch (AssertionError e) {
			CommonUtils.SetParam("testResult", "fail");
			//bug.add(message + " - " + e.getMessage());
			if (Fail){
				throw new AssertionFailedError(message + " - " + e.getMessage());
			}else{
				logger.error("Verification error: " + message + " - " + e.getMessage());
			}
		}		
	}
	
	private void verifyNotEquals(Object actual, Object expected, String message, Boolean Fail){
		try {
			Assert.assertNotEquals(actual, expected);
		} catch (AssertionError e) {
			CommonUtils.SetParam("testResult", "fail");
			//bug.add(message + " - " + e.getMessage());
			if (Fail){
				throw new AssertionFailedError(message + " - " + e.getMessage());
			}else{
				logger.error("Verification error: " + message + " - " + e.getMessage());
			}
		}		
	}
	
	protected void test_fail() {
		logger.debug("Failure the test. Function is called force quit the test. ");
		Assert.assertTrue(false);		
	}	
	
	protected void closeGoogleSession(){		
		logger.info("LogOut from google and back to the test page");		
		deletecoockies();
		SeleneseTestCase.driver.navigate().to("https://mail.google.com/mail/?logout&hl=ru");	
		sleep(3000);
							
	}
	
	protected void sleep(int t) {
		try {
			Thread.sleep(t);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected String getLocation() {
		logger.info("Get current URL");
		return driver.getCurrentUrl();
	}
	
	public LoginPage verifyAmountOfEmails(int amountOfEmails, int amountOfSplits) {
		Integer amountEmailsInSplit = amountOfEmails;
		if (amountOfSplits > 1) {
			Integer testGroup = Integer.valueOf(CommonUtils.getProperty("percentageOfTestGroup"));
			float s = (float) ((float)testGroup/(float)100);
			amountEmailsInSplit = (int) (amountOfEmails/amountOfSplits * s);
			
		}
		for (int i = 1; i <= amountOfSplits; i++) {
			String subj; 
			if (amountOfSplits > 1) {
				subj = CommonUtils.getProperty("emailSplitsSubject") + " Split " + i;
			}else{
				subj = CommonUtils.getProperty("emailSubject");
			}
			Integer amountEmails = null;
			try {
				
				amountEmails = new EmailClient().waitForEmails(amountEmailsInSplit).getEmailsBySubject(subj).size();
			} catch (MailosaurException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if ( ! CommonUtils.getProperty("Environment").equalsIgnoreCase("dev")) {
				verify(amountEmails, amountEmailsInSplit, "Not all emails " + subj + " were delivered");
			}else{
				verify(amountEmails > 0, true, "O emails were delivered");	
			}
		}
		
		return new LoginPage();
	}
	
	public void openEmails(String subj, Integer amount) {
		EmailClient client = new EmailClient();
		ArrayList<Email> emails = null;
		try {
			emails = client.getEmailsBySubject(subj);
		} catch (MailosaurException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (emails.size() < amount) amount = emails.size();
		for (int i = 0; i < amount; i++) {
			client.openEmail(emails.get(i));
			logger.info("Email for " + emails.get(i).to[0].address  + " was opened");
		}		
	}
	
	public void clickLinkInEmail(String subj, String linkText, Integer amountOfEmails) {
		EmailClient client = new EmailClient();
		ArrayList<Email> emails = null;
		try {
			emails = client.getEmailsBySubject(subj);
		} catch (MailosaurException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (emails.size() < amountOfEmails) amountOfEmails = emails.size();
		for (int i = 0; i < amountOfEmails; i++) {
			client.clickLinkByText(emails.get(i), linkText);
			logger.info("Link in the email for " + emails.get(i).to[0].address + " was clicked");
		}		
	}
}

