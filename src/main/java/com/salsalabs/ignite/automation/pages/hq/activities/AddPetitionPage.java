package com.salsalabs.ignite.automation.pages.hq.activities;

import com.salsalabs.ignite.automation.common.PropertyName;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.LabelImpl;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddPetitionPage extends AddSubscribeWidgetPage {
	
	private Button configBtn = new ButtonImpl("//div[@class='right']//span[@button-content-edit-form]", "Edit");
	private Button publishImmediately = new ButtonImpl("//label[@for='publish-comment-immediately']", "Publish Immediately");
	private Button allowHideComment = new ButtonImpl("//label[@for='petition-allow-hide-comment']", "Allow hide comment");
	private Button saveConfigBtn = new ButtonImpl("//*[@autotest-id='btn_save_form_config']", "Save");
	private Button publishCommentsImmediately = new ButtonImpl("//div[@class='large-5 columns end']//label[@class='ng-binding']//span", "Publish Immediately"); 	
	
			
	public AddPetitionPage() {
		openComposeStepButton = new ButtonImpl("//button[@id='btnCompose3']", "Compose");
		linkProperty = PropertyName.PETITION_WIDGET_LINK;
	}
	
	
	@Override
	public AddPetitionPage selectLayoutStep() {
		return (AddPetitionPage)  selectLayoutStep(4);
	}
	
	@Override
	protected SubscribeWidget newWidget(boolean clean) {
		return new PetitionWidget(clean);
	}
	
	public PetitionWidget openPetitionWidget() {
		return openWidget(PetitionWidget.class);
	}
	
	public void  configPetition(){
		sleep(2);
		configBtn.clickJS();
		sleep(3);
		allowHideComment.click();
		publishCommentsImmediately.click();
		saveConfigBtn.click();
		sleep(5);
	}
		
		 
		}
		
	
