package selenium;

import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Optional;

import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.testng.SauceOnDemandAuthenticationProvider;
import com.thoughtworks.selenium.Selenium;


//@Listeners({SauceOnDemandTestListener.class})
public class SeleneseTestCase implements SauceOnDemandSessionIdProvider, SauceOnDemandAuthenticationProvider { 
	
	protected static Driver Driver = new Driver();
	
	public static Environment USED_ENVIRONMENT;
	
	//protected static IDatabaseConnection connect;
	
	public static Selenium selenium = null;
	
	public static Integer defaultTimeOut = 30;
	
	public static int cTimeOut = 1000;	
	
	public static String browser;
	
	public static WebDriver driver;
	public static Logger logger;
	protected String Browser;
	
	protected boolean createIssues = false;
	public static ArrayList<String> bug = new ArrayList<String>();
	
	 /**
     * Constructs a {@link com.saucelabs.common.SauceOnDemandAuthentication} instance using the supplied user name/access key.  To use the authentication
     * supplied by environment variables or from an external file, use the no-arg {@link com.saucelabs.common.SauceOnDemandAuthentication} constructor.
     */
    public SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication("vavramchuk", "70fef8a9-3f9e-418c-abaa-0b588f9b17dc");

    /**
     * ThreadLocal variable which contains the  {@link WebDriver} instance which is used to perform browser interactions with.
     */
    private ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();

    /**
     * ThreadLocal variable which contains the Sauce Job Id.
     */
    private ThreadLocal<String> sessionId = new ThreadLocal<String>();
	
	public void startTestOnDriver(String bpath, String testURL) throws Exception {		
		driver = Driver.get_driver(bpath);		
		browser = bpath;
		logger.info("Open home page - " + testURL);			
		selenium = Driver.getSeleniumFromDriver(driver, testURL);
		if (!(bpath.equalsIgnoreCase("*android") || bpath.equalsIgnoreCase("*html"))) {
			driver.manage().window().setSize(getScreenSize());
		}		
	}
	
