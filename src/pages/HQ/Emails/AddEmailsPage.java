package pages.HQ.Emails;

import objects.Button;
import pages.HQ.HomePage;

public class AddEmailsPage extends HomePage{
	
	Button setupTabHeader = new Button("//a[.='Setup']", "Setup tab header");

	public AddEmailsPage_SetupTab openSetupTab() {
		
		setupTabHeader.click();
		return new AddEmailsPage_SetupTab();		
	}
}
