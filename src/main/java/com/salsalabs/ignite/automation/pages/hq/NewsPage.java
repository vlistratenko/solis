package com.salsalabs.ignite.automation.pages.hq;

import com.salsalabs.ignite.automation.elements.Table;
import com.salsalabs.ignite.automation.elements.impl.TableImpl;

public class NewsPage extends HomePage{

	Table tableWithMessages = new TableImpl("//span[.=' News ']/ancestor::div[@class='mainContent']/descendant::table", "Table with alerts");
	
	public NewsPage verifyTableWithMessagesDisplayed() {
		tableWithMessages.isDisplayed();
		tableWithMessages.highlight();
		return this;
	}
}
