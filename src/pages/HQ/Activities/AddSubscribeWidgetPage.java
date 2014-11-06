package pages.HQ.Activities;

import objects.Button;
import objects.CheckBox;
import objects.TextBox;
import selenium.CommonUtils;

public class AddSubscribeWidgetPage extends ActivitiesPage{
	
	String currentWindowHandle;
	TextBox widgetNameField = new TextBox("//input[@name='name']", "Widget name");
	TextBox widgetDescriptionField = new TextBox("//textarea[@name='description']", "Widget Description");
	Button openComposeStepButton = new Button("//a[@id='btnCompose']", "Compose");
	Button openPublishStepButton = new Button("//a[@id='btnPublish']", "Publish");
	CheckBox iNeedHostedPageCheckBox = new CheckBox("//span[contains(@ng-class, 'useHostedPage==true')]", " I need a hosted page");
	CheckBox iNeedWidgetCodeCheckBox = new CheckBox("//span[contains(@ng-class, 'useHostedPage==false')]", " I need a hosted page");
	TextBox titleField = new TextBox("//input[@ng-model='widget.page.title']", "Title");
	Button saveAndPublish = new Button("//button[contains(@ng-click,'publishHostedPage')]", "Save and Publish");
	
	Button widgetLink = new Button("//a[contains(text(), '" + CommonUtils.getProperty("Admin.orgName").toLowerCase().replaceAll(" ", "") + "')]", "Widget link");
	
	public AddSubscribeWidgetPage fillFieldsSubscribeWidgetStepOne(String widgetName, String widgetDescription) {
		widgetNameField.type(widgetName); 
		widgetDescriptionField.type(widgetDescription);
		openComposeStepButton.click();
		sleep(10000);
		return this;		
	}

	public AddSubscribeWidgetPage fillFieldsSubscribeWidgetStepTwo() {		
		openPublishStepButton.click();
		return this;		
	}
	
	public AddSubscribeWidgetPage hosteWidgetOnLocalPage(String widgetTitle, Boolean isHostedOnLocalPage) {
		if (isHostedOnLocalPage) {
			iNeedHostedPageCheckBox.check();
		}else{
			iNeedWidgetCodeCheckBox.check();
		}		
		titleField.type(widgetTitle);
		saveAndPublish.scrollIntoView();
		saveAndPublish.click();
		sleep(5000);
		return this;
	}
	
	public SubscribeWidget openSubscribeWidget() {
		currentWindowHandle = getWindowHandle();
		widgetLink.click();	
		sleep(5000);
		switchToPopupWindow(currentWindowHandle);
		CommonUtils.setProperty("currentWindowHandle", currentWindowHandle);
		return new SubscribeWidget();
	}

}