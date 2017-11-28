package com.salsalabs.ignite.automation.pages.p2p;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;

public class AddP2PPage_TeamPageTab<T> extends AddP2PPage_FundraiserPageTab<T>  {
	Button teamFundraisingPageSubTab = new ButtonImpl("//div[contains(@class, 'inactive')]/a[.='Team Fundraising Page']", "Team Fundraising Page");
	Button clickNextToAutorespondersPage = new ButtonImpl("//button[@id='btnGo-composeTeam-autoresponders']", "Next: Team Page Template");
	
	public AddP2PPage_TeamPageTab_TeamFundraisingSubTab openTeamFundraisingPageSubTab() {
		teamFundraisingPageSubTab.waitElement(5);
		if (!teamFundraisingPageSubTab.isNotExists()) {
			teamFundraisingPageSubTab.click();
		}
		eventVEPanel.waitElement(15);
		return new AddP2PPage_TeamPageTab_TeamFundraisingSubTab();
	}
	
	public AddP2PPage_AutorespondersTab clickNextToAutorespondersTabButton() {
		clickNextToAutorespondersPage.click();
		return new AddP2PPage_AutorespondersTab();
	}
}
