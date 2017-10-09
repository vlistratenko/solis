package com.salsalabs.ignite.automation.common;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;
import com.salsalabs.ignite.automation.suites.CreateDEMOData;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.TimeUnit;


public abstract class Browser {
	protected static WebDriver driver;
	protected static Logger logger;
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

	public LoginPage logOut() {
		deletecoockies();
		open(SeleneseTestCase.USED_ENVIRONMENT.getBaseTestUrl() + "/#/logout");
		sleep(5);
		return new LoginPage();
	}
	
	public Browser verifyEmail(String subj) {
		verifyEmail(SeleneseTestCase.emailClient, subj);
		return this;
	}
	
	public Browser verifyEmail(EmailClient<?> emailClient, String subj) {
		emailClient.waitForEmails(subj, 1, 15);
		verifier.verifyTrue(emailClient.getEmailBySubject(subj) != null, "Email isn't received", true);
		return this;
	}
	
	public void open(String url) {
		url = url.replaceFirst("hq.uat.igniteaction.net", "hq.uat.ignite.net");
		logger.info("Try to open URL - " + url);
		SeleneseTestCase.bug.add("Open " + url);
		driver.navigate().to(url);
		driver.manage().window().maximize();
		if (driver instanceof InternetExplorerDriver) {
			Button link = new ButtonImpl("//a[@name='overridelink']", "Continue");
			sleep(15);
			if (!link.isNotExists()) {
				link.click();
			}
		}
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
		logger.info("Page is refreshed");
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
		String[] subjs = new String[amountOfSplits];
		if (amountOfSplits > 1) {
			Integer testGroup = Integer.valueOf(CommonUtils.getProperty(PropertyName.PERCENTAGE_OF_TEST_GROUP));
			float s = (float) ((float) testGroup / (float) 100);
			amountOfEmails = (int) (amountOfEmails * s);
			for (int i = 1; i <= amountOfSplits; i++) {
				subjs[i - 1] = CommonUtils.getProperty(PropertyName.EMAIL_SPLIT_SUBJECT) + " Split " + i;
			}
		} else {
			subjs[0] = CommonUtils.getProperty(PropertyName.EMAIL_SUBJECT);
		}
		int amountEmails = SeleneseTestCase.emailClient.waitForEmails(subjs, amountOfEmails, amountOfMinutes);
		verifier.verifyEquals(amountEmails, amountOfEmails, "Not all emails were delivered", doFail);
		return new LoginPage();
	}

	public List<?> openEmails(String subj, Integer amount) {
		EmailClient<?> client = SeleneseTestCase.emailClient;
		List<?> emails = null;
		emails = client.waitForEmails(subj, amount, 10).getEmailsBySubject(subj);
		if (emails.size() < amount)
			amount = emails.size();
		for (int i = 0; i < amount; i++) {
			client.openEmail(emails.get(i));
			logger.info("Email for " + client.getRecipient(emails.get(i)) + " was opened." + i + " of " + amount);
		}
		return emails;
	}
	
	public Map<String, List<?>> openEmails(Integer amountOfSplits, Integer amount) {
		Map<String, List<?>> emails = new HashMap<>();
		if (amount == 0) {
			return emails;
		}
		String subjBase = CommonUtils.getProperty(PropertyName.EMAIL_SPLIT_SUBJECT);
		for (int i = 1; i <= amountOfSplits; i++) {
			String subj;
			if (amountOfSplits > 1) {
				subj = subjBase + " Split " + i;
			} else {
				subj = CommonUtils.getProperty(PropertyName.EMAIL_SUBJECT);
			}
			emails.put(subj, openEmails(subj, amount / amountOfSplits));
		}
		return emails;
	}
	
	public void clickLinkInEmail(Map<String, List<?>> emailMap, Integer amountOfSplits, String linkText, Integer amountOfEmails) {
		if (amountOfEmails == 0) {
			return;
		}
		String subjBase = CommonUtils.getProperty(PropertyName.EMAIL_SPLIT_SUBJECT);
		for (int i = 1; i <= amountOfSplits; i++) {
			String subj;
			if (amountOfSplits > 1) {
				subj = subjBase + " Split " + i;
			} else {
				subj = CommonUtils.getProperty(PropertyName.EMAIL_SUBJECT);
			}
			clickLinkInEmail(emailMap, subj, linkText, amountOfEmails / amountOfSplits);
		}
	}
	
	public void openDonationFormAndSubmitItInEmail(Map<String, List<?>> emailMap, Integer amountOfSplits, String linkText, Integer amountOfEmails) {
		if (amountOfEmails == 0) {
			return;
		}
		String subjBase = CommonUtils.getProperty(PropertyName.EMAIL_SPLIT_SUBJECT);
		for (int i = 1; i <= amountOfSplits; i++) {
			String subj;
			if (amountOfSplits > 1) {
				subj = subjBase + " Split " + i;
			} else {
				subj = CommonUtils.getProperty(PropertyName.EMAIL_SUBJECT);
			}
			clickLinkInEmail(emailMap, subj, linkText, amountOfEmails / amountOfSplits);
		}
	}
	
