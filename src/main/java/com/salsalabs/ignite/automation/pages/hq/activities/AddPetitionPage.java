package com.salsalabs.ignite.automation.pages.hq.activities;

import com.salsalabs.ignite.automation.common.PropertyName;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;

public class AddPetitionPage extends AddSubscribeWidgetPage {
	
	private Button configBtn = new ButtonImpl("//li[@id='formWidget']/span", "Config Button");
	private Button publishImmediately = new ButtonImpl("//label[@for='publish-comment-immediately']", "Publish Immediately");
	private Button allowHideComment = new ButtonImpl("//label[@for='petition-allow-hide-comment']", "Allow hide comment");
	private Button nextBtn = new ButtonImpl("//*[contains(@autotest-id,'btn_next_petition_config')]", "Next");
	private Button saveConfigBtn = new ButtonImpl("//*[@autotest-id='btn_save_petition_config_confirmation']", "Save");
	
	public AddPetitionPage() {
		openComposeStepButton = new ButtonImpl("//button[@id='btnCompose3']", "Compose");
		linkProperty = PropertyName.PETITION_WIDGET_LINK;
	}
	
	@Override
	public AddPetitionPage selectLayoutStep() {
		return (AddPetitionPage) selectLayoutStep("Basic");
	}
	
	@Override
	protected SubscribeWidget newWidget(boolean clean) {
		return new PetitionWidget(clean);
	}
	
	public PetitionWidget openPetitionWidget() {
		return openWidget(PetitionWidget.class);
	}
	
	public void  configPetition(){
		configBtn.clickJS();
		sleep(3);
		publishImmediately.click();
		allowHideComment.click();
		nextBtn.click();
		nextBtn.click();
		nextBtn.click();
		nextBtn.click();
		saveConfigBtn.click();
		sleep(5);
	}
}
