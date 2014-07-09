package selenium;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import junit.framework.AssertionFailedError; 

import org.junit.ComparisonFailure;

import com.thoughtworks.selenium.Selenium;

import java.lang.Object;

import org.testng.Assert;

import selenium.SeleneseTestCase;

public class CommonUtils{

	public static Map<String, String> strMap = new HashMap<String, String>();
	public static Map<String, String> StanParam = new HashMap<String, String>();
	static Properties props = new Properties();
	static Properties  issuesList = new Properties(); 
	static String propFileName = "properties.prop";
	public static void verify(String Message, Object Expected, Object Actual, Boolean Fail){
		try {
			Assert.assertEquals(Actual, Expected);
		} catch (AssertionError e) {
			SetParam("testResult", "fail");
			if (Fail){
				throw new AssertionFailedError(Message + " - " + e.getMessage());
			}else{
				SeleneseTestCase.logger.error("Verification error: " + Message + " - " + e.getMessage());
			}
		}		
	}
//	Check, if one of checks is fail, then fail test
	public static void checkAndFail(String TestName){
		if (CommonUtils.getParam("testResult", false).equals("fail")) {
			throw new AssertionError(TestName + " test is FAIL. Please see log for details");
		}
   }

	public static void SetStandtartParam(String key, String aValue){

    	StanParam.put(key, aValue);
    }

    public static String getStandtartParam(String aKey) {

    	 if (StanParam.get(aKey) == null){
         	System.err.println("Not correct data. Param " + aKey + " not found.");
         }
        return StanParam.get(aKey);
        
  	}

	public static void ClearStandtartParam() {

		StanParam.clear();
	}
	
	public static void SetParam(String key, String aValue){

          strMap.put(key, aValue);
    }

    public static String getParam(String aKey, Boolean printError) {

        if (strMap.get(aKey) == null){
        	return "false";
        }
    	return strMap.get(aKey);
    }
    
	public static String getParam(String aKey) {
		if (strMap.get(aKey) == null){
			System.out.println("Not correct data. Param " + aKey + " not found.");
			return "false";
        }
    	return strMap.get(aKey);
	}

    public static void Clear() {

    	strMap.clear();
    }
    
    public static String SetBrowser (String aBrowser){
		if (aBrowser == "IE") {
			return "*iehta";
		}else	if (aBrowser == "FF") {
				return "*firefox";
				} else {
					return "*chrome";
					}
    }
    
    public static boolean AssertValue (String message, Object expected, Object actual, Boolean ignoreException) throws ComparisonFailure {

		try {
			
			Assert.assertEquals(expected,actual);
			//selenium.logAssertion("assertEquals", message, "expected=" + expected.toString() + " actual=" + actual.toString());
		} catch (ComparisonFailure e) {
			System.err.println(e.getMessage());
			
			if (!ignoreException){
				throw new ComparisonFailure(message, expected.toString(), actual.toString());
			}
			
		}
		return false;    	
    }
    
    public static String BooleanTo1or0 (Boolean aValue) {
    	if (aValue){
    		return "1";
		}else {
			return "0";
			}    	
   }
    
    public static Boolean BooleanTo1or0Reverce (String aValue) {
    	if (aValue.equals("1")){
    		return true;
		}else {
			return false;
		}    	
   }
    
    public static Boolean ReverceBooleanValue (Boolean aValue) {
    	if (aValue){
    		return false;
		}else {
			return true;
		}    	
   }
    
    public static boolean WaitObject (Selenium selenium, String aIdLocator, int aTime){
    	try {
			for (int i = 0; i < aTime; i++) {
				if (selenium.isElementPresent(aIdLocator)) {
					return true;
				}else{
					Thread.sleep(1000);
					if (i == aTime -1){
						throw new Exception("Element '" + aIdLocator + "'. Not found");
					}
				}
			}
		} catch (Exception e) {
			new Exception("Element " + aIdLocator + "not found!");
		}
   	return false;
   }
    
    public static boolean WaitTextInObject (Selenium selenium, String aIdLocator,
    			String aText, int aTime) throws Exception {
    	for (int i = 0; i < aTime; i++) {
    		if (selenium.isElementPresent(aIdLocator) && selenium.isTextPresent(aText)) {
    			//System.err.println(selenium.getText(aIdLocator) + " - " + aText);
				if (selenium.getText(aIdLocator).indexOf(aText)!=-1){
					return true;
				}else if(i==aTime-1){
					throw new Exception("Wrong text. Text '" + aText + "' not found.");
				}else{
					Thread.sleep(1000);
				}
    		}else{
    			Thread.sleep(1000);
    		}
    	
		}	
    	return false;
   }
    
