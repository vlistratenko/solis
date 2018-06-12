package com.salsalabs.ignite.automation.pages.hq.activities;

import com.salsalabs.ignite.automation.common.PropertyName;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.VE2Elements.PetitionFormElements;
import com.salsalabs.ignite.automation.elements.VE2Elements.Signatures;
import com.salsalabs.ignite.automation.elements.VE2Elements.SignupFormElements;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.SelectBoxImpl;
import org.openqa.selenium.JavascriptExecutor;

public class AddPetitionPage extends AddSubscribeWidgetPage {

	private Button publishImmediately = new ButtonImpl("//label[@for='publish-comment-immediately']",
			"Publish Immediately");
	private Button allowHideComment = new ButtonImpl("//label[@for='petition-allow-hide-comment']",
			"Allow hide comment");
	private Button saveConfigBtn = new ButtonImpl("//*[@autotest-id='btn_save_form_config']", "Save");
	private Button publishCommentsImmediately = new ButtonImpl(
			"//div[@class='large-5 columns end']//label[@class='ng-binding']//span", "Publish Immediately");
	private Button configBtn = new ButtonImpl("//div[contains(@class, 'goalTrackerBlock')]/../div[@handles-content-form]/div[@class='right']/span[@button-content-edit-form]", "Edit");
	private Button goalTracker = new ButtonImpl("//div[contains(@class, 'goalTrackerBlock')]", "Goal Tracker");

	public AddPetitionPage() {
		openComposeStepButton = new ButtonImpl("//button[@id='btnGo-setup-compose']", "Compose");
		linkProperty = PropertyName.PETITION_WIDGET_LINK;
	}

	@Override
	public AddPetitionPage selectLayoutStep() {
		return (AddPetitionPage) selectLayoutStep(4);
	}

	@Override
	public AddPetitionPage selectLayoutStep(String type) {
		return (AddPetitionPage) super.selectLayoutStep(type);
	}
	
	@Override
	public AddPetitionPage proceedToTheNextAutoresponderStep() {
		return (AddPetitionPage) super.proceedToTheNextAutoresponderStep();
	}

	@Override
	protected SubscribeWidget newWidget(boolean clean) {
		return new PetitionWidget(clean);
	}

	public PetitionWidget openPetitionWidget() {
		return openWidget(PetitionWidget.class);
	}
	
	public PetitionWidget openPetitionWidget(String widgetName) {
		this.widgetName = widgetName;
		return this.openPetitionWidget();
	}

	public void  configPetition(){
		goalTracker.click();
		sleep(2);
		configBtn.clickJS();
		sleep(3);
		publishImmediately.click();
		allowHideComment.click();
		saveConfigBtn.click();
		sleep(5);
	}

	public AddSubscribeWidgetPage checkIdLikeToReceiveUpdatesCheckBoxProperties(String fieldLabel, String defaultValue) {
		idLikeToReceiveUpdatesElement.scrollIntoView();
		idLikeToReceiveUpdatesElement.doubleClick();
		FormFieldConfigurationModalWindow modal = new FormFieldConfigurationModalWindow();
		verifier.verifyEquals(
				(String) ((JavascriptExecutor) driver).executeScript("return document.querySelector('#FieldEditModal-form\\\\3a petition > div.appModalContent > div > div > div.element-config-container.vertical-scroll > div > div > div > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div > div > div > div > input').value"),
				fieldLabel);
		verifier.verifyEquals(((SelectBoxImpl) modal.checkBoxDefaultValue).getSelectedLabel(modal.checkBoxDefaultValue.getPath()), defaultValue, "Check default value", true);
		modal.saveButton.click();
		return this;
	}

	public AddPetitionPage dropVESignaturesElement(){
		new PetitionFormElements().performDrop(PetitionFormElements.VE.SIGNATURES);
		waitUntilAngularIsComplete();
		sleep(1);
		return this;
	}

	public AddPetitionPage editSignaturesField(){
		new PetitionFormElements().performEdit(PetitionFormElements.VE.SIGNATURES);
		return this;
	}
}

