package com.salsalabs.ignite.automation.pages.hq.emails;


import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.DropDown;
import com.salsalabs.ignite.automation.elements.Table;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.*;
import org.openqa.selenium.JavascriptExecutor;


public class AddEmailsPage_ComposeTab extends AddEmailsPage{
	
	DropDown selectScheme = new DropDownImpl("//custom-select-scheme/div[@class='custom dropdown scheme']", "a", "Select Scheme");
	TextBox subjectField = new ContentEditTextBoxImpl("//iframe[contains(@title,'subjectCkEditor')]" ,"//*[@contenteditable='true']", "Subject", true);
	ContentEditTextBoxImpl textElementContent = new ContentEditTextBoxImpl("//iframe[contains(@title,'ckeditor')]" ,"//*[@contenteditable='true']", "Email Template body", true);
	Button addLinkButtonMenu = new ButtonImpl("//a[@title='Link']", "Add link on the menu");
	Button addMergeFieldButtonMenu = new ButtonImpl("//a[@title='Insert a merge field']", "Insert a merge field");
	Button PublishButton = new ButtonImpl("//button[@id='btnPublish']", "Publish");
	TextBox emailFromField = new TextBoxImpl("//input[@name='fromAddress']", "Email from");
	TextBox fromNameField = new TextBoxImpl("//input[@name='fromName']", "From name");
	TextBox replyToEmailField = new TextBoxImpl("//input[@name='replyAddress']", "Reply address");
	Button addSplitButton = new ButtonImpl("//a[.='+']", "Add split");
	Button SplitTab = new ButtonImpl("//form[@id='emailblastform']/descendant::a[contains(text(),'Split')]", "Split tab");
	Button composeButton = new ButtonImpl("//button[@id='btnCompose']", "Next: Compose Your Email");
	LabelImpl firstContentElement = new LabelImpl("(//div[contains(@class,'content-render-wrapper')])[1]", "Element");
	Button firstEditBtn = new ButtonImpl("(//span[@button-content-edit-text])[1]", "Edit");
	TextBox inputLinkField = new TextBoxImpl("//input[@class='cke_dialog_ui_input_text']", "Input Link");
	Button addLinkButton = new ButtonImpl("//a[contains(@class,'cke_dialog_ui_button_ok')]", "Add link on the popup");
	Button addExternalLink = new ButtonImpl("//a[.='An External Page']", "An External Page");
	Button saveContent = new ButtonImpl("//a[contains(text(),'Save Content')]", "Save Content");
	
	Table mergeFildsList = new TableImpl("//table[@id='fieldResults']", "List with merge fields");
	Button okButton = new ButtonImpl("//div[.='Insert a merge field']/ancestor::table/descendant::td [contains(@id, 'cke_dialog_footer')]/descendant::a[@title='OK']/span", "Save merge field");
	TextBox defaultTextField = new TextBoxImpl("//label[contains(text(), 'field empty')]/../descendant::input", "Default text");
	
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
		}		
		PublishButton.click();
		return new AddEmailsPage_PublishTab();
	}
	
	public AddEmailsPage_ComposeTab AddAllMergeFields() {
		firstContentElement.scrollIntoViewAndDown();
		firstContentElement.moveToElement();//scrollIntoView();
		firstContentElement.highlight();
		firstContentElement.click();
		firstEditBtn.click();
		sleep(5);
		//textElementContent.click();
		switchDefaultContent();
		addMergeFieldButtonMenu.click();
		mergeFildsList.waitElement(10);//sleep(5);
		int fieldsCount = mergeFildsList.getRowsCount();
		for (int i = 1; i <= fieldsCount; i++) {
			mergeFildsList.waitElement(10);
			mergeFildsList.clickInCell(i, 1, "");
			defaultTextField.type(mergeFildsList.getCellValue(i, 1));
			okButton.highlight();
			okButton.click();
			textElementContent.sendENTERKey();
			if (i < fieldsCount) {
				addMergeFieldButtonMenu.click();
			}			
		}
		saveContent.waitElement(10);
		saveContent.click();
		//PublishButton.click();
		return this;
	}
	
	/**
	 * use label under img as layout value. For now
	 * layout can be Basic or Sidebar
	 * @param layout
	 * @return
	 */
	public AddEmailsPage_ComposeTab selectLayout(String layout) {
		sleep(10);
		Button lay = new ButtonImpl("//strong[.='" + layout + "']/ancestor::div[contains(@class,'layout_item')]/descendant::button[contains(@ng-click,'selectItem')]", layout + " layout");
		lay.click();
		composeButton.click();
		sleep(10);
		return this;
	}
	
	public AddEmailsPage_ComposeTab addLink(String link) {
		if (!link.equalsIgnoreCase("")){
			firstContentElement.scrollIntoViewAndDown();
			firstContentElement.moveToElement();//scrollIntoView();
			firstContentElement.highlight();
			firstContentElement.click();
			firstEditBtn.click();
			textElementContent.type("Link: ");
			switchDefaultContent();
			addLinkButtonMenu.click();
			sleep(5);
			addExternalLink.click();
			inputLinkField.type(link);
			addLinkButton.click();		
			saveContent.click();
		}
		return this;
	}
	
	private void addSplit(Integer splitAmounts) {
		for (int i = 1; i < splitAmounts; i++) {
			sleep(10);
			addSplitButton.click();
			sleep(10);
		}
	}

	public void checkEmailDefaults(String fromName, String fromEmailAddress, String replyEmailAddress) {
		sleep(2);
		verifier.verifyEquals(
				(String) ((JavascriptExecutor) driver).executeScript("return document.querySelector('#emailblastform > div:nth-child(4) > div > div:nth-child(3) > div:nth-child(7) > div > div:nth-child(1) > div:nth-child(2) > hq-input-validation > div > div > div > span:nth-child(1) > input').value"),
				fromEmailAddress);
		verifier.verifyEquals(
				(String) ((JavascriptExecutor) driver).executeScript("return document.querySelector('#emailblastform > div:nth-child(4) > div > div:nth-child(3) > div:nth-child(7) > div > div:nth-child(1) > div:nth-child(1) > validation-message > div > span > input').value"),
				fromName);
		verifier.verifyEquals(
				(String) ((JavascriptExecutor) driver).executeScript("return document.querySelector('#emailblastform > div:nth-child(4) > div > div:nth-child(3) > div:nth-child(7) > div > div:nth-child(2) > div > validation-message > div > span > input').value"),
				replyEmailAddress);
	}
}
