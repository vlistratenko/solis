package selenium;

import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
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
import javax.mail.Transport;
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

import com.mailosaur.MailboxApi;
import com.mailosaur.exception.MailosaurException;
import com.mailosaur.model.Email;
import com.mailosaur.model.Link;
import com.sun.mail.smtp.SMTPTransport;

public class EmailClient {
	public static String mbox = "4441b2bf";
	public String apikey = "b4e4d2b193b5eb2";
	MailboxApi mBoxAPI;
	
	public EmailClient(){
		TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return null;
				}
				public void checkClientTrusted(X509Certificate[] certs, String authType) {
				}
				public void checkServerTrusted(X509Certificate[] certs, String authType) {
				}
			}
		};

		// Install the all-trusting trust manager
		SSLContext sc = null;
		try {
			sc = SSLContext.getInstance("SSL");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	
	public EmailClient(Boolean isForSend){

	}
	
	
	public MailboxApi getClient() {
		
		return new MailboxApi(mbox, apikey);
	}
	
	public Email[] getEmailsByRecipient(String rec) throws MailosaurException {
		return getClient().getEmailsByRecipient(rec);
	}
	
	public Email getEmailBySubject(String subj) throws MailosaurException{
		Email[] e = mBoxAPI.getEmails(); 
		for (int i = 0; i < e.length; i++) {
			if (e[i].subject.contains(subj)) {
				return e[i];
			}
		}
		return null;
	}
	
	public Integer getEmailsAmount(){
		try {
			return mBoxAPI.getEmails().length;
		} catch (MailosaurException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public ArrayList<Email> getEmailsBySubject(String subj) throws MailosaurException{
		Email[] e = mBoxAPI.getEmails(); 
		ArrayList<Email> result = new ArrayList<Email>();
		for (int i = 0; i < e.length; i++) {
			if (e[i].subject.equalsIgnoreCase(subj)) {
				result.add(e[i]);
			}
		}
		return result;
	}
	
	public String getURLByEndWord(String emailSubj, String word) throws MailosaurException {
		Email e = waitForEmails(emailSubj, 1, 15).getEmailBySubject(emailSubj);
		Pattern pattern = Pattern.compile("http(.*?)" + word);
        Matcher matcher = pattern.matcher(e.html.body);
        if (matcher.find()) return matcher.group(0);
		return null;
	}
	
	public String getURLByDomain(String emailSubj, String domain) throws MailosaurException {
		Email e = waitForEmails(emailSubj, 1, 15).getEmailBySubject(emailSubj);
		Pattern pattern = Pattern.compile("https://" + domain + "(.*?)" + "confirm(.*?)\" ");
        Matcher matcher = pattern.matcher(e.html.body);
        if (matcher.find()) return matcher.group(0).replace("\" ", "");
		return null;
	}
	
	public String getUnsubscribeLink(String emailSubj) throws MailosaurException {
		Email e = waitForEmails(emailSubj, 1, 15).getEmailBySubject(emailSubj);
		return getLinkByText(e, "unsubscribe");
	}
	
	
	
	public static String getEmailBox(String name){
		return name + "." + mbox + "@mailosaur.in";
	}
	
	public String openEmailBySubj(String emailSubj){

		return "." + mbox + "@mailosaur.in";
	}
	
	public void deleteAllEmails(){
		try {
			if (mBoxAPI.getEmails().length > 0) {
				mBoxAPI.deleteAllEmail();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	
	public void clickLinkByText(Email email, String text){		
		Link[] l = email.html.links;
		for (int j = 0; j < 2; j++) {
			 if (l[j].text.equalsIgnoreCase(text)) {
				 try {
					l[j].Click();
					} catch (IOException | MailosaurException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				 
			}			 
		}		 
	}
	
	public String getLinkByText(Email email, String text){		
		Link[] l = email.html.links;
		for (int j = 0; j < l.length; j++) {
			 if (l[j].text.toLowerCase().contains(text.toLowerCase())) {
				 return l[j].href;				 
			}			 
		}
		return null;
	}
	
	public void openEmail(Email email){		
		try {
			email.open();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MailosaurException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		 
	}
	
	public EmailClient waitForEmails(Integer emailsAmount, Integer timeMin){		
		for (int i = 0; i < timeMin; i++) {
			if (getEmailsAmount() < emailsAmount) {
				try {
					Thread.sleep(3600);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				break;
			}
		}
		return this;
	}
	
	public EmailClient waitForEmails(String subj, Integer emailsAmount, Integer timeMin){		
		for (int i = 0; i < timeMin; i++) {
			try {
				if (getEmailsBySubject(subj).size() < emailsAmount) {
					try {
						Thread.sleep(60000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					break;
				}
			} catch (MailosaurException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return this;
	}
	
	public void sendEmail(String subj, String mailMessage, File attach) throws AddressException, MessagingException
	   {   
			
		 Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
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
	        If set to false, the QUIT command is sent and the connection is immediately closed. If set 
	        to true (the default), causes the transport to wait for the response to the QUIT command.

	        ref :   http://java.sun.com/products/javamail/javadocs/com/sun/mail/smtp/package-summary.html
	                http://forum.java.sun.com/thread.jspa?threadID=5205249
	                smtpsend.java - demo program from javamail
	        */
	        props.put("mail.smtps.quitwait", "false");

	        Session session = Session.getInstance(props, null);

	        // -- Create a new message --
	        final MimeMessage msg = new MimeMessage(session);

	        // -- Set the FROM and TO fields --
	        msg.setFrom(new InternetAddress("autoBot@salsalabs.com"));
	        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("qa@salsalabs.com", false));
	        /*if (ccEmail.length() > 0) {
	            msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccEmail, false));
	        }*/

	        msg.setSubject(subj);
//	        msg.setText(mailMessage, "utf-8");
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
	         msg.setContent(multipart );
	        
	        SMTPTransport t = (SMTPTransport)session.getTransport("smtps");

	        t.connect("smtp.gmail.com", "vavramchuk@salsalabs.com", "Vitalik3124");
	        t.sendMessage(msg, msg.getAllRecipients());      
	        t.close();
	    }
	   
	
}
