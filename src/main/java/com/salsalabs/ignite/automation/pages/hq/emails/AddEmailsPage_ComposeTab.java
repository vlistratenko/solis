package com.salsalabs.ignite.automation.pages.hq.emails;


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
	Table EmailTemplate = new TableImpl("//emailtemplate/descendant::table[@class='body-wrap']/descendant::div[@class='content']/descendant::table", "Email Template body" );
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
			selectScheme.selectByLabel("Arial, Helvetica, sans-serif");
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
		new ButtonImpl("//p[.='" + layout + "']/preceding-sibling::img", layout + "layout").click();
		sleep(10);
		return this;
	}
	
	public AddEmailsPage_ComposeTab addLink(String link) {
		TextBox inputLinkField = new TextBoxImpl("//input[@name='url']", "Input Link");
		Button addLinkButton = new ButtonImpl("//a[.='Add it!']", "Add link on the popup");
		switchToFrame("//iframe[@id='veframe']");
		try {
			EmailTemplate.clickInCell(1, 1, "h3[contains(text(),'Hi')]");
		} catch (Exception e) {
			e.printStackTrace();
			switchDefaultContent();
		}
		switchDefaultContent();
		addLinkButtonMenu.click();
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
