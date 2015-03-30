package com.salsalabs.ignite.automation.pages.zendesk;

import com.salsalabs.ignite.automation.common.Browser;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;

public class ZendeskSubmitRequestPage extends Browser {

	TextBox subject = new TextBoxImpl("//input[@id='request_subject']", "Subject");
	TextBox description = new TextBoxImpl("//textarea[@id='request_description']", "Description");
	Button submit = new ButtonImpl("//input[@name='commit']", "Submit");

	public ZendeskSubmitRequestPage checkSubmitFormIsDisplayed() {
		verifier.verifyElementIsDisplayed(subject, description, submit);
		return this;
	}

}
