package com.zerobank.step_definitions;

import com.zerobank.pages.AccountsActivityPage;
import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

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

    @Then("Account dropdown should have {string} selected")
    public void account_dropdown_should_have_selected(String string) {
        BrowserUtils.waitForPageToLoad(2);
        Assert.assertTrue(accountsActivityPage.isSelected(string));
    }

    @Given("the user accesses the Find Transactions tab")
    public void the_user_accesses_the_Find_Transactions_tab() {
        accountsActivityPage.findTransactionsSubTab.click();
    }

    @When("the user enters date range from {string} to {string}")
    public void the_user_enters_date_range_from_to(String from, String to) {
        BrowserUtils.waitForPageToLoad(2);
        accountsActivityPage.datesFrom.clear();
        accountsActivityPage.datesTo.clear();
        accountsActivityPage.datesFrom.sendKeys(from);
        accountsActivityPage.datesTo.sendKeys(to);
    }

    @When("clicks search")
    public void clicks_search() {
        BrowserUtils.waitForPageToLoad(2);
        accountsActivityPage.findButton.click();
        BrowserUtils.waitForPageToLoad(2);
    }

    @Then("results table should only show transactions dates between {string} to {string}")
    public void results_table_should_only_show_transactions_dates_between_to(String from, String to) {
        for(WebElement each: accountsActivityPage.datesFromTable){
            Assert.assertTrue(each.getText().compareTo(from)>=0);
            Assert.assertTrue(each.getText().compareTo(to)<=0);
        }
    }

    @Given("the results should be sorted by most recent date")
    public void the_results_should_be_sorted_by_most_recent_date() {
        List<String> list = BrowserUtils.getListOfString(accountsActivityPage.datesFromTable);
        for(int i=0; i<list.size()-1; i++){
            Assert.assertTrue(list.get(i).compareTo(list.get(i+1))>=0);
        }
    }

    @Then("the results table should only not contain transactions dated {string}")
    public void the_results_table_should_only_not_contain_transactions_dated(String date) {
        List<String> list = BrowserUtils.getListOfString(accountsActivityPage.datesFromTable);
        System.out.println(list);
        System.out.println(date);
        Assert.assertFalse(list.contains(date));
    }

    @When("the user enters description {string}")
    public void the_user_enters_description(String descriptionName) {
        BrowserUtils.waitForPageToLoad(2);
        accountsActivityPage.description.clear();
        accountsActivityPage.description.sendKeys(descriptionName);
    }

    @Then("results table should only show descriptions containing {string}")
    public void results_table_should_only_show_descriptions_containing(String string) {
        if(accountsActivityPage.descriptionsFromTable.size()==0) Assert.assertTrue(false);
        for (WebElement each : accountsActivityPage.descriptionsFromTable){
            Assert.assertTrue(each.getText().contains(string));
        }
    }

    @Then("results table should not show descriptions containing {string}")
    public void results_table_should_not_show_descriptions_containing(String string) {
        for (WebElement each : accountsActivityPage.descriptionsFromTable){
            Assert.assertFalse(each.getText().contains(string));
        }
    }





    @Then("results table should show at least one result under {string}")
    public void results_table_should_show_at_least_one_result_under(String string) {
        if(string.equals("Withdrawal")) {
            Assert.assertTrue(  !  BrowserUtils.getListOfString(accountsActivityPage.withdrawalsFromTable).isEmpty()  );
        } else{
            Assert.assertTrue(  !  BrowserUtils.getListOfString(accountsActivityPage.depositsFromTable).isEmpty()  );
        }
    }

    @When("user selects type {string}")
    public void user_selects_type(String string) {
        BrowserUtils.waitForPageToLoad(2);
        Select select = new Select(accountsActivityPage.type);
        select.selectByVisibleText(string);
    }

    @Then("results table should show no result under {string}")
    public void results_table_should_show_no_result_under(String string) {
        if(string.equals("Withdrawal")) {
            Assert.assertTrue(  BrowserUtils.getListOfString(accountsActivityPage.withdrawalsFromTable).isEmpty()  );
        } else{
            Assert.assertTrue(  BrowserUtils.getListOfString(accountsActivityPage.depositsFromTable).isEmpty()  );
        }
    }

//    public static void main(String[] args) {
//        //                     a                    z
//        System.out.println("2012-09-06".compareTo("2012-09-01")>0);
//        System.out.println("6".compareTo("6")>=0);
//
//
//    }

}
