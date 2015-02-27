package pages.HQ.Assets;

import objects.Button;
import objects.Panel;
import objects.Table;
import objects.TextBox;
import pages.HQ.HomePage;

public class AssetsPage extends HomePage {
	
	Button addAnAsset = new Button ("//*[@class='tiny button no-outline ng-binding']", "+ Add an Asset button");
	Button uploadImageBigField = new Button ("//*[@class='panel'][@file-upload-panel]", "Upload image big field");
	Button listView = new Button ("//*[contains(@ng-click, 'details')]", "List view button");
	Button tableView = new Button ("//*[contains(@ng-click, 'grid')]", "Grid view button");
	TextBox fileField = new TextBox("//input[@id='imageUpload']", "Image", false);
	Table thumbNails = new Table ("//*[@class='thumbnailItem ng-scope']", "List of Images");
	Panel imageUploading = new Panel ("//*[@class='imgPreviewWrapper']","Panel with the image currently being uploaded");
	Panel assetsHeader = new Panel (".//*[@class='icon ng-binding'][.='Assets']","Assets page header");
	TextBox seachField = new TextBox("//h1[.='Assets']/../../../descendant::*[@ng-model='searchInput']", "Search field");
	Button startSearchButton = new Button("//h1[.='Assets']/../../../descendant::*[@ng-click='filterListing(searchInput)']", "Start search button");
	Button topPartOfEveryImages = new Button(".//*[@class='thumbnailTop']", "Top part of every image");
	
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
			
			waitConditionBecomesTrue(!imageUploading.isDisplayed(), 2000);
						
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
		
		Button image = new Button ("//*[@class='thumbnailItem ng-scope']/descendant::h6[(contains(text(), '" + uploadedImage + "'))]", "Required image");
		
		verify(image.isDisplayed(), true, "Requested image is not present in a list");
		
		return this;
		
	}
	
	public AssetsPage verifyDefaultElemetsArePresent() {
		
		verify(listView.isDisplayed(), true, "List view button is not displayed");
		verify(tableView.isDisplayed(), true, "Table view button is not displayed");
		verify(addAnAsset.isDisplayed(), true, "Add an Asset button is not displayed");
		verify(assetsHeader.isDisplayed(), true, "Asset header is not displayed");
		verify(seachField.isDisplayed(), true, "Search field is not displayed");
		verify(startSearchButton.isDisplayed(), true, "Search button is not displayed");
		return this;
	}
	
	public AssetsPage verifyImageIsDeleted (String imageName) {
		
		Button image = new Button ("//*[@class='thumbnailItem ng-scope']/descendant::h6[(contains(text(), '" + imageName + "'))]", "Image that need to be deleted");
		
		verify(image.isDisplayed(), true, "Requested image is not deleted");
		
		return this;
			
		
	}
		
}
