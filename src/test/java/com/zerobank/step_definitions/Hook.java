package com.zerobank.step_definitions;

import com.zerobank.utilities.Driver;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hook {

    @Before
    public void setup(){
        System.out.println("-------------------------");
        System.out.println("Test setup!");
        Driver.getDriver().manage().window().maximize();
    }

    @After
    public void teardown(Scenario scenario){
        if(scenario.isFailed()){
            System.out.println("Test failed!");
            TakesScreenshot takesScreenshot = (TakesScreenshot) Driver.getDriver();
            byte[] screenshot = takesScreenshot.getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        } else {
            System.out.println("Test Ended");
        }
        System.out.println("--------------------------");
        Driver.close();
    }
}
