package com.salsalabs.ignite.automation.pages.hq.supporters;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.google.common.base.Function;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.Element;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.GeneralWebElement;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.concurrent.TimeUnit;

public class CreateEditSupporterQuery extends SupporterQBPage {

    Button firstCriteriaDropDown = new ButtonImpl("//*[contains(text(),'Please select one')]", "First criteria button");
    //Button firstCriteriaValue = new ButtonImpl("//*[contains(text(),'Supporter Fields')]", "First criteria");
    Button deleteRuleButton = new ButtonImpl("//*[@ng-click=\"deleteRule()\"]", "Delete rule button");
    Button showResultsButton = new ButtonImpl("//*[@class=\"button primary\"]", "Show results button");
    Element errorMessage = new GeneralWebElement("//*[@class='f-dropdown error right']", "Error message");
    Element resultTable = new GeneralWebElement("//table", "Results table");

    TextBox fourthCriteria = new TextBoxImpl("//input", "Results table");

    public CreateEditSupporterQuery checkIfTableIsAvailable(){
        try {
            waitFluentlyForElement(resultTable);
        } catch (WebDriverException e) {
            throw new ElementNotFoundException("Result table in not found", "QB result table", "");
        }
        try {
            if (errorMessage.isDisplayed()) throw new AssertionError("Query execution failed");
        } catch (ElementNotFoundException e) {
            logger.info("Error message is not displayed");
        }

        return this;
    }

    public CreateEditSupporterQuery pickFirstCriteria(String criteriaName) {
        clearQuery();
        firstCriteriaDropDown.click();
        new ButtonImpl("//*[contains(text(),'" + criteriaName + "')]", "First criteria selection").click();
        return this;
    }

    public void clearQuery() {
        deleteRuleButton.click();
    }

    private void waitFluentlyForElement(final Element inputElement) {
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
        wait.pollingEvery(50, TimeUnit.MILLISECONDS);
        wait.withTimeout(5, TimeUnit.SECONDS);
        wait.ignoring(NoSuchElementException.class);
        Function<WebDriver, WebElement> function = new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver arg0) {
                logger.info("Checking for the object...");
                WebElement element = driver.findElement(By.xpath(inputElement.getPath()));
                if (element != null) {
                    logger.info("Element was found.");
                }
                return element;
            }
        };
        wait.until(function);
    }

    public CreateEditSupporterQuery tempTest() {
        fourthCriteria.type("e2b4c49b-188b-4c62-805f-7fff8d34f9d5");
        showResultsButton.click();
        return this;
    }
}
