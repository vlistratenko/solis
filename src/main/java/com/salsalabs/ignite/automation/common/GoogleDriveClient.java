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
import com.google.api.services.sheets.v4.model.BatchUpdateSpreadsheetRequest;
import com.google.api.services.sheets.v4.model.CellData;
import com.google.api.services.sheets.v4.model.ExtendedValue;
import com.google.api.services.sheets.v4.model.GridCoordinate;
import com.google.api.services.sheets.v4.model.Request;
import com.google.api.services.sheets.v4.model.RowData;
import com.google.api.services.sheets.v4.model.Sheet;
import com.google.api.services.sheets.v4.model.Spreadsheet;
import com.google.api.services.sheets.v4.model.UpdateCellsRequest;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.salsalabs.ignite.automation.common.config.GoogleSpeadSheetsMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GoogleDriveClient{
	
	private static HttpTransport transport;
    private static JacksonFactory jsonFactory;
    private static FileDataStoreFactory dataStoreFactory;
    private static final java.io.File DATA_STORE_DIR = new java.io.File(System.getProperty("user.home"), ".credentials/sheets.googleapis.com.json");
    private static List<String> scopes = Arrays.asList(SheetsScopes.SPREADSHEETS);
    static String spreadsheetId = "1uwtl1AVXhn5wEuc1gvrFOuW2NJwS1MrpNLuH5zX2eLE";
    Sheets service;
    GoogleSpeadSheetsMapper map = new GoogleSpeadSheetsMapper();

    
    
    public GoogleDriveClient() {
    	try {
			transport = GoogleNetHttpTransport.newTrustedTransport();
		} catch (GeneralSecurityException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			dataStoreFactory = new FileDataStoreFactory(DATA_STORE_DIR);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        jsonFactory = JacksonFactory.getDefaultInstance();
        try {
			service = getSheetsService();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
	public static Credential authorize() throws IOException {
	    // Load client secrets.
	    File cfile = new File(System.getProperty("user.dir") + "/client_secret.json");
	    SeleneseTestCase.logger.info("Google drive Security file was found");
	    GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(jsonFactory, new InputStreamReader(new FileInputStream(cfile)));
	    // Build flow and trigger user authorization request.
	    GoogleAuthorizationCodeFlow flow =
	            new GoogleAuthorizationCodeFlow.Builder(
	                    transport, jsonFactory, clientSecrets, scopes)
	                    .setDataStoreFactory(dataStoreFactory)
	                    .setAccessType("offline")
	                    .build();
	    Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("vitalik.va@gmail.com");
	    return credential;
	}

	public static Sheets getSheetsService() throws IOException {
	    Credential credential = authorize();
	    return new Sheets.Builder(transport, jsonFactory, credential)
	            .setApplicationName("Google Sheets API")
	            .build();
	}

	private void writeValue(int row, int col, String value, Integer sheetId) throws IOException {
	    
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
	        service.spreadsheets().batchUpdate(spreadsheetId, batchUpdateRequest)
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
		}
	
	public List<List<Object>> readValue(String range, int colN) throws IOException {
        ValueRange response = service.spreadsheets().values()
            .get(spreadsheetId, range)
            .execute();
        List<List<Object>> values = response.getValues();
        if (values == null || values.size() == 0) {
            System.out.println("No data found.");
        }
        return values;
    }
	
	private List<Sheet> getListOfSheets() throws IOException {
        Spreadsheet sp = service.spreadsheets().get(spreadsheetId).execute();
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
