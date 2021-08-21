package com.zipcode.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/resources",
        glue = "com/zipcode/step_definitions",
        dryRun = false,
        plugin = {
                "html:target/cucumber-report.html",
                "json:target/json-report.json"
        }
)
public class CukesRunner {
}
