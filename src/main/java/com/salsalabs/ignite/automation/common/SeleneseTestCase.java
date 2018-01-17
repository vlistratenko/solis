package com.salsalabs.ignite.automation.common;

import com.salsalabs.ignite.automation.common.Environment.LocationOfServer;
import com.salsalabs.ignite.automation.common.config.DriverType;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import org.testng.annotations.Optional;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import static com.salsalabs.ignite.automation.common.config.DriverType.determineEffectiveDriverType;

@Listeners({ TestListener.class, RetryTestListener.class })
public class SeleneseTestCase {
	
	private static List<WebDriver> webDriverPool = Collections.synchronizedList(new ArrayList<WebDriver>());
	private static ThreadLocal<WebDriver> driverThread;
	public static Environment USED_ENVIRONMENT;
	public static Integer defaultTimeOut = 30;
	public static int cTimeOut = 1000;
	public static WebDriver driver;
	public static Logger logger;
	public static boolean isDebugMode = false;
	protected boolean createIssues = false;
	public static ArrayList<String> bug = new ArrayList<String>();
	public static EmailClient<?> emailClient;
	public static EmailClient<?> mailosaur;
	
	static {
		System.setProperty("log4j.configurationFile", "log4j.xml");
		logger = LogManager.getLogger(SeleneseTestCase.class);
	}
	
	@Parameters({ "USED_BROWSER", "USED_ENVIRONMENT", "USED_SERVER" })
	@BeforeTest(alwaysRun = true)
	protected void beforeSuite(@Optional("FIREFOX") String bpath, @Optional("TEST") String TestEnv, @Optional("LOCAL") String locationServer) throws Exception {
		if (System.getProperty("USED_ENVIRONMENT") != null) {
			TestEnv = System.getProperty("USED_ENVIRONMENT");
		}
		if (System.getProperty("USED_SERVER") != null) {
			locationServer = System.getProperty("USED_SERVER");
		}
		if (System.getProperty("USED_BROWSER") != null) {
			bpath = System.getProperty("USED_BROWSER");
		}
		USED_ENVIRONMENT = new Environment(TestEnv, locationServer);
		emailClient = USED_ENVIRONMENT.getSquirrelEmailClient();//getMailosourEmailClient();//
		mailosaur = USED_ENVIRONMENT.getSquirrelEmailClient();//getMailosourEmailClient();
		setAdminEmail();
		//emailClient.deleteAllEmails();
		
		if (USED_ENVIRONMENT.getServer().equals(LocationOfServer.LOCAL)) {
			startTestOnDriver(bpath, USED_ENVIRONMENT.getBaseTestUrl());
		} else {
			startTestOnDriver(bpath, USED_ENVIRONMENT.getBaseTestUrl());//startRemouteTestOnDriver("FF30", "0.16", "Win7x64-C1");
		}
	}
	
	private void setAdminEmail() {
		String adminEmail = CommonUtils.getProperty(PropertyName.ADMIN_EMAIL_BASE);
		adminEmail = mailosaur.getEmailBox(adminEmail);
		CommonUtils.setProperty(PropertyName.ADMIN_EMAIL, adminEmail);
		CommonUtils.setProperty(PropertyName.ADMIN_PASSWORD, "11111111");
		CommonUtils.setProperty(PropertyName.ADMIN_ORG_NAME, "automated_testing1");
	}
	
	public static WebDriver getDriver() {
        return driverThread.get();
    }

	public WebDriver startTestOnDriver(String bpath, String testURL) throws Exception {
		final DriverType desiredDriver = determineEffectiveDriverType(bpath);

		driverThread = new ThreadLocal<WebDriver>() {
			@Override
			protected WebDriver initialValue() {
				final WebDriver webDriver = desiredDriver.configureDriverBinaryAndInstantiateWebDriver();
				webDriverPool.add(webDriver);
				return webDriver;
			}
		};
		driver = getDriver();
		return driver;
	}
	
	protected void beforeTest() {
		bug.clear();
	}

	protected void beforeMethod() {
		System.err.println("Test");
	}

	@AfterTest(alwaysRun = true)
	protected void stopTestOnDriver() throws Exception {
		emailClient.closeConnection();
		driver.manage().deleteAllCookies();
		close();
	}

	protected void beforeClass() {

	}

