package com.salsalabs.ignite.automation.elements.VE2Elements;

public class TargetedMessages extends VEElements {
    public TargetedMessages(String elementPath, String name) {
        super(elementPath, name);
    }

    @Override
    public void drop() {
        dragAndDropElementOnLayoutWithRowElement();
    }
}