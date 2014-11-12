package pages.HQ.Manage;

import objects.Button;
import objects.TextBox;
import selenium.CommonUtils;

public class UnsubscribeSettingsPage extends ManagePage {

	TextBox introductoryTextField = new TextBox("//textarea[@id='emailUnsubscribeText']", "Introductory Text ", false);
	Button unsubscribeLink = new Button("//a[contains(@href, 'unsubscribe')]", "Unsubscribe", true);
	Button saveButton = new Button("//button[@id='btnSave']", "Save", true);
	
	public UnsubscribeSettingsPage editIntroductionaryText(String text) {
		sleep(3000);
		introductoryTextField.type(text);
		saveButton.click();
		sleep(5000);		
		return this;
	}
	
	public UnsubscribePage openUnsubscribePage() {
		String currentWindowHandle = getWindowHandle();
		unsubscribeLink.click();			
		switchToPopupWindow(currentWindowHandle);
		CommonUtils.setProperty("currentWindowHandle", currentWindowHandle);		
		return new UnsubscribePage();
	}
}
