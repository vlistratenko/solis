package com.salsalabs.ignite.automation.elements.VE2Elements;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.Label;
import com.salsalabs.ignite.automation.elements.Panel;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.ElementImpl;
import com.salsalabs.ignite.automation.elements.impl.LabelImpl;
import com.salsalabs.ignite.automation.elements.impl.PanelImpl;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

public class VEElements extends ElementImpl {

	Button elementsVe = new ButtonImpl("//button[contains(@title,  'Content Elements')]", "Elemenets Tab");
	Panel rowTarget = new PanelImpl(OneColumnRow.pathAfterDrop, "Row to drop elements");
    Panel emptyTarget = new PanelImpl("//*[@class='editorContentWrapper ui-droppable']", "Empty space to drop elements");
	Button saveContent = new ButtonImpl("//a[contains(text(),'Save Content')]", "Save Content");
	
	public VEElements(String elementPath, String name) {
        super(elementPath, name);
    }
    
    public VEElements(String elementPath, String targetPanelPath, String name) {
        super(elementPath, name);
    }

    public void deleteFormField(String fieldName){
        Label fieldLabel = new LabelImpl("//*[@title='Delete']/../../..//label[.='" + fieldName + "']","Form field label");
        Button elementDeleteButton = new ButtonImpl("//*[@title='Delete']/../../..//label[.='" + fieldName + "']/../../..//*[@title='Delete']","Form field element Delete button");
        if (!fieldLabel.isNotExists()) {
            elementDeleteButton.click();
        } else {
            List<Button> deleteButtons = new ArrayList<>();
            Button deleteButton = new ButtonImpl("//*[@title='Delete']/../../../*[.='No field configured.']/preceding-sibling::*//*[@class='right']//*[@title='Delete']", "Delete field buttons");
            deleteButtons.add(deleteButton);
            if (!deleteButtons.isEmpty()) {
                for (Button button : deleteButtons) {
                    button.clickJS();
                    getDriver().switchTo().alert().accept();
                }
            }
        }
    }

    public void drop(){}
    public void edit(String fieldName){
        Button fieldEditIcon = new ButtonImpl("//label[.='" + fieldName + "']/ancestor::*[@class='content-render-wrapper']//*[@title='Edit']", fieldName + " edit icon");
        fieldEditIcon.waitElement(10);
        fieldEditIcon.clickJS();
    }
    
    public void openEditElementPopUp(String elementLabel){
    	ButtonImpl fieldEditIcon = new ButtonImpl("//span[.='" + elementLabel + "']/ancestor::*[@class='content-render-wrapper']//*[@title='Edit Text']", elementLabel + " edit icon");
        fieldEditIcon.moveToElement();
        fieldEditIcon.waitElement(10);
        fieldEditIcon.clickJS();
    }
    
    public void save(){
    	saveContent.click();
    }

    //Use for Form VE element
    public void dragAndDropElementOnLayoutWithFormElement() {
        
    	dragAndDropElementOnElement(rowTarget);
    	
    	//Actions action = new Actions(getDriver());
        //Button elementsVe = new ButtonImpl("//button[contains(@title,  'Content Elements')]", "Elemenets Tab");
        /*elementsVe.scrollIntoView();
        elementsVe.click();
        getLogger().info("Elements menu was clicked");*/
    	//openContentElementsPopup();
        //WebElement source =  findElementByXpath(this.getElementPath());
        //WebElement target;
        //try {
    	//dragAndDrop(rowTarget);
        	/*target = findElementByXpath("(//div[@class='render-container-wrapper'])[2]");
            new ButtonImpl("(//div[@class='render-container-wrapper'])[2]", "Draggable area").scrollIntoView();
            action.clickAndHold(source).moveToElement(target).release().perform();*/
       /* } catch (StaleElementReferenceException | ElementNotFoundException e) {
            new ButtonImpl("(//div[@class='content-render-wrapper'])[2]", "Draggable area").scrollIntoView();
            target = findElementByXpath("(//div[@class='content-render-wrapper'])[2]");
            action.clickAndHold(source).moveToElement(target).release().perform();
        }*/
        //getLogger().info(getElementName() + " element was dropped into the top of the Visual Editor");
    }
    
    public void dragAndDropElementOnElement(Panel targetPanel) {
    	openContentElementsPopup();
    	dragAndDrop(targetPanel);
        getLogger().info(getElementName() + " element was dropped into the " + targetPanel.getName());
    }

    //Use for Rows
    public void dragAndDropOnEmptyLayout() {
        /*//Actions action = new Actions(getDriver());
        Button elementsVe = new ButtonImpl("//button[contains(@title,  'Sectional Elements')]", "Rows Tab");
        elementsVe.scrollIntoView();
        elementsVe.click();
        getLogger().info("Rows menu was clicked");*/
    	openContentRowsPopup();
        dragAndDrop(emptyTarget);
        /*WebElement source = findElementByXpath(getElementPath());
        WebElement target = findElementByXpath("//*[@class='editorContentWrapper ui-droppable']");
        action.clickAndHold(source).moveToElement(target).release().perform();*/
        getLogger().info(getElementName() + " was dropped into the layout");
    }

    //Use for Form
    public void dragAndDropOnRow() {
        //Actions action = new Actions(getDriver());
        openContentElementsPopup();
        getLogger().info("Elements menu was clicked");
        /*WebElement source = findElementByXpath(getElementPath());
        WebElement target = findElementByXpath("//*[@class='render-content ui-droppable']");
        action.clickAndHold(source).moveToElement(target).release().perform();*/
        dragAndDrop(rowTarget);
        getLogger().info(getElementName() + " was dropped into the layout");
    }
    
    private void openContentElementsPopup() {
    	elementsVe.waitElement();
    	elementsVe.scrollIntoView();
        elementsVe.click();
        getLogger().info("Elements menu was clicked");
	}
    
    private void openContentRowsPopup() {
    	 Button elementsVe = new ButtonImpl("//button[contains(@title,  'Sectional Elements')]", "Rows Tab");
         elementsVe.scrollIntoView();
         elementsVe.click();
         getLogger().info("Rows menu was clicked");
	}

    //Use for Register Button VE element
    public void dragAndDropElementOnLayoutWithRowElement() {
        Actions action = new Actions(getDriver());
        Button elementsVe = new ButtonImpl("//button[contains(@title,  'Content Elements')]", "Elemenets Tab");
        elementsVe.fluentWaitForElementPresenceIgnoringExceptions(5);
        elementsVe.scrollIntoView();
        elementsVe.click();
        getLogger().info("Elements menu was clicked");
        WebElement source =  findElementByXpath(this.getElementPath());
        WebElement target;
        try {
            new ButtonImpl("//div[@class='render-container-wrapper']", "Draggable area").scrollIntoView();
            target = findElementByXpath("//div[@class='render-container-wrapper']");
            action.clickAndHold(source).moveToElement(target).release().perform();
        } catch (StaleElementReferenceException | ElementNotFoundException e) {
            new ButtonImpl("//div[@class='content-render-wrapper']", "Draggable area").scrollIntoView();
            target = findElementByXpath("//div[@class='content-render-wrapper']");
            action.clickAndHold(source).moveToElement(target).release().perform();
        }
        getLogger().info(getElementName() + " element was dropped into the top of the Visual Editor");
    }


}
