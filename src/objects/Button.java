package objects;

import java.util.List;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebElement;

import selenium.SeleneseTestCase;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;

import interfaces.iButton;


public class Button extends Element implements iButton {
	
	
	public Button(String elementPath, String elementName) {
		super(elementPath, elementName);
	}

	public Button setLastElementPath() {
		logger.info(elementName + " was clicked.");
		path = path+ "[" + findElementsByXpath(path).size() + "]";
		path = path.replace("[0]", "");
		path = path.replace("][", "  and ");
		return this;
	}
	
	public WebElement getLastElement() {
		List<WebElement> ss =  findElementsByXpath(path);
		int s = ss.size();
		return ss.get(s-1);
	}
	
	@Override
	public void click() {
		SeleneseTestCase.bug.add("Click on " + elementName);
		super.click(path);
		logger.info(elementName + " was clicked.");
		
	}
	
	public void clickByN(Integer number) {
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
	public void highlight() {
		logger.info(elementName + " was highlighted.");
		super.highlight(path);
		
	}
	

	@Override
	public boolean isVisible() {
		logger.info("Check is " + elementName + " visible.");
		return super.isVisible(path);
	}

	@Override
	public boolean isEnabled() {
		return super.isEnabled(path);
	}

	@Override
	public boolean isDisplayed() {
		return super.isDisplayed(path);
	}
	
	public String getAttribute(String attrName) {
		return super.getAttributeValue(path, attrName);
	}
	
	@Override
	public boolean isNotDisplayed() {
		super.isNotDisplayed(path);
		return false;
	}

}
