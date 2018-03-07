package ru.kapion.carservice.utils;

import java.util.HashMap;
import java.util.Map;

public class DicHelper {

    private static Map<String, String> engineTypes;

    public static Map<String, String> getEngineTypes() {
         if (engineTypes==null) {
             engineTypes = new HashMap<>();
             engineTypes.put("OIL", "Бензиновый");
             engineTypes.put("DIESEL", "Дизельный");
             engineTypes.put("HYBRID", "Гибридный");
             engineTypes.put("ELECTRO", "Электрический");
         }
         return engineTypes;
     }

}
