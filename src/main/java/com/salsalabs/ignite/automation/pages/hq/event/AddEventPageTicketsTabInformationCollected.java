package com.salsalabs.ignite.automation.pages.hq.event;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.Frame;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.FrameImpl;
import com.salsalabs.ignite.automation.pages.hq.HomePage;

public class AddEventPageTicketsTabInformationCollected extends HomePage {

    private Button askFirstAndLastNameQuestionToggle = new ButtonImpl("//*[@id='question_listing']//*[@for='ask_name']","Ask First and Last name question toggle");
    private Button askEmailAddressQuestionToggle = new ButtonImpl("//*[@id='question_listing']//*[@for='ask_email']","Ask Email Address question toggle");
    private Button askPhoneNumberQuestionToggle = new ButtonImpl("//*[@id='question_listing']//*[@for='ask_phone']","Ask Phone Number question toggle");
    private Button askAddressQuestionToggle = new ButtonImpl("//*[@id='question_listing']//*[@for='ask_address']","Ask Address question toggle");
    private Button continueButton = new ButtonImpl("//*[@class='button expanded interim_button_holder']","Continue button");
    private Frame givezooksFrame = new FrameImpl("//iframe[@id='tickets_iframe']","Givezooks iFrame");

    public AddEventPageTicketsTabInformationCollected clickAskFirstAndLastNameToggle(){
        askFirstAndLastNameQuestionToggle.fluentWaitForElementPresenceIgnoringExceptions();
        askFirstAndLastNameQuestionToggle.click();
        return this;
    }

    public AddEventPageTicketsTabInformationCollected clickAskEmailAddressNameToggle(){
        askEmailAddressQuestionToggle.fluentWaitForElementPresenceIgnoringExceptions();
        askEmailAddressQuestionToggle.click();
        return this;
    }

    public AddEventPageTicketsTabInformationCollected clickAskPhoneNumberToggle(){
        askPhoneNumberQuestionToggle.fluentWaitForElementPresenceIgnoringExceptions();
        askPhoneNumberQuestionToggle.click();
        return this;
    }

    public AddEventPageTicketsTabInformationCollected clickAskAddressToggle(){
        askAddressQuestionToggle.fluentWaitForElementPresenceIgnoringExceptions();
        askAddressQuestionToggle.click();
        return this;
    }

    public AddEventPageTicketsTabManageTickets clickContinueButton(){
        givezooksFrame.swithToFrameWithFluentWait(10);
        continueButton.click();
        switchDefaultContent();
        return new AddEventPageTicketsTabManageTickets();
    }


}