    public static boolean Check(Boolean active, String aIdLocator, Selenium selenium) throws Exception {
    	if(active){
    		//if(!selenium.isChecked(aIdLocator)){
        	selenium.click(aIdLocator);    		
        	//}
    	}else{
    		//if(selenium.isChecked(aIdLocator)){
        		selenium.click(aIdLocator);    		
        	//}
    	}
    	
   	return false;
   }
    
    public static boolean WaitObjectAndClick (Selenium selenium, String aIdLocator, int aTime) {
    	try {
			if (WaitObject(selenium, aIdLocator, aTime)){
				selenium.click(aIdLocator);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
   	return false;
   }
    
    public static boolean WaitIsEditable (Selenium selenium, String aIdLocator, int aTime) {
    	try {
    		for (int i = 0; i < aTime; i++) {
				if (selenium.isEditable(aIdLocator)) {
					return true;
				}else{
					Thread.sleep(1000);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
   	return false;
   }
    
    public static boolean WaitIsEditableAndClick (Selenium selenium, String aIdLocator, int aTime) {
    	try {
    		for (int i = 0; i < aTime; i++) {
				if (selenium.isEditable(aIdLocator)) {
					selenium.click(aIdLocator);
					return true;
				}else{
					Thread.sleep(1000);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
   	return false;
   }
    
    public static String MakeDate (String aDate) {
    	if (Integer.parseInt(aDate) < 10){
    		return "0" + aDate;
    	}
   	return aDate;
   }
    
    //returns row count in the table. if not returns 0
    public static int getRowCount (String aTable, Selenium selenium){
    	Boolean i = true;
    	int j = 0;
    	CommonUtils.WaitObject(selenium, aTable, 5);
    	while (i) {
    		try {
    			j++;
    			selenium.getTable(aTable + "." + new Integer(j).toString() + ".0");
        		
    			
			} catch (Exception e) {
				return j - 1;
			}
		}
   	return j - 1;
   }
    
    //returns col count in the table. if not returns 0
    public static int getColCount (String aTable, Selenium selenium) throws Exception {
    	Boolean i = true;
    	int j = 0;
    	CommonUtils.WaitObject(selenium, aTable, 15);
    	while (i) {
    		try {
    			j++;
    			selenium.getTable(aTable + ".0." + new Integer(j).toString());
        		
    			
			} catch (Exception e) {
				return j - 1;
			}
		}
   	return j - 1;
   }
    
    public static String ReplaceSimbol (String aString, String subStr, String subStrNew) throws Exception {
		StringBuffer s = new StringBuffer(aString);
    	if(aString.indexOf(subStr) > 0){
    		//System.err.println("Input string - " + aString + " Incomming paramm - " + subStr + ", " + subStrNew + " Start index and end index - " + aString.indexOf(subStr) + ", " + aString.indexOf(subStr) + subStr.length());
        	s.replace(aString.indexOf(subStr), aString.indexOf(subStr) + subStr.length(), subStrNew);
    	}    	
		return s.toString();
    }
    
    public static String CreateDateTimeString(){
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd '_' HH-mm-ss");    	
		return dateFormat.format(new Date());
    }
    
    public static String AddDaysToCurrentDate(Integer days){
    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");    	
    	Calendar c = Calendar.getInstance(); 
    	c.setTime(new Date());
    	c.add(Calendar.DATE, days);
    	return dateFormat.format(c.getTime());
    }
    
    public static String getMonthNumber(String aMonth){
    	Integer monthNumber = 0;
    	
    	String[] profileData = {"January", "February", "March", "April", "May", "June",	"July", "August", "Septembar", "October", "November",
			"December"};    	
    	for (int i = 0; i < profileData.length; i++) {
    		if (aMonth.equals(profileData[i])) {
    			monthNumber = i+1;
    			
			}
		}
/*    	if (monthNumber.toString().length() == 1) {
    		return "0" + monthNumber.toString();
		}else {*/
			return monthNumber.toString();
		//}
		
    }
    
    public static String ConvertDateFormat(String aDate) throws ParseException{
    	aDate = aDate.replaceAll("/", ".");
    	SimpleDateFormat dateFormat = new SimpleDateFormat("MM.dd.yyyy");		
		return dateFormat.format(dateFormat.parse(aDate));	
    }

    
    public static Integer getAge(String bYear) {
    	Date birthdate = new Date();
    	Calendar calendar = GregorianCalendar.getInstance();
    	
    	calendar.setTime(new Date());
    	int currentYear = calendar.get(Calendar.YEAR);

    	calendar.setTime(birthdate);
    	calendar.set(Calendar.YEAR, Integer.parseInt(bYear));
    	int birthYear = calendar.get(Calendar.YEAR);
    	
    	return currentYear - birthYear;
    }

    public static String getOtherIndex(String aElement, Selenium selenium) throws InterruptedException {
 	
    	Integer selectIndex = Integer.parseInt(selenium.getSelectedIndex(aElement)) + 1;
    	if (selectIndex > selenium.getSelectedLabels(aElement).length) {
			try {
				selenium.select(aElement, "index=" + selectIndex);
			} catch (Exception e) {
				System.err.println("Now selected last value in a SelecteBox. Value is - " + selenium.getSelectedLabel(aElement) + ". Will be select previous value");
				selectIndex = selectIndex - 2;
			}
    		
		}
    	return selectIndex.toString();
    }
    
    public static String ChangeEditTextBoxValueEndGetNewValue(String aElement, String aParamName, String addValue, Selenium selenium) {

    	if (!aParamName.equals("")) {
    		CommonUtils.SetParam(aParamName, selenium.getValue(aElement));	
		} 
		String newValue = CommonUtils.getParam(aParamName) + addValue;
    	selenium.type(aElement, newValue);
		selenium.fireEvent(aElement, "blur");
		return newValue;
		

    }
    
    public static void ChangeYesNoRadioButtonValueEndGetNewValue(String aElementY, String aElementN, String aParamName, Selenium selenium) {
    	if (selenium.isChecked(aElementY)) {
			selenium.click(aElementN);
			SetParam(aParamName, "1");
		}else {
			selenium.click(aElementY);
			SetParam(aParamName, "0");
		}   	
    }
    
    public static void ChangeCheckBoxValueEndGetNewValue(String aElement, String aParamName, Selenium selenium) {

    	selenium.click(aElement);
    	CommonUtils.SetParam(aParamName, new Boolean(selenium.isChecked(aElement)).toString());
    }

    public static void ChangeSelectBoxValueEndSaveNewValue(String aElement, String aParamName, Selenium selenium) throws InterruptedException {
    	
    	selenium.select(aElement, "index=" + CommonUtils.getOtherIndex(aElement, selenium));
    	
    	selenium.fireEvent(aElement, "blur");
		CommonUtils.SetParam(aParamName, selenium.getSelectedLabel(aElement));
    }
    
	public static Boolean LoadImage (String aTextElementName, String aFullPathPictureWithName, String aBrowser, Selenium selenium) throws IOException {		
		if(new File(aFullPathPictureWithName).exists()){			
			if (aBrowser.equalsIgnoreCase("*iexplore")) {
				Runtime.getRuntime().exec(new File("utils").getAbsolutePath() + File.separator +  "upload.exe " + aFullPathPictureWithName);
				selenium.click(aTextElementName);
				
				if (selenium.isElementPresent("btnSelect")) {
					selenium.click("btnSelect");
				}
				
			}else {
				selenium.type(aTextElementName, aFullPathPictureWithName);
				selenium.fireEvent(aTextElementName, "blur");
			}
			return true;
		}
		return false;
		
	}
	
	public static String getDatesSeparator (String aBrowser) {		
		if (aBrowser.equalsIgnoreCase("*iexplore")){
			return ".";
		}else return "/";		
	}
	
	public static String Assert(String assertName, String expected, String actual) {		
		String errrorMessage = "true";
		if (!actual.equals(expected)) {
			errrorMessage = "Wrong " + assertName +	". Expexted " + expected + " but was " + actual;
		}
		return errrorMessage;		
	}
	
	public static String getTodayDateDependsOnBrowser(String browser) {		
		return getTodayDateDependsOnBrowser(browser, 0);
	}
	
	public static String getTodayDateDependsOnBrowser(String browser, Integer addDays) {		
		String separator = getDatesSeparator(browser);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd" + separator + "MM" + separator + "yyyy");
		Calendar c = Calendar.getInstance(); 
    	c.setTime(new Date());
    	c.add(Calendar.DATE, addDays);
		return dateFormat.format(c.getTime());
	}
	
	public static String getTodayDate() {		
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM.dd.yyyy");		
		return dateFormat.format(new Date());
	}
	
	public static String getUnicName() {		
		String uniqueName = CommonUtils.CreateDateTimeString();
		uniqueName = uniqueName.replace(" ", "");
		uniqueName = uniqueName.replace("-", "");
		uniqueName = uniqueName.replace("_", "");
		return uniqueName;
	}
	
	public static Boolean WriteDataToFile(String aData, Boolean reWrite) throws IOException {		
		Map<Integer, String> oldData = null;
		if (!reWrite) {
			oldData = ReadData();
		}
		String filename = new File("TestData").getAbsolutePath();

		if (!new File(filename + File.separator + "data.txt").exists()) {
				new File(filename + File.separator + "data.txt").createNewFile();
		}
		
		File data = new File(filename + File.separator + "data.txt");
		PrintWriter p = new PrintWriter(new FileWriter(data));
		p.println(aData);
		if(!reWrite){
			for (int i = 0; i < oldData.size(); i++) {
				p.println(oldData.get(i));
			}
		}
		
		p.close();
		return true;
	}	
	
	public static Map<Integer, String> ReadData() {		
		String filename = new File("TestData").getAbsolutePath();		
		Map<Integer, String> data = new HashMap<Integer, String>();
		int i = 0;
		try {
			BufferedReader in = new BufferedReader(new FileReader(filename + File.separator + "data.txt"));
			String line;
			while ((line = in.readLine()) != null){
				data.put(i, line);
				
				i++;
			}
			in.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public static Integer getTextAfter(String aText, String subString) {		
		return Integer.valueOf(aText.substring(aText.lastIndexOf(subString)+ subString.length(), aText.length()));
		
	}
	
	public static String CreateLongString(Integer aLong) {		
		String aText= "It's test string. Length is " + aLong.toString();
		while (aText.length()<aLong) {
			aText = aText + aText;			
		}
		return aText.substring(0, aLong);
		
	}
	
	public static String getRandomValue(Integer intMaxValue, Integer doubleValueLength) {		
		Random randomGenerator = new Random();
		String result = Double.toString(randomGenerator.nextInt(intMaxValue) + randomGenerator.nextDouble());
		result = result.substring(0, result.lastIndexOf(".")+ doubleValueLength+1); 
		if (doubleValueLength == 0) result = result.replace(".", "");
		return result;
	}
	
	public static String getRandomValueFromTo(Integer intMinValue, Integer intMaxValue, Integer doubleValueLength) {		
		Random randomGenerator = new Random();
		Integer val=0;
		while (val <= intMinValue) {
			val = randomGenerator.nextInt(intMaxValue);			
		}
		if (doubleValueLength > 0) {
			String result = Double.toString(val + randomGenerator.nextDouble());
			return result.substring(0, result.lastIndexOf(".")+ doubleValueLength+1); 
		}
		return Integer.toString(val);
	}
	
	public static String getRandomNumericValueFixedLength(Integer len) {		
		String s = "";
		while (s.length() < len) {
			s = s + getRandomValueFromTo(0, 9, 0);		
		}
		return s;
	}


	public static String[] getElementsByAttribute(String strTagName, String strAttributeName, 
		String strAttributeValue, Selenium selenium){
		String script = "var arrElements = window.document.getElementsByTagName(" + strTagName + ");";
			script += "var arrReturnElements = new Array();";
			script += "var oAttributeValue = (typeof " + strAttributeValue +" != \"undefined\")? new RegExp(\"(^|\\s)" + strAttributeValue + "(\\s|$)\") : null;";
			script += "var oCurrent;";
			script += "var oAttribute;";
			script += "for(var i=0; i<arrElements.length; i++){";
			script += "oCurrent = arrElements[i];";
			script += "oAttribute = oCurrent.getAttribute(" + strAttributeName + ");";
			script += "if(typeof oAttribute == \"string\" && oAttribute.length > 0){";
			script += "if(typeof " + strAttributeValue + "== \"undefined\" || (oAttributeValue && oAttributeValue.test(oAttribute))){";
			script += "arrReturnElements.push(oCurrent);";
			script += "   }" +
					"}" +
					"}";
System.err.println(script);
	script += " arrElements.toString();";
	 String[] elements = selenium.getEval(script).split(","); // Split the string.
     return elements;
	}
	
 	public static String[] getAllAttributes (String elementType, String elementTag, String elementAttribute, Selenium selenium) {
        String script = "var inputAttribute  = new Array();";// Create array in java script.
        script += "var counter = 0;"; // Counter for element type attributes.
        script += "var tagFields  = new Array();"; // Create array in java script.
        script += "tagFields = window.document.getElementsByTagName('" + elementTag + "');"; // Collect tag elements.
        script += "for(var i=0; i<tagFields.length; i++) {"; // Loop through the collected elements.
        script += "if(tagFields[i]." + elementAttribute + " !=null " +
        "&& tagFields[i]." + elementAttribute + " !='undefined' " +
        "&& tagFields[i].getAttribute('type') == '" + elementType + "') {"; // If input field is of type check box and input id is not null.
        script += "inputAttribute[counter]=tagFields[i]." + elementAttribute + " ;" + // Save element attribute of element type to inputAttribute array.
        "counter++;" + // increment the counter.
        "}" + // end of if.
        "}"; // end of for.
        script += "inputAttribute.toString();" ;// Convert array in to string.
        
        String[] attributes = selenium.getEval(script).split(","); // Split the string.
        return attributes;
 	}
 	
 	
 	public static String getRowNumberInTheTableByValue (String aTable, String aTableHeader,
 			String aColumnName, String aValue, Selenium selenium) throws Exception 
 	{
        Integer rowCount, colCount;
        rowCount = getRowCount(aTable, selenium);
        //System.err.println("Row count - " + rowCount);
        colCount = getColCount(aTableHeader, selenium);
        //System.err.println("Col count - " + colCount);
        for (int i = 0; i <= colCount; i++) {
			if (selenium.getTable(aTableHeader + ".0." + new Integer(i).toString()).equals(aColumnName)) {
				colCount = i;
				//System.err.println("Column with e-mail - " + colCount);
				break;
			}else {
				if (i==colCount-1) {
					System.err.println("Column with name " + aColumnName + " is not found.");
				} 
			}
		}
        for (int i = 0; i <= rowCount; i++) {
        	if (selenium.getTable(aTable + "." + new Integer(i).toString() + "." + colCount).equals(aValue)) {
        		//System.err.println("Row with e-mail - " + i);
        		return new Integer(i+1).toString();
			}else {
				if (i==rowCount) {
					System.err.println("Row with value " + aValue + " is not found.");
					return "false";
				} 
			}
		}
        
        return rowCount.toString();
 	}
 	
 	//returns array from string. Use : as symbol
 	public static String[] getArrayFromStringBySymbol (String aText, String aSymbol)
 	{
 		return aText.split(aSymbol);  
 	}
 	
 	public static FileInputStream getFileInputStream() {
 		return getFileInputStream(propFileName);
	}
 	
 	private static FileInputStream getFileInputStream(String fileName) {
        FileInputStream fis=null;
 		try {
 			fis = new FileInputStream(new File(fileName).getAbsolutePath());
 		} catch (FileNotFoundException e1) {
 			e1.printStackTrace();
 		}
 		return fis;
	}
 	
    private static void loadProperties(String fileName) {
      	 try {
    			props.load(getFileInputStream(fileName));
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
   	}
       
    private static void saveProperties() {
           saveProperties(propFileName);
    }
    
    @SuppressWarnings("deprecation")
	private static void saveProperties(String fileName) {
        try {
			props.save( new FileOutputStream(new File(fileName).getAbsolutePath()), "AUTOMATICALLY GENERATED" );
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static void setProperty(String key, String value) {
    	if (new File(propFileName).exists()) {
			loadProperties(propFileName);
		}
    	props.setProperty(key,value);
    	saveProperties(); 
	}
       
    public static String getProperty(String key) {
    	return getProperty(key, "0");    	
	}
    
    public static String getProperty(String key, String defValue) {
    	if (new File(propFileName).exists()) {
			loadProperties(propFileName);
		}
    	if (props.getProperty(key)==null) {
    		props.setProperty(key, defValue);
		}
    	return props.getProperty(key);
    	
	}
    
    public static void saveIssueKey(String key, String value) {
    	if (new File("issues.prop").exists()) {
			loadProperties("issues.prop");
		}
    	issuesList.setProperty(key,value);
    	saveIssues("issues.prop"); 
	}
    
    public static String getIssueKey(String key) {
    	if (new File("issues.prop").exists()) {
    		loadIssues("issues.prop");
		}
    	if (issuesList.getProperty(key)==null) {
    		return "";
		}
    	return issuesList.getProperty(key);
	}
    
    private static void loadIssues(String fileName) {
     	 try {
   			issuesList.load(getFileInputStream(fileName));
   		} catch (IOException e) {
   			e.printStackTrace();
   		}
  	}
    
    @SuppressWarnings("deprecation")
	private static void saveIssues(String fileName) {
        try {
        	issuesList.save( new FileOutputStream(new File(fileName).getAbsolutePath()), "AUTOMATICALLY GENERATED" );
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
 	
}
	


