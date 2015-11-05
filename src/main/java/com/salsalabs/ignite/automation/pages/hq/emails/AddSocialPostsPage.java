package com.salsalabs.ignite.automation.pages.hq.emails;

import org.apache.commons.lang3.RandomStringUtils;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;
import com.salsalabs.ignite.automation.pages.hq.HomePage;

public class AddSocialPostsPage extends HomePage{
	
	private Button selectTwitter = new ButtonImpl("//li[@class='socialAccountlist_item ng-scope'][1]/img", "Select twitter account");
	private Button selectFacebook = new ButtonImpl("//li[@class='socialAccountlist_item ng-scope'][2]/img", "Select facebook account");
	private Button addSocialPost = new ButtonImpl("//button[@ng-click='createPost()']", "Hit + Create a Social Post");
	private Button addItButton = new ButtonImpl("//button[@autotest-id='btn_save_insert_external_link']", "Add it!");
	private Button extPageTab = new ButtonImpl("//a[contains(text(), 'An External Page')]", "An External Page tab");
	private Button chooseALinkButton = new ButtonImpl("//button[contains(text(), 'Choose a link')]", "Choose a link");
	private Button publishButton = new ButtonImpl("//button[@id='btnPublish']", "Next: Schedule This Post");
	private Button publishMessage = new ButtonImpl("//button[@id='btnpublishMessage']", "Next: Send it!");
	private Button btnCompose = new ButtonImpl("//button[@id='btnCompose']", "Next: Compose Your Post »");
	private TextBox postNameField = new TextBoxImpl("//input[@name='name']", "Social post name");
	private TextBox postDescriptionField = new TextBoxImpl("//textarea[@name='description']", "Social post Description");
	private TextBox postContentField = new TextBoxImpl("//textarea[@name='content']", "Let's write the post!");
	private TextBox extLink = new TextBoxImpl("//input[@id='externalLinkUrl']", "External page URL");
	private String postName = "SocialPost_" + RandomStringUtils.randomAlphanumeric(5);
	private String postDescription = "Description_" + RandomStringUtils.randomAlphanumeric(10);
	private String postContent = "PostContent_" + RandomStringUtils.randomAlphanumeric(20);
	
	public AddSocialPostsPage createNewSocialPost() {
		addSocialPost.click();
		return new AddSocialPostsPage();
	}
	
	public AddSocialPostsPage fillBasics() {
		postNameField.type(postName); 
		postDescriptionField.type(postDescription);
		postDescriptionField.click();
		btnCompose.click();
		sleep(5);
		return new AddSocialPostsPage();
	}
	
	private void chooseMedia(boolean twitter, boolean facebook) {
		sleep(5);
		if (twitter) selectTwitter.click();
		if (facebook) selectFacebook.click();
	}
	
	public AddSocialPostsPage chooseMediaTypeAndUseLink(boolean twitter, boolean facebook) {
		chooseMedia(twitter, facebook);
		postContentField.type(postContent);
		chooseALinkButton.click();
		extPageTab.click();
		extLink.type("google.com");
		addItButton.click();
		publishButton.click();
		publishMessage.click();
		return new AddSocialPostsPage();
	}

}
