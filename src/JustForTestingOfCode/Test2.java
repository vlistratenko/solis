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
import pages.Other.Dispatcher;
import selenium.CommonUtils;
import selenium.ConnectDatabase;
import selenium.HttpClient;
import selenium.SeleneseTestCase;

public class Test2 extends SeleneseTestCase{

	@Test		
	public void loginTest() {

	}
}
