package com.salsalabs.ignite.automation.pages.hq;


import java.util.Set;

import com.salsalabs.ignite.automation.common.Browser;
import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.Environment;
import com.salsalabs.ignite.automation.common.PropertyName;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.DropDown;
import com.salsalabs.ignite.automation.elements.Label;
import com.salsalabs.ignite.automation.elements.Panel;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.DropDownImpl;
import com.salsalabs.ignite.automation.elements.impl.LabelImpl;
import com.salsalabs.ignite.automation.elements.impl.PanelImpl;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;
import com.salsalabs.ignite.automation.pages.donation.DonationsPage;
import com.salsalabs.ignite.automation.pages.hq.activities.ActivitiesPage;
import com.salsalabs.ignite.automation.pages.hq.assets.AssetsPage;
import com.salsalabs.ignite.automation.pages.hq.manage.ManagePage;
import com.salsalabs.ignite.automation.pages.zendesk.ZendeskPage;
import com.salsalabs.ignite.automation.pages.zendesk.ZendeskSubmitRequestPage;

public class HomePage extends Browser{
	
	public Panel feedBackDialogPanel = new PanelImpl("//feedback-dialog/div[contains(@class, 'feedback alert-box')]", "Feedback dialog");
	public Button closeFeedbackDialog = new ButtonImpl(feedBackDialogPanel.getPath() + "/descendant::a[@class='close']", "Close feedback dialog");
	Label userlabel = new LabelImpl("//div[@id='account-info-drop']/a[text()='" + 
			CommonUtils.getProperty(PropertyName.CURRENT_FIRST_NAME) + " " + 
			CommonUtils.getProperty(PropertyName.CURRENT_LAST_NAME) +"']"
			, "Drop with user name");
	Label orglabel = new LabelImpl(userlabel.getPath() + "/span[text()='" + CommonUtils.getProperty(PropertyName.ADMIN_ORG_NAME) + "']", "Drop with organization name");
	
	//left navigation bar
	Button audienceTab = new ButtonImpl("//a[@href='/#/audience']", "Audience tab");
	Button activitiesTab = new ButtonImpl("//a[@href='/#/activities']", "Activities tab");
	Button donationTab = new ButtonImpl("//a[@href='/#/insight/donations']", "Donations tab");
	Button dashboardTab = new ButtonImpl("//a[@href='/#/dashboard']", "Dashboard tab");
	Button assetsTab = new ButtonImpl ("//a[@href='/#/assets']", "Assets tab");
	
	//Top navigation bar
	Button settingsTab = new ButtonImpl("//div[contains(@class, 'hide-for-small')]/descendant::a[@title='Manage']", "Manage page");
	Button alertsTab = new ButtonImpl("//div[contains(@class, 'hide-for-small')]/descendant::a[@title='Alerts']", "Alerts popup");
	Button newsTab = new ButtonImpl("//div[contains(@class, 'hide-for-small')]/descendant::a[@title='News']", "News popup");
	Button helpTab = new ButtonImpl("//div[@id='topNav_help']/a", "Help");
	
	DropDown helpItems = new DropDownImpl("//div[@id='topNav_help']/ul", "Help Items");
	
	//Configure new org
	Button nextButtonConfigNewOrgPage = new ButtonImpl("//div[@class='row' and @ng-show='isNewOrg']/descendant::*[contains(text(), 'Save')]/ancestor-or-self::button", "Save & Keep Going!");
	Button saveButtonConfigNewOrgPage = new ButtonImpl("//div[@class='row' and @ng-show='isNewOrg']/descendant::*[contains(text(), \"Let's go!\")]/ancestor-or-self::button", "Let's go!");
	TextBox emailConfigNewOrgPage = new TextBoxImpl("//input[@id='email']", "New org email");
	TextBox HQEmailNewOrgPage = new TextBoxImpl("//input[@id='line1']", "HQ Mailing Address", true);
	TextBox zipConfigNewOrgPage = new TextBoxImpl("//input[@id='zip']", "Zip");
	TextBox cityConfigNewOrgPage = new TextBoxImpl("//input[@id='city']", "City");
	DropDown states = new DropDownImpl("//custom-select2[@data='states']/div/ul/li", "//custom-select2[@data='states']/div/a", "States");
	//supporterStatesField.selectByID(CommonUtils.getRandomValueFromTo(0, 71, 0));
	TextBox fromNameConfigNewOrgPage = new TextBoxImpl("//input[@name='fromName']", "From Name", true); 
	TextBox fromAddressConfigNewOrgPage = new TextBoxImpl("//input[@name='fromAddress']", "From Address", true);
	TextBox replyAddressConfigNewOrgPage = new TextBoxImpl("//input[@name='replyAddress']", "Reply Address", true);
	Button buyButton = new ButtonImpl("//a[@href='/#/purchase']", "Buy");
	
