package com.salsalabs.ignite.automation.elements.VE2Elements;

public class RegisterButton extends VEElements {

    public RegisterButton(String elementPath, String name) {
        super(elementPath, name);
    }

    @Override
    public void drop() {
        dragAndDropElementOnLayoutWithFormElement();
    }

}
