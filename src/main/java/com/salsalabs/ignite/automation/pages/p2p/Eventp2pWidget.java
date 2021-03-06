package com.salsalabs.ignite.automation.pages.p2p;


import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.PropertyName;
import com.salsalabs.ignite.automation.elements.*;
import com.salsalabs.ignite.automation.elements.impl.*;
import com.salsalabs.ignite.automation.pages.hq.activities.DonationWidget;
import com.salsalabs.ignite.automation.pages.hq.activities.EventWidget;
import org.testng.Assert;

public class Eventp2pWidget extends EventWidget {
	TextBox eventPersonFNameField = new TextBoxImpl("//input[contains(@id,'first_name')]", "Event attendees First name", true);
	TextBox eventPersonLNameField = new TextBoxImpl("//input[contains(@id,'last_name')]", "Event attendees Last name", true);
	TextBox eventPersonEmailField = new TextBoxImpl("//input[contains(@id,'email')]", "Event attendees Email", true);
	Label eventSubsrIsSccessMessage = new LabelImpl("//h3[.='Thank You!']", "Event is subscribed");
	Button donateOnlyButton = new ButtonImpl("//a[contains(text(),'Like to Donate')]", "Donate only", true);
	Button registrationButton = new ButtonImpl("//a[.='Register']", "Register", true);
	Button addToCartButton = new ButtonImpl("//*[contains(text(),'Add to Cart')]", "Add to Cart", true);
	Button nextButton = new ButtonImpl("//*[contains(text(),'Next')]", "Next", true);
	SelectBox ticketsQtySelectBox = new SelectBoxImpl("//select[@name='ticket_qty']", "Tickets qty");
	Button checkoutButton = new ButtonImpl("//*[contains(text(), 'Checkout')]", "Checkout", true);
	Button doThisLaterButton = new ButtonImpl("//*[.='Do This Later »']","Do This Later button");
	CheckBox isFundraiserCheckBox = new CheckBoxImpl("//input[@id='yes_register_fundraiser']", "Yes, I want to register as a fundraiser ");
	Button createFundraiserAccountButton = new ButtonImpl("//a[.='Create an Account']", "Create an Account");
	TextBox fundraiserFNameField = new TextBoxImpl("//input[@id='user_first_name']", "Fundraiser First Name", true);
	TextBox fundraiserLNameField = new TextBoxImpl("//input[@id='user_last_name']", "Fundraiser Last Name", true);
	TextBox fundraiserEmailField = new TextBoxImpl("//input[@id='user_email']", "Fundraiser Email", true);
	TextBox fundraiserPasswordField = new TextBoxImpl("//input[@id='user_password']", "Fundraiser Password", true);
	TextBox fundraiserPasswordConfirmationField = new TextBoxImpl("//input[@id='user_password_confirmation']", "Fundraiser Password confirmation", true);
	Button submitFundraiserRegistration = new ButtonImpl("//a[.='Next']", "Submit fundraiser registration", true);
	TextBox fundraiserPageNameField = new TextBoxImpl("//input[@id='fundraiser_stub_name']", "Fundraiser page name", true);
	TextBox fundraiserGoalField = new TextBoxImpl("//input[@id='fundraiser_stub_goal_amount']", "Fundraiser goal", true);
	Button withTeam = new ButtonImpl("//input[@id='yes_join_team']", "With team");
	Button withoutTeam = new ButtonImpl("//input[@id='no_join_team']", "With team");
	TextBox teamName = new TextBoxImpl("//input[@id='fundraiser_stub_team_name']", "Team goal");
	Button fundraiserPageLink = new ButtonImpl("//a[contains(.,'textforreplasment')]", "Fundraiser link ");
	Button teamPageLink = new ButtonImpl("//a[contains(.,'textforreplasment')]", "Team link ");
	Button nextButtonInRegistrationDetailsStep = new ButtonImpl("//*[.='Registration Details']/ancestor::body//*[.='Next »'][contains(@onclick,'submit')]", "Next button in Registration Details step");
	TextBox searchFundriserField = new TextBoxImpl("//label[.='Find a Fundraiser']/following::input", "Find a Fundraiser field");
	TextBox searchFundriserButton = new TextBoxImpl("//label[.='Find a Fundraiser']/following::span[.='Search']", "Search a Fundraiser button");
	TextBox searchTeamField = new TextBoxImpl("//label[.='Find a Team']/following::input", "Find a Team field");
	TextBox searchTeamButton= new TextBoxImpl("//label[.='Find a Team']/following::span[.='Search']", "Search a Team button");
	Tabs leaderboardTab = new TabsImpl("//div[@ignite-p2p-leaderboard='ignite-p2p-leaderboard']", "Leaderboard tabs element");
	Table checkoutSummaryTable = new TableImpl("//table[@class='sli-checkout-summary-table']", "CheckoutSummaryTable");
	Frame frameInRegistrationDetailsStep = new FrameImpl("//iframe[contains(@id, '_ticketFrame')]", "Frame in Registration Details step");
	Frame frameInFundraiserDetailsStep = new FrameImpl("//iframe[@id='tdr_mFAZf']", "Frame in Fundraiser Details step");
	Label iWouldLikeToMakeADonationCheckboxLabel = new LabelImpl("//*[@class='sli-optionalDonation sli-element']","");

