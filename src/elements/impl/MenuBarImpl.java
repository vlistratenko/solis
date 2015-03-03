package elements.impl;

import core.util.SeleneseTestCase;
import elements.Button;
import elements.List;


public class MenuBarImpl extends ElementImpl implements List {
	
	
	public MenuBarImpl(String elementPath, String elementName) {
		super(elementPath, elementName);
	}

	private Button getChildItemByLabel(String label) {
		logger.info("Get item with label " + label);
		return new ButtonImpl(path + "/descendant::*[text()='" + label + "']", label + " item");

	}

	@Override
	public void selectByLabel(String value) {
		logger.info("Select value by label " + value + " in the " + elementName);
		SeleneseTestCase.bug.add("Select value by label " + value + " in the " + elementName);
		this.getChildItemByLabel(value).click();
		sleep(2);
	}
}
