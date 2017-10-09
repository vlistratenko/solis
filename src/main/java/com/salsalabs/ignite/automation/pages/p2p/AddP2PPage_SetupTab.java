package com.salsalabs.ignite.automation.pages.p2p;

import com.salsalabs.ignite.automation.elements.BooleanRadiobutton;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.DropDown;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.BooleanRadiobuttonImpl;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.DropDownImpl;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;

public class AddP2PPage_SetupTab extends AddP2PPage{

	TextBox referenceNameField = new TextBoxImpl("//input[@name='name']", "Reference name "); 
	TextBox publiclyVisiblyNameField = new TextBoxImpl("//input[@name='publicName']", "Publicly visible name for the event field");
	TextBox eventStartDateField = new TextBoxImpl("//input[@id='datepickerStart']", "Event start date ");
	DropDown eventStartTimeField = new DropDownImpl("//div[@class='ui-timepicker-wrapper' and not(contains(@style, 'display: none'))]","//input[@id='timepickerStart']", "Event start time ");
	TextBox eventEndDateField = new TextBoxImpl("//input[@id='datepickerEnd']", "Event end date ");
	DropDown eventEndTimeField = new DropDownImpl("//div[@class='ui-timepicker-wrapper' and not(contains(@style, 'display: none'))]", "//input[@id='timepickerEnd']", "Event end time ");
	DropDown timeZoneDropdown = new DropDownImpl("//custom-select2[@data='setup.timeZones']", "//custom-select2[@data='setup.timeZones']/descendant::a", "Time zone dropdown");
	BooleanRadiobutton isOnlineOnlyRadio = new BooleanRadiobuttonImpl("//label[contains(., 'Yes')]", "//label[contains(., 'No')]", "Is this event online-only radio button");
	Button nextButton = new ButtonImpl("//button[@id='btnGo-setup-registration']", "Next: : Registration Levels button");
	
	public AddP2PPage_SetupTab fillSetupStep(String referenceName,
			String publiclyVisiblyName,
			String eventStartDate,
			String eventStartTime,
			String eventEndDate,
			String eventEndTime,
			String timeZone,
			boolean isOnlineOnly) {
		
		referenceNameField.type(referenceName);
		publiclyVisiblyNameField.type(publiclyVisiblyName);
		eventStartDateField.type(eventStartDate);
		eventStartTimeField.selectByLabel(eventStartTime);
		eventEndDateField.type(eventEndDate);
		eventEndTimeField.selectByLabel(eventEndTime);
		timeZoneDropdown.selectByLabel(timeZone);
		isOnlineOnlyRadio.select(isOnlineOnly);
		return this;
	}
	
	public AddP2PPage_RegistrationsTab clickNextButton() {
		nextButton.click();
		return new AddP2PPage_RegistrationsTab();
	}
	
	public AddP2PPage_RegistrationsTab_RegistrationInfoTab fillSetupStepAndGoNext(String referenceName,
			String publiclyVisiblyName,
			String eventStartDate,
			String eventStartTime,
			String eventEndDate,
			String eventEndTime,
			String timeZone,
			boolean isOnlineOnly) {
		
		fillSetupStep(referenceName, publiclyVisiblyName, eventStartDate, eventStartTime, eventEndDate, eventEndTime, timeZone, isOnlineOnly);
		clickNextButton();
		return new AddP2PPage_RegistrationsTab_RegistrationInfoTab();
	}
}
