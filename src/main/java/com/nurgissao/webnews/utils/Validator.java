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
        properties.setSpecificKey(SIGN_UP_SPECIFIC_KEY);

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
        properties.setAdditionalKey(".firstName");
        int minLength = Integer.parseInt(properties.getValue("minLength"));
        int maxLength = Integer.parseInt(properties.getValue("maxLength"));
        if (firstName.length() < minLength) {
            violations.put("firstNameError", "FirstName is too short");
        }
        if (firstName.length() > maxLength) {
            violations.put("firstNameError", "FirstName is too long");
        }
        return violations;
    }

    private Map<String, String> validateLastName(String lastName, Map<String, String> violations) {
        properties.setAdditionalKey(".lastName");
        int minLength = Integer.parseInt(properties.getValue("minLength"));
        int maxLength = Integer.parseInt(properties.getValue("maxLength"));
        if (lastName.length() < minLength) {
            violations.put("firstNameError", "FirstName is too short");
        }
        if (lastName.length() > maxLength) {
            violations.put("firstNameError", "FirstName is too long");
        }
        return violations;
    }

    private Map<String, String> validateEmail(String email, Map<String, String> violations) {
        properties.setAdditionalKey(".email");
        int minLength = Integer.parseInt(properties.getValue("minLength"));
        int maxLength = Integer.parseInt(properties.getValue("maxLength"));
        if (email.length() < minLength) {
            violations.put("firstNameError", "FirstName is too short");
        }
        if (email.length() > maxLength) {
            violations.put("firstNameError", "FirstName is too long");
        }
        return violations;
    }

    private Map<String, String> validatePassword(String password, Map<String, String> violations) {
        properties.setAdditionalKey(".password");
        int minLength = Integer.parseInt(properties.getValue("minLength"));
        int maxLength = Integer.parseInt(properties.getValue("maxLength"));
        if (password.length() < minLength) {
            violations.put("firstNameError", "FirstName is too short");
        }
        if (password.length() > maxLength) {
            violations.put("firstNameError", "FirstName is too long");
        }
        return violations;
    }
}
