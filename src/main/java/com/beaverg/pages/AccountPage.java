package com.beaverg.pages;

import com.beaverg.base.BasePage;
import com.beaverg.utils.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPage extends BasePage {
    @FindBy(xpath = "//li[@itemprop='itemListElement'][1]//span[@itemprop='name']")
    private WebElement titleElement;

    private final String title;

    private final WebElement logoutLink = Waits
            .waitLocator(driver, By.xpath("//li[@class='MyAccountTabListItem']//button[text()='Logout']"));

    public AccountPage(WebDriver driver) {
        super(driver);
        title = "My Account";
    }

    public boolean isPageOpen() {
        REPORT.info("[INFO]: Account Page '%s' opening check...");
        return Waits.waitPageLoading(driver) &&
                Waits.waitTitleContains(driver, title) &&
                Waits.waitVisibility(driver, titleElement) &&
                Waits.waitTextInElement(driver, titleElement, title);
    }

    public HomePage clickLogoutLink() {
        Waits.clickIfPresent(driver, logoutLink);
        REPORT.info("[INFO]: Logout link was clicked");
        return new HomePage(driver);
    }
}
