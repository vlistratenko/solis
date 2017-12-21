package com.salsalabs.ignite.automation.elements.impl;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.DropDown;

public class DropDownImpl extends ElementImpl implements DropDown {

	String extendButtonPath;

	public DropDownImpl(String elementPath, String extendButtonPath, String elementName) {
		super(elementPath, elementName);
		if (!extendButtonPath.contains("//")) {
			this.extendButtonPath = elementPath + "/descendant::" + extendButtonPath;
		} else {
			this.extendButtonPath = extendButtonPath;
		}

	}

	public DropDownImpl(String elementPath, String elementName) {
		super(elementPath, elementName);

		this.extendButtonPath = elementPath;

	}

	private Button getChildItemByLabel(String label) {
		logger.info("Get item with label " + label);
		;
		return new ButtonImpl(path + "/descendant::*[normalize-space(text())='" + label + "']", label + " item");
	}
	
	private Button getChildItemByLabelUsingContainsMEthod(String label) {
		logger.info("Get item with label " + label);
		return new ButtonImpl(path + "/descendant::*[contains(text(),'" + label + "')]", label + " item");
	}

	@Override
	public void selectByLabel(String value) {
		logger.info("Select value by label " + value + " in the " + elementName);
		WebElement el = findElementByXpath(extendButtonPath);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", el);
		//scrollIntoView();
		click(extendButtonPath);
		this.getChildItemByLabel(value).scrollIntoView();
		this.getChildItemByLabel(value).click();

	}
	@Override
	public void selectByLabelJSUsingContains(String value) {
		logger.info("Select value by label " + value + " in the " + elementName);
		WebElement el = findElementByXpath(extendButtonPath);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", el);
		//scrollIntoView();
		click(extendButtonPath);
		this.getChildItemByLabelUsingContainsMEthod(value).scrollIntoView();
		this.getChildItemByLabelUsingContainsMEthod(value).click();
	}
	

	@Override
	public void selectByLabelJS(String value) {
		logger.info("Select value by label " + value + " in the " + elementName);
		findElementByXpath(extendButtonPath);
		clickJS(extendButtonPath);
		this.getChildItemByLabel(value).clickJS();

	}

	@Override
	public void selectByID(String id) {
		logger.info("Select value by id " + id + " in the " + elementName);
		sendKeys(extendButtonPath, Keys.TAB);
		waitObject(path + "[" + id + "]", 10 * cTimeOut);
		onClick(findElementByXpath(path + "[" + id + "]"));
	}

	protected void selectAndWait(String value) throws InterruptedException {
		logger.info("Select value by label " + value + " in the " + elementName + " and wait");
		super.selectAndWait(path, value);
	}
	
	public void waitForElementPresence() {		
		super.waitObject(extendButtonPath, 30000);
	}

	
}
