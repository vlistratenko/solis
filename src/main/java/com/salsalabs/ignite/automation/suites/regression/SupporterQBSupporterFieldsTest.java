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

public class SupporterQBSupporterFieldsTest  extends SeleneseTestCase{
	public static final String supporterExpectedEmail = "qb_sup.74580786@mailosaur.in";
	public static final String SupporterFieldsRule = "Supporter Fields";
	public static final String SupporterFieldsExtendedButtonLAbel = "Internal ID";
	public static final String internalId = "Internal ID";
	public static final String externalId = "External ID";
	public static final String firstName = "First Name";
	public static final String lastName = "Last Name";
	public static final String middleName = "Middle Name";
	public static final String dateOfBirth = "Date of Birth";
	public static final String suffix = "Suffix";
	public static final String title = "Title";
	public static final String city = "City";
	public static final String preferedLanguage = "Preferred Language";
	public static final String addressLine = "Address Line 1";
	public static final String zipCode = "Zip Code";
	public static final String email = "Email";
	public static final String gender = "Gender";
	public static final String workPhone = "Work Phone";
	public static final String cellPhone = "Cell Phone";
	public static final String homePhone = "Home Phone";
	public static final String country = "Country";
	public static final String state = "State";
	
	//SupporterFieldsRule > internalId Tests
	  @Parameters({"login", "Passward"})
	    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
	  public void supporterInternalIdEqualsTest(String login, String passward){
		  doLoginAndOpenSupporterQbPage(login, passward ).
		  pickUpFirstLevelRuleOption(SupporterFieldsRule).
		  pickUpNextLevelRuleOption(internalId, SupporterFieldsExtendedButtonLAbel).
		  pickUpTheRuleOperator("equals").
		  enterValue("c2ada12d-9384-44d3-a3a9-665cd809d882").
		  checkErrorMessage().
		  checkShowResultButtonIsDisplayed().
		  clickShowTheResults().
		  checkErrorMessage().
		  checkSupporterEmailInTable(supporterExpectedEmail).
		  checkNumberOfRecordsInTable("1").
		  checkThatShowResultBottonIsNotDisplayedIfSearchValueIsCleared();
	  }
	
	//SupporterFieldsRule > externalId Tests
	  @Parameters({"login", "Passward"})
	    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
	  public void supporterExternalIdEqualsTest(String login, String passward){
		  doLoginAndOpenSupporterQbPage(login, passward ).
		  pickUpFirstLevelRuleOption(SupporterFieldsRule).
		  pickUpNextLevelRuleOption(externalId, SupporterFieldsExtendedButtonLAbel).
		  pickUpTheRuleOperator("equals").
		  enterValue("78136").
		  checkErrorMessage().
		  checkShowResultButtonIsDisplayed().
		  clickShowTheResults().
		  checkErrorMessage().
		  checkSupporterEmailInTable(supporterExpectedEmail).
		  checkNumberOfRecordsInTable("1").
		  checkThatShowResultBottonIsNotDisplayedIfSearchValueIsCleared();
	  }
	  
	//SupporterFieldsRule > externalId Tests
	  @Parameters({"login", "Passward"})
	    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
	  public void supporterExternalIdNotEqualsTest(String login, String passward){
		  String numerOfExpectedRecordsInTable= String.valueOf(getListOfAllSupportersIOrg(login, passward));
		  doLoginAndOpenSupporterQbPage(login, passward ).
		  pickUpFirstLevelRuleOption(SupporterFieldsRule).
		  pickUpNextLevelRuleOption(externalId, SupporterFieldsExtendedButtonLAbel).
		  pickUpTheRuleOperator("not equals").
		  enterValue("910144").
		  checkErrorMessage().
		  checkShowResultButtonIsDisplayed().
		  clickShowTheResults().
		  checkErrorMessage(). 
		  checkNumberOfRecordsInTable(numerOfExpectedRecordsInTable);
	  }
	  
	//SupporterFieldsRule > externalId Tests
	  @Parameters({"login", "Passward"})
	    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
	  public void supporterExternalIdContainsTest(String login, String passward){
		  doLoginAndOpenSupporterQbPage(login, passward ).
		  pickUpFirstLevelRuleOption(SupporterFieldsRule).
		  pickUpNextLevelRuleOption(externalId, SupporterFieldsExtendedButtonLAbel).
		  pickUpTheRuleOperator("contains").
		  enterValue("78136").
		  checkErrorMessage().
		  checkShowResultButtonIsDisplayed().
		  clickShowTheResults().
		  checkErrorMessage().
		  checkSupporterEmailInTable(supporterExpectedEmail).
		  checkNumberOfRecordsInTable("1");
	  }
	  
	//SupporterFieldsRule > externalId Tests
	  @Parameters({"login", "Passward"})
	    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
	  public void supporterExternalIdStartsWith(String login, String passward){
		  doLoginAndOpenSupporterQbPage(login, passward ).
		  pickUpFirstLevelRuleOption(SupporterFieldsRule).
		  pickUpNextLevelRuleOption(externalId, SupporterFieldsExtendedButtonLAbel).
		  pickUpTheRuleOperator("starts with").
		  enterValue("78").
		  checkErrorMessage().
		  checkShowResultButtonIsDisplayed().
		  clickShowTheResults().
		  checkErrorMessage().
		  checkSupporterEmailInTable(supporterExpectedEmail).
		  checkNumberOfRecordsInTable("1");
	  }
	  
	//SupporterFieldsRule > externalId Tests
	  @Parameters({"login", "Passward"})
	    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
	  public void supporterExternalIdIsNotBlank(String login, String passward){
		  String numerOfExpectedRecordsInTable= String.valueOf(getListOfAllSupportersIOrg(login, passward));
		  doLoginAndOpenSupporterQbPage(login, passward ).
		  pickUpFirstLevelRuleOption(SupporterFieldsRule).
		  pickUpNextLevelRuleOption(externalId, SupporterFieldsExtendedButtonLAbel).
		  pickUpTheRuleOperator("is not blank").
		  checkErrorMessage().
		  checkShowResultButtonIsDisplayed().
		  clickShowTheResults().
		  checkErrorMessage().
		  checkNumberOfRecordsInTable(numerOfExpectedRecordsInTable);
	  }
	  
	//SupporterFieldsRule > externalId Tests
	  @Parameters({"login", "Passward"})
	    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
	  public void supporterExternalIdIsBlank(String login, String passward){
		  doLoginAndOpenSupporterQbPage(login, passward ).
		  pickUpFirstLevelRuleOption(SupporterFieldsRule).
		  pickUpNextLevelRuleOption(externalId, SupporterFieldsExtendedButtonLAbel).
		  pickUpTheRuleOperator("is blank").
		  checkErrorMessage().
		  checkShowResultButtonIsDisplayed().
		  clickShowTheResults().
		  checkErrorMessage();
	  }
	  
