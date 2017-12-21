package com.salsalabs.ignite.automation.pages.p2p;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.pages.hq.activities.AddDonationWidgetPage;


public class AddP2PPage_FundraiserPageTab<T> extends AddP2PPage_EventPageTab<T> {

	Button donateSubTab = new ButtonImpl("//a[.='Donate']", "Donate Page subtab ");
	Button personalFundraisingPageSubTab = new ButtonImpl("//div[contains(@class, 'inactive')]/a[.='Personal Fundraising Page']", "Personal Fundraising Page");
	Button clickNextToTeamPage = new ButtonImpl("//button[@id='btnGo-composePersonal-composeTeam']", "Next: Team Page Template");
	
	@SuppressWarnings("unchecked")
	public T openDonateSubTab() {
		donateSubTab.waitElement();
		donateSubTab.scrollIntoView();
		donateSubTab.click();
		checkoutVEPanel.waitElement();
		return (T)this;
	}
	
	public AddP2PPage_FundraiserPageTab_PersonalFundraisingSubTab openPersonalFundraisingPageSubTab() {
		personalFundraisingPageSubTab.waitElement();
		if (!personalFundraisingPageSubTab.isNotExists()) {
			personalFundraisingPageSubTab.click();
		}
		eventVEPanel.waitElement();
		return new AddP2PPage_FundraiserPageTab_PersonalFundraisingSubTab();
	}
	
	public AddP2PPage_TeamPageTab_TeamFundraisingSubTab clickNextToTeamTabButton() {
		clickNextToTeamPage.waitElement();
		clickNextToTeamPage.click();
		return new AddP2PPage_TeamPageTab_TeamFundraisingSubTab();
	}
	
	
	/*public AddP2PPage_FundraiserPageTab_ConfirmationViewSubTab openConfirmationViewSubTab() {
		confirmationViewSubTab.click();
		return new AddP2PPage_FundraiserPageTab_ConfirmationViewSubTab();
	}*/
	
}
