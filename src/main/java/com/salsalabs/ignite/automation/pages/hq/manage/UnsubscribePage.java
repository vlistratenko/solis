package com.salsalabs.ignite.automation.pages.hq.manage;

import com.salsalabs.ignite.automation.common.Browser;
import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.PropertyName;
import com.salsalabs.ignite.automation.elements.*;
import com.salsalabs.ignite.automation.elements.List;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.CheckBoxImpl;
import com.salsalabs.ignite.automation.elements.impl.LabelImpl;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;
import org.openqa.selenium.WebElement;

import java.util.*;

public class UnsubscribePage extends Browser{
	
	TextBox emailField = new TextBoxImpl("//input[@name='field-email-address']", "Email");
	Button unsubscribeButton = new ButtonImpl("//button[@type='submit']", "Update Subscription");
	Label unsubscribeIsSuccesLabel = new LabelImpl("//div[@class='result']", "Unsubscribe is success message");
	Label introductoryTextLabel = new LabelImpl("//form/p[2]", "Introductory text");
	CheckBox unsubscrCheckBox = new CheckBoxImpl("//div[contains(@class, 'contact-types')]/descendant::input", "Unsabscribe");
	CheckBox messageTopics = new CheckBoxImpl("//*[@class='sli-channel-type']", "Message topics");
	CheckBox messageTopicsInputs = new CheckBoxImpl("//*[@class='sli-channel-type']/input", "Message topics");

	public UnsubscribePage fillUnsubscribeForm(String personEmail) {
		emailField.type(personEmail);
		unsubscrCheckBox.click();
		sleep(2);
		return this;
	}

	public UnsubscribePage fillUnsubscribeEmail(String personEmail) {
		emailField.type(personEmail);
		return this;
	}
	
	public UnsubscribePage clickUnsubscribeButton() {
		unsubscribeButton.click();
		sleep(3);
		return this;
	}
	
	public UnsubscribePage verifyInroductoryText(String message) {
		verifier.verifyEquals(introductoryTextLabel.getText(), message, "Wrong Introductory message", false);
		return this;
	}
	
	public UnsubscribePage verifyUnsubscribeIsSuccesses() {
		for (int i = 0; i < 15; i++) {
			if (waitConditionBecomesTrue(unsubscribeIsSuccesLabel.isDisplayed(), 1)) {
				break;
			}
		}
		verifier.verifyEquals(unsubscribeIsSuccesLabel.isDisplayed(), true, "Message that unsubscibe success, is not displayed");
		sleep(10);
		return this;
	}
	
	public UnsubscribeSettingsPage backToUnsubscribeSettingsPage() {
		closeWindow();
		switchToWindow(CommonUtils.getProperty(PropertyName.CURRENT_WINDOW_HANDLE));
		return new UnsubscribeSettingsPage();
	}
	
	public UnsubscribePage refreshPage() {
		super.refresh();
		return this;
	}


	public UnsubscribePage clearCache() {
		super.deletecoockies();
		return this;
	}

	public UnsubscribePage pickTopic(String topic) {
		java.util.List<WebElement> elementsInputs = messageTopicsInputs.findElementsByXpath(messageTopicsInputs.getPath());
		java.util.List<WebElement> elementsLabels = messageTopics.findElementsByXpath(messageTopics.getPath());
		for (int i = 0; i < elementsInputs.size(); i++) {
			if (elementsLabels.get(i).getText().equals(topic)) {
				if (!elementsInputs.get(i).isSelected()) elementsInputs.get(i).click();
			} else {
				elementsInputs.get(i).click();
			}
		}
		return this;
	}

	public java.util.List<String> getListOfMessageTopics() {
		java.util.List<String> resultSet = new ArrayList();
		java.util.List<WebElement> elements = messageTopics.findElementsByXpath(messageTopics.getPath());
		for (WebElement element : elements) {
			resultSet.add(element.getText());
		}
		return resultSet;
	}

	public String getRandomMessageTopic() {
		java.util.List<String> topics = getListOfMessageTopics();
		return topics.get(new Random().nextInt(topics.size()));
	}
}
