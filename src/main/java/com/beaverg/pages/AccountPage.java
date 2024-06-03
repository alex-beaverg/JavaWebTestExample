package com.beaverg.pages;

import com.beaverg.base.BasePage;
import com.beaverg.utils.Waiting;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPage extends BasePage {
    @FindBy(xpath = "//li[@itemprop='itemListElement'][1]//span[@itemprop='name']")
    private WebElement titleElement;

    private final String title;

    public AccountPage(WebDriver driver) {
        super(driver);
        title = "My Account";
    }

    public boolean isPageOpen() {
        REPORT.info("[INFO]: Account Page '%s' opening check...");
        return Waiting.waitPageLoading(driver) &&
                Waiting.waitTitleContains(driver, title) &&
                Waiting.waitVisibility(driver, titleElement) &&
                Waiting.waitTextInElement(driver, titleElement, title);
    }
}
