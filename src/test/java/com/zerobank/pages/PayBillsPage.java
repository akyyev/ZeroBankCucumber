package com.zerobank.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class PayBillsPage extends BasePage {

    @FindBy(id = "sp_payee")
    public WebElement payeeOptions;

    @FindBy(id = "sp_account")
    public WebElement accountOptions;

    @FindBy(id = "sp_amount")
    public WebElement amountOfMoneyInputBox;

    @FindBy(id = "sp_date")
    public WebElement date;

    @FindBy(id = "sp_description")
    public WebElement description;

    @FindBy(id = "pay_saved_payees")
    public WebElement payButton;

    @FindBy(id = "alert_content")
    public WebElement verificationMessage;

    public void setPayeeOptions(String payeeName){
        Select select = new Select(payeeOptions);
        select.selectByVisibleText(payeeName);
    }

    public void setAccountOptions(String accountName){
        Select select = new Select(accountOptions);
        select.selectByVisibleText(accountName);
    }



}
