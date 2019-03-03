package com.spotahome.rtest.front.pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.Assert.assertTrue;

/**
 * Created by jiglesias on 15/02/17.
 */
public class PersonalDetailsPageObject extends BasePageObject {

    private static Logger log = Logger.getLogger(PersonalDetailsPageObject.class);

    @FindBy(className = "booking-title")
    private WebElement informationText;

    @FindBy(className = "booking-steps")
    private WebElement bookingSteps;

    public void isOnPersonalDetailsPage() {
        log.info("[log-PageObjects] " + this.getClass().getSimpleName() + " - Start BasePageObject method");

        assertTrue("ERROR: Information text is not displayed", informationText.isDisplayed());
        assertTrue("ERROR: Booking steps are not displayed", bookingSteps.isDisplayed());

        log.info("[log-PageObjects] " + this.getClass().getSimpleName() + " - End BasePageObject method");
    }
}
