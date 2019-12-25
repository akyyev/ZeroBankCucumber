package com.zerobank.step_definitions;

import com.zerobank.pages.AccountSummaryPage;
import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class AccountSummaryStepDefinitions {

    AccountSummaryPage accountSummaryPage = new AccountSummaryPage();

    @Then("Account Summary page should have title as {string}")
    public void account_Summary_page_should_have_title_as(String title) {
        Assert.assertEquals(title, Driver.getDriver().getTitle());
    }

    @Given("verifies that account types are displayed")
    public void verifies_that_account_types_are_displayed(List<String> dataTable) {
        Assert.assertEquals(dataTable, BrowserUtils.getListOfString(accountSummaryPage.accountTypes));
    }

    @Given("verifies that column names are displayed on Credits Accounts table")
    public void verifies_that_column_names_are_displayed_on_Credits_Accounts_table(List<String> dataTable) {
        Assert.assertEquals(dataTable, BrowserUtils.getListOfString(accountSummaryPage.creditAccountTableColumnNames));
    }

    @When("the user clicks on Savings link on the Account Summary page")
    public void the_user_clicks_on_Savings_link_on_the_Account_Summary_page() {
        accountSummaryPage.savingsLink.click();
    }

    @When("the user clicks on {string} link on the Account Summary page")
    public void the_user_clicks_on_link_on_the_Account_Summary_page(String string) {
        accountSummaryPage.clickLink(string);
    }

    @Then("Account dropdown should have Brokerage selected")
    public void account_dropdown_should_have_Brokerage_selected() {

    }

}
