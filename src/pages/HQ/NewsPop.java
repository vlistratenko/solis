package pages.HQ;

import objects.Button;

public class NewsPop extends HomePage{

	Button messageLink = new Button("is not implemented", "Last added segment");
	Button headerMessage = new Button("//nav[@id='topNav']/descendant::li[@class='notice']/p", "News popup header");
	Button showAllButton = new Button("//a[contains(text(), 'See all') and contains(text(), 'messages ...')]", "Show all");
	
	public NewsPop verifyNewsPopUpIsExists() {
		verify(headerMessage.isDisplayed(), true, "Header is not displayed");
		headerMessage.highlight();
		return this;
	}
	
	public NewsPage openNewsPage() {
		showAllButton.click();
		return new NewsPage();
	}

}
