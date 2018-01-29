package com.salsalabs.ignite.automation.suites.regression;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;

public class RecurringDonationTest extends SeleneseTestCase {

	@Parameters({ "fundraisingFormYearlyRecurring", "login", "Passward", "widgetNameYearly" })
	@Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = { "recurringDonationTest" }, description = "")
	public void verifyYearlyRecurringDonationTest(String widgetWithYearlyRecurringOption, String login, String passward,
			String widgenName) {

		String amount = CommonUtils.getRandomValueFromTo(10, 200, 0);
		String email = RandomStringUtils.randomAlphabetic(5) + ".74580786@mailosaur.io";
		String supporterFirstname = RandomStringUtils.randomAlphabetic(5) + "supporterFirstname";
		String supporterLastname = RandomStringUtils.randomAlphabetic(5) + "supporterLastname";
		String supporterAddress = RandomStringUtils.randomAlphabetic(5) + "supporterAddress";
		int year = LocalDate.now().getYear() + CommonUtils.getRandomValueNumericFromTo(2, 9);

		LoginPage lp = new LoginPage();
		lp.openDonationWidgetByLink(widgetWithYearlyRecurringOption)
				.fillTheFirstStepOfTheDonationForm(amount, true, false)
				.fillTheSecondStepOfTheDonationForm(email, supporterFirstname, supporterLastname, supporterAddress,
						"Juneau", "99501", "AL")
				.fillTheThirdStepOfTheDonationForm("4111111111111111", "123", "01", String.valueOf(year))
				.clickDonationButtonNewForms();
		lp.doSuccessLogin(login, passward).
		openDonationsPage().
		openDonation(widgenName)
				.verifyNumberOfYearRecurringInstallmentsInTheTable(year);
	}

	@Parameters({ "fundraisingFormMonthlyRecurring", "login", "Passward", "widgetNameMonthly" })
	@Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = { "recurringDonationTest" }, description = "")
	public void verifyMonthlyRecurringDonationTest(String widgetWithMonthlyRecurringOption, String login,
			String passward, String widgenName) {
		String amount = CommonUtils.getRandomValueFromTo(10, 200, 0);
		String email = RandomStringUtils.randomAlphabetic(5) + ".74580786@mailosaur.io";
		String supporterFirstname = RandomStringUtils.randomAlphabetic(5) + "supporterFirstname";
		String supporterLastname = RandomStringUtils.randomAlphabetic(5) + "supporterLastname";
		String supporterAddress = RandomStringUtils.randomAlphabetic(5) + "supporterAddress";
		int year = LocalDate.now().getYear() + CommonUtils.getRandomValueNumericFromTo(0, 3);
		int monthValue = LocalDate.now().getMonthValue();
		LocalDate now = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
		String currentMonthInISOFormat = now.format(formatter).substring(5, 7);
		LoginPage lp = new LoginPage();
		lp.openDonationWidgetByLink(widgetWithMonthlyRecurringOption)
				.fillTheFirstStepOfTheDonationForm(amount, true, false)
				.fillTheSecondStepOfTheDonationForm(email, supporterFirstname, supporterLastname, supporterAddress,	"Juneau", "99501", "AL")
				.fillTheThirdStepOfTheDonationForm("4111111111111111", "123", currentMonthInISOFormat, String.valueOf(year))
				.clickDonationButtonNewForms();
		lp.doSuccessLogin(login, passward).
		openDonationsPage().
		openDonation(widgenName).
		verifyNumberOfMonthlyRecurringInstallmentsInTheTable(year, monthValue);

	}

}
