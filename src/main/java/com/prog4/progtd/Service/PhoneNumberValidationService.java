package com.prog4.progtd.Service;

import org.springframework.stereotype.Service;

@Service
public class PhoneNumberValidationService {

    //valider le length && code Paye
    private static final int DEFAULT_NUMBER_LENGTH = 10;
    private static final String[] VALID_COUNTRY_CODES = { "+261", "+33" };
    private static final int[] VALID_NUMBER_LENGTHS = { DEFAULT_NUMBER_LENGTH, DEFAULT_NUMBER_LENGTH };
    public void validatePhoneNumber(String phoneNumber) {
        String countryCode = extractCountryCode(phoneNumber);
        int phoneNumberLength = phoneNumber.length();

        for (int i = 0; i < VALID_COUNTRY_CODES.length; i++) {
            if (countryCode.equals(VALID_COUNTRY_CODES[i]) && phoneNumberLength == VALID_NUMBER_LENGTHS[i]) {
                return; // Phone number is valid, no need to throw an exception
            }
        }
        throw new InvalidPhoneNumberException("Invalid phone number format");
    }
    public boolean isPhoneNumberValid(String phoneNumber) {
        String countryCode = extractCountryCode(phoneNumber);
        int phoneNumberLength = phoneNumber.length();

        for (int i = 0; i < VALID_COUNTRY_CODES.length; i++) {
            if (countryCode.equals(VALID_COUNTRY_CODES[i]) && phoneNumberLength == VALID_NUMBER_LENGTHS[i]) {
                return true;
            }
        }
        return false;
    }

    public String extractCountryCode(String phoneNumber) {
        if (phoneNumber.startsWith("+")) {
            // If the phone number starts with "+", we assume that the country code is included
            int endOfCountryCode = phoneNumber.indexOf(' ', 1);
            if (endOfCountryCode > 0) {
                return phoneNumber.substring(0, endOfCountryCode);
            } else {
                // If there is no space after the "+", the country code is the whole string
                return phoneNumber;
            }
        } else {
            // If the phone number doesn't start with "+", there is no country code
            return "please select conuntry";
        }
    }
}
