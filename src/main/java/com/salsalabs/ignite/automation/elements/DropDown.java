package com.salsalabs.ignite.automation.elements;

public interface DropDown extends Element, List {

	void selectByLabelJS(String value);

	void selectByID(String id);

	void pickFirstValueInList();
}
