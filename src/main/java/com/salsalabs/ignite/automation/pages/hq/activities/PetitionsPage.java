package com.salsalabs.ignite.automation.pages.hq.activities;

import com.salsalabs.ignite.automation.common.Supporter;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.Table;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.TableImpl;

public class PetitionsPage extends ActivitiesPage {

	Button createPetitionButton = new ButtonImpl("//*[@autotest-id='btn_create_petition_petitions_dashboard']",
			"Create a Petition");
	Button moderateComments = new ButtonImpl("//button[contains(text(), 'Moderate')]", "Moderate Comments");
	Table moderateCommentTable = new TableImpl("//table", "Moderate Comments Table");
	Button actionsButton = new ButtonImpl("//a[contains(text(), 'Actions')]", "Actions Comments");
	Button approvedCommentsTab = new ButtonImpl("//a[contains(text(),'Approved')]", "Actions Comments tab");
	Button rejectCommentsTab = new ButtonImpl("//a[contains(text(),'Rejected ')]", "Rejected Comments tab");
	Table tableComments =new TableImpl("table", "Moderate Comments Table");

	public AddPetitionPage openAddPetitionPage() {
		createPetitionButton.click();
		return new AddPetitionPage();
	}

	public PetitionsPage openModerateComments() {
		moderateComments.click();
		return new PetitionsPage();
	}

	public PetitionsPage verifyCommentInTheTable(String formName, String comment, Supporter sup) {
		tableComments.waitForNotExists(5);
		verifier.verifyTrue(moderateCommentTable.isValueExists(formName) > 0,
				"Petition  item with source " + formName + "is not found");
		verifier.verifyEquals(moderateCommentTable.getCellValueUsingAllHeadersMethod(1, "Comments"), comment,
				"Wrong Comment");
		verifier.verifyEquals(moderateCommentTable.getCellValueUsingAllHeadersMethod(1, "Supporter Info")
				.contains(sup.getFinalEMAIL()), true, "Wrong Email");
		verifier.verifyEquals(moderateCommentTable.getCellValueUsingAllHeadersMethod(1, "Supporter Info")
				.contains(sup.getFirstName()), true, "Wrong First Name");
		verifier.verifyEquals(
				moderateCommentTable.getCellValueUsingAllHeadersMethod(1, "Supporter Info").contains(sup.getLastName()),
				true, "Wrong Last Name");
		verifier.verifyEquals(
				moderateCommentTable.getCellValueUsingAllHeadersMethod(1, "Supporter Info").contains(sup.getCity()),
				true, "Wrong Last Name");
		return this;
	}

	public PetitionsPage moderateComments(String formName, String hideOrPost) {
		if (moderateCommentTable.isValueExists(formName) > 0) {
			moderateCommentTable.clickInCell(1, 1);
			actionsButton.scrollIntoView();
			sleep(1);
			actionsButton.clickJS();
			Button moderateComment = new ButtonImpl("//button[contains(text(), '" + hideOrPost + "')]",
					"Actions Comments");
			moderateComment.click();
		}
		return this;
	}

	public PetitionsPage clickTheApprovedCommentsTab() {
		for (int i = 0; i < 3; i++) {
			if (waitConditionBecomesTrue(moderateCommentTable.isDisplayed(), 4)) {
				break;
			}
		}
		approvedCommentsTab.scrollIntoView();
		approvedCommentsTab.click();
		return this;
	}
	
	public PetitionsPage clickTheRejectedCommentsTab() {
		for (int i = 0; i < 3; i++) {
			if (waitConditionBecomesTrue(rejectCommentsTab.isDisplayed(), 4)) {
				break;
			}
		}
		rejectCommentsTab.scrollIntoView();
		rejectCommentsTab.click();
		return this;
	}


}