package selenium;


import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class Driver {
	
	public WebDriver getDriver(String driver) {
		if (driver.equals("*firefox")) {
			
			try {
				return getFirefoxDriver();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
	
	private static WebDriver getChromeDiver(){		
		System.setProperty("webdriver.chrome.driver", "lib/chromedriver.exe");
		return new ChromeDriver();
	}
	
	private static WebDriver getIEDriver() {
		System.setProperty("webdriver.ie.driver", "lib/IEDriverServer.exe");
		return new InternetExplorerDriver();
	}
	
}
