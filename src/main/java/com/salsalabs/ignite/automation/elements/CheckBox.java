package com.salsalabs.ignite.automation.elements;

public interface CheckBox extends Element {

	boolean isChecked();

	void check();

	void unCheck();

	void changeState();

	void check(boolean isCheck);
}
