package com.salsalabs.ignite.automation.suites.regression;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.pages.hq.HomePage;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;
import com.salsalabs.ignite.automation.pages.hq.activities.AddPetitionPage;
import com.salsalabs.ignite.automation.pages.hq.manage.SocialMediaPages;

public class SocialMediaPagesTest extends SeleneseTestCase {

	@Parameters({ "login", "Passward" , "facebookAccount", "twitterAccount", "instagramAccount", "pinterestAccount", "youTubeAccount" , "linkedinAccount" , "tumblerAccount"})
	@Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = {
			"settings.SocialMediaPages" }, description = "")
	public void verifySocialMediaPagesInTheFollowUpElement(
			
			String login,
			String passward,
			String facebookAccount,
			String twitterAccount,
			String instragramAccount,
			String pinterestAccount,
			String youTubeAccount,
			String linkedinAccount,
			String tumblerAccount) {
		
		String widgetName = "PetitionName_" + RandomStringUtils.randomAlphanumeric(5);
		String 	widgetDescription = "PetitionDescription_" + RandomStringUtils.randomAlphanumeric(10);
		
		new LoginPage().
		doSuccessLogin(login, passward).
		openSettingsPage().
		switchToAddSocialMediaPage().
		fillTheSocialPagesWithValues(facebookAccount, twitterAccount, instragramAccount, pinterestAccount, youTubeAccount, linkedinAccount, tumblerAccount);
		new HomePage().openActivitiesPage().
		openPetitionsPage().
		openAddPetitionPage().
		fillFieldsWidgetStepOne(widgetName, widgetDescription).
		selectLayoutStep("Hero");
		new SocialMediaPages().
		dragFollowElementIntoTheVisualEditor().
		verifySocialPagesInsideFollowelementAreDisabled().
		verifySocialPagesInsideFollowElement(login, passward, facebookAccount, twitterAccount, instragramAccount, pinterestAccount, youTubeAccount, linkedinAccount, tumblerAccount);
		

	}

}