package objects;

import interfaces.iPanel;

public class Panel extends Element implements iPanel {

	public Panel(String elementPath, String elementName) {
		super(elementPath, elementName);
	}
	
	public Integer getElementsCount() {
		return findElementsByXpath(path).size();
	}
	
	public Panel(String elementPath, String elementName, int number) {
		super(elementPath, elementName);
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
		return super.isDisplayed(path);
	}
	
	@Override
	public boolean isNotDisplayed() {
		logger.info("Check is " + elementName + " is not displayed.");
		return super.isNotDisplayed(path);
	}
	
	public String getChildElementPath(String elementPath, int setNumder) {
		return path + "[" + setNumder + "]/descendant::" + elementPath;
	}

}
