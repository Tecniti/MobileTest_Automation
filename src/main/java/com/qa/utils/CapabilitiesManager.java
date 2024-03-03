package com.qa.utils;

import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

/***
 * This closs is used to initialize the capability in IOS/Android,Capabilities are must to open the mobile application.
 * Thesse capabilities are like:
     * PLATFORM_NAME
     * UDID
     * DEVICE_NAME
     * appPackage
     * appActivity
     * androidAppUrl
 */
public class CapabilitiesManager {
    //TestUtils utils = new TestUtils();
    private String emulator;

    public DesiredCapabilities getCaps() throws IOException {
        GlobalParams params = new GlobalParams();
        Properties props = new PropertyManager().getProps();

        //setting up all the capabilities for Android & IOS device which includes PLATFORM_NAME,UDID,DEVICE_NAME,appPackage,appactivity
        try{
            params.log().info("getting capabilities");
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, params.getPlatformName());
            caps.setCapability(MobileCapabilityType.UDID, params.getUDID());
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, params.getDeviceName());


            switch(params.getPlatformName()){
                case "Android":
                    caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, props.getProperty("androidAutomationName"));
                    caps.setCapability("appPackage", props.getProperty("androidAppPackage"));
                    caps.setCapability("appActivity", props.getProperty("androidAppActivity"));
                    String androidAppUrl = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
                            + File.separator + "resources" + File.separator + "apps" + File.separator + "Mobile.app.2.7.1.apk";
                     caps.setCapability("app", androidAppUrl);
                    break;
                case "iOS":
                    caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, props.getProperty("iOSAutomationName"));
                    String iOSAppUrl = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
                            + File.separator + "resources" + File.separator + "apps" + File.separator + "IOS_MobileApp.app";
                    params.log().info("appUrl is" + iOSAppUrl);
                    caps.setCapability("bundleId", props.getProperty("iOSBundleId"));
                     caps.setCapability("app", iOSAppUrl);
                    break;
            }
            return caps;
        } catch(Exception e){
            e.printStackTrace();
            params.log().fatal("Failed to load capabilities. ABORT!!" + e.toString());
            throw e;
        }
    }
}