	public Eventp2pWidget() {
		super();
	}

	public Eventp2pWidget(boolean clean) {
		super(clean);
	}

	public DonationWidget verifyp2pEventSubscrIsSuccesses() {
		for (int i = 0; i < 10; i++) {
			if (waitConditionBecomesTrue(eventSubsrIsSccessMessage.isDisplayed(), 10)) {
				break;
			}
		}
		verifier.verifyElementIsDisplayed(eventSubsrIsSccessMessage);
		return this;
	}

	public Eventp2pWidget openp2pDonationPage() {
		// TODO Auto-generated method stub
		super.openDonationPage();
		return this;
	}

	public Eventp2pWidget openp2pEventRegistrationPage() {
		// TODO Auto-generated method stub
		super.openEventRegistrationPage();
		return this;
	}

	public Eventp2pWidget selectFundraiserCheckBox(Boolean isFundraiser) {
		switchToFrame("//iframe[contains(@id, '_ticketFrame')]");
		isFundraiserCheckBox.waitElement();
		isFundraiserCheckBox.check(isFundraiser);
		sleep(3);
		switchDefaultContent();
		return this;
	}

	public Eventp2pWidget addRegistrationToCart() {
		switchToFrame("//iframe[contains(@id, '_ticketFrame')]");
		addToCartButton.waitElement();
		addToCartButton.click();
		switchDefaultContent();
		return this;
	}

	public Eventp2pWidget selectRegistrationType() {
		return this;
	}

	public Eventp2pWidget clickNextButtonOnRegistrationTypesPage () {
		switchToFrame("//iframe[contains(@id, '_ticketFrame')]");
		addToCartButton.click();
		sleep(3);
		nextButton.click();
		addToCartButton.click();
		switchDefaultContent();
		switchToFrame("//iframe[contains(@id, '_ticketFrame')]");
		nextButton.fluentWaitForElementPresenceIgnoringExceptions();
		nextButton.click();
		switchDefaultContent();
		return this;
	}

	public Eventp2pWidget fillFundraiserSignInForm (String fundraiserFName, String fundraiserLName, String fundraiserEmail,
													String fundraiserPassword, String fundraiserPasswordConfirmation, boolean isWithTeam) {
		switchToFrame("//iframe[contains(@id, '_ticketFrame')]");
		if (!createFundraiserAccountButton.waitElement(5)) {
			new ButtonImpl("//div[@class='checkout_logout']/a", "Fundraiser logout link").click();
		}
		createFundraiserAccountButton.waitElement();
		createFundraiserAccountButton.click();
		fundraiserPasswordField.fluentWaitForElementPresenceIgnoringExceptions();
		fundraiserPasswordField.type(fundraiserPassword);
		fundraiserPasswordConfirmationField.type(fundraiserPasswordConfirmation);
		if (isWithTeam) {
			withTeam.click();
			CommonUtils.setParam("teamName", fundraiserFName + "." + fundraiserLName + "Team." + CommonUtils.getUnicName());
			teamName.type(CommonUtils.getParam(PropertyName.LAST_TEAM_NAME));
		}else{
			withoutTeam.click();
		}
		sleep(3);
		switchDefaultContent();
		return this;
	}

