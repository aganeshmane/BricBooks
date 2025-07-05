package com.bribooks.tests;



import com.bribooks.base.BaseTest;
import com.bribooks.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    LoginPage loginPage;

    @Test(priority = 1)
    public void verifyValidLogin() throws InterruptedException {
        loginPage = new LoginPage(driver);
        loginPage.open();
        Thread.sleep(2000); 

        loginPage.login(testData.getProperty("valid.username"), testData.getProperty("valid.password"));
        Thread.sleep(2000);

        Assert.assertTrue(loginPage.isLoggedIn(), "Valid login failed");
    }

    @Test(priority = 2)
    public void verifyInvalidLogin() throws InterruptedException {
        loginPage = new LoginPage(driver);
        loginPage.open();
        Thread.sleep(2000);

        loginPage.login(testData.getProperty("invalid.username"), testData.getProperty("invalid.password"));
        Thread.sleep(2000);

        Assert.assertTrue(loginPage.isLoginFailed(), "Login should fail for invalid credentials");
    }
}
