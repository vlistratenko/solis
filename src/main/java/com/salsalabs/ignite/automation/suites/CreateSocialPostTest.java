package com.salsalabs.ignite.automation.suites;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;

public class CreateSocialPostTest extends SeleneseTestCase {
	
	/**
	 * <b>Create twitter and facebook social posts</b>
	 * <p>
	 * Steps:
	 * <ul>
	 * <li> Login into existing organization
	 * <li> Open Messaging page --> Social Posts
	 * <li> Click on Create a Social Post
	 * <li> Fill name and description
	 * <li> Choose both twitter and facebook media
	 * <li> Fill content
	 * <li> Choose add a link
	 * <li> Use external page
	 * <li> Type 'google.com' as URL 
	 * <li> Select Post now. 
	 * </ul>
	 *  
	 */	

	@Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = { "social" }, description = "")
	@Parameters({"twitterUser", "twitterPassword", "facebookUser", "facebookPassword", "fileName"})
	public void loginAndCreateSocialPost(String twitterUser, String twitterPassword, String facebookUser, String facebookPassword, String fileName) {
		new LoginPage().
		doSuccessLogin().
		openMessagingPage().
		openSocialPostsPage().
		createNewSocialPost().
		fillBasics().
		addSocialMediaAccounts(twitterUser, twitterPassword, facebookUser, facebookPassword).
		chooseMedia(true, true). //first booleans is twitter, second is facebook
		choosePostTypeAndPost(fileName). //if no arguments are passed then link is used
		removeSocialMediaAccounts(); 
	}
}
