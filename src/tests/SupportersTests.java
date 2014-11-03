package tests;


import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.HQ.LoginPage;
import pages.HQ.Supporters.SupportersPage;
import selenium.CommonUtils;
import selenium.SeleneseTestCase;

public class SupportersTests extends SeleneseTestCase{
	
	@Parameters({ "createSupporter.amount"})
	@Test(priority=40, groups = {"acceptanceTests.user", "createSupporter.manually"}, description = "494:53:Supporter was NOT created")
	public void createSupporerManually(Integer amount){
		LoginPage loginPage = new LoginPage();
		SupportersPage supportersPage = loginPage.
		doSuccessLogin(CommonUtils.getProperty("Admin.email"), CommonUtils.getProperty("Admin.Password")).
		openAudiencePage().
		openSupportersPage();
		
		for (int i = 0; i < amount; i++) {
			supportersPage.
			openAddSupporterPage().
			createNewSupporter().
			checkSupporterExists(CommonUtils.getParam("supporterEmail"));
			logger.info("Supporter #" + i + " was added");
		}
		amount = amount + Integer.parseInt(CommonUtils.getProperty("amountOfSupporters")); 
		CommonUtils.setProperty("amountOfSupporters", amount.toString());
		
	}
	
}
		
