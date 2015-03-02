package core.util;


import java.io.IOException;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class Driver {
	
	private static final Logger logger = SeleneseTestCase.logger;
	
	public WebDriver getDriver(String driver) {
		if (driver.equals("*firefox")) {
			
			try {
				return getFirefoxDriver();
			} catch (IOException e) {
				logger.error("", e);
			}
		}
		
		if (driver.equals("*iexplorer")) {
			return getIEDriver();
		}
		
		if (driver.equals("*chrome")) {
			return getChromeDiver();
		}
		return null;
	}
	
	private static WebDriver getFirefoxDriver() throws IOException {		
		return new FirefoxDriver(/*capabilities*/);
	}
	
	private static WebDriver getChromeDiver(){		
		System.setProperty("webdriver.chrome.driver", "lib/chromedriver.exe");
		return new ChromeDriver();
	}
	
	private static WebDriver getIEDriver() {
		System.setProperty("webdriver.ie.driver", "lib/IEDriverServer.exe");
		return new InternetExplorerDriver();
	}
	
}
