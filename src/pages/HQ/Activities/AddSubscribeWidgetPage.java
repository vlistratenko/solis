package pages.HQ.Activities;

import objects.Button;
import objects.CheckBox;
import objects.TextBox;
import selenium.CommonUtils;

public class AddSubscribeWidgetPage extends ActivitiesPage{
	String widgetName;
	String currentWindowHandle;
	TextBox widgetNameField = new TextBox("//input[@name='name']", "Widget name");
	TextBox widgetDescriptionField = new TextBox("//textarea[@name='description']", "Widget Description");
	Button openComposeStepButton = new Button("//button[@id='btnCompose']", "Compose");
	Button openPublishStepButton = new Button("//button[@id='btnPublish']", "Publish");
	CheckBox iNeedHostedPageCheckBox = new CheckBox("//span[contains(@ng-class, 'useHostedPage==true')]", " I need a hosted page");
	CheckBox iNeedWidgetCodeCheckBox = new CheckBox("//span[contains(@ng-class, 'useHostedPage==false')]", " I need a hosted page");
	TextBox titleField = new TextBox("//input[@ng-model='widget.page.title']", "Title");
	Button saveAndPublish = new Button("//button[contains(@ng-click,'publishHostedPage')]", "Save and Publish");
	Button widgetLink;
	Button layoutButton = new Button("//*[.='layoutName']", "Layout label");
	
	

	public AddSubscribeWidgetPage fillFieldsSubscribeWidgetStepOne(String widgetName, String widgetDescription) {
		this.widgetName = widgetName;
		widgetNameField.type(widgetName); 
		widgetDescriptionField.type(widgetDescription);
		openComposeStepButton.click();
		sleep(10000);
		return this;		
	}
	
	public AddSubscribeWidgetPage selectLayoutForSubscribeWidgetStep(String layoutName) {
		layoutButton.changePath("layoutName", layoutName);
		layoutButton.click();
		sleep(5000);
		return this;
	}

	public AddSubscribeWidgetPage fillFieldsSubscribeWidgetStepTwo() {		
		openPublishStepButton.click();
		sleep(10000);
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
		widgetLink = new Button("//a[contains(text(), '" + widgetName.toLowerCase() + "')]", "Widget link");
		CommonUtils.setProperty("subscribeWidgetLink", widgetLink.getAttribute("href"));
		currentWindowHandle = getWindowHandle();
		widgetLink.click();	
		sleep(5000);
		switchToPopupWindow(currentWindowHandle);
		CommonUtils.setProperty("currentWindowHandle", currentWindowHandle);
		return new SubscribeWidget();
	}

}
