package com.beaverg.listeners;

import com.beaverg.utils.WebDriverService;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import static com.beaverg.utils.Printers.*;

public class TestNGListener implements ITestListener {
    private final File tempLogFile = new File("target/test_report.log");
    public static final Logger LOGGER = LogManager.getLogger(TestNGListener.class);

    // Colors:
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";

    @Override
    public void onTestStart(ITestResult result) {
        PRINT2LN.info(String.format(ANSI_BLUE + "Test: '%s'", result.getName()));
        PRINTLN.info(String.format("Group: '%s'", result.getTestContext().getName()));
        PRINTLN.info(String.format("Suite: '%s'", result.getTestContext().getSuite().getName()));
        PRINTLN.info("Test was started!" + ANSI_RESET);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        try {
            Allure.addAttachment("Log", FileUtils.openInputStream(tempLogFile));
            FileUtils.write(tempLogFile, "", Charset.defaultCharset());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Allure.addAttachment("Message", String.format("Test '%s' was completed successfully!", result.getName()));
        PRINTLN.info(String.format(ANSI_GREEN + "Test '%s' was completed successfully!", result.getName()));
        PRINTLN.info(ANSI_RESET);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        File screenshot = ((TakesScreenshot) WebDriverService.getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            Allure.addAttachment("Log", FileUtils.openInputStream(tempLogFile));
            Allure.addAttachment(result.getName() + " screenshot", FileUtils.openInputStream(screenshot));
            FileUtils.write(tempLogFile, "", Charset.defaultCharset());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        LOGGER.error(String.format("Test '%s' was failed!", result.getName()));
        PRINTLN.info("");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Allure.addAttachment("Message", String.format("Test '%s' was skipped!", result.getName()));
        try {
            FileUtils.write(tempLogFile, "", Charset.defaultCharset());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        PRINTLN.info(String.format(ANSI_YELLOW + "Test '%s' was skipped!", result.getName()));
        PRINTLN.info(ANSI_RESET);
    }
}
