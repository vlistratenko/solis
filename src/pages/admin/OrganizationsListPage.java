package pages.admin;

import elements.Table;
import elements.impl.TableImpl;

public class OrganizationsListPage extends HomePageAdmin {

	Table organizationTable = new TableImpl("//table[@role='grid']", "Table with organizations");

	public OrganizationsListPage checkOrganizationExists(String orgName) {
		verifier.verifyTrue(organizationTable.getRowsNumberByValue(orgName) >= 0, "Organization " + orgName + " was not found.");
		return this;
	}
}
