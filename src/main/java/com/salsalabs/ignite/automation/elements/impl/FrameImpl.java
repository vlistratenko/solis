package com.salsalabs.ignite.automation.elements.impl;

import com.salsalabs.ignite.automation.elements.Frame;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import java.util.concurrent.TimeUnit;

public class FrameImpl extends ElementImpl implements Frame {

    public FrameImpl(String elementPath, String name) {
        super(elementPath, name);
    }

    public void switchToFrame() {
        logger.debug("Trying to switch to " + this.elementName);
        getDriver().switchTo().frame(this.path);
    }

    public void switchToDefaultContent() {
        logger.debug("Trying to switch to default content section");
        getDriver().switchTo().defaultContent();
    }

    public void switchToFrame(int index) {
        logger.debug("Trying to switch to frame with index = " + index);
        driver.switchTo().frame(index);
    }

    public void swithToFrameWithFluentWait(int waitingTime){
            long pollingInterval = 500;
            Wait<WebDriver> wait = new FluentWait<>(driver)
                    .withTimeout(waitingTime, TimeUnit.SECONDS)
                    .pollingEvery(pollingInterval, TimeUnit.MILLISECONDS)
                    .ignoring(NoSuchElementException.class)
                    .withMessage("Error occured in " + Thread.currentThread().getStackTrace()[2].getMethodName() + " method." +'\n' + this.elementName +
                            " was not found after " + waitingTime + " seconds of waiting with " + pollingInterval + " milliseconds polling interval");
            logger.info("Waiting for " + waitingTime + " seconds with " + pollingInterval + " milliseconds polling interval until " + this.elementName +
                    " is present and clickable");
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(this.path));
        }
    }


