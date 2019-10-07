package com.telesens.automationpractice.page;

import com.telesens.framework.page.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AuthPage extends BasePage {
    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "passwd")
    private WebElement passwField;

    @FindBy(id ="SubmitLogin")
    private WebElement submitButton;

    @FindBy(xpath = "//*[@id='center_column']/div[1]/ol/li")
    private WebElement errorMessageBlock;

    public AuthPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Step("enter email '{0}'")
    public AuthPage enterEmail(String email) {
        inputTextField(emailField, email);

        return this;
    }

    @Step("enter password '{0}'")
    public AuthPage enterPassword(String passw) {
        inputTextField(passwField, passw);
        return this;
    }

    public BasePage pressSubmit(){
        submitButton.click();
        return this;
    }

    @Step("check message error")
    public String getErrorMessage(){
        return errorMessageBlock.getText();
    }
}