	/**
	 * This method fill Fundraiser form with random selection "Create team" option
	 * @param fundraiserFName
	 * @param fundraiserLName
	 * @param fundraiserEmail
	 * @param fundraiserPassword
	 * @param fundraiserPasswordConfirmation
	 * @return
	 */
	public Eventp2pWidget fillFundraiserSignInForm (String fundraiserFName, String fundraiserLName, String fundraiserEmail,
													String fundraiserPassword, String fundraiserPasswordConfirmation) {

		return fillFundraiserSignInForm(fundraiserFName,
				fundraiserLName,
				fundraiserEmail,
				fundraiserPassword,
				fundraiserPasswordConfirmation,
				CommonUtils.getRandomBoolean());
	}

	public Eventp2pWidget fillp2pEventRegistrationForm(String personEmail, String personFName, String personLName){
		switchToFrame("//iframe[contains(@id, '_ticketFrame')]");
		sleep(3);
		eventPersonEmailField.type(personEmail);
		eventPersonFNameField.type(personFName);
		eventPersonLNameField.type(personLName);
		sleep(3);
		switchDefaultContent();
		return this;
	}

	public Eventp2pWidget clickCheckOutButton(){
		switchToFrame("//iframe[contains(@id, '_ticketFrame')]");
		nextButton.click();
		switchDefaultContent();
		switchToFrame("//iframe[contains(@id, '_ticketFrame')]");
		checkoutButton.click();
		switchDefaultContent();
		checkoutSummaryTable.fluentWaitForElementPresenceIgnoringExceptions();
		return this;
	}

	public Eventp2pWidget clickOnGoToCheckoutButton(){
		sleep(2);
		frameInRegistrationDetailsStep.swithToFrameWithFluentWait(10);
		checkoutButton.fluentWaitForElementPresenceIgnoringExceptions();
		checkoutButton.click();
		switchDefaultContent();
		return this;
	}

	/*public Eventp2pWidget fillp2pEventRegistrationFormForAttendeeAndFundraiser(String personEmail, String personFName, String personLName){
		switchToFrame("//iframe[contains(@id, '_ticketFrame')]");
		fundraiserPageNameField.type(personFName + "." + personLName + CommonUtils.getUnicName());
		fundraiserGoalField.type("1000");
		
		Boolean isWithTeam = CommonUtils.getRandomBoolean();
		if (isWithTeam) {
			withTeam.click();
			teamGoal.type("500");
		}else{
			withoutTeam.click();
		}
		eventPersonEmailField.type(personEmail);
		eventPersonFNameField.type(personFName);
		eventPersonLNameField.type(personLName);
		sleep(3);
		switchDefaultContent();
		return this;
	}*/

	public Eventp2pWidget selectQtyOfAttendee(){
		switchToFrame("//iframe[contains(@id, '_ticketFrame')]");
		ticketsQtySelectBox.selectByLabel("1");
		sleep(3);
		switchDefaultContent();
		return this;
	}

	public Eventp2pWidget fillp2pEventDonationForm(String personEmail,
												   String personFName,
												   String personLName,
												   String personAddressLine1,
												   String personCity,
												   String personZip,
												   String personState,
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
		isEvent = true;
		fillDonationForm(personEmail, personFName, personLName, personAddressLine1, "", personCity, personZip, personState, false, donationAmount, nameOnCard, cardNumber, cvv, expiryMonth, expiryYear, isFundraising, isNewsletter, isEmail);
		isEvent = false;
		return this;
	}

