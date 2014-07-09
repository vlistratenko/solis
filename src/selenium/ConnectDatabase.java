package selenium;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.*;
import org.dbunit.dataset.*;
import org.dbunit.dataset.filter.ITableFilter;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.testng.annotations.*;


public class ConnectDatabase extends SeleneseTestCase{

	private String driver = "com.mysql.jdbc.Driver";//"oracle.jdbc.driver.OracleDriver";//"org.gjt.mm.mysql.Driver";//"com.mysql.jdbc.Driver";
	
	private String url = "jdbc:mysql://ignite-big5.ignite.net:3306/igniteoltp";
	
	String sid = "xe";
	
	private String user = "app_hq_rw";
	
	private String pssword = "app_hq_rw";
	
	private String BASE_PATH = "base";
	
	private String BASE_FILE = "Table_new.xml";
	
	//SSH
	/*Session session= null;
	int lport = 5656;
    String rhost = "sole..ru";
    String host = "62.152.58.20";//"sole..ru";
    int rport = 3306;
    String sshUser = "flirteka";
    String sshPassword = "eiGhie9y";
    String url = "jdbc:mysql://sole..ru";*/
	
	public String saveFullDataSet() throws Exception {		 
		System.out.println("Process save database is start");
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
	
/*	@AfterSuite
	public void stopSeleniumServer() throws Exception {
		StartServer.stopSeleniumServer();
	} 	*/
	
	@BeforeSuite//@AfterSuite
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
				  System.out.println("The refresh database proccess is started!");
				  	for (int i = TableNames.length-1; i >= 0; i--) {		
				  		tablesClean = false;
				  		do {
				  			if (base.getTable(TableNames[i]).getRowCount() < 1){
					  			System.out.println("Table " + TableNames[i] + " cleaned. Table " + new Integer(TableNames.length - i).toString() + " of " + new Integer(TableNames.length).toString());
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
								  System.out.println("Table " + TableNames[i] + " refreshed. Table " + new Integer(i).toString() + " of " + new Integer(TableNames.length).toString());
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
				  	System.out.println("The refresh database proccess is finished!");
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
	 
	/* public IDatabaseConnection getConnectionOverSSH() 
	 	throws ClassNotFoundException, SQLException, DatabaseUnitException {
		createSSHSession();
		IDatabaseConnection conn = getConnection();
		return conn;
	 } */
	 
	/* private Session createSSHSession() throws ClassNotFoundException, SQLException, DatabaseUnitException {
		 try {
			//Set StrictHostKeyChecking property to no to avoid UnknownHostKey issue
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			JSch jsch = new JSch();		
			session = jsch.getSession(user, host, 22);			
			session.setPassword(sshPassword);
			session.setConfig(config);
			session.connect();
			
			System.out.println(session.getUserName());

			System.out.println("Connected - " + session.isConnected());
			int assinged_port = session.setPortForwardingL(lport, rhost, rport);
			System.out.println("localhost:" + assinged_port + " -> " + rhost + ":" + rport);
			System.out.println("Port Forwarded");
		} catch (JSchException e) {
			e.printStackTrace();
		}
		return session;
	}*/
	 

}
