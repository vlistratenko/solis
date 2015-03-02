package elements.impl;

import elements.Panel;

public class PanelImpl extends ElementImpl implements Panel {

	public PanelImpl(String elementPath, String elementName) {
		super(elementPath, elementName);
	}

	@Override
	public Integer getElementsCount() {
		return findElementsByXpath(path).size();
	}

	public PanelImpl(String elementPath, String elementName, int number) {
		super(elementPath, elementName);
	}

	@Override
	public String getChildElementPath(String elementPath, int setNumder) {
		return path + "[" + setNumder + "]/descendant::" + elementPath;
	}

}
