package com.telesens.selenide.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.page;

public class AccountPage {

    @FindBy(css = "#header > div.nav > div > div > nav > div:nth-child(1) > a")
    private SelenideElement userNameLink;

    @FindBy(partialLinkText = "Sign out")
    private SelenideElement logoutLink;

    public HomePage logout() {
        logoutLink.click();
        return page(HomePage.class);
    }

    public AccountPage isLogIn(String username) {
        userNameLink.shouldHave(text(username));
        return page(AccountPage.class);
    }
}