	@BeforeGroups(groups = { "register_new_user_by_google", "confirm_registration_new_user_by_google" })
	protected void beforeGoogleStart() throws Exception {
		closeGoogleSession();
	}

	protected void beforeUserTests() throws Exception {
		driver.get(USED_ENVIRONMENT.getBaseTestUrl());
		implicityWait(defaultTimeOut);
	}

	protected void beforeAdminTests() throws Exception {
		driver.get(USED_ENVIRONMENT.getBaseAdminUrl());
		implicityWait(defaultTimeOut);
	}

	protected void startTestOnDriver(String bpath) throws Exception {
		startTestOnDriver(bpath, USED_ENVIRONMENT.getBaseTestUrl());
	}

	protected void closeGoogleSession() {
		logger.info("LogOut from google and back to the test page");
		driver.navigate().to("https://mail.google.com/mail/?logout&hl=ru");
		deletecoockies();
		driver.navigate().to(USED_ENVIRONMENT.getBaseTestUrl());

	}

	protected static Dimension getScreenSize() {
		Integer width = Toolkit.getDefaultToolkit().getScreenSize().width;
		Integer height = Toolkit.getDefaultToolkit().getScreenSize().height;
		return new Dimension(width - 5, height - 5);

	}

	public static void deletecoockies() {
		logger.info("Try to delete coockies");
		driver.manage().deleteAllCookies();
	}

	public static Set<Cookie> getCoockies() {
		logger.info("Try to get coockies");
		return driver.manage().getCookies();
	}

	public static void close() {
		logger.info("Try to close selenium");
		driver.quit();
	}

	public static void closeWindow() {
		logger.info("Try to close window");
		driver.close();
	}

	/**
	 * @param timeOut
	 *            in seconds
	 */
	public static void implicityWait(Integer timeOut) {
		logger.debug("Set implicitlyWait to " + timeOut);
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}

	public static File makeScreenshot(String filename) {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File file = new File("test-output\\fail-screenshots\\" + filename + ".png");
		logger.info("Screenshot was saved to " + file.getAbsoluteFile());
		try {
			FileUtils.copyFile(scrFile, file.getAbsoluteFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}
	
	public void startRemouteTestOnDriver(String browser, String build, String os) throws MalformedURLException {
		String login = "kvooturi@salsalabs.com";
		try {
			login = URLEncoder.encode(login, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("", e);
		}
		DesiredCapabilities caps = new DesiredCapabilities();

		switch (browser) {
		case "FF30":
			FirefoxProfile profile = new FirefoxProfile();
			profile.setAssumeUntrustedCertificateIssuer(false);
			caps = DesiredCapabilities.firefox();
			caps.setCapability(FirefoxDriver.PROFILE, profile);
			break;
		case "Chrome34":
			caps = DesiredCapabilities.chrome();
			caps.setCapability("chrome.switches", Arrays.asList("--ignore-certificate-errors"));
			break;
		case "IE10":
			caps = DesiredCapabilities.internetExplorer();
			caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			break;
		}

		caps.setCapability("browser_api_name", browser);
		if (build != null) {
			caps.setCapability("build", build);
		}
		caps.setCapability("os_api_name", os);
		caps.setCapability("name", "Selenium Test Example");
		caps.setCapability("screen_resolution", "1024x768");
		caps.setCapability("record_video", "true");
		caps.setCapability("record_network", "true");
		caps.setCapability("record_snapshot", "false");
		SeleneseTestCase.driver = new RemoteWebDriver(new URL("http://" + login + ":u5d0be5af7471cff@hub.crossbrowsertesting.com:80/wd/hub"), caps);
	}

	protected List<LogEntry> getJsConsoleErrors() {
		logger.info("Checking console errors");
		if (driver instanceof ChromeDriver) {
			List<LogEntry> logs = driver.manage().logs().get(LogType.BROWSER).filter(Level.SEVERE);
			if (!logs.isEmpty()) {
				for (int i = 0; i < logs.size(); i++) {
					logger.error("Console error: " + logs.get(i));
				}
			}else logger.info("Console is empty");
			return logs;
		}
		else logger.error("JS console errors retrieval available for chrome driver only");
		return null;
		/*throw new UnsupportedOperationException("JS console errors retrieval available for firefox driver only");*/
	}
}
