package com.salsalabs.ignite.automation.pages.p2p;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.pages.hq.HomePage;
import com.salsalabs.ignite.automation.pages.hq.emails.AddEmailsPage_ComposeTab;

public class AddP2PPage extends HomePage{

	/**
	 * use label under img as layout value. For now
	 * layout can be Basic or Sidebar
	 * @param layout
	 * @return
	 */
	public AddP2PPage selectLayout(String layout) {
		Button lay = new ButtonImpl("//strong[.='" + layout + "']/ancestor::div[contains(@class,'layout_item')]/descendant::button[contains(@ng-click,'selectItem')]", layout + " layout");
		lay.click();
		return this;
	}
}
