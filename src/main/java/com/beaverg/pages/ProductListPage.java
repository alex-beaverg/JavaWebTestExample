package com.beaverg.pages;

import com.beaverg.base.BasePage;
import com.beaverg.components.part_of_page.ProductListComponent;
import com.beaverg.utils.StringTransform;
import com.beaverg.utils.Waits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductListPage extends BasePage {
    @FindBy(xpath = "//h1")
    private WebElement title;

    private final ProductListComponent productListComponent;

    public ProductListPage(WebDriver driver) {
        super(driver);
        productListComponent = new ProductListComponent(driver);
    }

    public ProductListComponent getProductListComponent() {
        REPORT.info("[INFO]: Getting Product List...");
        return productListComponent;
    }

    public boolean isPageOpen(String pageTitle) {
        pageTitle = StringTransform.capitalizeWords(pageTitle);
        REPORT.info(String.format("[INFO]: Product List Page '%s' opening check...", pageTitle));
        return Waits.waitPageLoading(driver) &&
                Waits.waitTitleContains(driver, pageTitle) &&
                Waits.waitVisibility(driver, title) &&
                Waits.waitTextInElement(driver, title, pageTitle);
    }
}
