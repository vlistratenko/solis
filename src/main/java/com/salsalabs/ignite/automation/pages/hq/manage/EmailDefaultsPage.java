package com.salsalabs.ignite.automation.pages.hq.manage;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;

public class EmailDefaultsPage extends ManagePage {

    TextBox fromName = new TextBoxImpl(".//*[@name='emailDefaultFromName']", "From name");
    TextBox fromEmailAddress = new TextBoxImpl(".//*[@name='emailDefaultFromAddress']", "From email address");
    TextBox replyToEmailAddress = new TextBoxImpl(".//*[@name='emailDefaultReplyto']", "Reply to email address");
    Button saveButton = new ButtonImpl(".//*[@id='btnSave']", "Save button");

    public EmailDefaultsPage save() {
        saveButton.click();
        return this;
    }

    public EmailDefaultsPage changeEmailDefaults(String fromName, String fromEmailAddress, String replyToEmailAddress) {
        sleep(2);
        this.fromName.type(fromName);
        this.fromEmailAddress.type(fromEmailAddress);
        this.replyToEmailAddress.type(replyToEmailAddress);
        save();
        return this;
    }
}
