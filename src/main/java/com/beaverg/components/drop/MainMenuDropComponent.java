package com.beaverg.components.drop;

import com.beaverg.base.Base;
import com.beaverg.pages.ProductListPage;
import com.beaverg.utils.Waiting;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class MainMenuDropComponent extends Base {
    private final int index;
    private List<WebElement> dropMenuItems;

    public MainMenuDropComponent(WebDriver driver, int index) {
        super(driver);
        this.index = index;
        dropMenuItems = new ArrayList<>();
    }

    public List<WebElement> getDropMainMenuItemList() {
        REPORT.info("[INFO]: Getting for drop Main Menu items list...");
        dropMenuItems = driver.findElements(By.xpath(String.format("//li[@class='MenuOverlay-Item'][%d]//a", index)));
        return dropMenuItems;
    }

    public ProductListPage clickDropMainMenuItemByIndex(int dropIndex) {
        Waiting.clickIfPresent(driver, getDropMainMenuItemList().get(dropIndex));
        REPORT.info("[INFO]: Main Menu drop item was clicked");
        return new ProductListPage(driver);
    }
}
