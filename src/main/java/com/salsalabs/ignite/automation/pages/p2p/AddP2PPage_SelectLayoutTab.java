package com.salsalabs.ignite.automation.pages.p2p;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;

public class AddP2PPage_SelectLayoutTab extends AddP2PPage {
	
	Button nextButton = new ButtonImpl("//button[@id='btnCompose']", "Next: Composer button");

	public AddP2PPage_EventPageTab_EventPageSubTab selectLayoutAndClickNext(String layoutCaption) {
		selectLayout(layoutCaption);
		nextButton.click();
		return new AddP2PPage_EventPageTab_EventPageSubTab();
	}
	
	
}