	public void clickLinkInEmail(Integer amountOfSplits, String linkText, Integer amountOfEmails){
		clickLinkInEmail(null, amountOfSplits, linkText, amountOfEmails);
	}

	public void clickLinkInEmail(Map<String, List<?>> emailMap, String subj, String linkText, Integer amountOfEmails) {
		EmailClient<?> client = SeleneseTestCase.emailClient;
		List<?> emails = null;
		if (emailMap == null) {
			emails = client.getEmailsBySubject(subj);
		} else {
			emails = emailMap.get(subj);
		}
		if (emails.size() < amountOfEmails)
			amountOfEmails = emails.size();
		for (int i = 0; i < amountOfEmails; i++) {
			if (client.clickLinkByText(emails.get(i), linkText)) {
				logger.info("Link in the email for " + client.getRecipient(emails.get(i)) + " was clicked");
			}
		}
	}
	
	public void clickLinkInEmailAndFillDonationForm(Map<String, List<?>> emailMap, String subj, String linkText, Integer amountOfEmails, String login, String host) {
		EmailClient<?> client = SeleneseTestCase.emailClient;
		List<?> emails = null;
		if (emailMap == null) {
			emails = client.getEmailsBySubject(subj);
		} else {
			emails = emailMap.get(subj);
		}
		if (emails.size() < amountOfEmails)
			amountOfEmails = emails.size();
		for (int i = 0; i < amountOfEmails; i++) {
			String mainWindowhandle = getWindowHandle();
			String link = client.getLinkByText(emails.get(i), linkText);
			try {
				new CreateDEMOData().testDonateBySupporter(1, link, login, host);
			} catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException | JSONException
					| URISyntaxException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.switchToWindow(mainWindowhandle);
		}
	}
	
	
	public void unsubscribeByEmail(Map<String, List<?>> emailMap, Integer amountOfSplits, Integer amountOfEmails) {
		if (amountOfEmails == 0) {
			return;
		}
		String subjBase = CommonUtils.getProperty(PropertyName.EMAIL_SPLIT_SUBJECT);
		for (int i = 1; i <= amountOfSplits; i++) {
			String subj;
			if (amountOfSplits > 1) {
				subj = subjBase + " Split " + i;
			} else {
				subj = CommonUtils.getProperty(PropertyName.EMAIL_SUBJECT);
			}
			unsubscribeByEmail(emailMap, subj, amountOfEmails / amountOfSplits);
		}
	}
	
	public String  getEmailBody(String subj) {
		EmailClient<?> client = SeleneseTestCase.emailClient;
		List<?> emails = null;
		emails = client.getEmailsBySubject(subj);
		String msg = client.getEmailBody(emails.get(0));
		return msg;
		
	}
	
	public void unsubscribeByEmail(Integer amountOfSplits, Integer amountOfEmails) {
		unsubscribeByEmail(null, amountOfSplits, amountOfEmails);
	}
	
	public void unsubscribeByEmail(Map<String, List<?>> emailMap, String subj, Integer amountOfEmails) {
		EmailClient<?> client = SeleneseTestCase.emailClient;
		List<?> emails = null;
		if (emailMap == null) {
			emails = client.getEmailsBySubject(subj);
		} else {
			emails = emailMap.get(subj);
		}
		if (emails.size() < amountOfEmails)
			amountOfEmails = emails.size();
		for (int i = 0; i < amountOfEmails; i++) {
			new LoginPage().openUnsubscribeLinkFromEmail(subj).
			fillUnsubscribeForm(client.getRecipient(emails.get(i))).
			clickUnsubscribeButton().
			verifyUnsubscribeIsSuccesses();
		}
	}

	public void fluentWaitForElementPresenceIgnoringExceptions(final String locator){
		ArrayList<Class <? extends Throwable>> exceptionsList = new ArrayList<>();
		exceptionsList.add(NoSuchElementException.class);
		exceptionsList.add(ElementNotVisibleException.class);
		exceptionsList.add(StaleElementReferenceException.class);
		exceptionsList.add(InvalidElementStateException.class);
		long waitingTime = 30;
		long pollingInterval = 500;
		Wait<WebDriver> wait = new FluentWait<>(driver)
				.withTimeout(waitingTime, TimeUnit.SECONDS)
				.pollingEvery(pollingInterval, TimeUnit.MILLISECONDS)
				.ignoreAll(exceptionsList)
				.withMessage("Fluent wait of " + waitingTime + " seconds with " + pollingInterval +
						" milliseconds polling interval was unable to locate element with locator " + locator);

		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
	}

	protected Alert switchToAlert() {
		return driver.switchTo().alert();
	}
	


}
