package com.salsalabs.ignite.automation.common;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.*;
import com.salsalabs.ignite.automation.common.config.GoogleSpreadSheetsMapper;
import org.testng.ITestResult;
import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.security.GeneralSecurityException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import static com.salsalabs.ignite.automation.common.SeleneseTestCase.logger;

public class GoogleDriveClient {

	private static final String EMAIL_BOX;
	private static final String SPREAD_SHEET_ID;
	private static final java.io.File DATA_STORE_DIR;
	private static final boolean IS_GOOGLE_DRIVE_CLIENT_ENABLED;
	private static HttpTransport transport;
    private static JacksonFactory jsonFactory;
    private static FileDataStoreFactory dataStoreFactory;
    private static List<String> scopes;
    private static Sheets service;
	private static Map<String, Map<String,String>> testsResults;
    public GoogleSpreadSheetsMapper map = new GoogleSpreadSheetsMapper();

    static {
		EMAIL_BOX = CommonUtils.getProperty(PropertyName.GOOGLE_ACCOUNT_EMAIL);
		SPREAD_SHEET_ID = CommonUtils.getProperty(PropertyName.GOOGLE_SPREAD_SHEET_ID);
    	scopes = Arrays.asList(SheetsScopes.SPREADSHEETS);
		DATA_STORE_DIR = new java.io.File(System.getProperty("user.home"), ".credentials/sheets.googleapis.com.json");
		jsonFactory = JacksonFactory.getDefaultInstance();
		IS_GOOGLE_DRIVE_CLIENT_ENABLED = Boolean.valueOf(CommonUtils.getProperty(PropertyName.UPDATE_TC,"false"));
    	testsResults =  new HashMap();
    	try {
			transport = GoogleNetHttpTransport.newTrustedTransport();
			dataStoreFactory = new FileDataStoreFactory(DATA_STORE_DIR);
			service = getSheetsService();
		} catch (GeneralSecurityException | IOException e) {
			e.printStackTrace();
		}
	}

	private static Credential authorize() throws IOException {
	    // Load client secrets.
	    File cfile = new File(System.getProperty("user.dir") + "/client_secret.json");
	    if(!cfile.exists()) throw new FileNotFoundException("File /client_secret.json was not found"); SeleneseTestCase.logger.debug("Google drive Security file has been found found");
	    GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(jsonFactory, new InputStreamReader(new FileInputStream(cfile)));
	    // Build flow and trigger user authorization request.
	    GoogleAuthorizationCodeFlow flow =
	            new GoogleAuthorizationCodeFlow.Builder(
	                    transport, jsonFactory, clientSecrets, scopes)
	                    .setDataStoreFactory(dataStoreFactory)
	                    .setAccessType("offline")
	                    .build();
		Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize(EMAIL_BOX);
	    return credential;
	}

	public static Sheets getSheetsService() throws IOException {
	    Credential credential = authorize();
	    return new Sheets.Builder(transport, jsonFactory, credential)
	            .setApplicationName("Google Sheets API")
	            .build();
	}

	public static void writeTCResult(ITestResult result) {
		if (IS_GOOGLE_DRIVE_CLIENT_ENABLED) {
			String status = (result.getStatus() == 1) ?  GoogleSpreadSheetsMapper.PASS : GoogleSpreadSheetsMapper.FAIL;
			String cell = "", listName = "";
			Method method = result.getMethod().getConstructorOrMethod().getMethod();
			if(method.isAnnotationPresent(TestInfo.class)){
				Annotation annotation = method.getAnnotation(TestInfo.class);
				TestInfo testInfo = (TestInfo) annotation;
				cell = testInfo.statusCell();
				listName = testInfo.listName();
			}
			if (!cell.isEmpty() && !listName.isEmpty()) {
				Map<String, String> mapWithColumnsAndStatuses = new HashMap<>();
				if(testsResults.get(listName) != null) {
				   	mapWithColumnsAndStatuses = testsResults.get(listName);
					mapWithColumnsAndStatuses.put(cell, status);
					testsResults.put(listName, mapWithColumnsAndStatuses);
				} else {
					mapWithColumnsAndStatuses.put(cell, status);
					testsResults.put(listName, mapWithColumnsAndStatuses);
				}
			} else {
				logger.warn("@TestInfo annotation has not been populated correctly in " + method.getName() + " test method" );
			}
		}
	}

