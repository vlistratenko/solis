package pages.HQ.Emails;

import objects.Button;
import objects.DropDown;
import objects.Label;
import selenium.CommonUtils;

public class AddEmailsPage_PublishTab extends AddEmailsPage{

	Button SendNowButton = new Button("//button[@id='btnPublish']", "Send Now button");
	DropDown TestPercentageList = new DropDown("//custom-select2[@name='testPercentageList']", "//custom-select2[@name='testPercentageList']/descendant::a", "Test percentage list");
	Label amountOfEmails = new Label("//p[contains(text(), 'This email will be sent to ')]/strong", "Amount Of Emails");
	public AddEmailsPage_PublishTab fillAllFieldsAndPublish(Integer percentageOfTestGroup, Integer splitsAmount) {
		selectTimeToSend("Immediately");
		if (splitsAmount > 1) {
			TestPercentageList.selectByLabelJS(percentageOfTestGroup.toString() + "%");
		}
		CommonUtils.setProperty("amountOfPablishedEmails", amountOfEmails.getText());
		SendNowButton.click();
		sleep(30000);
		return new AddEmailsPage_PublishTab();
	}
	
	/**
	 * Send Immediately or Schedule for a later date
	 * @param timeToSend
	 */
	public void selectTimeToSend(String timeToSend) {
		new Button("//label[contains(text(), '" + timeToSend + "')]", "Select time to send").click();
	}
}
