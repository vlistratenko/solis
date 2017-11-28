package com.salsalabs.ignite.automation.pages.p2p;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;

public class AddP2PPage_AutorespondersTab extends AddP2PPage {

	Button publishButton = new ButtonImpl("//button[@id='btnGo-autoresponders-publish']", "Publish button");
	
	public AddP2PPage_PublishedDeatailsTab clickPublishButton() {
		publishButton.waitElement(15);
		publishButton.click();
		logger.info("Publish button was clicked");
		return new AddP2PPage_PublishedDeatailsTab();
	}
}