	//SupporterFieldsRule > firstName Tests
	  @Parameters({"login", "Passward"})
	    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
	  public void supporterFirstNameEqualsTest(String login, String passward){
		  doLoginAndOpenSupporterQbPage(login, passward ).
		  pickUpFirstLevelRuleOption(SupporterFieldsRule).
		  pickUpNextLevelRuleOption(firstName, SupporterFieldsExtendedButtonLAbel).
		  pickUpTheRuleOperator("equals").
		  enterValue("fnqb").
		  checkErrorMessage().
		  checkShowResultButtonIsDisplayed().
		  clickShowTheResults().
		  checkErrorMessage().
		  checkSupporterEmailInTable(supporterExpectedEmail).
		  checkNumberOfRecordsInTable("1").
		  checkThatShowResultBottonIsNotDisplayedIfSearchValueIsCleared();
	  }
	  
		//SupporterFieldsRule > firstName Tests
	  @Parameters({"login", "Passward"})
	    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
	  public void supporterFirstNameNotEqualsTest(String login, String passward){
		  String numerOfExpectedRecordsInTable= String.valueOf(getListOfAllSupportersIOrg(login, passward));
		  doLoginAndOpenSupporterQbPage(login, passward ).
		  pickUpFirstLevelRuleOption(SupporterFieldsRule).
		  pickUpNextLevelRuleOption(firstName, SupporterFieldsExtendedButtonLAbel).
		  pickUpTheRuleOperator("not equals").
		  enterValue("blablaName").
		  checkErrorMessage().
		  checkShowResultButtonIsDisplayed().
		  clickShowTheResults().
		  checkErrorMessage().
		  checkNumberOfRecordsInTable(numerOfExpectedRecordsInTable);
	  }
	  
		//SupporterFieldsRule > firstName Tests
	  @Parameters({"login", "Passward"})
	    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
	  public void supporterFirstNameDoesNotContainsTest(String login, String passward){
		  String numerOfExpectedRecordsInTable= String.valueOf(getListOfAllSupportersIOrg(login, passward));
		  doLoginAndOpenSupporterQbPage(login, passward ).
		  pickUpFirstLevelRuleOption(SupporterFieldsRule).
		  pickUpNextLevelRuleOption(firstName, SupporterFieldsExtendedButtonLAbel).
		  pickUpTheRuleOperator("does not contain").
		  enterValue("blablaName").
		  checkErrorMessage().
		  checkShowResultButtonIsDisplayed().
		  clickShowTheResults().
		  checkErrorMessage(). 
		  checkNumberOfRecordsInTable(numerOfExpectedRecordsInTable);
	  }
	  
		//SupporterFieldsRule > firstName Tests
	  @Parameters({"login", "Passward"})
	    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
	  public void supporterFirstNameStartsWith(String login, String passward){
		  doLoginAndOpenSupporterQbPage(login, passward ).
		  pickUpFirstLevelRuleOption(SupporterFieldsRule).
		  pickUpNextLevelRuleOption(firstName, SupporterFieldsExtendedButtonLAbel).
		  pickUpTheRuleOperator("starts with").
		  enterValue("fnqb").
		  checkErrorMessage().
		  checkShowResultButtonIsDisplayed().
		  clickShowTheResults().
		  checkErrorMessage().
		  checkSupporterEmailInTable(supporterExpectedEmail).
		  checkNumberOfRecordsInTable("1").
		  checkThatShowResultBottonIsNotDisplayedIfSearchValueIsCleared();
	  }
	  
	//SupporterFieldsRule > firstName Tests
	  @Parameters({"login", "Passward"})
	    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
	  public void supporterFirstNameIsNotBlank(String login, String passward){
		  String numerOfExpectedRecordsInTable= String.valueOf(getListOfAllSupportersIOrg(login, passward));
		  doLoginAndOpenSupporterQbPage(login, passward ).
		  pickUpFirstLevelRuleOption(SupporterFieldsRule).
		  pickUpNextLevelRuleOption(firstName, SupporterFieldsExtendedButtonLAbel).
		  pickUpTheRuleOperator("is not blank").
		  checkErrorMessage().
		  checkShowResultButtonIsDisplayed().
		  clickShowTheResults().
		  checkErrorMessage().
		  checkNumberOfRecordsInTable(numerOfExpectedRecordsInTable);
	  }
	  
	//SupporterFieldsRule > firstName Tests
	  @Parameters({"login", "Passward"})
	    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
	  public void supporterFirstNameIsBlank(String login, String passward){
		  doLoginAndOpenSupporterQbPage(login, passward ).
		  pickUpFirstLevelRuleOption(SupporterFieldsRule).
		  pickUpNextLevelRuleOption(firstName, SupporterFieldsExtendedButtonLAbel).
		  pickUpTheRuleOperator("is blank").
		  checkErrorMessage().
		  checkShowResultButtonIsDisplayed().
		  clickShowTheResults().
		  checkErrorMessage();
	  }
	  
	//SupporterFieldsRule > gender Tests
	  @Parameters({"login", "Passward"})
	    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
	  public void supporterGenderIsFemale(String login, String passward){
		  doLoginAndOpenSupporterQbPage(login, passward ).
		  pickUpFirstLevelRuleOption(SupporterFieldsRule).
		  pickUpNextLevelRuleOption(gender, SupporterFieldsExtendedButtonLAbel).
		  pickUpTheRuleOperator("is").
		  pickUpValueWhichComesAfterRuleOperator("Female").
		  checkErrorMessage().
		  checkShowResultButtonIsDisplayed().
		  clickShowTheResults().
		  checkErrorMessage().
		  checkNoResultsIsReturned();
	  }
	  
	//SupporterFieldsRule > gender Tests
	  @Parameters({"login", "Passward"})
	    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
	  public void supporterGenderIsMale(String login, String passward){
		  doLoginAndOpenSupporterQbPage(login, passward ).
		  pickUpFirstLevelRuleOption(SupporterFieldsRule).
		  pickUpNextLevelRuleOption(gender, SupporterFieldsExtendedButtonLAbel).
		  pickUpTheRuleOperator("is").
		  pickUpValueWhichComesAfterRuleOperator("Male").
		  checkErrorMessage().
		  checkShowResultButtonIsDisplayed().
		  clickShowTheResults().
		  checkErrorMessage().
		  checkNoResultsIsReturned();
	  }
	  
		//SupporterFieldsRule > gender Tests
	  @Parameters({"login", "Passward"})
	    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
	  public void supporterGenderIsNotOther(String login, String passward){
		  doLoginAndOpenSupporterQbPage(login, passward ).
		  pickUpFirstLevelRuleOption(SupporterFieldsRule).
		  pickUpNextLevelRuleOption(gender, SupporterFieldsExtendedButtonLAbel).
		  pickUpTheRuleOperator("is not").
		  pickUpValueWhichComesAfterRuleOperator("Other").
		  checkErrorMessage().
		  checkShowResultButtonIsDisplayed().
		  clickShowTheResults().
		  checkErrorMessage().
		  checkNoResultsIsReturned();
	  }
	  
