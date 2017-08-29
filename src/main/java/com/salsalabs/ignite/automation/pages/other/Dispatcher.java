package com.salsalabs.ignite.automation.pages.other;

import com.salsalabs.ignite.automation.common.Browser;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.elements.SelectBox;
import com.salsalabs.ignite.automation.elements.Table;
import com.salsalabs.ignite.automation.elements.impl.SelectBoxImpl;
import com.salsalabs.ignite.automation.elements.impl.TableImpl;

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
		Integer colN = logsTable.getColumnNumberByHeaderUsingGetAllHeadersMethod(columnName);
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
