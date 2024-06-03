package com.beaverg.pages;

import com.beaverg.base.BasePage;
import com.beaverg.utils.Waiting;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPage extends BasePage {
    @FindBy(xpath = "//li[@itemprop='itemListElement'][1]//span[@itemprop='name']")
    private WebElement titleElement;

    private final String title;

    private final WebElement logoutLink = Waiting
            .waitLocator(driver, By.xpath("//li[@class='MyAccountTabListItem']//button[text()='Logout']"));

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

    public HomePage clickLogoutLink() {
        Waiting.clickIfPresent(driver, logoutLink);
        REPORT.info("[INFO]: Logout link was clicked");
        return new HomePage(driver);
    }
}
