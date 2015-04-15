package com.salsalabs.ignite.automation.suites;

import org.testng.annotations.Test;

import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.pages.donation.DonationsAddPage;
import com.salsalabs.ignite.automation.pages.donation.DonationsPage;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;

public class CreateDonationFormHostedPageTest extends SeleneseTestCase {
	private DonationsPage donationsPage;
	private DonationsAddPage donationsAddPage;
	
	@Test
	public void createAndPublishDonationForm() {
		String formName = "";
		String formDescription = "";
		doLoginAndOpenDonationForms();
		donationsPage.openCreateDonationPage();
		donationsAddPage.fillFirstStep(formName, formDescription);
	}
	
	private void doLoginAndOpenDonationForms() {
		donationsPage = new LoginPage().doSuccessLogin().openActivitiesPage().openDonationsPage();
	}
}
