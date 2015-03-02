package pages.hq.manage;

import core.util.CommonUtils;
import core.util.PropertyName;
import elements.Button;
import elements.TextBox;
import elements.impl.ButtonImpl;
import elements.impl.TextBoxImpl;

public class UnsubscribeSettingsPage extends ManagePage {

	TextBox introductoryTextField = new TextBoxImpl("//textarea[@id='emailUnsubscribeText']", "Introductory Text ", false);
	Button unsubscribeLink = new ButtonImpl("//a[contains(@href, 'unsubscribe')]", "Unsubscribe", true);
	Button saveButton = new ButtonImpl("//button[@id='btnSave']", "Save", true);
	
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
		CommonUtils.setProperty(PropertyName.CURRENT_WINDOW_HANDLE, currentWindowHandle);		
		return new UnsubscribePage();
	}
}
