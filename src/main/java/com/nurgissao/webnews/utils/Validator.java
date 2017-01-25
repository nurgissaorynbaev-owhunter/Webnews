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
        System.out.println("4");

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
        properties.setSpecificKey(SIGN_UP_SPECIFIC_KEY + "." + formName);
        int minLength = Integer.parseInt(properties.getValue("minLength"));
        int maxLength = Integer.parseInt(properties.getValue("maxLength"));

        Map<String, String> minMap = checkMinLength(formName, firstName, minLength);
        Map<String, String> maxMap = checkMaxLength(formName, firstName, maxLength);

        if (!minMap.isEmpty()) {
            violations = minMap;
            System.out.println("6");
        } else {
            violations = maxMap;
        }

        return violations;
    }

    private Map<String, String> validateLastName(String lastName, Map<String, String> violations) {
        String formName = "lastName";
//        properties.setSpecificKey(SIGN_UP_SPECIFIC_KEY + "." + formName);
//        int minLength = Integer.parseInt(properties.getValue("minLength"));
//        int maxLength = Integer.parseInt(properties.getValue("maxLength"));
        setPropertiesKey(formName);
        int minLength = getPropertiesMinLength();
        int maxLength = getPropertiesMaxValue();

        Map<String, String> minMap = checkMinLength(formName, lastName, minLength);
        Map<String, String> maxMap = checkMaxLength(formName, lastName, maxLength);

        if (!minMap.isEmpty()) {
            violations = minMap;
        } else {
            violations = maxMap;
        }

        return violations;
    }

    private Map<String, String> validateEmail(String email, Map<String, String> violations) {
        String formName = "email";
        properties.setSpecificKey(SIGN_UP_SPECIFIC_KEY + "." + formName);
        int minLength = Integer.parseInt(properties.getValue("minLength"));
        int maxLength = Integer.parseInt(properties.getValue("maxLength"));

        Map<String, String> minMap = checkMinLength(formName, email, minLength);
        Map<String, String> maxMap = checkMaxLength(formName, email, maxLength);

        if (!minMap.isEmpty()) {
            violations = minMap;
        } else {
            violations = maxMap;
        }

        return violations;
    }

    private Map<String, String> validatePassword(String password, Map<String, String> violations) {
        String formName = "password";
        properties.setSpecificKey(SIGN_UP_SPECIFIC_KEY + "." + formName);
        int minLength = Integer.parseInt(properties.getValue("minLength"));
        int maxLength = Integer.parseInt(properties.getValue("maxLength"));

        Map<String, String> minMap = checkMinLength(formName, password, minLength);
        Map<String, String> maxMap = checkMaxLength(formName, password, maxLength);

        if (!minMap.isEmpty()) {
            violations = minMap;
        } else {
            violations = maxMap;
        }

        return violations;
    }

    private Map<String, String> checkMinLength(String formName, String value, int minLength) {
        Map<String, String> result = new HashMap<>();
        if (value.length() < minLength) {
            String errorValue = formName + "  is too short.";
            result.put(formName, errorValue);
        }
        return result;
    }

    private Map<String, String> checkMaxLength(String formName, String value, int maxLength) {
        Map<String, String> result = new HashMap<>();
        if (value.length() > maxLength) {
            String errorValue = formName + " is too long.";
            result.put(formName, errorValue);
        }
        return result;
    }

    private void setPropertiesKey(String key) {
        String fullSpecificKey = SIGN_UP_SPECIFIC_KEY + "." + key;
        properties.setSpecificKey(fullSpecificKey);
    }

    private int getPropertiesMinLength() {
        return Integer.parseInt(properties.getValue("minLength"));
    }

    private int getPropertiesMaxValue() {
        return Integer.parseInt(properties.getValue("maxLength"));
    }

}
