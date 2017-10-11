package com.salsalabs.ignite.automation.pages.hq.manage;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.DropDown;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.DropDownImpl;
import com.salsalabs.ignite.automation.pages.hq.HomePage;

public class ManagePage  extends HomePage{

	Button manageImportsButton = new ButtonImpl("//a[@href='/#/settings/imports']", "Settings of imports");
	Button manageAccountsButton = new ButtonImpl("//a[@href='/#/settings/accounts']", "Settings of  accounts");
	Button manageConfigurationButton = new ButtonImpl("//a[@href='/#/settings']", "Settings of configuration");
	Button manageCustomTargetsButton = new ButtonImpl("//*[@id='settingsDropdown']//a[contains(text(), 'Custom Targets')]", "Custom Targets");
	DropDown switchToDropDown = new DropDownImpl("//ul[@id='settingsDropdown']/li", "//button[contains(@ng-click, 'changeSettingDropdown')]", "Switch To");

	public AccountsPage openAccountsPage() {
		sleep(5);
		manageAccountsButton.click();
		return new AccountsPage();
	}

	/**
	 * Use the  {@link com.salsalabs.ignite.automation.pages.hq.AudiencePage#openImportPage() openImportPage()} method
	 * @return
	 */
	@Deprecated
	public ImportPage openImportPage() {
		sleep(5);
		manageImportsButton.click();
		return new ImportPage();
	}

	public ManagePage verifyURL() {

		verifier.verifyEquals(getLocation().contains("settings"), true, "Current URL does not contains settings");
		return this;
	}

	public CustomTargetsPage switchToCustomTargetsPage() {
		switchToDropDown.selectByLabelJS("Custom Targets");
		return new CustomTargetsPage();
	}

	public PaymentGatewaysPage switchToPaymentGatewaysPage() {
		switchToDropDown.selectByLabelJS("Payment Gateways");
		return new PaymentGatewaysPage();
	}

	public UnsubscribeSettingsPage switchToUnsubscribeSettingsPage() {
		switchToDropDown.selectByLabelJS("Subscription Management");
		return new UnsubscribeSettingsPage();
	}

	public CustomFieldsPage switchToCustomFieldsPage() {
		switchToDropDown.selectByLabelJS("Custom Fields");
		return new CustomFieldsPage();
	}

	public MessageTopicsPage switchToMessageTopicsPage() {
		switchToDropDown.selectByLabelJS("Message Topics");
		return new MessageTopicsPage();
	}

	public SocialMediaPages switchToAddSocialMediaPage() {
		switchToDropDown.selectByLabelJS("Social Media Pages");
		return new SocialMediaPages();
	}


	public SocialMediaAccountsPage switchToAddSocialPostsPage() {
		switchToDropDown.selectByLabelJS("Social Media Accounts");
		return new SocialMediaAccountsPage();
	}



}