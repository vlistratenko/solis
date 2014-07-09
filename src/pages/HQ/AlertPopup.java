package pages.HQ;

import objects.Button;
import objects.Panel;
import selenium.CommonUtils;

public class AlertPopup extends HomePage{

	Button messageLink = new Button("//a[.=\"Your segment '" + CommonUtils.getProperty("segmentName") + "' has been updated\"]", "Last added segment");
	Button showAllButton = new Button("//a[contains(text(), 'See all') and contains(text(), ' alerts...')]", "Show all");
	Panel alertPanel = new Panel("//div[@id='topNav_alerts']/ul", "Alerts panel");
	
	public AlertPopup checkAlertIsPresentInTheList() {
		verify(messageLink.isDisplayed(), true, "Link is not displayed");
		return this;
	}
	
	public AlertPopup checkAlertPanelIsPresent() {
		verify(alertPanel.isDisplayed(), true, "Panel is not displayed");
		return this;
	}
	
	public AlertsPage openAlertsPage() {
		showAllButton.click();
		return new AlertsPage(); 
		
	}
}
