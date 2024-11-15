package com.saucedemo.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private Properties properties;
    FileInputStream fileInputStream;

    public ConfigReader() {
        properties = new Properties();
        try {
            fileInputStream = new FileInputStream("src/resources/config.properties");
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
