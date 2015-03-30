package com.salsalabs.ignite.automation.elements;

public interface Tabs extends Element {

	String getSelectedTabName();

	void selectTab(String tabName);
}
