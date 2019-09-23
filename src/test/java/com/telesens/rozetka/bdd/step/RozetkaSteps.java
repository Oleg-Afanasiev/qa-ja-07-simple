package com.telesens.rozetka.bdd.step;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class RozetkaSteps {

    private String baseUrl = "https://rozetka.com.ua/";
    private TestHelper testHelper;
    private WebDriver driver;
    private String mainMenuCSS = "body > app-root > div > div:nth-child(2) > app-rz-main-page > div > aside > main-page-sidebar > sidebar-fat-menu > div > ul > li:nth-child(1) > a";

    @Before
    public void setUp() {
        testHelper = new TestHelper();
        driver = testHelper.getDriver();
    }


    @Given("Я нахожусь на домашней странице")
    public void IAmOnHomePage() {
        driver.get(baseUrl);
    }

    @Then("я выбираю {string}")
    public void selectMenuItem(String menuItem) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.cssSelector(mainMenuCSS)))
                .perform();
    }

    @Then("подождем {int} сек")
    public void wait(int duration) throws InterruptedException {
        Thread.sleep(duration*1000);
    }

    @And("нажимаю пункт {string}")
    public void clickSubMenuItem(String subMenuItem) {
        driver.findElement(By.partialLinkText(subMenuItem)).click();
    }

    @After
    public void tearDown() {
       driver.quit();
    }
}
