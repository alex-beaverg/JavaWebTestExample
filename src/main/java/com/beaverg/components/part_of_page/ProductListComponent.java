package com.beaverg.components.part_of_page;

import com.beaverg.base.Base;
import com.beaverg.domain.Product;
import com.beaverg.pages.ProductPage;
import com.beaverg.utils.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@FindBy(xpath = "//ul[@class='CategoryProductList-Page ']")
public class ProductListComponent extends Base {
    @FindBy(xpath = ".//p[@itemprop='name']")
    private List<WebElement> productNameList;

    @FindBy(xpath = ".//span[@itemprop='lowPrice']")
    private List<WebElement> productPriceList;

    private final WebElement currency = Waits
            .waitLocator(driver, By.xpath("//li[@class='ProductCard '][1]//span[@itemprop='lowPrice']//preceding-sibling::span"));

    public ProductListComponent(WebDriver driver) {
        super(driver);
    }

    private String getProductNameByIndex(int index) {
        REPORT.info("[INFO]: Getting Product name...");
        return productNameList.get(index).getText();
    }

    private double getProductPriceByIndex(int index) {
        REPORT.info("[INFO]: Getting Product price...");
        return Double.parseDouble(productPriceList.get(index).getText());
    }

    public Product getProductByIndex(int index) {
        REPORT.info("[INFO]: Creating Product from Product List page...");
        Waits.waitTextInElement(driver, currency, "£");
        return new Product(getProductNameByIndex(index), getProductPriceByIndex(index));
    }

    public ProductPage clickProductByIndex(int index) {
        Waits.clickIfPresent(driver, productNameList.get(index));
        REPORT.info("[INFO]: Product was clicked");
        return new ProductPage(driver);
    }
}
