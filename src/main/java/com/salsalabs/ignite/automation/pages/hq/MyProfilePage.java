package com.salsalabs.ignite.automation.pages.hq;

import com.salsalabs.ignite.automation.common.Verifier;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.Element;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.GeneralWebElement;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;

public class MyProfilePage extends HomePage {
    private TextBox firstName = new TextBoxImpl(".//*[@id='firstName']", "First Name");
    private TextBox lastName = new TextBoxImpl(".//*[@id='lastName']", "Last Name");
    private TextBox email = new TextBoxImpl(".//*[@id='emailAddress']", "Email Address");
    private TextBox phone = new TextBoxImpl(".//*[@id='phone']", "Phone");
    private TextBox title = new TextBoxImpl(".//*[@id='occupationTitle']", "Title");
    private Element permissionGrid = new GeneralWebElement(".//form[@name='profileForm']/div[5]", "Permission grid");
    private Button save = new ButtonImpl(".//span[contains(text(), 'Save')]", "Save button");
    private Button cancel = new ButtonImpl(".//button[contains(text(), 'Cancel')]", "Cancel button");

    private Verifier verifier = new Verifier();

    public boolean checkIfElementsExistOnPage() {
        firstName.scrollIntoView();
        boolean a = firstName.isDisplayed();
        lastName.scrollIntoView();
        boolean b = lastName.isDisplayed();
        email.scrollIntoView();
        boolean c = email.isDisplayed();
        phone.scrollIntoView();
        boolean d = phone.isDisplayed();
        permissionGrid.scrollIntoView();
        boolean e = permissionGrid.isDisplayed();

        return a & b & c & d & e;
    }

    public MyProfilePage updatePersonalData(String firstName, String lastName, String title, String email, String phone) {
        this.firstName.type(firstName);
        this.lastName.type(lastName);
        this.title.type(title);
        this.email.type(email);
        this.phone.type(phone);
        return this;
    }

    public MyProfilePage saveChanges() {
        save.click();
        return this;
    }

    public HomePage cancelChanges() {
        cancel.click();
        return new HomePage();
    }

    public HomePage checkUpdatedData(String firstName, String lastName, String title, String email, String phone) {
        verifier.verifyEquals(firstName, this.firstName);
        verifier.verifyEquals(lastName, this.lastName);
        verifier.verifyEquals(title, this.title);
        verifier.verifyEquals(email, this.email);
        verifier.verifyEquals(phone, this.phone);
        return this;
    }

}
