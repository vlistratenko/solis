
package com.salsalabs.ignite.automation.apiautomation.models.activity.getactivitybyid.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.salsalabs.ignite.automation.apiautomation.models.supporter.requests.CommonRequest;

public class GetActivityByIdRequest extends CommonRequest {

    @SerializedName("payload")
    @Expose
    private Payload payload;

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

}