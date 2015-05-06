package com.salsalabs.ignite.automation.pages.hq.activities;

import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;

public class AddPetitionPage extends AddSubscribeWidgetPage {
	
	public AddPetitionPage() {
		openComposeStepButton = new ButtonImpl("//button[@id='btnCompose3']", "Compose");
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
}
