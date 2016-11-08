package com.salsalabs.ignite.automation.suites;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.salsalabs.ignite.automation.common.Supporter;

public class Test {

	/**
	 * @param args
	 * @throws NamingException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws NamingException, FileNotFoundException {
		System.err.println(System.getProperty("user.dir"));
		/*for (int j = 1; j <= 1; j++) {
		//System.out.println("Cell Phone,City,Email,Facebook,First Name,Home Phone,ID,Last Name,Preferred Language,State,Time Zone,Twitter,Username,Zip Code");
		PrintWriter out = new PrintWriter(j+"_supportersWith2k.csv");
		out.println("Cell Phone,"
				+ "City,"
				+ "Email,"
				+ "Facebook,"
				+ "First Name,"
				+ "Home Phone,"
				//+ "ID,"
				+ "Last Name,"
				+ "Preferred Language,"
				+ "State,"
				//+ "Time Zone,"
				+ "Twitter,"
				//+ "Username,"
				+ "Zip Code," 
				+ "Address line 1,"
				+ "Address line 2,"
				+ "Middle name"

				);
		for (int i = 0; i <= 20000; i++) {
			Supporter sup = Supporter.getSupporterWithRandomDataFromFile();
			String cPhone = sup.getcPhone();//"32165498765";
			String City = sup.getCity();// "CityT";
			//String Email = j+"_test" + i + ".22c5a196@mailosaur.in";//".3e41c646@mailosaur.in";//"@salsalabs.com";//"@devnull.test.ignite.net";
			String Facebook = "FBV";
			String First_Name = sup.getFirstName();
			String Home_Phone = "98765432112";
			//String ID = Integer.toString(i*1321212);
			String Last_Name = sup.getLastName();//"Testerov" + i;
			String PreferredLanguage = "English (United States)";
			String State = sup.getState();//"NY";
			//String Time_Zone = "(GMT-05:00) Eastern Time";
			String Twitter = "TWV";
			//String Username = "Vitaliy" + i;
			String Zip_Code = sup.getPostalCode();//"65498";
			String AddressLine1 = sup.getAddressLine1();//"Street " + i;
			String AddressLine2 = sup.getAddressLine2();
			String MiddleName = "MName" + i;
			String Email = First_Name + "_" + Last_Name + ".22c5a196@mailosaur.in";//".3e41c646@mailosaur.in";//"@salsalabs.com";//"@devnull.test.ignite.net";
			
			out.println(cPhone + "," +
					City + "," +
					Email + "," +
					Facebook + "," +
					First_Name + "," +
					Home_Phone + "," +
					//ID + "," +
					Last_Name + "," +
					PreferredLanguage + "," +
					State + "," +
					//Time_Zone + "," +
					Twitter + "," +
					//Username + "," +
					Zip_Code + "," +
					AddressLine1 + "," +
					AddressLine2 + "," +
					MiddleName
					);
		}
		out.close();
		System.out.println("Done");

	}*/
	}
}