		//SupporterFieldsRule > gender Tests
	  @Parameters({"login", "Passward"})
	    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
	  public void supporterGenderIsBlank(String login, String passward){
		  String numerOfExpectedRecordsInTable= String.valueOf(getListOfAllSupportersIOrg(login, passward));
		  doLoginAndOpenSupporterQbPage(login, passward ).
		  pickUpFirstLevelRuleOption(SupporterFieldsRule).
		  pickUpNextLevelRuleOption(gender, SupporterFieldsExtendedButtonLAbel).
		  pickUpTheRuleOperator("is blank").
		  checkErrorMessage().
		  checkShowResultButtonIsDisplayed().
		  clickShowTheResults().
		  checkErrorMessage().
		  checkNumberOfRecordsInTable(numerOfExpectedRecordsInTable);
	  }
	  
	//SupporterFieldsRule > lastName Tests
	  @Parameters({"login", "Passward"})
	    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
	  public void supporterLastNameEqualsTest(String login, String passward){
		  doLoginAndOpenSupporterQbPage(login, passward ).
		  pickUpFirstLevelRuleOption(SupporterFieldsRule).
		  pickUpNextLevelRuleOption(lastName, SupporterFieldsExtendedButtonLAbel).
		  pickUpTheRuleOperator("equals").
		  enterValue("lnqb").
		  checkErrorMessage().
		  checkShowResultButtonIsDisplayed().
		  clickShowTheResults().
		  checkErrorMessage().
		  checkSupporterEmailInTable(supporterExpectedEmail).
		  checkNumberOfRecordsInTable("1");
	  }
	  
		//SupporterFieldsRule > lastName Tests
	  @Parameters({"login", "Passward"})
	    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
	  public void supporterLastNameNotEqualsTest(String login, String passward){
		  String numerOfExpectedRecordsInTable= String.valueOf(getListOfAllSupportersIOrg(login, passward));
		  doLoginAndOpenSupporterQbPage(login, passward ).
		  pickUpFirstLevelRuleOption(SupporterFieldsRule).
		  pickUpNextLevelRuleOption(lastName, SupporterFieldsExtendedButtonLAbel).
		  pickUpTheRuleOperator("not equals").
		  enterValue("blablaName").
		  checkErrorMessage().
		  checkShowResultButtonIsDisplayed().
		  clickShowTheResults().
		  checkErrorMessage().
		  checkNumberOfRecordsInTable(numerOfExpectedRecordsInTable).
		  checkThatShowResultBottonIsNotDisplayedIfSearchValueIsCleared();
	  }
	  
		//SupporterFieldsRule > lastName Tests
	  @Parameters({"login", "Passward"})
	    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
	  public void supporterLastNameContainsTest(String login, String passward){
		  String numerOfExpectedRecordsInTable= String.valueOf(getListOfAllSupportersIOrg(login, passward));
		  doLoginAndOpenSupporterQbPage(login, passward ).
		  pickUpFirstLevelRuleOption(SupporterFieldsRule).
		  pickUpNextLevelRuleOption(lastName, SupporterFieldsExtendedButtonLAbel).
		  pickUpTheRuleOperator("does not contain").
		  enterValue("blablaName").
		  checkErrorMessage().
		  checkShowResultButtonIsDisplayed().
		  clickShowTheResults().
		  checkErrorMessage(). 
		  checkNumberOfRecordsInTable(numerOfExpectedRecordsInTable);
	  }
	  
		//SupporterFieldsRule > lastName Tests
	  @Parameters({"login", "Passward"})
	    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
	  public void supporterLastNameStartsWith(String login, String passward){
		  doLoginAndOpenSupporterQbPage(login, passward ).
		  pickUpFirstLevelRuleOption(SupporterFieldsRule).
		  pickUpNextLevelRuleOption(lastName, SupporterFieldsExtendedButtonLAbel).
		  pickUpTheRuleOperator("starts with").
		  enterValue("lnqb").
		  checkErrorMessage().
		  checkShowResultButtonIsDisplayed().
		  clickShowTheResults().
		  checkErrorMessage().
		  checkSupporterEmailInTable(supporterExpectedEmail).
		  checkNumberOfRecordsInTable("1");
	  }
	  
	//SupporterFieldsRule > lastName Tests
	  @Parameters({"login", "Passward"})
	    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
	  public void supporterLastNameIsNotBlank(String login, String passward){
		  String numerOfExpectedRecordsInTable= String.valueOf(getListOfAllSupportersIOrg(login, passward));
		  doLoginAndOpenSupporterQbPage(login, passward ).
		  pickUpFirstLevelRuleOption(SupporterFieldsRule).
		  pickUpNextLevelRuleOption(lastName, SupporterFieldsExtendedButtonLAbel).
		  pickUpTheRuleOperator("is not blank").
		  checkErrorMessage().
		  checkShowResultButtonIsDisplayed().
		  clickShowTheResults().
		  checkErrorMessage().
		  checkNumberOfRecordsInTable(numerOfExpectedRecordsInTable);
	  }
	  
	//SupporterFieldsRule > lastName Tests
	  @Parameters({"login", "Passward"})
	    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
	  public void supporterLastNameIsBlank(String login, String passward){
		  doLoginAndOpenSupporterQbPage(login, passward ).
		  pickUpFirstLevelRuleOption(SupporterFieldsRule).
		  pickUpNextLevelRuleOption(lastName, SupporterFieldsExtendedButtonLAbel).
		  pickUpTheRuleOperator("is blank").
		  checkErrorMessage().
		  checkShowResultButtonIsDisplayed().
		  clickShowTheResults().
		  checkErrorMessage();
	  }
	  
	//SupporterFieldsRule > middleName Tests
	  @Parameters({"login", "Passward"})
	    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
	  public void supporterMiddleNameEqualsTest(String login, String passward){
		  doLoginAndOpenSupporterQbPage(login, passward ).
		  pickUpFirstLevelRuleOption(SupporterFieldsRule).
		  pickUpNextLevelRuleOption(middleName, SupporterFieldsExtendedButtonLAbel).
		  pickUpTheRuleOperator("equals").
		  enterValue("mdqb").
		  checkErrorMessage().
		  checkShowResultButtonIsDisplayed().
		  clickShowTheResults().
		  checkErrorMessage().
		  checkSupporterEmailInTable(supporterExpectedEmail).
		  checkNumberOfRecordsInTable("1");
	  }
	  
	//SupporterFieldsRule > middleName Tests
	  @Parameters({"login", "Passward"})
	    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
	  public void supporterMiddleNameNotEqualsTest(String login, String passward){
		  String numerOfExpectedRecordsInTable= String.valueOf(getListOfAllSupportersIOrg(login, passward));
		  doLoginAndOpenSupporterQbPage(login, passward ).
		  pickUpFirstLevelRuleOption(SupporterFieldsRule).
		  pickUpNextLevelRuleOption(middleName, SupporterFieldsExtendedButtonLAbel).
		  pickUpTheRuleOperator("not equals").
		  enterValue("mdqb111").
		  checkErrorMessage().
		  checkShowResultButtonIsDisplayed().
		  clickShowTheResults().
		  checkErrorMessage(). 
		  checkNumberOfRecordsInTable(numerOfExpectedRecordsInTable);
	  }
	  
