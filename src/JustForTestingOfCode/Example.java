package JustForTestingOfCode;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import net.lightbody.bmp.proxy.ProxyServer;
import net.lightbody.bmp.proxy.http.BrowserMobHttpResponse;
import net.lightbody.bmp.proxy.http.ResponseInterceptor;

import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.protocol.HttpContext;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.mailosaur.model.Email;
import com.mailosaur.model.Link;

import selenium.EmailClient;

import java.security.cert.X509Certificate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Example {
	public static void main(String[] args) throws Exception {
		// Start the BrowserMob proxy
		ProxyServer server = new ProxyServer(9101);
		try {
			server.start();
		} catch (Exception e) {
			// TODO: handle exception
		}

		server.addResponseInterceptor(new ResponseInterceptor()
		{
			@Override
			public void process(BrowserMobHttpResponse arg0) {
				System.out.println(arg0.getBody());
				
			}

		});

		// Get selenium proxy
		Proxy proxy = server.seleniumProxy();
		proxy.setHttpProxy("localhost:9101");
		// Configure desired capability for using proxy server with WebDriver
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.PROXY, proxy);

		// Set up driver
		try {
			WebDriver driver = new FirefoxDriver(capabilities);
			//server.newHar("mail.ru"); 
			//server.whitelistRequests("https://mail.ru,https://hq.test.ignite.net,http://jenkins.salsalabs.net/".split(","), 200);
			driver.get("https://hq.test.ignite.net");

			// Close the browser
			driver.quit();
			server.stop();
		} catch (Exception e) {
			server.stop();
		}
		
	}
}
