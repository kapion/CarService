package ru.kapion.carservice.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DicHelper {

    private static Map<String, String> engineTypes;
    private static ArrayList<Integer> yearBuildOut;
    private static ArrayList<String> capacity;

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

    public static ArrayList<Integer> getYearBuildOut(int min, int max){
        if (yearBuildOut == null) {
            yearBuildOut = new ArrayList<Integer>();
        }

        if (!yearBuildOut.contains(min) || !yearBuildOut.contains(max)) {
            yearBuildOut.clear();
            for (int i = min; i <= max; i++) {
                yearBuildOut.add(i);
            }
        }

        return yearBuildOut;
    }

    public static ArrayList<String> getCapacity(double min, double max){
        if (capacity == null) {
            capacity = new ArrayList<>();
        }

        if (!capacity.contains(min) || !capacity.contains(max)) {
            capacity.clear();
            for (double i = min; i <= max; i=i+0.1) {
                capacity.add(String.format("%1.1f",i));
            }
        }

        return capacity;
    }
}
