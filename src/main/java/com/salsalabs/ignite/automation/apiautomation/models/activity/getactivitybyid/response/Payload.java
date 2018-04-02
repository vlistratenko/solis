package com.salsalabs.ignite.automation.apiautomation.models.activity.getactivitybyid.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Payload {

    @SerializedName("activities")
    @Expose
    private List<Activity> activities = null;
    @SerializedName("count")
    @Expose
    private Integer count;

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}