package com.salsalabs.ignite.automation.suites.regression;

import com.salsalabs.ignite.automation.common.*;
import com.salsalabs.ignite.automation.pages.hq.HomePage;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;
import com.salsalabs.ignite.automation.pages.hq.manage.UnsubscribePage;
import org.json.JSONException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class VerifySubscriptionManagementInEmail extends SeleneseTestCase {

    @Parameters({ "sendEmail.from", "login", "password"})
    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = { "email.verifySubscriptionManagement" }, description = "")
    public void sendEmailBlastTest(String emailFrom, String login, String password) throws InterruptedException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, JSONException, IOException, URISyntaxException {
        String messageTopic;
        String emailBlastName = CommonUtils.getRandomNumericValueFixedLength(10);
        String emailAddress = CommonUtils.getRandomNumericValueFixedLength(10) + "@testauto.ignite.net";
        String emailSubject = emailBlastName;
        CommonUtils.setProperty(PropertyName.EMAIL_FROM, emailFrom);
        CommonUtils.setProperty(PropertyName.EMAIL_BLAST_NAME, emailBlastName);
        CommonUtils.setProperty(PropertyName.EMAIL_SUBJECT, emailSubject);
        new HttpClient(SeleneseTestCase.USED_ENVIRONMENT.getBaseTestUrl()).login(login, password).createSupporter(new Supporter().getSupporterJSON(emailAddress));
        LoginPage loginPage = new LoginPage();
        loginPage.
                doSuccessLogin(login, password).
                openMessagingPage().
                openEmailBlastsPage().
                openAddEmailPage().
                fillAllFieldsAndGoForward(emailBlastName).
                selectAudienceType("Selected segments of your list, or specific supporters").//(""Entire list ").
                addSupporters(emailAddress, 1, "").
                openComposePage().
                selectLayout("Basic").
                fillAllFieldsAndGoForward(emailSubject, emailFrom, 1).
                fillAllFieldsAndPublish(100, 1);
        String currentUrl = getDriver().getCurrentUrl();
        getDriver().get(SeleneseTestCase.emailClient.getUnsubscribeLink(emailSubject));
        Thread.sleep(3000);
        UnsubscribePage unsubscribePage = new UnsubscribePage();
        messageTopic = unsubscribePage.getRandomMessageTopic();
        unsubscribePage.
                pickTopic(messageTopic).
                fillUnsubscribeEmail(emailAddress).
                clickUnsubscribeButton();
        getDriver().get(currentUrl);
        new HomePage().
                openAudiencePage().
                openSupportersPage().
                searchSupporter(emailAddress).
                openSupporterDetailsPage().
                verifySupporterSubscriptionTopics(messageTopic, emailAddress);
    }

}
