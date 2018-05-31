package com.salsalabs.ignite.automation.pages.hq;


import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.PanelImpl;

public class FeedBackDialogPopUp extends PanelImpl{
	
	private Button closeFeedbackDialog = new ButtonImpl(super.getPath() + "/ancestor::feedback-dialog/descendant::a[@class='close']", "Close feedback dialog");
	
	public FeedBackDialogPopUp(String elementPath, String elementName) {
		super(elementPath, elementName);
	}

	public FeedBackDialogPopUp waitDialogPopUp(int sec) {
		this.waitElement(sec);
		return this;
	}
	
	public void closePopUp() {
		closeFeedbackDialog.click();
	}
	

}
