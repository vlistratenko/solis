package com.salsalabs.ignite.automation.suites.regression;

import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.PropertyName;
import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;
import com.salsalabs.ignite.automation.pages.p2p.AddP2PPage;
import com.salsalabs.ignite.automation.pages.p2p.AddP2PPage_EventPageTab_CheckoutSubTab;
import com.salsalabs.ignite.automation.pages.p2p.Eventp2pWidget;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Test(groups = {"events ", "regression", "new_data", "iWouldLikeToMakeADonationCheckboxLabelTest"})
public class IWouldLikeToMakeADonationCheckboxLabelTest extends SeleneseTestCase {

    LoginPage loginPage;
    String formName;
    String supporterEmail;
    String fundraiserEmail;
    String fundraiserFirstName;
    String fundraiserLastName;
    String fundraiserPassword;

    @Parameters({ "login", "password"})
    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, priority = 1)
    public void p2pIWouldLikeToMakeADonationCheckboxDefaultLabelTest(String login, String pass) throws Exception {
        loginPage = new LoginPage();
        formName = "p2p form " + CommonUtils.getUnicName();
        supporterEmail = "autosupporter" + RandomStringUtils.randomAlphanumeric(4) + "@test.com";
        fundraiserEmail = "fund" + RandomStringUtils.randomAlphanumeric(4) + "@test.com";
        fundraiserFirstName = "fName" + RandomStringUtils.randomAlphanumeric(4);
        fundraiserLastName = "lName" + RandomStringUtils.randomAlphanumeric(4);
        fundraiserPassword = "qwertyui";

                loginPage.doSuccessLogin(login, pass).
                openActivitiesPage().
                openP2PPage().
                openCreateNewp2pForm().
                fillSetupStepAndGoNext(formName,
                        "p2p form " + CommonUtils.getUnicName(),
                        CommonUtils.getTodayDateDependsOnBrowser(""),
                        "8:30am",
                        CommonUtils.getTodayDateDependsOnBrowser("", 30),
                        "7:00pm",
                        "(GMT-06:00) Central Time",
                        true).
                fillRegistrationStepAndGoNext("p2p registration " + CommonUtils.getUnicName(),
                        true,
                        true,
                        "10",
                        "5").
                clickContinueButton().
                clickContinueButton().
                clickNextButton().
                selectLayoutAndClickNext("Basic").
                openEventPageSubTab().
                clickNextToEventPageButton().
                clickNextToTeamTabButton().
                openTeamFundraisingPageSubTab().
                clickNextToAutorespondersTabButton().
                clickPublishButton().
                openWidget(formName);

        new Eventp2pWidget().
                openp2pEventRegistrationPage().
                clickNextButtonOnRegistrationTypesPage().
                fillp2pEventRegistrationForm(supporterEmail,"FirstName","LastName").
                clickNextButtonOnRegistrationDetailsStep().
                clickDoThisLaterButton().
                clickOnGoToCheckoutButton().
                verifyDefaultGeneralIWouldLikeToMakeADonationCheckboxLabel();

        //driver.get("https://testvco2712.test.igniteaction.net/p2pform20180618171737/index.html");

        new Eventp2pWidget().
                openp2pEventRegistrationPage().
                clickNextButtonOnRegistrationTypesPage().
                fillp2pEventRegistrationForm(supporterEmail,fundraiserFirstName,fundraiserLastName).
                clickNextButtonOnRegistrationDetailsStep().
                fillFundraiserSignInForm(fundraiserFirstName, fundraiserLastName, fundraiserEmail, fundraiserPassword, fundraiserPassword, false).
                clickNextButtonOnRegistrationDetailsStep().
                clickOnGoToCheckoutButton().
                verifyDefaultFundraiserIWouldLikeToMakeADonationCheckboxLabel();
    }

    @Parameters({ "login", "password"})
    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, priority = 4)
    public void p2pIWouldLikeToMakeADonationCheckboxCustomizedLabelTest(String login, String pass) throws Exception {
        supporterEmail = "autosupporter" + RandomStringUtils.randomAlphanumeric(4)+"@test.com";
        fundraiserEmail = "fund" + RandomStringUtils.randomAlphanumeric(4) + "@test.com";
        fundraiserFirstName = "fName" + RandomStringUtils.randomAlphanumeric(4);
        fundraiserLastName = "lName" + RandomStringUtils.randomAlphanumeric(4);
        fundraiserPassword = "qwertyui";

        AddP2PPage_EventPageTab_CheckoutSubTab page = new LoginPage().
                doSuccessLogin(login, pass).
                openP2PFormByFullUrl("https://hq.test.igniteaction.net/#/activities/widgets/p2p/4ecbda51-0883-4aaf-b6d7-7b083fa7fa44?tab=compose").
                //openP2PFormByFullUrl(CommonUtils.getProperty(PropertyName.P2P_FORM_HQ_LINK)).
                openP2PFormEventPageTab().
                openCheckoutSubTab();
        page.editVEField(" I would like to make a donation ").
                setIWouldLikeToMakeADonationCheckboxGeneralLabel("General" + RandomStringUtils.randomAlphanumeric(4)).
                setIWouldLikeToMakeADonationCheckboxFundraisersLabel("Fundraiser" + RandomStringUtils.randomAlphanumeric(4)).
                saveFormFieldConfiguration();
        page.clickNextToEventPageButton().
                clickNextToTeamTabButton().
                clickOnRepublishButton().
                openWidget("p2p form 20180619173832");
                //openWidget(formName);
                       //openWidget("p2p form 20180619130546");

        new Eventp2pWidget().
                openp2pEventRegistrationPage().
                clickNextButtonOnRegistrationTypesPage().
                fillp2pEventRegistrationForm(supporterEmail,"FirstName","LastName").
                clickNextButtonOnRegistrationDetailsStep().
                clickDoThisLaterButton().
                clickOnGoToCheckoutButton().
                verifyNewGeneralIWouldLikeToMakeADonationCheckboxLabel();

//        driver.get("https://testvco2712.test.igniteaction.net/p2pform20180618171737/index.html");

        new Eventp2pWidget().
                openp2pEventRegistrationPage().
                clickNextButtonOnRegistrationTypesPage().
                fillp2pEventRegistrationForm(supporterEmail,fundraiserFirstName,fundraiserLastName).
                clickNextButtonOnRegistrationDetailsStep().
                fillFundraiserSignInForm(fundraiserFirstName, fundraiserLastName, fundraiserEmail, fundraiserPassword, fundraiserPassword, false).
                clickNextButtonOnRegistrationDetailsStep().
                clickOnGoToCheckoutButton().
                verifyNewFundraiserIWouldLikeToMakeADonationCheckboxLabel();
    }
}
