package com.salsalabs.ignite.automation.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.activation.DataHandler;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.mail.search.SubjectTerm;

import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;

public class SquirrelEmailClient extends Browser implements EmailClient<Message> {

	private static final String innerImapHost = "ignite-mailstore1.ignite.net";
	private static final String password = "ignite";
	private static final String suffix = ".ignite.net";

	private static final Logger logger = SeleneseTestCase.logger;
	
	private String user = "testauto";
	private String domain = "@example.ignite.net";
	
	private Folder folder;
	private Store store;
	
	public SquirrelEmailClient(String user) {
		this.user = user;
		this.domain = "@" + user + suffix;
	}

	@Override
	public String getEmailBox(String name) {
		return name + domain;
	}

	@Override
	public void openEmail(Object message) {
		Document doc = getDocument((Message) message);
		Elements imgs = doc.getElementsByTag("img");
		for (Iterator<Element> itr = imgs.iterator(); itr.hasNext();) {
			Element img = itr.next();
			String primaryHandle = this.getWindowHandle();
			String src = img.attr("src");
			if (!src.contains("logo") && !src.equalsIgnoreCase("")) {
				this.openInNewWindow(src);
				this.closeWindow();
				this.switchToWindow(primaryHandle);
			}
		}

	}

	@Override
	public boolean clickLinkByText(Object message, String text) {
		Document doc = getDocument((Message) message);
		Elements as = doc.getElementsByTag("a");
		boolean clicked = false;
		for (Iterator<Element> itr = as.iterator(); itr.hasNext();) {
			Element a = itr.next();
			if (text.equalsIgnoreCase(a.text())) {
				String primaryHandle = this.getWindowHandle();
				this.openInNewWindow(a.attr("href"));
				this.closeWindow();
				this.switchToWindow(primaryHandle);
				clicked = true;
			}
		}
		return clicked;
	}

	@Override
	public String getLinkByText(Object message, String text) {
		Document doc = getDocument((Message) message);
		Elements as = doc.getElementsByTag("a");
		for (Iterator<Element> itr = as.iterator(); itr.hasNext();) {
			Element a = itr.next();
			if (a.text().toLowerCase().contains(text.toLowerCase())) {
				return a.attr("href");
			}
		}
		return null;
	}

	private Document getDocument(Message message) {
		return Jsoup.parse(getContent(message));
	}

