package com.zerobank.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.URL;

public class Driver {

    private static WebDriver driver;

    //by making it private, we cannot do this: Driver obj = new Driver();
    private Driver(){

    }

    //Allows to create copy of webDriver object during the run time. This class allows us to avoid forks
    private static ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();


    public static WebDriver getDriver(){
        // if webDriver object was not created yet

        if(driverPool.get() == null){
            //create webDriver object based on browser value form properties file
            String browserParamFromEnv = System.getProperty("browser");
            String browser = browserParamFromEnv == null ? ConfigurationReader.getProperty("browser") : browserParamFromEnv;
            switch (browser){
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driverPool.set(new ChromeDriver());
                    break;
                case "chrome_headless":
                    WebDriverManager.chromedriver().setup();
                    driverPool.set(new ChromeDriver(new ChromeOptions().setHeadless(true)));
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set( new FirefoxDriver());
                    break;
                case "firefox_headless":
                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver(new FirefoxOptions().setHeadless(true)));
                    break;
                case "safari":
                    if(!System.getProperty("os.name").toLowerCase().contains("mac"))
                        throw new WebDriverException("Your OS doesn't support Safari");
                    WebDriverManager.getInstance(SafariDriver.class).setup();
                    driverPool.set(new SafariDriver());
                    break;
                case "ie":
                    if(!System.getProperty("os.name").toLowerCase().contains("windows"))
                        throw new WebDriverException("Your OS doesn't support Internet Explorer");
                    WebDriverManager.iedriver().setup();
                    driverPool.set(new InternetExplorerDriver());
                    break;
                case "edge":
                    if(!System.getProperty("os.name").toLowerCase().contains("windows"))
                        throw new WebDriverException("Your OS doesn't support edge");
                    WebDriverManager.edgedriver().setup();
                    driverPool.set(new EdgeDriver());
                    break;

                case "remote_chrome":
                    try {
                        String node = ConfigurationReader.getProperty("node");
                        ChromeOptions chromeOptions = new ChromeOptions();
                        chromeOptions.setCapability("platform", Platform.ANY);
                        driverPool.set(new RemoteWebDriver(new URL(node+"/wd/hub"),chromeOptions));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "remote_firefox":
                    try {
                        String node = ConfigurationReader.getProperty("node");
                        FirefoxOptions firefoxOptions = new FirefoxOptions();
                        firefoxOptions.setCapability("platform", Platform.ANY);
                        driverPool.set(new RemoteWebDriver(new URL(node+"/wd/hub"),firefoxOptions));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                default:
                    //if the browser is wrong, throw exception
                    //no browser will be opened
                    throw new RuntimeException("Wrong browser type!");
            }
        }

        return driverPool.get();
    }

    public static void close(){
       driverPool.get().quit();
       driverPool.remove();
    }


}


/*
    I believe it's not very cool to create new driver in every class.
    So what if we will have one central place, that will create driver.
    In this way, every test will use same driver. We will make process of drive switch match easier.

    In OOP we have Design Patterns.
    It's a proven solution for specific task.
    One of the most popular design pattern for webDriver in selenium is Singleton.
    Singleton - means single object of something for entire project.

    What's the point? this object will be static and we can ensure that all tests use same driver object. So we can create test suits.
    Also, whenever we need to use driver, we will just call it from Driver class. And it's gonna be same driver for everyone.
    Otherwise, new webDriver object = new browser.
    With this design, driver will be sharable and easy to access.
    What are the conventions of singleton pattern?
        - private static instance
        - private constructor (to prevent class instantiation)
        - public getter that returns private static instance

                        class Driver{
                            //only one private static instance
                            private static WebDriver driver;
                            //to prevent class instantiation
                            private Driver(){
                            }
                            public static WebDriver get(){
                            //if object was not created yet - create it
                                if(driver==null){
                                    driver = new ChromeDriver();
                                }
                                return driver;
                            }
                        }

 */