package com.salsalabs.ignite.automation.pages.hq.activities;

import java.util.Set;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.PropertyName;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.CheckBox;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.CheckBoxImpl;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;
import com.salsalabs.ignite.automation.pages.hq.HomePage;

public class AddSubscribeWidgetPage extends HomePage {
	protected String[] layouts = {"Hero", "Sidebar Right", "Hero Sidekick", "Newsletter", 
			"Sidebar Hero Left", "Sidebar Left", "Sidebar Hero Right", "Basic"};
	protected String widgetName;
	protected String currentWindowHandle;
	protected TextBox widgetNameField = new TextBoxImpl("//input[@name='name']", "Widget name");
	protected TextBox widgetDescriptionField = new TextBoxImpl("//textarea[@name='description']", "Widget Description");
	protected Button openComposeStepButton = new ButtonImpl("//button[@id='btnCompose']", "Compose");
	protected Button openPublishStepButton = new ButtonImpl("//button[@id='btnPublish']", "Publish");
	protected CheckBox iNeedHostedPageCheckBox = new CheckBoxImpl("//span[contains(@ng-class, 'useHostedPage==true')]", " I need a hosted page");
	protected CheckBox iNeedWidgetCodeCheckBox = new CheckBoxImpl("//span[contains(@ng-class, 'useHostedPage==false')]", " I need a hosted page");
	protected TextBox titleField = new TextBoxImpl("//input[@ng-model='widget.page.title']", "Title");
	protected Button saveAndPublish = new ButtonImpl("//button[contains(@ng-click,'publishHostedPage')]", "Save and Publish");
	protected Button widgetLink;
	protected Button layoutButton = new ButtonImpl("//*[.='layoutName']", "Layout label");
	protected Button toPageSettingsBtn = new ButtonImpl("//button[@id='btnCompose3']", "Next: Page Settings");
	protected Button settingsButton = new ButtonImpl("//a[@class='account-info-drop saveBarBtn']", "Settings Button");
	protected Button previewButton = new ButtonImpl("//div[@ng-click='previewWidgetNoSave()']", "Preview Button");
	protected Button makePrivateButton = new ButtonImpl("//a[contains(@processing-text, 'Unpublishing...')]", "Unpublishing");
	protected Button deleteBtn = new ButtonImpl("//*[contains(text(), 'Delete')]", "Delete widget");
	protected Button confirmDeletionBtn = new ButtonImpl("//*[@id='formConfigModal']/div[2]/button[2]", "Yes, delete already!");
	
	public AddSubscribeWidgetPage fillFieldsWidgetStepOne(String widgetName, String widgetDescription) {
		this.widgetName = widgetName;
		widgetNameField.type(widgetName); 
		widgetDescriptionField.type(widgetDescription);
		openComposeStepButton.click();
		sleep(5);
		return this;		
	}
	
	public void removeWidget() {
		settingsButton.click();
		deleteBtn.click();
		confirmDeletionBtn.click();
		sleep(3);
	}
	
	protected String chooseRandomLayout() {
		return this.layouts[RandomUtils.nextInt(0, this.layouts.length)];
	}
	
	public AddSubscribeWidgetPage selectLayoutStep() {
		return this.selectLayoutStep(chooseRandomLayout());
	}
	
	public AddSubscribeWidgetPage selectLayoutStep(String layoutName) {
		layoutButton.changePath("layoutName", layoutName);
		layoutButton.click();
		sleep(5);
		return this;
	}

	public AddSubscribeWidgetPage fillFieldsWidgetStepTwo() {
		toPageSettingsBtn.click();
		sleep(5);
		return this;		
	}
	
	public AddSubscribeWidgetPage publishForm() {
		openPublishStepButton.click();
		sleep(5);
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
	
	protected <T> T openWidget(Class<T> clazz) {
		widgetLink = new ButtonImpl("//a[contains(text(), '" + widgetName.toLowerCase() + "')]", "Widget link");
		if (clazz.equals(SubscribeWidget.class)) {
			CommonUtils.setProperty(PropertyName.SUBSCRIBE_WIDGET_LINK, widgetLink.getAttribute("href"));
		} else if (clazz.equals(DonationWidget.class)) {
			CommonUtils.setProperty(PropertyName.DONATION_WIDGET_LINK, widgetLink.getAttribute("href"));
		} else if (clazz.equals(PetitionWidget.class)) {
			CommonUtils.setProperty(PropertyName.PETITION_WIDGET_LINK, widgetLink.getAttribute("href"));
		}
		currentWindowHandle = getWindowHandle();
		widgetLink.click();	
		sleep(5);
		switchToPopupWindow(currentWindowHandle);
		CommonUtils.setProperty(PropertyName.CURRENT_WINDOW_HANDLE, currentWindowHandle);
		try {
			return clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			logger.error("",e);
		}
		return null;
	}
	
	public SubscribeWidget openSubscribeWidget() {
		return openWidget(SubscribeWidget.class);
	}
	
	public AddSubscribeWidgetPage verifyWidgetVisible(String link, boolean visibleForCm, boolean visibleForSupporter) {
		String primaryHandle = this.getWindowHandle();
		this.openInNewWindow(link + "/index.html");
		verifyWidgetElements(visibleForCm, visibleForSupporter);
		this.closeWindow();
		this.switchToWindow(primaryHandle);
		return this;
	}
	
	protected SubscribeWidget newWidget(boolean clean) {
		return new SubscribeWidget(clean);
	}
	
	private void verifyWidgetElements(boolean visibleForCm, boolean visibleForSupporter) {
		newWidget(false).verifyWidgetElementsVisible(visibleForCm);
		newWidget(true).verifyWidgetElementsVisible(visibleForSupporter);
	}

	public void makeWidgetPrivate() {
		settingsButton.click();
		makePrivateButton.click();
		sleep(10);
	}

	// create widget in one step without verifications
	public AddSubscribeWidgetPage createForm() {
		String widgetName = "WidgetName_" + RandomStringUtils.randomAlphanumeric(5);
		String widgetDescription = "WidgetDescription_" + RandomStringUtils.randomAlphanumeric(10);
		return this.createForm(widgetName, widgetDescription);
	}
	
	public AddSubscribeWidgetPage createForm(String widgetName, String widgetDescription) {
		return this.createForm(widgetName, widgetDescription, chooseRandomLayout());
	}
	
	public AddSubscribeWidgetPage createForm(String widgetName, String widgetDescription, String layoutName) {
		this.fillFieldsWidgetStepOne(widgetName, widgetDescription);
		this.selectLayoutStep(layoutName);
		this.fillFieldsWidgetStepTwo();
		this.publishForm();
		return this;
	}
	
	public void verifyFormLinkIsPresent(String expectedLink) {
		Button link = new ButtonImpl("//a[@href='"+ expectedLink + "']", "Link");
		verifier.verifyElementIsDisplayed(true, link);
	}	
	
	public AddSubscribeWidgetPage previewForm(){
		settingsButton.click();
		previewButton.click();
		sleep(2);
		String primaryHandle = this.getWindowHandle();
		switchToPopupWindow(primaryHandle);
		newWidget(false).verifyWidgetElementsVisible(true);
		this.closeWindow();
		this.switchToWindow(primaryHandle);
		return this;
	}
}
