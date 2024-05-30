package com.beaverg.components.part_of_page;

import com.beaverg.base.Base;
import com.beaverg.pages.ProductListPage;
import com.beaverg.utils.Waiting;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@FindBy(xpath = "//div[@class='MenuOverlay-Menu']/ul")
public class MainMenuComponent extends Base {
    @FindBy(xpath = ".//li//figcaption")
    private List<WebElement> mainItemsList;

    public MainMenuComponent(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getMainItemsList() {
        REPORT.info("[INFO]: Getting for Main Menu items list...");
        return mainItemsList;
    }

    public List<String> getMainItemsTextList() {
        REPORT.info("[INFO]: Getting for Main Menu items text list...");
        return mainItemsList.stream().map(item -> item.getText().toLowerCase()).toList();
    }

    public ProductListPage clickMainMenuItem(int index) {
        Waiting.clickIfPresent(driver, mainItemsList.get(index));
        REPORT.info("[INFO]: Main Menu item was clicked");
        return new ProductListPage(driver);
    }
}
