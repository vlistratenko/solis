package com.salsalabs.ignite.automation.suites.regression;

import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;

public class AssetImageTest extends SeleneseTestCase {

	@Parameters({"login", "Passward", "imageToUpload"})
	@BeforeGroups(enabled = true, groups = {"assetImage"})
	public void removeRedundantImages(String login, String password, String imageToUpload){
		LoginPage page = new LoginPage();
		page.doSuccessLogin(login, password).
				openAssetsPage().
				searchAndDeleteAllImagesMatchingName(imageToUpload);
	}

	@Parameters({ "login", "Passward", "imageToUpload" })
	@Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = { "assetImage" })
	public void addNewAssetImage(String login, String passward, String imageToUpload) {
		LoginPage page = new LoginPage();
		page.doSuccessLogin(login, passward).
		openAssetsPage().
		clickAddAnAssetButton().
		uploadImageByName(imageToUpload).
		verifyNewImageIsUploaded(imageToUpload);
	}

	@Parameters({ "login", "Passward", "imageToUpload" })
	@Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = { "assetImage" } , dependsOnMethods = {"addNewAssetImage"})
	public void deleteAddedImage(String login, String passward, String imageToUpload) {
		LoginPage page = new LoginPage();
		page.doSuccessLogin(login, passward).
		openAssetsPage().
		searchAndDeleteImage(imageToUpload).
		verifyImageIsDeleted(imageToUpload);
	}
}