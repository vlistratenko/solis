package pages.other;

import core.util.Browser;
import core.util.SeleneseTestCase;
import elements.SelectBox;
import elements.Table;
import elements.impl.SelectBoxImpl;
import elements.impl.TableImpl;

public class Dispatcher extends Browser{

	Table logsTable = new TableImpl("//div[@id='mainform:jobtickets']/descendant::table", "Table with job log");
	SelectBox selectJobSelectBox = new SelectBoxImpl("//a[@id='mainform:refreshButton']/preceding::select", "Select job");
	
	public Dispatcher openDispatcher() {
		open(SeleneseTestCase.USED_ENVIRONMENT.getBaseTestUrl().replace("hq", "dispatcher"));
		return this;
	}
	
	public Dispatcher selectJob(String jobLabel) {
		selectJobSelectBox.selectByLabel(jobLabel);
		sleep(10);
		return this;
	}
	
	public String getValueFromLogs(String columnName, String textForGettingRowNumber) {
		Integer romN = logsTable.getRowsNumberByValue(textForGettingRowNumber);
		Integer colN = logsTable.getColumnNumberByHeader(columnName);
		return logsTable.getCellValue(romN, colN);		
	}
	
	public Dispatcher verifyValue(String columnName, String textForGettingRowNumber, Object expectedValue, String message, boolean doFail) {
		
		verifier.verifyEquals(getValueFromLogs(columnName, textForGettingRowNumber),
				expectedValue,
				message, 
				doFail);
		return this;
	}
	
	
}
