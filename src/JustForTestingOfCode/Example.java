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

import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.ProxyServer;
import net.lightbody.bmp.proxy.http.BrowserMobHttpResponse;
import net.lightbody.bmp.proxy.http.ResponseInterceptor;

import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.protocol.HttpContext;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
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
			public void process(BrowserMobHttpResponse arg0, Har arg1) {
				System.out.println(arg0.getBody());
				//System.out.println(arg0.getHeader(name));
				//System.out.println(arg0.getBody());
				
			}

		});

		// Get selenium proxy
		Proxy proxy = server.seleniumProxy();
		proxy.setHttpProxy("localhost:9101");
		// Configure desired capability for using proxy server with WebDriver
		 FirefoxProfile profile = new FirefoxProfile();
	        profile.setAcceptUntrustedCertificates(true);
	        profile.setAssumeUntrustedCertificateIssuer(true);
	        profile.setPreference("network.proxy.http", "localhost");
	        profile.setPreference("network.proxy.http_port", 9101);
	        profile.setPreference("network.proxy.ssl", "localhost");
	        profile.setPreference("network.proxy.ssl_port", 9101);
	        profile.setPreference("network.proxy.type", 1);
	        profile.setPreference("network.proxy.no_proxies_on", "");

	        
	        //capabilities.setCapability(CapabilityType.PROXY, server.seleniumProxy());

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        capabilities.setCapability(FirefoxDriver.PROFILE, profile);
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
