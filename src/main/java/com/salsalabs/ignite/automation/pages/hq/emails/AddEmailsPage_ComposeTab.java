package com.salsalabs.ignite.automation.pages.hq.emails;


import org.openqa.selenium.Keys;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.DropDown;
import com.salsalabs.ignite.automation.elements.Table;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.ContentEditTextBoxImpl;
import com.salsalabs.ignite.automation.elements.impl.DropDownImpl;
import com.salsalabs.ignite.automation.elements.impl.TableImpl;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;


public class AddEmailsPage_ComposeTab extends AddEmailsPage{
	
	DropDown selectScheme = new DropDownImpl("//custom-select-scheme/div[@class='custom dropdown scheme']", "a", "Select Scheme");
	TextBox subjectField = new ContentEditTextBoxImpl("//iframe[@id='subjectFrame']" ,"//*[@id='subjectId']", "Subject", true);
	TextBox emailTemplate = new ContentEditTextBoxImpl("//iframe[@id='veframe']" ,"//div[@class='lead editable restricted paragraph']", "Email Template body", true);
	//Table EmailTemplate = new TableImpl("//div[@class=\"content\"]/table", "Email Template body" );
	Button addLinkButtonMenu = new ButtonImpl("//span[@title='Link']", "Add link on the menu");
	Button PublishButton = new ButtonImpl("//button[@id='btnPublish']", "Publish");
	TextBox emailFromField = new TextBoxImpl("//input[@name='fromAddress']", "Email from");
	Button addSplitButton = new ButtonImpl("//a[.='+']", "Add split");
	Button SplitTab = new ButtonImpl("//form[@id='emailblastform']/descendant::a[contains(text(),'Split')]", "Split tab");
	
	
	public AddEmailsPage_PublishTab fillAllFieldsAndGoForward(String subj, String emailFrom, Integer splitsAmount) {
		if (splitsAmount < 1) splitsAmount = 1;
		addSplit(splitsAmount);
		for (int i = 1; i <= splitsAmount; i++) {
			if (splitsAmount > 1) {
				SplitTab.clickByNumber(i);
				subj = subj.replace(" Split " + (i-1), "");
				subj = subj + " Split " + i;				
				sleep(8);
			}
			//selectScheme.selectByLabel("Arial, Helvetica, sans-serif");
			subjectField.type(subj);
			emailFromField.type(emailFrom);
			addLink("salsalabs.com");
		}		
		PublishButton.click();
		return new AddEmailsPage_PublishTab();
	}
	
	/**
	 * use label under img as layout value. For now
	 * layout can be Basic or Sidebar
	 * @param layout
	 * @return
	 */
	public AddEmailsPage_ComposeTab selectLayout(String layout) {
		sleep(10);
		Button lay=new ButtonImpl("//p[.='" + layout + "']/preceding-sibling::img", layout + "layout");
		lay.click();
		/*while(!lay.isNotDisplayed()){
			lay.click();
		}*/
		sleep(10);
		return this;
	}
	
	public AddEmailsPage_ComposeTab addLink(String link) {
		TextBox inputLinkField = new TextBoxImpl("//input[@name='url']", "Input Link");
		Button addLinkButton = new ButtonImpl("//button[contains(@ng-click,'insertLink')]", "Add link on the popup");
		Button addExternal = new ButtonImpl("//a[.='An External Page']", "An External Page");
		emailTemplate.type("Link: ");
		switchDefaultContent();
		addLinkButtonMenu.click();
		addExternal.click();
		inputLinkField.type(link);
		addLinkButton.click();		
		return this;
	}
	
	private void addSplit(Integer splitAmounts) {
		for (int i = 1; i < splitAmounts; i++) {
			sleep(10);
			addSplitButton.click();
			sleep(10);
		}
	}
}
