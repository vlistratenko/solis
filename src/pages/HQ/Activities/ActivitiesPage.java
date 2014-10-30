package pages.HQ.Activities;

import objects.Button;
import pages.HQ.HomePage;
import pages.HQ.Emails.EmailBlastsPage;


public class ActivitiesPage extends HomePage{
	
	Button emailBlastsLink = new Button("//a[.='Email Blasts']", "Email blast"); 
	Button fundraisingWidgetLink = new Button("//a[.='Fundraising Widgets']", "Fundraising Widget");

	public ActivitiesPage verifyURL() {
		
		verify(getLocation().contains("activities"), true, "Current URL is not contains Activities");
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
}
