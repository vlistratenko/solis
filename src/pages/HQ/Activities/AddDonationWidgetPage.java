package pages.HQ.Activities;

import objects.Button;
import objects.CheckBox;
import objects.TextBox;
import selenium.CommonUtils;

public class AddDonationWidgetPage extends ActivitiesPage {

	String currentWindowHandle;
	
	TextBox widgetNameField = new TextBox("//input[@name='name']", "Widget Name", true);
	TextBox widgetDescriptionField = new TextBox("//textarea[@name='description']", "Widget description", false);
	Button nextButton = new Button("//a[@id='btnCompose2']", "Design My Widget button", true);
	Button publishButton = new Button("//a[@id='btnPublish']", "Publish button", true);
	CheckBox iNeedHostedPageCheckBox = new CheckBox("//span[contains(@ng-class, 'useHostedPage==true')]", " I need a hosted page");
	CheckBox iNeedWidgetCodeCheckBox = new CheckBox("//span[contains(@ng-class, 'useHostedPage==false')]", " I need a hosted page");
	
	TextBox titleField = new TextBox("//input[@ng-model='widget.page.title']", "Title");
	Button saveAndPublish = new Button("//button[contains(@ng-click,'publishHostedPage')]", "Save and Publish");
	
	Button widgetLink = new Button("//a[contains(text(), '" + CommonUtils.getProperty("Admin.orgName").toLowerCase().replaceAll(" ", "") + "')]", "Widget link");
	
	public AddDonationWidgetPage createDonationWidgetSetupStep(String widgetName, String widgetDescription) {
		widgetNameField.type(widgetName);
		widgetDescriptionField.type(widgetDescription);
		nextButton.click();
		sleep(10000);
		return this;
	}
	
	
	public AddDonationWidgetPage createDonationWidgetDesignWidgetStep() {
		publishButton.click();
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
		currentWindowHandle = getWindowHandle();
		widgetLink.click();			
		switchToPopupWindow(currentWindowHandle);
		CommonUtils.setProperty("currentWindowHandle", currentWindowHandle);
		return new DonationWidget();
	}

}
