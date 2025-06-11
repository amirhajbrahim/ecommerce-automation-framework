package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.ProductPage;
import utils.DataProviders;
import io.qameta.allure.*;

@Epic("Authentication")
@Feature("User Login")
public class LoginTests extends BaseTest {

    @Test(description = "Verify successful login with valid credentials")
    @Description("Test verifies that user can login successfully with valid username and password")
    @Severity(SeverityLevel.CRITICAL)
    public void testValidLogin() {
        // Test data
        String username = "standard_user";
        String password = "secret_sauce";

        // Test execution
        ProductPage productPage = loginPage.login(username, password)
                .clickLogin();

        // Verification
        Assert.assertTrue(loginPage.isLoginSuccessful(),
                "User should be redirected to products page after successful login");
    }

    @Test(dataProvider = "invalidLoginData",
            description = "Verify login fails with invalid credentials")
    @Description("Test verifies that appropriate error messages are shown for invalid login attempts")
    @Severity(SeverityLevel.HIGH)
    public void testInvalidLogin(String username, String password, String expectedError) {
        // Test execution
        loginPage.login(username, password);

        // Verification
        Assert.assertFalse(loginPage.isLoginSuccessful(),
                "Login should fail with invalid credentials");
        Assert.assertTrue(loginPage.getErrorMessage().contains(expectedError),
                "Expected error message should be displayed");
    }

    @Test(description = "Verify locked user cannot login")
    @Description("Test verifies that locked out user receives appropriate error message")
    @Severity(SeverityLevel.MEDIUM)
    public void testLockedUserLogin() {
        // Test data
        String username = "locked_out_user";
        String password = "secret_sauce";

        // Test execution
        loginPage.login(username, password);

        // Verification
        Assert.assertFalse(loginPage.isLoginSuccessful(),
                "Locked user should not be able to login");
        Assert.assertTrue(loginPage.getErrorMessage().contains("locked out"),
                "Locked out error message should be displayed");
    }

    @DataProvider(name = "invalidLoginData")
    public Object[][] getInvalidLoginData() {
        return new Object[][] {
                {"", "", "Username is required"},
                {"standard_user", "", "Password is required"},
                {"", "secret_sauce", "Username is required"},
                {"invalid_user", "secret_sauce", "Username and password do not match"},
                {"standard_user", "wrong_password", "Username and password do not match"}
        };
    }
}