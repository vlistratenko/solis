package com.salsalabs.ignite.automation.pages.hq.basic;

import com.salsalabs.ignite.automation.common.Browser;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.Panel;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.PanelImpl;

public class basicLayoutClass extends Browser {

	

	public static void selectLayout(String layout) {
		Button lay = getlayoutButton(layout);
		Panel img = new PanelImpl("//strong[.='" + layout + "']/ancestor::div[contains(@class,'layout_item')]/descendant::img[contains(@alt, '" + layout + "')]", layout + " layout image");
		img.waitElement();
		lay.click();
	}
	
	public static Button getlayoutButton(String layout) {
		return new ButtonImpl("//strong[.='" + layout + "']/ancestor::div[contains(@class,'layout_item')]/descendant::button[contains(@ng-click,'selectItem')]", layout + " layout");
	}
}
