package objects;

import org.openqa.selenium.Keys;

public class ContentEditableTextBox extends TextBox{

	String iframeXpath;
	
	public ContentEditableTextBox(String iframePath,String elementPath, String elementName,
			Boolean isReq) {
		super(elementPath, elementName, isReq);
		iframeXpath = iframePath;
		// TODO Auto-generated constructor stub
	}
	
	public void type(String value) {
		switchToFrame(findElementByXpath(iframeXpath));
		sendKeys(path, Keys.TAB);
		type(path, value);
		switchToDefaultContent();
		
	}

}
