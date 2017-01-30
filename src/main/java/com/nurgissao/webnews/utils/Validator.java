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

    public Map<String, String> validateSignForm(Map<String, String> formValue) {
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

        checkLength(formValue, violations);

        if (!password.equals(confirmPassword)) {
            violations.put("password", "Passwords not equal");
        }


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
        String minErrorMsg = "too short.";
        String maxErrorMsg = "too long.";

        for (Map.Entry<String, String> entry : formValue.entrySet()) {
            key = entry.getKey();
            value = entry.getValue();

            setPropertiesKey(key);

            String minValue = properties.getValue("minLength");
            String maxValue = properties.getValue("maxLength");

            if (minValue != null) {
                int minLength = Integer.parseInt(minValue);
                if (value.length() < minLength) {
                    violations.put(key, minErrorMsg);
                }
            }

            if (maxValue != null) {
                int maxLength = Integer.parseInt(maxValue);
                if (value.length() > maxLength) {
                    violations.put(key, maxErrorMsg);
                }
            }
        }
        return violations;
    }

    private void setPropertiesKey(String key) {
        String fullSpecificKey = specificKey + "." + key;
        properties.setSpecificKey(fullSpecificKey);
    }
}
