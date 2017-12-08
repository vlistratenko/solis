package com.salsalabs.ignite.automation.elements.impl;

import com.salsalabs.ignite.automation.elements.SelectBox;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class SelectBoxImpl extends TextBoxImpl implements SelectBox {

	public SelectBoxImpl(String elementPath, String elementName) {
		super(elementPath, elementName);
	}

	@Override
	public String getText() {
		logger.info("Get text from " + elementName);
		return super.getText(path);
	}

	@Override
	public void type(String value) {
		logger.info("Type " + value + " in to the " + elementName);
		super.type(path, value);
	}

	@Override
	public void clear() {
		logger.info("Clear the " + elementName);
		super.clearTextField(path);
	}

	@Override
	public void selectByIndex(int index) {
		logger.info("Select by index " + index + " in the " + elementName);
		super.select(path, index);
	}
	
	public void selectByValue(String value) {
		logger.info("Select by value " + value + " in the " + elementName);
		if (value.length()>1) {			
			new Select(super.findElementByXpath(path)).selectByValue(value);
		}
		
	}

	@Override
	public void selectByLabel(String value) {
		logger.info("Select value by label " + value + " in the " + elementName);
		if (value.length()>0) {
			super.select(path, value);	
		}
		
	}
	
	protected void selectAndWait(String value) throws InterruptedException{
		logger.info("Select value by label " + value + " in the " + elementName + " and wait");
		super.selectAndWait(path, value);
	}
	
	protected String getSelectedValue(String locator){		
		logger.info("Get selected value from the " + elementName);
		String selected = super.getSelectedValue(path);
		logger.info("Value is " + selected);
		return selected;
	}
	
	public String getSelectedLabel(String locator){
		logger.info("Get selected label from the " + elementName);
		String selected = super.getSelectedLabel(path);
		logger.info("label is " + selected);		
		return selected;
	}
	
	protected List<WebElement> getSelectOptions(String locator){
		logger.info("Get list of select options from the " + elementName);
		return super.getSelectOptions(path);
	}
	
	public String[] getListOfOptions() {
		List<WebElement> elem = getSelectOptions(path);
		String[] items = new String[elem.size()];
		for (int i = 0; i < items.length; i++) {
			items[i]=elem.get(i).getText();
			
		}
		return items;
	}
}
