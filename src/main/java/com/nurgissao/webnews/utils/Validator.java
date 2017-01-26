package com.nurgissao.webnews.utils;

import java.util.HashMap;
import java.util.Map;

public class Validator {
    private static final String FILE_NAME = "validator.properties";
    private static final String SIGN_UP_SPECIFIC_KEY = "signUp";
    private static PropertiesLoader properties;

    public Validator() {
        properties = new PropertiesLoader(FILE_NAME);
    }

    public Map<String, String> validateSignupForm(Map<String, String> formValue) {
        Map<String, String> violations = new HashMap<>();

        String firstName = formValue.get("firstName");
        String lastName = formValue.get("lastName");
        String email = formValue.get("email");
        String password = formValue.get("password");

        validateFirstName(firstName, violations);
        validateLastName(lastName, violations);
        validateEmail(email, violations);
        validatePassword(password, violations);

        return violations;
    }

    private Map<String, String> validateFirstName(String firstName, Map<String, String> violations) {
        String formName = "firstName";
        setPropertiesKey(formName);
        int minLength = getMinLength();
        int maxLength = getMaxLength();

        String minLengthResult = checkMinLength(formName, firstName, minLength);
        String maxLengthResult = checkMaxLength(formName, firstName, maxLength);

        if (minLengthResult != null) {
            violations.put(formName, minLengthResult);
        } else {
            violations.put(formName, maxLengthResult);
        }

        return violations;
    }

    private Map<String, String> validateLastName(String lastName, Map<String, String> violations) {
        String formName = "lastName";
        setPropertiesKey(formName);
        int minLength = getMinLength();
        int maxLength = getMaxLength();

        String minLengthResult = checkMinLength(formName, lastName, minLength);
        String maxLengthResult = checkMaxLength(formName, lastName, maxLength);

        if (minLengthResult != null) {
            violations.put(formName, minLengthResult);
        } else {
            violations.put(formName, maxLengthResult);
        }

        return violations;
    }

    private Map<String, String> validateEmail(String email, Map<String, String> violations) {
        String formName = "email";
        setPropertiesKey(formName);
        int minLength = getMinLength();
        int maxLength = getMaxLength();

        String minLengthResult = checkMinLength(formName, email, minLength);
        String maxLengthResult = checkMaxLength(formName, email, maxLength);

        if (minLengthResult != null) {
            violations.put(formName, minLengthResult);
        } else {
            violations.put(formName, maxLengthResult);
        }

        return violations;
    }

    private Map<String, String> validatePassword(String password, Map<String, String> violations) {
        String formName = "password";
        setPropertiesKey(formName);
        int minLength = getMinLength();
        int maxLength = getMaxLength();

        String minLengthResult = checkMinLength(formName, password, minLength);
        String maxLengthResult = checkMaxLength(formName, password, maxLength);

        if (minLengthResult != null) {
            violations.put(formName, minLengthResult);
        } else {
            violations.put(formName, maxLengthResult);
        }

        return violations;
    }

    private String checkMinLength(String formName, String value, int minLength) {
        String result = null;
        if (value.length() < minLength) {
            result = formName + "  is too short.";
        }
        return result;
    }

    private String checkMaxLength(String formName, String value, int maxLength) {
        String result = null;
        if (value.length() > maxLength) {
            result = formName + " is too long.";
        }
        return result;
    }

    private void setPropertiesKey(String key) {
        String fullSpecificKey = SIGN_UP_SPECIFIC_KEY + "." + key;
        properties.setSpecificKey(fullSpecificKey);
    }

    private int getMinLength() {
        return Integer.parseInt(properties.getValue("minLength"));
    }

    private int getMaxLength() {
        return Integer.parseInt(properties.getValue("maxLength"));
    }

}
