package com.zerobank.pages;

import com.zerobank.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BasePage {

    public BasePage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(css = "[class='nav nav-tabs']>li")
    public List<WebElement> navigationTabs;

}

 /*
        0. Account Summary
        1. Account Activity
        2. Transfer Funds
        3. Pay Bills
        4. My Money Map
        5. Online Statements
     */
