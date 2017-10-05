package com.salsalabs.ignite.automation.apiautomation.models.supporter.requests;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Payload {

    private List<String> identifiers = null;
    private String identifierType;

    public List<String> getIdentifiers() {
        return identifiers;
    }

    public void setIdentifiers(List<String> identifiers) {
        this.identifiers = identifiers;
    }

    public String getIdentifierType() {
        return identifierType;
    }

    public void setIdentifierType(String identifierType) {
        this.identifierType = identifierType;
    }
}
