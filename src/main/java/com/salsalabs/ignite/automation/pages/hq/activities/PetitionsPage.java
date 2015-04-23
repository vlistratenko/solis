package com.salsalabs.ignite.automation.pages.hq.activities;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;

public class PetitionsPage extends ActivitiesPage {
	
	Button createPetitionButton = new ButtonImpl("//*[@autotest-id='btn_create_petition_petitions_dashboard']", "Create a Petition");

	public AddPetitionPage openAddPetitionPage() {
		createPetitionButton.click();
		return new AddPetitionPage();
	}
}
