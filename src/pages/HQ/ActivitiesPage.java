package pages.HQ;

import objects.Button;
import pages.HQ.Emails.EmailBlastsPage;


public class ActivitiesPage extends HomePage{
	
	Button emailBlastsLink = new Button("//a[.='Email Blasts']", "Email blast"); 

	public ActivitiesPage verifyURL() {
		
		verify(getLocation().contains("activities"), true, "Current URL is not contains Activities");
		return this;
	}
	
	public EmailBlastsPage openEmailBlastsPage() {
		emailBlastsLink.click();
		return new EmailBlastsPage();
	}
}
