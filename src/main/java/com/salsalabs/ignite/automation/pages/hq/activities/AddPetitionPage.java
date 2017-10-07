package com.salsalabs.ignite.automation.pages.hq.activities;

import com.salsalabs.ignite.automation.common.PropertyName;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.DragableElement;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.DragableElementImp;
import com.salsalabs.ignite.automation.elements.impl.LabelImpl;
import com.salsalabs.ignite.automation.pages.hq.manage.SocialMediaPages;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
	
	public PetitionWidget openPetitionWidget(String formUrl) {
		this.widgetName = formUrl;
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
	
	

	

	

}
