package com.beaverg.tests;

import com.beaverg.base.BaseTest;
import com.beaverg.components.drop.MainMenuDropComponent;
import com.beaverg.components.part_of_page.MainMenuComponent;
import com.beaverg.domain.Product;
import com.beaverg.pages.HomePage;
import com.beaverg.pages.ProductListPage;
import com.beaverg.pages.ProductPage;
import com.beaverg.utils.PropertyGetter;
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
@Feature("Product testing")
public class ProductTest extends BaseTest {

    @Test(dataProvider = "getFirstDropMainMenuItems")
    @Story("Product from different pages testing")
    @Description("Comparing products from different pages")
    public void verifyMainMenuItemTest(int mainMenuIndex, int dropIndex, String dropTitle) {
        HomePage homePage = getHomePage();
        int productNumber = Integer.parseInt(PropertyGetter.getData("product_number"));

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

        Product productFromProductListPage = productListPage
                .getProductListComponent()
                .getProductByIndex(productNumber);
        ProductPage productPage = productListPage
                .getProductListComponent()
                .clickProductByIndex(productNumber);
        Assert.assertTrue(productPage.isPageOpen(productFromProductListPage.getTitle()),
                String.format("Opened page isn't Product Page '%s'", productFromProductListPage.getTitle()));

        Product productFromProductPage = productPage.getProduct();
        Assert.assertEquals(productFromProductPage, productFromProductListPage, "Products aren't equal!");
    }

    @DataProvider
    private Object[][] getFirstDropMainMenuItems() {
        setup();
        HomePage homePage = getHomePage();
        int itemsNumber = homePage
                .getMainMenuComponent()
                .getMainMenuItemsTextList()
                .size();
        Object[][] testData = new Object[itemsNumber][3];
        for (int i = 0; i < itemsNumber; i++) {
            List<WebElement> dropItems = homePage
                    .getMainMenuComponent()
                    .moveToMainMenuItemByIndex(i)
                    .getDropMainMenuItemList();
            testData[i][0] = i;
            testData[i][1] = 0;
            testData[i][2] = StringTransform.capitalizeWords(dropItems.get(0).getText().toLowerCase());
        }
        teardown();
        return testData;
    }
}
