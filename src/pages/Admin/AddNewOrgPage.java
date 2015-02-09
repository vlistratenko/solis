package pages.Admin;

import objects.Button;
import objects.DropDown;
import objects.List;
import objects.TextBox;
import selenium.CommonUtils;
import selenium.SeleneseTestCase;

public class AddNewOrgPage extends HomePageAdmin{

	TextBox orgName = new TextBox("//input[@id='name']", "Organization name");
	TextBox orgDescrption = new TextBox("//input[@id='description']", "Organization Descrption");
	TextBox orgAdminUserId = new TextBox("//input[@id='userName']", "Admin UserId (Email)");
	TextBox firstName = new TextBox("//input[@id='firstName']", "First Name");
	TextBox lastName = new TextBox("//input[@id='lastName']", "Last Name");
	TextBox PhoneNumber = new TextBox("//input[@id='phone']", "Phone Number");
	DropDown statusType = new DropDown("//div[@id='status_panel']", "//label[@id='status_label']", "Status type");
	/*Button standardStatus = new Button("//input[@id='status:0']", "Status");
	Button trialStatus = new Button("//input[@id='status:1']", "Status");*/
	TextBox crmID = new TextBox("//input[@id='crm']", "CRM id");
	TextBox customDomain = new TextBox("//input[@id='customDomain']", "Custom Domain");
	Button createButton = new Button("//button[@id='createOrg']", "Create button");
	List featureList = new List("//div[@id='feature']", "Features list");
	DropDown classificationType = new DropDown("//div[@id='class_panel']", "//label[@id='class_label']", "Classification type");
	Button sendEmailsTrue = new Button("//input[@id='email:1']/ancestor::div[@class='ui-radiobutton ui-widget']//descendant::span", "Send emails");
	Button sendEmailsFalse = new Button("//input[@id='email:0']/ancestor::div[@class='ui-radiobutton ui-widget']//descendant::span", "Do not send emails");
	
	
	public OrganizationsListPage createNewOrg(String domainTypeValue,
			String orgNameValue,
			String orgDescrptionValue,
			String orgAdminUserIdValue,
			String firstNameValue,
			String lastNameValue,		
			String statusValue,
			String featureListValue,
			Boolean isSendEmails,
			String classificationValue) {
		SeleneseTestCase.bug.add("Create new org.");
		/*domainType.selectByLabel(domainTypeValue);*/
		if (isSendEmails) {
			//sendEmailsTrue.highlight();
			sendEmailsTrue.click();
		}else{
			sendEmailsFalse.click();
		}
		orgName.type(orgNameValue);
		orgDescrption.type(orgDescrptionValue);
		orgAdminUserId.type(orgAdminUserIdValue);
		firstName.type(firstNameValue);
		lastName.type(lastNameValue);
		PhoneNumber.type("23" + CommonUtils.getRandomNumericValueFixedLength(9));
		statusType.selectByLabel(statusValue);
		classificationType.selectByLabel(classificationValue);
		//featureList.selectByLabel(featureListValue);
		crmID.type(CommonUtils.getUnicName());
		createButton.click();
		sleep(30000);
		return new OrganizationsListPage();
	}
}
