package com.beaverg.components.part_of_page;

import com.beaverg.base.Base;
import com.beaverg.components.popup.SignInPopupComponent;
import com.beaverg.utils.Waiting;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@FindBy(xpath = "//button[@aria-label='Open my account']")
public class LoginComponent extends Base {
    @FindBy(xpath = ".//span[1]")
    private WebElement signInButton;

    public LoginComponent(WebDriver driver) {
        super(driver);
    }

    public SignInPopupComponent clickSignInButton() {
        Waiting.clickIfPresent(driver, signInButton);
        REPORT.info("[INFO]: Sign In button was clicked");
        return new SignInPopupComponent(driver);
    }
}
