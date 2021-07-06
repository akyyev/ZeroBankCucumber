package com.zerobank.pages;

import com.zerobank.utilities.BrowserUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

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

    @FindBy(css = "[href='#ui-tabs-2']")
    public WebElement addNewPayeeSubTab;

    @FindBy(css = "[href='#ui-tabs-3']")
    public WebElement purchaseForeignCurrencySubTab;

    @FindBy(id = "np_new_payee_name")
    public WebElement payeeName;

    @FindBy(id = "np_new_payee_address")
    public WebElement payeeAddress;

    @FindBy(id = "np_new_payee_account")
    public WebElement payeeAccount;

    @FindBy(id = "np_new_payee_details")
    public WebElement payeeDetails;

    @FindBy(id = "pc_currency")
    public WebElement currencyDropDown;

    @FindBy(id = "purchase_cash")
    public WebElement purchaseButton;

    @FindBy(id = "pc_amount")
    public WebElement purchaseAmountInputBox;

    @FindBy(css = "[type='radio']")
    public List<WebElement> radioSelect;


    public void createPayee(String name, String address, String account, String details){
        BrowserUtils.waitForPageToLoad(2);
        payeeName.sendKeys(name);
        payeeAddress.sendKeys(address);
        payeeAccount.sendKeys(account);
        payeeDetails.sendKeys(details, Keys.ENTER);
    }
    public void setPayeeOptions(String payeeName){
        Select select = new Select(payeeOptions);
        select.selectByVisibleText(payeeName);
    }

    public void setAccountOptions(String accountName){
        Select select = new Select(accountOptions);
        select.selectByVisibleText(accountName);
    }

}
