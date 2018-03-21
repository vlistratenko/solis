package com.salsalabs.ignite.automation.pages.hq.activities;

import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;

public class TargetActionsPage extends SubscribeWidget {

    Button createActionDropDown = new ButtonImpl("//*[contains(text(), 'Start an Activity')]", "Create Action");
    Button createTargetedActionButton = new ButtonImpl("//*[@ng-click='createTargetedLetter()']", "Create Targeted Action");
    Button skipSendingEmail = new ButtonImpl("//a[contains(text(), 'Skip Sending Email')]", "Create Targeted Action");
    Button skipFacebookPost = new ButtonImpl("//a[contains(text(), 'Skip Facebook')]", "Create Targeted Action");
    Button skipTwitterPost = new ButtonImpl("//a[contains(text(), 'Skip Tweeting')]", "Create Targeted Action");

    public AddTargetedActionPage openAddTargetedActionPage() {
        createActionDropDown.click();
        createTargetedActionButton.click();
        return new AddTargetedActionPage();
    }

    public SubscribeWidget fillAndSubmitWidget(String personEmail,
                                               String personFName,
                                               String personLName,
                                               String personCity,
                                               String personZip,
                                               String state,
                                               String addressLine1) {
        personEmailField.type(personEmail);
        personFNameField.type(personFName);
        personLNameField.type(personLName);
        personCityField.type(personCity);
        personZipField.type(personZip);
        addressLine1Field.type(addressLine1);

        if (state.equals("")) {
            personStatesSelectBox.selectByIndex(Integer.parseInt(CommonUtils.getRandomValueFromTo(1, 50, 0)));
        }else{
            personStatesSelectBox.selectByValue(state);
        }
        sleep(3);
        subscribeButton.click();
        sleep(1);
        skipSendingEmail.click();
        skipFacebookPost.click();
        skipTwitterPost.click();
        return this;

    }
}