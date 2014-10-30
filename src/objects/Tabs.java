package objects;

import interfaces.iElement;
import interfaces.iTabs;

public class Tabs extends Element implements iElement, iTabs {

	public Tabs(String elementPath, String name) {
		super(elementPath, name);
	}

	@Override
	public String getSelectedTabName() {
		
		return null;//is not implemented;
	}

	@Override
	public void selectTab(String tabName) {
		
		//is not implemented
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

}
