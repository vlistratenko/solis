package com.salsalabs.ignite.automation.pages.hq.supporters;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.pages.hq.AudiencePage;

public class SupporterQBPage extends AudiencePage {

    Button createNewQueryButton = new ButtonImpl("//*[contains(text(),'Create New Query')]", "Query tab");

    public SupporterQBPage createNewQuery() {
        createNewQueryButton.click();
        return new CreateEditSupporterQuery();
    }

}
