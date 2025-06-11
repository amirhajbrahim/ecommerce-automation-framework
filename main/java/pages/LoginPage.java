package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(id = "user-name")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(css = "[data-test='error']")
    private WebElement errorMessage;

    public LoginPage enterUsername(String username) {
        sendKeys(usernameField, username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        sendKeys(passwordField, password);
        return this;
    }

    public ProductPage clickLogin() {
        click(loginButton);
        return new ProductPage();
    }

    public LoginPage login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        click(loginButton);
        return this;
    }

    public boolean isLoginSuccessful() {
        return getCurrentUrl().contains("inventory");
    }

    public String getErrorMessage() {
        return isElementDisplayed(errorMessage) ? getText(errorMessage) : "";
    }

    public boolean isLoginPageDisplayed() {
        return getCurrentUrl().contains("saucedemo.com") &&
                isElementDisplayed(loginButton);
    }
}