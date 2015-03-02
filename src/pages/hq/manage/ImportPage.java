package pages.hq.manage;

import elements.Button;
import elements.Table;
import elements.impl.ButtonImpl;
import elements.impl.TableImpl;

public class ImportPage extends ManagePage{

	Button addImportButton = new ButtonImpl("//button[@ng-click='addImport()']", "Create Your Import");
	Table tableWithImports = new TableImpl("//table[contains(@id, 'JColResizer')]", "Table with imports");
	
	public ImportAddPage startNewImport() {
		addImportButton.click();
		return new ImportAddPage();
	}

	public ImportPage verifyStatusOfImport(String importName, String importStatus){
		
		verifier.verifyEquals(tableWithImports.getCellValue(1, "Name"), importName, "Row of import is not found or not first");
		for (int i = 0; i < 10; i++) {
			waitConditionBecomesTrueWithRefersh(tableWithImports.getCellValue(1, "Status").equalsIgnoreCase(importStatus), 30000);
		}
		verifier.verifyEquals(tableWithImports.getCellValue(1, "Status"), importStatus, "Wrong import status");
		return this;		
	}
}
