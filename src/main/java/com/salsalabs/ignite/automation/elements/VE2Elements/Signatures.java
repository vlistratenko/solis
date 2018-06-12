package com.salsalabs.ignite.automation.elements.VE2Elements;

public class Signatures extends VEElements {

    public enum CustomizedSupporterNameOptions {
        FIRST_NAME_LAST_NAME,
        FIRST_INITIAL_LAST_NAME,
        FIRST_NAME_LAST_INITIAL,
        FIRST_INITIAL_LAST_INITIAL
    }

    public enum CustomizedSupporterLocationOptions {
        CITY_STATE,
        CITY_ONLY,
        STATE_ONLY,
        DO_NOT_DISPLAY
    }

    public Signatures(String elementPath, String name) {
        super(elementPath, name);
    }

    @Override
    public void drop() {
        dragAndDropElementOnLayoutWithRowElement();
    }
}
