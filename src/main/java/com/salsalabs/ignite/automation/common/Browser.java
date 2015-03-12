package com.salsalabs.ignite.automation.common;

import java.util.ArrayList;
import java.util.Set;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import com.mailosaur.exception.MailosaurException;
import com.mailosaur.model.Email;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;

public abstract class Browser {
	static WebDriver driver;
	static Logger logger;
	protected static Verifier verifier = new Verifier();
	int cTimeOut;
	String elementName;
	Integer defaultTimeOut;

	public Browser() {
		driver = SeleneseTestCase.driver;
		logger = SeleneseTestCase.logger;
		cTimeOut = SeleneseTestCase.cTimeOut;
		defaultTimeOut = SeleneseTestCase.defaultTimeOut;
	}

	protected String getBrowser() {
		Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
		String browserName = cap.getBrowserName().toLowerCase();
		return browserName;
	}

	protected void open() {
		open(SeleneseTestCase.USED_ENVIRONMENT.getBaseTestUrl());
	}

	protected LoginPage logOut() {
		deletecoockies();
		open(SeleneseTestCase.USED_ENVIRONMENT.getBaseTestUrl() + "/#/logout");
		sleep(5);
		return new LoginPage();
	}

	protected void open(String url) {
		logger.info("Try to open URL - " + url);
		SeleneseTestCase.bug.add("Open " + url);
		driver.navigate().to(url);
	}

	protected String openInNewWindow(String url) {
		String currentWindowHandle = getWindowHandle();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.open()");
		switchToPopupWindow(currentWindowHandle);
		open(url);
		return currentWindowHandle;
	}

	protected void refresh() {
		logger.info("Try to refresh the page");
		driver.navigate().refresh();
	}

	protected void close() {
		SeleneseTestCase.close();
	}

	protected void closeWindow() {
		SeleneseTestCase.closeWindow();
	}

	protected void deletecoockies() {
		SeleneseTestCase.deletecoockies();
		sleep(5);
	}

	protected Set<Cookie> getCoockies() {
		return SeleneseTestCase.getCoockies();
	}

	/**
	 * @param timeOut
	 *            in seconds
	 */
	public static void implicityWait(Integer timeOut) {
		SeleneseTestCase.implicityWait(timeOut);
	}

	protected String getWindowHandle() {
		return driver.getWindowHandle();
	}

	protected void switchToWindow(String popUpWindowHandle) {
		logger.debug("Try to switch focus to window " + popUpWindowHandle);
		SeleneseTestCase.driver.switchTo().window(popUpWindowHandle);
	}

	protected void switchToPopupWindow(String currentWindowHandle) {
		String popUpWindowHandle = null;
		Set<String> openWindowsList = SeleneseTestCase.driver.getWindowHandles();
		for (String windowHandle : openWindowsList) {
			if (!windowHandle.equals(currentWindowHandle))
				popUpWindowHandle = windowHandle;
		}
		switchToWindow(popUpWindowHandle);
	}

	protected void switchToFrame(String xpath) {
		driver.switchTo().frame(driver.findElement(By.xpath(xpath)));
	}

	protected void switchDefaultContent() {
		driver.switchTo().defaultContent();
	}

	protected void test_fail() {
		logger.debug("Failure the test. Function is called force quit the test. ");
		Assert.assertTrue(false);
	}

	protected void closeGoogleSession() {
		logger.info("LogOut from google and back to the test page");
		deletecoockies();
		SeleneseTestCase.driver.navigate().to("https://mail.google.com/mail/?logout&hl=ru");
		sleep(3);

	}

	public boolean waitConditionBecomesTrue(boolean condition, Integer timeOut) {
		if (!condition) {
			sleep(timeOut);
			return false;
		} else {
			return true;
		}

	}

	public boolean waitConditionBecomesTrueWithRefersh(boolean condition, Integer timeOut) {
		if (!condition) {
			logger.info("Wait for condition");
			sleep(timeOut);
			refresh();
			sleep(3);
			return false;
		} else {
			return true;
		}

	}

	public boolean waitConditionBecomesTrueWithRefersh(boolean condition, Integer timeOut, String message) {
		logger.info(message);
		return waitConditionBecomesTrueWithRefersh(condition, timeOut);

	}

	protected void sleep(int seconds) {
		try {
			logger.info("Wait for " + seconds + " seconds");
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			logger.error("", e);
		}
	}

	protected String getLocation() {
		logger.info("Get current URL");
		return driver.getCurrentUrl();
	}

	public LoginPage verifyAmountOfEmails(int amountOfEmails, int amountOfSplits, int amountOfMinutes, boolean doFail) {
		Integer amountEmailsInSplit = amountOfEmails;
		if (amountOfSplits > 1) {
			Integer testGroup = Integer.valueOf(CommonUtils.getProperty(PropertyName.PERCENTAGE_OF_TEST_GROUP));
			float s = (float) ((float) testGroup / (float) 100);
			amountEmailsInSplit = (int) (amountOfEmails / amountOfSplits * s);

		}
		for (int i = 1; i <= amountOfSplits; i++) {
			String subj;
			if (amountOfSplits > 1) {
				subj = CommonUtils.getProperty(PropertyName.EMAIL_SPLIT_SUBJECT) + " Split " + i;
			} else {
				subj = CommonUtils.getProperty(PropertyName.EMAIL_SUBJECT);
			}
			Integer amountEmails = null;
			try {

				amountEmails = new EmailClient().waitForEmails(subj, amountEmailsInSplit, amountOfMinutes).getEmailsBySubject(subj).size();
			} catch (MailosaurException e) {
				e.printStackTrace();
			}
			if (!CommonUtils.getProperty(PropertyName.ENVIRONMENT).equalsIgnoreCase("dev")) {
				verifier.verifyEquals(amountEmails, amountEmailsInSplit, "Not all emails " + subj + " were delivered", doFail);
			} else {
				verifier.verifyTrue(amountEmails > 0, "O emails were delivered", doFail);
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
			logger.error("", e);
		}
		if (emails.size() < amount)
			amount = emails.size();
		for (int i = 0; i < amount; i++) {
			client.openEmail(emails.get(i));
			logger.info("Email for " + emails.get(i).to[0].address + " was opened");
		}
	}

	public void clickLinkInEmail(String subj, String linkText, Integer amountOfEmails) {
		EmailClient client = new EmailClient();
		ArrayList<Email> emails = null;
		try {
			emails = client.getEmailsBySubject(subj);
		} catch (MailosaurException e) {
			logger.error("", e);
		}
		if (emails.size() < amountOfEmails)
			amountOfEmails = emails.size();
		for (int i = 0; i < amountOfEmails; i++) {
			client.clickLinkByText(emails.get(i), linkText);
			logger.info("Link in the email for " + emails.get(i).to[0].address + " was clicked");
		}
	}
}
