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
	protected void verifyWidgetElements(boolean visibleForCm, boolean visibleForSupporter) {
		new PetitionWidget(false).verifyWidgetElementsVisible(visibleForCm);
		new PetitionWidget(true).verifyWidgetElementsVisible(visibleForSupporter);
	}
	
	public PetitionWidget openPetitionWidget() {
		return openWidget(PetitionWidget.class);
	}
}
