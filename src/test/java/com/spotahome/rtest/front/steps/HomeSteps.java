package com.spotahome.rtest.front.steps;

import com.spotahome.rtest.front.TestContext;
import com.spotahome.rtest.front.driver.DriverFactory;
import com.spotahome.rtest.front.pageObjects.HomePageObject;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by jiglesias on 12/02/17.
 */
public class HomeSteps {

    private TestContext<HomePageObject> context = TestContext.getInstance();

    public static HomePageObject homePageObject = null;

    public static HomePageObject getHomePageObject() {
        if (homePageObject == null)
            homePageObject = PageFactory.initElements(DriverFactory.getWebDriver(), HomePageObject.class);
        return homePageObject;
    }

    @When("^I select a random city in home$")
    public void i_select_a_random_city_in_home() throws Throwable {
        getHomePageObject().selectARandomCity();
    }

    @When("^I click on Explore button$")
    public void i_click_on_Explore_button() throws Throwable {
        getHomePageObject().clickOnExploreButton();
    }

    @When("^I choose randoms date in and date out$")
    public void i_choose_randoms_date_in_and_date_out() throws Throwable {
        getHomePageObject().chooseRandomsDateInAndDateOut();
    }

    @Then("^I am on home page$")
    public void i_am_on_home_page() throws Throwable {
        getHomePageObject().isOnHomePage();
    }
}
