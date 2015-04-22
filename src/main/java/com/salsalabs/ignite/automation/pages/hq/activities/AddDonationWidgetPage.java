package com.salsalabs.ignite.automation.pages.hq.activities;

import org.apache.commons.lang3.RandomStringUtils;

import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.PropertyName;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.CheckBox;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.CheckBoxImpl;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;

public class AddDonationWidgetPage extends ActivitiesPage {

	String currentWindowHandle;
	String widgetName;
	TextBox widgetNameField = new TextBoxImpl("//input[@name='name']", "Widget Name", true);
	TextBox widgetDescriptionField = new TextBoxImpl("//textarea[@name='description']", "Widget description", false);
	Button nextButton = new ButtonImpl("//button[@id='btnCompose2']", "Design My Widget button", true);
	Button goToStep3Btn = new ButtonImpl("//button[@id='btnCompose3']", "Next: Page Settings >>");
	Button publishButton = new ButtonImpl("//button[@id='btnPublish']", "Publish button", true);
	CheckBox iNeedHostedPageCheckBox = new CheckBoxImpl("//span[contains(@ng-class, 'useHostedPage==true')]", " I need a hosted page");
	CheckBox iNeedWidgetCodeCheckBox = new CheckBoxImpl("//span[contains(@ng-class, 'useHostedPage==false')]", " I need a hosted page");
	
	TextBox titleField = new TextBoxImpl("//input[@ng-model='widget.page.title']", "Title");
	Button saveAndPublish = new ButtonImpl("//button[contains(@ng-click,'publishHostedPage')]", "Save and Publish");
	Button layoutButton = new ButtonImpl("//*[.='layoutName']", "Layout label");
	
	Button widgetLink;
	
	public AddDonationWidgetPage() {
		widgetButtonText = "Donate!";
	}
	
	public AddDonationWidgetPage createDonationWidgetSetupStep() {
		String formName = "DonationFormName_" + RandomStringUtils.randomAlphanumeric(5);
		String formDescription = "DonationFormDescription_" + RandomStringUtils.randomAlphanumeric(10);
		return this.createDonationWidgetSetupStep(formName, formDescription);
	}
	
	public AddDonationWidgetPage createDonationWidgetSetupStep(String widgetName, String widgetDescription) {
		this.widgetName = widgetName;
		widgetNameField.type(widgetName);
		widgetDescriptionField.type(widgetDescription);
		nextButton.click();
		sleep(10);
		return this;
	}
	
	public AddDonationWidgetPage selectLayoutForDanationWidgetStep() {
		return this.selectLayoutForDanationWidgetStep(chooseRandomLayout());
	}
	
	public AddDonationWidgetPage selectLayoutForDanationWidgetStep(String layoutName) {
		sleep(10);
		layoutButton.changePath("layoutName", layoutName);
		layoutButton.click();
		sleep(10);
		return this;
	}
	
	// Go to settings of form
	public AddDonationWidgetPage fillThirdStep() {
		goToStep3Btn.click();
		sleep(5);
		return this;
	}
	
	public AddDonationWidgetPage createDonationWidgetDesignWidgetStep() {
		publishButton.click();
		sleep(10);
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
	
	public DonationWidget openDonationWidget(String formName) {
		this.widgetName = formName;
		return this.openDonationWidget();
	}
	
	public DonationWidget openDonationWidget() {
		sleep(5);
		widgetLink = new ButtonImpl("//a[contains(text(), '" + widgetName.toLowerCase() + "')]", "Widget link");
		currentWindowHandle = getWindowHandle();
		widgetLink.click();			
		switchToPopupWindow(currentWindowHandle);
		CommonUtils.setProperty(PropertyName.CURRENT_WINDOW_HANDLE, currentWindowHandle);
		return new DonationWidget();
	}
	
	public AddDonationWidgetPage saveDonationWidgetLink() {
		sleep(5);
		widgetLink = new ButtonImpl("//a[contains(text(), '" + widgetName.toLowerCase() + "')]", "Widget link");
		currentWindowHandle = getWindowHandle();
		CommonUtils.setProperty(PropertyName.DONATION_WIDGET_LINK, widgetLink.getAttribute("href"));
		return this;
	}

}
