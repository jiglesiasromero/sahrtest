package com.spotahome.rtest.front.utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by jiglesias on 13/02/17.
 */
public class WebDriverUtil {

    private static Logger log = Logger.getLogger(WebDriverUtil.class);

    public static boolean waitUntilElementClickable(WebDriver driver, WebElement element, long seconds) {
        log.info("[log-Utils] WebDriverUtil - Start waitUntilElementClickable method");

        WebDriverWait w = new WebDriverWait(driver, seconds);
        boolean retVal = true;

        try {
            w.until(ExpectedConditions.elementToBeClickable(element));
        } catch (TimeoutException e) {
            retVal = false;

            log.error("The element: " + element.toString() + " is missing in the DOM. Waiting time: " + seconds
                    + " seconds");
        }

        log.info("[log-Utils] WebDriverUtil - End waitUntilElementClickable method");

        return retVal;
    }

    public static boolean waitUntilVisible(WebDriver driver, By selector, long seconds) {
        log.info("[log-Utils] WebDriverUtil - Start waitUntilVisible method");

        WebDriverWait w = new WebDriverWait(driver, seconds);
        boolean retVal = true;

        try {
            w.until(ExpectedConditions.visibilityOfElementLocated(selector));
        } catch (TimeoutException e) {
            retVal = false;

            log.error("The element: " + selector.toString() + " is not visible in the page. Waiting time: " + seconds
                    + " seconds");
        }

        log.info("[log-Utils] WebDriverUtil - End waitUntilVisible method");

        return retVal;
    }

    public static boolean waitUntilDisappear(WebDriver driver, By selector, long seconds) {
        log.info("[log-Utils] WebDriverUtil - Start waitUntilDisappear method");

        WebDriverWait w = new WebDriverWait(driver, seconds);
        boolean retVal = true;

        try {
            w.until(ExpectedConditions.invisibilityOfElementLocated(selector));
        } catch (TimeoutException e) {
            retVal = false;

            log.error("The element: " + selector.toString() + " is not visible in the page. Waiting time: " + seconds
                    + " seconds");
        }

        log.info("[log-Utils] WebDriverUtil - End waitUntilDisappear method");

        return retVal;
    }

    public static boolean wait(WebDriver driver, By selector, long seconds) {
        log.info("[log-Utils] WebDriverUtil - Start wait method");

        WebDriverWait w = new WebDriverWait(driver, seconds);
        boolean retVal = true;

        try {
            w.until(ExpectedConditions.presenceOfElementLocated(selector));
        } catch (TimeoutException e) {
            retVal = false;

            log.error("The element: " + selector.toString() + " is missing in the DOM. Waiting time: " + seconds
                    + " seconds");
        }

        log.info("[log-Utils] WebDriverUtil - End wait method");

        return retVal;
    }

    public static void sleep(int seconds) {
        log.info("[log-Utils] WebDriverUtil - Start sleep method");

        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            log.error("A thread has interrupted the current thread", e);
        }

        log.info("[log-Utils] WebDriverUtil - End sleep method");
    }
}
