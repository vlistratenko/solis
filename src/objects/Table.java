package objects;

import java.util.ArrayList;
import java.util.Iterator;
import org.openqa.selenium.WebElement;

import interfaces.iTable;

public class Table extends Element implements iTable {

	public Table(String elementPath, String elementName) {
		super(elementPath, elementName);
	}
	
	public void clickInCell(int row, int col) {
		clickInCell(row, col, "input");
	}
	
	public void clickInCell(int row, int col, String tag) {
		logger.info(elementName + " was clicked in cell, row " + row + " and column " +  col);
		String pathEl = path + "/tbody/descendant::tr[" + row + "]/td[" + col + "]/" + tag;
		super.click(pathEl.replace("[0]", ""));
	}
	
	public void sendKeysInCell(int row, int col, String tag, String key) {
		logger.info(elementName + " was clicked in cell, row " + row + " and column " +  col);
		String pathEl = path + "/descendant::tr[" + row + "]/td[" + col + "]/" + tag;
		super.sendKeys(pathEl.replace("[0]", ""), key);
	}
	
	public void clickInCell(String rowHeader, String colHeader) {
		logger.info(elementName + " was clicked in cell, row " + rowHeader + " and column " +  colHeader);
		Integer rowNumber = getRowsNumberByValue(rowHeader);
		Integer colNumber = getColNumberByValue(colHeader);
		clickInCell(rowNumber, colNumber);
	}
	
	public String getCallValue(int row, int col) {
		return getText(path + "/tbody/tr[" + row + "]/td[" + col + "]");
	}
	
	public String getCallValue(int row, String headerName) {
		return getCallValue(row, getColumnNumberByHeader(headerName));
	}
	
	public Integer getColumnNumberByHeader(String header) {
		
		return this.getHeaders().indexOf(header)+1;		 
	}
	
	public Integer getRowsCount() {
		
		return findElementsByXpath(path + "/tbody/tr").size();		 
	}
	
	public Integer getColsCount() {
		
		return findElementsByXpath(path + "/tbody/tr[1]/td").size();		 
	}
	
	public Integer getColNumberByValue(String value) {		
		if (isValueExists(value) <= 0) {
			return -1;
		}
		Integer colsCount = getColsCount();
		for (int i = 1; i <= colsCount; i++) {
			if (findElementsByXpath(path + "/descendant::tr/th[" + i + "]/descendant-or-self::*[contains(text(), '" + value + "')]").size() > 0) {
				return i;
			}
		}
		return -1;
	}
		
	public Integer getRowsNumberByValue(String value) {		
		if (isValueExists(value) <= 0) {
			return -1;
		}
		Integer rowsCount = getRowsCount()+1;
		for (int i = 1; i <= rowsCount; i++) {
			String elementPath = path + "/tbody/descendant::tr[" + i + "]/descendant::*[contains(text(), '" + value + "')]";
			if (findElementsByXpathWithOutWait(elementPath.replace("[0]", "")).size() > 0) { 
				return i;
			}
		}
		return -1;
	}
	

	
	public String getPathToChildElement(Integer row, Integer col, String elementType) {
		
		return path + "/tbody/tr[" + row + "]/td[" + col + "]/descendant::" + elementType;		
	}

	/**
	 * If true, it searches <a> in the header tag
	 * @param isClickable
	 * @return
	 */
	public ArrayList<String> getHeaders() {

		Iterator<WebElement> columns = findElementsByXpath(path + "/thead/tr/th/descendant-or-self::a").iterator(); 
		if (!columns.hasNext()) {
			columns = findElementsByXpath(path + "/thead/tr/th/descendant-or-self::*").iterator(); 
		}
		
		ArrayList<String> headers = new ArrayList<String>();
		while(columns.hasNext()) { 
	        WebElement column = columns.next();
	        if (!column.getText().equals("") && !headers.contains(column.getText())) {
	        	headers.add(column.getText());
			}
		}
		return headers;
	}
	
	@Override
	public void highlight() {
		logger.info(elementName + " was highlighted.");
		super.highlight(path);
		
	}

	@Override
	public boolean isVisible() {
		logger.info("Check is " + elementName + " is visible.");
		return super.isVisible(path);
	}

	@Override
	public boolean isEnabled() {
		logger.info("Check is " + elementName + " is enabled.");
		return super.isEnabled(path);
	}

	@Override
	public boolean isDisplayed() {
		logger.info("Check is " + elementName + " is displayed.");
		super.highlight(path);
		return super.isDisplayed(path);
	}
	
	@Override
	public boolean isNotDisplayed() {
		logger.info("Check that " + elementName + " is not displayed.");
		return super.isNotDisplayed(path);
	}

}
