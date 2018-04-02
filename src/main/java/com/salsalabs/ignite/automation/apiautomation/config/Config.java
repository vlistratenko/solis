package com.salsalabs.ignite.automation.apiautomation.config;

import org.testng.annotations.DataProvider;

public class Config {

    public static String TEST_DATA_PATH_SUPPORTERS_REQUESTS = System.getProperty("user.dir") + "\\src\\main\\java\\com\\salsalabs\\ignite\\automation\\apiautomation\\testdata\\supporters\\request\\";
    public static String TEST_DATA_PATH_SUPPORTERS_RESPONSES = System.getProperty("user.dir") + "\\src\\main\\java\\com\\salsalabs\\ignite\\automation\\apiautomation\\testdata\\supporters\\response\\";

    public static String TEST_DATA_PATH_SEGMENTS_REQUESTS = System.getProperty("user.dir") + "\\src\\main\\java\\com\\salsalabs\\ignite\\automation\\apiautomation\\testdata\\segments\\request\\";
    public static String TEST_DATA_PATH_SEGMENTS_RESPONSES = System.getProperty("user.dir") + "\\src\\main\\java\\com\\salsalabs\\ignite\\automation\\apiautomation\\testdata\\segments\\response\\";

    public static String TEST_DATA_PATH_ACTIVITIES_REQUESTS = System.getProperty("user.dir") + "\\src\\main\\java\\com\\salsalabs\\ignite\\automation\\apiautomation\\testdata\\activities\\request\\";
    public static String TEST_DATA_PATH_ACTIVITIES_RESPONSES = System.getProperty("user.dir") + "\\src\\main\\java\\com\\salsalabs\\ignite\\automation\\apiautomation\\testdata\\activities\\response\\";

    public static String TEST_DATA_PATH_DONATIONS_REQUESTS = System.getProperty("user.dir") + "\\src\\main\\java\\com\\salsalabs\\ignite\\automation\\apiautomation\\testdata\\donations\\request\\";
    public static String TEST_DATA_PATH_DONATIONS_RESPONSES = System.getProperty("user.dir") + "\\src\\main\\java\\com\\salsalabs\\ignite\\automation\\apiautomation\\testdata\\donations\\response\\";

