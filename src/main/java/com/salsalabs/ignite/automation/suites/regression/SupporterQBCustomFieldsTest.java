package com.salsalabs.ignite.automation.suites.regression;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import org.json.JSONException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.salsalabs.ignite.automation.common.HttpClient;
import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;
import com.salsalabs.ignite.automation.pages.hq.supporters.SupporterQueryBuilderPage;

public class SupporterQBCustomFieldsTest extends SeleneseTestCase{
	public static final String supporterExpectedEmail = "qb_sup.74580786@mailosaur.in";
	public static final String customFieldsRule = "Custom Fields";
	public static final String customFieldsExtendedButtonLAbel = "SupTextBox";
	public static final String supporterTextBoxCustomField = "SupTextBox";
	public static final String supporterNumberCustomField = "SupNumber";
	public static final String supporterYesNoCustomField = "SupYesNo";
	public static final String supporterDateTime = "SupDateTime";
	public static final String supporterSingleChoice = "SupSingleChoice";
	
	  // custom fields - Text Box
	  @Parameters({"login", "Passward"})
	  @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"CustomFields"})
	  public void customFieldTextBoxEqualsTest(String login, String passward){
		  doLoginAndOpenSupporterQbPage(login, passward ).
		  pickUpFirstLevelRuleOption(customFieldsRule).
		  pickUpNextLevelRuleOption(supporterTextBoxCustomField, customFieldsExtendedButtonLAbel).
		  pickUpTheRuleOperator("equals").
		  enterValue("text").
		  checkErrorMessage().
		  checkShowResultButtonIsDisplayed().
		  clickShowTheResults().
		  checkErrorMessage().
		  checkSupporterEmailInTable(supporterExpectedEmail).
		  checkNumberOfRecordsInTable("1").
		  checkThatShowResultBottonIsNotDisplayedIfSearchValueIsCleared();
	  }
	
	  //custom fields - Text Box
	  @Parameters({"login", "Passward"})
	  @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"CustomFields"})
	  public void customFieldTextBoxNotEqualsTest(String login, String passward){
		  String numerOfExpectedRecordsInTable= String.valueOf(getListOfAllSupportersIOrg(login, passward));
		  doLoginAndOpenSupporterQbPage(login, passward ).
		  pickUpFirstLevelRuleOption(customFieldsRule).
		  pickUpNextLevelRuleOption(supporterTextBoxCustomField, customFieldsExtendedButtonLAbel).
		  pickUpTheRuleOperator("not equals").
		  enterValue("bla").
		  checkErrorMessage().
		  checkShowResultButtonIsDisplayed().
		  clickShowTheResults().
		  checkErrorMessage().
		  checkNumberOfRecordsInTable(numerOfExpectedRecordsInTable);
	  }
	
	      // custom fields - Text Box
		  @Parameters({"login", "Passward"})
		  @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"CustomFields"})
		  public void customFieldTextBoxContainsTest(String login, String passward){
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(customFieldsRule).
			  pickUpNextLevelRuleOption(supporterTextBoxCustomField, customFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("contains").
			  enterValue("te").
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage().
			  checkSupporterEmailInTable(supporterExpectedEmail).
			  checkNumberOfRecordsInTable("1");
		  }
		  
		  //custom fields - Text Box
		  @Parameters({"login", "Passward"})
		  @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"CustomFields"})
		  public void customFieldTextBoxDoesNotContainTest(String login, String passward){
			  String numerOfExpectedRecordsInTable= String.valueOf(getListOfAllSupportersIOrg(login, passward));
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(customFieldsRule).
			  pickUpNextLevelRuleOption(supporterTextBoxCustomField, customFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("does not contain").
			  enterValue("bla").
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage().
			  checkNumberOfRecordsInTable(numerOfExpectedRecordsInTable);
		  }
		  
		  //custom fields - Text Box
		  @Parameters({"login", "Passward"})
		  @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"CustomFields"})
		  public void customFieldTextBoxStartsWithTest(String login, String passward){
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(customFieldsRule).
			  pickUpNextLevelRuleOption(supporterTextBoxCustomField, customFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("starts with").
			  enterValue("te").
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage().
			  checkSupporterEmailInTable(supporterExpectedEmail).
			  checkNumberOfRecordsInTable("1");
		  }
		  
		  //custom fields - Text Box
		  @Parameters({"login", "Passward"})
		  @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"CustomFields"})
		  public void  customFieldTextBoxrIsBlank(String login, String passward){
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(customFieldsRule).
			  pickUpNextLevelRuleOption(supporterTextBoxCustomField, customFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("is blank").
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage().
			  checkNoResultsIsReturned();
		  }
		  
		 //custom fields - Text Box
		  @Parameters({"login", "Passward"})
		  @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"CustomFields"})
		  public void  customFieldTextBoxrIsNotBlank(String login, String passward){
			  String numerOfExpectedRecordsInTable= String.valueOf(getListOfAllSupportersIOrg(login, passward));
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(customFieldsRule).
			  pickUpNextLevelRuleOption(supporterTextBoxCustomField, customFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("is not blank").
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage().
			  checkNumberOfRecordsInTable(numerOfExpectedRecordsInTable);
		  }
		  
		 //custom fields - Number
		  @Parameters({"login", "Passward"})
		  @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"CustomFields"})
		  public void customFieldNumberEqualsTest(String login, String passward){
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(customFieldsRule).
			  pickUpNextLevelRuleOption(supporterNumberCustomField, customFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("equals").
			  enterValue("805").
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage().
			  checkSupporterEmailInTable(supporterExpectedEmail).
			  checkNumberOfRecordsInTable("1").
			  checkThatShowResultBottonIsNotDisplayedIfSearchValueIsCleared();
		  }
		  
		//custom fields - Number 
		  @Parameters({"login", "Passward"})
		  @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"CustomFields"})
		  public void customFieldNumberNotEqualsTest(String login, String passward){
			  String numerOfExpectedRecordsInTable= String.valueOf(getListOfAllSupportersIOrg(login, passward));
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(customFieldsRule).
			  pickUpNextLevelRuleOption(supporterNumberCustomField, customFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("not equals").
			  enterValue("8094").
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage().
			  checkNumberOfRecordsInTable(numerOfExpectedRecordsInTable);
		  }
		  
			// custom fields - Number
		  @Parameters({"login", "Passward"})
		  @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"CustomFields"})
		  public void customFieldLessaThanTest(String login, String passward){
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(customFieldsRule).
			  pickUpNextLevelRuleOption(supporterNumberCustomField, customFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("less than").
			  enterValue("806").
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage().
			  checkSupporterEmailInTable(supporterExpectedEmail).
			  checkNumberOfRecordsInTable("1");
		  }
		  
			// custom fields - Number
		  @Parameters({"login", "Passward"})
		  @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"CustomFields"})
		  public void customFieldLessOrEqualsTest(String login, String passward){
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(customFieldsRule).
			  pickUpNextLevelRuleOption(supporterNumberCustomField, customFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("less or equals").
			  enterValue("805").
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage().
			  checkSupporterEmailInTable(supporterExpectedEmail).
			  checkNumberOfRecordsInTable("1");
		  }
		  
			// custom fields - Number
		  @Parameters({"login", "Passward"})
		  @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"CustomFields"})
		  public void customFieldGreaterThanTest(String login, String passward){
			  String numerOfExpectedRecordsInTable= String.valueOf(getListOfAllSupportersIOrg(login, passward));
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(customFieldsRule).
			  pickUpNextLevelRuleOption(supporterNumberCustomField, customFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("greater than").
			  enterValue("1").
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage().
			 checkNumberOfRecordsInTable(numerOfExpectedRecordsInTable);
		  }
		  
		// custom fields - Number
		  @Parameters({"login", "Passward"})
		  @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"CustomFields"})
		  public void customFieldGreaterOrEqualsTest(String login, String passward){
			  String numerOfExpectedRecordsInTable= String.valueOf(getListOfAllSupportersIOrg(login, passward));
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(customFieldsRule).
			  pickUpNextLevelRuleOption(supporterNumberCustomField, customFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("greater or equals").
			  enterValue("805").
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage().
			 checkNumberOfRecordsInTable(numerOfExpectedRecordsInTable);
		  }
		  
		// custom fields - YesNO
		  @Parameters({"login", "Passward"})
		  @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"CustomFields"})
		  public void customFieldYesNoTest(String login, String passward){
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(customFieldsRule).
			  pickUpNextLevelRuleOption(supporterYesNoCustomField, customFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("is").
			  pickUpValueWhichComesAfterRuleOperator("True").
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage().
			  checkSupporterEmailInTable(supporterExpectedEmail).	
			  checkNumberOfRecordsInTable("1");
		  }
		  
			// custom fields - YesNO
		  @Parameters({"login", "Passward"})
		  @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"CustomFields"})
		  public void customFieldYesNoIsBlankTest(String login, String passward){
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(customFieldsRule).
			  pickUpNextLevelRuleOption(supporterYesNoCustomField, customFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("is blank").
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage().
			  checkNoResultsIsReturned();
		  }
		  
			// custom fields - YesNO
		  @Parameters({"login", "Passward"})
		  @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"CustomFields"})
		  public void customFieldYesNoIsNotBlankTest(String login, String passward){
			  String numerOfExpectedRecordsInTable= String.valueOf(getListOfAllSupportersIOrg(login, passward));
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(customFieldsRule).
			  pickUpNextLevelRuleOption(supporterYesNoCustomField, customFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("is not blank").
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage().
			  checkNumberOfRecordsInTable(numerOfExpectedRecordsInTable);
		  }
		  
		// custom fields - DateTime
		  @Parameters({"login", "Passward"})
		  @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"CustomFields"})
		  public void customFieldDateBeforeTest(String login, String passward){
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(customFieldsRule).
			  pickUpNextLevelRuleOption(supporterDateTime, customFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("before").  
			  pickUpCalendarOperator("Calendar" ).
			  specifyCalendarValue("11/29/2000" , null, null).
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage().
			  checkSupporterEmailInTable(supporterExpectedEmail).	
		  	  checkNumberOfRecordsInTable("1");
		  }
		
		// custom fields - DateTime
		  @Parameters({"login", "Passward"})
		  @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"CustomFields"})
		  public void customFieldDateOnOrBeforeTest(String login, String passward){
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(customFieldsRule).
			  pickUpNextLevelRuleOption(supporterDateTime, customFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("on or before").  
			  pickUpCalendarOperator("Calendar" ).
			  specifyCalendarValue("05/08/1990" , null, null).
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage().
			  checkSupporterEmailInTable(supporterExpectedEmail).	
		  	  checkNumberOfRecordsInTable("1");
		  }
		  
			// custom fields - DateTime
		  @Parameters({"login", "Passward"})
		  @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"CustomFields"})
		  public void customFieldDateTimeAfterTest(String login, String passward){
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(customFieldsRule).
			  pickUpNextLevelRuleOption(supporterDateTime, customFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("after").  
			  pickUpCalendarOperator("Calendar" ).
			  specifyCalendarValue("06/03/2021" , null, null).
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage().
			  checkNoResultsIsReturned();
		  }
		  
			// custom fields - DateTime
		  @Parameters({"login", "Passward"})
		  @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"CustomFields"})
		  public void customFieldDateTimeOnOrAfterTest(String login, String passward){
			  String numerOfExpectedRecordsInTable= String.valueOf(getListOfAllSupportersIOrg(login, passward));
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(customFieldsRule).
			  pickUpNextLevelRuleOption(supporterDateTime, customFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("on or after").  
			  pickUpCalendarOperator("Calendar" ).
			  specifyCalendarValue("05/08/1990" , null, null).
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage().
			  checkNumberOfRecordsInTable(numerOfExpectedRecordsInTable);
		  }
		  
			// custom fields - DateTime
		  @Parameters({"login", "Passward"})
		  @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"CustomFields"})
		  public void customFieldDateTimeBetweenTest(String login, String passward){
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(customFieldsRule).
			  pickUpNextLevelRuleOption(supporterDateTime, customFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("between").  
			  specifyCalendarValue(null , "05/07/1990", "05/10/1990").
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage(). 
			  checkSupporterEmailInTable(supporterExpectedEmail).
			  checkNumberOfRecordsInTable("1").
			  checkThatShowResultBottonIsNotDisplayedIfSearchCalendarValueIsCleared(true);
		  }
		  
			// custom fields - DateTime
		  @Parameters({"login", "Passward"})
		  @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"CustomFields"})
		  public void customFieldDateTimeExactlyTest(String login, String passward){
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(customFieldsRule).
			  pickUpNextLevelRuleOption(supporterDateTime, customFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("exactly").  
			  pickUpCalendarOperator("Calendar" ).
			  specifyCalendarValue("05/08/1990" , null, null).
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage().
			  checkSupporterEmailInTable(supporterExpectedEmail).	
		  	  checkNumberOfRecordsInTable("1");
		  }
		  
		// custom fields - DateTime
		  @Parameters({"login", "Passward"})
		  @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"CustomFields"})
		  public void customFieldDateTimeDuringThisTest(String login, String passward){
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(customFieldsRule).
			  pickUpNextLevelRuleOption(supporterDateTime, customFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("during this").
			  pickUpDatePeriod("Day(s)").
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage().
			  checkNoResultsIsReturned();
		  }
		  
			// custom fields - DateTime
		  @Parameters({"login", "Passward"})
		  @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"CustomFields"})
		  public void customFieldDateTimeNotDuringThisTest(String login, String passward){
			  String numerOfExpectedRecordsInTable= String.valueOf(getListOfAllSupportersIOrg(login, passward));
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(customFieldsRule).
			  pickUpNextLevelRuleOption(supporterDateTime, customFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("not during this").
			  pickUpDatePeriod("Day(s)").
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage().
			  checkNumberOfRecordsInTable(numerOfExpectedRecordsInTable);
		  }
		  
			// custom fields - DateTime
		  @Parameters({"login", "Passward"})
		  @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"CustomFields"})
		  public void customFieldDateTimeEverTest(String login, String passward){
			  String numerOfExpectedRecordsInTable= String.valueOf(getListOfAllSupportersIOrg(login, passward));
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(customFieldsRule).
			  pickUpNextLevelRuleOption(supporterDateTime, customFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("ever").
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage().
			  checkNumberOfRecordsInTable(numerOfExpectedRecordsInTable);
		  }
		  
		  //custom fields - Single Choice
		  @Parameters({"login", "Passward"})
		  @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"CustomFields"})
		  public void customFieldSingleChoiceTest(String login, String passward){
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(customFieldsRule).
			  pickUpNextLevelRuleOption(supporterSingleChoice, customFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("is").
			  pickUpValueWhichComesAfterRuleOperator("option1").
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage().
			  checkSupporterEmailInTable(supporterExpectedEmail).	
			  checkNumberOfRecordsInTable("1");
		  }
		  
			// custom fields - Single Choice
		  @Parameters({"login", "Passward"})
		  @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"CustomFields"})
		  public void customFieldSingleChoiceIsNotBlankTest(String login, String passward){
			  String numerOfExpectedRecordsInTable= String.valueOf(getListOfAllSupportersIOrg(login, passward));
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(customFieldsRule).
			  pickUpNextLevelRuleOption(supporterSingleChoice, customFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("is not blank").
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage().
			  checkNumberOfRecordsInTable(numerOfExpectedRecordsInTable);
		  }
		  
			// custom fields - Single Choice
		  @Parameters({"login", "Passward"})
		  @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"CustomFields"})
		  public void customFieldSingleChoiceIsBlankTest(String login, String passward){
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(customFieldsRule).
			  pickUpNextLevelRuleOption(supporterSingleChoice, customFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("is blank").
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage().
			  checkNoResultsIsReturned();
		  }
		  
			// custom fields - Single Choice1
		  @Parameters({"login", "Passward"})
		  @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"CustomFields"})
		  public void customFieldSingleChoiceIsNotOneOFTest(String login, String passward){
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(customFieldsRule).
			  pickUpNextLevelRuleOption(supporterSingleChoice, customFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("is one of").
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage().
			  checkSupporterEmailInTable(supporterExpectedEmail).	
			  checkNumberOfRecordsInTable("1").
			  checkThatShowResultBottonIsNotDisplayedIfSelectOptionIsCleared();
		  }
		  
		  
	 private SupporterQueryBuilderPage doLoginAndOpenSupporterQbPage(String login , String passward){
		  if(new SupporterQueryBuilderPage().newRuleButton.isNotExists()){
			  new LoginPage().doSuccessLogin(login, passward).openAudiencePage().openSupporterQueryBuilderPage().createNewQuery();
		  }else {
			  return new SupporterQueryBuilderPage();
		  }
		  return new SupporterQueryBuilderPage();   
	  }
	  
	  private int getListOfAllSupportersIOrg(String userName, String pass){
		 int numberOfSupportersInOrg = 0;
		try {
			numberOfSupportersInOrg = new HttpClient(SeleneseTestCase.USED_ENVIRONMENT.getBaseTestUrl()).login(userName, pass).getNumberOfSupportersInOrg();
		} catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException | JSONException
				| URISyntaxException | IOException e) {
			e.printStackTrace();
		}
		 return numberOfSupportersInOrg; 
	  }

}
