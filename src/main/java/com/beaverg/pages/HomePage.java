package com.beaverg.pages;

import com.beaverg.base.BasePage;
import com.beaverg.utils.PropertyGetter;
import com.beaverg.utils.Waits;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    private final String home_url;

    public HomePage(WebDriver driver) {
        super(driver);
        home_url = PropertyGetter.getProperty("home_url");
        this.driver.get(home_url);
    }

    public boolean isPageOpen() {
        REPORT.info("[INFO]: Home Page opening check...");
        return Waits.waitPageLoading(driver) && driver.getCurrentUrl().equals(home_url);
    }
}