    // Developer API
    public static String TEST_DATA_PATH_EVENT_FORm_SUMMARY_RESPONSE = System.getProperty("user.dir") + "\\src\\main\\java\\com\\salsalabs\\ignite\\automation\\apiautomation\\testdata\\devApiTestData\\activities\\response\\";
    public static String TEST_DATA_PATH_ATTENDEE_SUMMARY_RESPONSE = System.getProperty("user.dir") + "\\src\\main\\java\\com\\salsalabs\\ignite\\automation\\apiautomation\\testdata\\devApiTestData\\activities\\response\\";
    public static String TEST_DATA_PATH_REGISTRATION_SUMMARY_RESPONSE = System.getProperty("user.dir") + "\\src\\main\\java\\com\\salsalabs\\ignite\\automation\\apiautomation\\testdata\\devApiTestData\\activities\\response\\";
    public static String TEST_DATA_PATH_P2p_FORm_SUMMARY_RESPONSE = System.getProperty("user.dir") + "\\src\\main\\java\\com\\salsalabs\\ignite\\automation\\apiautomation\\testdata\\devApiTestData\\activities\\response\\";
    public static String TEST_DATA_PATH_FUNDRAISER_SUMMARY_RESPONSE = System.getProperty("user.dir") + "\\src\\main\\java\\com\\salsalabs\\ignite\\automation\\apiautomation\\testdata\\devApiTestData\\activities\\response\\";
    public static String TEST_DATA_PATH_TEAM_SUMMARY_RESPONSE = System.getProperty("user.dir") + "\\src\\main\\java\\com\\salsalabs\\ignite\\automation\\apiautomation\\testdata\\devApiTestData\\activities\\response\\";
    public static String TEST_DATA_PATH_PURCHASES_SUMMARY_RESPONSE = System.getProperty("user.dir") + "\\src\\main\\java\\com\\salsalabs\\ignite\\automation\\apiautomation\\testdata\\devApiTestData\\activities\\response\\";
    public static String TEST_DATA_PATH_TA_SUMMARY_RESPONSE = System.getProperty("user.dir") + "\\src\\main\\java\\com\\salsalabs\\ignite\\automation\\apiautomation\\testdata\\devApiTestData\\activities\\response\\";
    public static String TEST_DATA_PATH_PETITION_SUMMARY_RESPONSE = System.getProperty("user.dir") + "\\src\\main\\java\\com\\salsalabs\\ignite\\automation\\apiautomation\\testdata\\devApiTestData\\activities\\response\\";
    public static String TEST_DATA_PATH_TARGETS_SUMMARY_RESPONSE = System.getProperty("user.dir") + "\\src\\main\\java\\com\\salsalabs\\ignite\\automation\\apiautomation\\testdata\\devApiTestData\\activities\\response\\";
    public static String TEST_DATA_PATH_FUNDRAISING_FORM_SUMMARY_RESPONSE = System.getProperty("user.dir") + "\\src\\main\\java\\com\\salsalabs\\ignite\\automation\\apiautomation\\testdata\\devApiTestData\\activities\\response\\";
    public static String TEST_DATA_PATH_FUNDRASER_METADATA_SUMMARY_RESPONSE = System.getProperty("user.dir") + "\\src\\main\\java\\com\\salsalabs\\ignite\\automation\\apiautomation\\testdata\\devApiTestData\\activities\\response\\";
    public static String TEST_DATA_PATH_TEAM_METADATA_SUMMARY_RESPONSE = System.getProperty("user.dir") + "\\src\\main\\java\\com\\salsalabs\\ignite\\automation\\apiautomation\\testdata\\devApiTestData\\activities\\response\\";
    public static String TEST_DATA_PATH_ACTIVITY_LIST_RESPONSE = System.getProperty("user.dir") + "\\src\\main\\java\\com\\salsalabs\\ignite\\automation\\apiautomation\\testdata\\devApiTestData\\activities\\response\\";

    public static String TEST_DATA_BLAST_LIST_RESPONSE = System.getProperty("user.dir") + "\\src\\main\\java\\com\\salsalabs\\ignite\\automation\\apiautomation\\testdata\\devApiTestData\\blasts\\response\\";

    public static class Endpoints {
        //Metrics
        public static final String METRICS = "/api/integration/ext/v1/metrics";

        //Supporters
        public static final String SEARCH_SUPPORTER = "api/integration/ext/v1/supporters/search";
        public static final String ADD_UPDATE_DELETE_SUPPORTER = "/api/integration/ext/v1/supporters";

        //Segments
        public static final String CREATE_UPDATE_DELETE_SEGMENT = "/api/integration/ext/v1/segments";
        public static final String SEARCH_SEGMENT = "/api/integration/ext/v1/segments/search";
        public static final String ASSIGN_REMOVE_SUPPORTER_TOFROM_SEGMENT = "/api/integration/ext/v1/segments/members";
        public static final String SEARCH_SUPPORTERS_ASSIGNED_TO_SEGMENT = "/api/integration/ext/v1/segments/members/search";

        //Activities
        public static final String SEARCH_ACTIVITIES = "/api/integration/ext/v1/activities/search";

        //Donations Import
        public static final String DONATIONS_IMPORT = "/api/integration/ext/v1/offlineDonations";

        // Developer API
        public static final String ACTIVITYCORESUMMARY = "/api/developer/ext/v1/activities/";

        public static final String GET_LIST_OF_BLASTS = "/api/developer/ext/v1/blasts/";
        public static final String GET_LIST_OF_SUBMISSIONS = "/api/developer/ext/v1/submissions/";
        public static final String DEVAPIMETRICS = "/api/developer/ext/v1/callMetrics";

        public static final String EMAILBLASTS = "/api/developer/ext/v1/blasts";


    }
}
