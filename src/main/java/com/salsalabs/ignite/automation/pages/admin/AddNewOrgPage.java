package com.salsalabs.ignite.automation.pages.admin;

import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.DropDown;
import com.salsalabs.ignite.automation.elements.List;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.DropDownImpl;
import com.salsalabs.ignite.automation.elements.impl.ListImpl;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;

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
	DropDown productType = new DropDownImpl("(//div[contains(@class,'ui-selectonemenu')])[last()]", "(//label[contains(@class,'ui-selectonemenu-label')])[last()]", "Product type");
	
	public OrganizationsListPage createNewOrg(
			String orgNameValue,
			String orgDescrptionValue,
			String orgAdminUserIdValue,
			String firstNameValue,
			String lastNameValue,		
			String statusValue,
			boolean isSendEmails,
			String classificationValue,
			String product) {
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
		crmID.type("13111111111");
		productType.selectByLabel(product);
		createButton.click();
		sleep(5);
		return new OrganizationsListPage();
	}
}
