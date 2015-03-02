package tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import core.util.CommonUtils;
import core.util.PropertyName;
import core.util.SeleneseTestCase;
import pages.hq.LoginPage;

public class AddAssetImageTest extends SeleneseTestCase {
	
	@Parameters({"imageToUpload"})
	
	@Test (groups={"qa2"})
	public void addNewAssetImage(String imageToUpload) {
		
		LoginPage page = new LoginPage();
		
		page.doSuccessLogin(CommonUtils.getProperty(PropertyName.ASSETS_USERNAME), CommonUtils.getProperty(PropertyName.ASSETS_PASSWORD)).
		openAssetsPage().
		clickAddAnAssetButton().
		uploadImageByName(imageToUpload).
		verifyNewImageIsUploaded(imageToUpload);		
		
	}
	
	@Test (groups={"qa3"})
	public void checkElementsPresence() {
		
		LoginPage page = new LoginPage();
		
		page.
		doSuccessLogin(CommonUtils.getProperty(PropertyName.ASSETS_USERNAME), CommonUtils.getProperty(PropertyName.ASSETS_PASSWORD)).
		openAssetsPage().
		verifyDefaultElemetsArePresent();
		
	}
	
	@Parameters({"imageToDelete"})
	
	@Test (groups={"qa4"})
	public void deleteAddedImage(String imageToDelete) {
		
		LoginPage page = new LoginPage();
		page.doSuccessLogin(CommonUtils.getProperty(PropertyName.ASSETS_USERNAME), CommonUtils.getProperty(PropertyName.ASSETS_PASSWORD)).
		openAssetsPage().
		deleteImage(imageToDelete).
		verifyImageIsDeleted(imageToDelete);
		
	}

}
