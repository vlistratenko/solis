package selenium;


import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.protocol.HttpContext;
import org.browsermob.proxy.ProxyServer;
import org.browsermob.proxy.jetty.http.HttpException;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.thoughtworks.selenium.webdriven.*;

import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

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
		/*ProxyServer server = new ProxyServer(9978);
		try {
			server.start();
		} catch (Exception e) {
			// TODO: handle exception
		}

		server.addResponseInterceptor(new HttpResponseInterceptor()
		{
		    @Override
		    public void process(HttpResponse response, HttpContext context)
		        throws HttpException, IOException
		    {
		        System.out.println(response.getStatusLine());
		        SeleneseTestCase.logger.info(response.getStatusLine());
		    }

		});

		// Get selenium proxy
		Proxy proxy = server.seleniumProxy();
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.PROXY, proxy);*/
		//System.setProperty("webdriver.firefox.profile", "driver");
		//System.setProperty("webdriver.reap_profile", "false");
		return new FirefoxDriver(/*capabilities*/);
	}
	
	private static WebDriver get_iedriver_driver() {		
		return new InternetExplorerDriver();
	}
	
}
