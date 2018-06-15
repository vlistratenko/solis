package com.salsalabs.ignite.automation.pages.p2p;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.Panel;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.PanelImpl;

public class AddP2PPage_RegistrationsTab extends AddP2PPage {
	
	Button nextButton = new ButtonImpl("//button[@id='btnGo-tickets-compose']", "Next: Event Page");
	Panel gzFrame = new PanelImpl("//iframe[@id='tickets_iframe']", "ZiveZooks panel ");
	
	public AddP2PPage_SelectLayoutTab clickNextButton() {
		nextButton.click();
		nextButton.click();
		return new AddP2PPage_SelectLayoutTab();
	}

}
