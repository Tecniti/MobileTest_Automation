package com.qa.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import java.io.IOException;

/***
 * This class is managing the driver for android & IOS application.
 * Androd/IOS Driver are started after starting the appium server and setting the capabilities.
 * So, This class is consuming the capabilies & server manager class.
 */
public class DriverManager {
    public static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();
    GlobalParams utils = new GlobalParams();

    public static AppiumDriver getDriver(){
        return driver.get();
    }

    public void setDriver(AppiumDriver driver2){

        driver.set(driver2);
    }

    public void initializeDriver() throws Exception {
        AppiumDriver driver = null;
        GlobalParams params = new GlobalParams();
        PropertyManager props = new PropertyManager();

        if(driver == null){
            try{
                utils.log().info("initializing Appium driver");
                switch(params.getPlatformName()){
                    case "Android":
                        driver = new AndroidDriver(new ServerManager().getServer().getUrl(), new CapabilitiesManager().getCaps());
                        break;
                    case "iOS":
                        driver = new IOSDriver(new ServerManager().getServer().getUrl(), new CapabilitiesManager().getCaps());
                        break;
                }
                if(driver == null){
                    throw new Exception("driver is null. ABORT!!!");
                }
                utils.log().info("Driver is initialized");
                this.driver.set(driver);
            } catch (IOException e) {
                e.printStackTrace();
                utils.log().fatal("Driver initialization failure. ABORT !!!!" + e.toString());
                throw e;
            }
        }

    }
}
