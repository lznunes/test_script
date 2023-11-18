package com.topcoder;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCase {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Configuração automática do WebDriver usando o WebDriverManager
        WebDriverManager.chromedriver().clearResolutionCache();
        WebDriverManager.chromedriver().setup();

        // Inicializar o WebDriver
        driver = new ChromeDriver();
    }

    @Test
    public void myTest() {
        driver.get("https://www.google.com");
        // Adicione a lógica do teste aqui
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
