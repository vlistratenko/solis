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

import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.protocol.HttpContext;
import org.browsermob.proxy.ProxyServer;
import org.browsermob.proxy.jetty.http.HttpException;




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
		ProxyServer server = new ProxyServer(8888);
		server.start();

		server.addResponseInterceptor(new HttpResponseInterceptor()
		{
		    @Override
		    public void process(HttpResponse response, HttpContext context)
		        throws HttpException, IOException
		    {
		        System.out.println(response.getStatusLine());
		    }

		});

		// Get selenium proxy
		Proxy proxy = server.seleniumProxy();
		proxy.setHttpProxy("localhost:8888");
		// Configure desired capability for using proxy server with WebDriver
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.PROXY, proxy);

		// Set up driver
		try {
			WebDriver driver = new FirefoxDriver(capabilities);

			driver.get("https://hq.test.ignite.net");

			// Close the browser
			driver.quit();
			server.stop();
		} catch (Exception e) {
			server.stop();
		}
		
	}
}
