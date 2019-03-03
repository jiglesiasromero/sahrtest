package com.spotahome.rtest.front.pageObjects;

import com.spotahome.rtest.front.utils.WebDriverUtil;
import org.apache.log4j.Logger;
import org.joda.time.LocalDate;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.assertTrue;

/**
 * Created by jiglesias on 12/02/17.
 */
public class HomePageObject extends  BasePageObject {

    private static Logger log = Logger.getLogger(HomePageObject.class);

    @FindBy(className = "ga-home-select-city")
    private WebElement citySelector;

    @FindBy(className = "ga-home-explore")
    private WebElement exploreButton;

    @FindBy(className = "city-selector--date__from")
    private WebElement dateFromInput;

    @FindBy(className = "city-selector--date__to")
    private WebElement dateToInput;

    @FindBy(className = "pika-select-year")
    private WebElement pikalendarYear;

    @FindBy(className = "pika-select-month")
    private WebElement pikalendarMonth;

    @FindBy(xpath = "//select[@class='pika-select pika-select-year']")
    private WebElement selectYearInElement;

    @FindBy(xpath = "//select[@class='pika-select pika-select-month']")
    private WebElement selectMonthInElement;

    @FindBy(className = "pika-table")
    private WebElement tableDaysInElement;

    @FindBy(xpath = "//span[@id='city-selector']/div/div/div[2]//select[@class='pika-select pika-select-year']")
    private WebElement selectYearOutElement;

    @FindBy(xpath = "//span[@id='city-selector']/div/div/div[2]//select[@class='pika-select pika-select-month']")
    private WebElement selectMonthOutElement;

    @FindBy(xpath = "//span[@id='city-selector']/div/div/div[2]//table")
    private WebElement tableDaysOutElement;

    public void selectARandomCity() {
        log.info("[log-PageObjects] " + this.getClass().getSimpleName() + " - Start selectARandomCity method");

        List<WebElement> options = citySelector.findElements(By.xpath("./option"));
        int index = (int) (Math.random() * options.size());

        Select select = new Select(citySelector);
        select.selectByIndex(index);

        log.info("[log-PageObjects] " + this.getClass().getSimpleName() + " - End selectARandomCity method");
    }

    public void clickOnExploreButton() {
        log.info("[log-PageObjects] " + this.getClass().getSimpleName() + " - Start clickOnExploreButton method");

        exploreButton.click();

        log.info("[log-PageObjects] " + this.getClass().getSimpleName() + " - End clickOnExploreButton method");
    }

    public void isOnHomePage() {
        log.info("[log-PageObjects] " + this.getClass().getSimpleName() + " - Start isOnHomePage method");

        assertTrue("ERROR: City selector doesn't displayed", citySelector.isDisplayed());
        assertTrue("ERROR: Explore button doesn't displayed", exploreButton.isDisplayed());
        assertTrue("ERROR: Calendar selector Date In doesn't displayed", dateFromInput.isDisplayed());
        assertTrue("ERROR: Calendar selector Date Out doesn't displayed", dateToInput.isDisplayed());

        log.info("[log-PageObjects] " + this.getClass().getSimpleName() + " - End isOnHomePage method");
    }

    public void chooseRandomsDateInAndDateOut() {
        log.info("[log-PageObjects] " + this.getClass().getSimpleName() + " - Start chooseRandomsDateInAndDateOut method");

        LocalDate today = new LocalDate();
        // Random date between today and 364 days after it
        LocalDate randomDateIn = today.plusDays(ThreadLocalRandom.current().nextInt(0, 365));
        // Random date between 31 and 129 days after randomDateIn
        LocalDate randomDateOut = randomDateIn.plusDays(ThreadLocalRandom.current().nextInt(31, 130));

        openPikalendarDateInHomePage();

        // Select date from
        selectYearMonthAndDayInCalendar(randomDateIn, selectYearInElement, selectMonthInElement, tableDaysInElement);

        // Select date to
        selectYearMonthAndDayInCalendar(randomDateOut, selectYearOutElement, selectMonthOutElement, tableDaysOutElement);

        log.info("[log-PageObjects] " + this.getClass().getSimpleName() + " - End chooseRandomsDateInAndDateOut method");
    }

    private void openPikalendarDateInHomePage() {
        log.info("[log-PageObjects] " + this.getClass().getSimpleName() + " - Start openPikalendarDateInHomePage method");

        dateFromInput.click();

        log.info("[log-PageObjects] " + this.getClass().getSimpleName() + " - End openPikalendarDateInHomePage method");
    }

}
