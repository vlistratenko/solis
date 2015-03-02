package elements.impl;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import core.util.SeleneseTestCase;
import elements.Button;


public class ButtonImpl extends ElementImpl implements Button {
	
	
	public ButtonImpl(String elementPath, String elementName) {
		super(elementPath, elementName);
	}
	
	public ButtonImpl(String elementPath, String elementName, boolean isRequired) {
		super(elementPath, elementName, isRequired);
	}

	public ButtonImpl setLastElementPath() {
		logger.info(elementName + " was clicked.");
		path = path+ "[" + findElementsByXpath(path).size() + "]";
		path = path.replace("[0]", "");
		path = path.replace("][", "  and ");
		return this;
	}
	
	@Override
	public WebElement getLastElement() {
		List<WebElement> ss =  findElementsByXpath(path);
		int s = ss.size();
		return ss.get(s-1);
	}
	
	@Override
	public void onClick() {
		SeleneseTestCase.bug.add("Click on " + elementName);
		super.onClick(findElementByXpath(path));
		logger.info(elementName + " was clicked.");
	}
		
	@Override
	public void moveAndClick() {
		new Actions(driver).moveToElement(findElementByXpath(path)).perform();
		click();
		
	}
	
	@Override
	public void clickByNumber(Integer number) {
		logger.info(elementName + " was clicked.");
		super.click(path + "[" + number + "]");
		
	}

	@Override
	public void submit() {
		logger.info(elementName + " was submitted.");
		super.submit(path);
	}

	@Override
	public String getLabel() {
		logger.info("Label for " + elementName + " was returned.");
		return super.getText(path);
	}
	
	@Override
	public String getAttribute(String attrName) {
		return super.getAttributeValue(path, attrName);
	}
	
	@Override
	public boolean isNotDisplayed() {
		logger.info("Check that " + elementName + " is not displayed.");
		return super.isNotDisplayed(path);
	}
	
	@Override
	public boolean isNotExists() {
		logger.info("Check that " + elementName + " is not exists.");
		return super.isNotElementPresent(path);
	}

}
