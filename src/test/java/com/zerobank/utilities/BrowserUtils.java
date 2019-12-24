package com.zerobank.utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

public class BrowserUtils {

    public static void wait(int seconds) {
        try{
            Thread.sleep(1000*seconds);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    /**
     * Waits for element to be not stale
     * StaleElementReferenceException --> Indicates that a reference to an element is now "stale"
     * --- the element no longer appears on the DOM of the page.
     * @param element
     */
    public static void waitForStaleElement(WebElement element){
        int y=0;
        while(y<=15){
            try{
                element.isDisplayed();
                break;
            } catch (StaleElementReferenceException st){
                y++;
                try{
                    Thread.sleep(200);
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * Waits for provided element to be visible on the page (explicit wait)
     *
     * @param element
     * @param timeToWaitInSec
     * @return
     */
    public static WebElement waitForVisibility(WebElement element, int timeToWaitInSec){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeToWaitInSec);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }


    /**
     * Clicks on an element using JavaScript
     *
     * @param element
     */
    public static void clickWithJS(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        js.executeScript("arguments[0].click();", element);
    }

    /**
     * Waits for provided element to be clickable
     *
     * @param element
     * @param timeToWaitInSec
     * @return
     */
    public static WebElement waitForClickability(WebElement element, int timeToWaitInSec){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeToWaitInSec);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static String getDate(){
        return new SimpleDateFormat("-yyyy-MM-dd-HH-mm").format(new Date());
    }
    /*
     * takes screenshot
     * @param name
     * take a name of a test and returns a path to screenshot takes
     */
    public static String getScreenshot(String name) {
        // name the screenshot with the current date time to avoid duplicate name
        //String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        String date = getDate();

        // TakesScreenshot ---> interface from selenium which takes screenshots
        TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);
        // full path to the screenshot location
        String target = System.getProperty("user.dir") + "/test-output/Screenshots/" + name + date + ".png";

        File finalDestination = new File(target);

        // save the screenshot to the path given
        try {
            FileUtils.copyFile(source, finalDestination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return target;
    }

    /**
     * Wait 15 seconds with polling interval of 200 milliseconds then click
     *
     * @param webElement of element
     */
    public static void clickWithWait(WebElement webElement) {
        Wait wait = new FluentWait(Driver.getDriver())
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(200))
                .ignoring(NoSuchElementException.class)
                .ignoring(ElementNotVisibleException.class)
                .ignoring(ElementClickInterceptedException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(WebDriverException.class);
        WebElement element = (WebElement) wait.until((Function<WebDriver, WebElement>) driver -> webElement);
        try {
            element.click();
        } catch (WebDriverException e) {
            System.out.println(e.getMessage());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            element.click();
        }
    }

    /**
     * waits for backgrounds processes on the browser to complete
     *
     * @param timeOutInSeconds
     */
    public static void waitForPageToLoad(long timeOutInSeconds) {
        ExpectedCondition<Boolean> expectation = driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
        ExpectedCondition<Boolean> expectation2 = driver -> ((JavascriptExecutor) driver).executeScript("return jQuery.active == 0").equals(true);
        try {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeOutInSeconds);
            wait.until(expectation);
            wait.until(expectation2);
        } catch (Throwable error) {
            error.printStackTrace();
        }
    }

    /**
     * Wait for proper page title.
     * @param pageTitle
     */
    public static void waitForPageTitle(String pageTitle) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 10);
        wait.until(ExpectedConditions.titleIs(pageTitle));

    }



    //To print test case number and verifying expected and actual result are the same
    public static void print(int testCaseNumber,String expected, String actual){
        System.out.println("Test-Case: "+testCaseNumber+ " "+(expected.equals(actual)? "Pass" : "Fail"));
        System.out.println("Expected result:\t"+expected);
        System.out.println("Actual result:\t\t"+actual+"\n");
    }

    //To print expected and actual result for only one test case (Overloading method)
    public static void print(String expected, String actual){
        System.out.println("Expected result:\t"+expected);
        System.out.println("Actual result:\t\t"+actual+"\n");
    }

    /**
     * This method will convert list of WebElements into list of string
     *
     * @param listOfWebElements
     * @return list of strings
     */
    public static List<String> getListOfString(List<WebElement> listOfWebElements) {
        List<String> listOfStrings = new ArrayList<>();
        for (WebElement element : listOfWebElements) {
            String value = element.getText().trim();
            //If there is no text
            //do not add this black text into list
            if(value.length()>0){
                listOfStrings.add(value);
            }
        }
        return listOfStrings;
    }
}

