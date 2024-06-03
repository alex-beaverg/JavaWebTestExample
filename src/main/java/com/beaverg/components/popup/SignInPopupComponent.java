package com.beaverg.components.popup;

import com.beaverg.base.Base;
import com.beaverg.domain.User;
import com.beaverg.pages.AccountPage;
import com.beaverg.utils.Waits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInPopupComponent extends Base {
    @FindBy(xpath = "//input[@label='Login or Email']")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@label='Password']")
    private WebElement passInput;

    @FindBy(xpath = "//button[text()='Sign in']")
    private WebElement signInButton;

    private final String signInButtonText;

    public SignInPopupComponent(WebDriver driver) {
        super(driver);
        signInButtonText = "SIGN IN";
    }

    public AccountPage login(User user) {
        REPORT.info("[INFO]: Logining...");
        Waits.waitTextInElement(driver, signInButton, signInButtonText);
        emailInput.sendKeys(user.getEmail());
        passInput.sendKeys(user.getPass());
        Waits.clickIfPresent(driver, signInButton);
        return new AccountPage(driver);
    }
}
