package com.salsalabs.ignite.automation.suites.regression;

import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.elements.VE2Elements.Signatures;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;
import com.salsalabs.ignite.automation.pages.hq.activities.AddPetitionPage;
import com.salsalabs.ignite.automation.pages.hq.activities.FormFieldConfigurationModalWindow;
import com.salsalabs.ignite.automation.pages.hq.activities.PetitionWidget;
import com.salsalabs.ignite.automation.pages.hq.basic.Layouts;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test(groups = {"petition", "regression", "petitionSignaturesElementTest"})
public class PetitionSignaturesElementTest extends SeleneseTestCase {

    private AddPetitionPage addPetitionPage;
    private String widgetName;
    private String widgetDescription;
    private String supporterEmail;
    private PetitionWidget petitionForm;
    private String login;
    private String password;
    private String existingFormUrl;
    private String existingFormName;

    @Test(priority = 1, groups = {"new_data"}, retryAnalyzer = RetryAnalyzer.class)
    public void createAndPublishPetitionFormWithSignaturesElement (ITestContext context){

        petitionForm = new PetitionWidget();
        login = context.getSuite().getParameter("login");
        password = context.getSuite().getParameter("password");
        existingFormUrl = context.getSuite().getParameter("existingFormUrl");
        existingFormName = context.getSuite().getParameter("existingFormName");
        widgetName = "PetitionWidgetName_" + RandomStringUtils.randomAlphanumeric(5);
        widgetDescription = "PetitionWidgetDescription_" + RandomStringUtils.randomAlphanumeric(10);
        supporterEmail = "autosupporter" + RandomStringUtils.randomAlphanumeric(4)+"@test.com";

        addPetitionPage = new LoginPage().
                doSuccessLogin(login, password).
                openActivitiesPage().
                openPetitionsPage().
                openAddPetitionPage();
        addPetitionPage.
                fillFieldsWidgetStepOne(widgetName, widgetDescription).
                selectLayoutStep(Layouts.LayoutName.BLANK).
                dropOneColumnRow().
                dropVEFormElement();
        addPetitionPage.dropVESignaturesElement();
        addPetitionPage.
                goToAutorespondersTab().
                publishFromAutoresponders().
                openSubscribeWidget();
        petitionForm.fillSubscribeWidget(
                supporterEmail,
                "FirstName",
                "LastName",
                "City",
                "20009",
                "CA"
        );
}

    @DataProvider
    public Object[][] petitionSupporterNameAndAddressFormat(){
        return new Object[][]
        {
                {Signatures.CustomizedSupporterNameOptions.FIRST_INITIAL_LAST_INITIAL, Signatures.CustomizedSupporterLocationOptions.CITY_ONLY},
                {Signatures.CustomizedSupporterNameOptions.FIRST_INITIAL_LAST_NAME,  Signatures.CustomizedSupporterLocationOptions.DO_NOT_DISPLAY},
                {Signatures.CustomizedSupporterNameOptions.FIRST_NAME_LAST_INITIAL, Signatures.CustomizedSupporterLocationOptions.CITY_STATE},
                {Signatures.CustomizedSupporterNameOptions.FIRST_NAME_LAST_NAME, Signatures.CustomizedSupporterLocationOptions.STATE_ONLY}
        };
    }

    @Test(priority = 2, groups = {"new_data"}, dependsOnMethods = "createAndPublishPetitionFormWithSignaturesElement", alwaysRun = true, retryAnalyzer = RetryAnalyzer.class, dataProvider = "petitionSupporterNameAndAddressFormat")
    public void testNewPetitionSignaturesElementNameAndAddressFormat(Signatures.CustomizedSupporterNameOptions nameFormat, Signatures.CustomizedSupporterLocationOptions addressFormat) {
        petitionForm = new PetitionWidget();
        addPetitionPage = (AddPetitionPage) new LoginPage().
                doSuccessLogin(login, password).
                openActivitiesPage().
                searchFormByNameAndClick(widgetName, AddPetitionPage.class).
                openFormComposeTab();
        addPetitionPage.editSignaturesField();
        new FormFieldConfigurationModalWindow().
                setCustomizeTheDisplayOfTheSupporterNameOptionTo(nameFormat).
                setCustomizeTheDisplayOfTheSupporterLocationOptionTo(addressFormat).
                saveSignatureElementConfigurationModal();
        addPetitionPage.
                goToAutorespondersTab().
                publishFromAutoresponders();
        addPetitionPage.openPetitionWidget(widgetName).
                verifyPublicFormDisplaysCorrectSupporterNameAndAddressFormat(nameFormat, addressFormat);
    }

    @Test(priority = 3, groups = {"existing_data"}, retryAnalyzer = RetryAnalyzer.class, dataProvider = "petitionSupporterNameAndAddressFormat")
    public void testExistingPetitionSignaturesElementNameAndAddressFormat(Signatures.CustomizedSupporterNameOptions nameFormat, Signatures.CustomizedSupporterLocationOptions addressFormat){
        petitionForm = new PetitionWidget();
        addPetitionPage = (AddPetitionPage) new LoginPage().
                doSuccessLogin(login, password).
                openPetitionFormByFullUrl(existingFormUrl).
                openFormComposeTab();
        addPetitionPage.editSignaturesField();
        new FormFieldConfigurationModalWindow().
                setCustomizeTheDisplayOfTheSupporterNameOptionTo(nameFormat).
                setCustomizeTheDisplayOfTheSupporterLocationOptionTo(addressFormat).
                saveSignatureElementConfigurationModal();
        addPetitionPage.
                goToAutorespondersTab().
                publishFromAutoresponders();
        addPetitionPage.openPetitionWidget(existingFormName).
                verifyPublicFormDisplaysCorrectSupporterNameAndAddressFormat(nameFormat, addressFormat);
    }
}