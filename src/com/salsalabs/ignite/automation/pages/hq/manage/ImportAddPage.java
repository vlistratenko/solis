package com.salsalabs.ignite.automation.pages.hq.manage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.EmailClient;
import com.salsalabs.ignite.automation.common.PropertyName;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.common.Supporter;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.DropDown;
import com.salsalabs.ignite.automation.elements.Table;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.DropDownImpl;
import com.salsalabs.ignite.automation.elements.impl.TableImpl;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;

public class ImportAddPage extends ManagePage{
	Button importLink = new ButtonImpl("//a[@href='/#/settings/imports']", "Import link");
	
	//firs step
	TextBox importNameField = new TextBoxImpl("//input[@id='name']", "Import Name", true);
	TextBox importDescriptionField = new TextBoxImpl("//textarea[@id='description']", "Import description", false);
	TextBox fileUpload = new TextBoxImpl("//input[@id='fileUpload']", "Logo", false);
	Button nextStepButton = new ButtonImpl("//button[@id='btnDoUpload']/*", "Match My Fields");
	
	//second step
	TextBox importFromRowField = new TextBoxImpl("//input[@id='offset']", " My data starts on row", false);
	Table mapTable = new TableImpl("//form[@id='importForm']/descendant::table", "Map");
	Button dedupeButton = new ButtonImpl("//button[@id='btnSave2']/*", "Match My Fields");
	
	//I'm done step
	Button doneButton = new ButtonImpl("//button[@id='btnSave3']/*", "Done button");
	
	public ImportAddPage fillFirstStep(String name, String description) {
		name = name + CommonUtils.getUnicName();
		importNameField.type(name);
		importDescriptionField.type(description);
		
		fileUpload.setAttribute("class", "ng-pristine ng-valid");
		try {
			fileUpload.type(generateSupporters(50, 20));
		} catch (FileNotFoundException e) {
			SeleneseTestCase.logger.error("",e);
		}
		
		nextStepButton.click();
		CommonUtils.setProperty(PropertyName.IMPORT_NAME, name);
		sleep(2);
		return this;
	}
	
	public ImportAddPage fillSecondStep(String importFromRow) {
		importFromRowField.type(importFromRow);
		Integer amountOfFields = mapTable.getRowsCount();
		
		for (int i = 1; i <= amountOfFields; i++) {
			String fieldName = mapTable.getCellValue(i, 1);
			String pathToDropDown = mapTable.getPathToChildElement(i, 2, "div[contains(@class, 'custom dropdown')]");
			DropDown mapToField = new DropDownImpl(pathToDropDown, pathToDropDown + "/a", "MapToField");
			mapToField.selectByLabel(fieldName);
			
		}
		dedupeButton.click();
		sleep(5);
		return this;
	}
	
	public ImportAddPage fillThirdStep() {
		doneButton.click();
		sleep(30);
		return this;
	}
	
	public ImportPage openImportPage() {
		importLink.click();
		return new ImportPage();
	}
	
	
	
	public String generateSupporters(Integer amount, Integer amountOfRealSupporters) throws FileNotFoundException {
		PrintWriter out = new PrintWriter("supporters.csv");
		out.println("Cell Phone,"
				+ "City,"
				+ "Email Address,"
				+ "Facebook Id,"
				+ "First Name,"
				+ "Home Phone,"
				+ "Last Name,"
				+ "Preferred Language,"
				+ "State,"
				+ "Twitter Id,"
				+ "Zip Code," 
				+ "\"Address, line 1\","
				+ "\"Address, line 2\","
				+ "Middle Name"
				);
		boolean unexistedDomain = false;
		boolean unexistedEmail = false;
		
		for (int i = 1; i <= amount; i++) {
			Supporter supporter = new Supporter();
			if (i<=amountOfRealSupporters) {
				supporter.setEmailDomain("." + EmailClient.mbox + "@mailosaur.in");
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
			out.println(supporter.getcPhone() + "," +
					supporter.getCity() + i + "," +
					supporter.getImportedEmail() + "," +
					supporter.getFacebook() + "," +
					supporter.getFirstName() + i + "," +
					supporter.getHome_Phone() + "," +
					supporter.getLastName() + i + "," +
					supporter.getPreferredLanguage() + "," +
					supporter.getState() + "," +
					supporter.getTwitter() + "," +
					supporter.getZipCode() + "," +
					supporter.getAddressLine1() + i + "," +
					supporter.getAddressLine2() + i + "," +
					supporter.getMiddleName() + i
					);
		}
		out.close();
		return new File("supporters.csv").getAbsolutePath();
	}
}
