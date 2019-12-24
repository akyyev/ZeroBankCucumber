package com.zerobank.step_definitions;

import com.zerobank.pages.PayBillsPage;
import com.zerobank.utilities.BrowserUtils;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class PayBillsStepDefinitions {

    PayBillsPage payBillsPage = new PayBillsPage();

    @Then("user selects {string} as payee type and {string} as account type")
    public void user_selects_as_payee_type_and_as_account_type(String payeeName, String accountName) {
        payBillsPage.setPayeeOptions(payeeName);
        payBillsPage.setAccountOptions(accountName);
    }


    @Then("user enters {string}  as amount of money for payment and selects day as {string}")
    public void user_enters_as_amount_of_money_for_payment_and_selects_day_as(String amount, String day) {
        payBillsPage.amountOfMoneyInputBox.sendKeys(amount);
        payBillsPage.date.sendKeys(day);
    }

    @Then("verify pay operation message {string} is displayed")
    public void verify_pay_operation_message_is_displayed(String message) {
        payBillsPage.payButton.click();
        Assert.assertEquals(message, payBillsPage.verificationMessage.getText());
    }

}
