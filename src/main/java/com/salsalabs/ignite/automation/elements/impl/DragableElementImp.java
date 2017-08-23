package com.salsalabs.ignite.automation.elements.impl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.DragableElement;

public class DragableElementImp extends  ElementImpl  implements DragableElement{

	public DragableElementImp(String elementPath, String name) {
		super(elementPath, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void dragAndDropElement() {
		Actions action = new Actions(driver);
		Button elementsVe = new ButtonImpl("//button[contains(@title,  'Content Elements')]", "Elemenets Tab");
		elementsVe.scrollIntoView();
		elementsVe.click();
		logger.info("Elements menu was clicked");
		WebElement source=  findElementByXpath(path);
		WebElement target = driver.findElement(By.xpath("//div[@class='content-render-wrapper']"));
		action.clickAndHold(source).moveToElement(target).release().perform();	
		logger.info( elementName + " Button element is Dragged into the  top of the Visual Editor ");
	
		
		
	}
	
	

}
