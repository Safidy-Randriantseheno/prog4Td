package com.prog4.progtd.utils;

import java.time.LocalDate;

public class YearUtil {
    public static String getLastDigitofTheYear(){
        String currentYear = String.valueOf(LocalDate.now().getYear()).substring(2);
        return currentYear;
    }
}
