package objects;

import java.io.File;

import org.openqa.selenium.interactions.Actions;

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
	
	public void clickByTABKey() {
		logger.info("Click on " + elementName);
		super.clickByTABKey(path);

	}
	
	public void focus() {
		logger.info("Focus on " + elementName);
		new Actions(driver).click(findElementByXpath(path)).build().perform();

	}
	
	public void waitElement() {
		super.waitObject(path, 30000);

	}

	@Override
	public String getText() {
		logger.info("Get text from " + elementName);
		return super.getText(path);
	}
	
	public String getValue() {
		logger.info("Get value from " + elementName);
		return super.getValue(path);
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
		logger.info("Check that " + elementName + " is not displayed.");
		return super.isNotDisplayed(path);
	}
	
	public void uploadAssetsImage(String filePath, String imageName) {
		
		logger.info("Trying to upload to Assets image called " + imageName);
		
		File image = new File(filePath);
		
		if (image.exists()) {
			
			setAttribute("class", "ng ng-valid ng-dirty");
			type(image.getAbsolutePath());
			
			logger.info("Image called " + imageName + " is being uploaded to Assets");
				
		} else { 
			
			logger.error("Image called " + imageName + " was NOT found in /images directory");
							
		}
		
	}

}
