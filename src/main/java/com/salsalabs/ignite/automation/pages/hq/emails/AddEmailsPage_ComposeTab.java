package com.salsalabs.ignite.automation.pages.hq.emails;


import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.DropDown;
import com.salsalabs.ignite.automation.elements.Label;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.ContentEditTextBoxImpl;
import com.salsalabs.ignite.automation.elements.impl.DropDownImpl;
import com.salsalabs.ignite.automation.elements.impl.LabelImpl;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;


public class AddEmailsPage_ComposeTab extends AddEmailsPage{
	
	DropDown selectScheme = new DropDownImpl("//custom-select-scheme/div[@class='custom dropdown scheme']", "a", "Select Scheme");
	TextBox subjectField = new ContentEditTextBoxImpl("//iframe[contains(@title,'subjectCkEditor')]" ,"//*[@contenteditable='true']", "Subject", true);
	TextBox textElementContent = new ContentEditTextBoxImpl("//iframe[contains(@title,'ckeditor')]" ,"//*[@contenteditable='true']", "Email Template body", true);
	Button addLinkButtonMenu = new ButtonImpl("//a[@title='Link']", "Add link on the menu");
	Button PublishButton = new ButtonImpl("//button[@id='btnPublish']", "Publish");
	TextBox emailFromField = new TextBoxImpl("//input[@name='fromAddress']", "Email from");
	Button addSplitButton = new ButtonImpl("//a[.='+']", "Add split");
	Button SplitTab = new ButtonImpl("//form[@id='emailblastform']/descendant::a[contains(text(),'Split')]", "Split tab");
	Button composeButton = new ButtonImpl("//button[@id='btnCompose']", "Next: Compose Your Email");
	Label firstContentElement = new LabelImpl("(//div[contains(@class,'content-render-content')])[1]", "Element");
	Button firstEditBtn = new ButtonImpl("(//span[@button-content-edit-text])[1]", "Edit");
	TextBox inputLinkField = new TextBoxImpl("//input[@class='cke_dialog_ui_input_text']", "Input Link");
	Button addLinkButton = new ButtonImpl("//a[contains(@class,'cke_dialog_ui_button_ok')]", "Add link on the popup");
	Button addExternalLink = new ButtonImpl("//a[.='An External Page']", "An External Page");
	Button saveContent = new ButtonImpl("//a[contains(text(),'Save Content')]", "Save Content");
	
	
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
			addLink("google.com");
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
	public AddEmailsPage_ComposeTab selectLayout(int layout) {
		sleep(10);
		Button lay = new ButtonImpl("(//button[contains(@ng-click,'selectItem')])[" + layout + "]", layout + " layout");
		lay.click();
		composeButton.click();
		sleep(10);
		return this;
	}
	
	public AddEmailsPage_ComposeTab addLink(String link) {
		firstContentElement.moveToElement();//scrollIntoView();
		firstContentElement.highlight();
		firstContentElement.click();
		firstEditBtn.click();
		textElementContent.type("Link: ");
		switchDefaultContent();
		addLinkButtonMenu.click();
		addExternalLink.click();
		inputLinkField.type(link);
		addLinkButton.click();		
		saveContent.click();
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
