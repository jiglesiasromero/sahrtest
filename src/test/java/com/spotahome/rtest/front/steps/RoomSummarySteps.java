package com.spotahome.rtest.front.steps;

import com.spotahome.rtest.front.TestContext;
import com.spotahome.rtest.front.driver.DriverFactory;
import com.spotahome.rtest.front.pageObjects.RoomSelectionPageObject;
import com.spotahome.rtest.front.pageObjects.RoomSummaryPageObject;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by jiglesias on 14/02/17.
 */
public class RoomSummarySteps {

    private TestContext<RoomSummaryPageObject> context = TestContext.getInstance();

    public static RoomSummaryPageObject roomSummaryPageObject = null;

    public static RoomSummaryPageObject getRoomSummaryPageObject() {
        if (roomSummaryPageObject == null)
            roomSummaryPageObject = PageFactory.initElements(DriverFactory.getWebDriver(), RoomSummaryPageObject.class);
        return roomSummaryPageObject;
    }

    @When("^I choose randoms date in and date out availables$")
    public void i_choose_randoms_date_in_and_date_out_availables() throws Throwable {
        getRoomSummaryPageObject().chooseRandomsDateInAndDateOutAvailables();
    }

    @When("^I click on Book now! button$")
    public void i_click_on_Book_now_button() throws Throwable {
        getRoomSummaryPageObject().clickOnBookNowButton();
    }

    @Then("^I am on room summary page$")
    public void i_am_on_room_summary_page() throws Throwable {
        getRoomSummaryPageObject().isOnRoomSummaryPage();
    }
}
