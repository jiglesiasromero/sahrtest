package com.spotahome.rtest.front.steps;

import com.spotahome.rtest.front.TestContext;
import com.spotahome.rtest.front.driver.DriverFactory;
import com.spotahome.rtest.front.pageObjects.HomePageObject;
import com.spotahome.rtest.front.pageObjects.RoomSelectionPageObject;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by jiglesias on 14/02/17.
 */
public class RoomSelectionSteps {

    private TestContext<RoomSelectionPageObject> context = TestContext.getInstance();

    public static RoomSelectionPageObject roomSelectionPageObject = null;

    public static RoomSelectionPageObject getRoomSelectionPageObject() {
        if (roomSelectionPageObject == null)
            roomSelectionPageObject = PageFactory.initElements(DriverFactory.getWebDriver(), RoomSelectionPageObject.class);
        return roomSelectionPageObject;
    }

    @When("^I choose a room$")
    public void i_choose_a_room() throws Throwable {
        getRoomSelectionPageObject().chooseRandomRoomFromGrid();
    }

    @Then("^I am on room selection page$")
    public void i_am_on_room_selection_page() throws Throwable {
        getRoomSelectionPageObject().isOnRoomSelectionPage();
    }
}
