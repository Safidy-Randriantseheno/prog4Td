package com.prog4.progtd.utils;

public class MatriculeGenerator {
    public static String generateMatricule(int lastEmployee){
        return "EMP"+ YearUtil.getLastDigitofTheYear() + lastEmployee;
    }
}
