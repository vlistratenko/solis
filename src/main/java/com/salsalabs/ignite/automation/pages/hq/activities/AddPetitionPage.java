package com.salsalabs.ignite.automation.pages.hq.activities;

import com.salsalabs.ignite.automation.common.PropertyName;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.LabelImpl;

public class AddPetitionPage extends AddSubscribeWidgetPage {
	
	private Button configBtn = new ButtonImpl("//div[contains(@class, 'goalTrackerBlock')]/../div[@handles-content-form]/div[@class='right']/span[@button-content-edit-form]", "Edit");
	private Button goalTracker = new ButtonImpl("//div[contains(@class, 'goalTrackerBlock')]", "Goal Tracker");
	private Button publishImmediately = new ButtonImpl("//label[@for='publish-comment-immediately']", "Publish Immediately");
	private Button allowHideComment = new ButtonImpl("//label[@for='petition-allow-hide-comment']", "Allow hide comment");
	private Button saveConfigBtn = new ButtonImpl("//*[@autotest-id='btn_save_form_config']", "Save");
	
	public AddPetitionPage() {
		openComposeStepButton = new ButtonImpl("//button[@id='btnCompose3']", "Compose");
		linkProperty = PropertyName.PETITION_WIDGET_LINK;
	}
	
	@Override
	public AddPetitionPage selectLayoutStep() {
		return (AddPetitionPage) selectLayoutStep(3);
	}
	
	@Override
	protected SubscribeWidget newWidget(boolean clean) {
		return new PetitionWidget(clean);
	}
	
	public PetitionWidget openPetitionWidget() {
		return openWidget(PetitionWidget.class);
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
