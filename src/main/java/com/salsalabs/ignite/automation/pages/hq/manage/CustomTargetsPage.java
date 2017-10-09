package com.salsalabs.ignite.automation.pages.hq.manage;
 
import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.PropertyName;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.DropDown;
import com.salsalabs.ignite.automation.elements.Table;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.CheckBoxImpl;
import com.salsalabs.ignite.automation.elements.impl.DropDownImpl;
import com.salsalabs.ignite.automation.elements.impl.TableImpl;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;
 
public class CustomTargetsPage extends ManagePage {
 
    // Locators
 
    Button addCustomTargetButton = new ButtonImpl(
            "//*[@id='dashboard']//button//*[contains(text(), 'Add a Custom Target')]",
            "Add a Custom target button");
    DropDown categoryList = new DropDownImpl(
            "//validation-message[@field-label='Category']/following::custom-select2/div[@class='custom dropdown']",
            "//validation-message[@field-label='Category']/following::custom-select2/div/a",
            "Category dropdown");
    TextBox firstName = new TextBoxImpl("//*[@name='firstName']",
            "First Name text field", true);
    TextBox lastName = new TextBoxImpl("//*[@field-label='Last Name']//input",
            "Last Name text field", true);
    TextBox title = new TextBoxImpl("//*[@field-label='Title']//input",
            "Title text field", true);
    TextBox emailAddress = new TextBoxImpl("//*[@name='email']",
            "Email Address text field", true);
    TextBox city = new TextBoxImpl("//*[@name='city']", "City text field",
            false);
    DropDown statesList = new DropDownImpl(
            "//*[@field-label='State/Province/Region']/following-sibling::*/*[@class='custom dropdown']",
            "//*[@field-label='State/Province/Region']/following-sibling::*/*[@class='custom dropdown']/a",
            "States list");
    TextBox zipCode = new TextBoxImpl("//*[@name='zipDistrictCode']",
            "ZIP/Postal Code text field", false);
    TextBox facebookAccountUrl = new TextBoxImpl("//*[@id='facebookPage']",
            "Facebook account text field", false);
    TextBox twitterAccountUrl = new TextBoxImpl("//*[@id='twitterPage']",
            "Twitter account text field", false);
    Button createButton = new ButtonImpl("//*[@id='btnSubmit']",
            "Create Custom Target button", true);
    Button saveButton = new ButtonImpl("//*[@id='btnSave']",
            "Save Custom Target button", true);
    Table tableWithCustomTargets = new TableImpl("//table",
            "Table with existing Custom Targets");
    DropDown actionsList = new DropDownImpl(
            "//*[@id='dashboard']//*[@class='custom dropdown']", "Actions list");
    Button confirmCustomTargetDeletion = new ButtonImpl(
            "//*[@class='appModal small']//button//span[contains(text(),'Delete')]",
            "Confirm Custom Target deletion button");
    DropDown showItemsPerPageList = new DropDownImpl(
            "//form//*[@class='custom dropdown']", "Items per page list");
 
    public String getFirstNameFieldValue() {
        return firstName.getValue();
    }
 
    public String getLastNameFieldValue() {
        return lastName.getValue();
    }
 
    public String getTitleFieldValue() {
        return title.getValue();
    }
 
    public String getEmailAddressFieldValue() {
        return emailAddress.getValue();
    }
 
    public String getCityFieldValue() {
        return city.getValue();
    }
 
    public String getZipCodeFieldValue() {
        return zipCode.getValue();
    }
 
    public String getFacebookAccountFieldValue() {
        return facebookAccountUrl.getValue();
    }
 
    public String getTwitterAccountFieldValue() {
        return twitterAccountUrl.getValue();
    }
 
    public CustomTargetsPage clickAddCustomTargetButton() {
        addCustomTargetButton.click();
        return this;
    }
 
    public CustomTargetsPage selectCategory(String value) {
        categoryList.selectByLabelJS(value);
        CommonUtils.setProperty("customTargetCategory", value);
        return this;
    }
 
    public CustomTargetsPage showAllCustomTargets() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        showItemsPerPageList.selectByLabel("250 items per page");
        return this;
    }
 
    public CustomTargetsPage specifyFirstName(String value) {
        firstName.type(value);
        CommonUtils.setProperty("customTargetFirstName", value);
        return this;
    }
 
    public CustomTargetsPage specifyLastName(String value) {
        lastName.type(value);
        CommonUtils.setProperty("customTargetLastName", value);
        return this;
    }
 
    public CustomTargetsPage specifyTitle(String value) {
        title.type(value);
        CommonUtils.setProperty("customTargetTitle", value);
        return this;
    }
 
    public CustomTargetsPage specifyEmailAddress() {
        CommonUtils.setProperty("customTargetEmailAddress", (CommonUtils
                .getUnicName() + CommonUtils
                .getProperty(PropertyName.ADMIN_EMAIL)));
        emailAddress.type(CommonUtils.getProperty("customTargetEmailAddress"));
        return this;
    }
 
    public CustomTargetsPage specifyCity(String value) {
        city.type(value);
        CommonUtils.setProperty("customTargetCity", value);
        return this;
    }
 
    public CustomTargetsPage selectState(String value) {
        statesList.selectByLabelJS(value);
        CommonUtils.setProperty("customTargetState", value);
        return this;
    }
 
    public CustomTargetsPage specifyZipCode(String value) {
        zipCode.type(value);
        CommonUtils.setProperty("customTargetZip", value);
        return this;
    }
 
    public CustomTargetsPage specifyFacebookAccount(String value) {
        facebookAccountUrl.type(value);
        CommonUtils.setProperty("customTargetFbAccount", value);
        return this;
    }
 
    public CustomTargetsPage specifyTwitterAccount(String value) {
        twitterAccountUrl.type(value);
        CommonUtils.setProperty("customTargetTwitterAccount", value);
        return this;
    }
 
    public CustomTargetsPage clickOnCreateCustomTargetButton() {
        createButton.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
 
        return new CustomTargetsPage();
    }
 
    public CustomTargetsPage clickOnSaveCustomTargetButton() {
        saveButton.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
 
        return new CustomTargetsPage();
    }
 
    public CustomTargetsPage clickOnDeleteAction() {
        actionsList.selectByLabel("Delete (1)");
        return this;
    }
 
    public CustomTargetsPage selectCustomTargetInTable(
            String customTargetEmailAddress) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        new CheckBoxImpl("//td//span[@ng-if][contains(text(), '"
                + customTargetEmailAddress + "')]/../../../../../td/input",
                "Custom target checkbox").check();
        return this;
    }
 
    public CustomTargetsPage confirmCustomTargetDeletion() {
        confirmCustomTargetDeletion.click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return this;
    }
 
    public int getNumberOfExistingCustomTargets() {
        return tableWithCustomTargets.getRowsCount();
    }
 
    public Table getTableWithCustomTargets() {
        return tableWithCustomTargets;
    }
 
    public CustomTargetsPage clickOnCustomTargetByEmail(
            String customTargetEmailAddress) {
        Button ct = new ButtonImpl("//span[contains(text(), '" + customTargetEmailAddress + "')]/..",
                "Custom Target email address value");
        fluentWaitForElementPresenceIgnoringExceptions(ct.getPath());
        ct.onClick();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return this;
    }
}