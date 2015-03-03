package pages.hq.activities;

import core.util.CommonUtils;
import core.util.PropertyName;
import elements.Button;
import elements.CheckBox;
import elements.TextBox;
import elements.impl.ButtonImpl;
import elements.impl.CheckBoxImpl;
import elements.impl.TextBoxImpl;

public class AddSubscribeWidgetPage extends ActivitiesPage{
	String widgetName;
	String currentWindowHandle;
	TextBox widgetNameField = new TextBoxImpl("//input[@name='name']", "Widget name");
	TextBox widgetDescriptionField = new TextBoxImpl("//textarea[@name='description']", "Widget Description");
	Button openComposeStepButton = new ButtonImpl("//button[@id='btnCompose']", "Compose");
	Button openPublishStepButton = new ButtonImpl("//button[@id='btnPublish']", "Publish");
	CheckBox iNeedHostedPageCheckBox = new CheckBoxImpl("//span[contains(@ng-class, 'useHostedPage==true')]", " I need a hosted page");
	CheckBox iNeedWidgetCodeCheckBox = new CheckBoxImpl("//span[contains(@ng-class, 'useHostedPage==false')]", " I need a hosted page");
	TextBox titleField = new TextBoxImpl("//input[@ng-model='widget.page.title']", "Title");
	Button saveAndPublish = new ButtonImpl("//button[contains(@ng-click,'publishHostedPage')]", "Save and Publish");
	Button widgetLink;
	Button layoutButton = new ButtonImpl("//*[.='layoutName']", "Layout label");
	
	

	public AddSubscribeWidgetPage fillFieldsSubscribeWidgetStepOne(String widgetName, String widgetDescription) {
		this.widgetName = widgetName;
		widgetNameField.type(widgetName); 
		widgetDescriptionField.type(widgetDescription);
		openComposeStepButton.click();
		sleep(10);
		return this;		
	}
	
	public AddSubscribeWidgetPage selectLayoutForSubscribeWidgetStep(String layoutName) {
		layoutButton.changePath("layoutName", layoutName);
		layoutButton.click();
		sleep(5);
		return this;
	}

	public AddSubscribeWidgetPage fillFieldsSubscribeWidgetStepTwo() {		
		openPublishStepButton.click();
		sleep(10);
		return this;		
	}
	
	public AddSubscribeWidgetPage hosteWidgetOnLocalPage(String widgetTitle, boolean isHostedOnLocalPage) {
		if (isHostedOnLocalPage) {
			iNeedHostedPageCheckBox.check();
		}else{
			iNeedWidgetCodeCheckBox.check();
		}		
		titleField.type(widgetTitle);
		saveAndPublish.scrollIntoView();
		saveAndPublish.click();
		sleep(5);
		return this;
	}
	
	public SubscribeWidget openSubscribeWidget() {
		widgetLink = new ButtonImpl("//a[contains(text(), '" + widgetName.toLowerCase() + "')]", "Widget link");
		CommonUtils.setProperty(PropertyName.SUBSCRIBE_WIDGET_LINK, widgetLink.getAttribute("href"));
		currentWindowHandle = getWindowHandle();
		widgetLink.click();	
		sleep(5);
		switchToPopupWindow(currentWindowHandle);
		CommonUtils.setProperty(PropertyName.CURRENT_WINDOW_HANDLE, currentWindowHandle);
		return new SubscribeWidget();
	}

}
