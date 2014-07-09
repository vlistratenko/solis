package selenium;

import pages.HQ.LoginPage;

public class Demo2 extends SeleneseTestCase
{

	@org.testng.annotations.Test
	public void Test() {
		new LoginPage().openPage().doSuccessLogin("vavav", "dskfdsk").openActivitiesPage().openEmailBlastsPage().openAddEmailPage();
	}
}
