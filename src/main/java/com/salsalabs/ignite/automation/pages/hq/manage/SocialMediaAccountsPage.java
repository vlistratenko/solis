package com.salsalabs.ignite.automation.pages.hq.manage;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.TableImpl;
import com.salsalabs.ignite.automation.pages.other.TwitterAndFBAuthorization;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

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
		String urlTwitter = driver.getCurrentUrl();
		driver.close();
		sleep(3);
		switchToWindow(mainContext);
		driver.findElement(By.tagName("body")).sendKeys(Keys.CONTROL,"t"); 
		sleep(3);
		driver.get(modifyUrl(urlTwitter));
		sleep(15);
		//switchToWindow(mainContext);
		driver.findElement(By.tagName("body")).sendKeys(Keys.CONTROL,"w");
		addAccount.click();
		sleep(3);
		selectFB.click();
		switchToPopupWindow(mainContext);
		new TwitterAndFBAuthorization().authorizeFacebook(facebookUsername, facebookPassword);
		String urlFb = driver.getCurrentUrl();
		driver.close();
		sleep(3);
		switchToWindow(mainContext);
		driver.findElement(By.tagName("body")).sendKeys(Keys.CONTROL,"t"); 
		sleep(3);
		driver.get(modifyUrl(urlFb));
		sleep(15);
		driver.findElement(By.tagName("body")).sendKeys(Keys.CONTROL,"w");;
	}
	
	public void removeTwitterAndFacebook() {
		new TableImpl("//table[@id='socialMediaAccountsDashboard']", "Twitter checkbox").clickInCell(1, 1);
		new TableImpl("//table[@id='socialMediaAccountsDashboard']", "Facebook checkbox").clickInCell(2, 1);
		deleteAccount.click();
	}
	
	private String modifyUrl(String url) {
		return url.replaceFirst("igniteaction", "ignite");
	}
}
