package tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.HQ.LoginPage;
import selenium.CommonUtils;
import selenium.SeleneseTestCase;

public class AddAssetImageTest extends SeleneseTestCase {
	
	@Parameters({"imageToUpload"})
	
	@Test (groups={"qa2"})
	public void addNewAssetImage(String imageToUpload) {
		
		LoginPage page = new LoginPage();
		
		page.doSuccessLogin(CommonUtils.getProperty("assets.username"), CommonUtils.getProperty("assets.password")).
		openAssetsPage().
		clickAddAnAssetButton().
		uploadImageByName(imageToUpload).
		verifyNewImageIsUploaded(imageToUpload);		
		
	}
	
	@Test (groups={"qa3"})
	public void checkElementsPresence() {
		
		LoginPage page = new LoginPage();
		
		page.
		doSuccessLogin(CommonUtils.getProperty("assets.username"), CommonUtils.getProperty("assets.password")).
		openAssetsPage().
		verifyDefaultElemetsArePresent();
		
	}
	
	@Parameters({"imageToDelete"})
	
	@Test (groups={"qa4"})
	public void deleteAddedImage(String imageToDelete) {
		
		LoginPage page = new LoginPage();
		page.doSuccessLogin(CommonUtils.getProperty("assets.username"), CommonUtils.getProperty("assets.password")).
		openAssetsPage().
		deleteImage(imageToDelete).
		verifyImageIsDeleted(imageToDelete);
		
	}

}
