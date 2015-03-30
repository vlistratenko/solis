package com.salsalabs.ignite.automation.pages.hq.manage;

import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.PropertyName;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.DropDown;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.DropDownImpl;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;
import com.salsalabs.ignite.automation.pages.hq.HomePage;

public class AddWePayPage extends HomePage{

	TextBox nickName = new TextBoxImpl("//input[@name='nickname']", "NickName", true);
	TextBox description = new TextBoxImpl("//textarea[@id='description']", "Description", true);
	DropDown orgType = new DropDownImpl("//custom-select2[@data='organizationTypes']/div", "//custom-select2[@data='organizationTypes']/div/a", "Org type");//nonprofit
	TextBox firstName = new TextBoxImpl("//input[@name='firstName']", "First name", true);
	TextBox lastName = new TextBoxImpl("//input[@name='lastName']", "Last name", true);
	TextBox email = new TextBoxImpl("//input[@name='email']", "email", true);
	Button submitButton = new ButtonImpl("//button[@id='btnSubmit']", "Create My Gateway");
	
	public PaymentGatewaysPage createWePayAcount(String wePayNickName, String wePayDescr, String wePayOrgType) {
		nickName.type(wePayNickName);
		description.type(wePayDescr);
		orgType.selectByLabelJS(wePayOrgType);
		email.type(CommonUtils.getProperty(PropertyName.ADMIN_EMAIL));
		firstName.type(CommonUtils.getProperty(PropertyName.ADMIN_FIRST_NAME));
		lastName.type(CommonUtils.getProperty(PropertyName.ADMIN_LAST_NAME));
		submitButton.click();
		sleep(10);
		return new PaymentGatewaysPage();
	}
}
