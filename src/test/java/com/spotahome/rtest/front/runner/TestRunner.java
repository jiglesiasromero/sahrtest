package com.spotahome.rtest.front.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by jiglesias on 12/02/17.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        format = {"pretty", "html:target/cucumber"},
        glue = {"com.spotahome.rtest.front.steps"},
        strict = true,
        features = {"src/test/resources/features"}
)
public class TestRunner {
}
