package com.salsalabs.ignite.automation.pages.p2p;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.pages.hq.HomePage;



public class p2psPage extends HomePage{

	Button createp2pEventButton = new ButtonImpl("//button[@autotest-id='btn_create_p2p_event_dashboard']", "+ Create a Peer-to-Peer Event button");
	
	public AddP2PPage_SetupTab openCreateNewp2pForm() {
		createp2pEventButton.click();
		return new AddP2PPage_SetupTab();
		
	}
	
	
	

}
