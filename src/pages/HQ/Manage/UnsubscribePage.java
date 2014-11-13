package pages.HQ.Manage;

import objects.Browser;
import objects.Button;
import objects.Label;
import objects.TextBox;
import selenium.CommonUtils;

public class UnsubscribePage extends Browser{
	
	TextBox emailField = new TextBox("//input[@name='PersonContact@Email@Value']", "Email");
	Button unsubscribeButton = new Button("//input[@value='Unsubscribe']", "Unsubscribe");
	Label unsubscribeIsSuccesLabel = new Label("//div[@class='result']", "Unsubscribe is success message");
	Label introductoryTextLabel = new Label("//form/p", "Introductory text");
	
	public UnsubscribePage fillUnsubscribeForm(String personEmail) 
	{
		emailField.type(personEmail);
		sleep(2000);
		return this;
	}
	
	public UnsubscribePage clickUnsubscribeButton() {
		unsubscribeButton.click();
		return this;
	}
	
	public UnsubscribePage verifyInroductoryText(String message) {
		verify(introductoryTextLabel.getText(), message, "Wrong Introductory message", false);
		return this;
	}
	
	public UnsubscribePage verifyUnsubscribeIsSuccesses() {
		for (int i = 0; i < 10; i++) {
			if (waitConditionBecomesTrue(unsubscribeIsSuccesLabel.isDisplayed(), 10000)) {
				break;
			}
		}
		verify(unsubscribeIsSuccesLabel.isDisplayed(), true, "Message that unsubscibe success, is not displayed");
		sleep(30000);
		return this;
	}
	
	public UnsubscribeSettingsPage backToUnsubscribeSettingsPage() {
		closeWindow();
		switchToWindow(CommonUtils.getProperty("currentWindowHandle"));
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
