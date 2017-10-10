package com.salsalabs.ignite.automation.common;

public class Environment {
	public enum NameOfEnvironment {
		DEV, TEST, /* PROD, */UAT
	}

	public enum LocationOfServer {
		LOCAL, REMOTE
	}

	private NameOfEnvironment environment;
	private LocationOfServer server;
	private String TESTURL;
	private String TESTURLADMIN;

	public Environment(String envName, String sarverLocation) {
		CommonUtils.setProperty(PropertyName.ENVIRONMENT, envName);
		environment = NameOfEnvironment.valueOf(envName);
		server = LocationOfServer.valueOf(sarverLocation);
	}
	
	public EmailClient<?> getMailosourEmailClient() {
		switch (environment) {
		case DEV:
			return new MailosourEmailClient("6fe4a402");
		case TEST:
			return new MailosourEmailClient("612fb360");
		case UAT:
			return new MailosourEmailClient("d05ab35d");
		}
		return null;
	}
	
	public EmailClient<?> getSquirrelEmailClient() {
		switch (environment) {
		case DEV:
			return new SquirrelEmailClient("devauto");
		case TEST:
			return new SquirrelEmailClient("testauto");
		case UAT:
			return new SquirrelEmailClient("uatauto");
		}
		return null;
	}

	public String getBaseAdminUrl() {
		switch (environment) {
		case DEV: {
			switch (server) {
			case LOCAL:
				TESTURLADMIN = "https://admin.dev.ignite.net";
				break;
			case REMOTE:
				TESTURLADMIN = "https://10.16.0.57";
				break;
			}
		}
			break;
		case TEST: {
			switch (server) {
			case LOCAL:
				TESTURLADMIN = "https://admin.test.ignite.net";
				break;
			case REMOTE:
				TESTURLADMIN = "https://10.16.0.73";
				break;
			}
		}
			break;
		/*
		 * case PROD: { switch (server){ case LOCAL: TESTURLADMIN =
		 * "https://admin.prod.salsalabs.org";break; case REMOTE: TESTURLADMIN =
		 * "https://admin.prod.salsalabs.org";break; } } break;
		 */
		case UAT: {
			switch (server) {
			case LOCAL:
				TESTURLADMIN = "https://admin.uat.ignite.net";
				break;
			case REMOTE:
				TESTURLADMIN = "https://admin.uat.ignite.net";
				break;
			}
		}
			break;
		}
		return TESTURLADMIN;
	}

	public String getBaseTestUrl() {
		switch (environment) {
		case DEV: {
			switch (server) {
			case LOCAL:
				TESTURL = "https://hq.dev.ignite.net";
				break;
			case REMOTE:
				TESTURL = "https://10.16.0.53";
				break;
			}
		}
			break;
		case TEST: {
			switch (server) {
			case LOCAL:
				TESTURL = "https://hq.test.ignite.net";
				break;
			case REMOTE:
				TESTURL = "https://10.16.0.69";
				break;
			}
		}
			break;
		/*
		 * case PROD: { switch (server){ case LOCAL: TESTURL =
		 * "https://HQ.salsalabs.org";break; case REMOTE: TESTURL =
		 * "https://HQ.salsalabs.org";break; } } break;
		 */
		case UAT: {
			switch (server) {
			case LOCAL:
				TESTURL = "https://HQ.uat.ignite.net";
				break;
			case REMOTE:
				TESTURL = "https://HQ.uat.ignite.net";
				break;
			}
		}
			break;
		}
		return TESTURL;
	}

	public NameOfEnvironment getEnvironment() {
		return environment;
	}

	public LocationOfServer getServer() {
		return server;
	}
}