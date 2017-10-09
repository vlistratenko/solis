package com.salsalabs.ignite.automation.apiautomation.utils;

import java.math.BigInteger;
import java.security.SecureRandom;

public class EmailGenerator {

    public static String getRandomEmail() {
        return new BigInteger(130, new SecureRandom()).toString(32) + "@testauto.ignite.net";
    }
}
