package com.salsalabs.ignite.automation.elements;

import org.openqa.selenium.By;

public interface Frame {

    void switchToFrame();

    void switchToDefaultContent();

    void switchToFrame(int index);

    void swithToFrameWithFluentWait(int waitingTime);
}
