package com.salsalabs.ignite.automation.tests.old;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.PropertyName;
import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;

//old  not working assets core, need to be removed
public class AddAssetImageTest extends SeleneseTestCase {
	
	/*@Parameters({"imageToUpload"})
	
	@Test(retryAnalyzer=RetryAnalyzer.class, groups={"qa2"})
	public void addNewAssetImage(String imageToUpload) {
		
		LoginPage page = new LoginPage();
		
		page.doSuccessLogin(CommonUtils.getProperty(PropertyName.ASSETS_USERNAME), CommonUtils.getProperty(PropertyName.ASSETS_PASSWORD)).
		openAssetsPage().
		clickAddAnAssetButton().
		uploadImageByName(imageToUpload).
		verifyNewImageIsUploaded(imageToUpload);		
		
	}
	
	@Test(retryAnalyzer=RetryAnalyzer.class, groups={"qa3"})
	public void checkElementsPresence() {
		
		LoginPage page = new LoginPage();
		
		page.
		doSuccessLogin(CommonUtils.getProperty(PropertyName.ASSETS_USERNAME), CommonUtils.getProperty(PropertyName.ASSETS_PASSWORD)).
		openAssetsPage().
		verifyDefaultElemetsArePresent();
		
	}
	
	@Parameters({"imageToDelete"})
	
	@Test(retryAnalyzer=RetryAnalyzer.class, groups={"qa4"})
	public void deleteAddedImage(String imageToDelete) {
		
		LoginPage page = new LoginPage();
		page.doSuccessLogin(CommonUtils.getProperty(PropertyName.ASSETS_USERNAME), CommonUtils.getProperty(PropertyName.ASSETS_PASSWORD)).
		openAssetsPage().
		searchAndDeleteImage(imageToDelete).
		verifyImageIsDeleted(imageToDelete);
		
	}*/

}
