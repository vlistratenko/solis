package com.salsalabs.ignite.automation.pages.hq.transactions;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;

public class TransactionsExportPage extends TransactionsPage {
    TextBox exportName = new TextBoxImpl(".//*[@name='name']", "Export name");
    TextBox exportDescription = new TextBoxImpl(".//*[@name='description']", "Export description");
    Button toExportRulesButton = new ButtonImpl(".//button[@id='btnCompose']", "Next: Export Rules »");
    Button runExportButton = new ButtonImpl(".//button[@id='btnResults']", "Run Export Now!");
    Button downloadExportResultsButton = new ButtonImpl(".//a[@class='circle-button primary' and @title='Download Export File']", "Download export results");

    public TransactionsExportPage fillTitleDescriptionAndProceed(String title, String description){
        exportName.fluentWaitForElementPresenceIgnoringExceptions();
        exportName.type(title);
        exportDescription.type(description);
        toExportRulesButton.click();
        return this;
    }

    public TransactionsExportPage runExportNow(){
        runExportButton.fluentWaitForElementPresenceIgnoringExceptions();
        runExportButton.click();
        return this;
    }

    public TransactionsExportPage downloadExportResults(){
        downloadExportResultsButton.fluentWaitForElementPresenceIgnoringExceptions();
        downloadExportResultsButton.click();
        return this;
    }
}