	//SupporterFieldsRule > MiddleName Tests
	  @Parameters({"login", "Passward"})
	    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
	  public void supporterMiddleNameContainsTest(String login, String passward){
		  doLoginAndOpenSupporterQbPage(login, passward ).
		  pickUpFirstLevelRuleOption(SupporterFieldsRule).
		  pickUpNextLevelRuleOption(middleName, SupporterFieldsExtendedButtonLAbel).
		  pickUpTheRuleOperator("contains").
		  enterValue("mdqb").
		  checkErrorMessage().
		  checkShowResultButtonIsDisplayed().
		  clickShowTheResults().
		  checkErrorMessage().
		  checkSupporterEmailInTable(supporterExpectedEmail).
		  checkNumberOfRecordsInTable("1");
	  }
	  
	  
		//SupporterFieldsRule > DateOfBirth Tests
	  @Parameters({"login", "Passward"})
	    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
	  public void supporterDateOfBirthBeforeTest(String login, String passward){
		  doLoginAndOpenSupporterQbPage(login, passward ).
		  pickUpFirstLevelRuleOption(SupporterFieldsRule).
		  pickUpNextLevelRuleOption(dateOfBirth, SupporterFieldsExtendedButtonLAbel).
		  pickUpTheRuleOperator("before").  
		  pickUpCalendarOperator("Calendar").
		  specifyCalendarValue("12/02/1992", null, null).
		  checkErrorMessage().
		  checkShowResultButtonIsDisplayed().
		  clickShowTheResults().
		  checkErrorMessage().
		  checkSupporterEmailInTable(supporterExpectedEmail).
		  checkNumberOfRecordsInTable("1");
	  }
	  
		//SupporterFieldsRule > DateOfBirth Tests
	  @Parameters({"login", "Passward"})
	    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
	  public void supporterDateOfBirthOnOrBeforeTest(String login, String passward){
		  doLoginAndOpenSupporterQbPage(login, passward ).
		  pickUpFirstLevelRuleOption(SupporterFieldsRule).
		  pickUpNextLevelRuleOption(dateOfBirth, SupporterFieldsExtendedButtonLAbel).
		  pickUpTheRuleOperator("on or before").  
		  pickUpCalendarOperator("Calendar") .
		  specifyCalendarValue("05/08/1990" , null, null).
		  checkErrorMessage().
		  checkShowResultButtonIsDisplayed().
		  clickShowTheResults().
		  checkErrorMessage().
		  checkSupporterEmailInTable(supporterExpectedEmail).
		  checkNumberOfRecordsInTable("1");
	  }
	  
		//SupporterFieldsRule > DateOfBirth Tests
	  @Parameters({"login", "Passward"})
	    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
	  public void supporterDateOfBirthAfterTest(String login, String passward){
		  String numerOfExpectedRecordsInTable= String.valueOf(getListOfAllSupportersIOrg(login, passward));
		  doLoginAndOpenSupporterQbPage(login, passward ).
		  pickUpFirstLevelRuleOption(SupporterFieldsRule).
		  pickUpNextLevelRuleOption(dateOfBirth, SupporterFieldsExtendedButtonLAbel).
		  pickUpTheRuleOperator("after").  
		  pickUpCalendarOperator("Calendar").
		  specifyCalendarValue("05/08/1989" , null, null).
		  checkErrorMessage().
		  checkShowResultButtonIsDisplayed().
		  clickShowTheResults().
		  checkErrorMessage().
		  checkNumberOfRecordsInTable(numerOfExpectedRecordsInTable);
	  }
	  
		//SupporterFieldsRule > DateOfBirth Tests
	  @Parameters({"login", "Passward"})
	    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
	  public void supporterDateOfBirthOnOrAfterTest(String login, String passward){
		  String numerOfExpectedRecordsInTable= String.valueOf(getListOfAllSupportersIOrg(login, passward));
		  doLoginAndOpenSupporterQbPage(login, passward ).
		  pickUpFirstLevelRuleOption(SupporterFieldsRule).
		  pickUpNextLevelRuleOption(dateOfBirth, SupporterFieldsExtendedButtonLAbel).
		  pickUpTheRuleOperator("on or after").  
		  pickUpCalendarOperator("Calendar" ).
		  specifyCalendarValue("05/08/1990" , null, null).
		  checkErrorMessage().
		  checkShowResultButtonIsDisplayed().
		  clickShowTheResults().
		  checkErrorMessage().
		  checkNumberOfRecordsInTable(numerOfExpectedRecordsInTable);
	  }
	  
