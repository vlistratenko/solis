package pages.HQ.Manage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import objects.Button;
import objects.DropDown;
import objects.Supporter;
import objects.Table;
import objects.TextBox;
import selenium.CommonUtils;
import selenium.EmailClient;

public class ImportAddPage extends ManagePage{
	Button importLink = new Button("//a[@href='/#/settings/imports']", "Import link");
	
	//firs step
	TextBox importNameField = new TextBox("//input[@id='name']", "Import Name", true);
	TextBox importDescriptionField = new TextBox("//textarea[@id='description']", "Import description", false);
	TextBox fileUpload = new TextBox("//input[@id='fileUpload']", "Logo", false);
	Button nextStepButton = new Button("//button[@id='btnDoUpload']/*", "Match My Fields");
	
	//second step
	TextBox importFromRowField = new TextBox("//input[@id='offset']", " My data starts on row", false);
	Table mapTable = new Table("//form[@id='importForm']/descendant::table", "Map");
	Button dedupeButton = new Button("//button[@id='btnSave2']/*", "Match My Fields");
	
	//I'm done step
	Button doneButton = new Button("//button[@id='btnSave3']/*", "Done button");
	
	public ImportAddPage fillFirstStep(String name, String description) {
		name = name + CommonUtils.getUnicName();
		importNameField.type(name);
		importDescriptionField.type(description);
		
		fileUpload.setAttribute("class", "ng-pristine ng-valid");
		try {
			fileUpload.type(generateSupporters(50, 20));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		nextStepButton.click();
		CommonUtils.setProperty("ImportName", name);
		sleep(15000);
		return this;
	}
	
	public ImportAddPage fillSecondStep(String importFromRow) {
		importFromRowField.type(importFromRow);
		Integer amountOfFields = mapTable.getRowsCount();
		
		for (int i = 1; i <= amountOfFields; i++) {
			String fieldName = mapTable.getCallValue(i, 1);
			String pathToDropDown = mapTable.getPathToChildElement(i, 2, "div[contains(@class, 'custom dropdown')]");
			DropDown mapToField = new DropDown(pathToDropDown, pathToDropDown + "/a", "MapToField");
			mapToField.selectByLabelJS(fieldName);
			
		}
		dedupeButton.click();
		return this;
	}
	
	public ImportAddPage fillThirdStep() {
		doneButton.click();
		sleep(30000);
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
				+ "Email,"
				+ "Facebook,"
				+ "First Name,"
				+ "Home Phone,"
				+ "Last Name,"
				+ "Preferred Language,"
				+ "State,"
				+ "Twitter,"
				+ "Zip Code," 
				+ "\"Address, line 1\","
				+ "\"Address, line 2\","
				+ "Middle Name"
				);
		Boolean unexistedDomain = false;
		Boolean unexistedEmail = false;
		
		for (int i = 1; i <= amount; i++) {
			Supporter supporter = new Supporter();
			if (i<=amountOfRealSupporters) {
				supporter.emailDomain = "." + EmailClient.mbox + "@mailosaur.in";
			}else{
				supporter.emailDomain = "@devnull.test.ignite.net";
			}
			supporter.Email = supporter.Email + i + supporter.emailDomain;//".3e41c646@mailosaur.in";//"@salsalabs.com";//"@devnull.test.ignite.net";
			if (!unexistedDomain && i >= amountOfRealSupporters) {
				supporter.Email = "unexisting@unexisting.dom";
				unexistedDomain = true;
			}else if (!unexistedEmail && i >= amountOfRealSupporters) {
				supporter.Email = "unexisting@igniteaction.net";
				unexistedEmail = true;
			}
			out.println(supporter.cPhone + "," +
					supporter.City + i + "," +
					supporter.Email + i + "," +
					supporter.Facebook + "," +
					supporter.First_Name + i + "," +
					supporter.Home_Phone + "," +
					supporter.Last_Name + i + "," +
					supporter.PreferredLanguage + "," +
					supporter.State + "," +
					supporter.Twitter + "," +
					supporter.Zip_Code + "," +
					supporter.AddressLine1 + i + "," +
					supporter.AddressLine2 + i + "," +
					supporter.MiddleName + i
					);
		}
		out.close();
		return new File("supporters.csv").getAbsolutePath();
	}
}
