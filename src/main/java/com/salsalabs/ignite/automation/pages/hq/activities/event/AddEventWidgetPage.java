package com.salsalabs.ignite.automation.pages.hq.activities.event;

import com.salsalabs.ignite.automation.pages.hq.activities.*;

public class AddEventWidgetPage extends AddDonationWidgetPage {

    public void setWidgetName(String widgetName) {
        this.widgetName = widgetName;
    }

    public EventWidget openEventWidget(String widgetName) {
        this.widgetName = widgetName;
        return (EventWidget) openDonationWidget();
    }

    public EventWidget openEventWidget() {
        return openWidget(EventWidget.class);
    }
}