	public EventWidget clickDonationButton() {
		super.clickDonationButton();
		return this;
	}

	public Eventp2pWidget checkDisplayDonationAnonymouslyOption(boolean isChecked) {
		displayDonationAnonymouslyOptionCheckBox.check(isChecked);
		return this;
	}

	public Eventp2pWidget selectLeaderboardTab(String tabLabel) {
		leaderboardTab.selectTab(tabLabel);
		return this;
	}

	public EventFundraiserWidgetPage clickFundraiserLinkInLeaderboard (String fundraiserFLname) {
		selectLeaderboardTab("Top Individuals");
		fundraiserPageLink.changePath("textforreplasment", fundraiserFLname);
		fundraiserPageLink.click();
		return new EventFundraiserWidgetPage();
	}

	public EventFundraiserWidgetPage findFundraiserViaSearchFieldAndClick (String fundraiserFLname) {
		searchFundriserField.scrollIntoView();
		searchFundriserField.type(fundraiserFLname);
		searchFundriserButton.click();
		fundraiserPageLink.changePath("textforreplasment", fundraiserFLname);
		fundraiserPageLink.waitElement();
		fundraiserPageLink.click();
		return new EventFundraiserWidgetPage();
	}

	public EventTeamWidgetPage clickTeamLinkInLeaderboard (String fundraiserFLname) {
		selectLeaderboardTab("Top Teams");
		teamPageLink.changePath("textforreplasment", fundraiserFLname);
		teamPageLink.click();
		return new EventTeamWidgetPage();
	}

	public EventTeamWidgetPage findTeamViaSearchFieldAndClick (String teamName) {
		searchTeamField.scrollIntoView();
		searchTeamField.type(teamName);
		searchTeamButton.click();
		teamPageLink.changePath("textforreplasment", teamName);
		teamPageLink.waitElement();
		teamPageLink.click();
		return new EventTeamWidgetPage();
	}

	public Eventp2pWidget clickDoThisLaterButton(){
		sleep(2);
		frameInRegistrationDetailsStep.swithToFrameWithFluentWait(10);
		doThisLaterButton.fluentWaitForElementPresenceIgnoringExceptions();
		doThisLaterButton.click();
		switchDefaultContent();
		return this;
	}

	public Eventp2pWidget clickNextButtonOnRegistrationDetailsStep(){
		sleep(2);
		frameInRegistrationDetailsStep.swithToFrameWithFluentWait(10);
		nextButtonInRegistrationDetailsStep.fluentWaitForElementPresenceIgnoringExceptions();
		nextButtonInRegistrationDetailsStep.click();
		switchDefaultContent();
		return this;
	}

	public String getIWouldLikeToMakeADonationCheckboxLabelActualText(){
		iWouldLikeToMakeADonationCheckboxLabel.fluentWaitForElementPresenceIgnoringExceptions();
		return iWouldLikeToMakeADonationCheckboxLabel.getText();
	}

	public void verifyDefaultGeneralIWouldLikeToMakeADonationCheckboxLabel(){
		Assert.assertEquals(getIWouldLikeToMakeADonationCheckboxLabelActualText(), "I would like to make a donation");
	}

	public void verifyDefaultFundraiserIWouldLikeToMakeADonationCheckboxLabel(){
		Assert.assertEquals(getIWouldLikeToMakeADonationCheckboxLabelActualText(), "I would like to kick off my fundraising efforts with my own contribution");
	}

	public void verifyNewGeneralIWouldLikeToMakeADonationCheckboxLabel(){
		Assert.assertEquals(getIWouldLikeToMakeADonationCheckboxLabelActualText(), CommonUtils.getProperty("IWouldLikeToMakeADonationCheckboxGeneralLabel"));
	}

	public void verifyNewFundraiserIWouldLikeToMakeADonationCheckboxLabel(){
		Assert.assertEquals(getIWouldLikeToMakeADonationCheckboxLabelActualText(), CommonUtils.getProperty("IWouldLikeToMakeADonationCheckboxFundraiserLabel"));
	}
}
