package JustForTestingOfCode;

import java.io.File;
import java.sql.SQLException;
import java.util.Map;

import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.DataSetException;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

import pages.HQ.LoginPage;
import selenium.CommonUtils;
import selenium.ConnectDatabase;
import selenium.SeleneseTestCase;

public class Test2 extends SeleneseTestCase{

	//@Test
	@Parameters({ "login", "password", "createOrg.featureList" })
	public void loginTest(String userName, String password, String s) throws DataSetException, ClassNotFoundException, SQLException, DatabaseUnitException{
		new ConnectDatabase().
		getConnection().
		createQueryTable("select  * from person_census where hex(person_census.organization_uuid) =  REPLACE('8c  bc  26  a7  c0  9a  4d  5b  b2  30  b0  2e  2e  d3  91  0d  ', ' ', '')", "Test");
	}
	
	//@Parameters({"login"})
	@Test(skipFailedInvocations=false, dataProvider = "donationData")
	public void login(String l, String l2) {
		System.err.println(l);
		System.err.println(l2);
	}
	
	//@Parameters({"login"})
	@DataProvider(name = "donationData")
	public Object[][] donationData(ITestContext context) 
	{
		 Map<String, String> s = context.getCurrentXmlTest().getAllParameters();
		 XmlTest xTest = context.getCurrentXmlTest();
		 
		 return new Object[][] {
		   {xTest.getParameter("login"), xTest.getParameter("password")},
		   { "widgetName", "widgetName"},
		 };
	}
}
