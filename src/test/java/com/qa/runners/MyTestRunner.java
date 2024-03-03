package com.qa.runners;

import com.qa.utils.DriverManager;
import com.qa.utils.GlobalParams;
import com.qa.utils.ServerManager;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;
import org.apache.logging.log4j.ThreadContext;

import org.junit.runner.RunWith;
import org.junit.AfterClass;
import org.junit.BeforeClass;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/HSBC_TestReport/report.html"}
        ,features = {"src/test/resources"}
        ,glue = {"com.qa.stepdef"}
        ,dryRun=false
        ,monochrome=true
)

public class MyTestRunner {
    @BeforeClass
    /**
        1. Initialize global parameter for IOS/ Android which includes: platformName,Udid,deviceName,systemPort,chromeDriverPort
        2. Starting Appium server
        3. Starting device driver which include for Android & IOS device which includes PLATFORM_NAME,UDID,DEVICE_NAME,appPackage,appactivity
     */
    public static void initilize() throws Exception {
        GlobalParams params = new GlobalParams();
        params.initializeGlobalParams();

        ThreadContext.put("ROUTINGKEY", params.getPlatformName() + "_" + params.getDeviceName());
        new ServerManager().startServer();
        new DriverManager().initializeDriver();
    }

    @AfterClass
    public static void quit() {
        DriverManager driverManager = new DriverManager();
        if (DriverManager.getDriver() != null) {
            DriverManager.getDriver().quit();
            driverManager.setDriver(null);
        }
        ServerManager serverManager = new ServerManager();
        if (serverManager.getServer() != null) {
            serverManager.getServer().stop();
        }
    }
}
