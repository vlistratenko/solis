package com.salsalabs.ignite.automation.pages.hq.emails;

import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.PropertyName;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.Label;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.DropDownImpl;
import com.salsalabs.ignite.automation.elements.impl.LabelImpl;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;


public class AddEmailsPage_ChooseAudienceTab extends AddEmailsPage{
	
	
	
	Button EntireList = new ButtonImpl("//span[contains(@ng-class, 'sendToAllSelected')]", "Entire list");
	Button SelectedSegmentsOrSupporters  = new ButtonImpl("//span[contains(@ng-class, '!blast.sendToAllSelected')]", " Selected segments of your list, or specific supporters");
	Button ComposeButton = new ButtonImpl("//button[@id='btnCompose']", "Next: Compose Your Email");
	TextBox addSupportersField = new TextBoxImpl("//p[contains(text(), 'Want to add')]/following-sibling::div/descendant::input", "Manually add supporters");
	DropDownImpl addSegmentField = new DropDownImpl("//p[contains(text(), 'Find groups and add them here!')]/following-sibling::div/descendant::a", "Add segment");
	Button suppirtersItemInTheSearchButton = new ButtonImpl("(//tr[@class='result fade-out'])[last()]", "Supporters item in the search result", false);
	Button calculateAudience = new ButtonImpl("//span[contains(text(),'Calculate')]", "Calculate Audience Reach");
	Label calculatedLabel = new LabelImpl("//span[@ng-show='blast.reachTotal>=0']", "Calculated Audience");
	
	public AddEmailsPage_ChooseAudienceTab selectAudienceType(String type) {
		if (type.trim().equalsIgnoreCase("Entire list")) {
			EntireList.click();
		}
		if (type.trim().equalsIgnoreCase("Selected segments of your list, or specific supporters")) {
			SelectedSegmentsOrSupporters.click();
		}
		return this;
	}
	
	public AddEmailsPage_ChooseAudienceTab addSupporters(Integer amount, String propertyName) {
		return addSupporters(SeleneseTestCase.emailClient.getEmailBox(""), amount, propertyName);
	}
	
	public AddEmailsPage_ChooseAudienceTab addSupporters(String searchString, Integer amount, String propertyName) {
		if (amount==0) {
			return this;
		}
		sleep(5);
		addSupportersField.scrollIntoView();
		
		int delta = 0, calculated = 0, num = amount;
		if (calculateAudience.isVisible()) {
			calculateAudience.scrollIntoView();
			calculateAudience.click();
			sleep(2);
		}
		delta = Integer.parseInt(calculatedLabel.getText().replace(" supporter(s)", ""));
		do {
			addSupportersField.type(searchString);
			sleep(5);
			if (suppirtersItemInTheSearchButton.isNotDisplayed()) {
				break;
			}
			for (int i = 0; i < num; i++) {
				suppirtersItemInTheSearchButton.click();
				sleep(1);
			}
			calculateAudience.scrollIntoView();
            calculateAudience.clickJS();
			sleep(2);
			calculated = Integer.parseInt(calculatedLabel.getText().replace(" supporter(s)", "")) - delta;
			num = amount - calculated;
		} while (num > 0);
		CommonUtils.setProperty(propertyName, new Integer(calculated + delta).toString());
		return this;
	}

	
	public AddEmailsPage_ComposeTab openComposePage() {
		ComposeButton.click();
		return new AddEmailsPage_ComposeTab();
	}
	
	public AddEmailsPage_ChooseAudienceTab addSegment(String searchString) {

		addSegmentField.scrollIntoView();
		addSegmentField.click();
		sleep(3);
		LabelImpl segmentItem = new LabelImpl("//table/descendant::td[contains(text(), '" + searchString + "')]", "Segment " + searchString + " item");
		while (segmentItem.isNotExists()) {
			new ButtonImpl("//a[contains(text(),'Load next')]", "Load next segments");
			
		}
		segmentItem.click();
		calculateAudience.waitElement();
		if (calculateAudience.isVisible()) {
            calculateAudience.clickJS();
			sleep(2);
		}
		
		CommonUtils.setProperty(PropertyName.AMOUNT_OF_PUBLISHED_EMAILS, calculatedLabel.getText().replace(" supporter(s)", ""));
		return this;
	}
	
}
