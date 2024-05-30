package com.beaverg.pages;

import com.beaverg.base.BasePage;
import com.beaverg.utils.StringTransform;
import com.beaverg.utils.Waiting;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductListPage extends BasePage {
    @FindBy(xpath = "//h1")
    private WebElement title;

    public ProductListPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageOpen(String pageTitle) {
        pageTitle = StringTransform.capitalizeWords(pageTitle);
        REPORT.info(String.format("[INFO]: Product List Page '%s' opening check...", pageTitle));
        return Waiting.waitPageLoading(driver) &&
                Waiting.waitTitleContains(driver, pageTitle) &&
                Waiting.waitVisibility(driver, title) &&
                Waiting.waitTextInElement(driver, title, pageTitle);
    }
}
