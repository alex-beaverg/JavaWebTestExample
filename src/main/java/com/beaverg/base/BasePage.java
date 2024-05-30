package com.beaverg.base;

import com.beaverg.components.part_of_page.MainMenuComponent;
import org.openqa.selenium.WebDriver;

public class BasePage extends Base {
    private final MainMenuComponent mainMenuComponent;

    public BasePage(WebDriver driver) {
        super(driver);
        mainMenuComponent = new MainMenuComponent(this.driver);
    }

    public MainMenuComponent getMainMenuComponent() {
        REPORT.info("[INFO]: Getting Main Menu...");
        return mainMenuComponent;
    }
}
