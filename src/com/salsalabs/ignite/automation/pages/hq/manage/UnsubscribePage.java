package com.salsalabs.ignite.automation.pages.hq.manage;

import com.salsalabs.ignite.automation.common.Browser;
import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.PropertyName;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.Label;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.LabelImpl;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;

public class UnsubscribePage extends Browser{
	
	TextBox emailField = new TextBoxImpl("//input[@name='PersonContact@Email@Value']", "Email");
	Button unsubscribeButton = new ButtonImpl("//input[@value='Unsubscribe']", "Unsubscribe");
	Label unsubscribeIsSuccesLabel = new LabelImpl("//div[@class='result']", "Unsubscribe is success message");
	Label introductoryTextLabel = new LabelImpl("//form/p[2]", "Introductory text");
	
	public UnsubscribePage fillUnsubscribeForm(String personEmail) 
	{
		emailField.type(personEmail);
		sleep(2);
		return this;
	}
	
	public UnsubscribePage clickUnsubscribeButton() {
		unsubscribeButton.click();
		return this;
	}
	
	public UnsubscribePage verifyInroductoryText(String message) {
		verifier.verifyEquals(introductoryTextLabel.getText(), message, "Wrong Introductory message", false);
		return this;
	}
	
	public UnsubscribePage verifyUnsubscribeIsSuccesses() {
		for (int i = 0; i < 10; i++) {
			if (waitConditionBecomesTrue(unsubscribeIsSuccesLabel.isDisplayed(), 10)) {
				break;
			}
		}
		verifier.verifyEquals(unsubscribeIsSuccesLabel.isDisplayed(), true, "Message that unsubscibe success, is not displayed");
		sleep(30);
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
}
