package com.salsalabs.ignite.automation.pages.hq.activities;

import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.HttpClient;
import com.salsalabs.ignite.automation.common.PropertyName;
import com.salsalabs.ignite.automation.common.Supporter;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.CheckBox;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.VE2Elements.SignupFormElements;
import com.salsalabs.ignite.automation.elements.impl.*;
import com.salsalabs.ignite.automation.pages.hq.HomePage;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import com.salsalabs.ignite.automation.pages.hq.basic.basicLayoutClass;
import org.json.JSONException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.List;


public class AddSubscribeWidgetPage extends HomePage {
	protected String widgetName;
	protected String currentWindowHandle;
	protected TextBox widgetNameField = new TextBoxImpl("//input[@name='name']", "Widget name");
	protected TextBox widgetDescriptionField = new TextBoxImpl("//textarea[@name='description']", "Widget Description");
	protected Button openComposeStepButton = new ButtonImpl("//button[@id='btnGo-setup-compose']", "Compose");
	protected Button composeButton = new ButtonImpl("//button[@id='btnCompose']", "Compose");
	protected Button openPublishStepButton = new ButtonImpl("//button[@title='Next: Publish This Form']", "Publish");
	protected CheckBox iNeedHostedPageCheckBox = new CheckBoxImpl("//span[contains(@ng-class, 'useHostedPage==true')]", " I need a hosted page");
	protected CheckBox iNeedWidgetCodeCheckBox = new CheckBoxImpl("//span[contains(@ng-class, 'useHostedPage==false')]", " I need a hosted page");
	protected TextBox titleField = new TextBoxImpl("//input[@ng-model='widget.page.title']", "Title");
	protected Button saveAndPublish = new ButtonImpl("//button[contains(@ng-click,'publishHostedPage')]", "Save and Publish");
	protected Button widgetLink;
	protected Button layoutButton = new ButtonImpl("//*[.='layoutName']", "Layout label");
	protected Button toPageSettingsBtn = new ButtonImpl("//button[@id='btnCompose3']", "Next: Page Settings");
	protected Button settingsButton = new ButtonImpl("//a[@class='account-info-drop saveBarBtn']", "Settings Button");
	protected Button previewButton = new ButtonImpl("//button[@title='Preview Output']", "Preview Button");
	protected Button makePrivateButton = new ButtonImpl("//a[contains(@processing-text, 'Unpublishing...')]", "Unpublishing");
	protected Button deleteBtn = new ButtonImpl("//*[contains(text(), 'Delete')]", "Delete widget");
	protected Button confirmDeletionBtn = new ButtonImpl("//span[contains(text(), 'Delete')]/ancestor:: button", "Yes, delete already!");
	protected Button nextResultButton = new ButtonImpl("//button[contains(@title, 'Results')]", "Yes, delete already!");
	protected Button saveButtonInAutoresponders = new ButtonImpl("//*[@id='btnSave-autoresponders']", "Save button in Autoresponders page");
	protected String linkProperty = PropertyName.SUBSCRIBE_WIDGET_LINK;
	protected Button toAutoresponders = new ButtonImpl("//*[@id='btnGo-compose-autoresponders']", "Next to Autoresponders button");
	protected Button publishFromAutorespondersTab = new ButtonImpl("//*[@id='btnGo-autoresponders-publish']", "Publish button");
	protected Button closeFeedbackWindowButton = new ButtonImpl("//feedback-dialog//a", "Close feedback window button");
	protected Button idLikeToReceiveUpdatesElement = new ButtonImpl("//*[@name='contactOptInCB']/parent::*", "Edit element");
	protected List<WebElement> formSteps = driver.findElements(By.xpath(".//*[.='Step']/following-sibling::*"));
	protected PanelImpl stickyTopBar = new PanelImpl(".//*[@class='toolbar sticky']", "Top bar");

	public AddSubscribeWidgetPage fillFieldsWidgetStepOne(String widgetName, String widgetDescription) {
		this.widgetName = widgetName;
		widgetNameField.type(widgetName); 
		widgetDescriptionField.type(widgetDescription);
		openComposeStepButton.click();
		sleep(5);
		return  this;		
	}
	
	public void removeWidget() {
		settingsButton.click();
		deleteBtn.click();
		confirmDeletionBtn.click();
		sleep(3);
	}
	
	protected int chooseRandomLayout() {
		return RandomUtils.nextInt(1, 6);
	}
	
	public AddSubscribeWidgetPage selectLayoutStep() {
		return this.selectLayoutStep(chooseRandomLayout());
	}
	
	public AddSubscribeWidgetPage selectLayoutStep(int layout) {
		Button lay = new ButtonImpl("(//button[contains(@ng-click,'selectItem')])[" + layout + "]", layout + " layout");
		lay.click();
		composeButton.click();
		sleep(10);
		return this;
	}
	
	public AddSubscribeWidgetPage publishForm() {
		openPublishStepButton.click();
		sleep(5);
		nextResultButton.waitElement();
		/*for(int i = 0; i <3; i++){
			if(waitConditionBecomesTrue(nextResultButton.isDisplayed(), 5));
			break;
		}*/
		
		return this;
	}

