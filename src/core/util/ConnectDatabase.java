package core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.DatabaseSequenceFilter;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.FilteredDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.filter.ITableFilter;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.testng.annotations.BeforeSuite;


public class ConnectDatabase extends SeleneseTestCase{

	private String driver = "com.mysql.jdbc.Driver";//"oracle.jdbc.driver.OracleDriver";//"org.gjt.mm.mysql.Driver";//"com.mysql.jdbc.Driver";
	
	private String url = "jdbc:mysql://ignite-big5.ignite.net:3306/igniteoltp";
	
	String sid = "xe";
	
	private String user = "app_hq_rw";
	
	private String pssword = "app_hq_rw";
	
	private String BASE_PATH = "base";
	
	private String BASE_FILE = "Table_new.xml";
	
	
	public String saveFullDataSet() throws Exception {		 
		logger.info("Process save database is start");
		if (!new File(BASE_PATH).exists()) {
            new File(BASE_PATH).mkdirs();
		}
		IDatabaseConnection conn = this.getConnection();
		
		if (new File(BASE_PATH + File.separator + BASE_FILE).exists()){
			IDataSet data = this.getDataSet(new File(BASE_PATH + File.separator + BASE_FILE).getAbsolutePath());
			
			String baseVersion = conn.createDataSet().getTable("DBVersion").getValue(0, "buildDate").toString();
			String actualBaseVersion = data.getTable("DBVersion").getValue(0, "buildDate").toString();
			
			if (!actualBaseVersion.equals(baseVersion)){	
				ITableFilter filter = new DatabaseSequenceFilter(conn);
				IDataSet datanew =  new FilteredDataSet(filter, conn.createDataSet());
				FlatXmlDataSet.write(datanew, new FileOutputStream(new File(BASE_PATH + File.separator + BASE_FILE).getAbsolutePath()));
				return "Version data base in the XML file not actual. New base saved successfully in file " + BASE_PATH + File.separator + BASE_FILE;
			}
			
		}else {
			ITableFilter filter = new DatabaseSequenceFilter(conn);
			IDataSet datanew =  new FilteredDataSet(filter, conn.createDataSet());
			FlatXmlDataSet.write(datanew, new FileOutputStream(new File(BASE_PATH + File.separator + BASE_FILE).getAbsolutePath()));
			return "Data base copy not exists. New base saved successfully in file " + BASE_PATH + File.separator + BASE_FILE;
		}
		return "Error in the ConnectDatabese.saveFullDataSet";
	 }
	
	@BeforeSuite
	public void seedDatabase() throws Exception, IOException {

		final IDatabaseConnection conn = this.getConnection();
			
		IDataSet data = this.getDataSet(new File(BASE_PATH + File.separator + BASE_FILE).getAbsolutePath());		

	  try {
		  
		  Thread thread = new Thread(new Runnable(){
			  public void run() {
				  try{
				  IDataSet data = new ConnectDatabase().getDataSet(new File(BASE_PATH + File.separator + BASE_FILE).getAbsolutePath());
				  IDatabaseConnection connect = new ConnectDatabase().getConnection();
				  IDataSet base = connect.createDataSet();
				  
				  String[] TableNames = data.getTableNames();
				  boolean tablesClean;
				  boolean tablesRefreshed;
				  int block = 0;
				  logger.info("The refresh database proccess is started!");
				  	for (int i = TableNames.length-1; i >= 0; i--) {		
				  		tablesClean = false;
				  		do {
				  			if (base.getTable(TableNames[i]).getRowCount() < 1){
				  				logger.info("Table " + TableNames[i] + " cleaned. Table " + new Integer(TableNames.length - i).toString() + " of " + new Integer(TableNames.length).toString());
					  			tablesClean = true;
							}else{
								Thread.sleep(1000);
								if (block == base.getTable(TableNames[i]).getRowCount()){
									System.err.println("Table " + TableNames[i] + " blocked!" +
											" Rowcount in the base = " + new Integer(data.getTable(TableNames[i]).getRowCount()).toString() + ". Table "
											+ new Integer(TableNames.length - i).toString() + " of " + new Integer(TableNames.length).toString());
									tablesClean = true;
								}
								block = base.getTable(TableNames[i]).getRowCount();
							}
						} while (tablesClean != true);
				  	}
				  	
				  	block = 0;		  	
				  	for (int i = 0; i < TableNames.length; i++){
				  		tablesRefreshed = false;				  		
				  		do {
							if (base.getTable(TableNames[i]).getRowCount() >= data.getTable(TableNames[i]).getRowCount()){
								logger.info("Table " + TableNames[i] + " refreshed. Table " + new Integer(i).toString() + " of " + new Integer(TableNames.length).toString());
								  tablesRefreshed = true;    
							}else{
								  Thread.sleep(4000);
								  if (block == base.getTable(TableNames[i]).getRowCount()){
										System.err.println("Table " + TableNames[i] + " blocked! " +
															" Rowcount in the base = " + new Integer(data.getTable(TableNames[i]).getRowCount()).toString() + ". Table "
															+ new Integer(i+1).toString() + " of " + new Integer(TableNames.length).toString());
										tablesRefreshed = true;
									}
									block = base.getTable(TableNames[i]).getRowCount();
							}
							
						} while (tablesRefreshed != true);						
					}
				  	logger.info("The refresh database proccess is finished!");
				  }
				  catch (Exception e) {
					e.printStackTrace();
				}
			}
		  });
		  
		  thread.start();		  
		  DatabaseOperation.CLEAN_INSERT.execute(conn, data);
	  }finally {
		  conn.close();
		  
	  }
	 }


	 private IDataSet getDataSet(String path) throws IOException, DataSetException {
		 return new FlatXmlDataSet(new FileInputStream(path));
	 }
	 
	 public IDatabaseConnection getConnection() 
	 	throws ClassNotFoundException, SQLException, DatabaseUnitException 
	 	 {		 
		 	logger.info("Try connect to DB on the - " 
		 			+ url.substring(url.indexOf('/')+2));
		 	Class.forName(driver);
		  	Connection jdbcConnection = DriverManager.getConnection(url, user, pssword);
		  	IDatabaseConnection i = new DatabaseConnection(jdbcConnection);
		  	logger.info("Connection is established ");
		  	return i;
	 }
	 
}
