package com.salsalabs.ignite.automation.pages.hq.assets;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.Panel;
import com.salsalabs.ignite.automation.elements.Table;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.PanelImpl;
import com.salsalabs.ignite.automation.elements.impl.TableImpl;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;
import com.salsalabs.ignite.automation.pages.hq.HomePage;

public class AssetsPage extends HomePage {

	Button addAnAsset = new ButtonImpl("//button[contains(text(), 'Add')]", "+ Add an Asset button");
	Button uploadImageBigField = new ButtonImpl("//*[@class='panel'][@file-upload-panel]", "Upload image big field");
	Button listView = new ButtonImpl("//*[contains(@ng-click, 'details')]", "List view button");
	Button tableView = new ButtonImpl("//*[contains(@ng-click, 'grid')]", "Grid view button");
	TextBox fileField = new TextBoxImpl("//input[@name='fileUpload']", "Image", false);
	Table thumbNails = new TableImpl("//*[@class='thumbnailItem ng-scope']", "List of Images");
	Panel imageUploading = new PanelImpl("//*[@class='imgPreviewWrapper']",
			"Panel with the image currently being uploaded");
	Panel assetsHeader = new PanelImpl(".//*[@class='icon ng-binding'][.='Assets']", "Assets page header");
	TextBox seachField = new TextBoxImpl("//h1[.='Assets']/../../../descendant::*[@ng-model='searchInput']",
			"Search field");
	Button startSearchButton = new ButtonImpl(
			"//h1[.='Assets']/../../../descendant::*[@ng-click='filterListing(searchInput)']", "Start search button");
	Button topPartOfEveryImages = new ButtonImpl(".//*[@class='thumbnailTop']", "Top part of every image");
	TextBox assertSerachInput = new TextBoxImpl("//input[@placeholder='search...']", "Search field");
	Button assertSerachButton = new ButtonImpl("//button[@autotest-id='btn_search_assets_dashboard']", "Assert search  button");
	Button deleteButton = new ButtonImpl("//span[contains(text(), 'Delete')]/ancestor:: button", "Delete Button");
	Button confirmDeleteButton = new ButtonImpl("//span[contains(text(), 'Yes')]/ancestor:: button", "Confirm the Delete Button");
	Button assetFallBackMessage = new ButtonImpl("//div[@class='feedback-message']", "Asset FallBackMessage");
	Button closeFallBackMessage = new ButtonImpl("//a[@class='close']", "Close Asset FallBackMessage");
	
	public AssetsPage clickAddAnAssetButton() {
		for (int i = 0; i < 3; i++) {
			if (waitConditionBecomesTrue(addAnAsset.isDisplayed(), 5));
			break;
		}
		addAnAsset.click();
		return new AssetsPage();

	}

	public AssetsPage clickUploadImageBigField() {
		uploadImageBigField.click();
		return new AssetsPage();

	}

	public AssetsPage uploadImageByName(String imageToUpload) {
		fileField.uploadAssetsImage("images\\" + imageToUpload, imageToUpload);
		if (imageUploading.isDisplayed()) {
			waitConditionBecomesTrue(!imageUploading.isDisplayed(), 4);
		} else {
			return new AssetsPage();
		}
		return new AssetsPage();

	}

	public AssetsPage searchAndDeleteImage(String imageToDelete) {
		assertSerachInput.type(imageToDelete);
		assertSerachButton.click();
		for (int i = 0; i < 3; i++) {
			if (waitConditionBecomesTrue(topPartOfEveryImages.isDisplayed(), 5));
			break;
		}
		topPartOfEveryImages.moveAndClick();
		waitConditionBecomesTrue(deleteButton.isDisplayed(), 4);
		deleteButton.click();
		waitConditionBecomesTrue(confirmDeleteButton.isDisplayed(), 4);
		confirmDeleteButton.click();
		return new AssetsPage();
	}

	public AssetsPage verifyNewImageIsUploaded(String uploadedImage) {
		sleep(2);
		for(int i = 0; i<3; i++){
			if (waitConditionBecomesTrue(assetFallBackMessage.isDisplayed(), 4)){
				if(assetFallBackMessage.isDisplayed()){
					closeFallBackMessage.click();
				}
				break;
		}	
		}
		
		topPartOfEveryImages.scrollIntoView();
		Button image = new ButtonImpl(
				".//*[@class='thumbnailTop']/following-sibling::div/descendant::*[.='"+uploadedImage+"']",
				"Required image");
		verifier.verifyElementIsDisplayed(image);
		return this;

	}

	public AssetsPage verifyDefaultElemetsArePresent() {
		verifier.verifyElementIsDisplayed(listView, tableView, addAnAsset, assetsHeader, seachField, startSearchButton);
		return this;
	}

	public AssetsPage verifyImageIsDeleted(String imageName) {
		sleep(3);
		waitConditionBecomesTrue(topPartOfEveryImages.isDisplayed(), 4);
		topPartOfEveryImages.scrollIntoView();
		Button image = new ButtonImpl(
				".//*[@class='thumbnailTop']/following-sibling::div/descendant::*[.='" + imageName + "']",
				"Deleted image");
		verifier.verifyElementIsNotDisplayed(true, image);
		return this;

	}

}
