package com.salsalabs.ignite.automation.suites;

import org.testng.annotations.Test;
import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;
import com.salsalabs.ignite.automation.pages.hq.emails.AddSocialPostsPage;


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
	
	private AddSocialPostsPage addSocialPostPage = new AddSocialPostsPage();
		
	@Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = { "social" }, description = "")
	public void loginAndCreateSocialPost() {
		doLoginAndOpenDonationForms();
		addSocialPostPage.
		createNewSocialPost().
		fillBasics().
		chooseMediaTypeAndUseLink(true, true);
	}
	
	private void doLoginAndOpenDonationForms() {
		addSocialPostPage = new LoginPage().doSuccessLogin().openMessagingPage().openSocialPostsPage();
	}
}
