package com.nurgissao.webnews.utils;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class Validator {
    public static final Logger log = Logger.getLogger(Validator.class);
    private static final String FILE_NAME = "validator.properties";
    private static final String SIGN_SPECIFIC_KEY = "sign";
    private static final String PRODUCT_SPECIFIC_KEY = "product";
    private static final String CUSTOMER_SPECIFIC_KEY = "customer";
    private static final String MIN_LENGTH_ERROR_MSG = "too short";
    private static final String MAX_LENGTH_ERROR_MSG = "too long";
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
        specificKey = SIGN_SPECIFIC_KEY;
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
        specificKey = PRODUCT_SPECIFIC_KEY;

        checkLength(formValue, violations);

        return violations;
    }

    public Map<String, String> validateCustomerRegistrationForm(Map<String, String> formValue) {
        Map<String, String> violations = new HashMap<>();
        specificKey = CUSTOMER_SPECIFIC_KEY;

        checkLength(formValue, violations);

        return violations;
    }

    private Map<String, String> checkLength(Map<String, String> formValue, Map<String, String> violations) {
        String key, value;

        for (Map.Entry<String, String> entry : formValue.entrySet()) {
            key = entry.getKey();
            value = entry.getValue();

            setPropertiesKey(key);

            String minValue = properties.getValue("minLength");
            String maxValue = properties.getValue("maxLength");

            if (minValue != null) {
                int minLength = Integer.parseInt(minValue);
                if (value.length() < minLength) {
                    violations.put(key, MIN_LENGTH_ERROR_MSG);
                }

            } else {
                log.info("Min length is null.");
            }

            if (maxValue != null) {
                int maxLength = Integer.parseInt(maxValue);
                if (value.length() > maxLength) {
                    violations.put(key, MAX_LENGTH_ERROR_MSG);
                }

            } else {
                log.info("Max length is null.");
            }
        }
        return violations;
    }

    private void setPropertiesKey(String key) {
        String fullSpecificKey = specificKey + "." + key;
        properties.setSpecificKey(fullSpecificKey);
    }
}
