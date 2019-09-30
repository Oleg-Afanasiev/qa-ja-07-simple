package com.telesens.selenide.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.page;

public class AuthPage {

    @FindBy(id="email")
    private SelenideElement loginField;

    @FindBy(id="passwd")
    private SelenideElement passwordField;

    @FindBy(id="SubmitLogin")
    private SelenideElement submitButton;

    public AuthPage inputLogin(String login) {
        loginField.setValue(login);
        return page(AuthPage.class);
    }

    public AuthPage inputPassword(String password) {
        passwordField.setValue(password);
        return page(AuthPage.class);
    }

    public AccountPage submit() {
        submitButton.click();
        return page(AccountPage.class);
    }
}
