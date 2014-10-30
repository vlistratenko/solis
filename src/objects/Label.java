package objects;

import interfaces.iLabel;

public class Label extends Element implements iLabel {

	public Label(String elementPath, String elementName) {
		super(elementPath, elementName);
	}

	public Integer getElementsAmount() {
		return findElementsByXpath(path).size();
	}
	
	@Override
	public void click() {
		logger.info(elementName + " was clicked.");
		super.click(path);

	}

	@Override
	public String getText() {
		logger.info("Get text from " + elementName);
		return super.getText(path);
	}

	@Override
	public void highlight() {
		logger.info(elementName + " was highlighted.");
		super.highlight(path);
		
	}

	@Override
	public boolean isVisible() {
		logger.info("Check that " + elementName + " is visible.");
		return super.isVisible(path);
	}

	@Override
	public boolean isEnabled() {
		logger.info("Check that " + elementName + " is enabled.");
		return super.isEnabled(path);
	}

	@Override
	public boolean isDisplayed() {
		logger.info("Check that " + elementName + " is displayed.");
		return super.isDisplayed(path);
	}
	
	@Override
	public boolean isNotDisplayed() {
		logger.info("Check that " + elementName + " is not displayed.");
		return super.isNotDisplayed(path);
	}
}
