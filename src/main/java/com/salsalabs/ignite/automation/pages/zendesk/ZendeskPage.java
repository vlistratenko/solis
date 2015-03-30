package com.salsalabs.ignite.automation.pages.zendesk;

import java.util.Set;

import com.salsalabs.ignite.automation.common.Browser;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.pages.hq.HomePage;

public class ZendeskPage extends Browser {

	Button sendRequest = new ButtonImpl("//a[@class='submit-a-request']", "Submit request");

	public ZendeskSubmitRequestPage clickSubmitRequest() {
		sendRequest.click();
		return new ZendeskSubmitRequestPage();
	}

	public HomePage returnToHomePage() {
		Set<String> windows = SeleneseTestCase.driver.getWindowHandles();
		verifier.verifyTrue(windows.size() > 0, "Home window was closed");
		String current = SeleneseTestCase.driver.getWindowHandle();
		for (String w : windows) {
			if (!w.equals(current)) {
				SeleneseTestCase.driver.close();
				SeleneseTestCase.driver.switchTo().window(w);
				verifier.verifyTrue(getLocation().contains("dashboard"), "Wrong url " + getLocation());
			}
		}
		return new HomePage();
	}

}
