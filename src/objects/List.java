package objects;

import selenium.CommonUtils;
import interfaces.iDropDown;


public class List extends Element implements iDropDown {
	
	
	public List(String elementPath, String elementName) {
		super(elementPath, elementName);
	}

	private Button getChildItemByLabel(String label) {
		logger.debug("Get item with label " + label);
		return new Button(path + "/descendant::*[text()='" + label + "']", label + " item");

	}

	@Override
	public void selectByLabel(String value) {
		if (value.contains(":")) {
			logger.info("Select values by labels " + value + " in the " + elementName);
			String[] values = CommonUtils.getArrayFromStringBySymbol(value, ":");
			for (int i = 0; i < values.length; i++) {
				this.getChildItemByLabel(values[i]).click();
			}
		}else {
			logger.info("Select value by label " + value + " in the " + elementName);
			this.getChildItemByLabel(value).click();
		}		
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
