package com.salsalabs.ignite.automation.apiautomation.models.supporter.requests;

public class GetSupporterRequest extends CommonRequest {

    private Payload payload;

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

}