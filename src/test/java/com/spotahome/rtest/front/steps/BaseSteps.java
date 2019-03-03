package com.spotahome.rtest.front.steps;

import com.spotahome.rtest.front.TestContext;
import com.spotahome.rtest.front.driver.DriverFactory;
import com.spotahome.rtest.front.pageObjects.BasePageObject;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by jiglesias on 12/02/17.
 */
public class BaseSteps {

    private TestContext context = TestContext.getInstance();
    public static BasePageObject basePageObject = null;

    public static BasePageObject getBasePageObject() {
        if (basePageObject == null)
            basePageObject = PageFactory.initElements(DriverFactory.getWebDriver(), BasePageObject.class);
        return basePageObject;
    }

    @Before
    public void before(){
        this.context.setPageObject(new BasePageObject());
    }

    @Given("^I open the home page$")
    public void i_open_the_home_page() throws Throwable {
        getBasePageObject().goToHome();
    }

    @After("@web")
    public void afterAllIsSaidAndDone() {
        this.context.getPageObject().close();
    }
}
