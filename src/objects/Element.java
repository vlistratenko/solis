package objects;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.thoughtworks.selenium.Selenium;

import selenium.CommonUtils;
import selenium.SeleneseTestCase;

abstract class Element {
	public String path;
	WebDriver driver;
	Logger logger;
	Selenium selenium;
	int cTimeOut;
	String elementName;
	Integer defaultTimeOut;
	Boolean isRequeired = true;
	
	public Element(String elementPath, String name) {
		this.path = elementPath;
		driver = SeleneseTestCase.driver;
		logger = SeleneseTestCase.logger;
		selenium = SeleneseTestCase.selenium;
		cTimeOut = SeleneseTestCase.cTimeOut;
		defaultTimeOut = SeleneseTestCase.defaultTimeOut;
		elementName = name;
	}
	
	public Element(String elementPath, String name, Boolean isReq) {
		this.path = elementPath;
		driver = SeleneseTestCase.driver;
		logger = SeleneseTestCase.logger;
		selenium = SeleneseTestCase.selenium;
		cTimeOut = SeleneseTestCase.cTimeOut;
		defaultTimeOut = SeleneseTestCase.defaultTimeOut;
		elementName = name;
		isRequeired = isReq;
	}
	
	/** 
	 * @param cTimeOut in miliseconds
	 */
	protected void sleep(Integer cTimeOut){
		try {
			logger.debug("Sleep on " + cTimeOut/1000 + " seconds");
			Thread.sleep(cTimeOut);
			logger.debug("Sleep on is over");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/** 
	 * @param cTimeOut in seconds
	 */
	public void setImplicity(Integer cTimeOut){
		driver.manage().timeouts().implicitlyWait(cTimeOut, TimeUnit.SECONDS);
	}
	
	protected void clickACtion(String locator) {
		logger.debug("Click on < " + locator + " >.");
		//getActionBuilder().moveToElement(findElementByXpath(locator)).perform();
		getActionBuilder().click(findElementByXpath(locator)).perform();
	}
	
	protected void moveToElement(String locator) {
		logger.debug("Move to element < " + locator + " >.");
		getActionBuilder().moveToElement(findElementByXpath(locator)).perform();
	}
	
	public String setAttribute(String attName, String attValue) {
		WebElement element = findElementByXpath(path);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", 
                element, attName, attValue);
		return attValue;
    }
	
	public void removeAttribute(String attName) {
		WebElement element = findElementByXpath(path);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].removeAttribute(arguments[1]);", 
                element, attName);
    }
		
	public void onClick(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
    }
	
	public void onBlur(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].onblur();", 
                element);
    }
	/*redefined methods*/	
	
	public void scrollIntoView() {
		WebElement element = findElementByXpath(path);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].scrollIntoView();", element);
	}
	
	protected void click(String locator){		
		logger.debug("Click on < " + locator + " >.");
		try {
			findElementByXpath(locator).click();
		} catch (Exception e) {
			if (isRequeired) {e.printStackTrace();
				throw new ElementNotFoundException(elementName, "xpath", locator);
			}else{
				logger.error(e.getMessage());
				e.printStackTrace();
			}
		}			

	}
	
	protected void clickJS() {
		WebElement element = findElementByXpath(path);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
	}
	
	protected void clickByTABKey(String locator){		
		logger.debug("Click on < " + locator + " >.");
		waitObject( locator, 10*cTimeOut);
		try {
			sendKeys(locator, Keys.TAB);
		} catch (Exception e) {
			if (isRequeired) {e.printStackTrace();
				throw new ElementNotFoundException(elementName, "xpath", locator);
			}else{
				logger.error(e.getMessage());
				e.printStackTrace();
			}
		}			

	}
	
	protected void sendKeys(String locator, Keys key){		
		logger.debug("Click on < " + locator + " >.");
		findElementByXpath(locator).sendKeys(key);	

	}
	
	protected void sendKeys(String locator, String key){		
		logger.debug("Click on < " + locator + " >.");
		findElementByXpath(locator).sendKeys(key);	

	}
	
