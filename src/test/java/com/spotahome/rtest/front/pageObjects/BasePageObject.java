package com.spotahome.rtest.front.pageObjects;

import com.spotahome.rtest.front.utils.WebDriverUtil;
import org.apache.log4j.Logger;
import org.joda.time.LocalDate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.spotahome.rtest.front.driver.DriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.sql.Driver;
import java.util.List;

/**
 * Created by jiglesias on 12/02/17.
 */
public class BasePageObject {

    protected final WebDriver driver;
    private static Logger log = Logger.getLogger(BasePageObject.class);

    public BasePageObject(){
        log.info("[log-PageObjects] " + this.getClass().getSimpleName() + " - Start BasePageObject method");

        this.driver = DriverFactory.getWebDriver();

        log.info("[log-PageObjects] " + this.getClass().getSimpleName() + " - End BasePageObject method");
    }

    public void goToHome() {
        log.info("[log-PageObjects] " + this.getClass().getSimpleName() + " - Start goToHome method");

        this.driver.get(DriverFactory.properties.getProperty("homePageUrl"));

        log.info("[log-PageObjects] " + this.getClass().getSimpleName() + " - End goToHome method");
    }

    protected void selectYearMonthAndDayInCalendar(LocalDate date, WebElement pikaSelectYear, WebElement pikaSelectMonth, WebElement pikaTable) {
        log.info("[log-PageObjects] " + this.getClass().getSimpleName() + " - Start selectYearMonthAndDayInCalendar method");
        log.info("Selecting date: " + date.toString());

        String yearIn = String.valueOf(date.getYear());
        String monthIn = String.valueOf(date.getMonthOfYear()-1);
        int dayIn = date.getDayOfMonth();

        selectOptionPikalendar(pikaSelectYear, yearIn);

        selectOptionPikalendar(pikaSelectMonth, monthIn);

        List<WebElement> columns = pikaTable.findElements(By.xpath("//td[not(@class='is-disabled')]"));

        for (WebElement cell: columns) {
            if (cell.getText().equals(String.valueOf(dayIn))) {
                cell.findElement(By.xpath(".//button")).click();
                log.info("Date select: "+date.toString());
                log.info("[log-PageObjects] " + this.getClass().getSimpleName() + " - End selectYearMonthAndDayInCalendar method");
                break;
            }
        }

        WebDriverUtil.sleep(1);
    }

    protected void selectOptionPikalendar(WebElement element, String value){
        element.click();
        selectByValue(element, value);
    }

    protected void selectByValue(WebElement element, String value){
        Select select = new Select(element);
        select.selectByValue(value);
    }

    public void close() {
        log.info("[log-PageObjects] " + this.getClass().getSimpleName() + " - Start close method");

        DriverFactory.close();

        log.info("[log-PageObjects] " + this.getClass().getSimpleName() + " - End close method");
    }

}
