package tests;

import org.testng.annotations.*;
import utils.DriverManager;
import utils.ConfigReader;
import pages.LoginPage;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class BaseTest {
    protected LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        String browser = ConfigReader.getProperty("browser");
        boolean headless = Boolean.parseBoolean(ConfigReader.getProperty("headless"));
        test;

        DriverManager.setDriver(browser, headless);
        DriverManager.getDriver().get(ConfigReader.getProperty("base.url"));

        loginPage = new LoginPage();
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) DriverManager.getDriver())
                .getScreenshotAs(OutputType.BYTES);
    }
}
