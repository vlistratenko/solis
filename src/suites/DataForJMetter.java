package suites;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.BeforeGroups;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.HQ.LoginPage;
import selenium.CommonUtils;
import selenium.SeleneseTestCase;
import tests.ActivitiesTests;
import tests.AdminTest;
import tests.SettingsTests;

public class DataForJMetter extends SeleneseTestCase{

	String pathToJMDataFile = "apache-jmeter-2.11/bin/";
	@Test(priority=10, enabled = true, groups = {"createAdmin"}, invocationCount=3)
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
		CommonUtils.saveDataToCSV(new File(pathToJMDataFile + "orgDataForJmetter.csv").getAbsolutePath(),
				CommonUtils.getProperty("Admin.email") + ":" + CommonUtils.getProperty("Admin.Password") + ":" + CommonUtils.getProperty("Admin.orgName"), true);
		new LoginPage(true);
	}
	
	@Test(priority=10, enabled = true, groups = {"createWePay"}, invocationCount=1, dataProvider = "getDataFromCSV", dependsOnMethods={"createNewOrgs"})
	public void createWePayInOrgs(String login, String pass) throws IOException {
		
		CommonUtils.setProperty("Admin.email", login); 
		CommonUtils.setProperty("Admin.Password", pass);
		String wePayName = CommonUtils.getUnicName();
		new SettingsTests().createWePayAcountTest(wePayName, "Description", "nonprofit");
		new LoginPage(true);
		CommonUtils.addDataToCSV(new File(pathToJMDataFile + "orgDataForJmetter.csv").getAbsolutePath(), wePayName, login);
	}
	
	@Test(priority=10, enabled = true, groups = {"createDonationWidgets"}, invocationCount=1, dataProvider = "getDataFromCSV", dependsOnMethods={"createWePayInOrgs"})
	public void createDonationWidgetsInOrgs(String login, String pass) throws IOException {
		
		CommonUtils.setProperty("Admin.email", login); 
		CommonUtils.setProperty("Admin.Password", pass);
		String widgetName = CommonUtils.getUnicName();
		new ActivitiesTests().createDonationWidgetTest(widgetName, "JM descr for widget", false);
		new LoginPage(true);
		CommonUtils.addDataToCSV(new File(pathToJMDataFile + "orgDataForJmetter.csv").getAbsolutePath(), CommonUtils.getProperty("donationWidgetLink").replace("https://", ""), login);
	}
	
	@Test(priority=10, enabled = false, groups = {"createSubscriptionnWidgets"}, invocationCount=1, dataProvider = "getDataFromCSV", dependsOnMethods={"createDonationWidgetsInOrgs"})
	public void createSubscriptionnWidgetsInOrgs(String login, String pass) throws IOException {
		
		CommonUtils.setProperty("Admin.email", login); 
		CommonUtils.setProperty("Admin.Password", pass);
		String widgetName = CommonUtils.getUnicName();
		new ActivitiesTests().createSubscribeWidget(widgetName, "JM descr for widget");
		new LoginPage(true);
		CommonUtils.addDataToCSV(new File(pathToJMDataFile + "orgDataForJmetter.csv").getAbsolutePath(), CommonUtils.getProperty("subscribeWidgetLink").replace("https://", ""), login);
	}
	
	@DataProvider(name = "getDataFromCSV")
    public Iterator<Object []> getDataFromCSV( ) throws InterruptedException, IOException
    {
        List<Object []> testCases = new ArrayList<>();
        //String[] data= null;

        //this loop is pseudo code
        BufferedReader br = new BufferedReader(new FileReader(new File(pathToJMDataFile + "orgDataForJmetter.csv").getAbsolutePath()));
        String line;
        String cvsSplitBy = ",";
        while ((line = br.readLine()) != null) {
            // use comma as separator
        	String[] data = {line.split(cvsSplitBy)[0], line.split(cvsSplitBy)[1]};
            testCases.add(data);
        }
        br.close();
        return testCases.iterator();
    }
    
    @BeforeGroups(groups ={"createAdmin"})
    public void beforeGroup() {
    	//CommonUtils.deleteFile(new File(pathToJMDataFile + "orgDataForJmetter.csv").getAbsolutePath());
	}
	
}
