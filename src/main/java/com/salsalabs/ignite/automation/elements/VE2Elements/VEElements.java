package com.salsalabs.ignite.automation.elements.VE2Elements;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.google.sitebricks.client.Web;
import com.salsalabs.ignite.automation.common.Browser;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.Element;
import com.salsalabs.ignite.automation.elements.Label;
import com.salsalabs.ignite.automation.elements.Table;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.ElementImpl;
import com.salsalabs.ignite.automation.elements.impl.LabelImpl;
import com.salsalabs.ignite.automation.elements.impl.TableImpl;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

public class VEElements extends ElementImpl {

    public VEElements(String elementPath, String name) {
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
        fieldEditIcon.clickJS();
    }

    public void dragAndDropElementOnLayoutWithFormElement() {
        Actions action = new Actions(getDriver());
        Button elementsVe = new ButtonImpl("//button[contains(@title,  'Content Elements')]", "Elemenets Tab");
        elementsVe.scrollIntoView();
        elementsVe.click();
        getLogger().info("Elements menu was clicked");
        WebElement source =  findElementByXpath(this.getElementPath());
        WebElement target;
        try {
            target = findElementByXpath("(//div[@class='render-container-wrapper'])[2]");
            new ButtonImpl("(//div[@class='render-container-wrapper'])[2]", "TEST").scrollIntoView();
            action.clickAndHold(source).moveToElement(target).release().perform();
             System.out.println("TRY block");
        } catch (StaleElementReferenceException | ElementNotFoundException e) {
            new ButtonImpl("(//div[@class='content-render-wrapper'])[2]", "TEST").scrollIntoView();
            target = findElementByXpath("(//div[@class='content-render-wrapper'])[2]");
            action.clickAndHold(source).moveToElement(target).release().perform();
            System.out.println("CATCH block");
        }
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
