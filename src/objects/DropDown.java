package objects;


import org.openqa.selenium.Keys;

import interfaces.iDropDown;


public class DropDown extends Element implements iDropDown {
	
	String extendButtonPath;
	
	public DropDown(String elementPath, String extendButtonPath, String elementName) {
		super(elementPath, elementName);
		if (!extendButtonPath.contains("//")) {
			this.extendButtonPath = elementPath + "/descendant::" + extendButtonPath;
		}else{
			this.extendButtonPath = extendButtonPath;
		}
		
	}

	private Button getChildItemByLabel(String label) {
		logger.info("Get item with label " + label);
		return new Button(path + "/descendant::*[text()='" + label + "']", label + " item");

	}

	@Override
	public void selectByLabel(String value) {
		logger.info("Select value by label " + value + " in the " + elementName);
		findElementByXpath(extendButtonPath);
		click(extendButtonPath);
		this.getChildItemByLabel(value).click();
		
	}
	
	public void selectByLabelJS(String value) {
		logger.info("Select value by label " + value + " in the " + elementName);
		findElementByXpath(extendButtonPath);
		clickByTABKey(extendButtonPath);
		this.getChildItemByLabel(value).onClick();
		
	}
	
	public void selectByID(String id) {
		logger.info("Select value by id " + id + " in the " + elementName);
		//sleep(3000);
		sendKeys(extendButtonPath, Keys.TAB);
		waitObject(path + "[" + id + "]", 10*cTimeOut);
		onClick(findElementByXpath(path + "[" + id + "]"));
		
	}
	
	protected void selectAndWait(String value) throws InterruptedException{
		logger.info("Select value by label " + value + " in the " + elementName + " and wait");
		super.selectAndWait(path, value);
	}

	@Override
	public void highlight() {
		logger.info("Highlight the " + elementName);
		super.highlight(path);
		
	}
	
	@Override
	public boolean isVisible() {
		logger.info("Check is the " + elementName + " visible");
		return super.isVisible(path);
	}

	@Override
	public boolean isEnabled() {
		logger.info("Check is the " + elementName + " enabled");
		return super.isEnabled(path);
	}

	@Override
	public boolean isDisplayed() {
		logger.info("Check is the " + elementName + " displayed");
		return super.isDisplayed(path);
	}

	@Override
	public boolean isNotDisplayed() {
		logger.info("Check that " + elementName + " is not displayed.");
		return super.isNotDisplayed(path);
	}


}
