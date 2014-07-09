package pages.HQ.Emails;

import objects.Button;
import objects.DropDown;

public class AddEmailsPage_PublishTab extends AddEmailsPage{

	Button SendNowButton = new Button("//button[@id='btnPublish']", "Send Now button");
	DropDown TestPercentageList = new DropDown("//custom-select2[@name='testPercentageList']", "//custom-select2[@name='testPercentageList']/descendant::a", "Test percentage list");
	
	public AddEmailsPage_PublishTab fillAllFieldsAndPublish(Integer percentageOfTestGroup, Integer splitsAmount) {
		selectTimeToSend("Immediately");
		if (splitsAmount > 1) {
			TestPercentageList.selectByLabelJS(percentageOfTestGroup.toString() + "%");
		}
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
