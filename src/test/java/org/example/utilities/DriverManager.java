package org.example.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.manager.SeleniumManager;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverManager {
    private static WebDriver driver;
    public static String browserVersion;

    public static WebDriver initializeDriver() {
        LogUtility.debug("Starting a new Test, initializing driver" + System.lineSeparator());
        String browser = ConfigReader.getBrowserName();

        LogUtility.info("Running on browser: " + browser);
        SeleniumManager.getInstance();

        switch (browser.toLowerCase()) {
            case "chrome" -> driver = new ChromeDriver();
            case "firefox" -> driver = new FirefoxDriver();
            case "edge" -> driver = new EdgeDriver();
            default -> LogUtility.fatal("Invalid browser/browser config doesn't exist");
        }

        LogUtility.info("Driver initialized");

        // Get browser version
        browserVersion = ((RemoteWebDriver)driver).getCapabilities().getBrowserVersion();

        driver.manage().window().maximize();

        return driver;
    }

    public static void quitDriver() {
        LogUtility.debug("End of Test. Quiting driver" + System.lineSeparator());

        if (driver != null) {
            driver.quit();
            LogUtility.info("Successfully quit the driver.");
        }
    }
}
