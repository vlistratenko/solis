package JustForTestingOfCode;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;






public class Test4 {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		System.err.println("Error");
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://ignite-big5.ignite.net:3306/igniteoltp";		
		String user = "app_hq_rw";		
		String pssword = "app_hq_rw";
		
		Class.forName(driver);
	  	Connection jdbcConnection = DriverManager.getConnection(url, user, pssword);
	  	try {
			IDatabaseConnection i = new DatabaseConnection(jdbcConnection);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

			/*getConnection().
			createQueryTable("select  * from person_census where hex(person_census.organization_uuid) =  REPLACE('8c  bc  26  a7  c0  9a  4d  5b  b2  30  b0  2e  2e  d3  91  0d  ', ' ', '')", "Test");*/
	}	
}
