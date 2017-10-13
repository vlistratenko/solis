package com.salsalabs.ignite.automation.elements.VE2Elements;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.ElementImpl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class VEElements extends ElementImpl {

    public VEElements(String elementPath, String name) {
        super(elementPath, name);
    }

    public void drop(){}
    public void edit(String fieldName){
        Button fieldEditIcon = new ButtonImpl("//label[.='" + fieldName + "']/ancestor::*[@class='content-render-wrapper']//*[@title='Edit']", fieldName + " edit icon");
        fieldEditIcon.clickJS();
    }

    public void dragAndDropElementOnLayoutWithFormElement() {
        Actions action = new Actions(getDriver());
        Button elementsVe = new ButtonImpl("//button[contains(@title,  'Content Elements')]", "Elemenets Tab");
        elementsVe.scrollIntoView();
        elementsVe.click();
        getLogger().info("Elements menu was clicked");
        WebElement source = findElementByXpath(getElementPath());
        WebElement target = findElementByXpath("//div[@class='render-container-wrapper']");
        action.clickAndHold(source).moveToElement(target).release().perform();
        getLogger().info(getElementName() + " element was dropped into the top of the Visual Editor");
    }

    //Use for Rows
    public void dragAndDropOnEmptyLayout() {
        Actions action = new Actions(getDriver());
       Button elementsVe = new ButtonImpl("//button[contains(@title,  'Sectional Elements')]", "Rows Tab");
        elementsVe.scrollIntoView();
        elementsVe.click();
        getLogger().info("Rows menu was clicked");
        WebElement source = findElementByXpath(getElementPath());
        WebElement target = findElementByXpath("//*[@class='editorContentWrapper ui-droppable']");
        action.clickAndHold(source).moveToElement(target).release().perform();
        getLogger().info(getElementName() + " was dropped into the layout");
    }

    //Use for Form
    public void dragAndDropOnRow() {
        Actions action = new Actions(getDriver());
        Button elementsVe = new ButtonImpl("//button[contains(@title,  'Content Elements')]", "Elements Tab");
        elementsVe.scrollIntoView();
        elementsVe.click();
        getLogger().info("Elements menu was clicked");
        WebElement source = findElementByXpath(getElementPath());
        WebElement target = findElementByXpath("//*[@class='render-content ui-droppable']");
        action.clickAndHold(source).moveToElement(target).release().perform();
        getLogger().info(getElementName() + " was dropped into the layout");
    }


}
