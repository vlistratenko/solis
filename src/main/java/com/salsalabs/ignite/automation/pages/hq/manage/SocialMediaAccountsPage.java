package com.salsalabs.ignite.automation.pages.hq.manage;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.TableImpl;
import com.salsalabs.ignite.automation.pages.other.TwitterAndFBAuthorization;
import com.salsalabs.ignite.automation.common.*;


public class SocialMediaAccountsPage extends ManagePage {
	
	private Button addAccount = new ButtonImpl("//button[contains(@ng-click, 'selectSocialMediaAccount')]", "Add a Social Network!");
	private Button selectTwitter = new ButtonImpl("//a[contains(text(), 'Twitter')]", "Twitter");
	private Button selectFB = new ButtonImpl("//a[contains(text(), 'Facebook')]", "Facebook");
	private Button deleteAccount = new ButtonImpl("//a[@ng-click='socialMediaAccounts.deleteSelectedAccounts()']", "Facebook");
	private String mainContext = driver.getWindowHandle();
		
	public void addTwitterAndFacebook(String twitterUsername, String twitterPassword, String facebookUsername, String facebookPassword) {
		addAccount.click();
		selectTwitter.click();
		switchToPopupWindow(mainContext);
		new TwitterAndFBAuthorization().authorizeTwitter(twitterUsername, twitterPassword);
		sleep(3);
		switchToWindow(mainContext);
		addAccount.click();
		selectFB.click();
		switchToPopupWindow(mainContext);
		new TwitterAndFBAuthorization().authorizeFacebook(facebookUsername, facebookPassword);
		sleep(3);
		switchToWindow(mainContext);
	}
	
	public void removeTwitterAndFacebook() {
		new TableImpl("//table[@id='socialMediaAccountsDashboard']", "Twitter checkbox").clickInCell(1, 1);
		new TableImpl("//table[@id='socialMediaAccountsDashboard']", "Facebook checkbox").clickInCell(2, 1);
		deleteAccount.click();
	}
}
