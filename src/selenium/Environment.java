package selenium;

public class Environment{
	  public enum NameOfEnvironment {DEV, TEST}
	  public enum LocationOfServer {LOCAL, REMOTE}
	  private NameOfEnvironment environment;
	  public LocationOfServer server;
	  private String TESTURL;
	  private String TESTURLADMIN;	

	  public Environment(String envName, String sarverLocation){
	     CommonUtils.setProperty("Environment", envName);
		  environment = NameOfEnvironment.valueOf(envName);
		  server = LocationOfServer.valueOf(sarverLocation);
	  }

	  
	  public String getBaseAdminUrl(){
	      switch (environment){
	         case DEV: {
	        	 switch (server){ 
	        	 	case LOCAL: 	TESTURLADMIN = "https://admin.dev.ignite.net";break;
	        	 	case REMOTE: 	TESTURLADMIN = "https://10.16.0.57";break;
	        	 }
	         }
	         break;
	         case TEST: {
	        	 switch (server){ 
	        	 	case LOCAL: 	TESTURLADMIN = "https://admin.test.ignite.net";break;
	        	 	case REMOTE: 	TESTURLADMIN = "https://10.16.0.73";break;
	        	 }
	         }
	         break;
	      }
	    return TESTURLADMIN;
	  }
	  
	  public String getBaseTestUrl(){
	      switch (environment){
	         case DEV: {
	        	 switch (server){ 
	        	 	case LOCAL: 	TESTURL = "https://hq.dev.ignite.net";break;
	        	 	case REMOTE: 	TESTURL = "https://10.16.0.53";break;
	        	 }
	         }
	         break;
	         case TEST: {
	        	 switch (server){ 
	        	 	case LOCAL: 	TESTURL = "https://hq.test.ignite.net";break;
	        	 	case REMOTE: 	TESTURL = "https://10.16.0.69";break;
	        	 }
	         }
	         break;
	      }
	    return TESTURL;
	  }
	 }