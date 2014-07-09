package objects;

import selenium.SeleneseTestCase;
import interfaces.iTextBox;


public class TextBox extends Element implements iTextBox {

	public TextBox(String elementPath, String elementName) {
		super(elementPath, elementName);
	}

	public TextBox(String elementPath, String elementName, Boolean isReq) {
		super(elementPath, elementName, isReq);
	}
	
	@Override
	public void click() {
		logger.info("Click on " + elementName);
		super.click(path);

	}

	@Override
	public String getText() {
		logger.info("Get text from " + elementName);
		return super.getText(path);
	}

	@Override
	public void type(String value) {
		logger.info("Type " + value + " in to the " + elementName);
		SeleneseTestCase.bug.add("Type " + value + " in to the " + elementName);
		super.type(path, value);
	}

	@Override
	public void clear() {
		logger.info("Clear the " + elementName);
		super.clearTextField(path);
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
		super.isNotDisplayed(path);
		return false;
	}

}
