package pages.HQ.Manage;

import objects.Button;
import objects.DropDown;
import pages.HQ.HomePage;

public class ManagePage  extends HomePage{
	
	Button manageImportsButton = new Button("//a[@href='/#/settings/imports']", "Settings of imports");
	Button manageAccountsButton = new Button("//a[@href='/#/settings/accounts']", "Settings of  accounts");
	Button manageConfigurationButton = new Button("//a[@href='/#/settings']", "Settings of configuration");
	DropDown switchToDropDown = new DropDown("//ul[@id='settingsDropdown']/li", "//button[contains(@ng-click, 'changeSettingDropdown')]", "Switch To");
	
	public AccountsPage openAccountsPage() {
		sleep(5000);
		manageAccountsButton.click();
		return new AccountsPage();
	}
	
	public ImportPage openImportPage() {
		sleep(5000);
		manageImportsButton.click();
		return new ImportPage();
	}
	
	public ManagePage verifyURL() {
		
		verify(getLocation().contains("settings"), true, "Current URL does not contains settings");
		return this;
	}
	
	public PaymentGatewaysPage switchToPaymentGatewaysPage() {
		switchToDropDown.selectByLabelJS("Payment Gateways");
		return new PaymentGatewaysPage();
	}
	
	public UnsubscribeSettingsPage switchToUnsubscribeSettingsPage() {
		switchToDropDown.selectByLabelJS("Standard Unsub Message");
		return new UnsubscribeSettingsPage();
	}

}
