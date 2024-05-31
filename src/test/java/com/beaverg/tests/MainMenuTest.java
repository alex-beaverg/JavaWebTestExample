package com.beaverg.tests;

import com.beaverg.base.BaseTest;
import com.beaverg.components.drop.MainMenuDropComponent;
import com.beaverg.components.part_of_page.MainMenuComponent;
import com.beaverg.pages.HomePage;
import com.beaverg.pages.ProductListPage;
import com.beaverg.utils.StringTransform;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

@Epic("Test Site web testing")
@Feature("Main Menu testing")
public class MainMenuTest extends BaseTest {

    @Test(dataProvider = "getMainMenuItems")
    @Story("Main Menu items testing")
    @Description("Verifying opening main pages")
    public void verifyMainMenuItemTest(int mainMenuIndex, String title) {
        HomePage homePage = getHomePage();
        MainMenuComponent mainMenuComponent = homePage
                .getMainMenuComponent();
        String mainMenuItem = mainMenuComponent
                .getMainMenuItemsTextList()
                .get(mainMenuIndex);
        ProductListPage productListPage = mainMenuComponent
                .clickMainMenuItemByIndex(mainMenuIndex);
        Assert.assertTrue(productListPage.isPageOpen(mainMenuItem),
                String.format("Opened page isn't Product List Page '%s'!", title));
    }

    @DataProvider
    private Object[][] getMainMenuItems() {
        setup();
        List<String> mainMenuItems = getHomePage()
                .getMainMenuComponent()
                .getMainMenuItemsTextList();
        Object[][] testData = new Object[mainMenuItems.size()][2];
        for (int i = 0; i < mainMenuItems.size(); i++) {
            testData[i][0] = i;
            testData[i][1] = StringTransform.capitalizeWords(mainMenuItems.get(i));
        }
        teardown();
        return testData;
    }

    @Test(dataProvider = "getDropMainMenuItems")
    @Story("Drop Main Menu items testing")
    @Description("Verifying opening other pages")
    public void verifyDropMainMenuItemTest(int mainMenuIndex, int dropIndex, String dropTitle) {
        HomePage homePage = getHomePage();
        MainMenuDropComponent menuDropComponent = homePage
                .getMainMenuComponent()
                .moveToMainMenuItemByIndex(mainMenuIndex);
        String dropMenuItem = StringTransform.capitalizeWords(menuDropComponent
                .getDropMainMenuItemList()
                .get(dropIndex)
                .getText()
                .toLowerCase());
        ProductListPage productListPage = menuDropComponent
                .clickDropMainMenuItemByIndex(dropIndex);
        Assert.assertTrue(productListPage.isPageOpen(dropMenuItem),
                String.format("Opened page isn't Product List Page '%s'!", dropTitle));
    }

    @DataProvider
    private Object[][] getDropMainMenuItems() {
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
