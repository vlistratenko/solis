package com.salsalabs.ignite.automation.suites;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;
import com.salsalabs.ignite.automation.tests.AdminTest;

public class CreateOrgTest extends SeleneseTestCase {
	@Test(priority=10, enabled = true, groups = {"createAdmin"}, invocationCount=1)
	@Parameters({"createOrg.domainType",
		"createOrg.orgName",
		"createOrg.orgDescrption",
		"createOrg.firstName",
		"createOrg.lastName",		
		"createOrg.status",
		"createOrg.featureList",
		"newuser.password",
		"createOrg.amountOfOrgs"})
	public void createNewOrgs(String domainType,
			String orgName,
			String orgDescrption,
			String firstName,
			String lastName,		
			String status,
			String featureList,
			String userPassword,
			Integer amountOfOrgs) {

		new AdminTest().createOrgTest(domainType, orgName, orgDescrption, firstName, lastName, status, featureList).
		confirmAdminAccountTest(userPassword);
		new LoginPage(true);
	}
}
