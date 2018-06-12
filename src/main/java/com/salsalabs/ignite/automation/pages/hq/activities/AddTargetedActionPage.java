package com.salsalabs.ignite.automation.pages.hq.activities;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.SelectBox;
import com.salsalabs.ignite.automation.elements.Table;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.SelectBoxImpl;
import com.salsalabs.ignite.automation.elements.impl.TableImpl;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

public class AddTargetedActionPage extends AddSubscribeWidgetPage {

    public enum Channel {
        EMAIL,
        FACEBOOK,
        TWITTER,
        C2C
    }

    protected TextBox targetSetName = new TextBoxImpl(".//*[starts-with(@id, 'targetsetname-0')]", "Target set name");
    protected TextBox targetSetDescription = new TextBoxImpl(".//*[starts-with(@id, 'targetsetdesc')]", "Target set description");
    protected Button addTargetButton = new ButtonImpl(".//button[contains(text(), 'Targets')]", "Add target button");
    protected TextBox targetSearchField = new TextBoxImpl(".//*[contains(@placeholder, 'keyword')]", "Search target by keyword");
    protected Button addSingleTarget = new ButtonImpl(".//*[@title='Select this target']", "Add found target");
    protected Button saveButtonTargetSelectionModal = new ButtonImpl(".//button[contains(text(), 'Save')]", "Save targets");
    protected Button targetsTabs = new ButtonImpl(".//a[contains(text(), 'Targets')]", "Targets tab");
    protected Button messagesTabs = new ButtonImpl(".//a[contains(text(), 'Messages')]", "Messages tab");
    protected Table channelsTable = new TableImpl(".//table", "Channels table");
    protected SelectBox messageTopics = new SelectBoxImpl(".//custom-select2/descendant::a", "Message topics");
    protected SelectBox firstTopic = new SelectBoxImpl(".//li[contains(text(), 'Agriculture')]", "Agriculture topic");
    protected TextBox messageSubject = new TextBoxImpl(".//*[contains(@name, 'subject-')]", "Message subject");
    protected TextBox messageBody = new TextBoxImpl(".//*[contains(@name, 'bodyMessage-')]", "Message body");
    protected Button saveButtonMessageModal = new ButtonImpl(".//*[contains(text(), 'Save Content')]", "Save message");
    protected Button openSelectTargetStepButton = new ButtonImpl("//button[@id='btnGo-setup-targets']", "Select targets");
    protected Button openSocialPromotionStepButton = new ButtonImpl("//button[@id='btnGo-targets-compose']", "Social promotion");
    protected Button openComposeStepButton = new ButtonImpl("//button[@id='btnCompose']", "Compose step");

    @Override
    public AddTargetedActionPage fillFieldsWidgetStepOne(String widgetName, String widgetDescription) {
        this.widgetName = widgetName;
        widgetNameField.type(widgetName);
        widgetDescriptionField.type(widgetDescription);
        openSelectTargetStepButton.click();
        sleep(5);
        return this;
    }

    @Override
    public AddTargetedActionPage checkIdLikeToReceiveUpdatesCheckBoxProperties(String fieldLabel, String defaultValue) {
        idLikeToReceiveUpdatesElement.scrollIntoView();
        idLikeToReceiveUpdatesElement.doubleClick();
        FormFieldConfigurationModalWindow modal = new FormFieldConfigurationModalWindow();
        verifier.verifyEquals(
                (String) ((JavascriptExecutor) driver).executeScript("return document.querySelector('#FieldEditModal-form\\\\3a targetedLetter > div.appModalContent > div > div > div.element-config-container.vertical-scroll > div > div > div > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div > div > div > div > input').value"),
                fieldLabel);
        verifier.verifyEquals(((SelectBoxImpl) modal.checkBoxDefaultValue).getSelectedLabel(modal.checkBoxDefaultValue.getPath()), defaultValue, "Check default value", true);
        modal.saveButton.click();
        return this;
    }

    public AddTargetedActionPage pickTargetAndEnableChannel(String targetName, Channel... channel) {
        targetSetName.type("set");
        targetSetDescription.type("desc");
        addTargetButton.click();
        targetSearchField.type(targetName);
        sleep(3);
        addSingleTarget.click();
        saveButtonTargetSelectionModal.click();
        messagesTabs.click();
        selectChannel(channel);
        return this;
    }

    public AddTargetedActionPage goToComposeFormTargetsPage() {
        openSocialPromotionStepButton.click();
        sleep(3);
        //openComposeStepButton.click();
        return this;
    }

    private void selectChannel(Channel... channel) {
        int rows = channelsTable.getRowsCount();
        for (Channel ch : channel) {
            for (int i = 1; i <= rows; i++) {
                    switch (ch) {
                        case EMAIL:
                            if (channelsTable.getCellValue(i, 2).contains("Email/Webform") & channelsTable.getCellValue(i, 1).contains("OFF")) {
                                clickToggleSwitch(i);
                                editMessage(i);
                            }
                            break;
                        case FACEBOOK:
                            if (channelsTable.getCellValue(i, 2).contains("Facebook") & channelsTable.getCellValue(i, 1).contains("OFF")) channelsTable.clickInCell(i, 1);
                            break;
                        case TWITTER:
                            if (channelsTable.getCellValue(i, 2).contains("Twitter") & channelsTable.getCellValue(i, 1).contains("OFF")) channelsTable.clickInCell(i, 1);
                            break;
                        case C2C:
                            if (channelsTable.getCellValue(i, 2).contains("Click To Call") & channelsTable.getCellValue(i, 1).contains("OFF")) channelsTable.clickInCell(i, 1);
                            break;
                    }
                }
            }
    }

    private void editMessage(int row) {
        driver.findElement(By.xpath(channelsTable.getPathToChildElement(row, 5, "span"))).click();
        messageTopics.click();
        firstTopic.click();
        messageSubject.type("subject");
        messageBody.type("body text");
        saveButtonMessageModal.click();
    }

    private void clickToggleSwitch(int row) {
        driver.findElement(By.xpath(channelsTable.getPathToChildElement(row, 1, "span"))).click();
    }
 }

