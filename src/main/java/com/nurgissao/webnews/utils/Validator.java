package com.nurgissao.webnews.utils;

import java.util.HashMap;
import java.util.Map;

public class Validator {
    private static final String FILE_NAME = "validator.properties";
    private static PropertiesLoader properties;
    private String specificKey;

    public Validator() {
        properties = new PropertiesLoader(FILE_NAME);
    }

    public Map<String, String> validateSignInForm(Map<String, String> formValue) {
        Map<String, String> violations = new HashMap<>();
        specificKey = "sign";

        checkLength(formValue, violations);

        return violations;
    }

    public Map<String, String> validateSignUpForm(Map<String, String> formValue) {
        Map<String, String> violations = new HashMap<>();
        specificKey = "sign";

        checkLength(formValue, violations);

        return violations;
    }

    public Map<String, String> validateProfileForm(Map<String, String> formValue) {
        Map<String, String> violations = new HashMap<>();
        specificKey = "sign";
        String password = formValue.get("password");
        String confirmPassword = formValue.get("confirmPassword");

        if (!password.equals(confirmPassword)) {
            violations.put("password", "Passwords not equal");
        }

        checkLength(formValue, violations);

        return violations;
    }

    public Map<String, String> validateAddProductForm(Map<String, String> formValue) {
        Map<String, String> violations = new HashMap<>();
        specificKey = "product";

        checkLength(formValue, violations);

        return violations;
    }

    private Map<String, String> checkLength(Map<String, String> formValue, Map<String, String> violations) {
        String key, value;
        int minLength, maxLength;
        String minErrorMsg = "too short.";
        String maxErrorMsg = "too long.";

        for (Map.Entry<String, String> entry : formValue.entrySet()) {
            key = entry.getKey();
            value = entry.getValue();

            setPropertiesKey(key);
            minLength = Integer.parseInt(properties.getValue("minLength"));
            maxLength = Integer.parseInt(properties.getValue("maxLength"));

            if (minLength != 0 && value.length() < minLength) {
                violations.put(key, minErrorMsg);
            }

            if (maxLength != 0 && value.length() > maxLength) {
                violations.put(key, maxErrorMsg);
            }
        }
        return violations;
    }

    private void setPropertiesKey(String key) {
        String fullSpecificKey = specificKey + "." + key;
        properties.setSpecificKey(fullSpecificKey);
    }
}
