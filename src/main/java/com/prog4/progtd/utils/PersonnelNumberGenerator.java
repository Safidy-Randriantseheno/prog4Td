package com.prog4.progtd.utils;

public class PersonnelNumberGenerator {
    private static int lastPersonnelNumber = 2000;

    public static int generatePersonnelNumber() {
        lastPersonnelNumber++;
        return lastPersonnelNumber;
    }
}
