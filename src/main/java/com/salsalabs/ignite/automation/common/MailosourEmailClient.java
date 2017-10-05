package com.salsalabs.ignite.automation.common;

import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.mailosaur.MailboxApi;
import com.mailosaur.exception.MailosaurException;
import com.mailosaur.model.Email;
import com.mailosaur.model.Link;
import com.sun.mail.smtp.SMTPTransport;

public class MailosourEmailClient implements EmailClient<Email> {

	private static final Logger logger = SeleneseTestCase.logger;

	private String mbox;
	public String apikey = "b4e4d2b193b5eb2";
	MailboxApi mBoxAPI;
	private String suffix = "@mailosaur.in";

	public MailosourEmailClient(String mbox) {
		this.mbox = mbox;
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkClientTrusted(X509Certificate[] certs, String authType) {
			}

			public void checkServerTrusted(X509Certificate[] certs, String authType) {
			}
		} };

		// Install the all-trusting trust manager
		SSLContext sc = null;
		try {
			sc = SSLContext.getInstance("SSL");
		} catch (NoSuchAlgorithmException e) {
			logger.error("", e);
		}
		try {
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
		} catch (KeyManagementException e) {
			logger.error("", e);
		}
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

		// Create all-trusting host name verifier
		HostnameVerifier allHostsValid = new HostnameVerifier() {
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		};
		HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
		mBoxAPI = getClient();
	}

	public MailosourEmailClient(boolean isForSend) {

	}

	private MailboxApi getClient() {

		return new MailboxApi(mbox, apikey);
	}

	public Email[] getEmailsByRecipient(String rec) throws MailosaurException {
		return getClient().getEmailsByRecipient(rec);
	}

	@Override
	public Email getEmailBySubject(String subj) {
		List<Email> emails = getEmailsBySubject(subj);
		if (!emails.isEmpty()) {
			return emails.get(0);
		}
		return null;
	}

	private Integer getEmailsAmount() {
		try {
			return mBoxAPI.getEmails().length;
		} catch (MailosaurException e) {
			logger.error("", e);
		}
		return 0;
	}

	@Override
	public List<Email> getEmailsBySubject(String subj) {
		SeleneseTestCase.logger.info("Try to find email with subject - " + subj);
		ArrayList<Email> result = new ArrayList<Email>();
		try {
			Email[] e = mBoxAPI.getEmails();
			for (int i = 0; i < e.length; i++) {
				if (e[i].subject.equalsIgnoreCase(subj)) {
					result.add(e[i]);
				}
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		return result;
	}

	@Override
	public String getURLByEndWord(String emailSubj, String word) {
		SeleneseTestCase.logger.info("Try to find email with subject - " + emailSubj);
		Email e = waitForEmails(emailSubj, 1, 15).getEmailBySubject(emailSubj);
		Pattern pattern = Pattern.compile("http(.*?)" + word);
		Matcher matcher = pattern.matcher(e.html.body);
		if (matcher.find())
			return matcher.group(0);
		return null;
	}

	@Override
	public String getURLByDomain(String emailSubj, String domain) {
		Email e = waitForEmails(emailSubj, 1, 15).getEmailBySubject(emailSubj);
		Pattern pattern = Pattern.compile("https://" + domain + "(.*?)" + "confirm(.*?)\" ");
		Matcher matcher = pattern.matcher(e.html.body);
		if (matcher.find())
			return matcher.group(0).replace("\" ", "");
		return null;
	}

	@Override
	public String getUnsubscribeLink(String emailSubj) {
		Email e = waitForEmails(emailSubj, 1, 15).getEmailBySubject(emailSubj);
		return getLinkByText(e, "Manage Subscription");
	}

	@Override
	public String getEmailBox(String name) {
		return name + "." + mbox + suffix;
	}

	@Override
	public void deleteAllEmails() {
		try {
			if (mBoxAPI.getEmails().length > 0) {
				logger.info("Deleting all emails from the mailbox: " + mbox);
				mBoxAPI.deleteAllEmail();
			}
		} catch (Exception e) {
			logger.warn("Unable to parse API response");
		}
	}

	@Override
	public boolean clickLinkByText(Object email, String text) {
		Link[] l = ((Email) email).html.links;
		boolean clicked = false;
		for (int j = 0; j < 2; j++) {
			if (l[j].text.equalsIgnoreCase(text)) {
				try {
					l[j].Click();
					clicked = true;
				} catch (IOException | MailosaurException e) {
					logger.error("", e);
				}
			}
		}
		return clicked;
	}

	@Override
	public String getLinkByText(Object email, String text) {
		Link[] l = ((Email) email).html.links;
		for (int j = 0; j < l.length; j++) {
			if (l[j].text.toLowerCase().contains(text.toLowerCase())) {
				return l[j].href;
			}
		}
		return null;
	}

	@Override
	public void openEmail(Object email) {
		try {
			((Email) email).open();
		} catch (IOException e) {
			logger.error("", e);
		} catch (MailosaurException e) {
			logger.error("", e);
		}
	}

	public MailosourEmailClient waitForEmails(Integer emailsAmount, Integer timeMin) {
		for (int i = 0; i < timeMin; i++) {
			if (getEmailsAmount() < emailsAmount) {
				try {
					Thread.sleep(3600);
				} catch (InterruptedException e) {
					logger.error("", e);
				}
			} else {
				break;
			}
		}
		return this;
	}

	@Override
	public MailosourEmailClient waitForEmails(String subj, Integer emailsAmount, Integer timeMin) {
		for (int i = 0; i < timeMin; i++) {
			if (getEmailsBySubject(subj).size() < emailsAmount) {
				try {
					Thread.sleep(60000);
				} catch (InterruptedException e) {
					logger.error("", e);
				}
			} else {
				break;
			}
		}
		return this;
	}

	@Override
	public int waitForEmails(String[] subjs, Integer emailsAmount, Integer timeMin) {
		int num = 0;
		for (int i = 0; i < timeMin; i++) {
			num = 0;
			for (String subj : subjs) {
				num += getEmailsBySubject(subj).size();
			}
			if (num < emailsAmount) {
				try {
					Thread.sleep(60000);
				} catch (InterruptedException e) {
					logger.error("", e);
				}
			} else {
				break;
			}
		}
		return num;
	}

	public void sendEmail(String subj, String mailMessage, File attach) throws AddressException, MessagingException {

		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

		// Get a Properties object
		Properties props = System.getProperties();
		props.setProperty("mail.smtps.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");
		props.setProperty("mail.smtps.auth", "true");

		/*
		 * If set to false, the QUIT command is sent and the connection is
		 * immediately closed. If set to true (the default), causes the
		 * transport to wait for the response to the QUIT command.
		 * 
		 * ref :
		 * http://java.sun.com/products/javamail/javadocs/com/sun/mail/smtp
		 * /package-summary.html
		 * http://forum.java.sun.com/thread.jspa?threadID=5205249 smtpsend.java
		 * - demo program from javamail
		 */
		props.put("mail.smtps.quitwait", "false");

		Session session = Session.getInstance(props, null);

		// -- Create a new message --
		final MimeMessage msg = new MimeMessage(session);

		// -- Set the FROM and TO fields --
		msg.setFrom(new InternetAddress("autoBot@salsalabs.com"));
		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("kvooturi@salsalabs.com"/* "devstaff@salsalabs.com" */, false));
		msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse("vavramchuk@salsalabs.com"/* "qa@salsalabs.com" */, false));

		msg.setSubject(subj);
		msg.setSentDate(new Date());
		// Create the message part
		BodyPart messageBodyPart = new MimeBodyPart();

		// Fill the message
		messageBodyPart.setText(mailMessage);

		// Create a multipar message
		Multipart multipart = new MimeMultipart();

		// Set text message part
		multipart.addBodyPart(messageBodyPart);

		// Part two is attachment
		messageBodyPart = new MimeBodyPart();
		DataSource source = new FileDataSource(attach.getAbsolutePath());
		messageBodyPart.setDataHandler(new DataHandler(source));
		messageBodyPart.setFileName(attach.getName());
		multipart.addBodyPart(messageBodyPart);

		// Send the complete message parts
		msg.setContent(multipart);

		SMTPTransport t = (SMTPTransport) session.getTransport("smtps");

		t.connect("smtp.gmail.com", "vavramchuk@salsalabs.com", "Vitalik3124");
		t.sendMessage(msg, msg.getAllRecipients());
		t.close();
	}

	@Override
	public String getRecipient(Object email) {
		return ((Email)email).to[0].address;
	}
	
	@Override
	public void closeConnection() {	}

	@Override
	public void setDriver(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getEmailBody(Object email) {
		// TODO Auto-generated method stub
		return null;
	}
}