		//SupporterFieldsRule > DateOfBirth Tests
	  @Parameters({"login", "Passward"})
	    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
	  public void supporterDateOfBirthBetweenTest(String login, String passward){
		  doLoginAndOpenSupporterQbPage(login, passward ).
		  pickUpFirstLevelRuleOption(SupporterFieldsRule).
		  pickUpNextLevelRuleOption(dateOfBirth, SupporterFieldsExtendedButtonLAbel).
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
	  
		//SupporterFieldsRule > DateOfBirth Tests
	  @Parameters({"login", "Passward"})
	    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
	  public void supporterDateOfBirthExactlyTest(String login, String passward){
		  doLoginAndOpenSupporterQbPage(login, passward ).
		  pickUpFirstLevelRuleOption(SupporterFieldsRule).
		  pickUpNextLevelRuleOption(dateOfBirth, SupporterFieldsExtendedButtonLAbel).
		  pickUpTheRuleOperator("exactly").  
		  pickUpCalendarOperator("Calendar") .
		  specifyCalendarValue("05/08/1990" , null, null).
		  checkErrorMessage().
		  checkShowResultButtonIsDisplayed().
		  clickShowTheResults().
		  checkErrorMessage().
		  checkSupporterEmailInTable(supporterExpectedEmail).
		  checkNumberOfRecordsInTable("1").
		  checkThatShowResultBottonIsNotDisplayedIfSearchCalendarValueIsCleared(false);
	  }
	  
		//SupporterFieldsRule > DateOfBirth Tests
	  @Parameters({"login", "Passward"})
	    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
	  public void supporterDateOfBirthDuringThisTest(String login, String passward){
		  doLoginAndOpenSupporterQbPage(login, passward ).
		  pickUpFirstLevelRuleOption(SupporterFieldsRule).
		  pickUpNextLevelRuleOption(dateOfBirth, SupporterFieldsExtendedButtonLAbel).
		  pickUpTheRuleOperator("during this").
		  pickUpDatePeriod("Week").
		  checkErrorMessage().
		  checkShowResultButtonIsDisplayed().
		  clickShowTheResults().
		  checkErrorMessage().
		  checkNoResultsIsReturned();
	  }
	  
		//SupporterFieldsRule > DateOfBirth Tests
	  @Parameters({"login", "Passward"})
	    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
	  public void supporterDateOfBirthNotDuringThisTest(String login, String passward){
		  String numerOfExpectedRecordsInTable= String.valueOf(getListOfAllSupportersIOrg(login, passward));
		  doLoginAndOpenSupporterQbPage(login, passward ).
		  pickUpFirstLevelRuleOption(SupporterFieldsRule).
		  pickUpNextLevelRuleOption(dateOfBirth, SupporterFieldsExtendedButtonLAbel).
		  pickUpTheRuleOperator("not during this").
		  pickUpDatePeriod("Month").
		  checkErrorMessage().
		  checkShowResultButtonIsDisplayed().
		  clickShowTheResults().
		  checkErrorMessage().
		  checkNumberOfRecordsInTable(numerOfExpectedRecordsInTable);
	  }
	  
		//SupporterFieldsRule > DateOfBirth Tests
	  @Parameters({"login", "Passward"})
	    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
	  public void supporterDateOfBirthtDuringLastYearTest(String login, String passward){
		  doLoginAndOpenSupporterQbPage(login, passward ).
		  pickUpFirstLevelRuleOption(SupporterFieldsRule).
		  pickUpNextLevelRuleOption(dateOfBirth, SupporterFieldsExtendedButtonLAbel).
		  pickUpTheRuleOperator("during last").
		  pickUpDatePeriod("Year").
		  checkErrorMessage().
		  checkShowResultButtonIsDisplayed().
		  clickShowTheResults().
		  checkErrorMessage().
		  checkNoResultsIsReturned();
	  }
	  
		//SupporterFieldsRule > DateOfBirth Tests
	  @Parameters({"login", "Passward"})
	    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
	  public void supporterDateOfBirthEverTest(String login, String passward){
		  String numerOfExpectedRecordsInTable= String.valueOf(getListOfAllSupportersIOrg(login, passward));
		  doLoginAndOpenSupporterQbPage(login, passward ).
		  pickUpFirstLevelRuleOption(SupporterFieldsRule).
		  pickUpNextLevelRuleOption(dateOfBirth, SupporterFieldsExtendedButtonLAbel).
		  pickUpTheRuleOperator("ever").
		  checkErrorMessage().
		  checkShowResultButtonIsDisplayed().
		  clickShowTheResults().
		  checkErrorMessage().
		  checkNumberOfRecordsInTable(numerOfExpectedRecordsInTable);
	  }
	  
		//SupporterFieldsRule > DateOfBirth Tests
	  @Parameters({"login", "Passward"})
	    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
	  public void supporterDateOfBirthBeforeRelative(String login, String passward){
		  doLoginAndOpenSupporterQbPage(login, passward ).
		  pickUpFirstLevelRuleOption(SupporterFieldsRule).
		  pickUpNextLevelRuleOption(dateOfBirth, SupporterFieldsExtendedButtonLAbel).
		  pickUpTheRuleOperator("before").
		  pickUpCalendarOperator("Relative").
		  specifyCalendarValueForRelativeOperator("25").
		  pickUpDatePeriod("Year(s)").
		  checkErrorMessage().
		  checkShowResultButtonIsDisplayed().
		  clickShowTheResults().
		  checkErrorMessage().checkSupporterEmailInTable(supporterExpectedEmail).
		  checkNumberOfRecordsInTable("1");
	  }
	  
	//SupporterFieldsRule > Suffix Tests
	  @Parameters({"login", "Passward"})
	    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
	  public void supporterSuffixEqualsTest(String login, String passward){
		  doLoginAndOpenSupporterQbPage(login, passward ).
		  pickUpFirstLevelRuleOption(SupporterFieldsRule).
		  pickUpNextLevelRuleOption(suffix, SupporterFieldsExtendedButtonLAbel).
		  pickUpTheRuleOperator("equals").
		  enterValue("suffixqb").
		  checkErrorMessage().
		  checkShowResultButtonIsDisplayed().
		  clickShowTheResults().
		  checkErrorMessage().
		  checkSupporterEmailInTable(supporterExpectedEmail).
		  checkNumberOfRecordsInTable("1");
	  }
	  
		//SupporterFieldsRule > suffix Tests
	  @Parameters({"login", "Passward"})
	    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
	  public void supporterSuffixNotEqualsTest(String login, String passward){
		  String numerOfExpectedRecordsInTable= String.valueOf(getListOfAllSupportersIOrg(login, passward));
		  doLoginAndOpenSupporterQbPage(login, passward ).
		  pickUpFirstLevelRuleOption(SupporterFieldsRule).
		  pickUpNextLevelRuleOption(suffix, SupporterFieldsExtendedButtonLAbel).
		  pickUpTheRuleOperator("not equals").
		  enterValue("blabla").
		  checkErrorMessage().
		  checkShowResultButtonIsDisplayed().
		  clickShowTheResults().
		  checkErrorMessage().
		  checkNumberOfRecordsInTable(numerOfExpectedRecordsInTable);
	  }
	  
		//SupporterFieldsRule > Suffix Tests
	  @Parameters({"login", "Passward"})
	    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
	  public void supporterSuffixContainsTest(String login, String passward){
		  String numerOfExpectedRecordsInTable= String.valueOf(getListOfAllSupportersIOrg(login, passward));
		  doLoginAndOpenSupporterQbPage(login, passward ).
		  pickUpFirstLevelRuleOption(SupporterFieldsRule).
		  pickUpNextLevelRuleOption(suffix, SupporterFieldsExtendedButtonLAbel).
		  pickUpTheRuleOperator("does not contain").
		  enterValue("blablaName").
		  checkErrorMessage().
		  checkShowResultButtonIsDisplayed().
		  clickShowTheResults().
		  checkErrorMessage(). 
		  checkNumberOfRecordsInTable(numerOfExpectedRecordsInTable);
	  }
	  
		//SupporterFieldsRule > Suffix Tests
	  @Parameters({"login", "Passward"})
	    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
	  public void supporterSuffixStartsWith(String login, String passward){
		  doLoginAndOpenSupporterQbPage(login, passward ).
		  pickUpFirstLevelRuleOption(SupporterFieldsRule).
		  pickUpNextLevelRuleOption(suffix, SupporterFieldsExtendedButtonLAbel).
		  pickUpTheRuleOperator("starts with").
		  enterValue("suffix").
		  checkErrorMessage().
		  checkShowResultButtonIsDisplayed().
		  clickShowTheResults().
		  checkErrorMessage().
		  checkSupporterEmailInTable(supporterExpectedEmail).
		  checkNumberOfRecordsInTable("1");
	  }
	  
	//SupporterFieldsRule > Suffix Tests
	  @Parameters({"login", "Passward"})
	    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
	  public void supporterSuffixIsNotBlank(String login, String passward){
		  String numerOfExpectedRecordsInTable= String.valueOf(getListOfAllSupportersIOrg(login, passward));
		  doLoginAndOpenSupporterQbPage(login, passward ).
		  pickUpFirstLevelRuleOption(SupporterFieldsRule).
		  pickUpNextLevelRuleOption(suffix, SupporterFieldsExtendedButtonLAbel).
		  pickUpTheRuleOperator("is not blank").
		  checkErrorMessage().
		  checkShowResultButtonIsDisplayed().
		  clickShowTheResults().
		  checkErrorMessage().
		  checkNumberOfRecordsInTable(numerOfExpectedRecordsInTable);
	  }
	  
	//SupporterFieldsRule > Suffix Tests
	  @Parameters({"login", "Passward"})
	    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
	  public void supporterSuffixIsBlank(String login, String passward){
		  doLoginAndOpenSupporterQbPage(login, passward ).
		  pickUpFirstLevelRuleOption(SupporterFieldsRule).
		  pickUpNextLevelRuleOption(suffix, SupporterFieldsExtendedButtonLAbel).
		  pickUpTheRuleOperator("is blank").
		  checkErrorMessage().
		  checkShowResultButtonIsDisplayed().
		  clickShowTheResults().
		  checkErrorMessage();
	  }
	  
	  
		//SupporterFieldsRule > title Tests
		  @Parameters({"login", "Passward"})
		    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
		  public void supporterTitleEqualsTest(String login, String passward){
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(SupporterFieldsRule).
			  pickUpNextLevelRuleOption(title, SupporterFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("equals").
			  enterValue("titleqb").
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage().
			  checkSupporterEmailInTable(supporterExpectedEmail).
			  checkNumberOfRecordsInTable("1");
		  }
		  
		//SupporterFieldsRule > title Tests
		  @Parameters({"login", "Passward"})
		    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
		  public void supporterTitleNameNotEqualsTest(String login, String passward){
			  String numerOfExpectedRecordsInTable= String.valueOf(getListOfAllSupportersIOrg(login, passward));
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(SupporterFieldsRule).
			  pickUpNextLevelRuleOption(title, SupporterFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("not equals").
			  enterValue("blabla").
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage(). 
			  checkNumberOfRecordsInTable(numerOfExpectedRecordsInTable);
		  }
		  
		//SupporterFieldsRule > title Tests
		  @Parameters({"login", "Passward"})
		    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
		  public void supporterTitileContainsTest(String login, String passward){
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(SupporterFieldsRule).
			  pickUpNextLevelRuleOption(title, SupporterFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("contains").
			  enterValue("qb").
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage().
			  checkSupporterEmailInTable(supporterExpectedEmail).
			  checkNumberOfRecordsInTable("1");
		  }
	  
			//SupporterFieldsRule > city Tests
		  @Parameters({"login", "Passward"})
		    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
		  public void supporterCityStartWithTest(String login, String passward){
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(SupporterFieldsRule).
			  pickUpNextLevelRuleOption(city, SupporterFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("starts with").
			  enterValue("new").
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage().
			  checkSupporterEmailInTable(supporterExpectedEmail).
			  checkNumberOfRecordsInTable("1");
		  }
		  
		//SupporterFieldsRule > City Tests
		  @Parameters({"login", "Passward"})
		    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
		  public void supporterCityEqualsTest(String login, String passward){
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(SupporterFieldsRule).
			  pickUpNextLevelRuleOption(city, SupporterFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("equals").
			  enterValue("new york").
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage().
			  checkSupporterEmailInTable(supporterExpectedEmail).
			  checkNumberOfRecordsInTable("1");
		  }
		  
		//SupporterFieldsRule > city Tests
		  @Parameters({"login", "Passward"})
		    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
		  public void supporterCityIsNotBlank(String login, String passward){
			  String numerOfExpectedRecordsInTable= String.valueOf(getListOfAllSupportersIOrg(login, passward));
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(SupporterFieldsRule).
			  pickUpNextLevelRuleOption(city, SupporterFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("is not blank").
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage().
			  checkNumberOfRecordsInTable(numerOfExpectedRecordsInTable);
		  }
		  
			//SupporterFieldsRule > city Tests
		  @Parameters({"login", "Passward"})
		    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
		  public void supporterCityIsBlank(String login, String passward){
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(SupporterFieldsRule).
			  pickUpNextLevelRuleOption(city, SupporterFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("is blank").
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage().
			  checkNoResultsIsReturned();
		  }
		  
			//SupporterFieldsRule > PreferredLanguage Tests
		  @Parameters({"login", "Passward"})
		    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
		  public void supporterPreferredLanguageIsTest(String login, String passward){
			  String numerOfExpectedRecordsInTable= String.valueOf(getListOfAllSupportersIOrg(login, passward));
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(SupporterFieldsRule).
			  pickUpNextLevelRuleOption(preferedLanguage, SupporterFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("is").
			  pickUpValueWhichComesAfterRuleOperator("English (United States)").
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage().
			  checkNumberOfRecordsInTable(numerOfExpectedRecordsInTable);
		  }
		  
			//SupporterFieldsRule > PreferredLanguage Tests
		  @Parameters({"login", "Passward"})
		    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
		  public void supporterPreferredLanguageIsNotTest(String login, String passward){
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(SupporterFieldsRule).
			  pickUpNextLevelRuleOption(preferedLanguage, SupporterFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("is not").
			  pickUpValueWhichComesAfterRuleOperator("English (United States)").
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage().
			  checkNoResultsIsReturned();
		  }
		  
			//SupporterFieldsRule > PreferredLanguage Tests
		  @Parameters({"login", "Passward"})
		    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
		  public void supporterPreferredLanguageIsBlankTest(String login, String passward){
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(SupporterFieldsRule).
			  pickUpNextLevelRuleOption(preferedLanguage, SupporterFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("is blank").
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage().
			  checkNoResultsIsReturned();
		  }
		  
		//SupporterFieldsRule > AddressLine1 Tests
		  @Parameters({"login", "Passward"})
		    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
		  public void supporterAddressLine1EqualsTest(String login, String passward){
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(SupporterFieldsRule).
			  pickUpNextLevelRuleOption(addressLine, SupporterFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("equals").
			  enterValue("350 5th Ave").
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage().
			  checkSupporterEmailInTable(supporterExpectedEmail).
			  checkNumberOfRecordsInTable("1");
		  }
		  
			//SupporterFieldsRule > AddressLine1 Tests
		  @Parameters({"login", "Passward"})
		    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
		  public void supporterAddressLine1NotEqualsTest(String login, String passward){
			  String numerOfExpectedRecordsInTable= String.valueOf(getListOfAllSupportersIOrg(login, passward));
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(SupporterFieldsRule).
			  pickUpNextLevelRuleOption(addressLine, SupporterFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("not equals").
			  enterValue("blabla").
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage().
			  checkNumberOfRecordsInTable(numerOfExpectedRecordsInTable);
		  }
		  
			//SupporterFieldsRule > AddressLine1 Tests
		  @Parameters({"login", "Passward"})
		    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
		  public void supporterAddressLine1DoesNotContainTest(String login, String passward){
			  String numerOfExpectedRecordsInTable= String.valueOf(getListOfAllSupportersIOrg(login, passward));
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(SupporterFieldsRule).
			  pickUpNextLevelRuleOption(addressLine, SupporterFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("does not contain").
			  enterValue("blablaName").
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage(). 
			  checkNumberOfRecordsInTable(numerOfExpectedRecordsInTable);
		  }
		  
			//SupporterFieldsRule > AddressLine1 Tests
		  @Parameters({"login", "Passward"})
		    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
		  public void supporterAddressLineStartsWith(String login, String passward){
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(SupporterFieldsRule).
			  pickUpNextLevelRuleOption(addressLine, SupporterFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("starts with").
			  enterValue("350").
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage().
			  checkSupporterEmailInTable(supporterExpectedEmail).
			  checkNumberOfRecordsInTable("1");
		  }
		  
		//SupporterFieldsRule > AddressLine1 Tests
		  @Parameters({"login", "Passward"})
		    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
		  public void supporterAddressLine2IsNotBlank(String login, String passward){
			  String numerOfExpectedRecordsInTable= String.valueOf(getListOfAllSupportersIOrg(login, passward));
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(SupporterFieldsRule).
			  pickUpNextLevelRuleOption(addressLine, SupporterFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("is not blank").
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage().
			  checkNumberOfRecordsInTable(numerOfExpectedRecordsInTable);
		  }
		  
		//SupporterFieldsRule > AddressLine1 Tests
		  @Parameters({"login", "Passward"})
		    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
		  public void supporterAddressLine1IsBlank(String login, String passward){
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(SupporterFieldsRule).
			  pickUpNextLevelRuleOption(addressLine, SupporterFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("is blank").
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage();
		  }
		  
		//SupporterFieldsRule > zipCode Tests
		  @Parameters({"login", "Passward"})
		    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
		  public void supporterzipCodeEqualsTest(String login, String passward){
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(SupporterFieldsRule).
			  pickUpNextLevelRuleOption(zipCode, SupporterFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("equals").
			  enterValue("10001-0110").
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage().
			  checkSupporterEmailInTable(supporterExpectedEmail).
			  checkNumberOfRecordsInTable("1");
		  }
		  
		//SupporterFieldsRule > zipCode  Tests
		  @Parameters({"login", "Passward"})
		    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
		  public void supporterzipCodedNotEqualsTest(String login, String passward){
			  String numerOfExpectedRecordsInTable= String.valueOf(getListOfAllSupportersIOrg(login, passward));
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(SupporterFieldsRule).
			  pickUpNextLevelRuleOption(zipCode, SupporterFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("not equals").
			  enterValue("910144").
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage(). 
			  checkNumberOfRecordsInTable(numerOfExpectedRecordsInTable);
		  }
		  
		//SupporterFieldsRule > zipCode  Tests
		  @Parameters({"login", "Passward"})
		    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
		  public void supporterzipCodeContainsTest(String login, String passward){
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(SupporterFieldsRule).
			  pickUpNextLevelRuleOption(zipCode, SupporterFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("contains").
			  enterValue("10001").
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage().
			  checkSupporterEmailInTable(supporterExpectedEmail).
			  checkNumberOfRecordsInTable("1");
		  }
		  
		//SupporterFieldsRule > zipCode  Tests
		  @Parameters({"login", "Passward"})
		    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
		  public void supporterZipdeCodeStartsWith(String login, String passward){
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(SupporterFieldsRule).
			  pickUpNextLevelRuleOption(zipCode, SupporterFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("starts with").
			  enterValue("100").
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage().
			  checkSupporterEmailInTable(supporterExpectedEmail).
			  checkNumberOfRecordsInTable("1");
		  }
		  
		//SupporterFieldsRule > zipCode  Tests
		  @Parameters({"login", "Passward"})
		    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
		  public void supporterzipCodedIsNotBlank(String login, String passward){
			  String numerOfExpectedRecordsInTable= String.valueOf(getListOfAllSupportersIOrg(login, passward));
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(SupporterFieldsRule).
			  pickUpNextLevelRuleOption(zipCode, SupporterFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("is not blank").
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage().
			  checkNumberOfRecordsInTable(numerOfExpectedRecordsInTable);
		  }
		  
		//SupporterFieldsRule > zipCode  Tests
		  @Parameters({"login", "Passward"})
		    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
		  public void supporterZipeCodeIsBlank(String login, String passward){
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(SupporterFieldsRule).
			  pickUpNextLevelRuleOption(zipCode, SupporterFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("is blank").
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage().
			  checkNoResultsIsReturned();
		  }
		  
		//SupporterFieldsRule > email Tests
		  @Parameters({"login", "Passward"})
		    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
		  public void supporterEmailEqualsTest(String login, String passward){
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(SupporterFieldsRule).
			  pickUpNextLevelRuleOption(email, SupporterFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("equals").
			  enterValue("qb_sup.74580786@mailosaur.in").
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage().
			  checkSupporterEmailInTable(supporterExpectedEmail).
			  checkNumberOfRecordsInTable("1");
		  }
		  
			//SupporterFieldsRule > email Tests
		  @Parameters({"login", "Passward"})
		    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
		  public void supporterEmailNotEqualsTest(String login, String passward){
			  String numerOfExpectedRecordsInTable= String.valueOf(getListOfAllSupportersIOrg(login, passward));
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(SupporterFieldsRule).
			  pickUpNextLevelRuleOption(email, SupporterFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("not equals").
			  enterValue("q1.74580786@mailosaur.in").
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage().
			  checkNumberOfRecordsInTable(numerOfExpectedRecordsInTable);
		  }
		  
			//SupporterFieldsRule > email Tests
		  @Parameters({"login", "Passward"})
		    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
		  public void supporterEmailContainsTest(String login, String passward){
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(SupporterFieldsRule).
			  pickUpNextLevelRuleOption(email, SupporterFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("contains").
			  enterValue("sup").
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage(). 
			  checkSupporterEmailInTable(supporterExpectedEmail).
			  checkNumberOfRecordsInTable("1");
		  }
		  
			//SupporterFieldsRule > email Tests
		  @Parameters({"login", "Passward"})
		    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
		  public void supporterEmailStartsWith(String login, String passward){
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(SupporterFieldsRule).
			  pickUpNextLevelRuleOption(email, SupporterFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("starts with").
			  enterValue("qb_sup").
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage().
			  checkSupporterEmailInTable(supporterExpectedEmail).
			  checkNumberOfRecordsInTable("1");
		  }
		  
		//SupporterFieldsRule > email Tests
		  @Parameters({"login", "Passward"})
		    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
		  public void supporterEmailIsNotBlank(String login, String passward){
			  String numerOfExpectedRecordsInTable= String.valueOf(getListOfAllSupportersIOrg(login, passward));
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(SupporterFieldsRule).
			  pickUpNextLevelRuleOption(email, SupporterFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("is not blank").
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage().
			  checkNumberOfRecordsInTable(numerOfExpectedRecordsInTable);
		  }
		  
		//SupporterFieldsRule > email Tests
		  @Parameters({"login", "Passward"})
		    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
		  public void supporterEmailIsBlank(String login, String passward){
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(SupporterFieldsRule).
			  pickUpNextLevelRuleOption(email, SupporterFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("is blank").
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage().
			  checkNoResultsIsReturned();
		  }
		  
		//SupporterFieldsRule > Work Phone Tests
		  @Parameters({"login", "Passward"})
		    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
		  public void supporterWorkPhoneEqualsTest(String login, String passward){
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(SupporterFieldsRule).
			  pickUpNextLevelRuleOption(workPhone, SupporterFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("equals").
			  enterValue("777-777-7777").
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage().
			  checkSupporterEmailInTable(supporterExpectedEmail).
			  checkNumberOfRecordsInTable("1").
			  checkThatShowResultBottonIsNotDisplayedIfSearchValueIsCleared();
		  }
		  
			//SupporterFieldsRule > Work Phone Tests
		  @Parameters({"login", "Passward"})
		    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
		  public void supporterWorkPhonexNotEqualsTest(String login, String passward){
			  String numerOfExpectedRecordsInTable= String.valueOf(getListOfAllSupportersIOrg(login, passward));
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(SupporterFieldsRule).
			  pickUpNextLevelRuleOption(workPhone, SupporterFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("not equals").
			  enterValue("blabla").
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage().
			  checkNumberOfRecordsInTable(numerOfExpectedRecordsInTable);
		  }
		  
			//SupporterFieldsRule > Work Phone Tests
		  @Parameters({"login", "Passward"})
		    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
		  public void supporterWorkPhoneContainsTest(String login, String passward){
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(SupporterFieldsRule).
			  pickUpNextLevelRuleOption(workPhone, SupporterFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("contains").
			  enterValue("777").
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage(). 
			  checkNumberOfRecordsInTable("1").
			  checkThatShowResultBottonIsNotDisplayedIfSearchValueIsCleared();
		  }
		  	  
		//SupporterFieldsRule > WorkPhone Tests
		  @Parameters({"login", "Passward"})
		    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
		  public void supporterWorkPhoneIsNotBlank(String login, String passward){
			  String numerOfExpectedRecordsInTable= String.valueOf(getListOfAllSupportersIOrg(login, passward));
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(SupporterFieldsRule).
			  pickUpNextLevelRuleOption(workPhone, SupporterFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("is not blank").
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage().
			  checkNumberOfRecordsInTable(numerOfExpectedRecordsInTable);
		  }
		  
			//SupporterFieldsRule > Cell Phone Tests
		  @Parameters({"login", "Passward"})
		    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
		  public void supporterCellPhoneEqualsTest(String login, String passward){
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(SupporterFieldsRule).
			  pickUpNextLevelRuleOption(cellPhone, SupporterFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("equals").
			  enterValue("666-666-6666").
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage().
			  checkSupporterEmailInTable(supporterExpectedEmail).
			  checkNumberOfRecordsInTable("1");
		  }
		  
			//SupporterFieldsRule > Cell Phone Tests
		  @Parameters({"login", "Passward"})
		    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
		  public void supporterCellPhoneStartWithTest(String login, String passward){
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(SupporterFieldsRule).
			  pickUpNextLevelRuleOption(cellPhone, SupporterFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("starts with").
			  enterValue("666-66").
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage().
			  checkSupporterEmailInTable(supporterExpectedEmail).
			  checkNumberOfRecordsInTable("1").
			  checkThatShowResultBottonIsNotDisplayedIfSearchValueIsCleared();
		  }
		  
			//SupporterFieldsRule >  Cell Phone Tests
		  @Parameters({"login", "Passward"})
		    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
		  public void supporterCellPhoneIsBlank(String login, String passward){
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(SupporterFieldsRule).
			  pickUpNextLevelRuleOption(cellPhone, SupporterFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("is blank").
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage().
			  checkNoResultsIsReturned();
		  }
		  
			//SupporterFieldsRule > Home Phone Tests
		  @Parameters({"login", "Passward"})
		    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
		  public void supporterHomePhoneEqualsTest(String login, String passward){
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(SupporterFieldsRule).
			  pickUpNextLevelRuleOption(homePhone, SupporterFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("equals").
			  enterValue("444-444-4444").
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage().
			  checkSupporterEmailInTable(supporterExpectedEmail).
			  checkNumberOfRecordsInTable("1");
		  }
		  
			//SupporterFieldsRule > Home Phone Tests
		  @Parameters({"login", "Passward"})
		    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
		  public void supporterHomePhoneDoesNotContainTest(String login, String passward){
			  String numerOfExpectedRecordsInTable= String.valueOf(getListOfAllSupportersIOrg(login, passward));
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(SupporterFieldsRule).
			  pickUpNextLevelRuleOption(homePhone, SupporterFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("does not contain").
			  enterValue("0s00s").
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage().
			  checkNumberOfRecordsInTable(numerOfExpectedRecordsInTable).
			  checkThatShowResultBottonIsNotDisplayedIfSearchValueIsCleared();
		  }
		  
			//SupporterFieldsRule > Country Tests
		  @Parameters({"login", "Passward"})
		    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
		  public void supporterCountryIsTest(String login, String passward){
			  String numerOfExpectedRecordsInTable= String.valueOf(getListOfAllSupportersIOrg(login, passward));
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(SupporterFieldsRule).
			  pickUpNextLevelRuleOption(country, SupporterFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("is").
			  pickUpValueWhichComesAfterRuleOperator("United States").
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage().
			  checkNumberOfRecordsInTable(numerOfExpectedRecordsInTable);
		  }
		  
			//SupporterFieldsRule > Country Tests
		  @Parameters({"login", "Passward"})
		    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
		  public void supporterCountryIsNotTest(String login, String passward){
			  String numerOfExpectedRecordsInTable= String.valueOf(getListOfAllSupportersIOrg(login, passward));
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(SupporterFieldsRule).
			  pickUpNextLevelRuleOption(country, SupporterFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("is not").
			  pickUpValueWhichComesAfterRuleOperator("Ukraine").
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage().
			 checkNumberOfRecordsInTable(numerOfExpectedRecordsInTable);
		  }
		  
			//SupporterFieldsRule > State Tests
		  @Parameters({"login", "Passward"})
		    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
		  public void supporterStateIsTest(String login, String passward){
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(SupporterFieldsRule).
			  pickUpNextLevelRuleOption(state, SupporterFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("is").
			  pickUpValueWhichComesAfterRuleOperator("New York").
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage().
			  checkSupporterEmailInTable(supporterExpectedEmail).
			  checkNumberOfRecordsInTable("1");
		  }
		  
			//SupporterFieldsRule > State Tests
		  @Parameters({"login", "Passward"})
		    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
		  public void supporterStateIsNotTest(String login, String passward){
			  String numerOfExpectedRecordsInTable= String.valueOf(getListOfAllSupportersIOrg(login, passward));
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(SupporterFieldsRule).
			  pickUpNextLevelRuleOption(state, SupporterFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("is not").
			  pickUpValueWhichComesAfterRuleOperator("Alberta").
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage().
			  checkNumberOfRecordsInTable(numerOfExpectedRecordsInTable);
		  }
		  
			//SupporterFieldsRule > State Tests
		  @Parameters({"login", "Passward"})
		    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"SupporterFields"})
		  public void supporterStateIsBlankTest(String login, String passward){
			  doLoginAndOpenSupporterQbPage(login, passward ).
			  pickUpFirstLevelRuleOption(SupporterFieldsRule).
			  pickUpNextLevelRuleOption(state, SupporterFieldsExtendedButtonLAbel).
			  pickUpTheRuleOperator("is blank").
			  checkErrorMessage().
			  checkShowResultButtonIsDisplayed().
			  clickShowTheResults().
			  checkErrorMessage().
			  checkNoResultsIsReturned();
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
			numberOfSupportersInOrg = new HttpClient(SeleneseTestCase.USED_ENVIRONMENT.getBaseTestUrl()).login(userName, pass).getNumberOfSupportersInOrg();
		 return numberOfSupportersInOrg; 
	  }
}
