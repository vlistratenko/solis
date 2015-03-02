package tests;


import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import core.util.CommonUtils;
import core.util.PropertyName;
import core.util.SeleneseTestCase;
import pages.hq.LoginPage;
import pages.hq.supporters.SupportersPage;

public class SupportersTests extends SeleneseTestCase{
	
	@Parameters({ "createSupporter.amount"})
	@Test(priority=40, groups = {"acceptanceTests.user", "createSupporter.manually"}, description = "494:53:Supporter was NOT created")
	public void createSupporerManually(Integer amount){
		LoginPage loginPage = new LoginPage();
		SupportersPage supportersPage = loginPage.
		doSuccessLogin(CommonUtils.getProperty(PropertyName.ADMIN_EMAIL), CommonUtils.getProperty(PropertyName.ADMIN_PASSWORD)).
		openAudiencePage().
		openSupportersPage();
		
		for (int i = 0; i < amount; i++) {
			supportersPage.
			openAddSupporterPage().
			createNewSupporter().
			checkSupporterExists(CommonUtils.getParam("supporterEmail"));
			logger.info("Supporter #" + i + " was added");
		}
		amount = amount + Integer.parseInt(CommonUtils.getProperty(PropertyName.AMOUNT_OF_SUPPORTERS)); 
		CommonUtils.setProperty(PropertyName.AMOUNT_OF_SUPPORTERS, amount.toString());
		
	}
	
}
		
