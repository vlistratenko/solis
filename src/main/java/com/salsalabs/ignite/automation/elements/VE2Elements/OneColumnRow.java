package com.salsalabs.ignite.automation.elements.VE2Elements;

public class OneColumnRow extends VEElements {
    
	static String pathAfterDrop = "//div[@autotest-id='render-container-division-12']/descendant::div[contains(@class, 'ui-droppable')]";
	
	OneColumnRow(String ownPath, String name) {
        super(ownPath, name);
    }

    @Override
    public void drop(){
        dragAndDropOnEmptyLayout();
    }

}
