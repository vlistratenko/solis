package JustForTestingOfCode;

import pages.HQ.LoginPage;
import selenium.SeleneseTestCase;

public class Demo2 extends SeleneseTestCase
{

	@org.testng.annotations.Test
	public void Test() {
		new LoginPage().openPage().doSuccessLogin("vavav", "dskfdsk").openActivitiesPage().openEmailBlastsPage().openAddEmailPage();
	}
}
