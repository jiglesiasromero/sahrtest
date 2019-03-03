package com.spotahome.rtest.front.pageObjects;

import com.spotahome.rtest.front.driver.DriverFactory;
import com.spotahome.rtest.front.utils.WebDriverUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.assertTrue;

/**
 * Created by jiglesias on 14/02/17.
 */
public class RoomSelectionPageObject extends BasePageObject {

    private static Logger log = Logger.getLogger(RoomSelectionPageObject.class);

    @FindBy(className = "ga-search-form-datepicker-from")
    private WebElement dateFromInput;

    @FindBy(className = "ga-search-form-datepicker-to")
    private WebElement dateToInput;

    @FindAll(@FindBy(xpath = "//div[@class='l-list']/div"))
    private List<WebElement> roomsList;

    @FindBy(id = "themap")
    private WebElement map;

    @FindBy(className = "filter-group--price-range")
    private WebElement priceRangeFilter;

    private By spinerLoadingMap = By.xpath("//div[@id='spinner-list-loader']");

    public void chooseRandomRoomFromGrid() {
        log.info("[log-PageObjects] " + this.getClass().getSimpleName() + " - Start chooseRandomRoomFromGrid method");

        WebDriverUtil.waitUntilDisappear(DriverFactory.getWebDriver(), spinerLoadingMap, 20);
        WebElement choosenRoom = roomsList.get(ThreadLocalRandom.current().nextInt(0, roomsList.size()));
        choosenRoom.click();

        WebDriverUtil.sleep(2);
        //ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
        //DriverFactory.getWebDriver().switchTo().window(tabs2.get(1));
        //Actions action= new Actions(DriverFactory.getWebDriver());
        //action.keyDown(Keys.CONTROL).sendKeys(Keys.TAB).build().perform();
        String parentHandle = driver.getWindowHandle();
        for (String winHandle : driver.getWindowHandles()) { //Gets the new window handle
            System.out.println(winHandle);
            driver.switchTo().window(winHandle);        // switch focus of WebDriver to the next found window handle (that's your newly opened window)
        }

        log.info("[log-PageObjects] " + this.getClass().getSimpleName() + " - End chooseRandomRoomFromGrid method");
    }

    public void isOnRoomSelectionPage() {
        log.info("[log-PageObjects] " + this.getClass().getSimpleName() + " - Start isOnRoomSelectionPage method");

        assertTrue("ERROR: Date in selector is not displayed", dateFromInput.isDisplayed());
        assertTrue("ERROR: Date out selector is not displayed", dateToInput.isDisplayed());
        assertTrue("ERROR: Price range filter is not displayed", priceRangeFilter.isDisplayed());
        assertTrue("ERROR: There aren't rooms displayed", roomsList.size() > 0);
        assertTrue("ERROR: Map is not displayed", map.isDisplayed());

        log.info("[log-PageObjects] " + this.getClass().getSimpleName() + " - End isOnRoomSelectionPage method");
    }
}
