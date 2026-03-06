package org.example.stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.example.base.BaseTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;

public class Hooks {
    private static final Logger log = LoggerFactory.getLogger(BaseTest.class);

    @Before
    public void before() throws MalformedURLException {
        BaseTest.initializeDriver();
        log.info("Test Started");
    }

    @After
    public void after(){
        log.info("Test Ended");
        BaseTest.getDriver().quit();
        log.info("Driver quit successfully");
    }
}
