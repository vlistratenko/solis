package com.salsalabs.ignite.automation.pages.hq.activities;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.pages.hq.HomePage;
import com.salsalabs.ignite.automation.pages.hq.emails.EmailBlastsPage;


public class ActivitiesPage extends HomePage{
	
	Button emailBlastsLink = new ButtonImpl("//a[.='Manage Emails']", "Email blast"); 
	Button fundraisingWidgetLink = new ButtonImpl("//a[.='Raise Money']", "Fundraising Widget");
	Button subscribeWidgetLink = new ButtonImpl("//a[.='Grow Your List']", "Grow Your List");

	public ActivitiesPage verifyURL() {
		verifier.verifyTrue(getLocation().contains("activities"), "Current URL is not contains Activities");
		return this;
	}
	
	public EmailBlastsPage openEmailBlastsPage() {
		emailBlastsLink.click();
		return new EmailBlastsPage();
	}
	
	public FundraisingWidgetPage openFundraisingWidgetPage() {
		fundraisingWidgetLink.click();
		return new FundraisingWidgetPage();
	}

	public SubscribeWidgetPage openSubscribeWidgetsPage() {
		subscribeWidgetLink.click();
		return new SubscribeWidgetPage();
	}
}
