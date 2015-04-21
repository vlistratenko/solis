package com.salsalabs.ignite.automation.suites;

import org.testng.annotations.Test;

import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.common.Supporter;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;
import com.salsalabs.ignite.automation.pages.hq.supporters.SupportersAddPage;
import com.salsalabs.ignite.automation.pages.hq.supporters.SupportersPage;

/**
 * <b>This test contains scenarios related to manual supporter creation (TestLink: TC6)</b>
 * @author a.hubachov
 *
 */
public class CreateSupporterManuallyTest extends SeleneseTestCase {
	private SupportersAddPage supporterAddPage;
	private SupportersPage supportersPage;
	private Supporter supporter;
	
	/**
	 * <b>Manually create single supporter.</b>
	 * <p>
	 * Steps:
	 * <ul>
	 * <li> Login into existing organization
	 * <li> Open Audience page
	 * <li> Open Supporters tab
	 * <li> Click on "Add Supporters" dropdown and choose "Add a Single Supporter"
	 * <li> Type email, first name, last name, city, state, zip code
	 * <li> Click "Save this supporter!" button
	 * <li> <font color="green"><b>Verify that new supporter is added and present on the top of the supporters table</b></font>
	 * </ul>
	 *  
	 */
	@Test(groups = {"createSupporterManually"}, retryAnalyzer = RetryAnalyzer.class)
	public void testCreateSupporterManually() {
		doLoginAndOpenAddSupporterPage();
		supporter = Supporter.generateSupporter();
		supportersPage = supporterAddPage.createNewSupporter(supporter);
		supportersPage.verifySupporterOnTopOfTableFull(supporter);
	}
	
	private void doLoginAndOpenAddSupporterPage() {
		supporterAddPage = new LoginPage().doSuccessLogin().
				openAudiencePage().openSupportersPage().openAddSupporterPage();
	}
}

