package org.example.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.example.utils.ConfigReader;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        tags = "",
        features = "src/test/resources/features/android",
        glue = {"org.example.stepdefinitions", "org.example.hooks"},
        plugin = {
                "pretty",
                "html:target/cucumber-report.html",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        }
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @BeforeSuite
    public void configureExecution() {
        String tags = ConfigReader.getProperty("execution.tags");
        String parallel = ConfigReader.getProperty("execution.parallel");
        String threads = ConfigReader.getProperty("execution.threadCount");

        if (tags != null && !tags.isEmpty()) {
            System.setProperty("cucumber.filter.tags", tags);
        }

        if ("true".equalsIgnoreCase(parallel)) {
            System.setProperty("dataproviderthreadcount", threads);
            System.out.println("Running in PARALLEL with " + threads + " threads");
        } else {
            System.setProperty("dataproviderthreadcount", "1");
            System.out.println("Running in SEQUENTIAL mode");
        }
    }

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
