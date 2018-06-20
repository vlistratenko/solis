package com.salsalabs.ignite.automation.pages.hq.basic;

import com.salsalabs.ignite.automation.common.Browser;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.Panel;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.PanelImpl;

public class Layouts extends Browser {

	public enum LayoutName {

		HERO("Hero"),
		HERO_SIDEBAR("Hero Sidebar"),
		HERO_SIDEKICK("Hero Sidekick"),
		SUGGESTED("Suggested"),
		SIDEBAR("Sidebar"),
		BLANK("Blank"),
		BASIC("Basic");

		private String layoutName;

		LayoutName(String layoutName) {
			this.layoutName = layoutName;
		}

		String getLayoutName(){
			return this.layoutName;
		}
	}

	public static void selectLayout(String layout) {
		Button lay = getLayoutButton(layout);
		Panel img = new PanelImpl("//strong[.='" + layout + "']/ancestor::div[contains(@class,'layout_item')]/descendant::img[contains(@alt, '" + layout + "')]", layout + " layout image");
		img.fluentWaitForElementPresenceIgnoringExceptions();
		img.scrollIntoView();
		lay.fluentWaitForElementPresenceIgnoringExceptions();
		lay.click();
	}

	public static void selectLayout(LayoutName layoutName) {
		String layout = layoutName.getLayoutName();
		Button lay = getLayoutButton(layout);
		Panel img = new PanelImpl("//strong[.='" + layout + "']/ancestor::div[contains(@class,'layout_item')]/descendant::img[contains(@alt, '" + layout + "')]", layout + " layout image");
		img.fluentWaitForElementPresenceIgnoringExceptions();
		img.scrollIntoView();
		lay.click();
	}

	private static Button getLayoutButton(String layout) {
		return new ButtonImpl("//strong[.='" + layout + "']/ancestor::div[contains(@class,'layout_item')]/descendant::button[contains(@ng-click,'selectItem')]", layout + " layout");
	}
}
