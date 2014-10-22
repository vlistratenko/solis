package pages.HQ.Manage;

import objects.Button;
import objects.Table;

public class ImportPage extends ManagePage{

	Button addImportButton = new Button("//button[@ng-click='addImport()']", "Create Your Import");
	Table tableWithImports = new Table("//table[contains(@id, 'JColResizer')]", "Table with imports");
	
	public ImportAddPage startNewImport() {
		addImportButton.click();
		return new ImportAddPage();
	}

	public ImportPage verifyStatusOfImport(String importName, String importStatus){
		
		verify(tableWithImports.getCallValue(1, "Name"), importName, "Row of import is not found or not first");
		verify(tableWithImports.getCallValue(1, "Status"), importStatus, "Wrong import status");
		return this;		
	}
}
