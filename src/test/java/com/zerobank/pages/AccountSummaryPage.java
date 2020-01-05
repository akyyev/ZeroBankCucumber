package com.zerobank.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AccountSummaryPage extends BasePage{

    @FindBy(css = "[class='board-header']")
    public List<WebElement> accountTypes;

    @FindBy(css = "div[class='board']:nth-of-type(3) tr>th")
    public List<WebElement> creditAccountTableColumnNames;

    @FindBy(css = "[href='/bank/account-activity.html?accountId=1']")
    public WebElement savingsLink;

    @FindBy(css = "[href='/bank/account-activity.html?accountId=6']")
    public WebElement brokerageLink;

    @FindBy(css = "[href='/bank/account-activity.html?accountId=2']")
    public WebElement checkingLink;

    @FindBy(css = "[href='/bank/account-activity.html?accountId=5']")
    public WebElement creditCardLink;

    @FindBy(css = "[href='/bank/account-activity.html?accountId=4']")
    public WebElement loanLink;

    public void clickLink(String str){
        switch (str){
            case "Brokerage": brokerageLink.click(); break;
            case "Savings": savingsLink.click(); break;
            case "Checking": checkingLink.click(); break;
            case "Credit Card": creditCardLink.click(); break;
            case "Loan": loanLink.click(); break;
            default: throw new RuntimeException("No Such Account Type!");
        }
    }

}
