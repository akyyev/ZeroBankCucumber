package com.zerobank.pages;

import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.ConfigurationReader;
import com.zerobank.utilities.Driver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public LoginPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }


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
