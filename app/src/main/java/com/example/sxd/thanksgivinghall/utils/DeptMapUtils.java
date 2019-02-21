package com.example.sxd.thanksgivinghall.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DeptMapUtils {
   private static HashMap<String,String> map = new HashMap<String,String>() {
        {
            put("amy","按摩院");
            put("ct","餐厅");
            put("kfs","开发室");
        }
    };
    public static String getFullName(String shortName) {
        return map.get(shortName);
    }

    public static String getShortName(String fullName) {
        Set<Map.Entry<String, String>> entries = map.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            String value = entry.getValue();
            if(fullName == null &&fullName == value
                    ||fullName.equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }
}
