package pages.Admin;

import objects.Button;
import objects.DropDown;
import objects.List;
import objects.TextBox;
import selenium.CommonUtils;
import selenium.SeleneseTestCase;

public class AddNewOrgPage extends HomePageAdmin{

	TextBox orgName = new TextBox("//input[@id='mainform:name']", "Organization name");
	TextBox orgDescrption = new TextBox("//input[@id='mainform:desc']", "Organization Descrption");
	TextBox orgAdminUserId = new TextBox("//input[@id='mainform:user']", "Admin UserId (Email)");
	TextBox firstName = new TextBox("//input[@id='mainform:pw1']", "First Name");
	TextBox lastName = new TextBox("//input[@id='mainform:chal']", "Last Name");
	TextBox PhoneNumber = new TextBox("//input[@id='mainform:phone']", "Phone Number");
	DropDown domainType = new DropDown("//div[@id='mainform:domainType_panel']", "//label[@id='mainform:domainType_label']", "Domain type");
	DropDown status = new DropDown("//div[@id='mainform:status_panel']", "//label[@id='mainform:status_label']", "Status");
	TextBox vanityDomain = new TextBox("//input[@id='mainform:vanityDomain']", "Vanity Domain");
	TextBox customDomain = new TextBox("//input[@id='mainform:customDomain']", "Custom Domain");
	Button createButton = new Button("//button[@id='mainform:createOrg']", "Create button");
	List featureList = new List("//div[@id='mainform:feature']", "Features list");
	
	
	public OrganizationsListPage createNewOrg(String domainTypeValue,
			String orgNameValue,
			String orgDescrptionValue,
			String orgAdminUserIdValue,
			String firstNameValue,
			String lastNameValue,		
			String statusValue,
			String featureListValue) {
		SeleneseTestCase.bug.add("Create new org.");
		/*domainType.selectByLabel(domainTypeValue);
		if (domainTypeValue.equalsIgnoreCase("Custom Domain")) {
			customDomain.type(CommonUtils.getUnicName() + ".com");
		}else if (domainTypeValue.equalsIgnoreCase("Vantiy Domain")) {
			vanityDomain.type("vanityDomain.com");
		}*/
		orgName.type(orgNameValue);
		orgDescrption.type(orgDescrptionValue);
		orgAdminUserId.type(orgAdminUserIdValue);
		firstName.type(firstNameValue);
		lastName.type(lastNameValue);
		PhoneNumber.type("23" + CommonUtils.getRandomNumericValueFixedLength(9));
		//status.selectByLabel(statusValue);
		//featureList.selectByLabel(featureListValue);
		createButton.click();
		sleep(30000);
		return new OrganizationsListPage();
	}
}
