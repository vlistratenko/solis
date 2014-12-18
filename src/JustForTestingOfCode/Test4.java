package JustForTestingOfCode;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import org.json.JSONException;

import selenium.CommonUtils;


public class Test4 {

	public static void main(String[] args) throws SQLException, ClassNotFoundException, JSONException, IOException {

		//CommonUtils.addDataToCSV("orgDataForJmetter.csv", "testData", "20141117180427.4441b2bf@mailosaur.in");
System.err.println(new File("apache-jmeter-2.11/bin/orgDataForJmetter.csv").getAbsolutePath());
	}
}
