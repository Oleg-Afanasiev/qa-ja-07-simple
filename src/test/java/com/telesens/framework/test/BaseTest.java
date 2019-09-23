package com.telesens.framework.test;

import com.telesens.automationpractice.AuthTests;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    private static final Logger LOG =  LogManager.getLogger(BaseTest.class);
    private static final Logger LOG_TRAFFIC = LogManager.getFormatterLogger("TRAFFIC");

    protected EventFiringWebDriver driver;
    private BrowserMobProxy proxy;

    @Parameters({"browser"})
    @BeforeClass(alwaysRun = true)
    public void setUp(@Optional("chrome") String browser) throws Exception {
        System.setProperty("webdriver.chrome.driver", "d:/distribs/selenium/chromedriver.exe");
        System.setProperty("webdriver.gecko.driver", "d:/distribs/selenium/geckodriver.exe");
//        driver = new ChromeDriver()

        proxy = new BrowserMobProxyServer();
        proxy.start(0);

        // get the Selenium proxy object
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
        ChromeOptions options = new ChromeOptions();
        // configure it as a desired capability
        options.setCapability(CapabilityType.PROXY, seleniumProxy);
        proxy.newHar("automation");

        driver = new EventFiringWebDriver(new ChromeDriver(options));
        driver.register(new WebDriverEventListenerImpl());
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void logTestStart(Method method, Object[] params) {
        LOG.info("Start test {} with parameters {}",
                method.getName(), Arrays.toString(params));
    }

    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method method) {
        LOG.info("Finish test {}", method.getName());
    }


    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();

        Har har = proxy.endHar();
        har.getLog().getEntries().forEach(l -> LOG_TRAFFIC.debug(l.getResponse().getStatus() + ":" + l.getRequest().getUrl()));
    }
}