	public static void updateTCStatusesInRegressionSheet(){
    	if (IS_GOOGLE_DRIVE_CLIENT_ENABLED) {
			getTestsResults().entrySet().stream().forEach(entry -> {
				Map<String, String> map = entry.getValue();
				map.forEach((k,v) -> {
					writeValueToRange(entry.getKey(),k,v);
					writeValueToRange(entry.getKey(),getRightCellA1Notation(k),"Status set by autotest on "
							+ "\n" + CommonUtils.getTodayDate() + " at " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")).toString());
				});
			});
		}
	}

	private static void writeValueToRange(String sheetListName, String range, String value){
		List<Object> listWithValue = new ArrayList<Object>(){{
			add(value);
		}};
		List<List<Object>> listOfListsWithRangeOfValues = new ArrayList<List<Object>>(){{
			add(listWithValue);
		}};
		String rangeToUpdate = sheetListName.concat("!").concat(range);
    	ValueRange valueRange = new ValueRange().setValues(listOfListsWithRangeOfValues);
		try {
			service.spreadsheets().values().update(SPREAD_SHEET_ID, rangeToUpdate, valueRange).setValueInputOption("RAW").execute();
			SeleneseTestCase.logger.info(valueRange.getValues() + " text has been inserted in " +
					range + " cell of " + sheetListName + " spread sheet list");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String getRightCellA1Notation(String currentCellA1Notation){
		return (String.valueOf((char)(currentCellA1Notation.charAt(0)+1))) +
				Integer.parseInt(currentCellA1Notation.substring(1));
	}

	private static Map<String, Map<String, String>> getTestsResults(){
		return testsResults;
	}

	/*private void writeValue(int row, int col, String value, Integer sheetId) throws IOException {
	    
		List<Request> requests = new ArrayList<>();
	    List<CellData> values = new ArrayList<>();

	    values.add(new CellData()
	                .setUserEnteredValue(new ExtendedValue()
	                        .setStringValue(value)));
	        requests.add(new Request()
	                .setUpdateCells(new UpdateCellsRequest()
	                        .setStart(new GridCoordinate()
	                                .setSheetId(sheetId)
	                                .setRowIndex(row)
	                                .setColumnIndex(col))
	                        .setRows(Arrays.asList(
	                                new RowData().setValues(values)))
	                        .setFields("userEnteredValue,userEnteredFormat.backgroundColor")));

	        BatchUpdateSpreadsheetRequest batchUpdateRequest = new BatchUpdateSpreadsheetRequest()
	                .setRequests(requests);
	        service.spreadsheets().batchUpdate(SPREAD_SHEET_ID, batchUpdateRequest)
	                .execute();
	    }

	public void updateTCStatus(int row, int col, String value, Integer sheetId, ArrayList<String> bug) throws IOException {
		writeValue(row, col, value, sheetId);
		writeValue(row, map.ISAUTOMATED_COLUMN, "YES", sheetId);
		if (bug != null) {
			for (int i = 0; i < bug.size(); i++) {
				bug.set(i, bug.get(i)+"\n");
			}

			writeValue(row, map.NOTES_COLUMN, CommonUtils.getTodayDate() + "\n" +
			
			bug.toString().replace("[", "").replace("]", "").replace("\n,", "\n"), sheetId);
		}
		}*/
	
	public List<List<Object>> readValue(String range, int colN) throws IOException {
        ValueRange response = service.spreadsheets().values()
            .get(SPREAD_SHEET_ID, range)
            .execute();
        List<List<Object>> values = response.getValues();
        if (values == null || values.size() == 0) {
            System.out.println("No data found.");
        }
        return values;
    }
	
	private List<Sheet> getListOfSheets() throws IOException {
        Spreadsheet sp = service.spreadsheets().get(SPREAD_SHEET_ID).execute();
        return sp.getSheets();
    }
	
	public int getRowNumberById(String id) throws IOException {
		List<List<Object>> ids = readValue("Syndication!A1:A1000",map.ID_COLUMN);
		for (int i = 0; i < ids.size(); i++) {
			if (ids.get(i).size() > 0 && ids.get(i).contains(id)) {
          		return i;
			}
		}
		return -1;
    }
	
	public Integer getSheetIdByTitle(String title) throws IOException {
		List<Sheet> sh = getListOfSheets();
		for (int i = 0; i < sh.size(); i++) {
        	if (sh.get(i).getProperties().getTitle().equals(title)) {
        		return sh.get(i).getProperties().getSheetId();
			}        	
		}
		return -1;
    }

	
}
