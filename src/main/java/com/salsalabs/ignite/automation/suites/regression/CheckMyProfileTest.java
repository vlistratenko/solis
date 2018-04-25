package com.salsalabs.ignite.automation.suites.regression;

import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.common.TestInfo;
import com.salsalabs.ignite.automation.pages.hq.HomePage;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;
import com.salsalabs.ignite.automation.pages.hq.MyProfilePage;
import org.apache.commons.lang.RandomStringUtils;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Test
public class CheckMyProfileTest extends SeleneseTestCase {

    private String firstName = RandomStringUtils.randomAlphanumeric(10);
    private String lastName = RandomStringUtils.randomAlphanumeric(10);
    private String title = RandomStringUtils.randomAlphanumeric(10);
    private String email = RandomStringUtils.randomAlphanumeric(10) + "@example.com";
    private String phone = CommonUtils.getRandomNumericValueFixedLength(10);

    @TestInfo(statusCell = "G1", listName = "Fundraising")
    @Parameters({"login", "password"})
    @Test(priority = 0, enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"checkMyProfile"})
    public void updateMyProfileData(String login, String password) {
        MyProfilePage myProfilePage = new LoginPage().
                doSuccessLogin(login, password).
                openMyProfilePage().
                updatePersonalData(firstName, lastName, title, email, phone).
                saveChanges();

        new HomePage().
                openDashboard().
                openMyProfilePage();

        myProfilePage.checkUpdatedData(firstName, lastName, title, email, phone);
    }

    @Parameters({"login", "password"})
    @Test(priority = 1, enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {"checkMyProfile"})
    public void updateMyProfileDataWithoutSaving(String login, String password) {
        new LoginPage().
                doSuccessLogin(login, password).
                openMyProfilePage().
                updatePersonalData("Fname", "Lname", "Title", "email@example.com", "1234567890").
                cancelChanges();

        new HomePage().openMyProfilePage();

        new MyProfilePage().checkUpdatedData(firstName, lastName, title, email, phone);
    }
}
