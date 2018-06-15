package com.salsalabs.ignite.automation.pages.hq.transactions;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.pages.hq.HomePage;

public class TransactionsPage extends HomePage {
    Button exportsTab = new ButtonImpl("//a[@href='/#/insight/donations/exports?type=DONATION']", "Manage exports");
    Button createExport = new ButtonImpl(".//*[@ng-click='addExport()']", "Create export");

    public TransactionsPage goToExportsTab(){
        exportsTab.click();
        return this;
    }

    public TransactionsExportPage createNewTransactionExport(){
        createExport.click();
        return new TransactionsExportPage();
    }
}
