package com.salsalabs.ignite.automation.pages.hq.emails;

import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.PropertyName;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.DropDown;
import com.salsalabs.ignite.automation.elements.Label;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.DropDownImpl;
import com.salsalabs.ignite.automation.elements.impl.LabelImpl;

public class AddEmailsPage_PublishTab extends AddEmailsPage{

	Button SendNowButton = new ButtonImpl("//button[@id='btnPublish']", "Send Now button");
	DropDown TestPercentageList = new DropDownImpl("//custom-select2[@name='testPercentageList']", "//custom-select2[@name='testPercentageList']/descendant::a", "Test percentage list");
	Label amountOfEmails = new LabelImpl("//p[contains(text(), 'This email will be sent to ')]/strong", "Amount Of Emails");

	Label confirmationMessage = new LabelImpl("//p[contains(text(), 'Congratulations')]", "Confirmation that blast is published");
	public AddEmailsPage_PublishTab fillAllFieldsAndPublish(Integer percentageOfTestGroup, Integer splitsAmount) {

		selectTimeToSend("Send now");
		if (splitsAmount > 1) {
			TestPercentageList.selectByLabelJS(percentageOfTestGroup.toString() + "%");
		}
		SendNowButton.waitElement();
		SendNowButton.click();
		confirmationMessage.waitElement();
		return new AddEmailsPage_PublishTab();
	}
	
	/**
	 * Send Immediately or Schedule for a later date
	 * @param timeToSend
	 */
	public void selectTimeToSend(String timeToSend) {
		Button timeToSendButton = new ButtonImpl("//label[contains(text(), '" + timeToSend + "')]", "Select time to send");
		timeToSendButton.waitElement();
		timeToSendButton.scrollIntoView();
		timeToSendButton.click();
	}
	
	public AddEmailsPage_PublishTab verifyAmountOfEmailsForPublishing(String expectedAmount) {
		verifier.verifyEquals(CommonUtils.getProperty(PropertyName.AMOUNT_OF_PUBLISHED_EMAILS), expectedAmount, "Wrong amount of emails for publishing", true);
		return this;
	}
}
