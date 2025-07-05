package com.bribooks.pages;

	import org.openqa.selenium.*;
	import org.openqa.selenium.support.*;
	import org.openqa.selenium.support.ui.*;

	import java.time.Duration;

	public class LoginPage {
	    private WebDriver driver;
	    private WebDriverWait wait;

	    public LoginPage(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    }

	    @FindBy(xpath = "//div[@role='button'][normalize-space()='USERNAME']")
	    WebElement usernameTab;

	    @FindBy(name = "username")
	    WebElement usernameField;

	    @FindBy(name = "password")
	    WebElement passwordField;

	    @FindBy(xpath = "//button[text()='Login']")
	    WebElement loginButton;

	    public void open() {
	        driver.get("https://www.bribooks.com/login");
	    }

	    public void login(String username, String password) {
	        wait.until(ExpectedConditions.visibilityOf(usernameTab)).click();
	        wait.until(ExpectedConditions.visibilityOf(usernameField)).sendKeys(username);
	        passwordField.sendKeys(password);
	        loginButton.click();
	    }

	    public boolean isLoggedIn() {
	        return driver.getCurrentUrl().contains("account");
	    }

	    public boolean isLoginFailed() {
	        return !driver.getCurrentUrl().contains("account");
	    }
	}



