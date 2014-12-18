package pages.HQ.Activities;

import objects.Browser;
import objects.Button;
import objects.CheckBox;
import objects.Label;
import objects.SelectBox;
import objects.TextBox;
import selenium.CommonUtils;

public class DonationWidget extends Browser{
	TextBox personEmailField = new TextBox("//input[@name='PersonContact@Email@Value']", "Email", true);
	TextBox personFNameField = new TextBox("//input[@name='PersonCensus@FirstName']", "First name", true);
	TextBox personLNameField = new TextBox("//input[@name='PersonCensus@LastName']", "Last name", true);
	TextBox personAddressLine1Field = new TextBox("//input[@name='Address@Home@Line1']", "Address line 1", true);
	TextBox personAddressLine2Field = new TextBox("//input[@name='Address@Home@Line2']", "Address line 2", true);
	TextBox personCityField = new TextBox("//input[@name='Address@Home@City']", "City", true);
	TextBox personZipField = new TextBox("//input[@name='Address@Home@Zip']", "Zip", true);
	SelectBox personStatesSelectBox = new SelectBox("//select[@name='Address@Home@State']", "States");
	CheckBox recurringDonationCheckBox = new CheckBox("//div[contains(text(), 'Make this donation recurring')]/input", "Recurring Donation");
	Label donationAmountLabel = new Label("//label[text()='$20.00']/preceding-sibling::input", "Donation amount");

	TextBox nameOnCardField = new TextBox("//input[@id='name_on_card']", "Name on Card", true);
	TextBox cardNumberField = new TextBox("//input[@id='card_number']", "Card number", true);
	TextBox cvvField = new TextBox("//input[@id='cvv']", "CVV", true);
	TextBox expiryMonthField = new TextBox("//input[@id='expiry_month']", "Expiry Month", true);
	TextBox expiryYearField = new TextBox("//input[@id='expiry_year']", "Expiry Year", true);
	
	CheckBox FundraisingCheckBox = new CheckBox("//div[contains(text(), 'Fundraising')]/input", "Fundraising");
	CheckBox NewsletterCheckBox = new CheckBox("//div[contains(text(), 'Newsletter')]/input", "Newsletter");
	CheckBox emailCheckBox = new CheckBox("//label[contains(text(), 'Email')]/preceding-sibling::input", "Email");
	Button donateButton = new Button("//input[@value='Donate!']", "Donate", true);
	
	Label donationIsSccessMessage = new Label("//div[.='Thank you for your donation!']", "Donation is success");
	
	public DonationWidget openDonationWidgetLink() {
		String currentWindowHandle = getWindowHandle();
		open(CommonUtils.getProperty("donationWidgetLink"));	
		switchToPopupWindow(currentWindowHandle);
		CommonUtils.setProperty("currentWindowHandle", currentWindowHandle);
		return this;
	}
	
	
	public DonationWidget fillDonationForm(String personEmail,
			String personFName,
			String personLName,
			String personAddressLine1,
			String personAddressLine2,
			String personCity,
			String personZip,
			Boolean recurringDonation,
			String donationAmount,
			String nameOnCard,
			String cardNumber,
			String cvv,
			String expiryMonth,
			String expiryYear,			
			Boolean isFundraising,
			Boolean isNewsletter,
			Boolean isEmail) 
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
			if (waitConditionBecomesTrue(donationIsSccessMessage.isDisplayed(), 10000)) {
				break;
			}
		}
		verify(donationIsSccessMessage.isDisplayed(), true, "Message that donation success, is not displayed");
		return this;
	}
	
	public AddDonationWidgetPage backToFundraisingWidgetPage() {
		closeWindow();
		switchToWindow(CommonUtils.getProperty("currentWindowHandle"));
		return new AddDonationWidgetPage();
	}
}

