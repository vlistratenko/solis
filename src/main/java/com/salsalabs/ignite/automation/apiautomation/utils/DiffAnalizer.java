package com.salsalabs.ignite.automation.apiautomation.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DiffAnalizer {

    public static String getDiff(String message) {
        StringBuilder comparison = new StringBuilder();
        List<String> expectedResult = removeWhiteSpaces(Arrays.asList((message.substring(message.indexOf("{") + 1, message.indexOf(">") - 1)).split(",")));
        List<String> actualResult = removeWhiteSpaces(Arrays.asList((message.substring(message.indexOf("{", message.indexOf(">")) + 1,
                message.indexOf(">", message.indexOf("found")) - 1).split(", "))));
        for (int i = 0; i < expectedResult.size(); i++) {
            if (!expectedResult.get(i).equals(actualResult.get(i))) {
                comparison.append("Expected: " + expectedResult.get(i) + " but found: " + actualResult.get(i) + "\n");
            }
        }
        return comparison.toString();
    }

    private static List<String> removeWhiteSpaces(List<String> list) {
        List<String> result = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++) {
            result.add(list.get(i).replaceAll("\\s+",""));
        }
        return result;
    }

}
