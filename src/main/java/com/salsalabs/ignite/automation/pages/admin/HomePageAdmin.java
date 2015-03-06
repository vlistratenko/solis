package com.salsalabs.ignite.automation.pages.admin;

import com.salsalabs.ignite.automation.common.Browser;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.List;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.MenuBarImpl;

public class HomePageAdmin extends Browser {
	
	List organizationsMenu = new MenuBarImpl("//form[@id='mainform']", "Menu bar");
	Button logOut = new ButtonImpl("//*[.='Logout']/ancestor-or-self::button", "Logout");
	
	public AddNewOrgPage openAddNewOrganizationPage() {
		try{
			organizationsMenu.selectByLabel("Organizations");
			organizationsMenu.selectByLabel("New");
		}catch  (Exception e) {
			open(SeleneseTestCase.USED_ENVIRONMENT.getBaseAdminUrl() + "/protected/organization_new.xhtml");
		}
		return new AddNewOrgPage();
	}
	
	public OrganizationsListPage openOrganizationsList() {
		SeleneseTestCase.bug.add("Open list with organizations");
		organizationsMenu.selectByLabel("List");
		return new OrganizationsListPage();
	}
	
	public OrganizationsMessagesPage openOrganizationsMessages() {
		
		organizationsMenu.selectByLabel("List");
		return new OrganizationsMessagesPage();
	}
	
	public LoginPageAdmin clickLogOut() {
		
		logOut.click();
		return new LoginPageAdmin();
	}
}
