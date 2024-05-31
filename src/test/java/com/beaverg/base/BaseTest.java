package com.beaverg.base;

import com.beaverg.pages.HomePage;
import com.beaverg.utils.StringTransform;
import com.beaverg.utils.WebDriverService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import java.util.List;

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

    @DataProvider
    protected Object[][] getDropMainMenuItems() {
        setup();
        HomePage homePage = getHomePage();
        int itemsNumber = homePage
                .getMainMenuComponent()
                .getMainMenuItemsTextList()
                .size();
        int counter = 0;
        for (int i = 0; i < itemsNumber; i++) {
            List<WebElement> dropItems = homePage
                    .getMainMenuComponent()
                    .moveToMainMenuItemByIndex(i)
                    .getDropMainMenuItemList();
            for (int j = 0; j < dropItems.size(); j++) {
                counter++;
            }
        }
        Object[][] testData = new Object[counter][3];
        int k = 0;
        for (int i = 0; i < itemsNumber; i++) {
            List<WebElement> dropItems = homePage
                    .getMainMenuComponent()
                    .moveToMainMenuItemByIndex(i)
                    .getDropMainMenuItemList();
            for (int j = 0; j < dropItems.size(); j++) {
                testData[k][0] = i;
                testData[k][1] = j;
                testData[k][2] = StringTransform.capitalizeWords(dropItems.get(j).getText().toLowerCase());
                k++;
            }
        }
        teardown();
        return testData;
    }
}
