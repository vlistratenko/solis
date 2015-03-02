package pages.admin;

import core.util.CommonUtils;
import core.util.SeleneseTestCase;
import elements.Button;
import elements.DropDown;
import elements.List;
import elements.TextBox;
import elements.impl.ButtonImpl;
import elements.impl.DropDownImpl;
import elements.impl.ListImpl;
import elements.impl.TextBoxImpl;

public class AddNewOrgPage extends HomePageAdmin{

	TextBox orgName = new TextBoxImpl("//input[@id='name']", "Organization name");
	TextBox orgDescrption = new TextBoxImpl("//input[@id='description']", "Organization Descrption");
	TextBox orgAdminUserId = new TextBoxImpl("//input[@id='userName']", "Admin UserId (Email)");
	TextBox firstName = new TextBoxImpl("//input[@id='firstName']", "First Name");
	TextBox lastName = new TextBoxImpl("//input[@id='lastName']", "Last Name");
	TextBox PhoneNumber = new TextBoxImpl("//input[@id='phone']", "Phone Number");
	DropDown statusType = new DropDownImpl("//div[@id='status_panel']", "//label[@id='status_label']", "Status type");
	TextBox crmID = new TextBoxImpl("//input[@id='crm']", "CRM id");
	TextBox customDomain = new TextBoxImpl("//input[@id='customDomain']", "Custom Domain");
	Button createButton = new ButtonImpl("//button[@id='createOrg']", "Create button");
	List featureList = new ListImpl("//div[@id='feature']", "Features list");
	DropDown classificationType = new DropDownImpl("//div[@id='class_panel']", "//label[@id='class_label']", "Classification type");
	Button sendEmailsTrue = new ButtonImpl("//input[@id='email:1']/ancestor::div[@class='ui-radiobutton ui-widget']//descendant::span", "Send emails");
	Button sendEmailsFalse = new ButtonImpl("//input[@id='email:0']/ancestor::div[@class='ui-radiobutton ui-widget']//descendant::span", "Do not send emails");
	
	
	public OrganizationsListPage createNewOrg(String domainTypeValue,
			String orgNameValue,
			String orgDescrptionValue,
			String orgAdminUserIdValue,
			String firstNameValue,
			String lastNameValue,		
			String statusValue,
			String featureListValue,
			boolean isSendEmails,
			String classificationValue) {
		SeleneseTestCase.bug.add("Create new org.");
		if (isSendEmails) {
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
		crmID.type(CommonUtils.getUnicName());
		createButton.click();
		sleep(30000);
		return new OrganizationsListPage();
	}
}
