package JustForTestingOfCode;

import org.testng.annotations.Test;

import pages.HQ.LoginPage;
import selenium.SeleneseTestCase;

public class Demo extends SeleneseTestCase{

	@Test
	public void Test(){
		new LoginPage().
		openPage().
		doSuccessLogin("vavramchuk+adminv1@salsalabs.com", "Avitalik3124").
		openSettingsPage().
		openAccountsPage().
		openInviteNewUserPage().
		inviteNewUser("vavramchuk+cm123@salsalabs.com",
				"TestV", 
				"Testerov",
				"Create & Edit",
				"Create & Edit",
				"Create & Edit",
				"Create & Edit",
				"Create & Edit",
				"Create & Edit",
				"Create & Edit");
	}
}
