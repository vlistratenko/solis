package com.salsalabs.ignite.automation.pages.admin;

import com.salsalabs.ignite.automation.elements.SelectBox;
import com.salsalabs.ignite.automation.elements.Table;
import com.salsalabs.ignite.automation.elements.impl.SelectBoxImpl;
import com.salsalabs.ignite.automation.elements.impl.TableImpl;

public class OrganizationsListPage extends HomePageAdmin {

	Table organizationTable = new TableImpl("//table[@role='grid']", "Table with organizations");
	SelectBox orgClassification = new SelectBoxImpl("//th[8]/select", "Classification");

	public OrganizationsListPage checkOrganizationExists(String orgName) {
		orgClassification.selectByLabel("Internal-Test");
		sleep(5);
		verifier.verifyTrue(organizationTable.getRowsNumberByValue(orgName) >= 0, "Organization " + orgName + " was not found.");
		return this;
	}
}
