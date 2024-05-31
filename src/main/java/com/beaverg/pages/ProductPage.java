package com.beaverg.pages;

import com.beaverg.base.BasePage;
import com.beaverg.domain.Product;
import com.beaverg.utils.Waiting;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage {
    @FindBy(xpath = "//article[@class='ProductActions']//p[@itemprop='name']")
    private WebElement productName;

    @FindBy(xpath = "//article[@class='ProductActions']//span[@itemprop='lowPrice']")
    private WebElement productPrice;

    @FindBy(xpath = "//button[@class='Button AddToCart ProductActions-AddToCart']/span[1]")
    private WebElement addToBasketButton;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageOpen(String productName) {
        REPORT.info(String.format("[INFO]: Product Page '%s' opening check...", productName));
        return Waiting.waitPageLoading(driver) &&
                Waiting.waitTitleContains(driver, productName) &&
                Waiting.waitVisibility(driver, this.productName) &&
                Waiting.waitTextInElement(driver, this.productName, productName);
    }

    private String getProductName() {
        REPORT.info("[INFO]: Getting Product name...");
        return productName.getText();
    }

    private double getProductPrice() {
        REPORT.info("[INFO]: Getting Product price...");
        return Double.parseDouble(productPrice.getText());
    }

    public Product getProduct() {
        REPORT.info("[INFO]: Getting Product from Product page...");
        Waiting.waitTextInElement(driver, addToBasketButton, "ADD TO BASKET");
        return new Product(getProductName(), getProductPrice());
    }
}
