package elements.impl;

import elements.Tabs;

public class TabsImpl extends ElementImpl implements Tabs {

	public TabsImpl(String elementPath, String name) {
		super(elementPath, name);
	}

	@Override
	public String getSelectedTabName() {
		
		return null;//is not implemented;
	}

	@Override
	public void selectTab(String tabName) {
		
	}
}
