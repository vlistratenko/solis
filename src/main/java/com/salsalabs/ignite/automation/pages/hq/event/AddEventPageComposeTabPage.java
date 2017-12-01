package com.salsalabs.ignite.automation.pages.hq.event;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.DropDown;
import com.salsalabs.ignite.automation.elements.VE2Elements.SignupFormElements;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.DropDownImpl;

public interface AddEventPageComposeTabPage {

    Button nextToAutorespondersButton = new ButtonImpl("//*[@id='btnGo-compose-autoresponders']","Next:Autoresponders button");
    Button eventPageWizardStep = new ButtonImpl("//*[@id='activityForm']//descendant::*[@class='wizard-steps']//a[.='Event Page']","Event Page wizard step");
    Button registrationWizardStep = new ButtonImpl("//*[@id='activityForm']//descendant::*[@class='wizard-steps']//a[.='Registration']","Registration wizard step");
    Button checkoutWizardStep = new ButtonImpl("//*[@id='activityForm']//descendant::*[@class='wizard-steps']//a[.='Checkout']","Checkout wizard step");
    Button confirmationViewWizardStep = new ButtonImpl("//*[@id='activityForm']//descendant::*[@class='wizard-steps']//a[.='Confirmation View']","Confirmation View wizard step");
    DropDown gatewaysList = new DropDownImpl(
            "//gateways-and-queues//*[@class='custom dropdown']",
            "//gateways-and-queues//*[@class='custom dropdown']/a",
            "Gateways dropdown");

    default AddEventPageAutorespondersTab clickNextButtonInComposeTab(){
        nextToAutorespondersButton.click();
        return new AddEventPageAutorespondersTab();
    }

    default <T extends AddEventPageComposeTabPage> T goToEventPageWizardStep(){
         eventPageWizardStep.click();
        return (T) new AddEventPageComposeTabEventPage();
     }

    default <T extends AddEventPageComposeTabPage> T goToRegistrationWizardStep(){
        registrationWizardStep.click();
        return (T) new AddEventPageComposeTabRegistration();
    }

    default <T extends AddEventPageComposeTabPage> T goToCheckoutWizardStep(){
        checkoutWizardStep.click();
        return (T) new AddEventPageComposeTabCheckout();
    }

    default <T extends AddEventPageComposeTabPage> T goToConfirmationViewWizardStep(){
        confirmationViewWizardStep.click();
        return (T) new AddEventPageComposeTabConfirmationView();
    }

    default <T extends AddEventPageComposeTabPage> T selectGatewayByName(String gatewayName, Class<T> clazz) {
        gatewaysList.selectByLabelJS(gatewayName);
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        } return null;
    }

    default <T extends AddEventPageComposeTabPage> T dropOneColumnRow(Class<T> clazz){
        new SignupFormElements().performDrop(SignupFormElements.VE.ONECOLUMN);
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        } return null;
    }

}
