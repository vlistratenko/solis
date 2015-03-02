package elements.impl;

import elements.Label;

public class LabelImpl extends ElementImpl implements Label {

	public LabelImpl(String elementPath, String elementName) {
		super(elementPath, elementName);
	}

	public Integer getElementsAmount() {
		return findElementsByXpath(path).size();
	}

	@Override
	public String getText() {
		logger.info("Get text from " + elementName);
		logger.info("Text is " + super.getText(path));
		return super.getText(path);
	}

	private boolean isNotExists() {
		logger.info("Check that " + elementName + " is not exists.");
		return super.isNotElementPresent(path);
	}

	@Override
	public boolean waitForNotExists(Integer timeOut) {
		for (int i = 0; i < timeOut; i++) {
			if (isNotExists()) {
				break;
			} else {
				sleep(1000);
			}
		}
		return isNotExists();
	}
}
