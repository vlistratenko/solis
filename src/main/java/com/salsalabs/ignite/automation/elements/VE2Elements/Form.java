package com.salsalabs.ignite.automation.elements.VE2Elements;

public class Form extends VEElements {
    public Form(String elementPath, String name) {
        super(elementPath, name);
    }
    @Override
    public void drop() {
        dragAndDropOnRow();
    }

}