/*	protected void clickAt(String locator, String coordString){		
		waitObject(locator, cTimeOut*60);
		try {
			selenium.clickAt(locator, coordString);
		} catch (Exception e) {
			if (isRequeired) {e.printStackTrace();
				throw new ElementNotFoundException(locator, "xpath", locator);
			}else{
				logger.error(e.getMessage());
				e.printStackTrace();
			}
		}
		logger.debug("Click on < " + locator + " >. by coord - " + coordString);
	}
	
	protected void click2(String locator){		
		try {
			selenium.click(locator);
		} catch (Exception e) {
			if (isRequeired) { e.printStackTrace();
				throw new ElementNotFoundException(elementName, "xpath", locator);
			}else{
				logger.error(e.getMessage());
				e.printStackTrace();
			}
		}
		logger.debug("Click on < " + locator + " >.");
	}*/
	
	protected void submit(String locator){		
		waitObject(locator, cTimeOut*30);
		try {
			driver.findElement(By.xpath(locator)).submit();
		} catch (Exception e) {
			if (isRequeired) { e.printStackTrace();
				throw new ElementNotFoundException(elementName, "xpath", locator);
			}else{
				logger.error(e.getMessage());
				e.printStackTrace();
			}
		}
		logger.debug("Submit on < " + locator + " >.");
	}
	
