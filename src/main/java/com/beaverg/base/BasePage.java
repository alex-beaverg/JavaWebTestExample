package com.beaverg.base;

import com.beaverg.components.part_of_page.LoginComponent;
import com.beaverg.components.part_of_page.MainMenuComponent;
import org.openqa.selenium.WebDriver;

public class BasePage extends Base {
    private final MainMenuComponent mainMenuComponent;
    private final LoginComponent loginComponent;

    public BasePage(WebDriver driver) {
        super(driver);
        mainMenuComponent = new MainMenuComponent(driver);
        loginComponent = new LoginComponent(driver);
    }

    public MainMenuComponent getMainMenuComponent() {
        REPORT.info("[INFO]: Getting Main Menu...");
        return mainMenuComponent;
    }

    public LoginComponent getLoginComponent() {
        REPORT.info("[INFO]: Getting Login component...");
        return loginComponent;
    }
}
