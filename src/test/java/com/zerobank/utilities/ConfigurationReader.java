package com.zerobank.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class ConfigurationReader {
    //this class will be responsible for loading properties file and will provide access
    //to values based on key names
    //we use Properties class to load custom .properties files


    private static Properties configFile;

    static{
        //without handling checked exception, we cannot run a code(FileNotFoundException, IOException...)
        try {
            //provides access to file
            // try/catch blocks stands for handling exceptions
            //if exception occurs, code inside a catch block will be executed
            //without handling checked exception, we can not run a code

            FileInputStream fileInputStream = new FileInputStream("configuration.properties");

            //initialization of properties object
            configFile = new Properties();
            //loading configuration.properties file
            configFile.load(fileInputStream);
            fileInputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("File not found or loaded!");
        }
    }



    public static String getProperty(String key) {
        return configFile.getProperty(key);
    }

}
