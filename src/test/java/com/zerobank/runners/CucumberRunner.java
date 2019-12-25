package com.zerobank.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com/zerobank/step_definitions",                           //to show where step definitions are located
        dryRun = false,
        plugin = {"html:target/default-cucumber-reports", "json:target/cucumber.json"}
       //, tags = "@foreign_currency"

)
public class CucumberRunner {


}
