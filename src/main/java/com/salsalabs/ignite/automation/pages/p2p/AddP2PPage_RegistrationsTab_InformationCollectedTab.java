package com.salsalabs.ignite.automation.pages.p2p;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;

public class AddP2PPage_RegistrationsTab_InformationCollectedTab extends AddP2PPage_RegistrationsTab {
	
	Button breadCrumbsLink = new ButtonImpl("//li[contains(@class, 'active')]/a[contains(.,'Information Collected')]", "Information Collected tab");
	Button continueButton = new ButtonImpl("//a[contains(.,'Continue')]", "Continue button");
	
	public AddP2PPage_RegistrationsTab_OptionalPurchasesTab clickContinueButton() {		
		gzFrame.waitElement();
		switchToFrame(gzFrame.getPath());
		breadCrumbsLink.waitElement();
		continueButton.click();
		switchDefaultContent();
		return new AddP2PPage_RegistrationsTab_OptionalPurchasesTab();
	}
	
}