    public AddSubscribeWidgetPage publishFromAutoresponders() {
		sleep(3);
		publishFromAutorespondersTab.fluentWaitForElementPresenceIgnoringExceptions(120);
        publishFromAutorespondersTab.click();
        try {
        	closeFeedbackWindowButton.fluentWaitForElementPresenceIgnoringExceptions();
		} catch (TimeoutException e) {}
		closeFeedbackWindowButton.clickJS();
        return this;
    }

	public AddSubscribeWidgetPage clickSaveInAutorespondersTab() {
		saveButtonInAutoresponders.fluentWaitForElementPresenceIgnoringExceptions();
		saveButtonInAutoresponders.click();
		return this;
	}

    public AddSubscribeWidgetPage goToAutorespondersTab() {
		toAutoresponders.fluentWaitForElementPresenceIgnoringExceptions(60);
        toAutoresponders.click();
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
		widgetLink.fluentWaitForElementPresenceIgnoringExceptions();
		if (!widgetLink.isNotDisplayed()) {
			CommonUtils.setProperty(linkProperty, widgetLink.getAttribute("href"));
		}		
		String link = CommonUtils.getProperty(linkProperty);
		currentWindowHandle = getWindowHandle();
		this.openInNewWindow(link);
		sleep(7);
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

	public SubscribeWidget openSubscribeWidget(String widgetName) {
		this.widgetName = widgetName;
		return openWidget(SubscribeWidget.class);
	}
	
	public AddSubscribeWidgetPage verifyWidgetVisible(boolean visibleForCm, boolean visibleForSupporter) {
		String link = CommonUtils.getProperty(linkProperty);
		String primaryHandle = this.getWindowHandle();
		openInNewWindow(link);
		verifyWidgetElements(visibleForCm, visibleForSupporter);
		this.closeWindow();
		this.switchToWindow(primaryHandle);
		return this;
	}
	
	public AddSubscribeWidgetPage verifyWidgetVisible(boolean visible) {
		String link = CommonUtils.getProperty(linkProperty);
		String primaryHandle = this.getWindowHandle();
		openInNewWindow(link);
		verifyWidgetElements(visible);
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
	
	private void verifyWidgetElements(boolean visible) {
		newWidget(true).verifyWidgetElementsVisible(visible);
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
	
	public AddSubscribeWidgetPage createForm(String widgetName, String widgetDescription, int layoutName) {
		this.fillFieldsWidgetStepOne(widgetName, widgetDescription);
		this.selectLayoutStep(layoutName);
		this.publishForm();
		return this;
	}
	
	public void verifyFormLinkIsPresent(String widgetName) {
		widgetLink = new ButtonImpl("//a[contains(text(), '" + widgetName.toLowerCase() + "')]", "Widget link");
		verifier.verifyElementIsDisplayed(true, widgetLink);
		CommonUtils.setProperty(linkProperty, widgetLink.getAttribute("href"));
	}	
	
	public AddSubscribeWidgetPage previewForm(){
		previewButton.click();
		sleep(5);
		switchToFrame("//iframe[@id='previewPaneIframe']");
		newWidget(false).verifyWidgetElementsVisible(true);
		switchDefaultContent();
		return this;
	}

	public AddSubscribeWidgetPage selectLayoutStep(String layout) {
		basicLayoutClass.selectLayout(layout);
		composeButton.fluentWaitForElementPresenceIgnoringExceptions();
		composeButton.click();
		sleep(10);
	    return this;
	}

	public AddSubscribeWidgetPage dropOneColumnRow(){
		new SignupFormElements().performDrop(SignupFormElements.VE.ONECOLUMN);
		return this;
	}

	public AddSubscribeWidgetPage dropVEFormElement(){
		new SignupFormElements().performDrop(SignupFormElements.VE.FORM);
		return this;
	}

	public FormFieldConfigurationModalWindow editVEField(String fieldName){
		new SignupFormElements().performEdit(SignupFormElements.VE.FORM_FIELD, fieldName);
		return new FormFieldConfigurationModalWindow();
	}

	public AddSubscribeWidgetPage deleteAllEmptyFormFields(){
		deleteFormField("empty");
		return this;
	}

	public AddSubscribeWidgetPage deleteFormField(String fieldName){
		new SignupFormElements().performDelete(SignupFormElements.VE.FORM_FIELD, fieldName);
		return this;
	}

    public AddSubscribeWidgetPage proceedToTheNextAutoresponderStep() {
        sleep(10);
        Button nextAutoresponder = new ButtonImpl("//button[@title='Next: Autoresponders']" , "Next Autoresponder Step");
        nextAutoresponder.waitElement();
        nextAutoresponder.click();
        sleep(10);
        return this;
    }

	public void verifySubmittedSupporterFieldsArePresentInSupporterDetails(String host, String login, String password) {
		try {
			HttpClient client = new HttpClient(host).login(login,password);
			client.waitUntilSupporterExists(CommonUtils.getProperty("personEmail"),20);
			Supporter sup = client.getSupporterByEmail(CommonUtils.getProperty("personEmail"));
			verifier.verifyEquals(sup.getFinalEMAIL(), CommonUtils.getProperty("personEmail").toLowerCase());
			verifier.verifyEquals(sup.getFirstName(), CommonUtils.getProperty("personFName"));
			verifier.verifyEquals(sup.getLastName(), CommonUtils.getProperty("personLName"));
			verifier.verifyEquals(sup.getCountry(), CommonUtils.getProperty("country"));
			verifier.verifyEquals(sup.getCity(), CommonUtils.getProperty("personCity"));
			verifier.verifyEquals(sup.getZipCode(), CommonUtils.getProperty("personZip"));
			verifier.verifyEquals(sup.getAddressLine1(), CommonUtils.getProperty("addressLine1"));
			verifier.verifyEquals(sup.getAddressLine2(), CommonUtils.getProperty("addressLine2"));
			verifier.verifyEquals(sup.getPhoneHome(), CommonUtils.getProperty("homePhone"));
			verifier.verifyEquals(sup.getMiddleName(),CommonUtils.getProperty("personMName"));
			verifier.verifyEquals(sup.getSuffix(),CommonUtils.getProperty("suffix"));
			verifier.verifyEquals(sup.getTitle(),CommonUtils.getProperty("title"));
			verifier.verifyEquals(sup.getPhoneWork(),CommonUtils.getProperty("workPhone"));
			verifier.verifyEquals(sup.getPhoneCell(),CommonUtils.getProperty("cellPhone"));
			verifier.verifyEquals(sup.getDateOfBirth(),CommonUtils.getProperty("dateOfBirth"));
			verifier.verifyEquals(sup.getState(),CommonUtils.getProperty("state"));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public void verifySubmittedCustomFieldsArePresentInSupporterDetails(String host, String login, String password) {
				try {
			HttpClient client = new HttpClient(host).login(login,password);
			client.waitUntilSupporterExists(CommonUtils.getProperty("personEmail"),20);
			Supporter sup = client.getSupporterByEmail(CommonUtils.getProperty("personEmail"));
			verifier.verifyEquals(sup.getCustomFieldValue("supporterTextBoxCustomField"), CommonUtils.getProperty("supporterTextBoxCustomFieldValue"));
			verifier.verifyEquals(sup.getCustomFieldValue("supporterNumberCustomField"), CommonUtils.getProperty("supporterNumberCustomFieldValue"));
			verifier.verifyEquals(sup.getCustomFieldValue("supporterYesNoCustomField"), CommonUtils.getProperty("supporterYesNoCustomFieldValue").toLowerCase());
			verifier.verifyEquals(sup.getCustomFieldValue("supporterDateTimeCustomField"), CommonUtils.getProperty("supporterDateTimeCustomFieldValue"));
			verifier.verifyEquals(sup.getCustomFieldValue("supporterSingleChoiceCustomField"), CommonUtils.getProperty("supporterSingleChoiceCustomFieldValue"));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}


	public AddSubscribeWidgetPage editIdLikeToReceiveUpdatesCheckBoxProperties(String newFieldLabel, String defaultValue) {
		idLikeToReceiveUpdatesElement.scrollIntoView();
		idLikeToReceiveUpdatesElement.doubleClick();
		FormFieldConfigurationModalWindow modal = new FormFieldConfigurationModalWindow();
		modal.labelTextBox.type(newFieldLabel);
		modal.checkBoxDefaultValue.selectByLabel(defaultValue);
		modal.saveButton.click();
		return this;
	}

	public AddSubscribeWidgetPage checkIdLikeToReceiveUpdatesCheckBoxProperties(String fieldLabel, String defaultValue) {
		idLikeToReceiveUpdatesElement.scrollIntoView();
		idLikeToReceiveUpdatesElement.doubleClick();
		FormFieldConfigurationModalWindow modal = new FormFieldConfigurationModalWindow();
		verifier.verifyEquals(
				(String) ((JavascriptExecutor) driver).executeScript("return document.querySelector('#FieldEditModal-form\\\\3a subscribe > div.appModalContent > div > div > div.element-config-container.vertical-scroll > div > div > div > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div > div > div > div > input').value"),
				fieldLabel);
		verifier.verifyEquals(((SelectBoxImpl) modal.checkBoxDefaultValue).getSelectedLabel(modal.checkBoxDefaultValue.getPath()), defaultValue, "Check default value", true);
		modal.saveButton.click();
		return this;
	}


	public AddSubscribeWidgetPage switchBetweenFormSteps(int stepNumber) {
		Button step = new ButtonImpl(".//*[@ng-repeat='step in elementSteps']" + "[" + stepNumber + "]", "Form step");
		step.scrollIntoView();
		step.clickJS();
		hideTopBar();
		return this;
	}

	public AddSubscribeWidgetPage hideTopBar() {
		stickyTopBar.removeElementFromDom();
		return this;
	}
}
