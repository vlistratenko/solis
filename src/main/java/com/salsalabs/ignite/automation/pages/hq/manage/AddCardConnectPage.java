package com.salsalabs.ignite.automation.pages.hq.manage;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.DropDown;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.DropDownImpl;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;
import com.salsalabs.ignite.automation.pages.hq.HomePage;
import org.apache.commons.lang3.RandomUtils;

public class AddCardConnectPage extends HomePage{
	String[] orgTypes = {"US Dollar", "Canadian Dollar"};
	TextBox nickName = new TextBoxImpl("//input[@name='nickname']", "NickName", true);
	TextBox description = new TextBoxImpl("//textarea[@id='description']", "Description", true);
	DropDown currency = new DropDownImpl("//custom-select2[@data='currencyCodes']/div", "//custom-select2[@data='currencyCodes']/div/a", "Currency code");//nonprofit
	TextBox merchantId = new TextBoxImpl("//input[@name='merchantId']", "Merchant ID", true);
	TextBox doesBusinsessAs = new TextBoxImpl("//input[@name='dbaTitle']", "Does Business As", true);
	Button submitButton = new ButtonImpl("//button[@id='btnSubmit']", "Create My Gateway");
	
	public PaymentGatewaysPage createCardConnectAcount(String name, String descr, String curr) {
		nickName.type(name);
		description.type(descr);
		currency.selectByLabelJS(curr);
		merchantId.type("496344295024");
		doesBusinsessAs.type("AAAAAAA");
		submitButton.click();
		sleep(10);
		return new PaymentGatewaysPage();
	}
	
	public String chooseRandomCurrency() {
		return this.orgTypes[RandomUtils.nextInt(0, this.orgTypes.length)];
	}
}
