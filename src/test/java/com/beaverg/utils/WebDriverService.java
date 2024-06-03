package com.beaverg.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverService {
    private static WebDriver driver;

    private WebDriverService() {}

    public static WebDriver getDriver() {
        if (driver == null) {
            String browserName = System.getProperty("browser");
            if (browserName == null) {
                browserName = PropertyGetter.getProperty("browser");
            }
            switch (browserName) {
                case ("firefox") -> driver = new FirefoxDriver();
                case ("edge") -> driver = new EdgeDriver();
                default -> driver = new ChromeDriver();
            }
        }
        return driver;
    }

    public static void closeDriver() {
        driver.quit();
        driver = null;
    }
}
