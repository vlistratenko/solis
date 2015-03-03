package pages.hq.activities;

import core.util.Browser;
import core.util.CommonUtils;
import core.util.PropertyName;
import elements.Button;
import elements.CheckBox;
import elements.Label;
import elements.SelectBox;
import elements.TextBox;
import elements.impl.ButtonImpl;
import elements.impl.CheckBoxImpl;
import elements.impl.LabelImpl;
import elements.impl.SelectBoxImpl;
import elements.impl.TextBoxImpl;

public class DonationWidget extends Browser{
	TextBox personEmailField = new TextBoxImpl("//input[@name='PersonContact@Email@Value']", "Email", true);
	TextBox personFNameField = new TextBoxImpl("//input[@name='PersonCensus@FirstName']", "First name", true);
	TextBox personLNameField = new TextBoxImpl("//input[@name='PersonCensus@LastName']", "Last name", true);
	TextBox personAddressLine1Field = new TextBoxImpl("//input[@name='Address@Home@Line1']", "Address line 1", true);
	TextBox personAddressLine2Field = new TextBoxImpl("//input[@name='Address@Home@Line2']", "Address line 2", true);
	TextBox personCityField = new TextBoxImpl("//input[@name='Address@Home@City']", "City", true);
	TextBox personZipField = new TextBoxImpl("//input[@name='Address@Home@Zip']", "Zip", true);
	SelectBox personStatesSelectBox = new SelectBoxImpl("//select[@name='Address@Home@State']", "States");
	CheckBox recurringDonationCheckBox = new CheckBoxImpl("//div[contains(text(), 'Make this donation recurring')]/input", "Recurring Donation");
	Label donationAmountLabel = new LabelImpl("//label[text()='$20.00']/preceding-sibling::input", "Donation amount");

	TextBox nameOnCardField = new TextBoxImpl("//input[@id='name_on_card']", "Name on Card", true);
	TextBox cardNumberField = new TextBoxImpl("//input[@id='card_number']", "Card number", true);
	TextBox cvvField = new TextBoxImpl("//input[@id='cvv']", "CVV", true);
	TextBox expiryMonthField = new TextBoxImpl("//input[@id='expiry_month']", "Expiry Month", true);
	TextBox expiryYearField = new TextBoxImpl("//input[@id='expiry_year']", "Expiry Year", true);
	
	CheckBox FundraisingCheckBox = new CheckBoxImpl("//div[contains(text(), 'Fundraising')]/input", "Fundraising");
	CheckBox NewsletterCheckBox = new CheckBoxImpl("//div[contains(text(), 'Newsletter')]/input", "Newsletter");
	CheckBox emailCheckBox = new CheckBoxImpl("//label[contains(text(), 'Email')]/preceding-sibling::input", "Email");
	Button donateButton = new ButtonImpl("//input[@value='Donate!']", "Donate", true);
	
	Label donationIsSccessMessage = new LabelImpl("//div[.='Thank you for your donation!']", "Donation is success");
	
	public DonationWidget openDonationWidgetLink() {
		String currentWindowHandle = getWindowHandle();
		open(CommonUtils.getProperty(PropertyName.DONATION_WIDGET_LINK));	
		switchToPopupWindow(currentWindowHandle);
		CommonUtils.setProperty(PropertyName.CURRENT_WINDOW_HANDLE, currentWindowHandle);
		return this;
	}
	
	
	public DonationWidget fillDonationForm(String personEmail,
			String personFName,
			String personLName,
			String personAddressLine1,
			String personAddressLine2,
			String personCity,
			String personZip,
			boolean recurringDonation,
			String donationAmount,
			String nameOnCard,
			String cardNumber,
			String cvv,
			String expiryMonth,
			String expiryYear,			
			boolean isFundraising,
			boolean isNewsletter,
			boolean isEmail) 
	{
		personEmailField.type(personEmail);
		personFNameField.type(personFName);
		personLNameField.type(personLName);
		personAddressLine1Field.type(personAddressLine1);
		personAddressLine2Field.type(personAddressLine2);
		personCityField.type(personCity);
		personZipField.type(personZip);
		personStatesSelectBox.selectByIndex(Integer.parseInt(CommonUtils.getRandomValueFromTo(1, 50, 0)));
		recurringDonationCheckBox.check(recurringDonation);
		donationAmountLabel.changePath("$20", donationAmount);
		donationAmountLabel.click();
		nameOnCardField.type(nameOnCard);
		cardNumberField.type(cardNumber);
		cvvField.type(cvv);
		expiryMonthField.type(expiryMonth);
		expiryYearField.type(expiryYear);
		
		FundraisingCheckBox.check(isFundraising);
		NewsletterCheckBox.check(isNewsletter);
		emailCheckBox.check(isEmail);
		
		return this;
	}
	
	public DonationWidget clickDonationButton() {
		donateButton.click();
		return this;
	}
	
	public DonationWidget verifyDonationIsSuccesses() {
		for (int i = 0; i < 10; i++) {
			if (waitConditionBecomesTrue(donationIsSccessMessage.isDisplayed(), 10)) {
				break;
			}
		}
		verifier.verifyElementIsDisplayed(donationIsSccessMessage);
		return this;
	}
	
	public AddDonationWidgetPage backToFundraisingWidgetPage() {
		closeWindow();
		switchToWindow(CommonUtils.getProperty(PropertyName.CURRENT_WINDOW_HANDLE));
		return new AddDonationWidgetPage();
	}
}

