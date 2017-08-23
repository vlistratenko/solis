package com.salsalabs.ignite.automation.suites.regression;
 
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;
import com.salsalabs.ignite.automation.pages.hq.manage.CustomTargetsPage;
 
/**
 * <b>This class contains scenarios related to custom targets creation (https://jira.salsalabs.net/browse/IG-10603)</b>
 */
 
public class CustomTargetsTest extends SeleneseTestCase {
    
    /**
     * <b>Create Custom Target</b>
     * <p>
     * Steps:
     * <ul>
     * <li> Login into existing organization
     * <li> Click on Settings --> Custom Targets
     * <li> Click on Add Custom Target button
     * <li> Specify custom target Category, First Name, Last Name, Title, Email Address, City, State, Zip, Facebook account, Twitter account values
     * <li> Click on Create Custom Target button
     * <li> Select to display 250 items per page
     * <li> <font color="green"><b>Verify whether custom target is present in the table</b></font>
     */
	
	@Parameters("login")
    @Test(enabled = true, priority = 1, groups = {"createAndDeleteCustomTarget"}, retryAnalyzer = RetryAnalyzer.class)
    public void createCustomTarget(String login) {
        LoginPage lp = new LoginPage();
        CustomTargetsPage ct = new CustomTargetsPage();
        ct = lp.doSuccessLogin(login, "!QAZ2wsx")
                .openSettingsPage()
                .switchToCustomTargetsPage()
                .clickAddCustomTargetButton()
                .selectCategory("Education")
                .specifyFirstName("FirstName")
                .specifyLastName("LastName")
                .specifyTitle("Mr.")
                .specifyEmailAddress()
                .specifyCity("City")
                .selectState("California")
                .specifyZipCode("20008")
                .specifyFacebookAccount("/fb")
                .specifyTwitterAccount("/tw")
                .clickOnCreateCustomTargetButton()
                .showAllCustomTargets();
        
        Assert.assertEquals(ct.getTableWithCustomTargets().isValueExistsInTable(CommonUtils.getProperty("customTargetEmailAddress")), true,
                "Created custom target is not found in the table with targets");
    }
    
    /**
     * <b>Edit Custom Target</b>
     * <p>
     * Steps:
     * <ul>
     * <li> Login into existing organization
     * <li> Click on Settings --> Custom Targets
     * <li> Click on some existing custom target
     * <li> Update custom target Category, First Name, Last Name, Title, Email Address, City, State, Zip, Facebook account, Twitter account values
     * <li> Save new Custom Target data
     * <li> Click on same existing custom target
     * <li> <font color="green"><b>Verify whether fields' values which were specified during updated are still on place</b></font>
     */
	
	@Parameters("login")
    @Test(enabled = true, priority = 2, groups = {"createAndDeleteCustomTarget"}, dependsOnMethods = {"createCustomTarget"}, retryAnalyzer = RetryAnalyzer.class)
    public void editCustomTarget(String login) {
        LoginPage lp = new LoginPage();
        CustomTargetsPage ct = new CustomTargetsPage();
        ct = lp.doSuccessLogin(login, "!QAZ2wsx")
                .openSettingsPage()
                .switchToCustomTargetsPage()
                .showAllCustomTargets()
                .clickOnCustomTargetByEmail(CommonUtils.getProperty("customTargetEmailAddress"))
                .selectCategory("Media")
                .specifyFirstName("FirstNameNew")
                .specifyLastName("LastNameNew")
                .specifyTitle("Miss.")
                .specifyEmailAddress()
                .specifyCity("CityNew")
                .selectState("Arizona")
                .specifyZipCode("20009")
                .specifyFacebookAccount("/fbNew")
                .specifyTwitterAccount("/twNew")
                .clickOnSaveCustomTargetButton()
                .showAllCustomTargets()
                .clickOnCustomTargetByEmail(CommonUtils.getProperty("customTargetEmailAddress"));
        
        Assert.assertEquals(CommonUtils.getProperty("customTargetFirstName").equals(ct.getFirstNameFieldValue()), true, "First Name field value is other than the one which was specify during form editing");
        Assert.assertEquals(CommonUtils.getProperty("customTargetLastName").equals(ct.getLastNameFieldValue()), true, "Last Name field value is other than the one which was specify during form editing");
        Assert.assertEquals(CommonUtils.getProperty("customTargetTitle").equals(ct.getTitleFieldValue()), true, "Title field value is other than the one which was specify during form editing");
        Assert.assertEquals(CommonUtils.getProperty("customTargetEmailAddress").equals(ct.getEmailAddressFieldValue()), true, "Email address field value is other than the one which was specify during form editing");
        Assert.assertEquals(CommonUtils.getProperty("customTargetCity").equals(ct.getCityFieldValue()), true, "City field value is other than the one which was specify during form editing");
        Assert.assertEquals(CommonUtils.getProperty("customTargetZip").equals(ct.getZipCodeFieldValue()), true, "ZIP field value is other than the one which was specify during form editing");
        Assert.assertEquals(CommonUtils.getProperty("customTargetFbAccount").equals(ct.getFacebookAccountFieldValue()), true, "FB account field value is other than the one which was specify during form editing");
        Assert.assertEquals(CommonUtils.getProperty("customTargetTwitterAccount").equals(ct.getTwitterAccountFieldValue()), true, "Twitter field value is other than the one which was specify during form editing");
    }
    
    /**
     * <b>Delete Custom Target</b>
     * <p>
     * Steps:
     * <ul>
     * <li> Login into existing organization
     * <li> Click on Settings --> Custom Targets
     * <li> Select to display 250 items per page
     * <li> Click on checkbox left to custom target you've just created
     * <li> Click on Delete --> Confirm deletion
     * <li> <font color="green"><b>Verify whether custom target is deleted from the table</b></font>
     */
	
	@Parameters("login")
    @Test(enabled = true, priority = 3, groups = {"createAndDeleteCustomTarget"}, dependsOnMethods = {"editCustomTarget"}, retryAnalyzer = RetryAnalyzer.class )
    public void deleteCustomTarget(String login) {
        LoginPage lp = new LoginPage();
        CustomTargetsPage ct = new CustomTargetsPage();
        ct = lp.doSuccessLogin(login, "!QAZ2wsx")
                .openSettingsPage()
                .switchToCustomTargetsPage()
                .showAllCustomTargets()
                .selectCustomTargetInTable(CommonUtils.getProperty("customTargetEmailAddress"))
                .clickOnDeleteAction()
                .confirmCustomTargetDeletion();                
        
        Assert.assertEquals(ct.getTableWithCustomTargets().isValueExistsInTable(CommonUtils.getProperty("customTargetEmailAddress")), false, "Deleted custom target is found in the table of custom targets");
    }
}
