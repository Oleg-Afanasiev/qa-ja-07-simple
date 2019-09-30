package com.telesens.selenide;

import com.telesens.selenide.page.HomePage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AutomationTests {

    @BeforeClass
    public void setUp() {
        baseUrl = "http://automationpractice.com/index.php";
        timeout = 10000;
        startMaximized = true;
    }

    @Test
    public void loginTest() {
        open(baseUrl);
        $(byLinkText("Sign in")).click();
//        $(byId("email")).sendKeys("oleg.kh81@gmail.com");
        $(byId("email")).setValue("oleg.kh81@gmail.com");
        $(byId("passwd")).setValue("123qwerty");
        $(byId("SubmitLogin")).click();
        $(byText("OLEG AFANASIEV")).shouldBe();
        $(byPartialLinkText("Sign out")).click();
    }

    @Test(dataProvider = "authDataProvider")
    public void loginTestUsingPages(String login, String password, String username) {
        open(baseUrl, HomePage.class)
                .clickSignIn()
                .inputLogin(login)
                .inputPassword(password)
                .submit()
                .isLogIn(username)
                .logout();
    }

    @DataProvider
    public Object[][] authDataProvider() {
        return new Object[][] {
                {"oleg.kh81@gmail.com", "123qwerty", "OLEG AFANASIEV"}
        };
    }

}
