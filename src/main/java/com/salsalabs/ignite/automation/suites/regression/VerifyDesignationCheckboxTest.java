package com.salsalabs.ignite.automation.suites.regression;

import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;
import com.salsalabs.ignite.automation.pages.hq.activities.AddDonationWidgetPage;
import com.salsalabs.ignite.automation.pages.hq.activities.DonationWidget;
import com.salsalabs.ignite.automation.pages.hq.activities.FormFieldConfigurationModalWindow;
import org.apache.commons.lang.RandomStringUtils;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Set;

@Test
public class VerifyDesignationCheckboxTest extends SeleneseTestCase {

    private AddDonationWidgetPage addDonationPage;
    private FormFieldConfigurationModalWindow formFieldConfigurationModal;
    private String widgetName;
    private String widgetDescription;
    private String supporterEmail;

    @Parameters({"login", "password"})
    @Test(priority = 0, enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"payments"})
    public void checkDesignationForFundraising(String login, String password) throws InterruptedException {
        widgetName = "FundraisingFormtName_" + RandomStringUtils.randomAlphanumeric(5);
        widgetDescription = "FundraisingFormDescription_" + RandomStringUtils.randomAlphanumeric(10);
        supporterEmail = "autosupporter" + RandomStringUtils.randomAlphanumeric(4) + "@test.com";
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

    /*@Parameters({"login", "password"})
    @Test(priority = 0, enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"payments"})
    public void checkDesignationForExistingFundraising(String login, String password) throws InterruptedException {

    }

    @Parameters({"login", "password"})
    @Test(priority = 0, enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"payments"})
    public void checkDesignationForNewEvent(String login, String password) throws InterruptedException {

    }

    @Parameters({"login", "password"})
    @Test(priority = 0, enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"payments"})
    public void checkDesignationForExistingEvent(String login, String password) throws InterruptedException {

    }

    @Parameters({"login", "password"})
    @Test(priority = 0, enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"payments"})
    public void checkDesignationForNewP2P(String login, String password) throws InterruptedException {

    }

    @Parameters({"login", "password"})
    @Test(priority = 0, enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"payments"})
    public void checkDesignationForExistingP2P(String login, String password) throws InterruptedException {
    }*/
}
