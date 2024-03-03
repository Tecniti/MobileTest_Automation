package com.qa.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Storing the global parameter
 */
public class GlobalParams {
    private static ThreadLocal<String> platformName = new ThreadLocal<String>();
    private static ThreadLocal<String> udid = new ThreadLocal<String>();
    public static ThreadLocal<String> deviceName = new ThreadLocal<String>();

    public void setPlatformName(String platformName1){

        platformName.set(platformName1);
    }

    public String getPlatformName(){
        return platformName.get();
    }

    public String getUDID() {

        return udid.get();
    }

    public void setUDID(String udid2) {
        udid.set(udid2);
    }

    public String getDeviceName() {

        return deviceName.get();
    }

    public void setDeviceName(String deviceName2) {

        deviceName.set(deviceName2);
    }

    /**
     *    Initilize these global parameter: platformName,Udid,deviceName,systemPort,chromeDriverPort
     */
    public void initializeGlobalParams(){
        GlobalParams params = new GlobalParams();
        params.setPlatformName(System.getProperty("platformName", "Android"));
        params.setUDID(System.getProperty("udid", "emulator-5554"));
        params.setDeviceName(System.getProperty("deviceName", "Device"));
    }
    public Logger log() {
        return LogManager.getLogger(Thread.currentThread().getStackTrace()[2].getClassName());
    }

}
