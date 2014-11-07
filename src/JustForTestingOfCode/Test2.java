package JustForTestingOfCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.DataSetException;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

import pages.HQ.LoginPage;
import pages.HQ.Manage.ImportAddPage;
import selenium.CommonUtils;
import selenium.ConnectDatabase;
import selenium.HttpClient;
import selenium.SeleneseTestCase;

public class Test2 extends SeleneseTestCase{

	@Test		
	public void loginTest() {
		try {
			System.err.println(new HttpClient().login("20141106123247.4441b2bf@mailosaur.in", "!QAZ2wsx"));
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
