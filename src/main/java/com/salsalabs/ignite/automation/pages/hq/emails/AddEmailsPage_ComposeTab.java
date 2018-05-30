package com.salsalabs.ignite.automation.pages.hq.emails;


import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.DropDown;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.VE2Elements.ButtonVEElement;
import com.salsalabs.ignite.automation.elements.VE2Elements.TextVEElement;
import com.salsalabs.ignite.automation.elements.impl.*;
import com.salsalabs.ignite.automation.pages.hq.basic.basicLayoutClass;

import org.openqa.selenium.JavascriptExecutor;


public class AddEmailsPage_ComposeTab extends AddEmailsPage{
	
	DropDown selectScheme = new DropDownImpl("//custom-select-scheme/div[@class='custom dropdown scheme']", "a", "Select Scheme");
	ContentEditTextBoxImpl subjectField = new ContentEditTextBoxImpl("//iframe[contains(@title,'subjectCkEditor')]" ,"//*[@contenteditable='true']", "Subject", true);
	Button addLinkButtonMenu = new ButtonImpl("//a[@title='Link']", "Add link on the menu");
	Button addBodyMergeFieldButtonMenu = new ButtonImpl("//div[@id='textEditModal']/descendant::a[@title='Insert a merge field']", "Insert a body merge field");
	Button addSubjectMergeFieldButtonMenu = new ButtonImpl("//div[@id='cke_subjectCkEditor']/descendant::a[@title='Insert a merge field']", "Insert a subject merge field");
	Button PublishButton = new ButtonImpl("//button[@id='btnPublish']", "Publish");
	TextBox emailFromField = new TextBoxImpl("//input[@name='fromAddress']", "Email from");
	TextBox fromNameField = new TextBoxImpl("//input[@name='fromName']", "From name");
	TextBox replyToEmailField = new TextBoxImpl("//input[@name='replyAddress']", "Reply address");
	Button addSplitButton = new ButtonImpl("//a[.='+']", "Add split");
	Button SplitTab = new ButtonImpl("//form[@id='emailblastform']/descendant::a[contains(text(),'Split')]", "Split tab");
	Button composeButton = new ButtonImpl("//button[@id='btnCompose']", "Next: Compose Your Email");
	LabelImpl firstTextElement = new LabelImpl("(//div[contains(@class,'content-render-wrapper')])[3]", "Element");
	Button firstEditBtn = new ButtonImpl("(//span[@button-content-edit-text])[1]", "Edit");
	TextBox inputLinkField = new TextBoxImpl("//input[@class='cke_dialog_ui_input_text']", "Input Link");
	Button addLinkButton = new ButtonImpl("//a[contains(@class,'cke_dialog_ui_button_ok')]", "Add link on the popup");
	Button addExternalLink = new ButtonImpl("//a[.='An External Page']", "An External Page");
	Button saveContent = new ButtonImpl("//a[contains(text(),'Save Content')]", "Save Content");
	//Button buttonFormElement = new ButtonImpl("//div[contains(@class,'content-render')]/descendant::a[contains(@class,'button')]", "Button form element");
	
	TableImpl mergeFildsList = new TableImpl("//div[@role='dialog' and not(contains(@style,'display: none'))]/descendant::table[@id='fieldResults']", "List with merge fields");
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
		setLinkInTheButton("Web version of this email");
		PublishButton.click();
		return new AddEmailsPage_PublishTab();
	}
	
	public AddEmailsPage_ComposeTab AddAllMergeFieldsIntoBody() {
		firstTextElement.scrollIntoViewAndDown();
		firstTextElement.moveToElement();//scrollIntoView();
		firstTextElement.highlight();
		firstTextElement.clickJS();
		firstEditBtn.click();
		sleep(5);
		//Text.Text.textElementContent.click();
		switchDefaultContent();
		addBodyMergeFieldButtonMenu.click();
		mergeFildsList.waitElement(10);//sleep(5);
		int fieldsCount = mergeFildsList.getRowsCount();
		for (int i = 1; i <= fieldsCount; i++) {
			mergeFildsList.waitElement(10);
			mergeFildsList.scrollCellintoViewByValue(mergeFildsList.getCellValue(i, 1));
			mergeFildsList.clickInCell(i, 1, "");
			defaultTextField.type(mergeFildsList.getCellValue(i, 1));
			okButton.highlight();
			okButton.click();
			TextVEElement.textElementContent.sendENTERKey();
			if (i < fieldsCount) {
				addBodyMergeFieldButtonMenu.click();
			}			
		}
		saveContent.waitElement();
		saveContent.click();
		return this;
	}
	
	public AddEmailsPage_ComposeTab AddAllMergeFieldsIntoSubject(String[] fields) {
		subjectField.type("Auto");
		subjectField.scrollFrameIntoViewAndDown();
		subjectField.switchToFrame();
		subjectField.highlight();
		subjectField.click();
		subjectField.switchToDefault();
		
		addSubjectMergeFieldButtonMenu.click();
		mergeFildsList.waitElement(10);//sleep(5);
		for (int i = 0; i < fields.length; i++) {
			mergeFildsList.waitElement(10);
			mergeFildsList.scrollCellintoViewByValue(fields[i]);
			mergeFildsList.clickOnCellByValue(fields[i]);
			defaultTextField.type(fields[i]);
			okButton.highlight();
			okButton.click();
			subjectField.switchToFrame();
			subjectField.highlight();
			subjectField.click();
			subjectField.switchToDefault();
			if (i < fields.length-1) {
				addSubjectMergeFieldButtonMenu.click();
			}			
		}

		
		return this;
	}
	
	/**
	 * use label under img as layout value. For now
	 * layout can be Basic or Sidebar
	 * @param layout
	 * @return
	 */
	public AddEmailsPage_ComposeTab selectLayout(String layout) {
		basicLayoutClass.selectLayout(layout);
		composeButton.click();
		sleep(10);
		return this;
	}
	
	public AddEmailsPage_ComposeTab addLink(String link) {
		if (!link.equalsIgnoreCase("")){
			firstTextElement.scrollIntoViewAndDown();
			firstTextElement.moveToElement();//scrollIntoView();
			firstTextElement.highlight();
			firstTextElement.click();
			firstEditBtn.click();
			TextVEElement.textElementContent.type("Link: ");
			//switchDefaultContent();
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
	
	private void setLinkInTheButton(String linkName) {
		/*buttonFormElement.waitElement();
		buttonFormElement.scrollIntoView();
		buttonFormElement.doubleClick();*/
		ButtonVEElement calltoActionButton = new ButtonVEElement("//div[contains(@class,'content-render')]/descendant::a[contains(@class,'button')]", "Call to Action button");
		calltoActionButton.setLink(linkName);
	}
}
