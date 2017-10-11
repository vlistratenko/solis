package com.salsalabs.ignite.automation.pages.hq.manage;

import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.PropertyName;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;

public class UnsubscribeSettingsPage extends ManagePage {

	TextBox introductoryTextField = new TextBoxImpl("//textarea[@id='emailUnsubscribeText']", "Introductory Text ", false);
	Button unsubscribeLink = new ButtonImpl("//a[contains(@href, 'manage-your-subscription')]", "Unsubscribe", true);
	Button saveButton = new ButtonImpl("//button[@id='btnSave']", "Save", true);

	public UnsubscribeSettingsPage editIntroductionaryText(String text) {
		sleep(3);
		introductoryTextField.type(text);
		saveButton.click();
		sleep(5);
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
