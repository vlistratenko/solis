package com.salsalabs.ignite.automation.suites.regression;

import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.pages.hq.HomePage;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;
import com.salsalabs.ignite.automation.pages.hq.activities.AddSubscribeWidgetPage;
import com.salsalabs.ignite.automation.pages.hq.activities.DonationWidget;
import com.salsalabs.ignite.automation.pages.hq.activities.EventWidget;
import com.salsalabs.ignite.automation.pages.hq.activities.FormFieldConfigurationModalWindow;
import com.salsalabs.ignite.automation.pages.p2p.AddP2PPage_EventPageTab_CheckoutSubTab;
import com.salsalabs.ignite.automation.pages.p2p.Eventp2pWidget;
import org.apache.commons.lang.RandomStringUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.nio.file.Paths;

@Test
public class VerifyDesignationCheckboxTest extends SeleneseTestCase {
    private String widgetName;
    private String email;
    private String hqHandle;

    @BeforeMethod(alwaysRun = true)
    public void generateData(){
        widgetName = RandomStringUtils.randomAlphanumeric(10);
        email = widgetName + "@test.com";
    }

    @Parameters({"login", "password"})
    @Test(groups = {"fundraising"})
    public void checkDesignationForFundraising(String login, String password) throws InterruptedException {
        AddSubscribeWidgetPage widgetPage = new LoginPage().
                doSuccessLogin(login, password).
                openActivitiesPage().
                openFundraisingWidgetPage().
                openAddDonationWidgetPage().
                fillFieldsWidgetStepOne(widgetName, widgetName).
                selectLayoutStep("Basic");
        new FormFieldConfigurationModalWindow().
                dropFormFieldByName("Designation").
                addDesignationFieldOption("1").
                addDesignationFieldOption("2").
                addDesignationFieldOption("3").
                saveFormFieldConfiguration();
        hqHandle = getDriver().getWindowHandle();
        widgetPage.
                preview(hqHandle).
                checkIfDesignationFieldExistsOnForm("1", "2", "3");
        getDriver().switchTo().window(hqHandle);
        widgetPage.
                goToAutorespondersTab().
                publishFromAutoresponders().
                openSubscribeWidget();
        new DonationWidget().
                fillTheFirstStepOfTheDonationForm("10",false,false).fillTheSecondStepOfTheDonationForm(email, widgetName, widgetName, widgetName, widgetName, "20009", "WA", true).
                fillTheThirdStepOfTheDonationForm("4111111111111111", "123", "11", "2023").clickOnSubmitFormButton();
        getDriver().switchTo().window(hqHandle);
        new HomePage().
                openTransactionsView().
                goToExportsTab().
                createNewTransactionExport().
                fillTitleDescriptionAndProceed(widgetName, widgetName).
                runExportNow().
                downloadExportResults();
        widgetPage.verifyDesignationFieldInCsv(email);
    }


    @Parameters({"login", "password"})
    @Test(groups = {"events"})
    public void checkDesignationForEvent(String login, String password){
       new LoginPage().doSuccessLogin(login, password).
                openActivitiesPage().
                openEventsPage().
                clickCreateAnEventButton().
                specifyEventReferenceName(widgetName).
                specifyEventPublicilyVisibleName(widgetName).
                clickNextButtonInSetupTab().
                specifyTicketName(widgetName).
                clickSaveTicketInfoButton().
                clickContinueButton().
                clickNextButtonInTicketsTab().
                selectLayout("Basic").
                clickNextButtonInSelectLayoutTab().
                openCheckoutSubTab();
        new FormFieldConfigurationModalWindow().
                dropFormFieldByName("Designation").
                addDesignationFieldOption("1").
                addDesignationFieldOption("2").
                addDesignationFieldOption("3").
                saveFormFieldConfiguration();
        hqHandle = getDriver().getWindowHandle();
        AddSubscribeWidgetPage widgetPage = new AddSubscribeWidgetPage().preview(hqHandle);
        widgetPage.checkIfDesignationFieldExistsOnForm("1", "2", "3");
        getDriver().switchTo().window(hqHandle);
        widgetPage.goToAutorespondersTab().publishFromAutoresponders();
        hqHandle = driver.getWindowHandle();
        widgetPage.openSubscribeWidget(widgetName);
        new EventWidget()
                .openEventRegistrationPage()
                .fillEventRegistrationForm(email, widgetName, widgetName)
                .fillEventDonationForm(
                        email,
                        widgetName,
                        widgetName,
                        widgetName,
                        widgetName,
                        "20009",
                        "WA",
                        "10",
                        "card holder",
                        "4111111111111111",
                        "123",
                        "12",
                        "2022"
                ).clickSubmitButtonAndWaitUntilConfirmationScreenOpened();
        getDriver().switchTo().window(hqHandle);
        new HomePage().
                openTransactionsView().
                goToExportsTab().
                createNewTransactionExport().
                fillTitleDescriptionAndProceed(widgetName, widgetName).
                runExportNow().
                downloadExportResults();
        widgetPage.verifyDesignationFieldInCsv(email);
    }


