package com.salsalabs.ignite.automation.pages.hq.basic;

import com.salsalabs.ignite.automation.common.Browser;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.Panel;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.PanelImpl;

public class Layouts extends Browser {

	public enum LayoutName {
		HERO,
		HERO_SIDEBAR,
		HERO_SIDEKICK,
		SUGGESTED,
		SIDEBAR,
		BLANK,
		BASIC
	}

	public static void selectLayout(String layout) {
		Button lay = getLayoutButton(layout);
		Panel img = new PanelImpl("//strong[.='" + layout + "']/ancestor::div[contains(@class,'layout_item')]/descendant::img[contains(@alt, '" + layout + "')]", layout + " layout image");
		img.fluentWaitForElementPresenceIgnoringExceptions();
		img.scrollIntoView();
		lay.click();
	}

	public static void selectLayout(LayoutName layoutName) {
		String layout = getLayoutName(layoutName);
		Button lay = getLayoutButton(layout);
		Panel img = new PanelImpl("//strong[.='" + layout + "']/ancestor::div[contains(@class,'layout_item')]/descendant::img[contains(@alt, '" + layout + "')]", layout + " layout image");
		img.fluentWaitForElementPresenceIgnoringExceptions();
		img.scrollIntoView();
		lay.click();
	}

	private static Button getLayoutButton(String layout) {
		return new ButtonImpl("//strong[.='" + layout + "']/ancestor::div[contains(@class,'layout_item')]/descendant::button[contains(@ng-click,'selectItem')]", layout + " layout");
	}

	private static String getLayoutName(LayoutName layoutName) {
		String layout = "";
		switch (layoutName) {
			case HERO: layout = "Hero"; break;
			case HERO_SIDEBAR: layout = "Hero Sidebar"; break;
			case HERO_SIDEKICK: layout = "Hero Sidekick"; break;
			case SUGGESTED: layout = "Suggested"; break;
			case SIDEBAR: layout = "Sidebar"; break;
			case BLANK: layout = "Blank"; break;
			case BASIC: layout = "Basic"; break;
		}
		return layout;
	}
}
