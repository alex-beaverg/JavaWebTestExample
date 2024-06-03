package com.beaverg.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waits {
    private static final Duration duration = Duration.ofSeconds(3);
    private static final Logger REPORT = LogManager.getLogger(Waits.class);

    public static boolean waitPageLoading(WebDriver driver) {
        REPORT.info("[INFO]: Waiting for page loading...");
        try {
            (new WebDriverWait(driver, duration))
                    .until(driver1 -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        } catch (TimeoutException e) {
            REPORT.info("[ERROR]: Page wasn't loaded!");
            return false;
        }
        return true;
    }

    public static boolean waitVisibility(WebDriver driver, WebElement element) {
        REPORT.info(String.format("[INFO]: Waiting for element with tag name '%s' visibility...", element.getTagName()));
        try {
            (new WebDriverWait(driver, duration))
                    .until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException e) {
            REPORT.info(String.format("[ERROR]: Element with tag name '%s' isn't visible!", element.getTagName()));
            return false;
        }
        return true;
    }

    public static WebElement waitLocator(WebDriver driver, By locator) {
        REPORT.info(String.format("[INFO]: Waiting for locator '%s' visibility...", locator));
        try {
            (new WebDriverWait(driver, duration))
                    .until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (TimeoutException e) {
            REPORT.info(String.format("[ERROR]: Locator '%s' isn't presence!", locator));
        }
        return driver.findElement(locator);
    }

    public static boolean waitTitleContains(WebDriver driver, String titleContent) {
        REPORT.info("[INFO]: Waiting for title loading...");
        try {
            (new WebDriverWait(driver, duration))
                    .until(ExpectedConditions.titleContains(titleContent));
        } catch (TimeoutException e) {
            REPORT.info(String.format("[ERROR]: Actual title is '%s'!", driver.getTitle()));
            REPORT.info(String.format("[ERROR]: Actual title doesn't contain '%s'!", titleContent));
            return false;
        }
        return true;
    }

    public static boolean waitTextInElement(WebDriver driver, WebElement element, String text) {
        REPORT.info(String.format("[INFO]: Waiting for text '%s' in element with tag name '%s'...", text, element.getTagName()));
        try {
            (new WebDriverWait(driver, duration))
                    .until(ExpectedConditions.textToBePresentInElement(element, text));
        } catch (TimeoutException e) {
            REPORT.info(String.format("[ERROR]: Actual text in element '%s' is '%s'!", element.getTagName(), element.getText()));
            REPORT.info(String.format("[ERROR]: Actual text in element '%s' doesn't contain '%s'!", element.getTagName(), text));
            return false;
        }
        return true;
    }

    public static void clickIfPresent(WebDriver driver, WebElement element) {
        REPORT.info(String.format("[INFO]: Waiting for element with tag name '%s' to be clickable...", element.getTagName()));
        (new WebDriverWait(driver, duration))
                .until(ExpectedConditions.elementToBeClickable(element)).click();
    }
}
