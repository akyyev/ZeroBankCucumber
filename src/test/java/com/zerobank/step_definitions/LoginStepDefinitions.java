package com.zerobank.step_definitions;

import com.zerobank.pages.BasePage;
import com.zerobank.pages.LoginPage;

import com.zerobank.utilities.ConfigurationReader;
import com.zerobank.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class LoginStepDefinitions {

    LoginPage loginPage = new LoginPage();

    @Given("user is on login page")
    public void user_is_on_login_page() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
    }

    @And("user logs in with username {string} and password {string}")
    public void user_logs_in_with_username_and_password(String username, String password) {
        loginPage.login(username, password);
    }

    @Then("{string} page should be displayed")
    public void page_should_be_displayed(String page) {
        int i=0;
        switch(page){
            case "Account Summary": i=0; break;
            case "Account Activity": i=1; break;
            case "Transfer Funds": i=2; break;
            case "Pay Bills": i=3; break;
            case "My Money Map": i=4; break;
            case "Online Statements": i=5; break;
            default: throw new RuntimeException("No such page!");
        }
        Assert.assertEquals(page, (new BasePage()).navigationTabs.get(i).getText());
    }


    @Then("{string} error message needs to be displayed")
    public void error_message_needs_to_be_displayed(String string) {
        Assert.assertEquals(string, loginPage.errorMessage.getText().trim());
    }

}