	public HomePage verifyUserNameDisplayed() {
		verifier.verifyElementIsDisplayed(userlabel);
		return this;
	}
	
	public HomePage verifyOrgNameDisplayed() {
		verifier.verifyElementIsDisplayed(orglabel);
		return this;
	}
	
	public HomePage verifyHomePageIsOpened() {
		sleep(10);
		if (SeleneseTestCase.USED_ENVIRONMENT.getServer().equals(Environment.LocationOfServer.REMOTE)) {
			sleep(20);
		}
		verifier.verifyTrue(getLocation().contains("dashboard"), "Wrong url " + getLocation());
		return this;
	}
	
	public AudiencePage openAudiencePage() {
		audienceTab.click();
		return new AudiencePage();
		
	}
	
	public ActivitiesPage openActivitiesPage() {
		activitiesTab.click();
		return new ActivitiesPage();
		
	}
	
	public DonationsPage openDonationsPage() {
		donationTab.click();
		return new DonationsPage();
		
	}
	
	public ManagePage openSettingsPage() {
		settingsTab.click();
		return new ManagePage();
		
	}
	
	public AlertPopup openAlertPopup() {
		alertsTab.click();
		return new AlertPopup();
		
	}
	
	public NewsPop openNewsPopup() {
		sleep(5);
		newsTab.click();
		return new NewsPop();
		
	}

	public PurchasePage clickBuyButton() {
		sleep(5);
		buyButton.click();
		sleep(5);
		return new PurchasePage();
		
	}
	public HomePage configureNewOrg(String email, String zip, String city) {
		sleep(10);
		if (getLocation().contains("/configure/new-organization")) {
			emailConfigNewOrgPage.type(email);
			zipConfigNewOrgPage.type(zip);
			cityConfigNewOrgPage.type(city);
			states.selectByID(CommonUtils.getRandomValueFromTo(2, 4, 0));
			HQEmailNewOrgPage.type(email);
			nextButtonConfigNewOrgPage.click();
			
			fromNameConfigNewOrgPage.type("Bot");
			fromAddressConfigNewOrgPage.type(email);
			replyAddressConfigNewOrgPage.type(email);
			saveButtonConfigNewOrgPage.click();
		}
		return this;
	}
	
	public AssetsPage openAssetsPage (){
		
		assetsTab.click();
		return new AssetsPage();
	}
	
	public void switchToNewWindow(){
		Set<String> windows = SeleneseTestCase.driver.getWindowHandles();
		verifier.verifyTrue(windows.size() > 0, "New window hasn't been opened");
		for (String w : windows) {
			if (!w.equals(SeleneseTestCase.driver.getWindowHandle())) {
				SeleneseTestCase.driver.switchTo().window(w);
			}
		}
	}
	
	public ZendeskPage openHelpPage() {
		helpTab.click();
		helpItems.selectByLabel("Help");
		sleep(5);
		switchToNewWindow();
		verifier.verifyTrue(getLocation().contains("zendesk"), "Wrong url " + getLocation());
		return new ZendeskPage();
	}
	
	public ZendeskSubmitRequestPage openSubmitRequestPage() {
		helpTab.click();
		helpItems.selectByLabel("Submit support request");
		sleep(5);
		switchToNewWindow();
		verifier.verifyTrue(getLocation().contains("zendesk"), "Wrong url " + getLocation());
		return new ZendeskSubmitRequestPage();
	}
	
}
