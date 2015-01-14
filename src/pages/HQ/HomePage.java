package pages.HQ;


import objects.Browser;
import objects.Button;
import objects.DropDown;
import objects.Label;
import objects.Panel;
import objects.TextBox;
import pages.Donation.DonationsPage;
import pages.HQ.Activities.ActivitiesPage;
import pages.HQ.Manage.ManagePage;
import selenium.CommonUtils;
import selenium.Environment;
import selenium.SeleneseTestCase;

public class HomePage extends Browser{
	
	public Panel feedBackDialogPanel = new Panel("//feedback-dialog/div[contains(@class, 'feedback alert-box')]", "Feedback dialog");
	public Button closeFeedbackDialog = new Button(feedBackDialogPanel.path + "/descendant::a[@class='close']", "Close feedback dialog");
	Label userlabel = new Label("//div[@id='account-info-drop']/a[text()='" + 
			CommonUtils.getProperty("current.firstName") + " " + 
			CommonUtils.getProperty("current.lastName") +"']"
			, "Drop with user name");
	Label orglabel = new Label(userlabel.path + "/span[text()='" + CommonUtils.getProperty("Admin.orgName") + "']", "Drop with organization name");
	
	//left navigation bar
	Button audienceTab = new Button("//a[@href='/#/audience']", "Audience tab");
	Button activitiesTab = new Button("//a[@href='/#/activities']", "Activities tab");
	Button donationTab = new Button("//a[@href='/#/insight/donations']", "Donations tab");
	Button dashboardTab = new Button("//a[@href='/#/dashboard']", "Dashboard tab");
	
	//Top navigation bar
	Button settingsTab = new Button("//div[contains(@class, 'hide-for-small')]/descendant::a[@title='Manage']", "Manage page");
	Button alertsTab = new Button("//div[contains(@class, 'hide-for-small')]/descendant::a[@title='Alerts']", "Alerts popup");
	Button newsTab = new Button("//div[contains(@class, 'hide-for-small')]/descendant::a[@title='News']", "News popup");
	
	//Configure new org
	Button nextButtonConfigNewOrgPage = new Button("//div[@class='row' and @ng-show='isNewOrg']/descendant::*[contains(text(), 'Save')]/ancestor-or-self::button", "Save & Keep Going!");
	Button saveButtonConfigNewOrgPage = new Button("//div[@class='row' and @ng-show='isNewOrg']/descendant::*[contains(text(), \"Let's go!\")]/ancestor-or-self::button", "Let's go!");
	TextBox emailConfigNewOrgPage = new TextBox("//input[@id='email']", "New org email");
	TextBox HQEmailNewOrgPage = new TextBox("//input[@id='line1']", "HQ Mailing Address", true);
	TextBox zipConfigNewOrgPage = new TextBox("//input[@id='zip']", "Zip");
	TextBox cityConfigNewOrgPage = new TextBox("//input[@id='city']", "City");
	DropDown states = new DropDown("//custom-select2[@data='states']/div/ul/li", "//custom-select2[@data='states']/div/a", "States");
	//supporterStatesField.selectByID(CommonUtils.getRandomValueFromTo(0, 71, 0));
	TextBox fromNameConfigNewOrgPage = new TextBox("//input[@name='fromName']", "From Name", true); 
	TextBox fromAddressConfigNewOrgPage = new TextBox("//input[@name='fromAddress']", "From Address", true);
	TextBox replyAddressConfigNewOrgPage = new TextBox("//input[@name='replyAddress']", "Reply Address", true);
	Button buyButton = new Button("//a[@href='/#/purchase']", "Buy");
	
	public HomePage verifyUserNameDisplayed() {
		verify(userlabel.isDisplayed(), true, "Drop with user name is not visible");
		return this;
	}
	
	public HomePage verifyOrgNameDisplayed() {
		verify(orglabel.isDisplayed(), true, "Drop with organization name is not visible");
		return this;
	}
	
	public HomePage verifyHomePageIsOpened() {
		sleep(10000);
		if (SeleneseTestCase.USED_ENVIRONMENT.server.equals(Environment.LocationOfServer.REMOTE)) {
			sleep(20000);
		}
		verify(getLocation().contains("dashboard") , true, "Wrong url " + getLocation());
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
		sleep(5000);
		newsTab.click();
		return new NewsPop();
		
	}

	public PurchasePage clickBuyButton() {
		sleep(5000);
		buyButton.click();
		sleep(5000);
		return new PurchasePage();
		
	}
	public HomePage configureNewOrg(String email, String zip, String city) {
		sleep(10000);
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
}
