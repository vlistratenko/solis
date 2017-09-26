package com.salsalabs.ignite.automation.pages.hq.manage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import org.apache.http.client.ClientProtocolException;
import org.openqa.selenium.support.ui.Wait;

import com.salsalabs.ignite.automation.common.Browser;
import com.salsalabs.ignite.automation.common.HttpClient;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.DragableElement;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.DragableElementImp;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;
import com.salsalabs.ignite.automation.pages.hq.activities.AddPetitionPage;
//import com.thoughtworks.selenium.webdriven.commands.WaitForCondition;

public class SocialMediaPages  extends ManagePage{
	
	TextBox facebookPageInputField = new TextBoxImpl("//input[contains(@placeholder,'facebook')]" , "Facebook page");
	TextBox twitterPageInputField = new TextBoxImpl("//input[contains(@placeholder,'twitter')]" , "Twitter page");
	TextBox instargamPageInputField = new TextBoxImpl("//input[contains(@placeholder,'insta')]" , "Instagram page");
	TextBox pinterestPageInputField = new TextBoxImpl("//input[contains(@placeholder,'pinterest')]" , "Pinterest  page");
	TextBox youTubePageInputField = new TextBoxImpl("//input[contains(@placeholder,'youtube')]" , "YouTube  page");
	TextBox linkedinPageInputField = new TextBoxImpl("//input[contains(@placeholder,'linkedin')]" , "Twitter page");
	TextBox tumblrPageInputField = new TextBoxImpl("//input[contains(@placeholder,'tumblr')]" , "Twitter page");
	Button savePageButton = new ButtonImpl("//span//ancestor-or-self::button[@id='btnSave']", "Save Socail PAges Button");
	TextBox configurationSavedPopUp = new TextBoxImpl("	//div[@class='feedback-message']" , "Social Media Page Configuration");
	TextBox configurationSavedPopUpCloseUp = new TextBoxImpl("//a[@class='close']" , "Social Media Page Configuration");
	Button saveContentButton = new ButtonImpl("//a[@class='small button primary']" , "SaveContentButton");
	 DragableElement followElement = new DragableElementImp("//span[contains(text(),'Follow')]","Follow Element");
	 Button followBlockEditIcon = new ButtonImpl( "//div[@edit-trigger='VENG_SHOW_EDIT_SOCIAL_ICONS_MODAL']//span[@title='Edit']", "Follow Block");
	 Button followBlock1 = new ButtonImpl("//div[@edit-trigger='VENG_SHOW_EDIT_SOCIAL_ICONS_MODAL']",
			"Follow Block");
	
	 public SocialMediaPages fillTheSocialPagesWithValues(String facebook , String twitter, String instagram, String pinterest, String youTube,
			 String Linkedin, String Tumbler){
		 savePageButton.waitElement(3);
		 facebookPageInputField.clear();
		 facebookPageInputField.type(facebook);
		 twitterPageInputField.clear();
		 twitterPageInputField.type(twitter);
		 instargamPageInputField.clear();
		 instargamPageInputField.type(instagram);
		 pinterestPageInputField.clear();
		 pinterestPageInputField.type(pinterest);
		 youTubePageInputField.clear();
		 youTubePageInputField.type(youTube);
		 linkedinPageInputField.click();
		 linkedinPageInputField.type(Linkedin);
		 tumblrPageInputField.clear();
		 tumblrPageInputField.type(Tumbler);
		 savePageButton.click();
		 sleep(2);
		 for(int i = 0  ; i<3; i ++){
			if( waitConditionBecomesTrue(configurationSavedPopUp.isNotDisplayed(), 3)){
				 if (configurationSavedPopUp.isDisplayed()) {
					 configurationSavedPopUpCloseUp.click();
				 }		
				break;
			} 
		 }
		 if(configurationSavedPopUpCloseUp.isDisplayed()){
			 configurationSavedPopUpCloseUp.click();
		 }
		
		 return this;
	 }
	 
	 public  SocialMediaPages dragFollowElementIntoTheVisualEditor() {
			followElement.dragAndDropElement();
			followBlock1.setAttribute("style", "display: block;");
			followBlockEditIcon.moveAndClick();
			sleep(2);
			return this;
		}
	
	 public  SocialMediaPages verifySocialPagesInsideFollowelementAreDisabled() {
		verifier.verifyEquals(facebookPageInputField.getAttribute("disabled"), "true", "Facebook network is not read Only" ); 
		verifier.verifyEquals(twitterPageInputField.getAttribute("disabled"), "true", "Twitter network is not read Only"); 
		verifier.verifyEquals(instargamPageInputField.getAttribute("disabled"), "true", "Instagram network is not read Only"); 
		verifier.verifyEquals(pinterestPageInputField.getAttribute("disabled"), "true", "Pinterest network is not read Only");
		verifier.verifyEquals(youTubePageInputField.getAttribute("disabled"), "true", "YouTube network is not read Only");
		verifier.verifyEquals(linkedinPageInputField.getAttribute("disabled"), "true", "LinkeDin network is not read Only");
		verifier.verifyEquals(tumblrPageInputField.getAttribute("disabled"), "true", "LinkeDin network is not read Only");
		saveContentButton.click();
			return this;
		}
	 
	 public  SocialMediaPages verifySocialPagesInsideFollowElement(String login, String password, String facebook , String twitter, String instagram , String pinterest, String youTube, String linkedin, String tumbler) {
		
			try {
		HttpClient httpClient = new HttpClient().login(login, password);
		String  getFacebook = httpClient.getListOfSocialPages().get("facebook");
		String  getTwitter = httpClient.getListOfSocialPages().get("twitter");
		String  getInstagram = httpClient.getListOfSocialPages().get("instagram");
		String  getPinterest = httpClient.getListOfSocialPages().get("pinterest");
		String  getyouTube = httpClient.getListOfSocialPages().get("youTube");
		String  getLinkedin = httpClient.getListOfSocialPages().get("linkedin");
		String  getLTumbler = httpClient.getListOfSocialPages().get("tumbler");
		
		verifier.verifyEquals(getFacebook, facebook, "Facebook link is incorrect", true);
		verifier.verifyEquals(getTwitter, twitter, "Twitter link is incorrect", true);
		verifier.verifyEquals(getInstagram, instagram, "Instagram link is incorrect" , true);
		verifier.verifyEquals(getPinterest, pinterest, "Pinterest link is incorrect" , true);
		verifier.verifyEquals(getyouTube, youTube, "YouTube link is incorrect" , true);
		verifier.verifyEquals(getLinkedin, linkedin, "Linkedin link is incorrect" , true);
		verifier.verifyEquals(getLTumbler, tumbler, "tumbler link is incorrect" , true);
			
			} catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException | URISyntaxException
					| IOException e) {
				e.printStackTrace();
			}
		
			return this;
			}
	 
	
	
	

}
