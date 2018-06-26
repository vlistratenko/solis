package com.salsalabs.ignite.automation.pages.p2p;

import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.PropertyName;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;

public class AddP2PPage_AutorespondersTab extends AddP2PPage {

	Button publishButton = new ButtonImpl("//button[@id='btnGo-autoresponders-publish']", "Publish button");
	
	public AddP2PPage_PublishedDeatailsTab clickPublishButton() {
		CommonUtils.setProperty(PropertyName.P2P_FORM_HQ_LINK, getLocation());
		publishButton.waitElement();
		publishButton.click();
		return new AddP2PPage_PublishedDeatailsTab();
	}
}
