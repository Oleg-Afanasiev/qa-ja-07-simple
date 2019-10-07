package com.telesens.automationpractice;

import com.telesens.automationpractice.listener.TestListener;
import com.telesens.automationpractice.page.AuthPage;
import com.telesens.framework.page.BasePage;
import com.telesens.framework.test.BaseTest;
import io.qameta.allure.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.FileReader;
import java.time.Duration;
import java.util.Properties;
import java.util.function.Function;

import static com.telesens.automationpractice.page.HomePage.startFromHome;

@Epic("Regression tests")
@Feature("Auth tests")
@Listeners(TestListener.class)
public class AuthTests extends BaseTest {
    private static final Logger LOG =  LogManager.getLogger(AuthTests.class);
    private static final String DEFAULT_PATH = "src/main/resources/automationpractice.properties";
    private String baseUrl;

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        String automationPracticePath = System.getProperty("cfgAP");
        if (automationPracticePath == null)
            automationPracticePath = DEFAULT_PATH;

        Properties prop = new Properties();
        prop.load(new FileReader(automationPracticePath));
        baseUrl = prop.getProperty("base.url");
    }

    public void testAuthSuccess() throws Exception {
        driver.get(baseUrl);
        driver.findElement(By.linkText("Sign in")).click();
        WebElement element = driver.findElement(By.id("email"));
        element.click();
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys("oleg.kh81@gmail.com");
        driver.findElement(By.id("passwd")).click();
        driver.findElement(By.id("passwd")).clear();
        driver.findElement(By.id("passwd")).sendKeys("123qwerty");
//         driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Forgot your password?'])[1]/following::span[1]")).click();
        driver.findElement(By.id("SubmitLogin")).click(); // #SubmitLogin
//        Assert.assertEquals("", "Oleg....");
    }

    @Test(dataProvider = "authErrorMessageProvider", description = "Invalid auth scenario")
    @Description("Description: Auth test with wrong login or password and checking msg error  ")
    @Issue("AP-122")
    @Severity(SeverityLevel.CRITICAL)
    @Story("invalid login and password tests")
    public void testAuthErrorMessage(String login, String passw, String expectedError) {
        BasePage basePage = startFromHome(driver, baseUrl)
                .clickSignIn()
                .enterEmail(login)
                .enterPassword(passw)
                .pressSubmit();

        String actualError = ((AuthPage) basePage).getErrorMessage();
        Assert.assertEquals(actualError, expectedError);
    }

    @Test
    @Ignore
    public void testRollover() {
        driver.get(baseUrl);
        WebElement slider = driver.findElement(By.id("homeslider"));
        String[] styles = {"", "", ""};

        Function<WebDriver, Boolean> rollingComplete =
                driver1 -> {
                    // логика условия
                    System.out.println(slider.getAttribute("style"));
                    for (int i = 0; i < styles.length; i++) {
                        if (styles[i].isEmpty()) {
                            styles[i] = slider.getAttribute("style");
                            return false;
                        }
                    }


                    for (int i = 0; i < styles.length; i++) {
                        if (styles[i].equals(slider.getAttribute("style"))) {
                            return true;
                        }
                    }
                    return false;
                };

        new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofSeconds(3))
                .until(rollingComplete);
    }



    protected boolean waitForJSandJQueryToLoad() {

        WebDriverWait wait = new WebDriverWait(driver, 30);

        // wait for jQuery to load
        Function<WebDriver, Boolean> jQueryLoad = driver -> {
            try {
                return ((Long)((JavascriptExecutor)driver).executeScript("return jQuery.active") == 0);
            }
            catch (Exception e) {
                // no jQuery present
                return true;
            }
        };

        // wait for Javascript to load
        Function<WebDriver, Boolean> jsLoad = driver -> ((JavascriptExecutor)driver).executeScript("return document.readyState")
                .toString().equals("complete");

        return wait.until(jQueryLoad) && wait.until(jsLoad);
    }

    @DataProvider(name="authErrorMessageProvider")
    public Object[][] authErrorMessageProvider() {
        return new Object[][]{
                {"log", "passw", "Invalid email address."},
                {"vasya", "123", "Invalid email address1."}
        };
//        List<String[]> parts = new ArrayList<>();
//            while(hasNext()) {
//                parts.add(line.split(","));
//            }

//        return parts.toArray(new Object[0][0]);
    }
}
