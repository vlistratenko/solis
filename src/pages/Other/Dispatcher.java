package pages.Other;

import objects.Browser;
import objects.SelectBox;
import objects.Table;
import selenium.SeleneseTestCase;

public class Dispatcher extends Browser{

	Table logsTable = new Table("//div[@id='mainform:jobtickets']/descendant::table", "Table with job log");
	SelectBox selectJobSelectBox = new SelectBox("//a[@id='mainform:refreshButton']/preceding::select", "Select job");
	
	public Dispatcher openDispatcher() {
		open(SeleneseTestCase.USED_ENVIRONMENT.getBaseTestUrl().replace("hq", "dispatcher"));
		return this;
	}
	
	public Dispatcher selectJob(String jobLabel) {
		selectJobSelectBox.selectByLabel(jobLabel);
		sleep(10000);
		return this;
	}
	
	public String getValueFromLogs(String columnName, String textForGettingRowNumber) {
		Integer romN = logsTable.getRowsNumberByValue(textForGettingRowNumber);
		Integer colN = logsTable.getColumnNumberByHeader(columnName);
		return logsTable.getCallValue(romN, colN);		
	}
	
	public Dispatcher verifyValue(String columnName, String textForGettingRowNumber, Object expectedValue, String message, Boolean doFail) {
		
		verify(getValueFromLogs(columnName, textForGettingRowNumber),
				expectedValue,
				message, 
				doFail);
		return this;
	}
	
	
}
