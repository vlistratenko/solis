package objects;

import java.util.List;

import org.openqa.selenium.WebElement;

import interfaces.iSelectBox;

public class SelectBox extends Element implements iSelectBox {

	public SelectBox(String elementPath, String elementName) {
		super(elementPath, elementName);
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
		super.type(path, value);
	}

	@Override
	public void clear() {
		logger.info("Clear the " + elementName);
		super.clearTextField(path);
	}

	@Override
	public void selectByIndex(int index) {
		logger.info("Select value by index" + index + " in the " + elementName);
		super.select(path, index);
	}

	@Override
	public void selectByLabel(String value) {
		logger.info("Select value by label " + value + " in the " + elementName);
		super.select(path, value);
		
	}
	
	protected void selectAndWait(String value) throws InterruptedException{
		logger.info("Select value by label " + value + " in the " + elementName + " and wait");
		super.selectAndWait(path, value);
	}
	
	protected String getSelectedValue(String locator){		
		logger.info("Get selected value from the " + elementName);
		String selected = super.getSelectedValue(path);
		logger.info("Value is " + selected);
		return selected;
	}
	
	protected String getSelectedLabel(String locator){		
		logger.info("Get selected label from the " + elementName);
		String selected = super.getSelectedLabel(path);
		logger.info("label is " + selected);		
		return selected;
	}
	
	protected List<WebElement> getSelectOptions(String locator){
		logger.info("Get list of select options from the " + elementName);
		return super.getSelectOptions(path);
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
