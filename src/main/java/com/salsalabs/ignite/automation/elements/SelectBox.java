package com.salsalabs.ignite.automation.elements;

public interface SelectBox extends TextBox, Element {

	void selectByLabel(String value);

	void selectByIndex(int index);
}
