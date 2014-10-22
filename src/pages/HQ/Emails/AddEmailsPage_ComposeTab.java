package pages.HQ.Emails;

import org.openqa.selenium.Keys;

import objects.Button;
import objects.ContentEditableTextBox;
import objects.DropDown;
import objects.Table;
import objects.TextBox;
import selenium.CommonUtils;

public class AddEmailsPage_ComposeTab extends AddEmailsPage{
	
	DropDown selectScheme = new DropDown("//custom-select-scheme/div[@class='custom dropdown scheme']", "a", "Select Scheme");
	ContentEditableTextBox subjectField = new ContentEditableTextBox("//iframe[@id='subjectFrame']" ,"//*[@id='subjectId']", "Subject", true);
	Table EmailTemplate = new Table("//emailtemplate/descendant::table[@class='body-wrap']/descendant::div[@class='content']/descendant::table", "Email Template body" );
	Button addLinkButtonMenu = new Button("//span[@title='Link']", "Add link on the menu");
	Button PublishButton = new Button("//button[@id='btnPublish']", "Publish");
	TextBox emailFromField = new TextBox("//input[@name='fromAddress']", "Email from");
	Button addSplitButton = new Button("//a[.='+']", "Add split");
	Button SplitTab = new Button("//form[@id='emailblastform']/descendant::a[contains(text(),'Split')]", "Split tab");
	
	
	public AddEmailsPage_PublishTab fillAllFieldsAndGoForward(String subj, String emailFrom, Integer splitsAmount) {
		if (splitsAmount < 1) splitsAmount = 1;
		addSplit(splitsAmount);
		for (int i = 1; i <= splitsAmount; i++) {
			if (splitsAmount > 1) {
				SplitTab.clickByN(i);
				subj = subj.replace(" Split " + (i-1), "");
				subj = subj + " Split " + i;				
				sleep(8000);
			}
			selectScheme.selectByLabelJS("Arial, Helvetica, sans-serif");
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
		
		new Button("//p[.='" + layout + "']/preceding-sibling::img", layout + "layout").click();;
		return this;
	}
	
	public AddEmailsPage_ComposeTab addLink(String link) {
		TextBox inputLinkField = new TextBox("//input[@name='url']", "Input Link");
		Button addLinkButton = new Button("//a[.='Add it!']", "Add link on the popup");
		switchToFrame("//iframe[@id='veframe']");
		try {
			EmailTemplate.clickInCell(1, 1, "h3[contains(text(),'Hi')]");
			//EmailTemplate.sendKeysInCell(1, 1, "h3[contains(text(),'Hi')]", Keys.CONTROL + Keys.chord("A"));
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
			sleep(10000);
			addSplitButton.click();
			sleep(10000);
		}
	}
}
