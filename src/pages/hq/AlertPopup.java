package pages.hq;

import core.util.CommonUtils;
import core.util.PropertyName;
import elements.Button;
import elements.Panel;
import elements.impl.ButtonImpl;
import elements.impl.PanelImpl;

public class AlertPopup extends HomePage{

	Button messageLink = new ButtonImpl("//a[.=\"Your segment '" + CommonUtils.getProperty(PropertyName.ASSETS_USERNAME) + "' has been updated\"]", "Last added segment");
	Button showAllButton = new ButtonImpl("//a[contains(text(), 'See all') and contains(text(), ' alerts...')]", "Show all");
	Panel alertPanel = new PanelImpl("//div[@id='topNav_alerts']/ul", "Alerts panel");
	
	public AlertPopup checkAlertIsPresentInTheList() {
		verifier.verifyElementIsDisplayed(messageLink);
		return this;
	}
	
	public AlertPopup checkAlertPanelIsPresent() {
		verifier.verifyElementIsDisplayed(alertPanel);
		return this;
	}
	
	public AlertsPage openAlertsPage() {
		showAllButton.click();
		return new AlertsPage(); 
		
	}
}
