package com.zerobank.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {
                "src/test/resources/features/AccountActivity.feature",
                "src/test/resources/features/AccountActivityNavigation.feature",
                "src/test/resources/features/AccountSummary.feature",
                "src/test/resources/features/AddNewPayee.feature",
                "src/test/resources/features/FindTransactions.feature",
                "src/test/resources/features/Login.feature",
                "src/test/resources/features/PayBills.feature",
                "src/test/resources/features/PurchaseForeignCurrency.feature",
        },
        glue = "com/zerobank/step_definitions",
        dryRun = false,
        plugin = {
                "html:target/default-cucumber-reports",
                "json:target/cucumber.json",
                "rerun:target/rerun.txt"
        }
)
public class RegressionRunner {

}