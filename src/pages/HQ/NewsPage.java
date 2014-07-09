package pages.HQ;

import objects.Table;

public class NewsPage extends HomePage{

	Table tableWithMessages = new Table("//span[.=' News ']/ancestor::div[@class='mainContent']/descendant::table", "Table with alerts");
	
	public NewsPage verifyTableWithMessagesDisplayed() {
		tableWithMessages.isDisplayed();
		tableWithMessages.highlight();
		return this;
	}
}
