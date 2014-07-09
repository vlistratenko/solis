package pages.HQ;

import objects.Table;


public class AlertsPage extends HomePage{
	
	Table tableWithAlerts = new Table("//span[.=' Alerts |']/ancestor::div[@class='mainContent']/descendant::table", "Table with alerts");
	
	public AlertsPage verifyTableWithAlertsDisplayed() {
		tableWithAlerts.isDisplayed();
		return this;
	}
}