/*	protected void fireEvent(String locator, String event){		
		waitObject(locator, cTimeOut*30);
		selenium.fireEvent(locator, event);
		logger.debug("Generate event < " + event + " > for < " + locator + " >.");
	}*/
	
	protected void highlight(String locator){		
		waitObject(locator, cTimeOut*30);
		try {
			selenium.highlight(locator);
		} catch (Exception e) {
			if (isRequeired) { e.printStackTrace();
				throw new ElementNotFoundException(elementName, "xpath", locator);
			}else{
				logger.error(e.getMessage());
				e.printStackTrace();
			}
		}
		logger.debug("Element < " + locator + " > was highlighted.");
	}
	
	protected void clearTextField(String locator){
		Boolean isFile = false;
		try {
			try{
				isFile = getAttributeValue(locator, "type").equalsIgnoreCase("file");
			}catch (Exception e) {
				
			}
			if (!isFile) {
				findElementByXpath(locator).clear();
			}
		} catch (Exception e) {
			if (isRequeired) { e.printStackTrace();
				throw new ElementNotFoundException(elementName, "xpath", locator);
			}else{
				logger.error(e.getMessage());
				e.printStackTrace();
			}
		}
		logger.debug("Cleaned up a value in the <" + locator + " >.");	
	}	 
		
	protected void type(String locator, String value){
		clearTextField(locator);
		if (value != "") {
			waitObject(locator, cTimeOut*30);
			try {
				findElementByXpath(locator).sendKeys(value);
			} catch (Exception e) {
				if (isRequeired) { 
					e.printStackTrace();
					throw new ElementNotFoundException(elementName, "xpath", locator);					
				}else{
					logger.error(e.getMessage());
					e.printStackTrace();
				}
			}
			logger.debug("Text < " + value + " > was entered into the < " + locator+ " >.");	
		}else{
			logger.debug("Value is empty. Nothing was typed");
		}
	}
	
	protected void closePopupWindow(){
		logger.debug("Try to close popup window");
		selenium.close();
	}
	
	protected Integer getXPathCount(String xpath) {
		logger.debug("Try to count xpathes " + xpath);
		return findElementsByXpath(xpath).size();//selenium.getXpathCount(xpath).intValue();
	}
	
	protected void select(String locator, String value){		
		logger.debug("Try to select value < " + value + " > in the < " + locator+" >.");
		try {
			new	Select(findElementByXpath(locator)).selectByVisibleText(value);
		} catch (Exception e) {
			if (isRequeired) { e.printStackTrace();
				throw new ElementNotFoundException(elementName, "xpath", locator);
			}else{
				logger.error(e.getMessage());
				e.printStackTrace();
			}
		}
		logger.debug("Value < " + value + " > was selected into the < " + locator+" >.");
	}
	
	protected void select(String locator, int index){		
		logger.debug("Try to select value by index < " + index + " > in the < " + locator + " >.");
		try {
			new	Select(findElementByXpath(locator)).selectByIndex(index);
		} catch (Exception e) {
			if (isRequeired) { e.printStackTrace();
				throw new ElementNotFoundException(elementName, "xpath", locator);
			}else{
				logger.error(e.getMessage());
				e.printStackTrace();
			}
		}
		logger.debug("Value with index < " + index + " > was selected into the < " + locator+" >.");
	}

	protected void selectAndWait(String locator, String value) throws InterruptedException{		
		select(locator, value);
		waitForPageToLoad();
	}
	
	protected String getSelectedValue(String locator){		
		return new	Select(findElementByXpath(locator)).getFirstSelectedOption().getCssValue("value");
	}
	
	protected String getSelectedLabel(String locator){		
		return new Select(findElementByXpath(locator)).getFirstSelectedOption().getText();
	}
	
	protected List<WebElement> getSelectOptions(String locator){		
		Select select = new	Select(findElementByXpath(locator));
		return select.getOptions();
	}
	
	protected void check(String locator, Boolean value){		
		if (value) {
			check(locator);
		}else{
			uncheck(locator);
		}
	}
	
	protected void check(String locator){		
		logger.debug("Check checkbox - < " + locator+" >.");
		try {
			selenium.check(locator);
		} catch (Exception e) {
			if (isRequeired) { e.printStackTrace();
				throw new ElementNotFoundException(elementName, "xpath", locator);
			}else{
				logger.error(e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	protected void uncheck(String locator){		
		logger.debug("Uncheck checkbox - < " + locator+" >.");		
		try {
			selenium.uncheck(locator);	
		} catch (Exception e) {
			if (isRequeired) { e.printStackTrace();
				throw new ElementNotFoundException(elementName, "xpath", locator);
			}else{
				logger.error(e.getMessage());
				e.printStackTrace();
			}
		}	
	}
	
	/**
	 * Returns URL of current page
	 * @return String URL
	 */
	protected String getLocation(){
		logger.debug("Get current URL location < "+ selenium.getLocation()+" >.");
		return selenium.getLocation();
	}
	
	protected void waitForPageToLoad(){
		logger.debug("Waiting for page loads.");
		selenium.waitForPageToLoad(cTimeOut*20+"");
	}
	
	protected String getText(String locator){
		logger.debug("Get text from - <" + locator+">. ");
		//logger.debug("Returned text is - <" + selenium.getText(locator)+">. ");
		try {
			return findElementByXpath(locator).getText();	
		} catch (Exception e) {
			if (isRequeired) { e.printStackTrace();
				throw new ElementNotFoundException(elementName, "xpath", locator);
			}else{
				logger.error(e.getMessage());
				e.printStackTrace();
			}
		}
		return selenium.getText(locator);
	}
	
	protected String getValue(String locator){
		logger.debug("Get value from - <" + locator+">. ");
		logger.debug("Returned text is - <" + selenium.getValue(locator)+">. ");
		try {
			return selenium.getValue(locator);	
		} catch (Exception e) {
			if (isRequeired) { e.printStackTrace();
				throw new ElementNotFoundException(elementName, "xpath", locator);
			}else{
				logger.error(e.getMessage());
				e.printStackTrace();
			}
		}
		return selenium.getValue(locator);
	}
	
	

		
	public Boolean isElementPresent(String path){		
		Browser.implicityWait(1);
		logger.debug("Check is element " + path + " present on the page");
		Boolean is;
		is = findElementsByXpathWithOutWait(path).size() != 0;
		if(is) is = isDisplayed(path);
		logger.debug("isElementPresent returns " + is);
		Browser.implicityWait(SeleneseTestCase.defaultTimeOut);
		return is;				
	}
	
	public Boolean isNotElementPresent(String path){		
		Browser.implicityWait(1);
		logger.debug("Check is element " + path + " present on the page");
		Boolean is;
		is = findElementsByXpathWithOutWait(path).size() == 0;
		logger.debug("isElementPresent returns " + is);
		Browser.implicityWait(SeleneseTestCase.defaultTimeOut);
		return is;				
	}
	
	protected Boolean isVisible(String locator){		
		Browser.implicityWait(1);
		logger.debug("Check is element " + locator + " visible on the page");
		return findElementByXpath(locator).isDisplayed();				
	}
	
	protected void setActiveFrameByXpath(String locator){		
		logger.debug("Try to set focus into frame " + locator);
		WebElement elem = findElementByXpath(locator);
		logger.debug("Focus was set into the frame " + locator);
		switchToFrame(elem);				
	}
	
	public void setFocus(){		
		logger.debug("Try to set focus into element " + path);		
		WebElement element = findElementByXpath(path);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].focus();", 
                element);			
	}
	
	protected void switchToDefaultContent(){		
		logger.debug("Try to set focus into parent page");
		driver.switchTo().defaultContent();				
	}
	
	protected WebElement findElementByXpath(String locator){		
		locator = locator.replace("[0]", "");
		logger.debug("Try to find element " + locator);
		List<WebElement> elem = findElementsByXpath(locator);
		if (elem.size() == 0) {
			throw new ElementNotFoundException("Element was not found", "xpath", locator);
		}
		if (elem.size() == 1) {
			return driver.findElement(By.xpath(locator));
		}else{
			for (int i = 0; i < elem.size(); i++) {
				if (elem.get(i).isDisplayed()) {
					return elem.get(i);
				}
			}
		}
		return driver.findElement(By.xpath(locator));
	}
	
	protected WebElement findElementByid(String id){		
		//implicityWait(10);
		logger.debug("Try to find element " + id);
		return driver.findElement(By.id(id));
						
	}
	
	protected WebElement findElementByCss(String css){		
		//implicityWait(10);
		logger.debug("Try to find element " + css);
		return driver.findElement(By.cssSelector(css));
						
	}
	
	protected List<WebElement> findElementsByXpath(String xpath){		
		setImplicity(30);
		List<WebElement> l = findElementsByXpathWithOutWait(xpath);
		setImplicity(defaultTimeOut);
		return l;
						
	}
	
	protected List<WebElement> findElementsByXpathWithOutWait(String xpath){		
		xpath.replace("[0]", "");
		logger.debug("Try to find elements " + xpath);
		List<WebElement> elem = driver.findElements(By.xpath(xpath));
		logger.debug(elem.size() + " elements were found");

		return elem;
						
	}
	
	protected void waitForPopUp(String locator){		
		selenium.waitForPopUp(locator, "60000");		
	}
	
	protected void deselectPopUp(){		
		selenium.deselectPopUp();		
	}
	
	protected void selectWindow(String locator){		
		selenium.selectWindow(locator);				
	}
	
	
	protected void windowFocus(String locator){		
		selenium.windowFocus();				
	}
	
	
	protected void switchToFrame(Integer index){		
		logger.debug("Try to switch focus to frame with index = " + index);
		driver.switchTo().frame(index);				
	}
	
	protected void switchToFrame(String id){		
		logger.debug("Try to switch focus to frame with Id =  " + id);
		driver.switchTo().frame(id);				
	}
	
	protected void switchToFrame(WebElement element){		
		logger.debug("Try to switch focus to frame");
		driver.switchTo().frame(element);				
	}
	
	protected String getCSSValue(String xpath, String CSSParametr){		
		logger.debug("Try to find CSS " + CSSParametr + " for element " + xpath);
		return driver.findElement(By.xpath(xpath)).getCssValue(CSSParametr);				
	}

	protected String getAttributeValue(String xpath, String attrName){		
		logger.debug("Try to find Attribute " + attrName + " for element " + xpath);
		return driver.findElement(By.xpath(xpath)).getAttribute(attrName);				
	}
	
	protected String findNOTBrokenPartOfXpat(String xpath) {
		String s[] = CommonUtils.getArrayFromStringBySymbol(xpath.replace("//", ""), "/");
		String path = "//" + s[0];
		for (int i = 1; i <= s.length; i++) {
			if (driver.findElements(By.xpath(path)).size() == 0) {
				logger.error("Min broken path" + xpath);
				return xpath;
			}			
			if (i < s.length) {
				path = path + "/" + s[i];
			}
		}
		return "Correct part of path " + path;
	}
	
	
	/*utils-------------------------------------------------------------*/

	
	
	/**
	 * 
	 * @param aIdLocator
	 * @param aTime in mlSeconds
	 * @return
	 */
	protected boolean waitObject (String aIdLocator, int aTime){
		try {
			for (int i = 0; i < aTime/1000; i++) {
				Boolean isPrsent = isElementPresent(aIdLocator);
				if (isPrsent) {
					Boolean isVisible = false;
					try {
						isVisible = isVisible(aIdLocator);
					} catch (Exception e) {
						return true;
					}					
					if (isVisible) {
						return true;
					}else{
						logger.debug("Element " + aIdLocator + " is present but is not visible");
					}
				}else{
					
						logger.debug("Waiting for element " + aIdLocator + ". " + 
								(aTime/1000 - i) + " seconds left.");			
					
					Thread.sleep(1000);
					if (i ==  aTime/1000 -1){
						if (!logger.getLevel().equals(Level.INFO)) {
							SeleneseTestCase.makeScreenshot(CommonUtils.getUnicName());
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("Exception: Element " + aIdLocator + " not found!");
			return false;
		}
		return false;
   }
	
	protected void waitObjectAndFail (String aIdLocator, int aTime) throws Exception{
		if (!waitObject(aIdLocator, aTime)) {
			throw new ElementNotFoundException(aIdLocator, "xpath", "");
		}
			
   }
	
	protected boolean waitOption (String aIdLocator, String option, int aTime) throws InterruptedException{
		Browser.implicityWait(0);
		if (waitObject(aIdLocator, aTime)) {
			for (int i = 0; i < aTime/1000; i++) {
				String[] options = {};
				try {
					options = selenium.getSelectOptions(aIdLocator);
				} catch (Exception e) {
					// TODO: handle exception
				} 
				if (options.length > 0) {
					Browser.implicityWait(SeleneseTestCase.defaultTimeOut);
					return true;
				}else{
					
						logger.debug("Waiting for options in the " + aIdLocator + ". " + 
								(aTime/1000 - i) + " seconds left.");			
					
					Thread.sleep(1000);
					if (i ==  aTime/1000 -1){
						if (logger.getLevel().equals(Level.INFO)) {
							SeleneseTestCase.makeScreenshot(CommonUtils.getUnicName());
						}
					}
				}
			}
		}
		Browser.implicityWait(SeleneseTestCase.defaultTimeOut);
		return false;
   }
	
	protected boolean waitTextInObject (String aIdLocator, String aText, int aTime)
	throws Exception {
		
		for (int i = 0; i < aTime/1000; i++) {
			if (isElementPresent(aIdLocator) && selenium.isTextPresent(aText)) {
				if (getText(aIdLocator).indexOf(aText)!=-1){
					return true;
				}else if(i==aTime/1000-1){
					throw new Exception("Exception: Wrong text. Text '" 
							+ aText + "' is not found.");
				}else{
					sleep(1000);
				}
			}else{
				sleep(1000);
			}
		
		}	
		return false;
	}
	
	public void changePath(String old, String newPath) {
		this.path = this.path.replace(old, newPath);
	}

	
	protected Actions getActionBuilder() {
		return new Actions(driver);
	}
	
	protected boolean isEnabled(String path) {
		return findElementByXpath(path).isEnabled();
	}

	protected boolean isDisplayed(String path) {
		return findElementByXpath(path).isDisplayed();
	}
	
	protected boolean isNotDisplayed(String path) {
		return findElementsByXpath(path).size() == 0;
	}
	
	public Integer isValueExists(String value) {
		
		return findElementsByXpath(path + "/descendant::*[contains(text(), '" + value + "')]").size();		
	}

}
