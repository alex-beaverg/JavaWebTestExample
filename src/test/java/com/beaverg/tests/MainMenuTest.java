package com.beaverg.tests;

import com.beaverg.base.BaseTest;
import com.beaverg.pages.HomePage;
import com.beaverg.pages.ProductListPage;
import com.beaverg.utils.StringTransform;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
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

        String mainMenuItem = homePage.getMainMenuComponent().getMainItemsTextList().get(mainMenuIndex);
        ProductListPage productListPage = homePage.getMainMenuComponent().clickMainMenuItem(mainMenuIndex);
        Assert.assertTrue(productListPage.isPageOpen(mainMenuItem), String.format("Opened page isn't Product List Page '%s'!", title));
    }

    @DataProvider
    private Object[][] getMainMenuItems() {
        setup();
        List<String> mainMenuItems = getHomePage()
                .getMainMenuComponent()
                .getMainItemsTextList();
        teardown();
        Object[][] testData = new Object[mainMenuItems.size()][2];
        for (int i = 0; i < mainMenuItems.size(); i++) {
            testData[i][0] = i;
            testData[i][1] = StringTransform.capitalizeWords(mainMenuItems.get(i));
        }
        return testData;
    }
}
