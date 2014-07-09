package pages.HQ.Manage;

import objects.Button;
import pages.HQ.HomePage;

public class ManagePage  extends HomePage{
	
	Button manageImportsButton = new Button("//a[@href='/#/manage/imports']", "Manage imports");
	Button manageAccountsButton = new Button("//a[@href='/#/manage/accounts']", "Manage accounts");
	Button manageConfigurationButton = new Button("//a[@href='/#/manage/configure']", "Manage configuration");

	public AccountsPage openAccountsPage() {
		manageAccountsButton.click();
		return new AccountsPage();
	}
	
	public ManagePage verifyURL() {
		
		verify(getLocation().contains("manage"), true, "Current URL does not contains manage");
		return this;
	}

}
