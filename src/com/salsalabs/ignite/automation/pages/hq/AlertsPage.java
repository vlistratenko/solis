package com.salsalabs.ignite.automation.pages.hq;

import com.salsalabs.ignite.automation.elements.Table;
import com.salsalabs.ignite.automation.elements.impl.TableImpl;


public class AlertsPage extends HomePage{
	
	Table tableWithAlerts = new TableImpl("//span[.=' Alerts |']/ancestor::div[@class='mainContent']/descendant::table", "Table with alerts");
	
	public AlertsPage verifyTableWithAlertsDisplayed() {
		tableWithAlerts.isDisplayed();
		return this;
	}
}
