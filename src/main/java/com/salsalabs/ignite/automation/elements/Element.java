package com.salsalabs.ignite.automation.elements;

import org.openqa.selenium.WebElement;


public interface Element {

	boolean isVisible();

	boolean isEnabled();

	boolean isDisplayed();

	boolean isNotDisplayed();

	void highlight();

	String getPath();

	void changePath(String old, String newPath);
	
	void changePathAndElementName(String old, String newPath, String elName);

	void scrollIntoView();
	
	void scrollIntoViewAndDown();

	void addPath(String pathToAdd);

	void setImplicity(int cTimeOut);

	void click();

	void clickJS();

	void clickByTABKey();
	
	void clickByENTERKey();

	boolean waitElement();
	
	boolean waitElementWithPageRefresh(int amountOfRefreshes);
	
	boolean waitElement(int seconds);
	
	void waitElementWithFail(int seconds) throws Exception;

	String getName();

	boolean isNotExists();

	void clickByNumber(Integer number);

	String getAttribute(String attrName);

	void moveAndClick();
	
	void dragAndDrop(Panel panel);

	WebElement getLastElement();

	String setAttribute(String attName, String attValue);

	void removeAttribute(String string);

	boolean waitForNotExists(Integer timeOut);
	
	boolean waitForNotVisible(Integer timeOut);

	void focus();

	java.util.List<WebElement> findElementsByXpath(String xpath);

	void fluentWaitForElementPresenceIgnoringExceptions();

	void fluentWaitForElementPresenceIgnoringExceptions(int waitingTime);

}
