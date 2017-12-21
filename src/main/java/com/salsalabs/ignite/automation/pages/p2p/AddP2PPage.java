package com.salsalabs.ignite.automation.pages.p2p;

import com.salsalabs.ignite.automation.elements.Panel;
import com.salsalabs.ignite.automation.elements.VE2Elements.TextVEElement;
import com.salsalabs.ignite.automation.elements.impl.PanelImpl;
import com.salsalabs.ignite.automation.pages.hq.HomePage;
import com.salsalabs.ignite.automation.pages.hq.basic.basicLayoutClass;


public class AddP2PPage extends HomePage{

	Panel registrationVEPanel = new PanelImpl("//div[@autotest-id='compose-tab-tickets']", "Event tab, Registration sub tab");
	Panel eventVEPanel = new PanelImpl("//div[@autotest-id='compose-tab-content']", "Event tab, Event page sub tab");
	Panel confirmationVEPanel = new PanelImpl("//div[@autotest-id='compose-tab-confirmation']", "Event tab, confirmation sub tab");
	Panel checkoutVEPanel = new PanelImpl("//div[@autotest-id='compose-tab-checkout']", "Event tab, Checkout sub tab");
	TextVEElement textElement = new TextVEElement(TextVEElement.elPath, "Text element");
	
	/**
	 * use label under img as layout value. For now
	 * layout can be Basic or Sidebar
	 * @param layout
	 * @return
	 */
	public AddP2PPage selectLayout(String layout) {
		basicLayoutClass.selectLayout(layout);
		return this;
	}
}
