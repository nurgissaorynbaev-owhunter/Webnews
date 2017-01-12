package com.nurgissao.webnews;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DAOProperties {
    public static Logger log = Logger.getLogger(DAOProperties.class);
    private Properties properties;
    private static final String file = "dao.properties";
    private String specificKey;

    public DAOProperties(String specificKey) {
        this.specificKey = specificKey;
        loadProperties();
    }

    private void loadProperties() {
        properties = new Properties();

        try (InputStream in = ClassLoader.getSystemResourceAsStream(file)) {
            properties.load(in);

        } catch (IOException e) {
            log.error("Failed to load dao configuration file.", e);
        }
    }

    public String getValue(String key) {
        String fulKey = specificKey + "." + key;
        String result = properties.getProperty(fulKey);
        if (result == null) {
            log.error("Failed to get property value by key.");
        }
        return result;
    }
}
