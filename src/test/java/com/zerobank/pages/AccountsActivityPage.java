package com.zerobank.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AccountsActivityPage extends BasePage{

    @FindBy(css = "[id='aa_accountId'] > option")
    public List<WebElement> accountDropDownOptions;

    @FindBy(css = "[id='all_transactions_for_account'] th")
    public List<WebElement> transactionsTableColumnNames;
}
