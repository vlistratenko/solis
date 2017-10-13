package com.salsalabs.ignite.automation.pages.hq;

import com.salsalabs.ignite.automation.common.EmailClient;
import com.salsalabs.ignite.automation.common.Verifier;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;

public class MyAccountPage extends HomePage {

    private TextBox password = new TextBoxImpl(".//*[@id='password']", "Password");
    private Button continueToAccountInfo = new ButtonImpl(".//*[contains(text(), 'Continue to Account Info')]", "Continue to Account Info button");
    private TextBox firstName = new TextBoxImpl(".//*[@id='accountFirstName']", "First Name");
    private TextBox lastName = new TextBoxImpl(".//*[@id='accountLastName']", "Last Name");
    private TextBox email = new TextBoxImpl(".//*[@id='email']", "Email Address");
    private Button saveInfo = new ButtonImpl(".//form[@name='accountForm']//*[contains(text(), 'Save')]", "Save your info");
    private Button savePassword = new ButtonImpl(".//form[@name='passwordForm']//*[contains(text(), 'Save')]", "Save password");
    private TextBox firstSecurityQuestionAnswer = new TextBoxImpl(".//*[@id='firstAnswer']", "Email Address");
    private TextBox newPassword = new TextBoxImpl(".//*[@id='newPassword']", "New password");
    private TextBox confirmPassword = new TextBoxImpl(".//*[@id='confirmPassword']", "Confirm password");
    private TextBox secondSecurityQuestionAnswer = new TextBoxImpl(".//*[@id='secondAnswer']", "Email Address");
    private Button saveSecurityAnswers = new ButtonImpl(".//form[@name='securityForm']//*[contains(text(), 'Save')]", "Save security questions");
    private Button discardSaveInfo = new ButtonImpl(".//form[@name='accountForm']//*[contains(text(), 'Discard')]", "Discard changes your info");
    private Button discardSavePassword = new ButtonImpl(".//form[@name='passwordForm']//*[contains(text(), 'Discard')]", "Discard changes password");
    private Button discardSecurityAnswers = new ButtonImpl(".//form[@name='securityForm']//*[contains(text(), 'Discard')]", "Discard changes security questions");

    private Verifier verifier = new Verifier();

    public MyAccountPage enterSecureArea(String password) {
        this.password.type(password);
        continueToAccountInfo.click();
        return this;
    }

    public MyAccountPage updatePersonalInfo(String firstName, String lastName, String email) {
        sleep(3);
        this.firstName.type(firstName);
        this.lastName.type(lastName);
        this.email.type(email);
        saveInfo.click();
        return this;
    }

    public MyAccountPage updatePassword(String newPassword, String confirmPassword) {
        this.newPassword.type(newPassword);
        this.confirmPassword.type(confirmPassword);
        savePassword.click();
        return this;
    }

    public MyAccountPage updateSecurityAnswers(String firstSecurityQuestionAnswer, String secondSecurityQuestionAnswer) {
        this.firstSecurityQuestionAnswer.type(firstSecurityQuestionAnswer);
        this.secondSecurityQuestionAnswer.type(secondSecurityQuestionAnswer);
        saveSecurityAnswers.click();
        return this;
    }

    public MyAccountPage checkIfDiscardButtonsAppear() {
        verifier.verifyElementIsDisplayed(true, discardSaveInfo);
        verifier.verifyElementIsDisplayed(true, discardSavePassword);
        verifier.verifyElementIsDisplayed(true, discardSecurityAnswers);
        return this;
    }

    public MyAccountPage verifyAccountUpdatedEmail(EmailClient<?> emailClient) {
        Integer amounOfEmails = 0;
        sleep(5);
        amounOfEmails = emailClient.waitForEmails("Your account has been modified.", 1, 10).getEmailsBySubject("Your account has been modified.").size();
        verifier.verifyEquals(amounOfEmails, 3, "Wrong amount of emails", true);
        return this;
    }

}
