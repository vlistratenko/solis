package com.salsalabs.ignite.automation.pages.hq.manage;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.Table;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.TableImpl;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;

public class MessageTopicsPage extends ManagePage {

	TextBox messageTopicInput = new TextBoxImpl(".//input[@placeholder='Or create your own...']",
			"Message Topic input");
	Table table = new TableImpl(".//table[@class='no-table-styles normal-font-size']", "Message Topic Table");
	Button addButton = new ButtonImpl("//span[contains(text(), 'Add')]/ancestor:: button", "Add button");
	Table listOfTopics = new TableImpl("//table/tbody/tr/td[2]", "ListOfTopics");

	public MessageTopicsPage addCustomTopic(String topic) {

		waitConditionBecomesTrue(table.isDisplayed(), 3);
		waitConditionBecomesTrue(messageTopicInput.isDisplayed(), 2);
		sleep(3);
		messageTopicInput.scrollIntoView();
		if (messageTopicInput.waitElement(5)) {
			messageTopicInput.type(topic);
		}
		addButton.click();
		sleep(3);

		return new MessageTopicsPage();
	}

	public MessageTopicsPage verifyAddedTopicInTheTable(String expectedTopic) {

		List<WebElement> topics = listOfTopics.findElementsByXpath(listOfTopics.getPath());
		String actualTopic = topics.get(topics.size() -1).getText();
		verifier.verifyEquals(actualTopic, expectedTopic, "New added message topic  == Topic frpm the table");
		return new MessageTopicsPage();
	}
	
	public MessageTopicsPage deleteMessageTopic() {
		waitConditionBecomesTrue(table.isDisplayed(), 3);
		waitConditionBecomesTrue(messageTopicInput.isDisplayed(), 2);
		sleep(1);
		List<WebElement> topics = listOfTopics.findElementsByXpath(listOfTopics.getPath());
		int row = topics.size() ;
		Button icon  = new ButtonImpl(".//table[@class='no-table-styles normal-font-size']/tbody/descendant::tr["+ row +"]/td[1]/span" , "TrashIcon");
		icon.moveAndClick();
		sleep(2);
		return new MessageTopicsPage();
	}

}
