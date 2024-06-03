package com.beaverg.components.part_of_page;

import com.beaverg.base.Base;
import com.beaverg.components.drop.MainMenuDropComponent;
import com.beaverg.pages.ProductListPage;
import com.beaverg.utils.Waits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@FindBy(xpath = "//div[@class='MenuOverlay-Menu']/ul")
public class MainMenuComponent extends Base {
    @FindBy(xpath = ".//li//figcaption")
    private List<WebElement> mainMenuItemsList;

    public MainMenuComponent(WebDriver driver) {
        super(driver);
    }

    public List<String> getMainMenuItemsTextList() {
        REPORT.info("[INFO]: Getting for Main Menu items text list...");
        return mainMenuItemsList.stream().map(item -> item.getText().toLowerCase()).toList();
    }

    public ProductListPage clickMainMenuItemByIndex(int index) {
        Waits.clickIfPresent(driver, mainMenuItemsList.get(index));
        REPORT.info("[INFO]: Main Menu item was clicked");
        return new ProductListPage(driver);
    }

    public MainMenuDropComponent moveToMainMenuItemByIndex(int index) {
        Actions actions = new Actions(driver);
        actions.moveToElement(mainMenuItemsList.get(index)).perform();
        return new MainMenuDropComponent(driver, index + 1);
    }
}