	@Override
	public SquirrelEmailClient waitForEmails(String subj, Integer emailsAmount, Integer timeMin) {
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
			num = getEmailsBySubjects(subjs).size();
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

	@Override
	public List<Message> getEmailsBySubject(String subject) {
		String[] subjs = new String[] { subject };
		return getEmailsBySubjects(subjs);
	}
	
	private Folder getFolder(int status){
		if (folder != null && folder.isOpen()) {
			return folder;
		}
		try {
			folder = getStore().getFolder("INBOX");
			folder.open(status);
		} catch (Exception ex) {
			logger.error("", ex);
		}
		return folder;
	}
	
	private Store getStore(){
		if (store != null && store.isConnected()) {
			return store;
		}
		Session session = Session.getDefaultInstance(new Properties(), null);
		// session.setDebug(true);
		try {
			store = session.getStore("imap");
			store.connect(innerImapHost, user, password);
		} catch (Exception ex) {
			logger.error("", ex);
		}
		return store;
	}

	@Override
	public void deleteAllEmails() {
		Folder inbox = getFolder(Folder.READ_WRITE);
		try {
			Message[] messages = inbox.getMessages();
			logger.info("Deleting emails. Total emails " + messages.length);
			int i = messages.length;
			for (Message msg : messages) {
				msg.setFlag(Flags.Flag.DELETED, true);
				i--;
				logger.info("Emails left " + i);				
			}
		} catch (Exception ex) {
			logger.error("", ex);
		} finally {
			try {
				inbox.close(true);
			} catch (MessagingException e) {
				logger.error("", e);
			}
		}
	}

	private List<Message> getEmailsBySubjects(String[] subjects) {
		List<Message> msgs = new ArrayList<>();
		try {
			Folder inbox = getFolder(Folder.READ_ONLY);
			Message[] messages = inbox.getMessages();
			for (String subj : subjects) {
				logger.info("Try to find email with subject - " + subj);
				msgs.addAll(Arrays.asList(inbox.search(new SubjectTerm(subj), messages)));
				logger.info(msgs.size() + " emails with subject - " + subj + " were found");
			}
		} catch (Exception ex) {
			logger.error("", ex);
		}
		return msgs;
	}
	
	@Override
	public void closeConnection(){
		try {
			if (folder != null && folder.isOpen())
				folder.close(false);
			if (store != null && store.isConnected())
				store.close();
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Override
	public String getUnsubscribeLink(String emailSubj) {
		Message e = waitForEmails(emailSubj, 1, 15).getEmailBySubject(emailSubj);
		return getLinkByText(e, "Manage Subscription");
	}

	@Override
	public Message getEmailBySubject(String subj) {
		List<Message> emails = getEmailsBySubject(subj);
		if (!emails.isEmpty()) {
			return emails.get(emails.size() - 1);
		}
		return null;
	}
	
	@Override
	public String getURLByDomain(String emailSubj, String domain) {
		Message e = waitForEmails(emailSubj, 1, 15).getEmailBySubject(emailSubj);
		Pattern pattern = Pattern.compile("https://" + domain + "(.*?)" + "confirm(.*?)\" ");
		Matcher matcher;
		matcher = pattern.matcher(getContent(e));
		if (matcher.find()) {
			return matcher.group(0).replace("\" ", "");
		}
		return null;
	}

	@Override
	public String getURLByEndWord(String emailSubj, String word) {
		SeleneseTestCase.logger.info("Try to find email with subject - " + emailSubj);
		Message e = waitForEmails(emailSubj, 1, 15).getEmailBySubject(emailSubj);
		Pattern pattern = Pattern.compile("http(.*?)" + word);
		Matcher matcher = pattern.matcher(getContent(e));
		if (matcher.find()) {
			return matcher.group(0).replace("\" ", "");
		}
		return null;
	}

	@Override
	public String getRecipient(Object email) {
		try {
			return ((InternetAddress) ((Message) email).getAllRecipients()[0]).getAddress();
		} catch (MessagingException e) {
			logger.error("", e);
		}
		return null;
	}
	
	@Override
	public String getEmailBody(Object email) {
		try {
			return ((Message) email).getContent().toString();
		} catch (IOException | MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private String getContent(Message msg) {
		String content = "";
		try {
			Object msgContent = msg.getContent();
			if (msgContent instanceof Multipart) {
				Multipart multipart = (Multipart) msgContent;
				for (int j = 0; j < multipart.getCount(); j++) {
					BodyPart bodyPart = multipart.getBodyPart(j);
					String disposition = bodyPart.getDisposition();
					if (disposition != null && (disposition.equalsIgnoreCase("ATTACHMENT"))) {
						@SuppressWarnings("unused")
						DataHandler handler = bodyPart.getDataHandler();
					} else {
						content = getText(bodyPart);
					}
				}
			} else {
				content = msg.getContent().toString();
			}
		} catch (IOException | MessagingException e) {
			logger.error("", e);
		}
		return content;
	}

	private String getText(Part p) throws MessagingException, IOException {
		if (p.isMimeType("text/*")) {
			String s = (String) p.getContent();
			return s;
		}

		if (p.isMimeType("multipart/alternative")) {
			// prefer html text over plain text
			Multipart mp = (Multipart) p.getContent();
			String text = null;
			for (int i = 0; i < mp.getCount(); i++) {
				Part bp = mp.getBodyPart(i);
				if (bp.isMimeType("text/plain")) {
					if (text == null)
						text = getText(bp);
					continue;
				} else if (bp.isMimeType("text/html")) {
					String s = getText(bp);
					if (s != null)
						return s;
				} else {
					return getText(bp);
				}
			}
			return text;
		} else if (p.isMimeType("multipart/*")) {
			Multipart mp = (Multipart) p.getContent();
			for (int i = 0; i < mp.getCount(); i++) {
				String s = getText(mp.getBodyPart(i));
				if (s != null)
					return s;
			}
		}

		return null;
	}
	

	@Override
	public void setDriver(WebDriver driver) {
		// TODO Auto-generated method stub
		Browser.driver = driver;
	}


}
