package com.telesens.rozetka.bdd.step;

import com.telesens.framework.test.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class TestHelper extends BaseTest {

    public WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", "d:/distribs/selenium/chromedriver.exe");
        driver = new EventFiringWebDriver(new ChromeDriver());
        return driver;
    }
}
