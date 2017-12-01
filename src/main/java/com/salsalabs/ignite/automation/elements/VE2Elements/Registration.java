package com.salsalabs.ignite.automation.elements.VE2Elements;

public class Registration extends VEElements {
    public Registration(String elementPath, String name) {
        super(elementPath, name);
    }

    @Override
    public void drop() {
        dragAndDropElementOnLayoutWithFormElement();
    }

}
