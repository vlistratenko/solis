package pages.hq;

import elements.Button;
import elements.impl.ButtonImpl;

public class NewsPop extends HomePage{

	Button messageLink = new ButtonImpl("is not implemented", "Last added segment");
	Button headerMessage = new ButtonImpl("//nav[@id='topNav']/descendant::li[@class='notice']/p", "News popup header");
	Button showAllButton = new ButtonImpl("//a[contains(text(), 'See all') and contains(text(), 'messages ...')]", "Show all");
	
	public NewsPop verifyNewsPopUpIsExists() {
		verifier.verifyElementIsDisplayed(headerMessage);
		headerMessage.highlight();
		return this;
	}
	
	public NewsPage openNewsPage() {
		showAllButton.click();
		return new NewsPage();
	}

}
