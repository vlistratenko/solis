package pages.Admin;

import objects.Browser;
import objects.Button;
import objects.MenuBar;
import selenium.SeleneseTestCase;

public class HomePageAdmin extends Browser {
	
	MenuBar organizationsMenu = new MenuBar("//form[@id='mainform']", "Menu bar");
	Button logOut = new Button("//*[.='Logout']/ancestor-or-self::button", "Logout");
	
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
