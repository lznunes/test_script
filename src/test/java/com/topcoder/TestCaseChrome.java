package com.topcoder;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestCaseChrome {
    private WebDriver driver;
    private static final String BASE_URL = "http://www.google.com";

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        // Inicializar os WebDrivers
        driver = new ChromeDriver();
    }

    @Test
    public void myTest() {
        driver.get(BASE_URL);
        System.out.println("pass");
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (driver != null) {
            if (result.getStatus() == ITestResult.FAILURE) {
                captureScreenshot(result.getName());
            }
            driver.quit();
        }
    }

    private void captureScreenshot(String methodName) {
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String screenshotName = methodName + "_" + timeStamp + ".png";
            FileUtils.copyFile(screenshot, new File("screenshots/" + screenshotName));
            System.out.println("Screenshot captured: " + screenshotName);
        } catch (IOException e) {
            System.err.println("Error capturing screenshot: " + e.getMessage());
        }
    }
}
