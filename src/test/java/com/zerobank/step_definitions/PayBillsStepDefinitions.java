package com.zerobank.step_definitions;

import com.zerobank.pages.PayBillsPage;
import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.Driver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Map;

public class PayBillsStepDefinitions {

    PayBillsPage payBillsPage = new PayBillsPage();
    int a;

    @Then("verify pay operation error message {string} is displayed")
    public void verify_pay_operation_error_message_is_displayed(String errorMessage) {
        payBillsPage.payButton.click();
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) Driver.getDriver();
        String locator;
        if(a==1){
            locator = "sp_amount";
        } else {
            locator = "sp_date";
        }
        String actual = (String) javascriptExecutor.executeScript("return JSON.stringify(document.getElementById('"+locator+"').validationMessage);");
        Assert.assertEquals("\""+errorMessage+"\"", actual);
    }

    @Then("user enters {string}  as amount of money for payment and selects day as {string}")
    public void user_enters_as_amount_of_money_for_payment_and_selects_day_as(String amount, String day) {
        payBillsPage.amountOfMoneyInputBox.sendKeys(amount);
        payBillsPage.date.sendKeys(day);
        a = (amount.isEmpty())? 1 : 2;
    }

    @Then("user selects {string} as payee type and {string} as account type")
    public void user_selects_as_payee_type_and_as_account_type(String payeeName, String accountName) {
        payBillsPage.setPayeeOptions(payeeName);
        payBillsPage.setAccountOptions(accountName);
    }




    @Then("verify pay operation message {string} is displayed")
    public void verify_pay_operation_message_is_displayed(String message) {
        payBillsPage.payButton.click();
        Assert.assertEquals(message, payBillsPage.verificationMessage.getText());
    }

    @Then("the user accesses the Add New Payee tab")
    public void the_user_accesses_the_Add_New_Payee_tab() {
        BrowserUtils.waitForPageToLoad(2);
        payBillsPage.addNewPayeeSubTab.click();
    }

    @Then("creates new payee using following information")
    public void creates_new_payee_using_following_information(Map<String, String> dataTable) {
        payBillsPage.createPayee(dataTable.get("Payee name"),
                                    dataTable.get("Payee Address"),
                                    dataTable.get("Account"),
                                    dataTable.get("Payee details"));
    }

    @Then("message {string} should be displayed")
    public void message_should_be_displayed(String string) {
        Assert.assertEquals(string, payBillsPage.verificationMessage.getText());
    }


    @Then("the user accesses the Purchase Foreign Currency tab")
    public void the_user_accesses_the_Purchase_Foreign_Currency_tab() {
        payBillsPage.purchaseForeignCurrencySubTab.click();
    }

    @Then("following currencies should be available")
    public void following_currencies_should_be_available(List<String> dataTable) {
        BrowserUtils.waitForPageToLoad(2);
        Select select = new Select(payBillsPage.currencyDropDown);
        Assert.assertEquals(dataTable, BrowserUtils.getListOfString(select.getOptions()));
    }

    @When("user tries to calculate cost without entering value")
    public void user_tries_to_calculate_cost_without_entering_value() {
        BrowserUtils.waitForPageToLoad(2);
        Select select = new Select(payBillsPage.currencyDropDown);
        select.selectByIndex((int)(Math.random()*10+1));
        payBillsPage.radioSelect.get((int)(Math.random()*2)).click();
        payBillsPage.purchaseButton.click();

    }

    @Then("error message should be displayed")
    public void error_message_should_be_displayed() {
        String expectedErrorMessage = "Please, ensure that you have filled all the required fields with valid values.";
        Alert alert = Driver.getDriver().switchTo().alert();
        Assert.assertEquals(expectedErrorMessage, alert.getText());
    }

    @When("user tries to calculate cost without selecting a currency")
    public void user_tries_to_calculate_cost_without_selecting_a_currency() {
        BrowserUtils.waitForPageToLoad(2);
        payBillsPage.purchaseAmountInputBox.sendKeys((int)(Math.random()*1000)+"");
        payBillsPage.radioSelect.get((int)(Math.random()*2)).click();
        payBillsPage.purchaseButton.click();
    }



}
