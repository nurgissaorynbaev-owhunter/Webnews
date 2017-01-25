package com.nurgissao.webnews.utils;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {
    public static Logger log = Logger.getLogger(PropertiesLoader.class);
    private Properties properties;
    private String fileName;
    private String specificKey;

    public PropertiesLoader(String fileName) {
        this.fileName = fileName;
        loadProperties();
    }

    public void setSpecificKey(String specificKey) {
        this.specificKey = specificKey;
    }

    private void loadProperties() {
        properties = new Properties();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        try (InputStream in = classLoader.getResourceAsStream(fileName)) {
            properties.load(in);

        } catch (IOException e) {
            log.error("Failed to load configuration file.", e);
        }
    }

    public String getValue(String key) {
        String fullKey = specificKey + "." + key;
//        System.out.println("full key: " + fullKey);
        String value = properties.getProperty(fullKey);
        if (value == null) {
            log.error("Failed to get property value by key.");
        }
        return value;
    }
}
