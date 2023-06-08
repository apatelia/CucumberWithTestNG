package org.example.utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtility {
    private final WebDriver driver;

    public ScreenshotUtility(WebDriver driver) {
        this.driver = driver;
    }

    public String takeScreenshot(String testName) {
        File scrnFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String currentDir = System.getProperty("user.dir");
        String currentTimeStamp = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
        String screenshotFileName = testName + "_" + currentTimeStamp + ".png";
        String screenshotFileFullPath = currentDir + "/target/screenshots/" + screenshotFileName;

        try {
            FileUtils.copyFile(scrnFile, new File(screenshotFileFullPath));
            LogUtility.info("Successfully taken the screenshot for failed Test: " + testName);
        } catch (IOException e) {
            LogUtility.error("Could not take screenshot: " + e.getMessage());
        }

        return screenshotFileName;
    }
}
