package JustForTestingOfCode;

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

import com.mailosaur.model.Email;
import com.mailosaur.model.Link;

import selenium.EmailClient;

import java.security.cert.X509Certificate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Example {
	public static void main(String[] args) throws Exception {
		Email[] e = new EmailClient().getEmailsByRecipient("example.b7b87c85@mailosaur.in");
		String l = null;
		for (int i = 0; i < e.length; i++) {
			if (e[i].subject.equalsIgnoreCase("Welcome to Ignite HQ")) {
				Pattern pattern = Pattern.compile("https(.*?)completion");
		        Matcher matcher = pattern.matcher(e[i].html.body);
		        if (matcher.find()) System.out.println(matcher.group(0));
			}
		}
		
	}
}
