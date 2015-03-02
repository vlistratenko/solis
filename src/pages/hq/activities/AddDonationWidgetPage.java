package pages.hq.activities;

import core.util.CommonUtils;
import core.util.PropertyName;
import elements.Button;
import elements.CheckBox;
import elements.TextBox;
import elements.impl.ButtonImpl;
import elements.impl.CheckBoxImpl;
import elements.impl.TextBoxImpl;

public class AddDonationWidgetPage extends ActivitiesPage {

	String currentWindowHandle;
	String widgetName;
	TextBox widgetNameField = new TextBoxImpl("//input[@name='name']", "Widget Name", true);
	TextBox widgetDescriptionField = new TextBoxImpl("//textarea[@name='description']", "Widget description", false);
	Button nextButton = new ButtonImpl("//button[@id='btnCompose2']", "Design My Widget button", true);
	Button publishButton = new ButtonImpl("//button[@id='btnPublish']", "Publish button", true);
	CheckBox iNeedHostedPageCheckBox = new CheckBoxImpl("//span[contains(@ng-class, 'useHostedPage==true')]", " I need a hosted page");
	CheckBox iNeedWidgetCodeCheckBox = new CheckBoxImpl("//span[contains(@ng-class, 'useHostedPage==false')]", " I need a hosted page");
	
	TextBox titleField = new TextBoxImpl("//input[@ng-model='widget.page.title']", "Title");
	Button saveAndPublish = new ButtonImpl("//button[contains(@ng-click,'publishHostedPage')]", "Save and Publish");
	Button layoutButton = new ButtonImpl("//*[.='layoutName']", "Layout label");
	
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
	
	public AddDonationWidgetPage hosteWidgetOnLocalPage(String widgetTitle, boolean isHostedOnLocalPage) {
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
		widgetLink = new ButtonImpl("//a[contains(text(), '" + widgetName.toLowerCase() + "')]", "Widget link");
		currentWindowHandle = getWindowHandle();
		widgetLink.click();			
		switchToPopupWindow(currentWindowHandle);
		CommonUtils.setProperty(PropertyName.CURRENT_WINDOW_HANDLE, currentWindowHandle);
		return new DonationWidget();
	}
	
	public AddDonationWidgetPage saveDonationWidgetLink() {
		sleep(5000);
		widgetLink = new ButtonImpl("//a[contains(text(), '" + widgetName.toLowerCase() + "')]", "Widget link");
		currentWindowHandle = getWindowHandle();
		CommonUtils.setProperty(PropertyName.DONATION_WIDGET_LINK, widgetLink.getAttribute("href"));
		return this;
	}

}
