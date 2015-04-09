package com.salsalabs.ignite.automation.pages.hq;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.pages.hq.manage.ImportPage;
import com.salsalabs.ignite.automation.pages.hq.segments.SegmentsPage;
import com.salsalabs.ignite.automation.pages.hq.supporters.SupportersPage;

public class AudiencePage extends HomePage{

	Button supportersButton = new ButtonImpl("//a[text()='Supporters']", "Supporters");
	Button segmentsButton = new ButtonImpl("//a[text()='Segments']", "Segments");
	Button manageImportsButton = new ButtonImpl("//a[text()='Manage Imports']", "Manage Imports");

	public SupportersPage openSupportersPage() {
		supportersButton.click();
		return new SupportersPage();
	}
	
	public SegmentsPage openSegmentsPage() {
		segmentsButton.click();
		return new SegmentsPage();
	}
	
	public AudiencePage verifyURL() {
		verifier.verifyTrue(getLocation().contains("audience"), "Current URL is not contains Audience");
		return this;
	}
	
	public ImportPage openImportPage() {
		sleep(2);
		manageImportsButton.click();
		return new ImportPage();
	}
	
}
