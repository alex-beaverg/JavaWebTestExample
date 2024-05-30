package com.beaverg.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyGetter {
    private static final Properties property = new Properties();

    public static String getProperty(String key) {
        String value;
        try (FileInputStream fis = new FileInputStream("src/main/resources/config.properties")) {
            property.load(fis);
            value = property.getProperty(key);
        } catch (IOException e) {
            throw new RuntimeException("You have been problem with reading from property file!", e);
        }
        return value;
    }

    public static String getData(String key) {
        String value;
        try (FileInputStream fis = new FileInputStream("src/main/resources/data.properties")) {
            property.load(fis);
            value = property.getProperty(key);
        } catch (IOException e) {
            throw new RuntimeException("You have been problem with reading from property file!", e);
        }
        return value;
    }
}
