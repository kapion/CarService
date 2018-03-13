package ru.kapion.carservice.utils;

import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Locale;

public class IntegerHelper {
    public static int convertStringToInteger(String in) {
        return Integer.parseInt(StringUtils.isEmpty(in)? "0": in);
    }
    public static int convertStringToInteger(char in){
        String ins = in+"";
        return convertStringToInteger(ins);
    }

    public static BigDecimal toAbsoluteDecimal(String beanAmount) {
        if (beanAmount==null || beanAmount.isEmpty()) {
            throw new NumberFormatException("Нечего парсить");
        }

        int separatorIndex1 = beanAmount.lastIndexOf(".");
        int separatorIndex2 = beanAmount.lastIndexOf(",");

        String separator = null;
        if (separatorIndex1 > 0 && separatorIndex2 > 0) {
            if (separatorIndex1 > separatorIndex2) {
                separator = "\\.";
            } else {
                separator = ",";
            }
        } else if (separatorIndex1 > 0 && separatorIndex2 <= 0) {
            separator = "\\.";
        } else if (separatorIndex2 > 0 && separatorIndex1 <= 0) {
            separator = ",";
        } else {
            separator = "";
        }

        if (!separator.isEmpty()){
            String[] partsAmount = beanAmount.split(separator);
            if (partsAmount != null && partsAmount.length > 0) {
                String newBeanAmount = "";
                for (int i = 0; i < partsAmount.length; i++) {
                    // если последний элемент
                    if (i == partsAmount.length - 1) {
                        newBeanAmount += separator.replace("\\", "");
                    }
                    newBeanAmount += delNoDigit(partsAmount[i]);

                }

                beanAmount = newBeanAmount.replace(".", ",");
            }
        }
        DecimalFormat nf = (DecimalFormat) NumberFormat.getInstance(new Locale("ru", "RU"));
        nf.setParseBigDecimal(true);
        BigDecimal amount = (BigDecimal) nf.parse(beanAmount,new ParsePosition(0));
        System.out.println(amount);
        return amount;
    }

    public static String delNoDigit(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i)))
                sb.append(s.charAt(i));
        }
        return sb.toString();
    }

}
