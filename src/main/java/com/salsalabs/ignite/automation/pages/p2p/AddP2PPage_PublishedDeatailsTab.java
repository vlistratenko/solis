package com.salsalabs.ignite.automation.pages.p2p;

import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.PropertyName;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;

public class AddP2PPage_PublishedDeatailsTab extends AddP2PPage {

	private Button p2pEventLink = new ButtonImpl("//a[contains(@href, 'formNameForReplacement')]", "p2p link");
	
	public void clickOnEventLink(String eventName) {
		p2pEventLink.changePath("formNameForReplacement", eventName.replaceAll(" ", ""));
		p2pEventLink.waitElement();
		p2pEventLink.click();
	}
	
	public void storeEventLink(String eventName) {
		p2pEventLink.changePath("formNameForReplacement", eventName.replaceAll(" ", ""));
		p2pEventLink.waitElement();
		CommonUtils.setParam(PropertyName.P2P_FORM_LINK, p2pEventLink.getAttribute("href"));
	}

	public void openWidget(String eventName) {
		clickOnEventLink(eventName);
		storeEventLink(eventName);
		String currentWindowHandle = getWindowHandle();
		switchToPopupWindow(currentWindowHandle);
	}

	public void openWidgetAndWait(String eventName, int waitTimeInSeconds) {
		clickOnEventLink(eventName);
		storeEventLink(eventName);
		String currentWindowHandle = getWindowHandle();
		switchToPopupWindow(currentWindowHandle);
		sleep(waitTimeInSeconds);
	}
}
