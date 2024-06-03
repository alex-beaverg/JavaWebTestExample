package com.beaverg.tests;

import com.beaverg.base.BaseTest;
import com.beaverg.components.popup.SignInPopupComponent;
import com.beaverg.pages.AccountPage;
import com.beaverg.pages.HomePage;
import com.beaverg.utils.ObjectService;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Test Site web testing")
@Feature("Logining testing")
public class LoginTest extends BaseTest {

    @Test
    @Story("Successful logining testing")
    @Description("Successful logining test")
    public void verifySuccessfulLoginTest() {
        HomePage homePage = getHomePage();

        SignInPopupComponent signInPopupComponent = homePage.getLoginComponent().clickSignInButton();
        AccountPage accountPage = signInPopupComponent.login(ObjectService.createValidUser());
        Assert.assertTrue(accountPage.isPageOpen(), "Opened page isn't Account Page!");
    }

    @Test
    @Story("Unsuccessful logining testing")
    @Description("Unsuccessful logining test")
    public void verifyUnsuccessfulLoginTest() {
        HomePage homePage = getHomePage();

        SignInPopupComponent signInPopupComponent = homePage.getLoginComponent().clickSignInButton();
        AccountPage accountPage = signInPopupComponent.login(ObjectService.createInvalidUser());
        Assert.assertFalse(accountPage.isPageOpen(), "Opened page is Account Page!");
    }

    @Test
    @Story("Logout testing")
    @Description("Logout test")
    public void verifyLogoutTest() {
        HomePage homePage = getHomePage();

        SignInPopupComponent signInPopupComponent = homePage.getLoginComponent().clickSignInButton();
        AccountPage accountPage = signInPopupComponent.login(ObjectService.createValidUser());

        homePage = accountPage.clickLogoutLink();
        Assert.assertTrue(homePage.isPageOpen(), "Home page isn't open!");
    }
}
