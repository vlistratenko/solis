package com.salsalabs.ignite.automation.pages.hq.manage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;

import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.PropertyName;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.common.Supporter;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.DropDown;
import com.salsalabs.ignite.automation.elements.Label;
import com.salsalabs.ignite.automation.elements.Table;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.DropDownImpl;
import com.salsalabs.ignite.automation.elements.impl.LabelImpl;
import com.salsalabs.ignite.automation.elements.impl.TableImpl;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;
import com.salsalabs.ignite.automation.pages.hq.manage.CustomFieldsPage.CustomField;

public class ImportAddPage extends ManagePage{
	Button importLink = new ButtonImpl("//a[@href='/#/audience/supporters/imports']", "Import link");
	
	//firs step
	TextBox importNameField = new TextBoxImpl("//input[@id='name']", "Import Name", true);
	TextBox importDescriptionField = new TextBoxImpl("//textarea[@id='description']", "Import description", false);
	TextBox fileUpload = new TextBoxImpl("//input[@id='fileUpload']", "Logo", false);
	Button nextStepButton = new ButtonImpl("//button[@id='btnDoUpload']/*", "Match My Fields");
	
	//second step
	TextBox importFromRowField = new TextBoxImpl("//input[@id='offset']", " My data starts on row", false);
	Table mapTable = new TableImpl("//form[@id='importForm']/descendant::table", "Map");
	Button dedupeButton = new ButtonImpl("//button[contains(@id, 'btnSave')]", "Match My Fields");
	
	//I'm done step
	Button doneButton = new ButtonImpl("//button[@id='btnSave3']/*", "Done button");
	private int amountOfSupporters = 50;
	private static final String IMPORT_DONE_LABEL = "Congrats! Your import is complete.";
	
	public ImportAddPage fillFirstStep(String name, String description) {
		return this.fillFirstStep(name, description, null);
	}
	
	public ImportAddPage fillFirstStep(String name, String description, List<CustomField> customFields) {
		importNameField.type(name);
		importDescriptionField.type(description);
		
		fileUpload.setAttribute("class", "ng-pristine ng-valid");
		fileUpload.scrollIntoView();
		try {
			fileUpload.type(generateSupporters(amountOfSupporters, 20, customFields));
		} catch (FileNotFoundException e) {
			SeleneseTestCase.logger.error("",e);
		}
		
		nextStepButton.click();
		CommonUtils.setProperty(PropertyName.IMPORT_NAME, name);
		sleep(2);
		return this;
	}
	
	public ImportAddPage fillSecondStep(String importFromRow) {
		sleep(2);
		importFromRowField.type(importFromRow);
		Integer amountOfFields = mapTable.getRowsCount();
		
		for (int i = 1; i <= amountOfFields; i++) {
			String fieldName = mapTable.getCellValue(i, 1);
			String pathToDropDown = mapTable.getPathToChildElement(i, 2, "div[contains(@class, 'custom dropdown')]");
			DropDown mapToField = new DropDownImpl(pathToDropDown, pathToDropDown + "/a", "MapToField");
			mapToField.selectByLabelJS(fieldName);
			
		}
		dedupeButton.click();
		sleep(5);
		return this;
	}
	
	public ImportAddPage fillThirdStep() {
		doneButton.click();
		sleep(60);
		Label label = new LabelImpl("//*[contains(text(), '" + IMPORT_DONE_LABEL + "')]", "Import done");
		for (int i = 0; i < 10; i++) {
			waitConditionBecomesTrueWithRefersh(!label.isNotDisplayed(), 1);
		}
		return this;
	}
	
	public ImportPage openImportPage() {
		importLink.click();
		return new ImportPage();
	}
	
	public String generateSupporters(Integer amount, Integer amountOfRealSupporters) throws FileNotFoundException {
		return generateSupporters(amount, amountOfRealSupporters, null);
	}
	
	public String generateSupporters(Integer amount, Integer amountOfRealSupporters, List<CustomField> customFields) throws FileNotFoundException {
		PrintWriter out = new PrintWriter("supporters.csv");
		StringBuilder header = new StringBuilder();
		header.append("Cell Phone,City,Email Address,Facebook Id,First Name,Home Phone,Last Name,Preferred Language,");
		header.append("State,Twitter Id,Zip Code,\"Address, line 1\",\"Address, line 2\",Middle Name");
		if (customFields != null) {
			for (CustomField type : customFields) {
				header.append(",").append(type.getName());
			}
		}
		out.println(header.toString());
		boolean unexistedDomain = false;
		boolean unexistedEmail = false;
		
		for (int i = 1; i <= amount; i++) {
			Supporter supporter = new Supporter();
			if (i<=amountOfRealSupporters) {
				supporter.setEmailDomain("." + SeleneseTestCase.emailClient.getMbox() + "@mailosaur.in");
			}else{
				supporter.setEmailDomain("@devnull.test.ignite.net");
			}
			supporter.setImportedEmail(supporter.getImportedEmail() + i + supporter.getEmailDomain());//".3e41c646@mailosaur.in";//"@salsalabs.com";//"@devnull.test.ignite.net";
			if (!unexistedDomain && i >= amountOfRealSupporters) {
				supporter.setImportedEmail("unexisting@unexisting.dom");
				unexistedDomain = true;
			}else if (!unexistedEmail && i >= amountOfRealSupporters) {
				supporter.setImportedEmail("unexisting@igniteaction.net");
				unexistedEmail = true;
			}
			StringBuilder row = new StringBuilder();
			row.append(supporter.getcPhone()).append(",");
			row.append(supporter.getCity()).append(",");
			row.append(supporter.getImportedEmail()).append(",");
			row.append(supporter.getFacebook()).append(",");
			row.append(supporter.getFirstName()).append(",");
			row.append(supporter.getHome_Phone()).append(",");
			row.append(supporter.getLastName()).append(",");
			row.append(supporter.getPreferredLanguage()).append(",");
			row.append(supporter.getState()).append(",");
			row.append(supporter.getTwitter()).append(",");
			row.append(supporter.getZipCode()).append(",");
			row.append(supporter.getAddressLine1()).append(",");
			row.append(supporter.getAddressLine2()).append(",");
			row.append(supporter.getMiddleName());
			if (customFields != null) {
				appendCustomFields(row, customFields);
			}
			out.println(row.toString());
		}
		out.close();
		return new File("supporters.csv").getAbsolutePath();
	}
	
	private void appendCustomFields(StringBuilder row, List<CustomField> customFields) {
		for (CustomField cf : customFields) {
			row.append(",");
			switch (cf.getType()) {
				case TextBox: {
					row.append(RandomStringUtils.randomAlphabetic(5));
					break;
				}
				case Number: {
					row.append(CommonUtils.getRandomNumericValueFixedLength(5));
					break;
				}
				default: { break; }
			}
		}
	}
}
