package pages.hq.activities;

import elements.Button;
import elements.impl.ButtonImpl;
import pages.hq.HomePage;
import pages.hq.emails.EmailBlastsPage;


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
