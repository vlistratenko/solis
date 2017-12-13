package com.salsalabs.ignite.automation.elements.VE2Elements;

public class ButtonVEElement extends VEElements {

    ButtonVEElement(String path, String name) {
        super(path, name);
    }

    @Override
    public void drop() {
        dragAndDropElementOnLayoutWithFormElement();
    }

/*
    @Override
    public VEElements configure(VEElements el) {
        return null;
    }

    @Override
    public VEElements edit(VEElements el) {
        return null;
    }

    @Override
    public VEElements delete(VEElements el) {
        return null;
    }*/
}
