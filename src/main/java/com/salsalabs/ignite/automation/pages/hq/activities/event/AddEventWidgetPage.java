package com.salsalabs.ignite.automation.pages.hq.activities.event;

import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.PropertyName;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.pages.hq.activities.*;

public class AddEventWidgetPage extends AddDonationWidgetPage {

    public void setWidgetName(String widgetName) {
        this.widgetName = widgetName;
    }

    public EventWidget openEventWidget(String widgetName) {
        this.widgetName = widgetName;
        return openEventWidget();
    }

    public EventWidget openEventWidget() {
        return openWidget(EventWidget.class);
    }

    protected EventWidget openEventFormRegisterPage() {
        widgetLink = new ButtonImpl("//a[contains(text(), '" + widgetName.toLowerCase() + "?page=register')]", "Event form link to Register page");
        widgetLink.fluentWaitForElementPresenceIgnoringExceptions();
        CommonUtils.setProperty(linkProperty, widgetLink.getAttribute("href"));
        currentWindowHandle = getWindowHandle();
        this.openInNewWindow(CommonUtils.getProperty(linkProperty));
        sleep(7);
        CommonUtils.setProperty(PropertyName.CURRENT_WINDOW_HANDLE, currentWindowHandle);
        return new EventWidget();
    }
}
