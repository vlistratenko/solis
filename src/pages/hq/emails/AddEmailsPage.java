package pages.hq.emails;

import elements.Button;
import elements.impl.ButtonImpl;
import pages.hq.HomePage;

public class AddEmailsPage extends HomePage{
	
	Button setupTabHeader = new ButtonImpl("//a[.='Setup']", "Setup tab header");

	public AddEmailsPage_SetupTab openSetupTab() {
		
		setupTabHeader.click();
		return new AddEmailsPage_SetupTab();		
	}
}
