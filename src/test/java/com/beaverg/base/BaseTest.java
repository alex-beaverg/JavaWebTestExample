package com.beaverg.base;

import com.beaverg.pages.HomePage;
import com.beaverg.utils.WebDriverService;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        driver = WebDriverService.getDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    public void teardown() {
        WebDriverService.closeDriver();
    }

    protected HomePage getHomePage() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isPageOpen(), "Home page isn't open!");
        return homePage;
    }
}
