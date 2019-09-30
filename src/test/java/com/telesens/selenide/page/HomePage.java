package com.telesens.selenide.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.page;

public class HomePage {

    @FindBy(linkText = "Sign in")
    private SelenideElement signInLink;

    public AuthPage clickSignIn() {
        signInLink.click();
        return page(AuthPage.class);
    }
}
