package pages.hq.emails;

import core.util.CommonUtils;
import core.util.PropertyName;
import elements.Button;
import elements.DropDown;
import elements.Label;
import elements.impl.ButtonImpl;
import elements.impl.DropDownImpl;
import elements.impl.LabelImpl;

public class AddEmailsPage_PublishTab extends AddEmailsPage{

	Button SendNowButton = new ButtonImpl("//button[@id='btnPublish']", "Send Now button");
	DropDown TestPercentageList = new DropDownImpl("//custom-select2[@name='testPercentageList']", "//custom-select2[@name='testPercentageList']/descendant::a", "Test percentage list");
	Label amountOfEmails = new LabelImpl("//p[contains(text(), 'This email will be sent to ')]/strong", "Amount Of Emails");
	public AddEmailsPage_PublishTab fillAllFieldsAndPublish(Integer percentageOfTestGroup, Integer splitsAmount) {
		selectTimeToSend("Immediately");
		if (splitsAmount > 1) {
			TestPercentageList.selectByLabelJS(percentageOfTestGroup.toString() + "%");
		}
		sleep(5);
		CommonUtils.setProperty(PropertyName.AMOUNT_OF_PUBLISHED_EMAILS, amountOfEmails.getText());
		SendNowButton.click();
		sleep(3);
		return new AddEmailsPage_PublishTab();
	}
	
	/**
	 * Send Immediately or Schedule for a later date
	 * @param timeToSend
	 */
	public void selectTimeToSend(String timeToSend) {
		new ButtonImpl("//label[contains(text(), '" + timeToSend + "')]", "Select time to send").click();
	}
	
	public AddEmailsPage_PublishTab verifyAmountOfEmailsForPublishing(String expectedAmount) {
		verifier.verifyEquals(CommonUtils.getProperty(PropertyName.AMOUNT_OF_PUBLISHED_EMAILS), expectedAmount, "Wrong amount of emails for publishing", true);
		return this;
	}
}
