package com.salsalabs.ignite.automation.pages.hq.manage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;

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
import com.salsalabs.ignite.automation.pages.hq.manage.CustomFieldsPage.CustomFieldType;

public class ImportAddPage extends ManagePage{
	Button importLink = new ButtonImpl("//a[@href='/#/audience/supporters/imports']", "Import link");
	
	//first step
	TextBox importNameField = new TextBoxImpl("//input[@id='name']", "Import Name", true);
	TextBox importDescriptionField = new TextBoxImpl("//textarea[@id='description']", "Import description", false);
	TextBox fileUpload = new TextBoxImpl("//input[@name='fileUpload']", "Input for file upload", false);
	Button nextStepButton = new ButtonImpl("//*[contains(@id, 'btnSave')]", "Next: Import Settings >>");
	Button doneProgressBar = new ButtonImpl("//div[contains(text(), 'Done')]","Progress bar with done status");
	Button iAmDoneButton = new ButtonImpl("//button[@id='btnSave3']","I am done button");
	Button backMapsFields = new ButtonImpl("//span[contains(text(), 'Back: Map Fields')]","Back Maps Fields");
	Button dowloadAuditFile = new ButtonImpl("//a[contains(text(), ' Download your audit file now')]","Download audit file");
	

	
	//second step
	TextBox importFromRowField = new TextBoxImpl("//input[@id='offset']", " My data starts on row", false);
	Table mapTable = new TableImpl("//form[@id='importForm']/descendant::table", "Map");
	
	private List<CustomField> customFields;
	public static  String textBoxMappingValue= RandomStringUtils.randomAlphabetic(5);
	public static  String numberMappingValue= 	CommonUtils.getRandomNumericValueFixedLength(5);
	public static  String singleChoiceMappingValue= "1choice";
	public static  String dateTimePMappingValue =CommonUtils.createTodayDateString();
	public static  String  boleanOptionValue ="False";
	
	
	public ImportAddPage fillFirstStep(String name, String description) {
		return this.fillFirstStep(name, description, null);
	}
	
	public ImportAddPage fillFirstStep(String name, String description, List<CustomField> customFields) {
		this.customFields = customFields;  
		importNameField.type(name);
		importDescriptionField.type(description);
		fileUpload.setAttribute("class", "text");
		fileUpload.scrollIntoView();
		try {
			fileUpload.type(generateSupporters(10, customFields));
		} catch (FileNotFoundException e) {
			SeleneseTestCase.logger.error("",e);
		}
		for (int i = 0; i < 5; i++) {
			if(waitConditionBecomesTrue(doneProgressBar.isDisplayed(), 5)){
				break;
				}
			}
		nextStepButton.click();
		CommonUtils.setProperty(PropertyName.IMPORT_NAME, name);
		sleep(2); 
		return this;
	}
	
	public ImportAddPage fillSecondStep(String importFromRow) {
		for (int i = 0; i < 5; i++) {
			if(waitConditionBecomesTrue(importFromRowField.isDisplayed(), 5)){
				break;
				}
			}
		importFromRowField.type(importFromRow);
		if (customFields != null) {
			for (int i = 1; i <= customFields.size(); i++) {
				String pathToDropDown = getpathToDropDownForCustomFieldMapping(customFields.get(i - 1).getType());
				DropDown mapToField = new DropDownImpl(pathToDropDown, "MapToField");
				DropDown scrollElement = new DropDownImpl(pathToDropDown.replace("/parent::div/parent::div/following-sibling::div", ""), "Element for scrolling");
				scrollElement.scrollIntoView();
				mapToField.moveAndClick();
				mapToField.selectByLabel(customFields.get(i - 1).getName());
			}
		}
		nextStepButton.click();
		return this;
	}
	
	private String getpathToDropDownForCustomFieldMapping(CustomFieldType type){
		String pathToDropDown=null;
		switch (type) {
		case TextBox:
			pathToDropDown= "//div[contains(text(), '"+textBoxMappingValue+"')]/parent::div/parent::div/following-sibling::div";
			break;
		case Number:
			pathToDropDown= "//div[contains(text(), '"+numberMappingValue+"')]/parent::div/parent::div/following-sibling::div";
			break;
		case SingleChoice:
			pathToDropDown= "//div[contains(text(), '"+singleChoiceMappingValue+"')]/parent::div/parent::div/following-sibling::div";
			break;
			
		case YesNo:
			pathToDropDown= "//div[contains(text(), '"+boleanOptionValue+"')]/parent::div/parent::div/following-sibling::div";
			break;
		case DateTime:
			pathToDropDown= "//div[contains(text(), '"+dateTimePMappingValue+"')]/parent::div/parent::div/following-sibling::div";
			break;
		default:
			break;
		}
		
		return pathToDropDown;
	}
	
	public ImportAddPage fillThirdStep() {
		for (int i = 0; i<6  ; i++) {
			if(waitConditionBecomesTrue(backMapsFields.isDisplayed(), 5)){
				break;
				}
			}
		iAmDoneButton.click();
		sleep(2);
		Label label = new LabelImpl("//p[contains(text(), 'complete!')]", "Import done");
		for (int i = 0; i < 5; i++) {
			if(waitConditionBecomesTrue(label.isDisplayed(), 5) & waitConditionBecomesTrue(dowloadAuditFile.isDisplayed(), 3) )
				break;	
		}
		sleep(2);
		return this;
	}
	
	public ImportPage openImportPage() {
		importLink.click();
		return new ImportPage();
	}
	
	public String generateSupporters(Integer amount) throws FileNotFoundException {
		return generateSupporters(amount,  null);
	}
	
	public String generateSupporters(Integer amount, List<CustomField> customFields) throws FileNotFoundException {
		PrintWriter out = new PrintWriter("supporters.csv");
		StringBuilder header = new StringBuilder();
		header.append("Cell Phone,City,Email Address,Facebook Id, First Name,Home Phone,Last Name,Preferred Language, External Id, Date of Birth, ");
		header.append("State,Twitter Id,Zip Code,\"Address, line 1\",\"Address, line 2\",Middle Name");
		if (customFields != null) {
			for (CustomField type : customFields) {
				header.append(",").append(type.getName());
			}
		}
		out.println(header.toString());
	
		Supporter supporter = Supporter.generateSupporter();
		
	
		for (int i = 1; i <= amount; i++) {
			
			StringBuilder row = new StringBuilder();
			row.append(supporter.getcPhone()).append(",");  
			row.append(supporter.getCity()).append(","); 
			String  emailUniqueValue = "imp" + RandomStringUtils.randomAlphabetic(4) +".74580786@mailosaur.in";
			row.append(emailUniqueValue).append(",");
			row.append(supporter.getFacebook()).append(",");
			row.append(supporter.getFirstName()).append(",");
			row.append(supporter.getHome_Phone()).append(",");
			row.append(supporter.getLastName()).append(",");
			row.append(supporter.getPreferredLanguage()).append(",");
			String  externalId = RandomStringUtils.randomNumeric(6);
			row.append(externalId).append(",");
			row.append(supporter.getBirthdate()).append(",");
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
					row.append(textBoxMappingValue);
					break;
				}
				case Number: {
					row.append(numberMappingValue);
					break;
				}
				case SingleChoice: {
					row.append(singleChoiceMappingValue);
					break;
				}
				
				case DateTime: {
					row.append(dateTimePMappingValue);
					break;
				}
				
				case YesNo: {
					row.append(boleanOptionValue);
					break;
				}
				
				default: { break; }
			}
		}
	}
}
