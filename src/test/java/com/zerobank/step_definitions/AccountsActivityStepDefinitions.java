package com.zerobank.step_definitions;

import com.zerobank.pages.AccountsActivityPage;
import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.Driver;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class AccountsActivityStepDefinitions {

    AccountsActivityPage accountsActivityPage = new AccountsActivityPage();

    @When("user clicks on {string} tab")
    public void user_clicks_on_tab(String string) {
        List<String> tabs = BrowserUtils.getListOfString(accountsActivityPage.navigationTabs);
        int i=0;
        for(String each : tabs){
            if(each.equals(string)) {
                accountsActivityPage.navigationTabs.get(i).click();
            }
            i++;
        }
    }

    @When("verify that page title is {string}")
    public void verify_that_page_title_is(String string) {
        Assert.assertEquals(string, Driver.getDriver().getTitle());
    }

    @When("verify options are available on account dropdown")
    public void verify_options_are_available_on_account_dropdown(List<String> dataTable) {
        Assert.assertEquals(dataTable, BrowserUtils.getListOfString(accountsActivityPage.accountDropDownOptions));
    }

    @When("verify transactions table options are displayed")
    public void verify_transactions_table_options_are_displayed(List<String> dataTable) {
        BrowserUtils.waitForPageToLoad(1);
        Assert.assertEquals(dataTable, BrowserUtils.getListOfString(accountsActivityPage.transactionsTableColumnNames));
    }

}
