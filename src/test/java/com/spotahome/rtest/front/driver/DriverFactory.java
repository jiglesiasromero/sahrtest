package com.spotahome.rtest.front.driver;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by jiglesias on 12/02/17.
 */
public class DriverFactory {

    private static WebDriver driver = null;
    public static Properties properties;
    private static Logger log = Logger.getLogger(DriverFactory.class);

    public static void setupDriver() {
        log.info("[log-Utils] DriverFactory - Start setupDriver method");

        properties = new Properties();
        String webdriverPath;
        try {
            properties.load(new FileReader("src/test/resources/test.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String browser = properties.getProperty("browser");
        String os = properties.getProperty("os");

        if (driver == null) {
            if(browser.equalsIgnoreCase("Chrome")){
                webdriverPath = properties.getProperty("webdriverChromePath");
                if (os.equalsIgnoreCase("Linux") || os.equalsIgnoreCase("Mac")){
                    System.setProperty("webdriver.chrome.driver", webdriverPath);
                } else {
                    System.setProperty("webdriver.chrome.driver", webdriverPath + ".exe");
                }
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
                options.addArguments("-incognito");

                DesiredCapabilities capabilities = DesiredCapabilities.chrome();
                capabilities.setCapability(ChromeOptions.CAPABILITY, options);

                driver = new ChromeDriver(capabilities);
            }

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        }

        log.info("[log-Utils] DriverFactory - End setupDriver method");
    }

    public static WebDriver getWebDriver() {
        log.info("[log-Utils] DriverFactory - Start getWebDriver method");

        if (driver == null) {
            setupDriver();
        }

        log.info("[log-Utils] DriverFactory - End getWebDriver method");
        return driver;
    }

    public static void close() {
        log.info("[log-Utils] DriverFactory - Start close method");
        driver.quit();
        driver = null;
        log.info("[log-Utils] DriverFactory - End close method");
    }
}
