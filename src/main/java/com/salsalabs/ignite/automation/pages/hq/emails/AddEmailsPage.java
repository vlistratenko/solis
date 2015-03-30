package com.salsalabs.ignite.automation.pages.hq.emails;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.pages.hq.HomePage;

public class AddEmailsPage extends HomePage{
	
	Button setupTabHeader = new ButtonImpl("//a[.='Setup']", "Setup tab header");

	public AddEmailsPage_SetupTab openSetupTab() {
		
		setupTabHeader.click();
		return new AddEmailsPage_SetupTab();		
	}
}
