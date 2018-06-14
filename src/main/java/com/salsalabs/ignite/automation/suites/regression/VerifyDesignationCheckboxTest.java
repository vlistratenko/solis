package com.salsalabs.ignite.automation.suites.regression;

import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;
import com.salsalabs.ignite.automation.pages.hq.activities.*;
import com.salsalabs.ignite.automation.pages.p2p.AddP2PPage_PublishedDeatailsTab;
import com.salsalabs.ignite.automation.pages.p2p.Eventp2pWidget;
import org.apache.commons.lang.RandomStringUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Set;

import static com.salsalabs.ignite.automation.suites.regression.SupporterQBSupporterFieldsTest.email;

@Test
public class VerifyDesignationCheckboxTest extends SeleneseTestCase {

    private AddDonationWidgetPage addDonationPage;
    private FormFieldConfigurationModalWindow formFieldConfigurationModal;
    private String widgetName;
    private String widgetDescription;
    private String supporterEmail;
    private String hqHandle;

    @BeforeMethod(alwaysRun = true)
    public void generateData(){
        widgetName = "FundraisingFormtName_" + RandomStringUtils.randomAlphanumeric(5);
        widgetDescription = "FundraisingFormDescription_" + RandomStringUtils.randomAlphanumeric(10);
        supporterEmail = "autosupporter" + RandomStringUtils.randomAlphanumeric(4) + "@test.com";
    }

    @Parameters({"login", "password"})
    @Test(groups = {"fundraising"})
    public void checkDesignationForFundraising(String login, String password) throws InterruptedException {
        addDonationPage = new LoginPage().
                doSuccessLogin(login, password).
                openActivitiesPage().
                openFundraisingWidgetPage().
                openAddDonationWidgetPage();
        addDonationPage.fillFieldsWidgetStepOne(widgetName, widgetDescription)
                .selectLayoutStep("Blank");
        addDonationPage.dropOneColumnRow();
        addDonationPage.dropVEFormElement();
        new FormFieldConfigurationModalWindow().
                dropFormFieldByName("Designation").
                addDesignationFieldOption("1").
                addDesignationFieldOption("2").
                addDesignationFieldOption("3").
                saveFieldConfiguration();
        String currentWindow = getDriver().getWindowHandle();
        addDonationPage.preview();//open preview and check designation
        Set<String> windows = getDriver().getWindowHandles();
        for (String handle : windows) {
            if (!handle.equals(currentWindow)) getDriver().switchTo().window(handle);
        }
        getDriver().switchTo().frame("previewIframe");
        addDonationPage.checkIfDesignationFieldExistsOnForm("1", "2", "3");
        getDriver().switchTo().window(currentWindow);
        addDonationPage.goToAutorespondersTab();
        addDonationPage.publishFromAutoresponders();
        addDonationPage.openSubscribeWidget();
        addDonationPage.checkIfDesignationFieldExistsOnForm("1", "2", "3");
        DonationWidget fundraisingForm1 = new DonationWidget();
        fundraisingForm1.fillCreditCardDetails("10", "4111111111111111", "123", "11", "2023", "card holder");
        fundraisingForm1.fillSubscribeWidget(supporterEmail, supporterEmail, supporterEmail, supporterEmail, "20009", "WA", supporterEmail).clickOnSubmitFormButton();
        getDriver().switchTo().window(currentWindow);
        addDonationPage.goToResultPage();
        addDonationPage.verifyDesignationInCsv();
    }

    @Parameters({"login", "password"})
    @Test(groups = {"events"})
    public void checkDesignationForEvent(String login, String password){
        AddSubscribeWidgetPage addWidgetPage = new LoginPage().doSuccessLogin(login, password)
                .openActivitiesPage()
                .openEventsPage()
                .clickCreateAnEventButton()
                .specifyEventReferenceName(widgetName)
                .specifyEventPublicilyVisibleName(widgetName)
                .clickNextButtonInSetupTab()
                .specifyTicketName(widgetName)
                .clickSaveTicketInfoButton()
                .clickContinueButton()
                .clickNextButtonInTicketsTab()
                .selectLayout("Basic")
                .clickNextButtonInSelectLayoutTab()
                .clickNextButtonInComposeTab()
                .clickPublishOnAutorespondersTab();
        hqHandle = driver.getWindowHandle();
        addWidgetPage.openSubscribeWidget(widgetName);
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
                ).clickSubmitButton();
        getDriver().switchTo().window(hqHandle);
    }


    @Parameters({"login", "password"})
    @Test(groups = {"events"})
    public void checkDesignationForP2P(String login, String password){
        AddP2PPage_PublishedDeatailsTab publishedDeatailsTab = new LoginPage().
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
                        true,
                        "10",
                        "5").
                clickContinueButton().
                clickContinueButton().
                clickNextButton().
                selectLayoutAndClickNext("Basic").
                openCheckoutSubTab().
                editIdLikeToReceiveUpdatesCheckBoxProperties(fieldLabel, "False (Unchecked)").
                checkIdLikeToReceiveUpdatesCheckBoxProperties(fieldLabel, "False (Unchecked)").
                editIdLikeToReceiveUpdatesCheckBoxProperties(fieldLabel, "True (Checked)").
                clickNextToEventPageButton().
                clickNextToTeamTabButton().
                clickNextToAutorespondersTabButton().
                clickPublishButton();
        hqHandle = driver.getWindowHandle();
        publishedDeatailsTab.openWidget(widgetName.toLowerCase());
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
    }

}
