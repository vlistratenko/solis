package com.salsalabs.ignite.automation.apiautomation.models.activity.getactivitybyid.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Payload {

    @SerializedName("activityIds")
    @Expose
    private List<String> activityIds = null;

    public List<String> getActivityIds() {
        return activityIds;
    }

    public void setActivityIds(List<String> activityIds) {
        this.activityIds = activityIds;
    }

}