	public void startRemouteTestOnDriver(String browser, String build, String os) throws MalformedURLException {
		String login = "kvooturi@salsalabs.com";
		try {
			login = URLEncoder.encode(login, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	public void startRemouteTestOnDriverOld(String browser, String version, String os) throws MalformedURLException {
		DesiredCapabilities caps = new DesiredCapabilities();
		switch (browser) {
        case "firefox":
            FirefoxProfile profile = new FirefoxProfile();
            profile.setAssumeUntrustedCertificateIssuer(false);
            caps = DesiredCapabilities.firefox();
            caps.setCapability(FirefoxDriver.PROFILE, profile);
            break;
        case "chrome":
        	caps = DesiredCapabilities.chrome();
        	caps.setCapability("chrome.switches", Arrays.asList("--ignore-certificate-errors"));
            break;
        case "ie":
        	caps = DesiredCapabilities.internetExplorer();
        	caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            break;
    }
		
		caps.setCapability(CapabilityType.BROWSER_NAME, browser);
        if (version != null) {
        	caps.setCapability(CapabilityType.VERSION, version);
        }
        caps.setCapability(CapabilityType.PLATFORM, os);
        SeleneseTestCase.driver = new RemoteWebDriver(
                new URL("http://" + authentication.getUsername() + ":" + authentication.getAccessKey() + "@ondemand.saucelabs.com:80/wd/hub"),
                caps);
	}
	
	protected static void getLogger() {				
		File propertiesFile = new File("lib\\apache-log4j-1.2.16\\log4j.properties")
		.getAbsoluteFile();	 	
		PropertyConfigurator.configure(propertiesFile.toString());	 	
		logger = Logger.getLogger(SeleneseTestCase.class);
	}
	
	
	//@Parameters({"bpath", "login", "pass"})
	@Parameters({"bpath", "USED_ENVIRONMENT", "USED_SERVER"})
	@BeforeSuite(alwaysRun=true)
	protected void beforeSuite(@Optional("*firefox") String bpath, @Optional("TEST") String TestEnv, @Optional("LOCAL") String locationServer) throws Exception {
		if (System.getProperty("USED_ENVIRONMENT") != null) {
			TestEnv = System.getProperty("USED_ENVIRONMENT");
		}		
		if (System.getProperty("USED_SERVER") != null) {
			locationServer = System.getProperty("USED_SERVER");
		}
		USED_ENVIRONMENT = new Environment(TestEnv, locationServer);
		getLogger();
		new EmailClient().deleteAllEmails();
		if (!CommonUtils.getParam("browser", false).equals("false")) {
			bpath = CommonUtils.getParam("browser");
		}else if (bpath.equalsIgnoreCase("")) {
			bpath = CommonUtils.getProperty("bpath");
		}
		if (USED_ENVIRONMENT.server.equals(USED_ENVIRONMENT.server.LOCAL)) {
			startTestOnDriver(bpath, USED_ENVIRONMENT.getBaseTestUrl());
		}else{
			startRemouteTestOnDriver("FF30","0.16", "Win7x64-C1");
		}
	}
	
	//@BeforeTest
	protected void beforeTest() {
		bug.clear();
	}
	
	//@BeforeMethod
	protected void beforeMethod() {

	}


	@AfterSuite(alwaysRun=true)
	protected void stopTestOnDriver() throws Exception {
		driver.manage().deleteAllCookies();
		close();			
		//connect.close();
	 }
	
	//@BeforeClass
	protected void beforeClass() {

	}
	
	@BeforeGroups(groups ={"register_new_user_by_google",
			"confirm_registration_new_user_by_google"})
	protected void beforeGoogleStart() throws Exception {
		closeGoogleSession();		
	 }
	
	//@BeforeGroups(groups = {"acceptanceTests.user"})
	protected void beforeUserTests() throws Exception {		
		driver.get(USED_ENVIRONMENT.getBaseTestUrl());
		implicityWait(defaultTimeOut);		
	 }
	//
	//@BeforeGroups(groups ={"acceptanceTests.admin"})
	protected void beforeAdminTests() throws Exception {		
		driver.get(USED_ENVIRONMENT.getBaseAdminUrl());
		implicityWait(defaultTimeOut);		
	}

	protected void startTestOnDriver(String bpath) throws Exception {
		startTestOnDriver(bpath, USED_ENVIRONMENT.getBaseTestUrl());
	}
	
	protected void closeGoogleSession(){		
		logger.info("LogOut from google and back to the test page");		
		driver.navigate().to("https://mail.google.com/mail/?logout&hl=ru");		
		deletecoockies();		
		driver.navigate().to(USED_ENVIRONMENT.getBaseTestUrl());
				
	}
	
	protected static Dimension getScreenSize() {
		Integer width = Toolkit.getDefaultToolkit().getScreenSize().width;
		Integer height = Toolkit.getDefaultToolkit().getScreenSize().height;
		return new Dimension(width-5, height-5);
		
	}
	
	public static void deletecoockies(){
		logger.info("Try to delete coockies");
		driver.manage().deleteAllCookies();
	}
	
	public static void close(){
		logger.info("Try to close selenium");
		driver.quit();	
	}
	
	/**
	 * @param timeOut in seconds
	 */
	public static void implicityWait(Integer timeOut){		
		logger.debug("Set implicitlyWait to " + timeOut);
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);				
	}
	
	
	public static File makeScreenshot(String filename){
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        /*filename = filename + "_" + CommonUtils.getUnicName();*/
        File file = new File("test-output\\" + filename + ".png");
        logger.info("Screnshot was saved to " +  file.getAbsoluteFile());
        try {
        	FileUtils.copyFile(scrFile, new File("test-output\\"
        			+ filename + ".png").getAbsoluteFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
        return file;
    }
    /**
    *
    * @return the Sauce Job id for the current thread
    */
   public String getSessionId() {
       return sessionId.get();
   }

   /**
    *
    * @return the {@link SauceOnDemandAuthentication} instance containing the Sauce username/access key
    */
   @Override
   public SauceOnDemandAuthentication getAuthentication() {
       return authentication;
   }

   //public @Rule
   //SauceOnDemandTestWatcher resultReportingTestWatcher = new SauceOnDemandTestWatcher(this, authentication);

}
