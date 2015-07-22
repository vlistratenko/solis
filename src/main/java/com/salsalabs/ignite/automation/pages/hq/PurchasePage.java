package com.salsalabs.ignite.automation.pages.hq;

import org.apache.commons.lang3.RandomUtils;

import com.salsalabs.ignite.automation.common.Browser;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.DropDown;
import com.salsalabs.ignite.automation.elements.Label;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.DropDownImpl;
import com.salsalabs.ignite.automation.elements.impl.LabelImpl;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;

public class PurchasePage extends Browser {

	protected final static String[] sizes = { "up to 1,000 supporters", "up to 2,500 supporters", "up to 5,000 supporters" };
	protected final static String[] billings = { "Good Value", "Better Value", "Best Value" };

	DropDown selectListSize = new DropDownImpl("//custom-select2[@out='selectedListSize']", "//custom-select2[@out='selectedListSize']/div/a", "List Size");
	TextBox cardNumberInput = new TextBoxImpl("//input[@id='cardNumber']", "Card Number");
	TextBox cvvInput = new TextBoxImpl("//input[@id='cvv2']", "CVV2");
	TextBox nameCardInput = new TextBoxImpl("//input[@name='nameOnCard']", "Card Name");
	DropDown selectMonth = new DropDownImpl("//custom-select[@data='monthList']", "//custom-select[@data='monthList']/div/a", "Month");
	DropDown selectYear = new DropDownImpl("//custom-select[@data='curYearList']", "//custom-select[@data='curYearList']/div/a", "Year");
	Button purchase = new ButtonImpl("(//button[contains(@ng-click,'doPurchaseSubmit')])", "Purchase");
	Label success = new LabelImpl("//p[contains(text(), 'Your credit card has been successfully charged. ')]", "Success");
	
	public PurchasePage selectListSize() {
		selectListSize.selectByLabelJS(chooseRandomListSize());
		sleep(5);
		return this;
	}
	
	public PurchasePage chooseBillingType() {
		String type = billings[RandomUtils.nextInt(0, 3)];
		Button chooseBillingType = new ButtonImpl("(//div[text()='" + type + "'])", "Billing Type: " + type);
		chooseBillingType.click();
		return this;
	}
	
	public PurchasePage enterCreditCardInfo(String cardNumber, String cvv, String name){
		cardNumberInput.type(cardNumber);
		cvvInput.type(cvv);
		nameCardInput.type(name);
		selectMonth.selectByLabelJS("01");
		selectYear.selectByLabelJS("2020");
		return this;
	}
	
	public HomePage purchase(){
		purchase.click();
		sleep(10);
		verifier.verifyElementIsDisplayed(true, success);
		return new HomePage();
	}

	protected String chooseRandomListSize() {
		return sizes[RandomUtils.nextInt(0, sizes.length)];
	}
}
