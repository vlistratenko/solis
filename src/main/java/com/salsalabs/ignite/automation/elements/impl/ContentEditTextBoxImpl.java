package com.salsalabs.ignite.automation.elements.impl;

import org.openqa.selenium.Keys;

public class ContentEditTextBoxImpl extends TextBoxImpl {

	String iframeXpath;

	public ContentEditTextBoxImpl(String iframePath, String elementPath, String elementName, boolean isReq) {
		super(elementPath, elementName, isReq);
		iframeXpath = iframePath;
	}

	public void type(String value) {
		if (!value.equalsIgnoreCase("")) {
			switchToFrame(findElementByXpath(iframeXpath));
			sendKeys(path, Keys.TAB);
			type(path, value);
			switchToDefaultContent();	
		}
	}
	
	public void sendENTERKey() {
		logger.info(" Click on " + elementName + " and press ENTER button afterwards ");
		switchToFrame(findElementByXpath(iframeXpath));
		waitElement(10);
		highlight(path);
		sendKeys(path, Keys.ARROW_LEFT);
		sendKeys(path, Keys.ENTER);
		sendKeys(path, Keys.ARROW_UP);
		switchToDefaultContent();
	}
	
	
	public void scrollFrameIntoViewAndDown() {
		new PanelImpl(iframeXpath, "iFrame with subjectfield").scrollIntoViewAndDown();
	}
	
	public void switchToFrame() {
		switchToFrame(findElementByXpath(iframeXpath));
	}
	
	public void switchToDefault() {
		switchToDefaultContent();
	}

}
