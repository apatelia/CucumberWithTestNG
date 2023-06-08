package org.example.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static String browserName;
    private static String baseUrl;

    private static void loadProperties() {
        try {
            String propsFilepath = "/src/test/resources/config.properties";
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + propsFilepath);
            Properties props = new Properties();

            LogUtility.debug("Loading properties");
            props.load(fis);
            browserName = props.getProperty("browser");
            baseUrl = props.getProperty("baseUrl");
        } catch (FileNotFoundException e) {
            LogUtility.fatal("Configuration properties file not found: " + e.getMessage());
        } catch (IOException e) {
            LogUtility.fatal("Exception while loading properties: " + e.getMessage());
        }
    }

    public static String getBrowserName() {
        if (browserName == null)
            loadProperties();

        return browserName;
    }

    public static String getBrowserFullName() {
        if (browserName == null)
            loadProperties();

        switch (browserName) {
            case "chrome" -> {
                return "Google Chrome";
            }
            case "firefox" -> {
                return "Mozilla Firefox";
            }
            case "edge" -> {
                return "Microsoft Edge";
            }
            default -> {
                return browserName;
            }
        }
    }

    public static String getBaseUrl() {
        if (baseUrl == null)
            loadProperties();

        return baseUrl;
    }
}
