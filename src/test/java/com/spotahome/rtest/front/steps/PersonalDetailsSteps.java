package com.spotahome.rtest.front.steps;

import com.spotahome.rtest.front.TestContext;
import com.spotahome.rtest.front.driver.DriverFactory;
import com.spotahome.rtest.front.pageObjects.PersonalDetailsPageObject;
import com.spotahome.rtest.front.pageObjects.RoomSummaryPageObject;
import cucumber.api.java.en.Then;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by jiglesias on 15/02/17.
 */
public class PersonalDetailsSteps {

    private TestContext<PersonalDetailsPageObject> context = TestContext.getInstance();

    public static PersonalDetailsPageObject personalDetailsPageObject = null;

    public static PersonalDetailsPageObject getPersonalDetailsPageObject() {
        if (personalDetailsPageObject == null)
            personalDetailsPageObject = PageFactory.initElements(DriverFactory.getWebDriver(), PersonalDetailsPageObject.class);
        return personalDetailsPageObject;
    }

    @Then("^I am on Personal details page$")
    public void i_am_on_Personal_details_page() throws Throwable {
        getPersonalDetailsPageObject().isOnPersonalDetailsPage();
    }
}
