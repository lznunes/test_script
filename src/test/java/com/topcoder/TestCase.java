package com.topcoder;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

public class TestCase {

    private WebDriver driverCh;
    private WebDriver driverFx;

    @BeforeMethod
    public void setUp() {
        // Configuração automática do WebDriver usando o WebDriverManager
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();

        // Inicializar os WebDrivers
        driverCh = new ChromeDriver();
        driverFx = new FirefoxDriver();
    }

    @Test
    public void myTestChrome() {
        runTest(driverCh);
    }

    @Test
    public void myTestFirefox() {
        runTest(driverFx);
    }

    private void runTest(WebDriver driver) {
        driver.get("https://www.google.com");
        // Adicione a lógica do teste aqui
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        // Captura de screenshot apenas em caso de falha
        if (result.getStatus() == ITestResult.SUCCESS) {
            captureScreenshot(driverCh, "Chrome");
            captureScreenshot(driverFx, "Firefox");
        }

        if (driverCh != null) {
            driverCh.quit();
        }

        if (driverFx != null) {
            driverFx.quit();
        }
    }

    private void captureScreenshot(WebDriver driver, String browserName) {
        try {
            if (driver != null) {
                TakesScreenshot ts = (TakesScreenshot) driver;
                byte[] screenshotBytes = ts.getScreenshotAs(OutputType.BYTES);

                // Salvar a screenshot em um local desejado
                // Por exemplo, você pode usar uma biblioteca como Apache Commons IO para salvar o arquivo
                FileUtils.writeByteArrayToFile(new File("src/main/resources/screenshot_" + browserName + ".png"), screenshotBytes);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
