package com.salsalabs.ignite.automation.suites.regression;

import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.HttpClient;
import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;
import com.salsalabs.ignite.automation.pages.hq.activities.AddDonationWidgetPage;
import com.salsalabs.ignite.automation.pages.hq.activities.DonationWidget;
import com.salsalabs.ignite.automation.pages.hq.activities.FormFieldConfigurationModalWindow;
import com.salsalabs.ignite.automation.pages.hq.activities.SubscribeWidget;
import com.salsalabs.ignite.automation.pages.hq.manage.CustomFieldsPage;
import com.salsalabs.ignite.automation.pages.hq.manage.PaymentGatewaysPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONException;
import org.testng.ITestContext;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

@Test(groups = {"eventFormFieldsValidation"})
public class EventFormFieldsValidationTest extends SeleneseTestCase {

        private AddDonationWidgetPage addDonationPage;
        private FormFieldConfigurationModalWindow formFieldConfigurationModal;
        private PaymentGatewaysPage paymentGatewaysPage;
        private String widgetName;
        private String widgetDescription;
        private String supporterEmail;
        private String gatewayName;

    }


