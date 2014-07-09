package selenium;


import java.io.IOException;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.thoughtworks.selenium.webdriven.*;
import org.openqa.selenium.ie.InternetExplorerDriver;
import com.thoughtworks.selenium.Selenium;

public class Driver {
	
	public WebDriver get_web_driver_from_selenium(Selenium selenium) {
		return ((WebDriverBackedSelenium) selenium).getWrappedDriver();
	}
	
	public Selenium getSeleniumFromDriver(WebDriver driver, String baseUrl) {
		return  new WebDriverBackedSelenium(driver, baseUrl);
	}
	
	public WebDriver get_driver(String driver) {
		if (driver.equals("*firefox")) {
			
			try {
				return get_firefox_driver();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (driver.equals("*iexplore")) {
			return get_iedriver_driver();
		}
		return null;
	}
	
	private static WebDriver get_firefox_driver() throws IOException {		
		//System.setProperty("webdriver.firefox.profile", "driver");
		//System.setProperty("webdriver.reap_profile", "false");
		return new FirefoxDriver();
	}
	
	private static WebDriver get_iedriver_driver() {		
		return new InternetExplorerDriver();
	}
	
}
