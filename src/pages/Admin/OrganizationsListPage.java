package pages.Admin;

import objects.Table;

public class OrganizationsListPage extends HomePageAdmin{
	
	Table organizationTable = new Table("//table[@role='grid']", "Table with organizations");
	
	public OrganizationsListPage checkOrganizationExists(String orgName) {
		verify(organizationTable.getRowsNumberByValue(orgName)>=0, true, "Organization " + orgName + " was not found.", false); 
		return this;
	}
}
