package com.salsalabs.ignite.automation.pages.hq;

import com.salsalabs.ignite.automation.elements.Element;
import com.salsalabs.ignite.automation.elements.Table;
import com.salsalabs.ignite.automation.elements.impl.LabelImpl;
import com.salsalabs.ignite.automation.elements.impl.TableImpl;
import com.salsalabs.ignite.automation.pages.hq.manage.ImportPage;


public class AlertsPage extends HomePage{
	
	Table tableWithAlerts = new TableImpl("//span[.=' Alerts |']/ancestor::div[@class='mainContent']/descendant::table", "Table with alerts");

	public AlertsPage verifyTableWithAlertsDisplayed() {
		tableWithAlerts.isDisplayed();
		return this;
	}
	
	public AlertsPage checkImport(String importName) {
		String importStarted = String.format(ImportPage.IMPORT_STARTED, importName);
		String importFinished = String.format(ImportPage.IMPORT_FINISHED, importName);
		sleep(2);
		Element started = new LabelImpl("//a[text()='" + importStarted + "']", "");
		Element finished = new LabelImpl("//a[text()='" + importFinished + "']", "");
		verifier.verifyTrue(started.isDisplayed(), "Import started alert not appeared");
		verifier.verifyTrue(finished.isDisplayed(), "Import finished alert not appeared");
		return this;
	}
}
