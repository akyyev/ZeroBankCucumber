package com.zerobank.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AccountSummaryPage extends BasePage{

    @FindBy(css = "[class='board-header']")
    public List<WebElement> accountTypes;

    @FindBy(css = "div[class='board']:nth-of-type(3) tr>th")
    public List<WebElement> creditAccountTableColumnNames;

}