    @Parameters({"login", "password"})
    @Test(groups = {"events"})
    public void checkDesignationForP2P(String login, String password){
        AddP2PPage_EventPageTab_CheckoutSubTab p2pCheckout = new LoginPage().
                doSuccessLogin(login, password).
                openActivitiesPage().
                openP2PPage().
                openCreateNewp2pForm().
                fillSetupStepAndGoNext(
                        widgetName,
                        widgetName,
                        "12/15/2017"
                        , "8:00am",
                        "12/01/2021",
                        "8:00am",
                        "(GMT-10:00) Aleutian Islands",
                        false).
                fillRegistrationStepAndGoNext(
                        widgetName,
                        false,
                        false,
                        "10",
                        "5").
                clickContinueButton().
                clickContinueButton().
                clickNextButton().
                selectLayoutAndClickNext("Basic").
                openCheckoutSubTab();
        new FormFieldConfigurationModalWindow().
                dropFormFieldByName("Designation").
                addDesignationFieldOption("1").
                addDesignationFieldOption("2").
                addDesignationFieldOption("3").
                saveFormFieldConfiguration();
        hqHandle = getDriver().getWindowHandle();
        AddSubscribeWidgetPage widgetPage = new AddSubscribeWidgetPage().
                preview(hqHandle).
                checkIfDesignationFieldExistsOnForm("1", "2", "3");
        getDriver().switchTo().window(hqHandle);
        p2pCheckout.
                clickNextToEventPageButton().
                clickNextToTeamTabButton().
                clickNextToAutorespondersTabButton().
                clickPublishButton();
        hqHandle = driver.getWindowHandle();
        widgetPage.openSubscribeWidget(widgetName);
        new Eventp2pWidget().openp2pEventRegistrationPage().clickNextButtonOnRegistrationTypesPage().
                fillp2pEventRegistrationForm(email, widgetName, widgetName).
                clickCheckOutButton().
                fillp2pEventDonationForm(
                        email,
                        widgetName,
                        widgetName,
                        widgetName,
                        widgetName,
                        "20009",
                        "WA",
                        "10",
                        "Card Holder",
                        "4111111111111111",
                        "123",
                        "12",
                        "2022",
                        true,
                        true,
                        true).
                clickOnSubmitFormButton();
       getDriver().switchTo().window(hqHandle);
        new HomePage().
                openTransactionsView().
                goToExportsTab().
                createNewTransactionExport().
                fillTitleDescriptionAndProceed(widgetName, widgetName).
                runExportNow().
                downloadExportResults();
        widgetPage.verifyDesignationFieldInCsv(email);
    }

    @AfterMethod(alwaysRun = true)
    public void cleanDownloadsFolder(){
       File[] files = CommonUtils.getListOfFilesInFolder(Paths.get(System.getProperty("user.dir"), "downloads").toString());
       for (File f : files) {
           f.delete();
       }
    }
}
