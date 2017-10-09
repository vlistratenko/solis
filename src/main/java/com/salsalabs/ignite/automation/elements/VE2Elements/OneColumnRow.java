package com.salsalabs.ignite.automation.elements.VE2Elements;

public class OneColumnRow extends VEElements {
    OneColumnRow(String ownPath, String name) {
        super(ownPath, name);
    }

    @Override
    public void drop(){
        dragAndDropOnEmptyLayout();
    }

}
