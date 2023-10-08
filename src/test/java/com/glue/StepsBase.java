package com.glue;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class StepsBase {

    static WebDriver driver;

    /***
     * Opens chrome browser before each scenario
     */
    @Before
    public static void before_each() {
        driver = new ChromeDriver();
    }

    /***
     * Takes screenshot after each FAILED scenario (before browser closing)
     * @param scenario
     */
    @After(order = 1)
    public void screenshotOnFailure(Scenario scenario) {

        if (scenario.isFailed()) {

            TakesScreenshot ts = (TakesScreenshot) driver;

            byte[] src = ts.getScreenshotAs(OutputType.BYTES);
            scenario.attach(src, "image/png", "screenshot");
        }
    }

    /***
     * Closes browser after each scenario
     */
    @After(order = 0)
    public static void closeAndQuit() {
        driver.close();
        driver.quit();
    }
}
