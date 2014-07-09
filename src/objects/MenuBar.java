package objects;

import selenium.SeleneseTestCase;
import interfaces.iDropDown;


public class MenuBar extends Element implements iDropDown {
	
	
	public MenuBar(String elementPath, String elementName) {
		super(elementPath, elementName);
	}

	private Button getChildItemByLabel(String label) {
		logger.info("Get item with label " + label);
		return new Button(path + "/descendant::*[text()='" + label + "']", label + " item");

	}

	@Override
	public void selectByLabel(String value) {
		logger.info("Select value by label " + value + " in the " + elementName);
		SeleneseTestCase.bug.add("Select value by label " + value + " in the " + elementName);
		this.getChildItemByLabel(value).click();
		
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
		super.isNotDisplayed(path);
		return false;
	}


}
