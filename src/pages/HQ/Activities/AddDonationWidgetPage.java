package pages.HQ.Activities;

import objects.Button;
import objects.CheckBox;
import objects.TextBox;
import selenium.CommonUtils;

public class AddDonationWidgetPage extends ActivitiesPage {

	String currentWindowHandle;
	String widgetName;
	TextBox widgetNameField = new TextBox("//input[@name='name']", "Widget Name", true);
	TextBox widgetDescriptionField = new TextBox("//textarea[@name='description']", "Widget description", false);
	Button nextButton = new Button("//button[@id='btnCompose2']", "Design My Widget button", true);
	Button publishButton = new Button("//button[@id='btnPublish']", "Publish button", true);
	CheckBox iNeedHostedPageCheckBox = new CheckBox("//span[contains(@ng-class, 'useHostedPage==true')]", " I need a hosted page");
	CheckBox iNeedWidgetCodeCheckBox = new CheckBox("//span[contains(@ng-class, 'useHostedPage==false')]", " I need a hosted page");
	
	TextBox titleField = new TextBox("//input[@ng-model='widget.page.title']", "Title");
	Button saveAndPublish = new Button("//button[contains(@ng-click,'publishHostedPage')]", "Save and Publish");
	Button layoutButton = new Button("//*[.='layoutName']", "Layout label");
	
	Button widgetLink;
	
	public AddDonationWidgetPage createDonationWidgetSetupStep(String widgetName, String widgetDescription) {
		this.widgetName = widgetName;
		widgetNameField.type(widgetName);
		widgetDescriptionField.type(widgetDescription);
		nextButton.click();
		sleep(10000);
		return this;
	}
	
	public AddDonationWidgetPage selectLayoutForDanationWidgetStep(String layoutName) {
		sleep(10000);
		layoutButton.changePath("layoutName", layoutName);
		layoutButton.click();
		sleep(10000);
		return this;
	}
	
	public AddDonationWidgetPage createDonationWidgetDesignWidgetStep() {
		publishButton.click();
		sleep(10000);
		return this;
	}
	
	public AddDonationWidgetPage hosteWidgetOnLocalPage(String widgetTitle, Boolean isHostedOnLocalPage) {
		if (isHostedOnLocalPage) {
			iNeedHostedPageCheckBox.check();
		}else{
			iNeedWidgetCodeCheckBox.check();
		}		
		titleField.type(widgetTitle);
		saveAndPublish.scrollIntoView();
		saveAndPublish.click();
		return this;
	}
	
	public DonationWidget openDonationWidget() {
		sleep(5000);
		widgetLink = new Button("//a[contains(text(), '" + widgetName.toLowerCase() + "')]", "Widget link");
		currentWindowHandle = getWindowHandle();
		widgetLink.click();			
		switchToPopupWindow(currentWindowHandle);
		CommonUtils.setProperty("currentWindowHandle", currentWindowHandle);
		return new DonationWidget();
	}
	
	public AddDonationWidgetPage saveDonationWidgetLink() {
		sleep(5000);
		widgetLink = new Button("//a[contains(text(), '" + widgetName.toLowerCase() + "')]", "Widget link");
		currentWindowHandle = getWindowHandle();
		CommonUtils.setProperty("donationWidgetLink", widgetLink.getAttribute("href"));
		return this;
	}

}
