package com.zipcodewilmington.phone;

import com.zipcodewilmington.exceptions.InvalidPhoneNumberFormatException;

import java.util.logging.Level;
import java.util.logging.Logger;

import static com.zipcodewilmington.tools.RandomNumberFactory.createInteger;

/**
 * Created by leon on 5/1/17.
 */
public final class PhoneNumberFactory {
    private static final Logger logger = Logger.getGlobal();

    private PhoneNumberFactory() {
        /** This constructor is private
         *  This class is uninstantiable */
    }

    /**
     * @param phoneNumberCount - number of PhoneNumber objects to instantiate
     * @return array of randomly generated PhoneNumber objects
     */ //TODO - Implement logic
    public static PhoneNumber[] createRandomPhoneNumberArray(int phoneNumberCount) {
        PhoneNumber[] phoneNumbers = new PhoneNumber[phoneNumberCount];
        for(int i = 0; i < phoneNumberCount; i++) {
            phoneNumbers[i] = createRandomPhoneNumber();
        }
        return phoneNumbers;
    }

    /**
     * @return an instance of PhoneNumber with randomly generated phone number value
     */ //TODO - Implement logic
    private static PhoneNumber createRandomPhoneNumber() {
        int areaCode = createInteger(100, 999);
        int centralOfficeCode = createInteger(100, 999);
        int phoneLineCode = createInteger(1000, 9999);
        return createPhoneNumberSafely(areaCode, centralOfficeCode, phoneLineCode);
    }


    /**
     * @param areaCode          - 3 digit code
     * @param centralOfficeCode - 3 digit code
     * @param phoneLineCode     - 4 digit code
     * @return a new phone number object
     */ //TODO - if input is valid, return respective PhoneNumber object, else return null
    public static PhoneNumber createPhoneNumberSafely(int areaCode, int centralOfficeCode, int phoneLineCode) {
        String safePhoneNumber = String.format("(%d)-%d-%d", areaCode, centralOfficeCode, phoneLineCode);
        try {
            return createPhoneNumber(safePhoneNumber);
        } catch (InvalidPhoneNumberFormatException ipnfe) {
            String message = String.format("(%d)-%d-%d is not a valid phone number", areaCode, centralOfficeCode, phoneLineCode);
            logger.log(Level.WARNING, message);
            return null;
        }
    }

    /**
     * @param phoneNumberString - some String corresponding to a phone number whose format is `(###)-###-####`
     * @return a new phone number object
     * @throws InvalidPhoneNumberFormatException - thrown if phoneNumberString does not match acceptable format
     */ // TODO - Add throws statement to method signature
    public static PhoneNumber createPhoneNumber(String phoneNumberString) throws InvalidPhoneNumberFormatException {
        String message = String.format("Attempting to create a new PhoneNumber object with a value of %s",
                phoneNumberString);
        logger.log(Level.WARNING, message);
        return new PhoneNumber(phoneNumberString);
    }
}
