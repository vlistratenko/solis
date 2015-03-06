package com.salsalabs.ignite.automation.pages.hq.assets;

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
	
	Button addAnAsset = new ButtonImpl ("//*[@class='tiny button no-outline ng-binding']", "+ Add an Asset button");
	Button uploadImageBigField = new ButtonImpl ("//*[@class='panel'][@file-upload-panel]", "Upload image big field");
	Button listView = new ButtonImpl ("//*[contains(@ng-click, 'details')]", "List view button");
	Button tableView = new ButtonImpl ("//*[contains(@ng-click, 'grid')]", "Grid view button");
	TextBox fileField = new TextBoxImpl("//input[@id='imageUpload']", "Image", false);
	Table thumbNails = new TableImpl ("//*[@class='thumbnailItem ng-scope']", "List of Images");
	Panel imageUploading = new PanelImpl ("//*[@class='imgPreviewWrapper']","Panel with the image currently being uploaded");
	Panel assetsHeader = new PanelImpl (".//*[@class='icon ng-binding'][.='Assets']","Assets page header");
	TextBox seachField = new TextBoxImpl("//h1[.='Assets']/../../../descendant::*[@ng-model='searchInput']", "Search field");
	Button startSearchButton = new ButtonImpl("//h1[.='Assets']/../../../descendant::*[@ng-click='filterListing(searchInput)']", "Start search button");
	Button topPartOfEveryImages = new ButtonImpl(".//*[@class='thumbnailTop']", "Top part of every image");
	
	public AssetsPage clickAddAnAssetButton() {
		
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
			
			waitConditionBecomesTrue(!imageUploading.isDisplayed(), 2);
						
		} else {return new AssetsPage();}
		
		return new AssetsPage(); 

	}
	
	public AssetsPage deleteImage(String imageToDelete) {
		
		topPartOfEveryImages.addPath("/following-sibling::div/descendant::*[.='" + imageToDelete + "']");
		topPartOfEveryImages.moveAndClick();
		return new AssetsPage();
	}
	
	
	//Verifiers
	
	public AssetsPage verifyNewImageIsUploaded (String uploadedImage) {
		
		Button image = new ButtonImpl ("//*[@class='thumbnailItem ng-scope']/descendant::h6[(contains(text(), '" + uploadedImage + "'))]", "Required image");
		
		verifier.verifyElementIsDisplayed(image);
		
		return this;
		
	}
	
	public AssetsPage verifyDefaultElemetsArePresent() {
		verifier.verifyElementIsDisplayed(
				listView, 
				tableView, 
				addAnAsset, 
				assetsHeader, 
				seachField, 
				startSearchButton);
		return this;
	}
	
	public AssetsPage verifyImageIsDeleted (String imageName) {
		
		Button image = new ButtonImpl ("//*[@class='thumbnailItem ng-scope']/descendant::h6[(contains(text(), '" + imageName + "'))]", "Image that need to be deleted");
		
		verifier.verifyElementIsDisplayed(image);
		
		return this;
			
		
	}
		
}
