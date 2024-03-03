package com.qa.pages;

import com.qa.utils.DriverManager;
import com.qa.utils.GlobalParams;
import io.appium.java_client.*;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;

/***
 * This Class is used to contain all the common utility which is helping in the step defination
 */
public class BasePage {
    static FileReader reader;
    static Properties p;

    private static final Object NATIVE_APP = null;
    public static final long WAIT = 20;
    private static AppiumDriver<?> driver;
    GlobalParams utils = new GlobalParams();

    public BasePage() {
        this.driver = new DriverManager().getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
    }
    public void tapByElement (MobileElement e) {
        new TouchAction(driver)
                .tap(tapOptions().withElement(element(e)))
                .waitAction(waitOptions(ofMillis(250))).perform();
    }

    public void swipeByElements (MobileElement startElement, MobileElement endElement) {
        int startX = startElement.getLocation().getX() + (startElement.getSize().getWidth() / 2);
        int startY = startElement.getLocation().getY() + (startElement.getSize().getHeight() / 2);
        int endX = endElement.getLocation().getX() + (endElement.getSize().getWidth() / 2);
        int endY = endElement.getLocation().getY() + (endElement.getSize().getHeight() / 2);
        new TouchAction(driver)
                .press(point(startX,startY))
                .waitAction(waitOptions(ofMillis(1000)))
                .moveTo(point(endX, endY))
                .release().perform();
    }

    public void waitForVisibility(MobileElement e) {
        WebDriverWait wait = new WebDriverWait(driver, WAIT);
        wait.until(ExpectedConditions.visibilityOf(e));
    }

    public void waitForVisibility(By e) {
        WebDriverWait wait = new WebDriverWait(driver, WAIT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(e));
    }

    public void clear(MobileElement e) {
        waitForVisibility(e);
        e.clear();
    }

    public void click(MobileElement e) {
        waitForVisibility(e);
        e.click();
    }

    public void click(MobileElement e, String msg) {
        waitForVisibility(e);
        utils.log().info(msg);
        e.click();
    }

    public void click(By e, String msg) {
        waitForVisibility(e);
        utils.log().info(msg);
        driver.findElement(e).click();
    }

    public void sendKeys(MobileElement e, String txt, String msg) {
        waitForVisibility(e);
        utils.log().info(msg);
        e.sendKeys(txt);
    }

    public String getAttribute(MobileElement e, String attribute) {
        waitForVisibility(e);
        return e.getAttribute(attribute);
    }

    public String getText(MobileElement e, String msg) {
        String txt;
        switch (new GlobalParams().getPlatformName()) {
            case "Android":
                txt = getAttribute(e, "text");
                break;
            case "iOS":
                txt = getAttribute(e, "label");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + new GlobalParams().getPlatformName());
        }
        utils.log().info(msg + txt);
        return txt;
    }

    public static String properties(String fileName, String value) {
        String propValue = null;
        try {
            reader = new FileReader("AppConfig" + "//" + fileName + ".properties");
            p = new Properties();
            p.load(reader);
            propValue = p.getProperty(value);
        } catch (FileNotFoundException e) {
            new RuntimeException("File is not found : " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
            new RuntimeException("IO exception occured");
        }
        return propValue;
    }
}