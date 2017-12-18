package com.salsalabs.ignite.automation.elements.VE2Elements;

public class DonateButton extends VEElements {

    public DonateButton(String elementPath, String name) {
        super(elementPath, name);
    }

    @Override
    public void drop() {
        dragAndDropElementOnLayoutWithRowElement();
    }

}
