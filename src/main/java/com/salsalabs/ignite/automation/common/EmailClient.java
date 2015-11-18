package com.salsalabs.ignite.automation.common;

import java.util.List;

import org.openqa.selenium.WebDriver;

public interface EmailClient<T> {
	
	String getEmailBox(String name);

	void openEmail(Object message);

	boolean clickLinkByText(Object message, String text);

	String getLinkByText(Object message, String text);

	EmailClient<T> waitForEmails(String subj, Integer emailsAmount, Integer timeMin);

	int waitForEmails(String[] subjs, Integer emailsAmount, Integer timeMin);

	List<T> getEmailsBySubject(String subject);

	void deleteAllEmails();

	String getUnsubscribeLink(String emailSubj);

	T getEmailBySubject(String subj);

	String getURLByDomain(String emailSubj, String domain);

	String getURLByEndWord(String emailSubj, String word);
	
	String getRecipient(Object email);

	void closeConnection();
	
	void setDriver(WebDriver driver);
}
