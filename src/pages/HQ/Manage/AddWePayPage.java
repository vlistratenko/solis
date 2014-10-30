package pages.HQ.Manage;

import objects.Button;
import objects.DropDown;
import objects.TextBox;
import pages.HQ.HomePage;
import selenium.CommonUtils;

public class AddWePayPage extends HomePage{

	TextBox nickName = new TextBox("//input[@name='nickname']", "NickName", true);
	TextBox description = new TextBox("//textarea[@id='description']", "Description", true);
	DropDown orgType = new DropDown("//custom-select2[@data='organizationTypes']/div", "//custom-select2[@data='organizationTypes']/div/a", "Org type");//nonprofit
	TextBox firstName = new TextBox("//input[@name='firstName']", "First name", true);
	TextBox lastName = new TextBox("//input[@name='lastName']", "Last name", true);
	TextBox email = new TextBox("//input[@name='email']", "email", true);
	Button submitButton = new Button("//button[@id='btnSubmit']", "Create My Gateway");
	
	public PaymentGatewaysPage createWePayAcount(String wePayNickName, String wePayDescr, String wePayOrgType) {
		nickName.type(wePayNickName);
		description.type(wePayDescr);
		orgType.selectByLabelJS(wePayOrgType);
		email.type(CommonUtils.getProperty("Admin.email"));
		firstName.type(CommonUtils.getProperty("Admin.firstName"));
		lastName.type(CommonUtils.getProperty("Admin.lastName"));
		submitButton.click();
		sleep(10000);
		return new PaymentGatewaysPage();
	}
}
