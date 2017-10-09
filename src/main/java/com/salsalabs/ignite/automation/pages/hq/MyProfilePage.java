package com.salsalabs.ignite.automation.pages.hq;

import com.salsalabs.ignite.automation.elements.Element;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.GeneralWebElement;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;

public class MyProfilePage extends HomePage {
    TextBox firstName = new TextBoxImpl(".//*[@id='firstName']", "First Name");
    TextBox lastName = new TextBoxImpl(".//*[@id='lastName']", "Last Name");
    TextBox emailAddress = new TextBoxImpl(".//*[@id='emailAddress']", "Email Address");
    TextBox phone = new TextBoxImpl(".//*[@id='phone']", "Phone");
    Element permissionGrid = new GeneralWebElement(".//form[@name='profileForm']/div[5]", "Permission grid");

    public boolean checkIfElementsExistOnPage() {
        firstName.scrollIntoView();
        boolean a = firstName.isDisplayed();
        lastName.scrollIntoView();
        boolean b = lastName.isDisplayed();
        emailAddress.scrollIntoView();
        boolean c = emailAddress.isDisplayed();
        phone.scrollIntoView();
        boolean d = phone.isDisplayed();
        permissionGrid.scrollIntoView();
        boolean e = permissionGrid.isDisplayed();

        return a & b & c & d & e;
    }

}
