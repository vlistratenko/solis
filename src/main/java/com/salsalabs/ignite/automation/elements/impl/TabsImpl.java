package com.salsalabs.ignite.automation.elements.impl;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.Tabs;

public class TabsImpl extends ElementImpl implements Tabs {

	public TabsImpl(String elementPath, String name) {
		super(elementPath, name);
	}

	@Override
	public String getSelectedTabName() {
		logger.info("Get selected tab in the " + elementName);
		return new ButtonImpl(path + "/descendant::*[contains(@class, 'selected')]", " tab ").getLabel();
	}

	@Override
	public void selectTab(String tabName) {
		if (!getSelectedTabName().equalsIgnoreCase(tabName)) {
			logger.info("Select tab by label " + tabName + " in the " + elementName);
			WebElement el = findElementByXpath(path);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", el);
			this.getChildItemByLabel(tabName).click();
		}
	}
	
	private Button getChildItemByLabel(String label) {
		logger.info("Get item with label " + label);
		return new ButtonImpl(path + "/descendant::*[text()='" + label + "']", label + " item");

	}
}
