package com.salsalabs.ignite.automation.pages.hq.manage;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.Label;
import com.salsalabs.ignite.automation.elements.Table;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.LabelImpl;
import com.salsalabs.ignite.automation.elements.impl.TableImpl;

public class ImportPage extends ManagePage{

	Button addImportButton = new ButtonImpl("//button[contains(text(), 'Import List')]", "Import List of Supporters BUtton");
	Table tableWithImports = new TableImpl("//table", "Table with imports");
	Button importsPage = new ButtonImpl("//a[@href='/#/audience/supporters/imports']", "Imports Page");
	
	
	
	public static final String IMPORT_STARTED = "Heads up! We started to import %s.";
	public static final String IMPORT_FINISHED = "Good news! We finished importing %s.";
	
	public ImportAddPage hitImportListOFSupportersButton() {
		addImportButton.click();
		return new ImportAddPage();
	}

	public ImportPage verifyStatusOfImport(String importName, String importStatus){
		waitConditionBecomesTrue(importsPage.isDisplayed(), 5);
		importsPage.scrollIntoView();
		importsPage.click();
		waitConditionBecomesTrue(tableWithImports.isDisplayed(), 5);
		Label name = new LabelImpl("//table[contains(@id,'JColResizer')]/tbody/tr[1]/td[2]/div/span/span/span", "Import Name");
		Label status = new LabelImpl("//table[contains(@id,'JColResizer')]/tbody/tr[1]/td[4]/div/span/span/span", "Import Status");
		verifier.verifyEquals(name.getText(), importName, "Row of import is not found or not first");
		verifier.verifyEquals(status.getText(), importStatus, "Wrong import status");
		return this;		
	}
	
	
}
