package com.beaverg.tests;

import com.beaverg.base.BaseTest;
import com.beaverg.components.drop.MainMenuDropComponent;
import com.beaverg.domain.Product;
import com.beaverg.pages.HomePage;
import com.beaverg.pages.ProductListPage;
import com.beaverg.pages.ProductPage;
import com.beaverg.utils.PropertyGetter;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Test Site web testing")
@Feature("Product testing")
public class ProductTest extends BaseTest {

    @Test(dataProvider = "getDropMainMenuItems")
    @Story("Product from different pages testing")
    @Description("Comparing products from different pages")
    public void verifyMainMenuItemTest(int mainMenuIndex, int dropIndex, String dropTitle) {
        HomePage homePage = getHomePage();
        int productNumber = Integer.parseInt(PropertyGetter.getData("product_number"));

        MainMenuDropComponent menuDropComponent = homePage
                .getMainMenuComponent()
                .moveToMainMenuItemByIndex(mainMenuIndex);
        ProductListPage productListPage = menuDropComponent
                .clickDropMainMenuItemByIndex(dropIndex);

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
}
