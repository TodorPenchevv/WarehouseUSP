package app.business.validators;

import app.business.exceptions.CustomException;
import app.business.exceptions.InvalidPhoneNumberException;

import java.util.regex.Pattern;

public class PhoneNumber implements Validator {
    private String number;

    public PhoneNumber(String number) {
        this.number = number;
    }

    public void validate() throws CustomException {
        if(!validFormat()) {
            throw new InvalidPhoneNumberException();
        }
    }

    private boolean validFormat() {
        String digits = "^\\d{10}$";
        String format = "(0)?[8-9][7-9][0-9]{7}";
        return patternMatches(number, digits) && patternMatches(number, format);
    }
    //Source:
    //https://en.wikipedia.org/wiki/Telephone_numbers_in_Bulgaria#Mobile_numbers
    //https://www.javatpoint.com/mobile-number-validation-in-java

    public static boolean patternMatches(String phoneNumber, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(phoneNumber)
                .matches();
    }
}
