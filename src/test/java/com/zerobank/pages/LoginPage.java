package com.zerobank.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(css = "[id='user_login']")
    public WebElement userIdInput;

    @FindBy(css = "[id='user_password']")
    public WebElement userPasswordInput;

    @FindBy(css = "input[value='Sign in']")
    public WebElement signInButton;

    @FindBy(css = "div[class='alert alert-error']")
    public WebElement errorMessage;

    public void login(String username, String password){
        userIdInput.sendKeys(username);
        userPasswordInput.sendKeys(password, Keys.ENTER);
    }

}
