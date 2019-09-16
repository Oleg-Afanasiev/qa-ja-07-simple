package com.telesens.framework.test;

import com.google.common.io.Files;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

import java.io.File;
import java.io.IOException;

public class WebDriverEventListenerImpl extends AbstractWebDriverEventListener {
    private static final Logger LOG =  LogManager.getLogger(BaseTest.class);

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        LOG.debug("Try find by {}", by);
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        LOG.debug("Found by {}", by);
    }

    @Override
    public void beforeNavigateTo(String url, WebDriver driver) {
        LOG.debug("Navigate to url {}", url);
    }

    @Override
    public void onException(Throwable err, WebDriver driver) {
        LOG.error("Error occurs: {}", err.getMessage());

        makeScreenshot(driver);
    }

    private void makeScreenshot(WebDriver driver) {
        File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenName = "screen_" + System.currentTimeMillis()+".png";
        String screenPath =  "D:/temp/screenshots/" + screenName;
        File screen = new File(screenPath);
        try {
            Files.copy(tmp, screen);
        } catch (IOException exc) {
            LOG.error("Error copying screenshot from '{}' to '{}'. Details: {}",
                    tmp, screen, exc);
        }
    }
}
