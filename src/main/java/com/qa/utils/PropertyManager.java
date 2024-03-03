package com.qa.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/***
 * This class is containg the function to Read the values from config file
 */
public class PropertyManager {
    private static Properties props = new Properties();
    GlobalParams utils = new GlobalParams();

    public Properties getProps() throws IOException {
        InputStream is = null;

        String propsFileName = "config.properties";
        if(props.isEmpty()){
            try{
                utils.log().info("loading config properties");
                is = getClass().getClassLoader().getResourceAsStream(propsFileName);
                props.load(is);
            } catch (IOException e) {
                e.printStackTrace();
                utils.log().fatal("Failed to load config properties. ABORT!!" + e.toString());
                throw e;
            } finally {
                if(is != null){
                    is.close();
                }
            }
        }
        return props;
    }
}
